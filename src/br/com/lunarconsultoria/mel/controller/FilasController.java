package br.com.lunarconsultoria.mel.controller;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.lunarconsultoria.mel.annotation.Restrito;
import br.com.lunarconsultoria.mel.dao.AtendimentoDao;
import br.com.lunarconsultoria.mel.dao.ClienteDao;
import br.com.lunarconsultoria.mel.dao.FilaDao;
import br.com.lunarconsultoria.mel.dao.TipoAtendimentoDao;
import br.com.lunarconsultoria.mel.dao.UltimosChamadosDao;
import br.com.lunarconsultoria.mel.model.Atendimento;
import br.com.lunarconsultoria.mel.model.Cliente;
import br.com.lunarconsultoria.mel.model.Fila;
import br.com.lunarconsultoria.mel.model.UltimosChamados;
import br.com.lunarconsultoria.mel.sessionscoped.UsuarioWeb;
import br.com.lunarconsultoria.mel.websocket.PainelWebSocketServlet;

@Resource
public class FilasController {

	private FilaDao dao;
	private ClienteDao clienteDao;
	private TipoAtendimentoDao tipoAtendimentoDao;
	private AtendimentoDao atendimentoDao;
	private UltimosChamadosDao ultimosChamadosDao;
	private Result result;
	private final UsuarioWeb usuarioWeb;
	
	
	public FilasController(FilaDao dao, ClienteDao clienteDao,
			TipoAtendimentoDao tipoAtendimentoDao,
			AtendimentoDao atendimentoDao, UltimosChamadosDao ultimosChamadosDao,
			Result result, UsuarioWeb usuarioWeb) {
		super();
		this.dao = dao;
		this.clienteDao = clienteDao;
		this.tipoAtendimentoDao = tipoAtendimentoDao;
		this.atendimentoDao = atendimentoDao;
		this.ultimosChamadosDao = ultimosChamadosDao;
		this.result = result;
		this.usuarioWeb = usuarioWeb;
	}

	@Get
	@Restrito
	public void adicionaFila(){
		result.include("tipoAtendimentoList", tipoAtendimentoDao.list());
		result.include("filaList", dao.list());
	}
	
	@Post
	@Restrito
	public void adicionaFila(Fila fila){
		dao.save(fila);
		result.redirectTo(FilasController.class).adicionaFila();
	}
	
	@Get
	@Restrito
	public void selecionaFila(){
		result.include("filaList", dao.list());
	}
	
	@Post
	@Restrito
	public void selecionaFila(Fila fila){
		Fila selecionado = dao.get(fila.getId());
		this.usuarioWeb.setFila(selecionado);
		result.include("selected", selecionado.getNome());
		result.redirectTo(FilasController.class).chamaProximo();
	}

	@Get
	@Restrito
	public void chamaProximo() {
		result.include("selected", this.usuarioWeb.getFila().getNome());
	}
	
//	@Post
//	@Restrito
//	public void chamaProximo(Boolean atendimentoRealizado, Boolean clienteAusente){
//		if(atendimentoRealizado == null){
//			atendimentoRealizado = false;
//		}
//		if(clienteAusente == null){
//			clienteAusente = false;
//		}
//		Atendimento atendimento = this.usuarioWeb.getAtendimento();
//		Cliente proximoCliente = null;
//		if(atendimento !=null){
//			Cliente clienteEmAtendimento = this.usuarioWeb.getAtendimento().getCliente();
//			clienteEmAtendimento.setEmAtendimento(false);
//			this.clienteDao.save(clienteEmAtendimento);
//			if(atendimentoRealizado){
//				persisteAtendimento(atendimento);
//				Cliente cliente = atendimento.getCliente();
//				List<Fila> filas = dao.getByTipoAtendimento(atendimento.getTipoAtendimento());
//				retiraClienteAtendidoDasFilas(cliente, filas);
//				//se o primeiro cliente já foi ignorado uma vez, não ignora-o novamente
//				if(this.usuarioWeb.getIgnoraPrimeiro()){
//					this.usuarioWeb.setIgnoraPrimeiro(false);
//				}
//			}else{
//				this.usuarioWeb.setAtendimento(null);
//				if(clienteAusente){
//					List<Fila> filas = dao.list();
//					retiraClienteAtendidoDasFilas(atendimento.getCliente(), filas);
//					//se o primeiro cliente já foi ignorado uma vez, não ignora-o novamente
//					if(this.usuarioWeb.getIgnoraPrimeiro()){
//						this.usuarioWeb.setIgnoraPrimeiro(false);
//					}
//				}else{
//					//se o primeiro cliente já foi ignorado uma vez, não ignora-o novamente
//					if(!this.usuarioWeb.getIgnoraPrimeiro()){
//						this.usuarioWeb.setIgnoraPrimeiro(true);
//					}else{
//						this.usuarioWeb.setIgnoraPrimeiro(false);
//					}
//				}
//			}
//			
//		}else{
//			this.usuarioWeb.setIgnoraPrimeiro(false);
//		}
//		Fila fila = this.usuarioWeb.getFila();
//		proximoCliente = selecionaProximoDaFila(fila, this.usuarioWeb.getIgnoraPrimeiro());
//		if(proximoCliente != null){
//			criaNovoAtendimento(proximoCliente, fila);
//			exibeProximoClienteNoPainel(proximoCliente, fila);
//			result.redirectTo(FilasController.class).chamaProximoResult(proximoCliente);
//			return;
//		}else{
//			result.include("message", "Não existem mais clientes nessa fila neste momento");
//		}
//		result.redirectTo(FilasController.class).chamaProximo();
//	
//	}
	
	@Get
	@Restrito
	public void finalizaAtendimento(){
		Atendimento atendimento = this.usuarioWeb.getAtendimento();
		Cliente clienteEmAtendimento = this.usuarioWeb.getAtendimento().getCliente();
		clienteEmAtendimento.setEmAtendimento(false);
		this.clienteDao.save(clienteEmAtendimento);
		persisteAtendimento(atendimento);
		Cliente cliente = atendimento.getCliente();
		List<Fila> filas = dao.getByTipoAtendimento(atendimento.getTipoAtendimento());
		retiraClienteAtendidoDasFilas(cliente, filas);
		this.usuarioWeb.setAtendimento(null);
		this.usuarioWeb.setIgnoraPrimeiro(false);
		result.redirectTo(FilasController.class).chamaProximo();
	}
	
	@Get
	@Restrito
	public void clienteAusente(){
		Atendimento atendimento = this.usuarioWeb.getAtendimento();
		Cliente clienteEmAtendimento = this.usuarioWeb.getAtendimento().getCliente();
		clienteEmAtendimento.setEmAtendimento(false);
		this.clienteDao.save(clienteEmAtendimento);
		this.usuarioWeb.setAtendimento(null);
		List<Fila> filas = dao.list();
		retiraClienteAtendidoDasFilas(atendimento.getCliente(), filas);
		this.usuarioWeb.setIgnoraPrimeiro(false);
		result.redirectTo(FilasController.class).chamaProximo();
	}
	
	@Post
	@Restrito
	public void chamaProximoPost(){
		Atendimento atendimento = this.usuarioWeb.getAtendimento();
		Cliente proximoCliente = null;
		if(atendimento !=null){
			Cliente clienteEmAtendimento = this.usuarioWeb.getAtendimento().getCliente();
			clienteEmAtendimento.setEmAtendimento(false);
			this.clienteDao.save(clienteEmAtendimento);
			this.usuarioWeb.setAtendimento(null);
			//se o primeiro cliente já foi ignorado uma vez, não ignora-o novamente
			if(!this.usuarioWeb.getIgnoraPrimeiro()){
				this.usuarioWeb.setIgnoraPrimeiro(true);
			}else{
				this.usuarioWeb.setIgnoraPrimeiro(false);
			}
		}else{
			this.usuarioWeb.setIgnoraPrimeiro(false);
		}
		Fila fila = this.usuarioWeb.getFila();
		proximoCliente = selecionaProximoDaFila(fila, this.usuarioWeb.getIgnoraPrimeiro());
		if(proximoCliente != null){
			criaNovoAtendimento(proximoCliente, fila);
			exibeProximoClienteNoPainel(proximoCliente, fila);
			result.redirectTo(FilasController.class).chamaProximoResult(proximoCliente);
			return;
		}else{
			result.include("message", "Não existem mais clientes nessa fila neste momento");
		}
		result.redirectTo(FilasController.class).chamaProximo();
	
	}
	
	
	@Get
	@Restrito
	public void chamaProximoResult(Cliente proximoCliente){
		
		result.include("nome", proximoCliente.getNome());
		result.include("senha", proximoCliente.getSenhaAtendimento());
		result.include("codigoCliente", proximoCliente.getNumeroClienteERP());
		result.include("codigoEmpresa", proximoCliente.getCodigoEmpresa());
		result.include("selected", this.usuarioWeb.getFila().getNome());
		
	}

	//Esse método cria um novo atendimento e altera o cliente no banco de dados (em atendimento = 1)
	private void criaNovoAtendimento(Cliente proximoCliente, Fila fila) {
		Atendimento atendimento = new Atendimento();
		atendimento.setAberto(true);
		atendimento.setEntrada(Calendar.getInstance().getTime());
		atendimento.setCliente(proximoCliente);
		atendimento.setTipoAtendimento(fila.getTipoAtendimento());
		this.usuarioWeb.setAtendimento(atendimento);
		proximoCliente.setEmAtendimento(true);
		List<Fila> filas = dao.getByTipoAtendimento(this.usuarioWeb.getFila().getTipoAtendimento());
		this.clienteDao.save(proximoCliente);
	}

	private void retiraClienteAtendidoDasFilas(Cliente cliente, List<Fila> filas) {
		for (Fila fila : filas) {
			for (Cliente cliente2 : fila.getClientes()) {
				if(cliente2.getId().equals(cliente.getId())){
					fila.getClientes().remove(cliente2);
					dao.removeClienteDaFila(fila, cliente2);
					break;
				}
			}
		}
		
	}

	private void persisteAtendimento(Atendimento atendimento) {
		atendimento.setSaida(Calendar.getInstance().getTime());
		atendimento.setRealizado(true);
		atendimento.setAberto(false);
		this.atendimentoDao.save(atendimento);
	}
	
	private void exibeProximoClienteNoPainel(Cliente proximoCliente, Fila fila) {
		
		List<UltimosChamados> ultimosChamados = this.ultimosChamadosDao.getLast(4);
		String message = fila.getNome()+"-"+proximoCliente.getSenhaAtendimento()+"-"+proximoCliente.getNome();
		for (UltimosChamados ultimoChamado : ultimosChamados) {
			message = message +"-"+ultimoChamado.getSenhaAtendimento()+"-"+ultimoChamado.getNomeFila();
		}
		UltimosChamados chamadoAtual = new UltimosChamados();
		chamadoAtual.setNomeFila(fila.getNome());
		chamadoAtual.setSenhaAtendimento(proximoCliente.getSenhaAtendimento());
		this.ultimosChamadosDao.save(chamadoAtual);
		PainelWebSocketServlet.broadcast(message);
	}
	
	@Get
	public void painel(){
		
	}

	@Get
	public void painelSemVideo(){
		
	}
	private Cliente selecionaProximoDaFila(Fila fila, Boolean ignoraPrimeiro){
		fila = dao.get(fila.getId());
		List<Cliente> clientes = fila.getClientes();
		Collections.sort(clientes);
		Cliente proximo = null;
		if(numeroDeClientesDisponiveis(clientes)<=1){
			ignoraPrimeiro = false;
		}
		for (Cliente cliente : clientes) {
			if(cliente.getPreferencial()){
				if(!cliente.getEmAtendimento()){
					if(ignoraPrimeiro){
						ignoraPrimeiro = false;
					}else{
						return cliente;
					}
				}
			}
		}
		if(proximo == null){
			for (Cliente cliente : clientes) {
				if(!cliente.getEmAtendimento()) {
					if(ignoraPrimeiro){
						ignoraPrimeiro = false;
					}else{
						return cliente;
					}
				}
			}
		}
		
		return null;
	}

	private int numeroDeClientesDisponiveis(List<Cliente> clientes) {
		int count = 0;
		for (Cliente cliente : clientes) {
			if(!cliente.getEmAtendimento())
				count++;
		}
		return count;

	}
	
}
