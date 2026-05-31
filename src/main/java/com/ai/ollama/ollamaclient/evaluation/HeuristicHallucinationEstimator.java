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

package com.ai.ollama.ollamaclient.evaluation;

public class HeuristicHallucinationEstimator {

    // =====================================
    // ESTIMACIÓN DE RIESGO DE HALLUCINATION
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
}