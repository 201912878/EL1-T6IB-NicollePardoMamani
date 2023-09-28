package pe.cibertec.evaluacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import pe.cibertec.evaluacion.model.Articulo;
import pe.cibertec.evaluacion.model.Carrito;
import pe.cibertec.evaluacion.model.Compra;
import pe.cibertec.evaluacion.model.DetalleCompra;
import pe.cibertec.evaluacion.service.ArticuloService;
import pe.cibertec.evaluacion.service.CarritoService;
import pe.cibertec.evaluacion.service.CompraService;
import pe.cibertec.evaluacion.service.DetalleCompraService;

@Controller
public class GeneralController {
	@Autowired
	private ArticuloService serviceArticulo;

	@Autowired
	private CarritoService serviceCarrito;
	
	@Autowired
	private CompraService serviceCompra;
	
	@Autowired
	private DetalleCompraService serviceDetalleCompra;

	@RequestMapping("")
	public String list(HttpServletRequest request) {
		request.setAttribute("articulos", serviceArticulo.list());
		return "index";
	}

	@RequestMapping("agregar-carrito")
	public String agregarCarrito(HttpServletRequest request) {

		request.removeAttribute("success");

		int articulo_id = Integer.parseInt(request.getParameter("id"));
		
		Carrito c = serviceCarrito.find(articulo_id);
		if(c != null) {
			c.setCantidad(c.getCantidad() + 1);
			serviceCarrito.save(c);
			request.setAttribute("success", "El artículo ya existía en el carrito, se agregó una unidad más");
		}else {
			Articulo articulo = serviceArticulo.find(articulo_id);
			if (articulo != null) {
				c = new Carrito();
				c.setArticulo(articulo);
				c.setCantidad(1);
				c.setPrecio(articulo.getPrecio());

				serviceCarrito.save(c);
				request.setAttribute("success", "Producto agregado al carrito");
			}
		}
		request.setAttribute("articulos", serviceArticulo.list());
		return "index";
	}

	@RequestMapping("carrito")
	public String carrito(HttpServletRequest request) {
		request.setAttribute("articulosCarrito", serviceCarrito.list());
		return "carrito";
	}

	@RequestMapping("eliminar-articulo")
	public String eliminarCarrito(HttpServletRequest request) {
		request.removeAttribute("success");

		int id = Integer.parseInt(request.getParameter("id"));

		if (serviceCarrito.delete(id)) {
			request.setAttribute("success", "Producto eliminado del carrito");
		}

		request.setAttribute("articulosCarrito", serviceCarrito.list());
		return "carrito";
	}
	
	@RequestMapping("comprar")
	public String comprar(HttpServletRequest request) {
		request.removeAttribute("success");
		
		

		double subtotal = Double.parseDouble(request.getParameter("subtotal"));
		double precio_envio = Double.parseDouble(request.getParameter("precio_envio"));
		double descuento = Double.parseDouble(request.getParameter("descuento"));
		double total = Double.parseDouble(request.getParameter("total"));
		
		Compra c = new Compra();
		c.setCodigo("");
		c.setSubtotal(subtotal);
		c.setDescuento(descuento);
		c.setPrecio_envio(precio_envio);
		c.setTotal(total);
		
		c = serviceCompra.save(c);
		if(c != null) {
			List<Carrito> detalle = serviceCarrito.list();
			for (Carrito item : detalle) {
				DetalleCompra dc = new DetalleCompra();
				dc.setArticulo(item.getArticulo());
				dc.setCantidad(item.getCantidad());
				dc.setPrecio(item.getPrecio());
				dc.setCompra(c);
				
				serviceDetalleCompra.save(dc);
			}
			
			for (Carrito carrito : detalle) {
				serviceCarrito.delete((int)carrito.getId());
			}
			
			request.setAttribute("success", "Compra exitosa");
		}

		request.setAttribute("articulosCarrito", serviceCarrito.list());
		return "carrito";
	}
}
