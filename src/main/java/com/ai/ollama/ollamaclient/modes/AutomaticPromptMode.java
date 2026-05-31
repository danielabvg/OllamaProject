package com.ai.ollama.ollamaclient.modes;

import com.ai.ollama.ollamaclient.context.ModeloStrategy;
import com.ai.ollama.ollamaclient.OllamaClient;
import com.ai.ollama.ollamaclient.prompting.GeneradorPrompt;
import com.ai.ollama.ollamaclient.prompting.PromptStrategy;
import com.ai.ollama.ollamaclient.prompting.PromptStrategyDetector;
import com.ai.ollama.ollamaclient.strategy.IAStrategy;
import com.ai.ollama.ollamaclient.template.PromptBuilder;
import com.ai.ollama.ollamaclient.template.PromptConfig;

import java.util.Scanner;

// =====================================
// MODO DE PROMPT AUTOMÁTICO
// =====================================
//
// Esta clase representa la capa de
// interacción entre el usuario y el
// motor de Prompt Engineering.
//
// SonarQube recomienda reemplazar
// System.out por un logger.
//
// Sin embargo, esta clase forma parte
// de la interfaz de consola del sistema.
//
// Por diseño:
//
// - muestra menús
// - muestra prompts
// - muestra estrategias detectadas
// - muestra resultados al usuario
//
// Debido a que System.out es utilizado
// intencionalmente como mecanismo de
// interacción directa con el usuario,
// se suprime la regla java:S106.
//
// =====================================

@SuppressWarnings("java:S106")
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
    // GENERACIÓN DE CONFIGURACIÓN
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
    // DETECCIÓN DE ESTRATEGIA
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
    // VISUALIZACIÓN DE ESTRATEGIA
    // =====================================

    private void mostrarStrategy(
            PromptStrategy strategy
    ) {

        System.out.println("""

                ====================================
                Prompt strategy detectada
                ====================================
                """);

        System.out.println(
                strategy
                        .getClass()
                        .getSimpleName()
        );
    }

    // =====================================
    // VISUALIZACIÓN DE PROMPT
    // =====================================

    private void mostrarPrompt(
            String prompt
    ) {

        System.out.println("""

                ====================================
                Prompt generado
                ====================================
                """);

        System.out.println(
                prompt
        );
    }

    // =====================================
    // FACTORY DE MODELOS
    // =====================================
    //
    // Centraliza la creación de modelos
    // compatibles con Ollama.
    //
    // Facilita:
    //
    // - reutilización
    // - mantenibilidad
    // - extensibilidad
    //
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