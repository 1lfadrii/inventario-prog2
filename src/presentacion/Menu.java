package presentacion;

import entidades.Producto;
import negocio.IProductoServicio;
import negocio.ProductoServicio;
import datos.ProductoDAO;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private IProductoServicio servicio = new ProductoServicio(new ProductoDAO());
    private Scanner scanner = new Scanner(System.in);

    public void mostrar() {
        int opcion;

        do {
            System.out.println();
            System.out.println("╔══════════════════════════════╗");
            System.out.println("║     SISTEMA DE INVENTARIO    ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║  1. Registrar producto       ║");
            System.out.println("║  2. Listar productos         ║");
            System.out.println("║  3. Buscar producto por ID   ║");
            System.out.println("║  4. Actualizar producto      ║");
            System.out.println("║  5. Eliminar producto        ║");
            System.out.println("║  6. Salir                    ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("  Elige una opcion: ");
            opcion = leerInt("INGRESE UN NÚMERO DE OPCIÓN");

            switch (opcion) {
                case 1:
                    registrarProducto();
                    break;
                case 2:
                    listarProductos();
                    break;
                case 3:
                    buscarProducto();
                    break;
                case 4:
                    actualizarProducto();
                    break;
                case 5:
                    eliminarProducto();
                    break;
                case 6:
                    System.out.println("\n  Hasta luego!");
                    break;
                default:
                    System.out.println("\n  Opcion invalida, intenta de nuevo.");
            }

        } while (opcion != 6);
    }

    private void registrarProducto() {
        System.out.println("\n--- Registrar producto ---");
        System.out.print("ID: ");
        int id = leerInt("INGRESE EL NÚMERO DE ID");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Cantidad: ");
        int cantidad = leerInt("INGRESE UN NÚMERO DE CANTIDAD");
        System.out.print("Precio: ");
        double precio = leerDouble("INGRESE UN NÚMERO DE PRECIO");

        Producto producto = new Producto(id, nombre, cantidad, precio);
        servicio.agregar(producto);
        System.out.println("  Producto registrado correctamente.");
    }

    private void listarProductos() {
        System.out.println("\n--- Lista de productos ---");
        List<Producto> productos = servicio.listarTodos();

        if (productos.isEmpty()) {
            System.out.println("  No hay productos registrados.");
        } else {
            System.out.println(String.format("  %-5s %-20s %-10s %-10s", "ID", "Nombre", "Cantidad", "Precio"));
            System.out.println("  " + "-".repeat(47));
            for (Producto p : productos) {
                System.out.println(String.format("  %-5d %-20s %-10d %-10.2f",
                        p.getId(), p.getNombre(), p.getCantidad(), p.getPrecio()));
            }
        }
    }

    private void buscarProducto() {
        System.out.println("\n--- Buscar producto ---");
        System.out.print("ID a buscar: ");
        int id = leerInt("INGRESE EL NÚMERO DE ID");

        Producto p = servicio.buscarPorId(id);
        if (p != null) {
            System.out.println(String.format("  %-5s %-20s %-10s %-10s", "ID", "Nombre", "Cantidad", "Precio"));
            System.out.println("  " + "-".repeat(47));
            System.out.println(String.format("  %-5d %-20s %-10d %-10.2f",
                    p.getId(), p.getNombre(), p.getCantidad(), p.getPrecio()));
        } else {
            System.out.println("  Producto no encontrado ):");
        }
    }

    private void actualizarProducto() {
        System.out.println("\n--- Actualizar producto ---");
        System.out.print("ID del producto a actualizar: ");
        int id = leerInt("INGRESE EL NÚMERO DE ID");
        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nueva cantidad: ");
        int cantidad = leerInt("INGRESE UN NÚMERO DE CANTIDAD");
        System.out.print("Nuevo precio: ");
        double precio = leerDouble("INGRESE UN NÚMERO DE PRECIO");

        Producto producto = new Producto(id, nombre, cantidad, precio);
        servicio.actualizar(producto);
        System.out.println("  Producto actualizado correctamente.");
    }

    private void eliminarProducto() {
        System.out.println("\n--- Eliminar producto ---");
        System.out.print("ID del producto a eliminar: ");
        int id = leerInt("  INGRESE EL NÚMERO DE ID");

        servicio.eliminar(id);
        System.out.println("  Producto eliminado correctamente.");
    }

    private int leerInt(String mensajeError) {
        while (true) {
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("  " + mensajeError);
                System.out.print("  Intenta de nuevo: ");
            }
        }
    }

    private double leerDouble(String mensajeError) {
        while (true) {
            try {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("  " + mensajeError);
                System.out.print("  Intenta de nuevo: ");
            }
        }
    }
}
