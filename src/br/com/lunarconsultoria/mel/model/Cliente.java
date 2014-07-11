package br.com.lunarconsultoria.mel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cliente implements Serializable, Comparable<Cliente>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Long id;
	private String nome;
	private String senhaAtendimento;
	private Boolean preferencial = false;
	private String codigoEmpresa;
	private String numeroClienteERP;
	private Boolean emAtendimento = false;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaEntrada;
	
	@ManyToMany
	private List<Fila> filas = new ArrayList();
	
	@OneToMany(mappedBy="cliente", targetEntity=Atendimento.class)
	private List<Atendimento> atendimentos = new ArrayList();

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

	public String getSenhaAtendimento() {
		return senhaAtendimento;
	}

	public void setSenhaAtendimento(String senhaAtendimento) {
		this.senhaAtendimento = senhaAtendimento;
	}

	public Boolean getPreferencial() {
		return preferencial;
	}

	public void setPreferencial(Boolean preferencial) {
		this.preferencial = preferencial;
	}

	public Date getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public List<Fila> getFilas() {
		return filas;
	}

	public void setFilas(List<Fila> filas) {
		this.filas = filas;
	}

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	@Override
	public int compareTo(Cliente arg0) {
		return this.senhaAtendimento.compareTo(arg0.getSenhaAtendimento());
	}

	public Boolean getEmAtendimento() {
		return emAtendimento;
	}

	public void setEmAtendimento(Boolean emAtendimento) {
		this.emAtendimento = emAtendimento;
	}

	public String getNumeroClienteERP() {
		return numeroClienteERP;
	}

	public void setNumeroClienteERP(String numeroClienteERP) {
		this.numeroClienteERP = numeroClienteERP;
	}
	
	
	
}
