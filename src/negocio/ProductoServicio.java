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
        productoDAO.agregar(producto);
    }

    @Override
    public Producto buscarPorId(int id) {
        return productoDAO.buscarPorId(id);
    }

    @Override
    public List<Producto> listarTodos() {
        return productoDAO.listarTodos();
    }

    @Override
    public void actualizar(Producto producto) {
        productoDAO.actualizar(producto);
    }

    @Override
    public void eliminar(int id) {
        productoDAO.eliminar(id);
    }
}
