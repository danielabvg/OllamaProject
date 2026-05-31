// =====================================
// GENERADOR DE PROMPTS
// =====================================
//
// Esta clase automatiza la construcción
// de configuraciones de prompt.
//
// Utiliza IntentRouter para:
// - detectar el rol adecuado
// - optimizar instrucciones
//
// Esto permite generar prompts más
// dinámicos e inteligentes.
// =====================================

package com.ai.ollama.ollamaclient.prompting;

import com.ai.ollama.ollamaclient.intentrouting.IntentRouter;
import com.ai.ollama.ollamaclient.template.PromptConfig;

public class GeneradorPrompt {

    // =====================================
    // ROUTER DE INTENCIONES
    // =====================================
    //
    // Analiza automáticamente la entrada
    // del usuario antes de generar
    // la configuración del prompt.
    // =====================================

    private final IntentRouter router;

    // =====================================
    // CONSTRUCTOR
    // =====================================
    //
    // Inicializa el sistema de routing.
    // =====================================

    public GeneradorPrompt() {

        this.router = new IntentRouter();
    }

    // =====================================
    // GENERACIÓN DE PROMPT
    // =====================================
    //
    // Construye automáticamente un
    // PromptConfig utilizando:
    // - rol detectado
    // - instrucciones optimizadas
    // - entrada del usuario
    // =====================================

    public PromptConfig generar(
            String entradaUsuario
    ) {

        // Determina el rol más adecuado.

        String rol =
                router.determinarRol(
                        entradaUsuario
                );

        // Optimiza las instrucciones.

        String instrucciones =
                router.optimizarInstrucciones(
                        entradaUsuario
                );

        // Retorna configuración completa.

        return new PromptConfig(
                rol,
                instrucciones,
                entradaUsuario
        );
    }
}

