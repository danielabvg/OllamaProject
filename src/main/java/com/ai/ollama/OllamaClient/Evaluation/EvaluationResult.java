// =====================================
// MODELO DE RESULTADOS DE EVALUACIÓN
// =====================================
//
// Esta clase encapsula las métricas
// generadas durante el benchmarking
// de modelos de IA.
//
// Permite almacenar:
// - precisión
// - latencia
// - longitud de respuesta
// - consistencia
// - hallucination rate
// =====================================

package com.ai.ollama.OllamaClient.Evaluation;

public class EvaluationResult {

    // =====================================
    // MÉTRICAS PRINCIPALES
    // =====================================

    private double semanticPrecision;
    private long latency;
    private int responseLength;
    private double consistencyScore;
    private double hallucinationRate;

    // =====================================
    // CONSTRUCTOR
    // =====================================
    //
    // Inicializa todas las métricas
    // calculadas para un modelo.
    // =====================================

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

    // =====================================
    // GETTERS
    // =====================================
    //
    // Permiten acceder a las métricas
    // de forma controlada.
    // =====================================

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
