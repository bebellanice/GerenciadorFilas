package br.com.lunarconsultoria.mel.sessionscoped;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.lunarconsultoria.mel.model.Atendimento;
import br.com.lunarconsultoria.mel.model.Fila;
import br.com.lunarconsultoria.mel.model.Usuario;

@Component
@SessionScoped
public class UsuarioWeb {
	
	private Usuario logado;
	private Fila fila;
	private Atendimento atendimento;
	private Boolean ignoraPrimeiro = false;
	
	public void login(Usuario usuario) {
		this.logado = usuario;
	}
	
	public String getNome() {
		return logado.getNome();
	}
	
	public String getLogin(){
		return logado.getLogin();
	}
	
	public boolean isLogado() {
		return logado != null;
	}

	public void logout() {
		this.logado = null;
	}

	public Fila getFila() {
		return fila;
	}

	public void setFila(Fila fila) {
		this.fila = fila;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public Boolean getIgnoraPrimeiro() {
		return ignoraPrimeiro;
	}

	public void setIgnoraPrimeiro(Boolean ignoraPrimeiro) {
		this.ignoraPrimeiro = ignoraPrimeiro;
	}


}