package pe.cibertec.evaluacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jakarta.transaction.Transactional;
import pe.cibertec.evaluacion.model.Articulo;
import pe.cibertec.evaluacion.repository.IArticuloRepository;

@Service
@Transactional
public class ArticuloService {
	@Autowired
	private IArticuloRepository repository;

	public Articulo save(Articulo value) {
		return repository.save(value);
	}
	
	public Articulo find(int id) {
		return repository.findById(id).get();
	}

	public List<Articulo> list() {		
		return repository.findAll();
	}
}
