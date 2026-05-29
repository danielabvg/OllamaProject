package com.ai.ollama.OllamaClient.Modes;

public class ModelBenchmarkResult {

    private final String modelo;

    private final double semanticSimilarity;

    private final double consistencyScore;

    private final double hallucinationRisk;

    private final double latencyScore;

    private final double finalScore;

    public ModelBenchmarkResult(

            String modelo,

            double semanticSimilarity,

            double consistencyScore,

            double hallucinationRisk,

            double latencyScore,

            double finalScore
    ) {

        this.modelo = modelo;
        this.semanticSimilarity = semanticSimilarity;
        this.consistencyScore = consistencyScore;
        this.hallucinationRisk = hallucinationRisk;
        this.latencyScore = latencyScore;
        this.finalScore = finalScore;
    }

    public String getModelo() {
        return modelo;
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
}
