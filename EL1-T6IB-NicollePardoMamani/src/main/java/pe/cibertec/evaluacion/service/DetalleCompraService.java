package pe.cibertec.evaluacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pe.cibertec.evaluacion.model.DetalleCompra;
import pe.cibertec.evaluacion.repository.IDetalleCompraRepository;

@Service
@Transactional
public class DetalleCompraService {
	@Autowired
	private IDetalleCompraRepository repository;

	public DetalleCompra save(DetalleCompra value) {
		return repository.save(value);
	}
}
