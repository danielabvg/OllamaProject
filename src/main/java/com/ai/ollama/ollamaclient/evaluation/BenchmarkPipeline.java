package com.ai.ollama.ollamaclient.evaluation;

// =====================================
// PIPELINE CENTRAL DE BENCHMARKING
// =====================================
//
// Orquesta el sistema completo:
//
// - semantic evaluation
// - consistency evaluation
// - heuristic hallucination estimation
// - latency analysis
// - weighted scoring
//
// Inspirado en frameworks modernos
// de evaluación de LLMs.
//
// =====================================

public class BenchmarkPipeline {

    public EvaluationResult ejecutarEvaluacion(

            String prompt,

            String respuesta,

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

        PromptDeviationRiskEstimator hallucination =
                new PromptDeviationRiskEstimator();

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
                        prompt,
                        respuesta
                );

        double consistencyScore =
                consistency.calcularConsistencia(
                        respuesta,
                        segundaRespuesta
                );

        double hallucinationRisk =
                hallucination.estimarHallucinationRisk(
                        prompt,
                        respuesta
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