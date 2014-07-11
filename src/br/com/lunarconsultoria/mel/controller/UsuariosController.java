package br.com.lunarconsultoria.mel.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.lunarconsultoria.mel.annotation.Restrito;
import br.com.lunarconsultoria.mel.dao.UsuarioDao;
import br.com.lunarconsultoria.mel.model.Usuario;
import br.com.lunarconsultoria.mel.sessionscoped.UsuarioWeb;

@Resource
public class UsuariosController {
	
	private final UsuarioDao dao;
	private final Result result;
	private final UsuarioWeb usuarioWeb;
	private final Validator validator;

	public UsuariosController(UsuarioDao dao, Result result, UsuarioWeb usuarioWeb, Validator validator) {
		this.dao = dao;
		this.result = result;
		this.validator = validator;
		this.usuarioWeb = usuarioWeb;
	}

	//@Restrito
	@Get
	public void adicionaUsuario(){
		
	}
	
	@Post
	@Restrito
	public void adicionaUsuario(Usuario usuario) {
//		if (dao.existeUsuario(usuario)) {
//			validator.add(new ValidationMessage("Login já existe", "usuario.login"));
//		}
//		validator.onErrorUsePageOf(UsuariosController.class).adicionaUsuario();
		Usuario usuarioManaged = dao.get(usuario.getLogin());
		usuarioManaged.setPassword(usuario.getPassword());
		dao.save(usuarioManaged);
		result.redirectTo(UsuariosController.class).adicionaUsuario();
	}
	
	@Get
	public void login(){
		 
	}
	
	@Post("/login")
	public void login(Usuario usuario) {
		Usuario carregado = dao.carrega(usuario);
		if (carregado == null) {
			validator.add(
					new ValidationMessage("Login e/ou senha inválidos",
							"usuario.login"));
		}
		validator.onErrorUsePageOf(UsuariosController.class)
		.login();
		
		usuarioWeb.login(carregado);
		
		result.redirectTo(IndexController.class).index();
	}
	
	@Path("/logout")
	@Restrito
	public void logout() {
		usuarioWeb.logout();
		result.redirectTo(UsuariosController.class).login();
	}

}
