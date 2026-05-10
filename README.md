# ms-api-gateway

API Gateway del sistema de gestión escolar AULABO.

Este servicio centraliza el acceso desde el frontend hacia los microservicios backend. Actualmente valida autenticación con Firebase y enruta solicitudes hacia `ms-gestion-academica`.

---

## Estado actual

Para la primera entrega, el gateway está configurado para trabajar con:

- Frontend en `http://localhost:5173`
- API Gateway en `http://localhost:8080`
- `ms-gestion-academica` en `http://localhost:8081`
- Firebase Authentication para validar tokens Bearer
- PostgreSQL usado indirectamente por `ms-gestion-academica`

Actualmente `ms-asistencia` no está activo en las rutas del gateway. Se integrará cuando ese microservicio esté listo y probado.

---

## Responsabilidad del gateway

El gateway se encarga de:

- Recibir solicitudes del frontend.
- Validar tokens Firebase en rutas protegidas `/api/**`.
- Redirigir solicitudes hacia los microservicios configurados.
- Centralizar CORS para permitir llamadas desde el frontend.
- Exponer health check mediante Spring Boot Actuator.

El gateway no contiene lógica de negocio académica ni conexión directa a base de datos.

---

## Tecnologías principales

- Java 21
- Spring Boot 4.0.6
- Spring Cloud Gateway Server WebMVC
- Spring Security
- Firebase Admin SDK
- Spring Boot Actuator
- Docker

---

## Rutas configuradas

Las siguientes rutas se redirigen hacia `ms-gestion-academica`:

```txt
/api/alumnos/**
/api/alumnosCurso/**
/api/asignaturas/**
/api/cargas-academicas/**
/api/cursos/**
/api/evaluaciones/**
/api/notas/**
/api/usuarios/**