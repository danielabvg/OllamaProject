// =====================================
// ESTRATEGIA CONCRETA LLAMA3
// =====================================
//
// Esta clase implementa la lógica
// necesaria para comunicarse con
// el modelo local Llama3 mediante Ollama.
//
// Implementa IAStrategy para permitir
// intercambio dinámico de modelos.
// =====================================

package com.ai.ollama.OllamaClient.Context;

import com.ai.ollama.OllamaClient.OllamaClient;
import com.ai.ollama.OllamaClient.Strategy.IAStrategy;
import com.ai.ollama.OllamaClient.Template.PromptBuilder;
import com.ai.ollama.OllamaClient.Template.PromptConfig;

public class Llama3Strategy
        extends BaseStrategy
        implements IAStrategy {

    // Cliente encargado de conectarse
    // con la API local de Ollama.

    private final OllamaClient cliente =
            new OllamaClient();

    @Override
    public String generarRespuesta(
            PromptConfig config
    ) {

        // =====================================
        // CONSTRUCCIÓN DEL PROMPT
        // =====================================
        //
        // Se genera un prompt estructurado
        // utilizando el patrón Builder.
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
        // PETICIÓN A OLLAMA
        // =====================================

        String json =
                cliente.enviarPeticion(
                        "llama3",
                        prompt
                );

        // =====================================
        // LIMPIEZA DE RESPUESTA
        // =====================================
        //
        // Se extrae únicamente el texto útil
        // del JSON recibido.
        // =====================================

        return extraerRespuesta(json);
    }

    @Override
    public String getNombreModelo() {

        // Nombre mostrado en benchmarking.

        return "Llama3";
    }
}