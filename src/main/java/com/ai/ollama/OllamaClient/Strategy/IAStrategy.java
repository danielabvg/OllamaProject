// =====================================
// ABSTRACCIÓN PRINCIPAL DE IA
// =====================================
//
// Esta interfaz define el contrato
// que deben seguir todos los modelos
// de lenguaje integrados en el sistema.
//
// Gracias a esta abstracción,
// el sistema puede trabajar con:
// - Llama3
// - Mistral
// - Phi3
//
// sin depender de implementaciones
// concretas.
//
// Esto permite:
// - Polimorfismo
// - Bajo acoplamiento
// - Escalabilidad
// - Arquitectura Plug and Play
// =====================================

package com.ai.ollama.OllamaClient.Strategy;

import com.ai.ollama.OllamaClient.Template.PromptConfig;

public interface IAStrategy {

    // Genera una respuesta utilizando
    // la configuración del prompt.

    String generarRespuesta(
            PromptConfig config
    );

    // Retorna el nombre del modelo
    // utilizado en benchmarking.

    String getNombreModelo();
}
