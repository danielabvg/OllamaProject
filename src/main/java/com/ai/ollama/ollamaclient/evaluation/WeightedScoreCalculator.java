// =====================================
// CALCULADORA DE SCORE COMPUESTO
// =====================================
//
// Combina múltiples métricas:
//
// - semantic similarity
// - consistency
// - hallucination risk
// - latency
//
// para producir un score global.
//
// =====================================

package com.ai.ollama.ollamaclient.evaluation;

public class WeightedScoreCalculator {

    public double calcularScoreFinal(

            double semantic,

            double consistency,

            double hallucination,

            double latencyScore
    ) {

        return (

                semantic * 0.4 +

                        consistency * 0.3 +

                        latencyScore * 0.2 +

                        (100 - hallucination) * 0.1
        );
    }
}