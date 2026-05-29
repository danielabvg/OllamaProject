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
// - phi3 Mini
// - gemma
// - codellama
//
// Esto elimina duplicación
// y mejora escalabilidad.
//
// ==========================================

public class ModeloStrategy
        implements IAStrategy {

    // ==========================================
    // CLIENTE HTTP
    // ==========================================

    private final OllamaClient cliente;

    // ==========================================
    // PARSER DE RESPUESTAS
    // ==========================================
    //
    // Desacopla completamente el procesamiento
    // JSON de la lógica de ejecución.
    //
    // Esto mejora:
    //
    // - mantenibilidad
    // - reutilización
    //
    // ==========================================

    private final ResponseParser parser;

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

        this.parser =
                new ResponseParser();
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

        return parser.extraerRespuesta(json);
    }

    // ==========================================
    // GETTERS
    // ==========================================

    @Override
    public String getNombreModelo() {

        return nombreVisual;
    }
}
