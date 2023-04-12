package com.api.estacionamentocontrole.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EstacionamentoDto {
	
	@NotBlank
	private String numeroDaVaga;
	
	@NotBlank
	@Size(max = 7)
	private String placaDoCarro;
	
	@NotBlank
	private String marcaDoCarro;
	
	@NotBlank
	private String modeloDoCarro;
	
	@NotBlank
	private String corDoCarro;
	
	@NotBlank
	private String nomeDoResponsavel;
	
	@NotBlank
	private String apartamento;
	
	@NotBlank
	private String bloco;

}
