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
import java.util.function.ToDoubleFunction;

@SuppressWarnings("java:S106")
public class BenchmarkMode {

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

                        config.getPromptFinal(),
                        respuesta,
                        segundaRespuesta,
                        latency
                );

        System.out.printf(

                """

                =====================================

                Resultados de la evaluación del modelo %s
                =====================================
                %n""",

                estrategia.getNombreVisual()
        );

        System.out.println();

        System.out.println(
                "Prompt strategy: "
                        + strategy.getClass().getSimpleName()
        );

        System.out.printf(
                "Semantic Similarity: %.4f%%%n",
                resultado.semanticSimilarity()
        );

        System.out.printf(
                "Consistency Score: %.4f%%%n",
                resultado.consistencyScore()
        );

        System.out.printf(
                "Hallucination Risk: %.4f%%%n",
                resultado.hallucinationRisk()
        );

        System.out.printf(
                "Latency Score: %.4f%%%n",
                resultado.latencyScore()
        );

        System.out.printf(
                "Final Composite Score: %.4f%%%n",
                resultado.finalScore()
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
                %n""",

                estrategia.getNombreVisual()
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

    private EvaluationResult obtenerEvaluacion(

            String prompt,

            String respuesta,

            String segundaRespuesta,

            long latency
    ) {

        BenchmarkPipeline pipeline =
                new BenchmarkPipeline();

        return pipeline.ejecutarEvaluacion(

                prompt,

                respuesta,

                segundaRespuesta,

                latency
        );
    }

    private ModelBenchmarkResult obtenerMaximo(

            List<ModelBenchmarkResult> resultados,

            ToDoubleFunction<ModelBenchmarkResult> criterio
    ) {

        return resultados.stream()
                .max(
                        Comparator.comparingDouble(
                                criterio
                        )
                )
                .orElseThrow(
                        () -> new IllegalStateException(
                                "No existen resultados para comparar."
                        )
                );
    }

    private ModelBenchmarkResult obtenerMinimo(

            List<ModelBenchmarkResult> resultados,

            ToDoubleFunction<ModelBenchmarkResult> criterio
    ) {

        return resultados.stream()
                .min(
                        Comparator.comparingDouble(
                                criterio
                        )
                )
                .orElseThrow(
                        () -> new IllegalStateException(
                                "No existen resultados para comparar."
                        )
                );
    }

    private void mostrarComparacion(

            List<ModelBenchmarkResult> resultados
    ) {

        if (resultados.isEmpty()) {

            System.out.println(
                    "No hay resultados para comparar."
            );

            return;
        }

        ModelBenchmarkResult mejorSemantic =
                obtenerMaximo(
                        resultados,
                        ModelBenchmarkResult::semanticSimilarity
                );

        ModelBenchmarkResult mejorConsistency =
                obtenerMaximo(
                        resultados,
                        ModelBenchmarkResult::consistencyScore
                );

        ModelBenchmarkResult mejorLatency =
                obtenerMaximo(
                        resultados,
                        ModelBenchmarkResult::latencyScore
                );

        ModelBenchmarkResult mejorHallucination =
                obtenerMinimo(
                        resultados,
                        ModelBenchmarkResult::hallucinationRisk
                );

        ModelBenchmarkResult ganador =
                obtenerMaximo(
                        resultados,
                        ModelBenchmarkResult::finalScore
                );

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

        System.out.printf(
                "Final Composite Score: %.4f%n",
                ganador.finalScore()
        );
    }
}