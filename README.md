# Mutantes

Como parte del challenge, este componente implementa una API REST que verifica si una representación del ADN en base a una matriz cuadrada de NxN (que solo puede contener los elementos A, T, G y C), incluye más de una secuencia de 4 caracteres iguales dentro de sus entradas.

Por ejemplo, un ADN válido sería: 

|||||||
|---|---|---|---|---|---|
| **A** | T | G | C | **G** | A |
| C | **A** | G | T | **G** | C |
| T | T | **A** | T | **G** | T |
| A | G | A | **A** | **G** | G |
| **C** | **C** | **C** | **C** | T | A |
| T | C | A | C | T | G |

## Implementación

Está implementado exclusivamente en Spring Boot. Para la generación y ejecución, también se dispone de un Makefile para facilitar estás operaciones.

### Algoritmo

### API REST

## Build

## Tests

### Coverage

### Load

## Deploy

### Local

### Cloud 
