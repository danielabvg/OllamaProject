package com.ai.ollama.ollamaclient.modes;

import com.ai.ollama.ollamaclient.context.AgenteConversacional;
import com.ai.ollama.ollamaclient.evaluation.BenchmarkPipeline;
import com.ai.ollama.ollamaclient.evaluation.EvaluationResult;
import com.ai.ollama.ollamaclient.prompting.PromptStrategy;
import com.ai.ollama.ollamaclient.strategy.IAStrategy;
import com.ai.ollama.ollamaclient.template.PromptConfig;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("java:S106")
public class BenchmarkMode {

    // =====================================
    // FORMATO DE TABLA DE COMPARACIÓN
    // =====================================
    //
    // SonarQube recomienda evitar la
    // duplicación de literales.
    //
    // Este formato es reutilizado para
    // imprimir las filas de la tabla
    // comparativa del benchmark.
    //
    // =====================================

    private static final String FORMATO_TABLA =
            "%-30s %-20s%n";

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

        EvaluationResult resultado =
                obtenerEvaluacion(

                        respuesta,

                        segundaRespuesta,

                        latency
                );

        System.out.printf(

                """
                        
                        =====================================
                        
                        Resultados de la evaluación del modelo %s
                        =====================================
                        %n""", estrategia.getNombreVisual()

        );

        System.out.println();

        System.out.println(
                "Prompt strategy: "
                        + strategy.getClass().getSimpleName()
        );

        System.out.println(
                "Semantic Similarity: "
                        + resultado.semanticSimilarity()
                        + "%"
        );

        System.out.println(
                "Consistency Score: "
                        + resultado.consistencyScore()
                        + "%"
        );

        System.out.println(
                "Hallucination Risk: "
                        + resultado.hallucinationRisk()
                        + "%"
        );

        System.out.println(
                "Latency Score: "
                        + resultado.latencyScore()
                        + "%"
        );

        System.out.println(
                "Final Composite Score: "
                        + resultado.finalScore()
                        + "%"
        );

        System.out.println(
                "Quality Analysis: "
                        + resultado.quality()
        );

        System.out.printf(

                """
                        
                        =====================================
                        
                        Respuesta del modelo %s
                        =====================================
                        %n""", estrategia.getNombreVisual()

        );

        System.out.println();

        System.out.println(
                respuesta
        );

        return new ModelBenchmarkResult(

                estrategia.getNombreModelo(),

                resultado.semanticSimilarity(),

                resultado.consistencyScore(),

                resultado.hallucinationRisk(),

                resultado.latencyScore(),

                resultado.finalScore()
        );
    }

// EVALUACIÓN DEL MODELO
// =====================================
//
// Centraliza toda la lógica de
// benchmarking y evaluación.
//
// Facilita:
//
// - reutilización
// - mantenibilidad
// - pruebas unitarias
//
// =====================================

    private EvaluationResult obtenerEvaluacion(

            String respuesta,

            String segundaRespuesta,

            long latency
    ) {

        String referencia =
                """
                arquitectura modular orientada
                a objetos utilizando patrones
                de diseño y principios SOLID
                """;

        BenchmarkPipeline pipeline =
                new BenchmarkPipeline();

        return pipeline.ejecutarEvaluacion(

                respuesta,

                referencia,

                segundaRespuesta,

                latency
        );
    }
    private void mostrarComparacion(

            List<ModelBenchmarkResult> resultados
    ) {

        ModelBenchmarkResult mejorSemantic =
                resultados.stream()
                        .max(
                                Comparator.comparingDouble(
                                        ModelBenchmarkResult::semanticSimilarity
                                )
                        )
                        .orElse(null);

        ModelBenchmarkResult mejorConsistency =
                resultados.stream()
                        .max(
                                Comparator.comparingDouble(
                                        ModelBenchmarkResult::consistencyScore
                                )
                        )
                        .orElse(null);

        ModelBenchmarkResult mejorLatency =
                resultados.stream()
                        .max(
                                Comparator.comparingDouble(
                                        ModelBenchmarkResult::latencyScore
                                )
                        )
                        .orElse(null);

        ModelBenchmarkResult mejorHallucination =
                resultados.stream()
                        .min(
                                Comparator.comparingDouble(
                                        ModelBenchmarkResult::hallucinationRisk
                                )
                        )
                        .orElse(null);

        ModelBenchmarkResult ganador =
                resultados.stream()
                        .max(
                                Comparator.comparingDouble(
                                        ModelBenchmarkResult::finalScore
                                )
                        )
                        .orElse(null);

        System.out.println("""

            =====================================
            Comparación multimodelo
            =====================================
            """);

        System.out.printf(
                FORMATO_TABLA,
                "Evaluación",
                "Mejor Modelo"
        );

        System.out.println(
                "--------------------------------------------------------"
        );

        assert mejorSemantic != null;
        System.out.printf(
                FORMATO_TABLA,
                "Semantic Similarity",
                mejorSemantic.modelo()
        );

        System.out.printf(
                FORMATO_TABLA,
                "Consistency Score",
                mejorConsistency.modelo()
        );

        System.out.printf(
                FORMATO_TABLA,
                "Hallucination Risk",
                mejorHallucination.modelo()
        );

        System.out.printf(
                FORMATO_TABLA,
                "Latency Score",
                mejorLatency.modelo()
        );

        System.out.printf(
                FORMATO_TABLA,
                "Final Composite Score",
                ganador.modelo()
        );

        System.out.println("""

            =====================================
            Ganador general
            =====================================
            """);

        System.out.println(
                "Modelo ganador: "
                        + ganador.modelo()
        );

        System.out.println(
                "Final Composite Score: "
                        + ganador.finalScore()
        );
    }
}
