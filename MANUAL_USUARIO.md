# Manual de Usuario — Sistema de Inventario

Este manual explica cómo usar el Sistema de Inventario desde cero,
aunque no tengas experiencia con programas de consola.

---

## ¿Para qué sirve este sistema?

Este programa te permite llevar un registro de productos. Puedes agregar
productos nuevos, ver la lista de lo que tienes, buscar uno en específico,
actualizarlo si cambió el precio o la cantidad, y eliminarlo si ya no lo necesitas.
Toda la información se guarda en un archivo de texto para que no se pierda
al cerrar el programa.

---

## ¿Cómo ejecutar el programa?

1. Abre el proyecto en **Visual Studio Code**
2. Asegúrate de tener instalado el **Extension Pack for Java** de VS Code
3. Abre el archivo `src/Main.java`
4. Haz clic en el botón **Run** (el triángulo ▶ que aparece arriba a la derecha)
5. El programa se abrirá en la terminal de VS Code

Deberías ver esto:

```
Bienvenido al Sistema de Inventario (v1.2)

╔══════════════════════════════╗
║     SISTEMA DE INVENTARIO    ║
╠══════════════════════════════╣
║  1. Registrar producto       ║
║  2. Listar productos         ║
║  3. Buscar producto por ID   ║
║  4. Actualizar producto      ║
║  5. Eliminar producto        ║
║  6. Salir                    ║
╚══════════════════════════════╝
  Elige una opcion:
```

Escribe el número de la opción que quieras y presiona **Enter**.

---

## Opciones del menú

### Opción 1 — Registrar producto

Sirve para agregar un producto nuevo al inventario.

El programa te va a pedir:
- **ID**: un número entero que identifica al producto (por ejemplo: 1, 2, 3...)
- **Nombre**: el nombre del producto (por ejemplo: Laptop)
- **Cantidad**: cuántas unidades hay (por ejemplo: 10)
- **Precio**: el precio del producto (por ejemplo: 1500.00)

```
--- Registrar producto ---
ID: 1
Nombre: Laptop
Cantidad: 10
Precio: 1500.00
  Producto registrado correctamente.
```

### Opción 2 — Listar productos

Muestra todos los productos que hay en el inventario en forma de tabla.

```
--- Lista de productos ---
  ID    Nombre               Cantidad   Precio
  -----------------------------------------------
  1     Laptop               10         1500.00
  2     Mouse                25         15.50
```

Si no hay ningún producto registrado, aparece el mensaje:
`No hay productos registrados.`

### Opción 3 — Buscar producto por ID

Te permite buscar un producto específico si sabes su ID.

```
--- Buscar producto ---
ID a buscar: 1
  ID    Nombre               Cantidad   Precio
  -----------------------------------------------
  1     Laptop               10         1500.00
```

Si el ID no existe, verás:
`Error: Producto no encontrado ):`

### Opción 4 — Actualizar producto

Sirve para modificar los datos de un producto que ya existe.
Debes ingresar el ID del producto que quieres cambiar y luego los nuevos datos.

```
--- Actualizar producto ---
ID del producto a actualizar: 1
Nuevo nombre: Laptop Pro
Nueva cantidad: 8
Nuevo precio: 1800.00
  Producto actualizado correctamente.
```

### Opción 5 — Eliminar producto

Borra un producto del inventario. Solo necesitas el ID.

```
--- Eliminar producto ---
ID del producto a eliminar: 1
  Producto eliminado correctamente.
```

### Opción 6 — Salir

Cierra el programa.

```
  Hasta luego!
```

---

## Ejemplo completo paso a paso

A continuación un ejemplo de cómo registrar un producto, buscarlo y luego eliminarlo.

**Paso 1 — Registrar un producto**

Elige la opción 1 e ingresa los datos:

```
  Elige una opcion: 1

--- Registrar producto ---
ID: 5
Nombre: Teclado
Cantidad: 15
Precio: 45.00
  Producto registrado correctamente.
```

**Paso 2 — Verificar que quedó guardado**

Elige la opción 2 para ver la lista:

```
  Elige una opcion: 2

--- Lista de productos ---
  ID    Nombre               Cantidad   Precio
  -----------------------------------------------
  5     Teclado              15         45.00
```

**Paso 3 — Buscar el producto por ID**

Elige la opción 3 y escribe el ID 5:

```
  Elige una opcion: 3

--- Buscar producto ---
ID a buscar: 5
  ID    Nombre               Cantidad   Precio
  -----------------------------------------------
  5     Teclado              15         45.00
```

**Paso 4 — Eliminar el producto**

Elige la opción 5 y escribe el ID 5:

```
  Elige una opcion: 5

--- Eliminar producto ---
ID del producto a eliminar: 5
  Producto eliminado correctamente.
```

---

## ¿Qué pasa si ingreso datos incorrectos?

El sistema está preparado para manejar errores sin romperse.

**Si escribes texto donde se espera un número:**

```
ID: laptop
  INGRESE EL NÚMERO DE ID
  Intenta de nuevo:
```

El programa te avisa y te pide que lo intentes de nuevo.

**Si intentas registrar un producto con un ID que ya existe:**

```
ID: 1
...
  Error: Ya existe un producto con el ID 1.
```

**Si intentas buscar, actualizar o eliminar un ID que no existe:**

```
  Error: No existe un producto con el ID 99.
```

**Si dejas el nombre vacío o el precio en cero:**

```
  Error: El nombre no puede estar vacío.
  Error: El precio debe ser mayor a 0.
```

En todos estos casos el programa vuelve al menú principal sin perder datos.
