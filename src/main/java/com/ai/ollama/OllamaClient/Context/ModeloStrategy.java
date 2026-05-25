package com.ai.ollama.OllamaClient.Context;

import com.ai.ollama.OllamaClient.OllamaClient;
import com.ai.ollama.OllamaClient.Strategy.IAStrategy;
import com.ai.ollama.OllamaClient.Template.PromptConfig;

// ==========================================
// STRATEGY DINÁMICA DE MODELOS IA
// ==========================================
//
// Esta clase permite reutilizar
// la misma lógica para cualquier
// modelo compatible con Ollama.
//
// Ejemplos:
//
// - llama3
// - mistral
// - phi3
// - gemma
// - codellama
//
// Esto elimina duplicación
// y mejora escalabilidad.
//
// ==========================================

public class ModeloStrategy
        extends BaseStrategy
        implements IAStrategy {

    // ==========================================
    // CLIENTE HTTP
    // ==========================================

    private final OllamaClient cliente;

    // ==========================================
    // NOMBRE INTERNO DEL MODELO
    // ==========================================

    private final String nombreModelo;

    // ==========================================
    // NOMBRE VISUAL
    // ==========================================

    private final String nombreVisual;

    // ==========================================
    // CONSTRUCTOR
    // ==========================================

    public ModeloStrategy(

            String nombreModelo,

            String nombreVisual,

            OllamaClient cliente
    ) {

        this.nombreModelo = nombreModelo;

        this.nombreVisual = nombreVisual;

        this.cliente = cliente;
    }

    // ==========================================
    // GENERACIÓN DE RESPUESTA
    // ==========================================

    @Override
    public String generarRespuesta(

            PromptConfig config
    ) {

        // ==========================================
        // ENVÍO DEL PROMPT A OLLAMA
        // ==========================================

        String json =
                cliente.enviarPeticion(

                        nombreModelo,

                        config.getPromptFinal()
                );

        // ==========================================
        // LIMPIEZA DE RESPUESTA JSON
        // ==========================================

        return extraerRespuesta(json);
    }

    // ==========================================
    // GETTERS
    // ==========================================

    @Override
    public String getNombreModelo() {

        return nombreVisual;
    }
}
