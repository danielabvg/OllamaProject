// =====================================
// CLIENTE HTTP PARA OLLAMA
// =====================================
//
// Esta clase administra la conexión
// entre Java y la API local de Ollama.
//
// Su responsabilidad principal es:
// - construir peticiones HTTP
// - enviar prompts al modelo
// - recibir respuestas JSON
//
// Funciona como la capa de integración
// entre la aplicación y los LLMs locales.
// =====================================

package com.ai.ollama.OllamaClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class OllamaClient {

    // Endpoint local de Ollama.

    private static final String URL_API =
            "http://localhost:11434/api/generate";

    // =====================================
    // ENVÍO DE PETICIONES
    // =====================================
    //
    // Envía un prompt al modelo indicado
    // y retorna la respuesta generada.
    // =====================================

    public String enviarPeticion(
            String modelo,
            String prompt
    ) {

        // =====================================
        // CONSTRUCCIÓN DEL JSON
        // =====================================
        //
        // Se genera manualmente el body
        // para enviarlo a la API de Ollama.
        // =====================================

        String jsonBody = String.format(
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

        try {

            // =====================================
            // CLIENTE HTTP
            // =====================================

            HttpClient client =
                    HttpClient.newBuilder()
                            .connectTimeout(
                                    Duration.ofSeconds(20)
                            )
                            .build();

            // =====================================
            // PETICIÓN HTTP POST
            // =====================================

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(URI.create(URL_API))
                            .header(
                                    "Content-Type",
                                    "application/json"
                            )
                            .POST(
                                    HttpRequest.BodyPublishers
                                            .ofString(jsonBody)
                            )
                            .build();

            // =====================================
            // RESPUESTA DEL SERVIDOR
            // =====================================

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers
                                    .ofString()
                    );

            return response.body();

        } catch (Exception e) {

            // Manejo básico de errores.

            return "Error de conexión: "
                    + e.getMessage();
        }
    }
}
