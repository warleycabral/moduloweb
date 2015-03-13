package br.com.sistxweb.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="Usuario")
@DiscriminatorValue("br.com.sistxweb.model.Usuario")
public class Usuario implements Serializable{
	private static final long serialVersionUID = -7247490325255629545L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Expose
	private Long id;
	@Expose
	private String nome;
	@Expose
	private String email;
	@Expose
	private String login;
	@Expose
	private String senha;
	@Expose
	private String cpf;
	@Expose
	private byte[] foto;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	private Cooperativa cooperativa;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	private Perfil perfil;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Cooperativa getCooperativa() {
		return cooperativa;
	}
	public void setCooperativa(Cooperativa cooperativa) {
		this.cooperativa = cooperativa;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	
}
