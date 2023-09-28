package pe.cibertec.evaluacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pe.cibertec.evaluacion.model.Compra;
import pe.cibertec.evaluacion.repository.ICompraRepository;

@Service
@Transactional
public class CompraService {
	@Autowired
	private ICompraRepository repository;

	public Compra save(Compra value) {
		return repository.save(value);
	}
}
