package com.ai.ollama.ollamaclient.evaluation;

// =====================================
// ESTIMADOR HEURÍSTICO DE HALLUCINATIONS
// =====================================
//
// Esta clase NO detecta factualidad real.
//
// En su lugar:
//
// estima riesgo heurístico de
// desviación respecto a los
// conceptos presentes en el prompt.
//
// Inspirado en evaluación heurística.
//
// =====================================

public class PromptDeviationRiskEstimator {

    // =====================================
    // ESTIMACIÓN DE RIESGO
    // =====================================

    public double estimarHallucinationRisk(

            String prompt,

            String respuesta
    ) {

        SemanticEvaluator evaluator =
                new SemanticEvaluator();

        double similitud =
                evaluator.calcularSimilitudSemantica(

                        prompt,

                        respuesta
                );

        // =====================================
        // MENOR SIMILITUD =
        // MAYOR RIESGO
        // =====================================

        return 100 - similitud;
    }
}