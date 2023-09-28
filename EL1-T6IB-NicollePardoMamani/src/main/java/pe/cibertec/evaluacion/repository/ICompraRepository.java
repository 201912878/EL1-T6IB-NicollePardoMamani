package pe.cibertec.evaluacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.cibertec.evaluacion.model.Compra;

public interface ICompraRepository extends JpaRepository<Compra, Integer> {

}
