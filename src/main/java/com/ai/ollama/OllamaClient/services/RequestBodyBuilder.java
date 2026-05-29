
package com.ai.ollama.OllamaClient.services;

// =====================================
// CONSTRUCTOR DE REQUEST BODIES
// =====================================
//
// Esta clase tiene la responsabilidad
// exclusiva de construir el JSON que será
// enviado a la API de Ollama.
//
// Beneficios arquitectónicos:
//
// - SRP (Single Responsibility Principle)
// - reutilización
// - desacoplamiento
// - mantenibilidad
// - extensibilidad
//
// Esto permite soportar fácilmente:
//
// - temperatura
// - top_p
// - streaming
// - múltiples modelos
// - múltiples providers
//
// sin modificar OllamaClient.
//
// =====================================

public class RequestBodyBuilder {

    // =====================================
    // CONSTRUCCIÓN DEL JSON
    // =====================================
    //
    // Genera dinámicamente el body JSON
    // necesario para enviar prompts
    // al servidor Ollama.
    //
    // =====================================

    public String construirBody(

            String modelo,

            String prompt
    ) {

        return String.format(
                """
                {
                  "model": "%s",
                  "prompt": "%s",
                  "stream": false
                }
                """,
                modelo,
                prompt
                        .replace("\"", "\\\"")
                        .replace("\n", "\\n")
        );
    }
}
