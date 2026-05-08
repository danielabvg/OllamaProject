// Model used to encapsulate evaluation results
package com.ai.ollama.OllamaClient.Evaluation;

public class EvaluationResult {

    private double semanticPrecision;
    private long latency;
    private int responseLength;
    private double consistencyScore;
    private double hallucinationRate;

    public EvaluationResult(
            double semanticPrecision,
            long latency,
            int responseLength,
            double consistencyScore,
            double hallucinationRate
    ) {
        this.semanticPrecision = semanticPrecision;
        this.latency = latency;
        this.responseLength = responseLength;
        this.consistencyScore = consistencyScore;
        this.hallucinationRate = hallucinationRate;
    }

    public double getSemanticPrecision() {
        return semanticPrecision;
    }

    public long getLatency() {
        return latency;
    }

    public int getResponseLength() {
        return responseLength;
    }

    public double getConsistencyScore() {
        return consistencyScore;
    }

    public double getHallucinationRate() {
        return hallucinationRate;
    }
}
