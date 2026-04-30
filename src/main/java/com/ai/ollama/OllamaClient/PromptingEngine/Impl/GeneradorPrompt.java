package com.ai.ollama.OllamaClient.PromptingEngine.Impl;

import com.ai.ollama.OllamaClient.Context.Llama3Strategy;
import com.ai.ollama.OllamaClient.IntentRouting.IntentRouter;
import com.ai.ollama.OllamaClient.Strategy.IAStrategy;
import com.ai.ollama.OllamaClient.Template.PromptConfig;

public class GeneradorPrompt {

    private final IntentRouter router;
    private final IAStrategy estrategia;

    public GeneradorPrompt() {
        this.router = new IntentRouter();
        this.estrategia = new Llama3Strategy();
    }

    public String generarRespuesta(String entradaUsuario) {

        // 1. Detectar rol
        String rol = router.determinarRol(entradaUsuario);

        // 2. Optimizar instrucciones
        String instrucciones = router.optimizarInstrucciones(entradaUsuario);

        // 3. Crear config del prompt
        PromptConfig config = new PromptConfig(
                rol,
                instrucciones,
                entradaUsuario
        );

        // 4. Ejecutar estrategia (llama a Ollama)
        return estrategia.generarRespuesta(config);
    }
}
