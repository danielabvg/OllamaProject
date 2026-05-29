package com.ai.ollama.OllamaClient.services;

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
// la lógica HTTP del sistema.
//
// Responsabilidades:
//
// - construir requests
// - ejecutar llamadas HTTP
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
    //
    // Se crea una única instancia para
    // reutilizar conexiones HTTP y mejorar
    // eficiencia.
    //
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
    //
    // Envía requests HTTP POST utilizando
    // JSON como body principal.
    //
    // =====================================

    public String post(

            String url,

            String jsonBody
    ) {

        try {

            // =====================================
            // CONSTRUCCIÓN DEL REQUEST
            // =====================================

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(URI.create(url))
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
            // EJECUCIÓN HTTP
            // =====================================

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers
                                    .ofString()
                    );

            return response.body();

        } catch (Exception e) {

            // =====================================
            // MANEJO BÁSICO DE ERRORES
            // =====================================

            return "Error HTTP: "
                    + e.getMessage();
        }
    }
}
