package br.com.sistxweb.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.Expose;

@XmlRootElement
@Entity
@DiscriminatorValue("br.com.sistxweb.model.Taxista")
public class Taxista extends Usuario implements Serializable{
	private static final long serialVersionUID = 4036358877295440771L;
	@Expose
	private String cnh;
	@Expose
	private Date dataNascimento;
	@Expose
	private String telefone;
	@Expose
	private String endereco;
	
	@OneToMany(mappedBy="motorista", fetch=FetchType.LAZY)
	private Set<Carro> carros;
	
	@OneToMany(mappedBy="taxista", fetch=FetchType.LAZY)
	private Set<Corrida> corridas;
	
	public String getCnh() {
		return cnh;
	}
	public void setCnh(String cnh) {
		this.cnh = cnh;
	}
	public Set<Carro> getCarros() {
		return carros;
	}
	public void setCarros(Set<Carro> carros) {
		this.carros = carros;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Set<Corrida> getCorridas() {
		return corridas;
	}
	public void setCorridas(Set<Corrida> corridas) {
		this.corridas = corridas;
	}
}
