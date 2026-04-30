package com.ai.ollama.OllamaClient.Strategy;

import com.ai.ollama.OllamaClient.Template.PromptConfig ;

public interface IAStrategy {

    String generarRespuesta(PromptConfig config);

    String getNombreModelo();

    String construirPrompt(String rol, String instrucciones);
}
