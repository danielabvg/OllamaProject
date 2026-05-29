package com.ai.ollama.OllamaClient.Modes;

import com.ai.ollama.OllamaClient.Context.ModeloStrategy;
import com.ai.ollama.OllamaClient.OllamaClient;
import com.ai.ollama.OllamaClient.PromptingEngine.Impl.GeneradorPrompt;
import com.ai.ollama.OllamaClient.PromptingEngine.Impl.PromptStrategy;
import com.ai.ollama.OllamaClient.PromptingEngine.Impl.PromptStrategyDetector;
import com.ai.ollama.OllamaClient.Strategy.IAStrategy;
import com.ai.ollama.OllamaClient.Template.PromptBuilder;
import com.ai.ollama.OllamaClient.Template.PromptConfig;

import java.util.Scanner;

// =====================================
// MODO DE PROMPT AUTOMÁTICO
// =====================================

public class AutomaticPromptMode {

    // =====================================
    // MODO INDIVIDUAL
    // =====================================

    public void ejecutar(

            Scanner scanner,

            int opcionModelo
    ) {

        System.out.print("""

                Escribe tu pregunta:
                """);

        String pregunta =
                scanner.nextLine();

        PromptConfig config =
                generarConfiguracion(
                        pregunta
                );

        PromptStrategy strategy =
                detectarStrategy(
                        pregunta
                );

        mostrarStrategy(
                strategy
        );

        mostrarPrompt(
                config.getPromptFinal()
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

            String pregunta,

            IAStrategy[] modelos
    ) {

        PromptConfig config =
                generarConfiguracion(
                        pregunta
                );

        PromptStrategy strategy =
                detectarStrategy(
                        pregunta
                );

        mostrarStrategy(
                strategy
        );

        mostrarPrompt(
                config.getPromptFinal()
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
    // GENERACIÓN DE CONFIG
    // =====================================

    private PromptConfig generarConfiguracion(
            String pregunta
    ) {

        GeneradorPrompt generador =
                new GeneradorPrompt();

        PromptConfig config =
                generador.generar(
                        pregunta
                );

        PromptStrategy strategy =
                detectarStrategy(
                        pregunta
                );

        PromptBuilder builder =
                new PromptBuilder()

                        .conRol(
                                config.getRol()
                        )

                        .conInstrucciones(
                                config.getInstrucciones()
                        )

                        .conEntrada(
                                config.getEntrada()
                        );

        String promptFinal =
                strategy
                        .definirEstructuraPrompt(
                                builder
                        );

        config.setPromptFinal(
                promptFinal
        );

        return config;
    }

    // =====================================
    // DETECCIÓN DE STRATEGY
    // =====================================

    private PromptStrategy detectarStrategy(
            String pregunta
    ) {

        PromptStrategyDetector detector =
                new PromptStrategyDetector();

        return detector.detectar(
                pregunta
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
    // VISUALIZAR PROMPT
    // =====================================

    private void mostrarPrompt(
            String prompt
    ) {

        System.out.println("""

                ====================================
                PROMPT GENERADO
                ====================================
                """);

        System.out.println(
                prompt
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