package com.ai.ollama.OllamaClient.Modes;

import com.ai.ollama.OllamaClient.Context.ModeloStrategy;
import com.ai.ollama.OllamaClient.OllamaClient;
import com.ai.ollama.OllamaClient.PromptingEngine.Impl.PromptStrategy;
import com.ai.ollama.OllamaClient.PromptingEngine.Impl.PromptStrategyDetector;
import com.ai.ollama.OllamaClient.Strategy.IAStrategy;
import com.ai.ollama.OllamaClient.Template.PromptConfig;

import java.util.Scanner;

// =====================================
// MODO DE PROMPT MANUAL
// =====================================
//
// Permite:
//
// - pegar prompts externos
// - usar prompts personalizados
// - detectar automáticamente
//   la técnica de prompting
//
// Técnicas soportadas:
//
// - Zero-Shot
// - Few-Shot
// - Chain-of-Thought
//
// =====================================

public class ManualPromptMode {

    // =====================================
    // MODO INDIVIDUAL
    // =====================================

    public void ejecutar(

            Scanner scanner,

            int opcionModelo
    ) {

        System.out.print("""

                Escribe tu prompt manual:
                """);

        String promptManual =
                scanner.nextLine();

        PromptConfig config =
                generarConfiguracion(
                        promptManual
                );

        PromptStrategy strategy =
                detectarStrategy(
                        promptManual
                );

        mostrarStrategy(
                strategy
        );

        IAStrategy modelo =
                crearModelo(
                        opcionModelo
                );

        if (modelo == null) {

            System.out.println(
                    "Modelo inválido."
            );

            return;
        }

        BenchmarkMode benchmark =
                new BenchmarkMode();

        benchmark.ejecutar(

                config,

                strategy,

                new IAStrategy[]{
                        modelo
                }
        );
    }

    // =====================================
    // BENCHMARK MULTIMODELO
    // =====================================

    public void ejecutarBenchmark(

            String prompt,

            IAStrategy[] modelos
    ) {

        PromptConfig config =
                generarConfiguracion(
                        prompt
                );

        PromptStrategy strategy =
                detectarStrategy(
                        prompt
                );

        mostrarStrategy(
                strategy
        );

        BenchmarkMode benchmark =
                new BenchmarkMode();

        benchmark.ejecutar(

                config,

                strategy,

                modelos
        );
    }

    // =====================================
    // CONFIGURACIÓN
    // =====================================

    private PromptConfig generarConfiguracion(
            String prompt
    ) {

        PromptConfig config =
                new PromptConfig(

                        "Usuario Manual",

                        "Prompt manual",

                        prompt
                );

        config.setPromptFinal(
                prompt
        );

        return config;
    }

    // =====================================
    // DETECCIÓN DE STRATEGY
    // =====================================

    private PromptStrategy detectarStrategy(
            String prompt
    ) {

        PromptStrategyDetector detector =
                new PromptStrategyDetector();

        return detector.detectar(
                prompt
        );
    }

    // =====================================
    // VISUALIZAR STRATEGY
    // =====================================

    private void mostrarStrategy(
            PromptStrategy strategy
    ) {

        System.out.println("""

                ====================================
                PROMPT STRATEGY DETECTADA
                ====================================
                """);

        System.out.println(
                strategy
                        .getClass()
                        .getSimpleName()
        );
    }

    // =====================================
    // FACTORY DE MODELOS
    // =====================================

    private IAStrategy crearModelo(
            int opcion
    ) {

        OllamaClient cliente =
                new OllamaClient();

        return switch (opcion) {

            case 1 -> new ModeloStrategy(
                    "llama3",
                    "Llama3",
                    cliente
            );

            case 2 -> new ModeloStrategy(
                    "mistral",
                    "Mistral",
                    cliente
            );

            case 3 -> new ModeloStrategy(
                    "phi3:mini",
                    "Phi3 Mini",
                    cliente
            );

            default -> null;
        };
    }
}