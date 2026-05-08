// Core abstraction for all AI strategies
package com.ai.ollama.OllamaClient.Strategy;

import com.ai.ollama.OllamaClient.Template.PromptConfig;

public interface IAStrategy {

    String generarRespuesta(PromptConfig config);

    String getNombreModelo();
}
