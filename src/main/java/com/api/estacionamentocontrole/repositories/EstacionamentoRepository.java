package com.api.estacionamentocontrole.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.estacionamentocontrole.models.EstacionamentoModel;

@Repository
public interface EstacionamentoRepository extends JpaRepository<EstacionamentoModel, UUID> {

	boolean existsByPlacaDoCarro(String placaDoCarro);
	
	boolean existsByNumeroDaVaga(String numeroDaVaga);
	
	boolean existsByApartamentoAndBloco(String apartamento, String bloco);
}
