package pe.cibertec.evaluacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.cibertec.evaluacion.model.Carrito;



public interface ICarritoRepository extends JpaRepository<Carrito, Integer>  {
	
	
	@Query(value = "SELECT c FROM Carrito c WHERE c.articulo.id = :articulo_id")
	public List<Carrito> listByArticulo(@Param("articulo_id") long articulo_id);
}
