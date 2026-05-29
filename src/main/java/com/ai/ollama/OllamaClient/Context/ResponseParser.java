package com.ai.ollama.OllamaClient.Context;

// =====================================
// PARSER DE RESPUESTAS OLLAMA
// =====================================
//
// Esta clase tiene la responsabilidad
// exclusiva de procesar y limpiar
// las respuestas JSON generadas por
// Ollama.
//
// =====================================

public class ResponseParser {

    public String extraerRespuesta(
            String json
    ) {

        try {

            int inicio =
                    json.indexOf(
                            "\"response\":\""
                    ) + 12;

            int fin =
                    json.indexOf(
                            "\",",
                            inicio
                    );

            return json.substring(
                            inicio,
                            fin
                    )
                    .replace("\\n", "\n")
                    .replace("\\\"", "\"");

        } catch (Exception e) {

            // =====================================
            // FALLBACK DE SEGURIDAD
            // =====================================
            //
            // Si ocurre un error durante
            // el parsing, se retorna el JSON
            // completo para debugging.
            //
            // =====================================

            return json;
        }
    }
}
