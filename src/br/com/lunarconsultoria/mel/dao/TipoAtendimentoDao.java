package br.com.lunarconsultoria.mel.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.lunarconsultoria.mel.model.TipoAtendimento;

@Component
public class TipoAtendimentoDao extends GenericDao<TipoAtendimento>{

	public TipoAtendimentoDao(Session session) {
		super(TipoAtendimento.class, session);
	}

}
