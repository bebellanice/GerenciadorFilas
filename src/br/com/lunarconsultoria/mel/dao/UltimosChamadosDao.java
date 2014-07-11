package br.com.lunarconsultoria.mel.dao;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.lunarconsultoria.mel.model.Cliente;
import br.com.lunarconsultoria.mel.model.UltimosChamados;
import br.com.lunarconsultoria.mel.util.DateUtils;

@Component
public class UltimosChamadosDao extends GenericDao<UltimosChamados>{

	public UltimosChamadosDao(Session session) {
		super(Cliente.class, session);
	}

	public List<UltimosChamados> getLast(Integer number) {
		
		return session.createCriteria(UltimosChamados.class)
			.addOrder(Order.desc("id"))
			.setMaxResults(number).list();
	}

}
