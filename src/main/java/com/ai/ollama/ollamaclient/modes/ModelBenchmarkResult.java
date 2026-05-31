package com.ai.ollama.ollamaclient.modes;

public record ModelBenchmarkResult(String modelo, double semanticSimilarity, double consistencyScore,
                                   double hallucinationRisk, double latencyScore, double finalScore) {

}
