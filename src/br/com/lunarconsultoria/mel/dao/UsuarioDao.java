package br.com.lunarconsultoria.mel.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.lunarconsultoria.mel.model.Usuario;

@Component
public class UsuarioDao extends GenericDao<Usuario> {

	public UsuarioDao(Session session) {
		super(Usuario.class, session);
	}

	public boolean existeUsuario(Usuario usuario) {
		Usuario encontrado = (Usuario) session.createCriteria(Usuario.class)
			      .add(Restrictions.eq("login", usuario.getLogin()))
			      .uniqueResult();
	    return encontrado != null;
	}
	
	public Usuario get(String login) {
		Usuario encontrado = (Usuario) session.createCriteria(Usuario.class)
			      .add(Restrictions.eq("login", login))
			      .uniqueResult();
	    return encontrado;
	}
	
	public Usuario carrega(Usuario usuario) {
	    Usuario object = (Usuario) session.createCriteria(Usuario.class)
	      .add(Restrictions.eq("login", usuario.getLogin()))
	      .add(Restrictions.eq("password", usuario.getPassword()))
	      .uniqueResult();
	    return object;
	  }

	
}
