package com.ai.ollama.ollamaclient.context;

import com.ai.ollama.ollamaclient.OllamaClient;
import com.ai.ollama.ollamaclient.strategy.IAStrategy;
import com.ai.ollama.ollamaclient.template.PromptConfig;

public class ModeloStrategy implements IAStrategy {

    private final OllamaClient cliente;

    private final ResponseParser parser;

    private final String nombreModelo;

    private final String nombreVisual;

    public ModeloStrategy(
            String nombreModelo,
            String nombreVisual,
            OllamaClient cliente
    ) {

        this.nombreModelo = nombreModelo;
        this.nombreVisual = nombreVisual;
        this.cliente = cliente;
        this.parser = new ResponseParser();
    }

    @Override
    public String generarRespuesta(
            PromptConfig config
    ) {

        String json =
                cliente.enviarPeticion(
                        nombreModelo,
                        config.getPromptFinal()
                );

        return parser.extraerRespuesta(
                json
        );
    }

    @Override
    public String getNombreModelo() {

        return nombreModelo;
    }

    @Override
    public String getNombreVisual() {

        return nombreVisual;
    }
}