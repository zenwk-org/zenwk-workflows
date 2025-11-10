# Zenwk Workflows

Repositorio público que contiene los **workflows reutilizables** de CI/CD utilizados por los microservicios y componentes del ecosistema **Zenwk**.

Estos workflows permiten estandarizar los procesos de:
- Construcción y análisis de código.
- Integración con SonarCloud.
- Pruebas automatizadas.
- Construcción y despliegue de imágenes Docker.
- Publicación de artefactos (cuando aplique).

## Objetivos

1. **Estandarización:** garantizar que todos los microservicios de Zenwk sigan los mismos procesos de build, análisis y despliegue.  
2. **Mantenibilidad:** aplicar mejoras o correcciones en un solo punto y propagar automáticamente a todos los repositorios dependientes.  
3. **Transparencia:** permitir auditoría y revisión pública de los pipelines, sin exponer secretos ni configuraciones sensibles.  
4. **Escalabilidad:** facilitar la incorporación de nuevos módulos sin duplicar configuraciones.

---

## Estructura del Repositorio
zenwk-workflows/
├── .github/
│   └── workflows/
│        ├── sonarcloud.yml            # Workflow reusable para análisis SonarCloud
│        ├── gradle-build.yml          # Workflow reusable para builds con Gradle
│        ├── docker-build-push.yml     # Workflow reusable para construir y subir imágenes a DockerHub o AWS ECR
│        ├── test-runner.yml           # Ejecutor de pruebas unitarias y cobertura
│        └── release.yml               # Workflow reusable para crear versiones (tags) automáticas
├── sonar/
│   └── sonar-common.properties        # Configuración base para todos los análisis Sonar
├── README.md
└── LICENSE

---

## Uso desde otro repositorio

Ejemplo en `.github/workflows/sonar.yml` dentro de un microservicio Zenwk:

```yaml
name: SonarCloud Analysis

on:
  push:
    branches: [ main ]
  pull_request:
    types: [ opened, synchronize, reopened ]

jobs:
  sonar:
    uses: zenwk-org/zenwk-workflows/.github/workflows/sonarcloud.yml@main
    with:
      projectKey: zenwk-verification
      organization: zenwk-org
    secrets: inherit
