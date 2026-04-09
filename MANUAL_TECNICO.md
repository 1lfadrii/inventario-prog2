# Manual Técnico — Sistema de Inventario

Este manual explica cómo está construido el proyecto por dentro: cómo están
organizadas las carpetas, para qué sirve cada archivo y cómo funciona el
sistema de principio a fin.

---

## Organización del proyecto

```
QUIZS13/
├── src/
│   ├── entidades/
│   │   └── Producto.java
│   ├── datos/
│   │   ├── IProductoDAO.java
│   │   └── ProductoDAO.java
│   ├── negocio/
│   │   ├── IProductoServicio.java
│   │   └── ProductoServicio.java
│   ├── presentacion/
│   │   └── Menu.java
│   └── Main.java
└── datos/
    └── productos.txt
```

El proyecto usa **arquitectura por capas**, que básicamente significa que
cada parte del código tiene una responsabilidad específica y no se mezcla
con las demás.

---

## ¿Para qué sirve cada capa?

### Capa de Entidades

Contiene las clases que representan los objetos del mundo real.
En este caso, solo hay un objeto: **Producto**.
Esta capa no sabe nada de archivos, ni de menús, ni de reglas de negocio.
Solo guarda y entrega datos.

### Capa de Datos (DAO)

DAO significa *Data Access Object*. Es la capa que se encarga de leer y
escribir en el archivo `productos.txt`. Nadie más en el sistema toca el archivo
directamente, solo esta capa.

### Capa de Negocio (Servicio)

Es el intermediario entre el menú y los datos. Aquí están todas las reglas:
que el ID no puede repetirse, que el precio debe ser mayor a cero, que el
nombre no puede estar vacío, etc. Si algo no cumple las reglas, lanza un error
antes de llegar al archivo.

### Capa de Presentación

Es lo que ve el usuario: el menú en consola, los mensajes, las tablas de
resultados. Esta capa recibe lo que escribe el usuario, se lo pasa al servicio
y muestra lo que le devuelven. No toma decisiones sobre los datos.

---

## ¿Para qué sirve cada archivo?

| Archivo | Para qué sirve |
|---|---|
| `Producto.java` | Define los atributos del producto (id, nombre, cantidad, precio) con sus getters, setters y toString |
| `IProductoDAO.java` | Interfaz que define qué operaciones debe poder hacer cualquier DAO |
| `ProductoDAO.java` | Implementa las operaciones del CRUD leyendo y escribiendo en el archivo txt |
| `IProductoServicio.java` | Interfaz que define qué operaciones expone el servicio al menú |
| `ProductoServicio.java` | Implementa las reglas de negocio y delega las operaciones al DAO |
| `Menu.java` | Muestra el menú en consola, lee lo que escribe el usuario y llama al servicio |
| `Main.java` | Punto de entrada del programa, solo crea el menú y lo arranca |
| `productos.txt` | Archivo donde se guardan los productos, una línea por producto |

---

## ¿Cómo viaja la información desde el menú hasta el archivo?

Cuando el usuario elige registrar un producto, la información pasa por
estas capas en orden:

```
Usuario escribe en consola
        ↓
Menu.java recoge los datos y crea un objeto Producto
        ↓
ProductoServicio.java valida que los datos sean correctos
        ↓
ProductoDAO.java escribe el producto en productos.txt
```

Y cuando el usuario pide ver la lista, el camino es al revés:

```
productos.txt es leído línea por línea
        ↓
ProductoDAO.java convierte cada línea en un objeto Producto
        ↓
ProductoServicio.java devuelve la lista al menú
        ↓
Menu.java muestra la tabla en consola
```

Cada capa solo habla con la que tiene justo al lado. El menú no sabe que
existe un archivo txt, y el DAO no sabe que existe un menú.

---

## ¿Por qué se usaron interfaces?

Se usaron dos interfaces: `IProductoDAO` e `IProductoServicio`.

Una interfaz es como un contrato: define qué métodos tiene que tener una clase,
pero no cómo los implementa. Esto sirve para dos cosas principales:

**1. Separar el "qué" del "cómo"**

El menú trabaja con `IProductoServicio`, no con `ProductoServicio` directamente.
Eso significa que si mañana quisiéramos cambiar cómo funciona el servicio,
el menú no necesitaría cambiar nada.

**2. Inyección de dependencias**

`ProductoServicio` recibe un `IProductoDAO` en su constructor, no un
`ProductoDAO` hardcodeado. Esto permite que en el futuro se pueda pasar
un DAO diferente (por ejemplo, uno que guarde en una base de datos en lugar
de un txt) sin modificar la lógica del servicio.

En resumen, las interfaces hacen el sistema más flexible y fácil de cambiar
en el futuro.

---

## ¿Por qué se eligió guardar los datos en un archivo txt?

Se eligió el archivo txt por varias razones prácticas para este proyecto:

- **Simplicidad**: no requiere instalar ni configurar nada extra como una base de datos
- **Visibilidad**: se puede abrir el archivo con cualquier editor y ver los datos directamente
- **Suficiente para el alcance**: para un inventario pequeño con operaciones básicas, un txt funciona perfectamente
- **Fácil de entender**: leer y escribir líneas de texto es un buen ejercicio para aprender manejo de archivos en Java

El formato elegido para cada línea es `id,nombre,cantidad,precio`, que es
simple de escribir y de leer. El método `leerTodos()` divide cada línea
por comas y convierte cada parte al tipo de dato correcto.

La desventaja de este enfoque es que si los datos crecen mucho o se necesitan
búsquedas más complejas, sería mejor pasarse a una base de datos. Pero para
los objetivos de este proyecto, el txt es la opción más directa.
