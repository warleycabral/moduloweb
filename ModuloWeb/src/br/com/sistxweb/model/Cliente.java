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
@DiscriminatorValue("br.com.sistxweb.model.Cliente")
public class Cliente extends Usuario implements Serializable{
	private static final long serialVersionUID = 6036101325640316559L;
	@Expose
	private String genero;
	@Expose
	private Date dataNascimento;
	
	@OneToMany(mappedBy="cliente", fetch=FetchType.LAZY)
	private Set<Corrida> corridas;
	
	@OneToMany(mappedBy="cliente", fetch=FetchType.LAZY)
	private Set<Endereco> enderecos;
	
	@OneToMany(mappedBy="cliente", fetch=FetchType.LAZY)
	private Set<Telefone> telefones;
	
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Set<Corrida> getCorridas() {
		return corridas;
	}
	public void setCorridas(Set<Corrida> corridas) {
		this.corridas = corridas;
	}
	public Set<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	public Set<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}
}
