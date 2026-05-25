// =====================================
// ESTIMADOR HEURÍSTICO DE HALLUCINATIONS
// =====================================
//
// Esta clase NO detecta factualidad real.
//
// En su lugar:
//
// estima inconsistencias utilizando:
//
// - overlap semántico
// - conceptos esperados
// - cobertura contextual
//
// Inspirado en evaluación heurística.
//
// =====================================

package com.ai.ollama.OllamaClient.Evaluation;

public class HeuristicHallucinationEstimator {

    // =====================================
    // ESTIMACIÓN DE RIESGO
    // =====================================

    public double estimarHallucinationRisk(

            String respuesta,

            String referenciaEsperada
    ) {

        SemanticEvaluator evaluator =
                new SemanticEvaluator();

        double similitud =
                evaluator
                        .calcularSimilitudSemantica(
                                respuesta,
                                referenciaEsperada
                        );

        // =====================================
        // MENOR SIMILITUD =
        // MAYOR RIESGO
        // =====================================

        return 100 - similitud;
    }

    // =====================================
    // INTERPRETACIÓN
    // =====================================

    public String interpretarRiesgo(
            double riesgo
    ) {

        if (riesgo < 20) {

            return "LOW";
        }

        if (riesgo < 50) {

            return "MODERATE";
        }

        return "HIGH";
    }
}