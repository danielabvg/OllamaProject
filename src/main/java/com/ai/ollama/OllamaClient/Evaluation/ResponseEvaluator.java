package com.ai.ollama.OllamaClient.Evaluation;

import java.util.HashSet;
import java.util.Set;

public class ResponseEvaluator {

    // =====================================
    // SEMANTIC PRECISION
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
