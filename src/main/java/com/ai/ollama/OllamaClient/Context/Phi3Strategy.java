// =====================================
// ESTRATEGIA CONCRETA PHI3 MINI
// =====================================
//
// Esta clase implementa la conexión
// con el modelo ligero Phi3 Mini
// ejecutado localmente mediante Ollama.
//
// Gracias al patrón Strategy,
// el modelo puede intercambiarse
// dinámicamente con otros LLMs.
// =====================================

package com.ai.ollama.OllamaClient.Context;

import com.ai.ollama.OllamaClient.OllamaClient;
import com.ai.ollama.OllamaClient.Strategy.IAStrategy;
import com.ai.ollama.OllamaClient.Template.PromptBuilder;
import com.ai.ollama.OllamaClient.Template.PromptConfig;

public class Phi3Strategy
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
        // PETICIÓN AL MODELO PHI3 MINI
        // =====================================

        String json =
                cliente.enviarPeticion(
                        "phi3:mini",
                        prompt
                );

        // Extraemos únicamente
        // la respuesta útil del JSON.

        return extraerRespuesta(json);
    }

    @Override
    public String getNombreModelo() {

        // Nombre mostrado en benchmarking.

        return "Phi3 Mini";
    }
}
