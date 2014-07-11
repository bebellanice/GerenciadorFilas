package br.com.lunarconsultoria.mel.dao;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.lunarconsultoria.mel.model.Cliente;
import br.com.lunarconsultoria.mel.util.DateUtils;

@Component
public class ClienteDao extends GenericDao<Cliente>{

	public ClienteDao(Session session) {
		super(Cliente.class, session);
	}

	public List<Cliente> getByNameAndDate(String nome, String data) {
		
		String[] dateArray = data.split("/");
		Date date = new GregorianCalendar(Integer.valueOf(dateArray[2]), Integer.valueOf(dateArray[1])-1, Integer.valueOf(dateArray[0])).getTime();
		
		return session.createCriteria(Cliente.class)
			.add(Restrictions.between("horaEntrada", DateUtils.lowDateTime(date), DateUtils.highDateTime(date)))
			.add(Restrictions.ilike("nome", "%"+nome+"%")).list();
	}

	public List<Cliente> getInDateInterval(String inicio, String fim) {
		
		String[] inicioArray = inicio.split("/");
		Date dateInicio = new GregorianCalendar(Integer.valueOf(inicioArray[2]), Integer.valueOf(inicioArray[1])-1, Integer.valueOf(inicioArray[0])).getTime();
		
		String[] fimArray = fim.split("/");
		Date dateFim = new GregorianCalendar(Integer.valueOf(fimArray[2]), Integer.valueOf(fimArray[1])-1, Integer.valueOf(fimArray[0])).getTime();
		
		
		return session.createCriteria(Cliente.class)
			.add(Restrictions.between("horaEntrada", DateUtils.lowDateTime(dateInicio), DateUtils.highDateTime(dateFim))).list();
	}
	
}
