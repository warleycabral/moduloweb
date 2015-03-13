package br.com.sistxweb.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Carro")
public class Carro {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String identUnidade;
	private String placa;
	private String modelo;
	private String ano;
	private String descricao;
	
//	@OneToMany(mappedBy="carro", fetch=FetchType.LAZY)
//	private Set<Corrida> corridas;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	private Taxista motorista;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIdentUnidade() {
		return identUnidade;
	}
	public void setIdentUnidade(String identUnidade) {
		this.identUnidade = identUnidade;
	}
	public Taxista getMotorista() {
		return motorista;
	}
	public void setMotorista(Taxista motorista) {
		this.motorista = motorista;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
//	public Set<Corrida> getCorridas() {
//		return corridas;
//	}
//	public void setCorridas(Set<Corrida> corridas) {
//		this.corridas = corridas;
//	}
	
}
