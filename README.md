# Todo List App — Backend

Backend REST desarrollado con Quarkus (Java) como parte del proyecto final del módulo. Expone una API para gestionar usuarios, listas de tareas y tareas, con autenticación mediante Firebase JWT.

## Tecnologías

- Quarkus 3.x + Java 21
- Hibernate ORM + Panache
- MySQL (Cloud SQL en producción)
- Firebase Admin SDK (verificación de tokens JWT)
- Docker (deploy en Render)
- Clean Architecture (domain / application / infrastructure / interfaces)

## Endpoints principales

| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/users` | Registro de usuario |
| GET | `/listas` | Obtener listas del usuario |
| POST | `/listas` | Crear lista |
| PUT | `/listas/{id}` | Editar lista |
| DELETE | `/listas/{id}` | Eliminar lista |
| GET | `/listas/{id}/todos` | Tareas de una lista |
| GET | `/todos` | Todas las tareas del usuario |
| POST | `/todos` | Crear tarea |
| PUT | `/todos/{id}` | Editar tarea |
| DELETE | `/todos/{id}` | Eliminar tarea |
| GET | `/search?q=` | Buscar listas y tareas |
| GET | `/health` | Estado del servidor y Firebase |

Todos los endpoints excepto `POST /users` y `GET /health` requieren el header:

```
Authorization: Bearer <firebase_id_token>
```

## Variables de entorno

```env
DB_URL=jdbc:mysql://host:3306/nombre_db
DB_USERNAME=usuario
DB_PASSWORD=contraseña
FIREBASE_CREDENTIALS=/etc/secrets/firebase.json
```

La credencial de Firebase se configura como **Secret File** en Render con el JSON del Service Account.

## Ejecutar en modo desarrollo

Requiere Java 21 y Maven instalados. También necesita MySQL corriendo localmente.

```bash
./mvnw quarkus:dev
```

El servidor inicia en `http://localhost:8080`.

## Ejecutar con Docker

```bash
docker build -t todolist-backend .
docker run -p 8080:8080 \
  -e DB_URL=jdbc:mysql://host:3306/db \
  -e DB_USERNAME=root \
  -e DB_PASSWORD=password \
  -e FIREBASE_CREDENTIALS=/app/firebase.json \
  todolist-backend
```

## Deploy

El backend está deployado en Render:

```
https://backtodolist-ccu4.onrender.com
```

El deploy es automático al hacer push a `main`. Render construye la imagen Docker y reinicia el servicio.
