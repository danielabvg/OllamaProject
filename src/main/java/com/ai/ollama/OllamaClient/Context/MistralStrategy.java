// =====================================
// ESTRATEGIA CONCRETA MISTRAL
// =====================================
//
// Esta clase implementa la lógica
// necesaria para utilizar el modelo
// local Mistral mediante Ollama.
//
// Gracias a IAStrategy, el modelo puede
// intercambiarse dinámicamente.
// =====================================

package com.ai.ollama.OllamaClient.Context;

import com.ai.ollama.OllamaClient.OllamaClient;
import com.ai.ollama.OllamaClient.Strategy.IAStrategy;
import com.ai.ollama.OllamaClient.Template.PromptBuilder;
import com.ai.ollama.OllamaClient.Template.PromptConfig;

public class MistralStrategy
        extends BaseStrategy
        implements IAStrategy {

    // Cliente de conexión con Ollama.

    private final OllamaClient cliente =
            new OllamaClient();

    @Override
    public String generarRespuesta(
            PromptConfig config
    ) {

        // =====================================
        // CONSTRUCCIÓN DEL PROMPT
        // =====================================

        String prompt =
                new PromptBuilder()
                        .conRol(config.getRol())
                        .conInstrucciones(
                                config.getInstrucciones()
                        )
                        .conEntrada(config.getEntrada())
                        .build();

        // =====================================
        // PETICIÓN AL MODELO MISTRAL
        // =====================================

        String json =
                cliente.enviarPeticion(
                        "mistral",
                        prompt
                );

        // Extraemos únicamente
        // la respuesta útil del JSON.

        return extraerRespuesta(json);
    }

    @Override
    public String getNombreModelo() {

        // Nombre mostrado en el sistema.

        return "Mistral";
    }
}