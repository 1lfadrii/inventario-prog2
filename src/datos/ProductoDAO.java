package datos;

import entidades.Producto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO implements IProductoDAO {

    private static final String RUTA_ARCHIVO = "datos/productos.txt";

    private List<Producto> leerTodos() {
        List<Producto> productos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) {
                    continue;
                }
                String[] partes = linea.split(",");
                int id = Integer.parseInt(partes[0].trim());
                String nombre = partes[1].trim();
                int cantidad = Integer.parseInt(partes[2].trim());
                double precio = Double.parseDouble(partes[3].trim());
                productos.add(new Producto(id, nombre, cantidad, precio));
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return productos;
    }

    private void guardarTodos(List<Producto> productos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))) {
            for (Producto p : productos) {
                bw.write(p.getId() + "," + p.getNombre() + "," + p.getCantidad() + "," + p.getPrecio());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    @Override
    public void agregar(Producto producto) {
        List<Producto> productos = leerTodos();
        productos.add(producto);
        guardarTodos(productos);
    }

    @Override
    public Producto buscarPorId(int id) {
        List<Producto> productos = leerTodos();
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        System.out.println("Atencion: no se encontro un producto con id " + id);
        return null;
    }

    @Override
    public List<Producto> listarTodos() {
        return leerTodos();
    }

    @Override
    public void actualizar(Producto producto) {
        List<Producto> productos = leerTodos();
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == producto.getId()) {
                productos.set(i, producto);
                guardarTodos(productos);
                return;
            }
        }
        System.out.println("Atencion: no se encontro un producto con id " + producto.getId());
    }

    @Override
    public void eliminar(int id) {
        List<Producto> productos = leerTodos();
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == id) {
                productos.remove(i);
                guardarTodos(productos);
                return;
            }
        }
        System.out.println("Atencion!: no se encontro un producto con id " + id + "):");
    }
}
