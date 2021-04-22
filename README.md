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

El algoritmo se encuentra implementado en el servici ADNCheckService, en el método *isMutant*.

En una primera instancia se implementó el algoritmo plano, con una ejecución promedio en las pruebas de 361ms. Luego se optó por una implementación en base a las clases *OverallStatus* y *EvaluationSectionStatus*, obteniendo tiempos del orden de los 32ms en promedio.

### API REST

Se implementa la siguiente API REST:

- **POST /mutant**: verifica que el adn enviado en el request body pertenezca a un mutante (response code 200) o a un humano (response code 403).
- **GET /stats**: obtiene las estadisticas de las evaluaciones realizadas.

## Build

Se buildea a través de maven, utilizando profiles:

- **local**: para ejecución local
- **docker**: para ejecución dockerizada local
- **cloud**: para ejecución en Google App Engine

### Local

```
mvn clean package -Plocal
```

### Cloud

```
mvn clean package
```

También se puede utilizar el Makefile

```
make build
```

## Tests

### Coverage

Los tests fueron implementados con JUnit5 (por una cuestión de tiempo no se utilizó Cucumber al aplicar TDD).

```
mvn test
```

Se puede generar el site para ver el informe de JaCoCo

```
mvn site
```

En el target/site/index.html se encuentra el informe completo (que está copiado a la raíz del proyecto). El coverage da un 90%, principalmente lo que falta son algunos elementos de lombok y algunos POJO creados.

### Load

El test de carga se ejecutó con jmeter, y éste se encuentra en tests/post-dnas.jmx.

Se llegó a correr con 500.000 threads, pero por temas de tiempo no se pudo probar más. 

## Deploy

Para el deploy, también se dejaron los scripts centralizados en el Makefile

### Local

Para el perfil local, se ejecuta el jar (una vez construido el artefacto con el profile -Plocal). Como requisito, hay que tener levantado un mongodb localmente.

```
java -jar target/mutantes.jar
```

También se puede ejecutar tanto el build como el run a través del comando

```
make run-local
```

Este comando levanta un mongodb definido en el docker-compose.yml, luego buildea con el perfil local y finalmente ejecuta el java -jar. 

### Cloud 

Para el despliegue en cloud, se utilizó el plugin de maven appengine, con la definición en src/main/appengine, previa construicción del artefacto con el perfil cloud (el perfil por defecto también está configurado para ejecutar en el cloud).

```
mvn appengine:deploy
```

A través del siguiente comando en el Makefile se buildea, se genera el site, y se despliega en el cloud:

```
make full-build
```

