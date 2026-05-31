package com.ai.ollama.ollamaclient.main;

import com.ai.ollama.ollamaclient.controller.ApplicationController;

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