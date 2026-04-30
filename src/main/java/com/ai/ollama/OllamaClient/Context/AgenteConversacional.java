package com.ai.ollama.OllamaClient.Context;

import com.ai.ollama.OllamaClient.Strategy.IAStrategy;

public class AgenteConversacional {

    private final IAStrategy estrategia;

    public AgenteConversacional(IAStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public String generarPrompt(String rol, String instrucciones) {
        return estrategia.construirPrompt(rol, instrucciones);
    }
}