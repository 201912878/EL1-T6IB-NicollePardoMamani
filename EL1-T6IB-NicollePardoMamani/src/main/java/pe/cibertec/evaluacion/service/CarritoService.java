package pe.cibertec.evaluacion.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pe.cibertec.evaluacion.model.Carrito;
import pe.cibertec.evaluacion.repository.ICarritoRepository;

@Service
@Transactional
public class CarritoService {
	@Autowired
	private ICarritoRepository repository;

	public Carrito save(Carrito value) {
		return repository.save(value);
	}
	
	public Carrito find(int id) {
		List<Carrito> data = repository.listByArticulo(id);
		return data.size() > 0? data.get(0): null;
	}
	
	public boolean delete(int id) {
		Carrito item = repository.findById(id).get();
		if (item == null) {
			return false;
		}
		repository.deleteById(id);
		return true;
	}

	public List<Carrito> list() {		
		return repository.findAll();
	}
}
