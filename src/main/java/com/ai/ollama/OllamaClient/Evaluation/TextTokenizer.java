package com.ai.ollama.OllamaClient.Evaluation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// =====================================
// TOKENIZADOR DE TEXTO
// =====================================
//
// Esta clase tiene la responsabilidad
// exclusiva de procesar texto y convertirlo
// en tokens utilizables para evaluación.
//
// Beneficios arquitectónicos:
//
// - SRP (Single Responsibility Principle)
// - reutilización
// - desacoplamiento
// - mantenibilidad
// - escalabilidad
//
// Esto permite reutilizar tokenización
// en:
//
// - benchmarking
// - NLP
// - embeddings
// - búsqueda semántica
// - análisis textual
//
// =====================================

public class TextTokenizer {

    // =====================================
    // TOKENIZACIÓN
    // =====================================
    //
    // Convierte texto libre en un conjunto
    // limpio de tokens.
    //
    // El proceso incluye:
    //
    // - lowercase normalization
    // - eliminación de símbolos
    // - separación por espacios
    // - eliminación de duplicados
    //
    // =====================================

    public Set<String> tokenizar(
            String texto
    ) {

        String[] tokens =
                texto.toLowerCase()
                        .replaceAll(
                                "[^a-zA-ZáéíóúÁÉÍÓÚñÑ ]",
                                ""
                        )
                        .split("\\s+");

        return new HashSet<>(
                Arrays.asList(tokens)
        );
    }
}
