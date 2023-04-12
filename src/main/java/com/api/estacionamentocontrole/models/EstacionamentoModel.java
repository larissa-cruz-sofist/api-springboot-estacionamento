package com.api.estacionamentocontrole.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_ESTACIONAMENTO_CONTROLE")
public class EstacionamentoModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false, unique = true, length = 10)
	private String numeroDaVaga;
	
	@Column(nullable = false, unique = true, length = 7)
	private String placaDoCarro;
	
	@Column(nullable = false, length = 70)
	private String marcaDoCarro;
	
	@Column(nullable = false, length = 70)
	private String modeloDoCarro;
	
	@Column(nullable = false, length = 70)
	private String corDoCarro;
	
	@Column(nullable = false)
	private LocalDateTime dataDeRegistro;
	
	@Column(nullable = false, length = 130)
	private String nomeDoResponsavel;
	
	@Column(nullable = false, length = 30)
	private String apartamento;
	
	@Column(nullable = false, length = 30)
	private String bloco;
	
}
