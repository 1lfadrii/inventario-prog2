package negocio;

import datos.IProductoDAO;
import entidades.Producto;
import java.util.List;

public class ProductoServicio implements IProductoServicio {

    private IProductoDAO productoDAO;

    public ProductoServicio(IProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    @Override
    public void agregar(Producto producto) {
        if (producto.getId() <= 0)
            throw new IllegalArgumentException("El ID debe ser mayor a 0.");
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        if (producto.getCantidad() < 0)
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        if (producto.getPrecio() <= 0)
            throw new IllegalArgumentException("El precio debe ser mayor a 0.");
        if (productoDAO.buscarPorId(producto.getId()) != null)
            throw new IllegalArgumentException("Ya existe un producto con el ID " + producto.getId() + ".");

        productoDAO.agregar(producto);
    }

    @Override
    public Producto buscarPorId(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("El ID debe ser mayor a 0.");

        Producto producto = productoDAO.buscarPorId(id);
        if (producto == null)
            throw new IllegalArgumentException("Producto no encontrado ):");

        return producto;
    }

    @Override
    public List<Producto> listarTodos() {
        return productoDAO.listarTodos();
    }

    @Override
    public void actualizar(Producto producto) {
        if (producto.getId() <= 0)
            throw new IllegalArgumentException("El ID debe ser mayor a 0.");
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        if (producto.getCantidad() < 0)
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        if (producto.getPrecio() <= 0)
            throw new IllegalArgumentException("El precio debe ser mayor a 0.");
        if (productoDAO.buscarPorId(producto.getId()) == null)
            throw new IllegalArgumentException("No existe un producto con el ID " + producto.getId() + ".");

        productoDAO.actualizar(producto);
    }

    @Override
    public void eliminar(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("El ID debe ser mayor a 0.");
        if (productoDAO.buscarPorId(id) == null)
            throw new IllegalArgumentException("No existe un producto con el ID " + id + ".");

        productoDAO.eliminar(id);
    }
}
