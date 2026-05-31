// =====================================
// ANALIZADOR DE CALIDAD
// =====================================
//
// Interpreta métricas para generar
// una evaluación textual.
//
// =====================================

package com.ai.ollama.ollamaclient.evaluation;

public class ResponseQualityAnalyzer {

    public String analizarCalidad(
            double score
    ) {

        if (score >= 85) {

            return "HIGH QUALITY RESPONSE";
        }

        if (score >= 65) {

            return "MODERATE QUALITY RESPONSE";
        }

        return "LOW QUALITY RESPONSE";
    }
}
