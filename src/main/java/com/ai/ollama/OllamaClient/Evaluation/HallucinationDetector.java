// =====================================
// DETECTOR DE HALLUCINATIONS
// =====================================
//
// Esta clase analiza qué tan confiable
// es una respuesta generada por un LLM.
//
// Compara la respuesta con conceptos
// esperados para estimar posibles
// inconsistencias o información inventada.
// =====================================

package com.ai.ollama.OllamaClient.Evaluation;

public class HallucinationDetector {

    // =====================================
    // DETECCIÓN DE HALLUCINATIONS
    // =====================================
    //
    // Calcula un porcentaje estimado de
    // información incorrecta o inconsistente.
    // =====================================

    public double detectarHallucinationRate(
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

        // Menor precisión =
        // mayor hallucination rate.

        return (1 - precision) * 100;
    }

    // =====================================
    // ANÁLISIS DEL RESULTADO
    // =====================================
    //
    // Convierte el porcentaje calculado
    // en una interpretación más entendible.
    // =====================================

    public String analizarResultado(
            double hallucinationRate
    ) {

        if (hallucinationRate <= 10) {

            return "Muy confiable";
        }

        if (hallucinationRate <= 30) {

            return "Moderadamente confiable";
        }

        if (hallucinationRate <= 50) {

            return "Posibles inconsistencias";
        }

        return "Alta probabilidad de hallucinations";
    }
}