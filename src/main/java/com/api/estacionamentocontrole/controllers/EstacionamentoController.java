package com.api.estacionamentocontrole.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.estacionamentocontrole.dtos.EstacionamentoDto;
import com.api.estacionamentocontrole.models.EstacionamentoModel;
import com.api.estacionamentocontrole.services.EstacionamentoService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/estacionamento")
public class EstacionamentoController {
	
	@Autowired
	EstacionamentoService estacionamentoService;
	
	@PostMapping
	public ResponseEntity<Object> saveEstacionamentoControle(@RequestBody @Valid EstacionamentoDto estacionamentoDto) {
		if(estacionamentoService.existsByPlacaDoCarro(estacionamentoDto.getPlacaDoCarro())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("A placa do carro já está em uso");
		}
		if(estacionamentoService.existsByNumeroDaVaga(estacionamentoDto.getNumeroDaVaga())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("O número desta vaga já está em uso");
		}
		if(estacionamentoService.existsByApartamentoAndBloco(estacionamentoDto.getApartamento(), estacionamentoDto.getBloco())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("A vaga já está registrada para esse apartamento/bloco");
		}
		var estacionamentoModel = new EstacionamentoModel();
		BeanUtils.copyProperties(estacionamentoDto, estacionamentoModel);
		estacionamentoModel.setDataDeRegistro(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(estacionamentoService.save(estacionamentoModel));
	}
	
	@GetMapping
	public ResponseEntity<List<EstacionamentoModel>> getAllRegistros() {
		return ResponseEntity.status(HttpStatus.OK).body(estacionamentoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneRegistro(@PathVariable(value = "id") UUID id) {
		Optional<EstacionamentoModel> estacionamentoModelOptional = estacionamentoService.findById(id);
		if(!estacionamentoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(estacionamentoModelOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteOneRegistro(@PathVariable(value = "id") UUID id) {
		Optional<EstacionamentoModel> estacionamentoModelOptional = estacionamentoService.findById(id);
		if(!estacionamentoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
		}
		estacionamentoService.delete(estacionamentoModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Registro deletado com sucesso");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateOneRegistro(@PathVariable(value = "id") UUID id, @RequestBody @Valid EstacionamentoDto estacionamentoDto) {
		Optional<EstacionamentoModel> estacionamentoModelOptional = estacionamentoService.findById(id);
		if(!estacionamentoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
		}
		var estacionamentoModel = estacionamentoModelOptional.get();
		estacionamentoModel.setNumeroDaVaga(estacionamentoDto.getNumeroDaVaga());
		estacionamentoModel.setPlacaDoCarro(estacionamentoDto.getPlacaDoCarro());
		estacionamentoModel.setMarcaDoCarro(estacionamentoDto.getMarcaDoCarro());
		estacionamentoModel.setModeloDoCarro(estacionamentoDto.getModeloDoCarro());
		estacionamentoModel.setCorDoCarro(estacionamentoDto.getCorDoCarro());
		estacionamentoModel.setNomeDoResponsavel(estacionamentoDto.getNomeDoResponsavel());
		estacionamentoModel.setApartamento(estacionamentoDto.getApartamento());
		estacionamentoModel.setBloco(estacionamentoDto.getBloco());
		
		return ResponseEntity.status(HttpStatus.OK).body(estacionamentoService.save(estacionamentoModel));
		
		///var estacionamentoModel = new EstacionamentoModel();
		///BeanUtils.copyProperties(estacionamentoDto, estacionamentoModel);
		///estacionamentoModel.setId(estacionamentoModelOptional.get().getId());
		///estacionamentoModel.setDataDeRegistro(estacionamentoModelOptional.get().getDataDeRegistro());
		///return ResponseEntity.status(HttpStatus.CREATED).body(estacionamentoService.save(estacionamentoModel));

	}
}
