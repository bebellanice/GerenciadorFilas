package br.com.lunarconsultoria.mel.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
public class FabricaDeSession implements ComponentFactory<Session>{
	
	private SessionFactory factory;
	private Session session;
	
	@PostConstruct
	public void abre() {
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
		factory = configuration.buildSessionFactory(serviceRegistry);  
		this.session = factory.openSession();
	}

	public Session getInstance() {
	     return this.session;
	}
	 
	@PreDestroy
	public void fecha() {
		session.doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				connection.close();
			}
		});
	
	}

}
