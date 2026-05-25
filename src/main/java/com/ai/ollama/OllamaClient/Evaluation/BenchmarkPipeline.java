// =====================================
// PIPELINE CENTRAL DE BENCHMARKING
// =====================================
//
// Orquesta TODO el sistema:
//
// - semantic evaluation
// - consistency evaluation
// - hallucination estimation
// - latency analysis
// - weighted scoring
//
// Inspirado en frameworks modernos
// de evaluación de LLMs.
//
// =====================================

package com.ai.ollama.OllamaClient.Evaluation;

public class BenchmarkPipeline {

    public EvaluationResult ejecutarEvaluacion(

            String respuesta,

            String referencia,

            String segundaRespuesta,

            long latency
    ) {

        // =====================================
        // COMPONENTES
        // =====================================

        SemanticEvaluator semantic =
                new SemanticEvaluator();

        ConsistencyEvaluator consistency =
                new ConsistencyEvaluator();

        HeuristicHallucinationEstimator hallucination =
                new HeuristicHallucinationEstimator();

        LatencyEvaluator latencyEvaluator =
                new LatencyEvaluator();

        WeightedScoreCalculator calculator =
                new WeightedScoreCalculator();

        ResponseQualityAnalyzer analyzer =
                new ResponseQualityAnalyzer();

        // =====================================
        // MÉTRICAS
        // =====================================

        double semanticScore =
                semantic.calcularSimilitudSemantica(
                        respuesta,
                        referencia
                );

        double consistencyScore =
                consistency.calcularConsistencia(
                        respuesta,
                        segundaRespuesta
                );

        double hallucinationRisk =
                hallucination.estimarHallucinationRisk(
                        respuesta,
                        referencia
                );

        double latencyScore =
                latencyEvaluator.calcularLatencyScore(
                        latency
                );

        // =====================================
        // SCORE FINAL
        // =====================================

        double finalScore =
                calculator.calcularScoreFinal(

                        semanticScore,

                        consistencyScore,

                        hallucinationRisk,

                        latencyScore
                );

        // =====================================
        // QUALITY ANALYSIS
        // =====================================

        String quality =
                analyzer.analizarCalidad(
                        finalScore
                );

        // =====================================
        // RESULTADO FINAL
        // =====================================

        return new EvaluationResult(

                semanticScore,

                consistencyScore,

                hallucinationRisk,

                latencyScore,

                finalScore,

                quality
        );
    }
}
