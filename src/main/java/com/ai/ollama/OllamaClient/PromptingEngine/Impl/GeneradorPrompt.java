package com.ai.ollama.OllamaClient.PromptingEngine.Impl;

import com.ai.ollama.OllamaClient.IntentRouting.IntentRouter;
import com.ai.ollama.OllamaClient.Template.PromptConfig;

public class GeneradorPrompt {

    private final IntentRouter router;

    public GeneradorPrompt() {
        this.router = new IntentRouter();
    }

    public PromptConfig generar(String entradaUsuario) {

        String rol =
                router.determinarRol(entradaUsuario);

        String instrucciones =
                router.optimizarInstrucciones(
                        entradaUsuario
                );

        return new PromptConfig(
                rol,
                instrucciones,
                entradaUsuario
        );
    }
}
