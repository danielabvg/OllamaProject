// =====================================
// ESTRATEGIA BASE REUTILIZABLE
// =====================================
//
// Esta clase abstracta contiene lógica
// compartida entre los modelos de IA.
//
// Su principal función es procesar
// y limpiar la respuesta JSON enviada
// por Ollama.
//
// Esto evita duplicar código en:
// - Llama3Strategy
// - MistralStrategy
// - Phi3Strategy
// =====================================

package com.ai.ollama.OllamaClient.Context;

public abstract class BaseStrategy {

    // =====================================
    // EXTRACCIÓN DE RESPUESTA
    // =====================================
    //
    // Obtiene únicamente el texto generado
    // por el modelo desde el JSON de Ollama.
    // =====================================

    protected String extraerRespuesta(String json) {

        try {

            int inicio =
                    json.indexOf("\"response\":\"") + 12;

            int fin =
                    json.indexOf("\",", inicio);

            return json.substring(inicio, fin)
                    .replace("\\n", "\n")
                    .replace("\\\"", "\"");

        } catch (Exception e) {

            // Si ocurre un error,
            // devolvemos el JSON completo.

            return json;
        }
    }
}
