package com.api.estacionamentocontrole.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.estacionamentocontrole.models.EstacionamentoModel;
import com.api.estacionamentocontrole.repositories.EstacionamentoRepository;

import jakarta.transaction.Transactional;

@Service
public class EstacionamentoService {

	@Autowired
	EstacionamentoRepository estacionamentoRepository;

	@Transactional
	public EstacionamentoModel save(EstacionamentoModel estacionamentoModel) {
		return estacionamentoRepository.save(estacionamentoModel);
	}

	public boolean existsByPlacaDoCarro(String placaDoCarro) {
		return estacionamentoRepository.existsByPlacaDoCarro(placaDoCarro);
	}

	public boolean existsByNumeroDaVaga(String numeroDaVaga) {
		return estacionamentoRepository.existsByNumeroDaVaga(numeroDaVaga);
	}

	public boolean existsByApartamentoAndBloco(String apartamento, String bloco) {
		return estacionamentoRepository.existsByApartamentoAndBloco(apartamento, bloco);
	}

	public List<EstacionamentoModel> findAll() {
		return estacionamentoRepository.findAll();
	}

	public Optional<EstacionamentoModel> findById(UUID id) {
		return estacionamentoRepository.findById(id);
	}

	@Transactional
	public void delete(EstacionamentoModel estacionamentoModel) {
		estacionamentoRepository.delete(estacionamentoModel);
		
	}

}
