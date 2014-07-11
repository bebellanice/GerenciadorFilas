package br.com.lunarconsultoria.mel.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class GenericDao<T> {

	protected Session session;
	private Class clazz;
	
	public GenericDao(Class clazz, Session session){
		this.clazz = clazz;
		this.session = session;
	}
	
	public T save (T t){
		//TODO: VERIFICAR GARGALO
		Transaction tx = session.beginTransaction();
		t = (T) session.merge(t);
		tx.commit();
		session.flush();
		session.clear();
		return t;
	}
	
	public void remove (T t){
		//TODO: VERIFICAR GARGALO
		Transaction tx = session.beginTransaction();
		session.delete(t);
		tx.commit();
	}
	
	public T get (Long id){
		T object = (T) session.get(clazz, id);
		return object;
	}
	
	public List<T> list(){
		List list = session.createCriteria(clazz).list();
		return list;
	}
	
}
