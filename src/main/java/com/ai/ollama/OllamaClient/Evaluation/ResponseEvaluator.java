// =====================================
// EVALUADOR DE RESPUESTAS LLM
// =====================================
//
// Esta clase calcula métricas utilizadas
// para analizar rendimiento y calidad
// de modelos de lenguaje.
//
// Incluye:
// - Semantic Precision
// - Token Analysis
// - Consistency Score
// - Hallucination Rate
// =====================================

package com.ai.ollama.OllamaClient.Evaluation;

import java.util.HashSet;
import java.util.Set;

public class ResponseEvaluator {

    // =====================================
    // SEMANTIC PRECISION
    // =====================================
    //
    // Evalúa qué tan alineada está
    // la respuesta con conceptos esperados.
    // =====================================

    public double calcularSemanticPrecision(
            String respuesta,
            String[] keywords
    ) {

        int coincidencias = 0;

        for (String keyword : keywords) {

            if (respuesta.toLowerCase()
                    .contains(keyword.toLowerCase())) {

                coincidencias++;
            }
        }

        return (double) coincidencias
                / keywords.length * 100;
    }

    // =====================================
    // TOKEN / LENGTH ANALYSIS
    // =====================================
    //
    // Analiza tamaño y cantidad aproximada
    // de tokens generados.
    // =====================================

    public int calcularLongitud(
            String respuesta
    ) {
        return respuesta.length();
    }

    public int calcularTokens(
            String respuesta
    ) {

        return respuesta.split("\\s+").length;
    }

    // =====================================
    // CONSISTENCY SCORE
    // =====================================
    //
    // Compara dos respuestas para medir
    // estabilidad entre ejecuciones.
    // =====================================

    public double calcularConsistencyScore(
            String respuesta1,
            String respuesta2
    ) {

        Set<String> palabras1 =
                convertirASet(respuesta1);

        Set<String> palabras2 =
                convertirASet(respuesta2);

        int comunes = 0;

        for (String palabra : palabras1) {

            if (palabras2.contains(palabra)) {
                comunes++;
            }
        }

        int total =
                Math.max(
                        palabras1.size(),
                        palabras2.size()
                );

        return (double) comunes / total * 100;
    }

    // Convierte texto a Set para evitar
    // palabras repetidas durante comparación.

    private Set<String> convertirASet(
            String texto
    ) {

        String[] palabras =
                texto.toLowerCase().split("\\s+");

        return new HashSet<>(
                java.util.Arrays.asList(palabras)
        );
    }

    // =====================================
    // HALLUCINATION RATE
    // =====================================
    //
    // Estima posibles inconsistencias
    // comparando conceptos esperados.
    // =====================================

    public double calcularHallucinationRate(
            String respuesta,
            String[] conceptosEsperados
    ) {

        int conceptosCorrectos = 0;

        for (String concepto : conceptosEsperados) {

            if (respuesta.toLowerCase()
                    .contains(concepto.toLowerCase())) {

                conceptosCorrectos++;
            }
        }

        double precision =
                (double) conceptosCorrectos
                        / conceptosEsperados.length;

        return (1 - precision) * 100;
    }
}
