package com.ai.ollama.OllamaClient.Context;

import com.ai.ollama.OllamaClient.Strategy.IAStrategy;
import com.ai.ollama.OllamaClient.OllamaClient;
import com.ai.ollama.OllamaClient.Template.PromptBuilder ;
import com.ai.ollama.OllamaClient.Template.PromptConfig;

public class Llama3Strategy implements IAStrategy {

    private final OllamaClient cliente = new OllamaClient();

    @Override
    public String generarRespuesta(PromptConfig config) {

        String promptFinal = new PromptBuilder()
                .conRol(config.getRol())
                .conInstrucciones(config.getInstrucciones())
                .conEntrada(config.getEntrada())
                .build();

        String jsonRespuesta = cliente.enviarPeticion("llama3", promptFinal);

        return extraerRespuesta(jsonRespuesta);
    }

    @Override
    public String getNombreModelo() {
        return "Llama3-Local";
    }

    @Override
    public String construirPrompt(String rol, String instrucciones) {
        return "";
    }

    private String extraerRespuesta(String json) {
        try {
            int inicio = json.indexOf("\"response\":\"") + 12;
            int fin = json.indexOf("\",", inicio);
            return json.substring(inicio, fin)
                    .replace("\\n", "\n")
                    .replace("\\\"", "\"");
        } catch (Exception e) {
            return json;
        }
    }
}
