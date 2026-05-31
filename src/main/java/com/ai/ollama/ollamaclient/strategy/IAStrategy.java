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

package com.ai.ollama.ollamaclient.strategy;

import com.ai.ollama.ollamaclient.template.PromptConfig;

public interface IAStrategy {

    String generarRespuesta(PromptConfig config);

    String getNombreModelo();

    String getNombreVisual();
}
