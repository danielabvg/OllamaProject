// =====================================
// CLASE CONTEXTO DEL PATRÓN STRATEGY
// =====================================
//
// Esta clase administra dinámicamente
// la estrategia/modelo de IA que se utilizará.
//
// El agente NO conoce detalles internos
// de Llama3, Mistral o Phi3. (etc.)
//
// Solo trabaja con la abstracción:
// IAStrategy.
//
// Esto permite:
// - Polimorfismo
// - Bajo acoplamiento
// - Escalabilidad
// - Plug and Play de modelos
// =====================================

package com.ai.ollama.OllamaClient.Context;

import com.ai.ollama.OllamaClient.Strategy.IAStrategy;
import com.ai.ollama.OllamaClient.Template.PromptConfig;

public class AgenteConversacional {

    // Estrategia/modelo activo.
    // Puede ser:
    // - ModeloStrategy(llama3)
    // - ModeloStrategy(mistral)
    // - ModeloStrategy(phi3)

    private IAStrategy estrategia;

    // =====================================
    // CONSTRUCTOR
    // =====================================
    //
    // Recibe dinámicamente la estrategia
    // mediante Inyección de Dependencias.
    // =====================================

    public AgenteConversacional(
            IAStrategy estrategia
    ) {

        this.estrategia = estrategia;
    }

    // =====================================
    // MÉTODO PRINCIPAL
    // =====================================
    //
    // Recibe la configuración del prompt
    // y delega la generación de respuesta
    // a la estrategia seleccionada.
    // =====================================

    public String preguntar(
            PromptConfig config
    ) {

        // Aquí ocurre el polimorfismo:
        // cada estrategia implementa
        // su propia lógica de respuesta.

        return estrategia.generarRespuesta(config);
    }
}