package br.com.lunarconsultoria.mel.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Distinct;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.lunarconsultoria.mel.model.Cliente;
import br.com.lunarconsultoria.mel.model.Fila;
import br.com.lunarconsultoria.mel.model.TipoAtendimento;

@Component
public class FilaDao extends GenericDao<Fila>{


	
	public FilaDao(Session session) {
		super(Fila.class, session);
	}
	
	public List<Fila> getByTipoAtendimento(TipoAtendimento tipoAtendimento){
		List list = session.createCriteria(Fila.class).add(Restrictions.eq("tipoAtendimento.id", tipoAtendimento.getId())).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
		
	}

	public void removeClienteDaFila(Fila fila, Cliente cliente) {
		
		Transaction tx = session.beginTransaction();
		SQLQuery query = session.createSQLQuery("DELETE FROM gerenciador_filas_mel.Cliente_Fila WHERE clientes_id = '"+cliente.getId()+"' and filas_id='"+fila.getId()+"'");
		query.executeUpdate();
		tx.commit();
		session.flush();
		session.clear();
	}

	@Override
	public List<Fila> list() {
		return session.createCriteria(Fila.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}
	
}
