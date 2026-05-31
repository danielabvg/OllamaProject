// =====================================
// EVALUADOR DE CONSISTENCIA
// =====================================
//
// Esta clase analiza estabilidad
// entre múltiples respuestas del modelo.
//
// Evalúa:
//
// - repetibilidad
// - coherencia
// - estabilidad semántica
//
// =====================================

package com.ai.ollama.ollamaclient.evaluation;

public class ConsistencyEvaluator {

    // =====================================
    // EVALUACIÓN DE CONSISTENCIA
    // =====================================

    public double calcularConsistencia(

            String respuesta1,

            String respuesta2
    ) {

        SemanticEvaluator evaluator =
                new SemanticEvaluator();

        return evaluator
                .calcularSimilitudSemantica(
                        respuesta1,
                        respuesta2
                );
    }
}
