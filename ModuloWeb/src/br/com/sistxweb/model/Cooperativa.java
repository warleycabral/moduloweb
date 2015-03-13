package br.com.sistxweb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="Cooperativa")
public class Cooperativa {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nome;
	private String razaoSocial;
	private String cnpj;
	private String endereco;
	private String descricao;
	private String situacao;
	
//	@OneToMany(mappedBy="cooperativa", fetch=FetchType.LAZY)
//	private Set<Taxista> taxistas;
//	
//	@OneToMany(mappedBy="cooperativa", fetch=FetchType.LAZY)
//	private Set<Usuario> usuarios;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
//	public Set<Taxista> getTaxistas() {
//		return taxistas;
//	}
//	public void setTaxistas(Set<Taxista> taxistas) {
//		this.taxistas = taxistas;
//	}
//	public Set<Usuario> getUsuarios() {
//		return usuarios;
//	}
//	public void setUsuarios(Set<Usuario> usuarios) {
//		this.usuarios = usuarios;
//	}
}
