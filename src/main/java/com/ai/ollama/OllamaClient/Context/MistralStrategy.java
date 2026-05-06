package com.ai.ollama.OllamaClient.Context;

import com.ai.ollama.OllamaClient.OllamaClient;
import com.ai.ollama.OllamaClient.Strategy.IAStrategy;
import com.ai.ollama.OllamaClient.Template.PromptBuilder;
import com.ai.ollama.OllamaClient.Template.PromptConfig;

public class MistralStrategy implements IAStrategy {

    private final OllamaClient cliente =
            new OllamaClient();

    @Override
    public String generarRespuesta(PromptConfig config) {

        String promptFinal = new PromptBuilder()
                .conRol(config.getRol())
                .conInstrucciones(config.getInstrucciones())
                .conEntrada(config.getEntrada())
                .build();

        String json =
                cliente.enviarPeticion(
                        "mistral",
                        promptFinal
                );

        return extraerRespuesta(json);
    }

    @Override
    public String getNombreModelo() {
        return "Mistral";
    }

    private String extraerRespuesta(String json) {

        try {

            int inicio =
                    json.indexOf("\"response\":\"") + 12;

            int fin =
                    json.indexOf("\",", inicio);

            return json.substring(inicio, fin)
                    .replace("\\n", "\n")
                    .replace("\\\"", "\"");

        } catch (Exception e) {

            return json;
        }
    }
}