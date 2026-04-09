package negocio;

import entidades.Producto;
import java.util.List;

public interface IProductoServicio {

    void agregar(Producto producto);

    Producto buscarPorId(int id);

    List<Producto> listarTodos();

    void actualizar(Producto producto);

    void eliminar(int id);
}
