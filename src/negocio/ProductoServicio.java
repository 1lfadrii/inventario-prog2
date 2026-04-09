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
        // TODO: implementar
    }

    @Override
    public Producto buscarPorId(int id) {
        // TODO: implementar
        return null;
    }

    @Override
    public List<Producto> listarTodos() {
        // TODO: implementar
        return null;
    }

    @Override
    public void actualizar(Producto producto) {
        // TODO: implementar
    }

    @Override
    public void eliminar(int id) {
        // TODO: implementar
    }
}
