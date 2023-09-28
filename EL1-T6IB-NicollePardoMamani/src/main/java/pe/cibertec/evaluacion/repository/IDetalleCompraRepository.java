package pe.cibertec.evaluacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.cibertec.evaluacion.model.DetalleCompra;

public interface IDetalleCompraRepository extends JpaRepository<DetalleCompra, Integer> {

}
