package com.ai.ollama.ollamaclient.evaluation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
// =====================================

public class TextTokenizer {

    // =====================================
    // STOPWORDS
    // =====================================

    private static final Set<String> STOPWORDS =
            Set.of(

                    "de",
                    "la",
                    "el",
                    "los",
                    "las",

                    "un",
                    "una",
                    "unos",
                    "unas",

                    "y",
                    "o",

                    "que",
                    "en",
                    "del",
                    "al",

                    "es",
                    "son",
                    "se",

                    "por",
                    "para",
                    "con",
                    "sin",

                    "a"
            );

    // =====================================
    // TOKENIZACIÓN
    // =====================================

    public Set<String> tokenizar(
            String texto
    ) {

        if (texto == null ||
                texto.isBlank()) {

            return new HashSet<>();
        }

        String[] tokens =
                texto.toLowerCase()
                        .replaceAll(
                                "[^a-zA-ZáéíóúÁÉÍÓÚñÑ]",
                                " "
                        )
                        .split("\\s+");

        return Arrays.stream(tokens)

                .filter(
                        token ->
                                !token.isBlank()
                )

                .filter(
                        token ->
                                !STOPWORDS.contains(
                                        token
                                )
                )

                .collect(
                        Collectors.toSet()
                );
    }
}