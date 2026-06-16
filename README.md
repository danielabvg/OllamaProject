# Ollama Multimodel Integration Framework

## Overview

This project presents an **Object-Oriented Design proposal** for the integration and evaluation of multiple Large Language Models (LLMs) executed locally through Ollama.

The proposal focuses on applying Object-Oriented Programming principles, design patterns, Prompt Engineering techniques, and benchmarking mechanisms to create a maintainable, reusable, and extensible conversational system.

To validate the proposed design, an implementation was developed in **Java 17**, integrating multiple language models and evaluation components under a common software structure.

The framework was conceived as a software engineering solution rather than a model-specific implementation, emphasizing modularity, low coupling, and scalability.

---

# Research Motivation

The growing adoption of Large Language Models has created new challenges for software design.

Modern conversational systems must:

- Integrate multiple LLMs
- Support different prompting strategies
- Compare model performance objectively
- Incorporate new models without modifying the system core
- Maintain software quality and extensibility

This project addresses these challenges through an Object-Oriented Design approach.

---

# Main Contributions

The proposed solution provides:

- Integration of multiple LLMs under a common structure
- Dynamic Prompt Engineering strategy selection
- Multimodel benchmarking capabilities
- Application of Strategy and Builder patterns
- Reusable and extensible software design
- Experimental evaluation under homogeneous conditions

---

# Object-Oriented Design Approach

The framework was developed following core Object-Oriented Programming principles.

## Abstraction

Common interfaces define the behavior of language models and prompting strategies.

Examples:

- `IAStrategy`
- `PromptStrategy`

## Encapsulation

Responsibilities are distributed across specialized components.

Examples:

- Prompt generation
- Intent routing
- Model execution
- Evaluation processes

## Polymorphism

Models and prompting techniques can be exchanged dynamically through common abstractions without modifying the application workflow.

---

# Design Patterns Applied

## Strategy Pattern

The Strategy pattern was adopted to support dynamic model integration and prompt strategy selection.

Implemented through:

- `IAStrategy`
- `ModeloStrategy`
- `PromptStrategy`

Benefits:

- Low coupling
- Extensibility
- Runtime behavior flexibility

---

## Builder Pattern

Prompt generation requires combining instructions, context, examples, and user input.

To avoid rigid prompt construction, the Builder pattern was implemented through:

- `PromptBuilder`
- `PromptConfig`

Benefits:

- Flexible prompt generation
- Reusable construction process
- Improved maintainability

---

# Prompt Engineering Layer

The framework incorporates several Prompt Engineering techniques:

- Zero-Shot Prompting
- Few-Shot Prompting
- Chain-of-Thought Prompting

Prompt strategies are selected according to the characteristics of the user's request, allowing adaptive conversational behavior.

---

# Integrated Models

The validation implementation currently integrates:

- Llama3
- Mistral
- Phi3 Mini

The design allows new models to be incorporated without modifying the system core.

---

# Evaluation Framework

To compare model performance objectively, the framework includes a benchmarking process based on multiple evaluation metrics.

## Evaluation Metrics

| Metric | Purpose |
|----------|----------|
| Semantic Similarity | Measures relevance to the prompt |
| Consistency Score | Measures response stability |
| Prompt Deviation Risk | Estimates conceptual deviation |
| Latency Score | Measures response speed |
| Composite Score | Aggregates overall performance |

---

# Benchmarking Pipeline

The evaluation process is organized through reusable components.

## Main Components

| Component | Responsibility |
|------------|---------------|
| SemanticEvaluator | Semantic similarity |
| ConsistencyEvaluator | Response consistency |
| LatencyEvaluator | Execution time |
| PromptDeviationRiskEstimator | Prompt deviation analysis |
| WeightedScoreCalculator | Final score generation |
| ResponseQualityAnalyzer | Quality interpretation |
| BenchmarkPipeline | Evaluation orchestration |

---

# Software Quality Analysis

In addition to benchmarking, the project incorporates static code analysis.

## SonarQube

SonarQube was used to evaluate:

- Maintainability
- Code duplication
- Complexity
- Technical debt
- Software quality indicators

The tool was employed as a software quality validation mechanism rather than a performance evaluation system.

---

# Project Structure

```plaintext
src/main/java/com/ai/ollama/ollamaclient

├── context
│   ├── AgenteConversacional
│   ├── ModeloStrategy
│   └── ResponseParser
│
├── controller
│   └── ApplicationController
│
├── evaluation
│   ├── BenchmarkPipeline
│   ├── SemanticEvaluator
│   ├── ConsistencyEvaluator
│   ├── LatencyEvaluator
│   ├── PromptDeviationRiskEstimator
│   ├── ResponseQualityAnalyzer
│   ├── WeightedScoreCalculator
│   └── EvaluationResult
│
├── intentrouting
│   └── IntentRouter
│
├── prompting
│   ├── PromptStrategy
│   ├── ZeroShotPromptStrategy
│   ├── FewShotPromptStrategy
│   ├── ChainOfThoughtPromptStrategy
│   ├── PromptStrategyDetector
│   └── GeneradorPrompt
│
├── strategy
│   └── IAStrategy
│
├── template
│   ├── PromptBuilder
│   └── PromptConfig
│
├── services
│   ├── HttpService
│   └── RequestBodyBuilder
│
├── main
│   └── Main
│
├── modes
│   ├── AutomaticPromptMode
│   ├── ManualPromptMode
│   ├── BenchmarkMode
│   └── ModelBenchmarkResult
│
└── utils
    ├── ConsoleManager
    └── OllamaClient
```

---

# Validation Process

The proposal was validated following the Software Development Life Cycle (SDLC).

## Analysis

- Domain model definition
- Identification of concepts and relationships
- Conversational flow analysis

## Design

- Domain Model
- General Class Diagram
- Sequence Diagram
- Strategy Pattern
- Builder Pattern

## Implementation

- Java 17
- Ollama integration
- Llama3
- Mistral
- Phi3 Mini
- Prompt Engineering strategies

## Validation

- Multimodel benchmarking
- Evaluation metrics
- Static code analysis with SonarQube

---

# Results Summary

Benchmarking experiments were conducted using:

- Llama3
- Mistral
- Phi3 Mini

under two execution modes:

- Automatic Prompt Generation
- Manual Prompt Definition

Results showed that:

- The proposed design supports dynamic model integration.
- Models can be compared without modifying the application core.
- Manual prompting achieved higher composite scores.
- Llama3 obtained the best overall performance.
- The architecture remained stable across all evaluation scenarios.

---

# Future Work

Potential extensions include:

- Additional language models
- New evaluation metrics
- Advanced prompt strategy selection
- Larger validation datasets
- Domain-specific benchmarking scenarios

---

# Conclusion

This project demonstrates that Object-Oriented Design provides an effective foundation for integrating and evaluating multiple language models within a common software structure.

The application of abstraction, encapsulation, polymorphism, and the Strategy and Builder patterns enabled the construction of a maintainable, reusable, and extensible solution.

The validation process confirmed that the proposed design supports multimodel integration, Prompt Engineering strategies, and benchmarking workflows while preserving low coupling and separation of responsibilities.