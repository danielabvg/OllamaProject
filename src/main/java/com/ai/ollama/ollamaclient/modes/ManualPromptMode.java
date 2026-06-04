package com.ai.ollama.ollamaclient.modes;

import com.ai.ollama.ollamaclient.context.ModeloStrategy;
import com.ai.ollama.ollamaclient.OllamaClient;
import com.ai.ollama.ollamaclient.prompting.PromptStrategy;
import com.ai.ollama.ollamaclient.prompting.PromptStrategyDetector;
import com.ai.ollama.ollamaclient.strategy.IAStrategy;
import com.ai.ollama.ollamaclient.template.PromptConfig;

@SuppressWarnings("java:S106")
public class ManualPromptMode {

    public void ejecutar(

            String promptManual,

            int opcionModelo
    ) {

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

    private PromptStrategy detectarStrategy(
            String prompt
    ) {

        PromptStrategyDetector detector =
                new PromptStrategyDetector();

        return detector.detectar(
                prompt
        );
    }

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