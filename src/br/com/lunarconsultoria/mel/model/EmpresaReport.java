package br.com.lunarconsultoria.mel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmpresaReport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoEmpresa;
	private List<Cliente> clientesAtendidos;

	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}
	public List<Cliente> getClientesAtendidos() {
		return clientesAtendidos;
	}
	public void setClientesAtendidos(List<Cliente> clientesAtendidos) {
		this.clientesAtendidos = clientesAtendidos;
	}
	public void addClienteAtendido(Cliente cliente){
		if(this.clientesAtendidos == null){
			this.clientesAtendidos = new ArrayList<Cliente>();
		}
		this.clientesAtendidos.add(cliente);
	}
	
	
	@Override
	public boolean equals(Object arg0) {
		return this.codigoEmpresa.equals(((EmpresaReport) arg0).getCodigoEmpresa());
	}
	
	
	
}
