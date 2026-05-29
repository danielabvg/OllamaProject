package com.ai.ollama.OllamaClient.Modes;

import com.ai.ollama.OllamaClient.Context.AgenteConversacional;
import com.ai.ollama.OllamaClient.Evaluation.BenchmarkPipeline;
import com.ai.ollama.OllamaClient.Evaluation.EvaluationResult;
import com.ai.ollama.OllamaClient.PromptingEngine.Impl.*;
import com.ai.ollama.OllamaClient.Strategy.IAStrategy;
import com.ai.ollama.OllamaClient.Template.PromptBuilder;
import com.ai.ollama.OllamaClient.Template.PromptConfig;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BenchmarkMode {
    public void ejecutarAutomatico(

            String pregunta,

            IAStrategy[] modelos
    ) {

        GeneradorPrompt generador =
                new GeneradorPrompt();

        PromptConfig config =
                generador.generar(
                        pregunta
                );

        PromptStrategyDetector detector =
                new PromptStrategyDetector();

        PromptStrategy strategy =
                detector.detectar(
                        pregunta
                );

        PromptBuilder builder =
                new PromptBuilder()

                        .conRol(
                                config.getRol()
                        )

                        .conInstrucciones(
                                config.getInstrucciones()
                        )

                        .conEntrada(
                                config.getEntrada()
                        );

        String promptFinal =
                strategy
                        .definirEstructuraPrompt(
                                builder
                        );

        config.setPromptFinal(
                promptFinal
        );

        ejecutar(
                config,
                strategy,
                modelos
        );
    }

    public void ejecutarManual(

            String promptManual,

            IAStrategy[] modelos
    ) {

        PromptStrategyDetector detector =
                new PromptStrategyDetector();

        PromptStrategy strategy =
                detector.detectar(
                        promptManual
                );

        PromptConfig config =
                new PromptConfig(

                        "Usuario Manual",

                        "Prompt manual",

                        promptManual
                );

        config.setPromptFinal(
                promptManual
        );

        ejecutar(
                config,
                strategy,
                modelos
        );
    }

    public void ejecutar(

            PromptConfig config,

            PromptStrategy strategy,

            IAStrategy[] modelos
    ) {

        List<ModelBenchmarkResult> resultados =
                new ArrayList<>();

        for (IAStrategy modelo : modelos) {

            resultados.add(

                    ejecutarModelo(
                            modelo,
                            config,
                            strategy
                    )
            );
        }

        if (modelos.length > 1) {

            mostrarComparacion(
                    resultados
            );
        }
    }

    private ModelBenchmarkResult ejecutarModelo(

            IAStrategy estrategia,

            PromptConfig config,

            PromptStrategy strategy
    ) {

        AgenteConversacional agente =
                new AgenteConversacional(
                        estrategia
                );

        long inicio =
                System.currentTimeMillis();

        String respuesta =
                agente.preguntar(config);

        long fin =
                System.currentTimeMillis();

        long latency =
                fin - inicio;

        String segundaRespuesta =
                agente.preguntar(config);

        String referencia =
                """
                arquitectura modular orientada
                a objetos utilizando patrones
                de diseño y principios SOLID
                """;

        BenchmarkPipeline pipeline =
                new BenchmarkPipeline();

        EvaluationResult resultado =
                pipeline.ejecutarEvaluacion(

                        respuesta,

                        referencia,

                        segundaRespuesta,

                        latency
                );

        System.out.println(

                """
        
                =====================================
        
                RESULTADOS DE LA EVALUACIÓN DEL MODELO %s
        
                =====================================
        
                """

                        .formatted(

                                estrategia.getNombreModelo()

                        )

        );

        System.out.println();

        System.out.println(
                "Prompt Strategy: "
                        + strategy.getClass().getSimpleName()
        );

        System.out.println(
                "Semantic Similarity: "
                        + resultado.getSemanticSimilarity()
                        + "%"
        );

        System.out.println(
                "Consistency Score: "
                        + resultado.getConsistencyScore()
                        + "%"
        );

        System.out.println(
                "Hallucination Risk: "
                        + resultado.getHallucinationRisk()
                        + "%"
        );

        System.out.println(
                "Latency Score: "
                        + resultado.getLatencyScore()
                        + "%"
        );

        System.out.println(
                "Final Composite Score: "
                        + resultado.getFinalScore()
                        + "%"
        );

        System.out.println(
                "Quality Analysis: "
                        + resultado.getQuality()
        );

        System.out.println(

                """
        
                =====================================
        
                RESPUESTA DEL MODELO %s
        
                =====================================
        
                """

                        .formatted(

                                estrategia.getNombreModelo()

                        )

        );

        System.out.println();

        System.out.println(
                respuesta
        );

        return new ModelBenchmarkResult(

                estrategia.getNombreModelo(),

                resultado.getSemanticSimilarity(),

                resultado.getConsistencyScore(),

                resultado.getHallucinationRisk(),

                resultado.getLatencyScore(),

                resultado.getFinalScore()
        );
    }

    private void mostrarComparacion(

            List<ModelBenchmarkResult> resultados
    ) {

        ModelBenchmarkResult mejorSemantic =
                resultados.stream()
                        .max(
                                Comparator.comparingDouble(
                                        ModelBenchmarkResult::getSemanticSimilarity
                                )
                        )
                        .orElse(null);

        ModelBenchmarkResult mejorConsistency =
                resultados.stream()
                        .max(
                                Comparator.comparingDouble(
                                        ModelBenchmarkResult::getConsistencyScore
                                )
                        )
                        .orElse(null);

        ModelBenchmarkResult mejorLatency =
                resultados.stream()
                        .max(
                                Comparator.comparingDouble(
                                        ModelBenchmarkResult::getLatencyScore
                                )
                        )
                        .orElse(null);

        ModelBenchmarkResult mejorHallucination =
                resultados.stream()
                        .min(
                                Comparator.comparingDouble(
                                        ModelBenchmarkResult::getHallucinationRisk
                                )
                        )
                        .orElse(null);

        ModelBenchmarkResult ganador =
                resultados.stream()
                        .max(
                                Comparator.comparingDouble(
                                        ModelBenchmarkResult::getFinalScore
                                )
                        )
                        .orElse(null);

        System.out.println("""

            =====================================
            COMPARACIÓN MULTIMODELO
            =====================================
            """);

        System.out.printf(
                "%-30s %-20s%n",
                "Evaluación",
                "Mejor Modelo"
        );

        System.out.println(
                "--------------------------------------------------------"
        );

        System.out.printf(
                "%-30s %-20s%n",
                "Semantic Similarity",
                mejorSemantic.getModelo()
        );

        System.out.printf(
                "%-30s %-20s%n",
                "Consistency Score",
                mejorConsistency.getModelo()
        );

        System.out.printf(
                "%-30s %-20s%n",
                "Hallucination Risk",
                mejorHallucination.getModelo()
        );

        System.out.printf(
                "%-30s %-20s%n",
                "Latency Score",
                mejorLatency.getModelo()
        );

        System.out.printf(
                "%-30s %-20s%n",
                "Final Composite Score",
                ganador.getModelo()
        );

        System.out.println("""

            =====================================
            GANADOR GENERAL
            =====================================
            """);

        System.out.println(
                "Modelo ganador: "
                        + ganador.getModelo()
        );

        System.out.println(
                "Final Composite Score: "
                        + ganador.getFinalScore()
        );
    }
}
