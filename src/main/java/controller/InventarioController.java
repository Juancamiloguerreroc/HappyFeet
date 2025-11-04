package controller;

import repository.InventarioDAO;
import model.entities.Inventario;
import model.entities.MovimientoInventario;

import java.sql.Date;
import java.util.List;

public class InventarioController {

    private final InventarioDAO inventarioDAO;

    public InventarioController() {
        this.inventarioDAO = new InventarioDAO();
    }

    public String registrarInventario(Inventario inv) {
        if (inv.getNombreProducto() == null || inv.getNombreProducto().isEmpty()) {
            return "El nombre del producto no puede estar vacío.";
        }

        boolean resultado = inventarioDAO.agregarInventario(inv);
        return resultado ? "Producto registrado exitosamente." : "Error al registrar el producto.";
    }

    public List<Inventario> listarInventario() {
        return inventarioDAO.obtenerTodos();
    }

    public Inventario buscarPorId(int id) {
        return inventarioDAO.obtenerPorId(id);
    }

    public String actualizarInventario(Inventario inv) {
        if (inv.getId() <= 0) {
            return "ID de inventario inválido.";
        }
        boolean resultado = inventarioDAO.actualizarInventario(inv);
        return resultado ? "Inventario actualizado correctamente." : "Error al actualizar el inventario.";
    }

    public String eliminarInventario(int id) {
        boolean resultado = inventarioDAO.eliminarInventario(id);
        return resultado ? "Producto eliminado del inventario." : "No se pudo eliminar el producto.";
    }

    public String registrarMovimiento(MovimientoInventario mov) {
        if (mov.getCantidad() <= 0) {
            return "La cantidad debe ser mayor que cero.";
        }

        boolean resultado = inventarioDAO.registrarMovimiento(mov);
        return resultado ? "Movimiento registrado correctamente." : "Error al registrar el movimiento.";
    }

    public List<MovimientoInventario> obtenerMovimientosPorProducto(int productoId) {
        return inventarioDAO.obtenerMovimientosPorProducto(productoId);
    }

    public String ajustarStock(int inventarioId, int cantidad, String tipoMovimiento, String motivo, String usuario) {
        Inventario inv = inventarioDAO.obtenerPorId(inventarioId);
        if (inv == null) {
            return "No se encontró el producto en inventario.";
        }

        int stockAnterior = inv.getCantidadStock();
        int stockNuevo = stockAnterior;

        if (tipoMovimiento.equalsIgnoreCase("ENTRADA")) {
            stockNuevo += cantidad;
        } else if (tipoMovimiento.equalsIgnoreCase("SALIDA")) {
            if (cantidad > stockAnterior) {
                return "No hay suficiente stock disponible.";
            }
            stockNuevo -= cantidad;
        } else {
            return "Tipo de movimiento inválido (use ENTRADA o SALIDA).";
        }

        inv.setCantidadStock(stockNuevo);
        boolean actualizado = inventarioDAO.actualizarInventario(inv);

        if (!actualizado) {
            return "Error al actualizar stock.";
        }

        // Registrar movimiento
        MovimientoInventario mov = new MovimientoInventario();
        mov.setProductoId(inventarioId);
        mov.setTipoMovimiento(tipoMovimiento);
        mov.setCantidad(cantidad);
        mov.setStockAnterior(stockAnterior);
        mov.setStockNuevo(stockNuevo);
        mov.setMotivo(motivo);
        mov.setUsuario(usuario);
        mov.setFechaMovimiento(new Date(System.currentTimeMillis()));

        boolean movimientoRegistrado = inventarioDAO.registrarMovimiento(mov);

        if (!movimientoRegistrado) {
            return "Stock actualizado, pero no se registró el movimiento.";
        }

        return "Stock actualizado y movimiento registrado exitosamente.";
    }
    
    public Inventario buscarVacunaPorId(int id) {
        if (id <= 0) {
            System.out.println("ID inválido.");
            return null;
        }

        Inventario vacuna = inventarioDAO.obtenerVacunaPorId(id);
        if (vacuna == null) {
            System.out.println("No se encontró una vacuna con el ID proporcionado.");
        }
        return vacuna;
    }

}