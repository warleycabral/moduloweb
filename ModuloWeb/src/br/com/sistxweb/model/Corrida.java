package br.com.sistxweb.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.Expose;

@XmlRootElement
@Entity
@Table(name="Corrida")
public class Corrida implements Serializable{
	private static final long serialVersionUID = 4607882660672217986L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Expose
	private Long id;
	@Expose
	private String horaSolicitacao;
	@Expose
	private String horaAtendimento;
	@Expose
	private String horaInicio;
	@Expose
	private String horaFim;
	@Expose
	private Double latitudeOrigem;
	@Expose
	private Double longitudeOrigem;
	@Expose
	private String status;
	@Expose
	private String cidade;
	@Expose
	private String bairro;
	@Expose
	private String rua;
	@Expose
	private String numero;
	@Expose
	private String referencia;
	
	@Expose
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	private Cliente cliente;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@Expose
	private Taxista taxista;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getLatitudeOrigem() {
		return latitudeOrigem;
	}
	public void setLatitudeOrigem(Double latitudeOrigem) {
		this.latitudeOrigem = latitudeOrigem;
	}
	public Double getLongitudeOrigem() {
		return longitudeOrigem;
	}
	public void setLongitudeOrigem(Double longitudeOrigem) {
		this.longitudeOrigem = longitudeOrigem;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Taxista getTaxista() {
		return taxista;
	}
	public void setTaxista(Taxista taxista) {
		this.taxista = taxista;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getHoraSolicitacao() {
		return horaSolicitacao;
	}
	public void setHoraSolicitacao(String horaSolicitacao) {
		this.horaSolicitacao = horaSolicitacao;
	}
	public String getHoraAtendimento() {
		return horaAtendimento;
	}
	public void setHoraAtendimento(String horaAtendimento) {
		this.horaAtendimento = horaAtendimento;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}
	
}
