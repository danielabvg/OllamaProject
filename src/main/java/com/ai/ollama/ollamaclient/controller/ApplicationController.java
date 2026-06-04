package com.ai.ollama.ollamaclient.controller;

import com.ai.ollama.ollamaclient.OllamaClient;
import com.ai.ollama.ollamaclient.context.ModeloStrategy;
import com.ai.ollama.ollamaclient.modes.AutomaticPromptMode;
import com.ai.ollama.ollamaclient.modes.ManualPromptMode;
import com.ai.ollama.ollamaclient.strategy.IAStrategy;
import com.ai.ollama.ollamaclient.utils.ConsoleManager;

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

    // =====================================
    // MODO AUTOMÁTICO
    // =====================================

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

    // =====================================
    // MODO MANUAL
    // =====================================

    private void ejecutarModoManual(
            int opcionModelo
    ) {

        String prompt =
                console.leerPromptManual();

        if (opcionModelo == 4) {

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

                prompt,

                opcionModelo
        );
    }

    // =====================================
    // FACTORY DE MODELOS
    // =====================================

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