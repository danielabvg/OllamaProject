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

import com.ai.ollama.OllamaClient.services.HttpService;
import com.ai.ollama.OllamaClient.services.RequestBodyBuilder;

public class OllamaClient {

    // Endpoint local de Ollama.

    private static final String URL_API =
            "http://localhost:11434/api/generate";

    // =====================================
    // COMPONENTES DESACOPLADOS
    // =====================================
    //
    // OllamaClient ahora funciona como
    // fachada/orquestador.
    //
    // Delegando:
    //
    // - construcción JSON
    // - ejecución HTTP
    //
    // en componentes especializados.
    //
    // =====================================

    private final RequestBodyBuilder bodyBuilder =
            new RequestBodyBuilder();

    private final HttpService httpService =
            new HttpService();

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
        // CONSTRUCCIÓN DESACOPLADA
        // =====================================
        //
        // La generación del JSON ahora está
        // separada de la lógica HTTP.
        //
        // =====================================

        String jsonBody =
                bodyBuilder.construirBody(
                        modelo,
                        prompt
                );

        // =====================================
        // EJECUCIÓN HTTP DESACOPLADA
        // =====================================
        //
        // Toda la comunicación HTTP ahora
        // está encapsulada en HttpService.
        //
        // OllamaClient únicamente orquesta
        // el flujo de inferencia.
        //
        // =====================================

        return httpService.post(
                URL_API,
                jsonBody
        );
    }
}
