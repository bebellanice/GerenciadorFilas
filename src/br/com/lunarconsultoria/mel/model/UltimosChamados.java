package br.com.lunarconsultoria.mel.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UltimosChamados implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Long id;
	private String nomeFila;
	private String senhaAtendimento;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeFila() {
		return nomeFila;
	}
	public void setNomeFila(String nomeFila) {
		this.nomeFila = nomeFila;
	}
	public String getSenhaAtendimento() {
		return senhaAtendimento;
	}
	public void setSenhaAtendimento(String senhaAtendimento) {
		this.senhaAtendimento = senhaAtendimento;
	}

}
