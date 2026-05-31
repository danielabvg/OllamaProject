// =====================================
// EVALUADOR DE LATENCIA
// =====================================
//
// Convierte tiempo de respuesta
// en score interpretable.
//
// =====================================

package com.ai.ollama.ollamaclient.evaluation;

public class LatencyEvaluator {

    public double calcularLatencyScore(
            long latency
    ) {

        if (latency <= 1000) {

            return 100;
        }

        if (latency <= 3000) {

            return 80;
        }

        if (latency <= 5000) {

            return 60;
        }

        return 40;
    }
}
