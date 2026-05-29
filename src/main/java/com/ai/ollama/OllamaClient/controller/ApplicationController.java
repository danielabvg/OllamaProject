package com.ai.ollama.OllamaClient.controller;

import com.ai.ollama.OllamaClient.Context.ModeloStrategy;
import com.ai.ollama.OllamaClient.Modes.AutomaticPromptMode;
import com.ai.ollama.OllamaClient.Modes.BenchmarkMode;
import com.ai.ollama.OllamaClient.Modes.ManualPromptMode;
import com.ai.ollama.OllamaClient.OllamaClient;
import com.ai.ollama.OllamaClient.Strategy.IAStrategy;
import com.ai.ollama.OllamaClient.Utils.ConsoleManager;

public class ApplicationController {

    private final ConsoleManager console =
            new ConsoleManager();

    public void iniciar() {

        while (true) {

            console.mostrarMenuPrincipal();

            int opcionPrompt =
                    console.leerOpcion();

            if (opcionPrompt == 3) {

                console.mostrarDespedida();
                console.cerrar();
                break;
            }

            console.mostrarMenuModelos();

            int opcionModelo =
                    console.leerOpcion();

            if (opcionPrompt == 1) {

                ejecutarModoAutomatico(
                        opcionModelo
                );
            }

            else if (opcionPrompt == 2) {

                ejecutarModoManual(
                        opcionModelo
                );
            }

            else {

                console.mostrarOpcionInvalida();
            }
        }
    }

    private void ejecutarModoAutomatico(
            int opcionModelo
    ) {

        if (opcionModelo == 4) {

            String pregunta =
                    console.leerPregunta();

            AutomaticPromptMode automatico =
                    new AutomaticPromptMode();

            automatico.ejecutarBenchmark(
                    pregunta,
                    crearModelos()
            );

            return;
        }

        AutomaticPromptMode modo =
                new AutomaticPromptMode();

        modo.ejecutar(

                console.getScanner(),

                opcionModelo
        );
    }

    private void ejecutarModoManual(
            int opcionModelo
    ) {

        if (opcionModelo == 4) {

            String prompt =
                    console.leerPromptManual();

            ManualPromptMode manual =
                    new ManualPromptMode();

            manual.ejecutarBenchmark(
                    prompt,
                    crearModelos()
            );

            return;
        }

        ManualPromptMode modo =
                new ManualPromptMode();

        modo.ejecutar(

                console.getScanner(),

                opcionModelo
        );
    }

    private IAStrategy[] crearModelos() {

        OllamaClient cliente =
                new OllamaClient();

        return new IAStrategy[] {

                new ModeloStrategy(
                        "llama3",
                        "Llama3",
                        cliente
                ),

                new ModeloStrategy(
                        "mistral",
                        "Mistral",
                        cliente
                ),

                new ModeloStrategy(
                        "phi3:mini",
                        "Phi3 Mini",
                        cliente
                )
        };
    }
}