# Zenwk Workflows

Repositorio centralizado de workflows reutilizables para el ecosistema Zenwk.

Este repositorio contiene **plantillas base (templates)** diseñadas para ser invocadas desde otros repositorios mediante `workflow_call`.  
No contiene pipelines / actions acoplados a un servicio específico ni a un entorno determinado.

El objetivo es estandarizar CI/CD, análisis de calidad y automatización de despliegue bajo una convención clara, escalable y mantenible.

---

# Convención Oficial de Nombres

Todos los workflows deben seguir estrictamente la siguiente estructura:

#### [categoria]-[accion]-[stack]-[scope].yml


---

## 1. Componentes de la Convención

### categoria

Define el dominio funcional del workflow.

Valores permitidos:

- `ci` → Integración continua
- `cd` → Despliegue continuo
- `quality` → Análisis de calidad
- `security` → Análisis de seguridad
- `infra` → Automatización de infraestructura

No se permiten categorías personalizadas sin aprobación arquitectónica.

---

### accion

Define la operación principal que ejecuta el workflow.

Valores permitidos:

- `build`
- `test`
- `scan`
- `deploy`
- `publish`

El nombre debe representar una única responsabilidad clara.

Un workflow no debe mezclar múltiples acciones principales.

---

### stack

Define la tecnología o contexto técnico principal.

Valores permitidos (extensible bajo control):

- `java`
- `nextjs`
- `docker`
- `node`
- `shared`

Si el workflow es agnóstico de tecnología, usar `shared`.

---

### scope

Define el alcance del workflow.

Valores permitidos:

- `template` → Workflow base reutilizable vía `workflow_call`
- `reusable` → Workflow genérico preparado para invocación externa

En este repositorio, la mayoría deben ser `template`.

---

# Reglas Obligatorias

1. Todos los nombres deben estar en minúsculas.
2. No usar abreviaturas ambiguas.
3. No incluir nombres de servicios específicos.
4. No incluir entorno (`dev`, `qa`, `prod`).
5. Un archivo = una responsabilidad principal.
6. Todos los workflows deben ser reutilizables vía `workflow_call`.

---

# Workflows Existentes

A continuación se describen los workflows actualmente disponibles en el repositorio, adaptados a la  convención.

---

## ci-build-docker-template.yml  

**Categoría:** CI  
**Acción:** build  
**Stack:** docker  
**Scope:** template  

### Propósito

- Construir imagen Docker.
- Etiquetar la imagen.
- Publicar en registry configurado.
- Preparar artefacto para despliegue posterior.

Este workflow no realiza despliegue.

---

## cd-deploy-docker-template.yml  

**Categoría:** CD  
**Acción:** deploy  
**Stack:** docker  
**Scope:** template  

### Propósito

- Desplegar imagen Docker previamente construida.
- Ejecutar pasos de despliegue controlados.
- Operar sobre infraestructura destino.

Este workflow no realiza build.

---

## quality-scan-java-template.yml  

**Categoría:** quality  
**Acción:** scan  
**Stack:** java  
**Scope:** template  

### Propósito

- Ejecutar análisis de calidad sobre proyectos Java.
- Integración con SonarQube o SonarCloud.
- Validar cobertura, deuda técnica y reglas estáticas.

No compila ni despliega.

---

## quality-scan-nextjs-template.yml  

**Categoría:** quality  
**Acción:** scan  
**Stack:** nextjs  
**Scope:** template  

### Propósito

- Ejecutar análisis de calidad sobre aplicaciones Next.js.
- Validar métricas de calidad y reglas estáticas.
- Integración con plataforma de análisis configurada.

---

# Filosofía del Repositorio

Este repositorio existe para evitar:

- Duplicación de pipelines.
- Inconsistencias entre servicios.
- Acoplamiento innecesario.
- Automatización caótica.

Un workflow es infraestructura declarativa.  
La infraestructura debe ser explícita, simple y predecible.

---

# Cómo Crear un Nuevo Workflow

Antes de crear un nuevo archivo:

1. Verificar que no exista uno equivalente.
2. Confirmar que la responsabilidad sea única.
3. Asegurar que cumpla la convención oficial.
4. Implementar como `workflow_call`.
5. Documentar su propósito en este README.

Si un workflow necesita múltiples responsabilidades, debe dividirse.

---

Zenwk prioriza consistencia, claridad y escalabilidad en su automatización.


