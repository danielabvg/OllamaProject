package com.ai.ollama.OllamaClient.Context;

import com.ai.ollama.OllamaClient.Strategy.IAStrategy;
import com.ai.ollama.OllamaClient.Template.PromptConfig;

public class AgenteConversacional {

    private IAStrategy estrategia;

    public AgenteConversacional(
            IAStrategy estrategia
    ) {

        this.estrategia = estrategia;
    }

    public String preguntar(
            PromptConfig config
    ) {

        return estrategia.generarRespuesta(config);
    }
}