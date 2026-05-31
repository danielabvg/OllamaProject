package com.ai.ollama.ollamaclient.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

// =====================================
// SERVICIO HTTP DESACOPLADO
// =====================================
//
// Esta clase encapsula completamente
// la comunicación HTTP del sistema.
//
// Responsabilidades:
//
// - construir requests
// - ejecutar peticiones HTTP
// - manejar comunicación externa
//
// Beneficios:
//
// - reutilización
// - bajo acoplamiento
// - mantenibilidad
// - testing más sencillo
// - arquitectura limpia
//
// =====================================

public class HttpService {

    // =====================================
    // CLIENTE HTTP REUTILIZABLE
    // =====================================

    private final HttpClient client =
            HttpClient.newBuilder()
                    .connectTimeout(
                            Duration.ofSeconds(20)
                    )
                    .build();

    // =====================================
    // EJECUCIÓN DE PETICIONES POST
    // =====================================

    public String post(

            String url,

            String jsonBody
    ) {

        try {

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(
                                    URI.create(url)
                            )
                            .header(
                                    "Content-Type",
                                    "application/json"
                            )
                            .POST(
                                    HttpRequest.BodyPublishers
                                            .ofString(jsonBody)
                            )
                            .build();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers
                                    .ofString()
                    );

            return response.body();

        } catch (InterruptedException e) {

            // =====================================
            // RESTAURAR INTERRUPCIÓN DEL HILO
            // =====================================
            //
            // SonarQube recomienda volver a
            // marcar el hilo como interrumpido.
            //
            // =====================================

            Thread.currentThread()
                    .interrupt();

            return "Error HTTP: "
                    + e.getMessage();

        } catch (IOException e) {

            return "Error HTTP: "
                    + e.getMessage();
        }
    }
}
