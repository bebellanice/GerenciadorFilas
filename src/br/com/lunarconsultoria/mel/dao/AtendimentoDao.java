package br.com.lunarconsultoria.mel.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.lunarconsultoria.mel.model.Atendimento;

@Component
public class AtendimentoDao extends GenericDao<Atendimento>{

	public AtendimentoDao(Session session) {
		super(Atendimento.class, session);
	}

}
