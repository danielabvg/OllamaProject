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
// - prompt final generado
//
// Esto mejora:
//
// - organización
// - reutilización
// - encapsulamiento
// - debugging
// - benchmarking
//
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
    // PROMPT FINAL
    // =====================================
    //
    // Guarda el prompt completo
    // después de aplicar:
    //
    // - Prompt Engineering
    // - Prompt Strategies
    // - Builder Pattern
    //
    // =====================================

    private String promptFinal;

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

    // =====================================
    // GET PROMPT FINAL
    // =====================================

    public String getPromptFinal() {

        return promptFinal;
    }

    // =====================================
    // SET PROMPT FINAL
    // =====================================
    //
    // Guarda el prompt final ya construido.
    //
    // Esto permite:
    //
    // - reutilización
    // - evaluación
    // - benchmarking
    // - logging
    // - debugging
    //
    // =====================================

    public void setPromptFinal(

            String promptFinal
    ) {

        this.promptFinal = promptFinal;
    }
}
