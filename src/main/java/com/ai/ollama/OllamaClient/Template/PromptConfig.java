// =====================================
// CONFIGURACIÓN DE PROMPTS
// =====================================
//
// Esta clase encapsula toda la información
// necesaria para construir un prompt.
//
// Incluye:
// - rol/persona
// - instrucciones
// - entrada del usuario
//
// Esto mejora organización y reutilización.
// =====================================

package com.ai.ollama.OllamaClient.Template;

public class PromptConfig {

    // =====================================
    // COMPONENTES DEL PROMPT
    // =====================================

    private String rol;
    private String instrucciones;
    private String entrada;

    // =====================================
    // CONSTRUCTOR
    // =====================================
    //
    // Inicializa toda la configuración
    // necesaria para el modelo.
    // =====================================

    public PromptConfig(
            String rol,
            String instrucciones,
            String entrada
    ) {

        this.rol = rol;
        this.instrucciones = instrucciones;
        this.entrada = entrada;
    }

    // =====================================
    // GETTERS
    // =====================================
    //
    // Permiten acceder a la configuración
    // del prompt de forma controlada.
    // =====================================

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
