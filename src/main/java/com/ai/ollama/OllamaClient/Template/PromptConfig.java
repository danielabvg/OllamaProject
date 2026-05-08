// Configuration model for prompt engineering
package com.ai.ollama.OllamaClient.Template;

public class PromptConfig {

    private String rol;
    private String instrucciones;
    private String entrada;

    public PromptConfig(
            String rol,
            String instrucciones,
            String entrada
    ) {
        this.rol = rol;
        this.instrucciones = instrucciones;
        this.entrada = entrada;
    }

    public String getRol() {
        return rol;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public String getEntrada() {
        return entrada;
    }
}
