// Concrete strategy implementation for Mistral
package com.ai.ollama.OllamaClient.Context;

import com.ai.ollama.OllamaClient.OllamaClient;
import com.ai.ollama.OllamaClient.Strategy.IAStrategy;
import com.ai.ollama.OllamaClient.Template.PromptBuilder;
import com.ai.ollama.OllamaClient.Template.PromptConfig;

public class MistralStrategy
        extends BaseStrategy
        implements IAStrategy {

    private final OllamaClient cliente =
            new OllamaClient();

    @Override
    public String generarRespuesta(
            PromptConfig config
    ) {

        String prompt =
                new PromptBuilder()
                        .conRol(config.getRol())
                        .conInstrucciones(
                                config.getInstrucciones()
                        )
                        .conEntrada(config.getEntrada())
                        .build();

        String json =
                cliente.enviarPeticion(
                        "mistral",
                        prompt
                );

        return extraerRespuesta(json);
    }

    @Override
    public String getNombreModelo() {
        return "Mistral";
    }
}