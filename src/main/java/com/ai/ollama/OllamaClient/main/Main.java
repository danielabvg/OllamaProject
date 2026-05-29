package com.ai.ollama.OllamaClient.main;

import com.ai.ollama.OllamaClient.controller.ApplicationController;

// =====================================
// MAIN PRINCIPAL
// =====================================
//
// Punto de entrada del sistema.
//
// RESPONSABILIDAD:
//
// - iniciar aplicación
//
// Toda la lógica fue desacoplada hacia:
//
// - controller
// - modes
// - services
// - evaluation
//
// =====================================

public class Main {

    public static void main(String[] args) {

        ApplicationController app =
                new ApplicationController();

        app.iniciar();
    }
}