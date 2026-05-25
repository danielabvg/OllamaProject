// =====================================
// RESULTADO GLOBAL DE EVALUACIÓN
// =====================================
//
// Encapsula:
//
// - semantic similarity
// - consistency
// - hallucination risk
// - latency
// - score final
//
// =====================================

package com.ai.ollama.OllamaClient.Evaluation;

public class EvaluationResult {

    private double semanticSimilarity;

    private double consistencyScore;

    private double hallucinationRisk;

    private double latencyScore;

    private double finalScore;

    private String quality;

    public EvaluationResult(

            double semanticSimilarity,

            double consistencyScore,

            double hallucinationRisk,

            double latencyScore,

            double finalScore,

            String quality
    ) {

        this.semanticSimilarity =
                semanticSimilarity;

        this.consistencyScore =
                consistencyScore;

        this.hallucinationRisk =
                hallucinationRisk;

        this.latencyScore =
                latencyScore;

        this.finalScore =
                finalScore;

        this.quality =
                quality;
    }

    public double getSemanticSimilarity() {
        return semanticSimilarity;
    }

    public double getConsistencyScore() {
        return consistencyScore;
    }

    public double getHallucinationRisk() {
        return hallucinationRisk;
    }

    public double getLatencyScore() {
        return latencyScore;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public String getQuality() {
        return quality;
    }
}