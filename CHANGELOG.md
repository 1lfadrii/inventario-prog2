# Changelog — Sistema de Inventario

Todos los cambios importantes del proyecto están documentados aquí.

---

## [v1.2] — 2026-04-08

### Agregado
- Mensaje de bienvenida al iniciar el programa en `Main.java`
- Manejo de entradas inválidas en el menú: si el usuario escribe texto
  donde se espera un número, el programa muestra un mensaje claro y
  pide intentar de nuevo sin romperse

### Cambiado
- Métodos `leerInt()` y `leerDouble()` en `Menu.java` reemplazan las
  llamadas directas a `scanner.nextInt()` y `scanner.nextDouble()`

---

## [v1.1] — 2026-04-08

### Agregado
- Validaciones en `ProductoServicio.java` para todos los métodos del CRUD:
  - El ID debe ser mayor a 0
  - El nombre no puede estar vacío
  - La cantidad no puede ser negativa
  - El precio debe ser mayor a 0
  - No se permite registrar dos productos con el mismo ID
  - Se verifica que el producto exista antes de actualizar o eliminar
- Manejo de errores en `Menu.java` con `try-catch` para mostrar mensajes
  amigables cuando una validación falla

---

## [v1.0] — 2026-04-08

### Agregado
- Estructura completa del proyecto con arquitectura por capas
- Clase `Producto` con atributos id, nombre, cantidad y precio
- Interfaz `IProductoDAO` con las firmas del CRUD
- Clase `ProductoDAO` con lectura y escritura en `datos/productos.txt`
  usando `BufferedReader` y `BufferedWriter`
- CRUD completo en `ProductoDAO`: agregar, listar, buscar, actualizar
  y eliminar
- Interfaz `IProductoServicio` con las firmas del CRUD
- Clase `ProductoServicio` como puente entre el menú y el DAO
- Menú interactivo en consola con las opciones: registrar, listar,
  buscar, actualizar, eliminar y salir
- `Main.java` como punto de entrada del programa
