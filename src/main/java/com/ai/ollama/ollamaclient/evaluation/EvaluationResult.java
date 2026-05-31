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

package com.ai.ollama.ollamaclient.evaluation;

public record EvaluationResult(double semanticSimilarity, double consistencyScore, double hallucinationRisk,
                               double latencyScore, double finalScore, String quality) {

}