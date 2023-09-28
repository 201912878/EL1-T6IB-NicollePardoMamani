package pe.cibertec.evaluacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.cibertec.evaluacion.model.Articulo;

public interface IArticuloRepository extends JpaRepository<Articulo, Integer> {

}
