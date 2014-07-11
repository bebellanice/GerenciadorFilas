package br.com.lunarconsultoria.mel.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.lunarconsultoria.mel.annotation.Restrito;
import br.com.lunarconsultoria.mel.dao.ClienteDao;
import br.com.lunarconsultoria.mel.dao.FilaDao;
import br.com.lunarconsultoria.mel.dao.TipoAtendimentoDao;
import br.com.lunarconsultoria.mel.model.Atendimento;
import br.com.lunarconsultoria.mel.model.Cliente;
import br.com.lunarconsultoria.mel.model.EmpresaReport;
import br.com.lunarconsultoria.mel.model.Fila;
import br.com.lunarconsultoria.mel.model.TipoAtendimento;
import br.com.lunarconsultoria.mel.util.Html2Pdf;

import com.lowagie.text.DocumentException;

@Resource
public class ClientesController {

	private ClienteDao dao;
	private TipoAtendimentoDao tipoAtendimentoDao;
	private FilaDao filaDao;
	private Result result;
	private ServletContext context;
	
	public ClientesController(ClienteDao dao, TipoAtendimentoDao tipoAtendimentoDao, FilaDao filaDao, Result result, ServletContext context) {
		this.dao = dao;
		this.tipoAtendimentoDao = tipoAtendimentoDao;
		this.filaDao = filaDao;
		this.result = result;
		this.context = context;
	}
	
	@Get
	@Restrito
	public void registraEntrada(){
		result.include("tipoAtendimentoList", tipoAtendimentoDao.list());
	}
	
	@Post
	@Restrito
	public void registraEntrada(Cliente cliente, List<TipoAtendimento> tipoAtendimento){
		cliente.setHoraEntrada(GregorianCalendar.getInstance().getTime());
		for (TipoAtendimento tipo : tipoAtendimento) {
			List<Fila> filas = filaDao.getByTipoAtendimento(tipo);
			for (Fila fila : filas) {
				cliente.getFilas().add(fila);
			}
		}
		dao.save(cliente);
		result.redirectTo(ClientesController.class).registraEntrada();
	}
	
	@Get
	@Restrito
	public void buscaRegistro() {
	}
	
	@Post
	@Restrito
	public void buscaRegistro(String nome, String data){
		List<Cliente> clientes = dao.getByNameAndDate(nome, data);
		result.redirectTo(ClientesController.class).exibeResultadoRelatorio(clientes);
	}

	@Get
	@Restrito
	public void exibeResultadoRelatorio(List<Cliente> clientes) {
		result.include("clientesList", clientes);
	}
	
	@Get
	@Restrito
	public void geraDecComparecimento(String cliente_id) throws IOException, DocumentException {
		Cliente cliente = dao.get(Long.valueOf(cliente_id));
		String fileName = convert(cliente, cliente.getAtendimentos());
		result.include("fileName",fileName);
	}
	
	private String convert(Cliente cliente, List<Atendimento> atendimentosList) throws IOException, DocumentException{
		StringBuilder html = new StringBuilder();
		File file = createFileInTemp();
		this.addHeader(html);
		this.addStyleDefinitions(html);
		this.addContents(html, cliente, atendimentosList);
		OutputStream os = new FileOutputStream(file);  
		Html2Pdf.convert(html.toString(), os);
		os.close();
		return file.getName();
		
	}

	private void addHeader(StringBuilder html) {
		html.append("<!DOCTYPE html><html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'></head><body><div id='report'><p><span><img width=239 height=87 src='http://www.mellavras.com.br/site/wp-content/uploads/2012/03/logotipo_ok.png'></span></p><p align=center style='text-align:center'><span style='font-size:22.0pt;'>DECLARA&Ccedil;&Atilde;O DE COMPARECIMENTO<o:p></o:p></span></p>");
	}

	private void addStyleDefinitions(StringBuilder html) {
		html.append("<style>#report {font-family: Verdana;} #div_atendimentos{min-height: 650px;}</style>");
	}

	
	private void addContents(StringBuilder html, Cliente cliente, List<Atendimento> atendimentosList) {
		DateFormat dateformatter = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat hourFormatter = new SimpleDateFormat("HH:mm");
		html.append("<p ><span>Declaro que o segurado ")
		.append(cliente.getNome())
		.append(" permaneceu nesta unidade no dia ")
		.append(dateformatter.format(cliente.getHoraEntrada()))
		.append(" a partir das ")
		.append(hourFormatter.format(cliente.getHoraEntrada()))
		.append("h para os seguintes atendimentos:<o:p></o:p></span></p><p ><span>&nbsp;</span></p>")
		.append("<div id='div_atendimentos'>");
		for (Atendimento atendimento : atendimentosList) {
			html.append("<p><span> ")
			.append(hourFormatter.format(atendimento.getEntrada()))
			.append(" a ")
			.append(hourFormatter.format(atendimento.getSaida()))
			.append(" &#45; ")
			.append(atendimento.getTipoAtendimento().getNome())
			.append("<o:p></o:p></span></p>");
		}
		html.append("</div><p align=center style='text-align:center'><span>Assinatura e carimbo do servidor</span></p></div></body></html>");
	}
	
	
	private File createFileInTemp() {
			File file = new File(context.getRealPath("/") + "/reportsTemp/");
			// check if exist the directory
			if (!file.exists()) {
				file.mkdir();// create directory
			}

			// create the image
			file = new File(context.getRealPath("/") + "/reportsTemp/"
					+ String.valueOf(new Date().getTime()) + ".pdf");
			return file;
	}
	
	@Get
	@Restrito
	public void buscaRegistrosAgrupadoPorEmpresa() {
	}
	
	@Post
	@Restrito
	public void geraEmpresaReport(String inicio, String fim) throws IOException, DocumentException{
		List<Cliente> clientes = dao.getInDateInterval(inicio, fim);
		HashMap<String, EmpresaReport> reportData = new HashMap<String, EmpresaReport>();
		for (Cliente cliente : clientes) {
			EmpresaReport empresa = new EmpresaReport();
			empresa.setCodigoEmpresa(cliente.getCodigoEmpresa());
			if(reportData.containsKey(empresa.getCodigoEmpresa())){
				empresa = reportData.get(cliente.getCodigoEmpresa());
			}
			empresa.addClienteAtendido(cliente);
			reportData.put(empresa.getCodigoEmpresa(), empresa);
		}
		
		String fileName = convertEmpresaReport(reportData);
		result.include("fileName",fileName);
	}

	private String convertEmpresaReport(HashMap<String, EmpresaReport> reportData) throws IOException, DocumentException{
		String html = "";
		File file = createFileInTemp();
		html = addHeaderEmpresaReport(html);
		html = addStyleDefinitionsEmpresaReport(html);
		html = addContentsEmpresaReport(html, reportData);
		OutputStream os = new FileOutputStream(file);  
		Html2Pdf.convert(html, os);
		os.close();
		return file.getName();
		
	}

	private String addContentsEmpresaReport(String html,
			HashMap<String, EmpresaReport> reportData) {
		DateFormat dateformatter = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat hourFormatter = new SimpleDateFormat("HH:mm");
		
		for (String empresa : reportData.keySet()) {
			html +="<table class='table_atendimentos' cellspacing='0' cellpadding='0'>";
			
			html +="<thead>";
			html +="<tr>";
			html +="<th>";
			html +="Empresa "+empresa;
			html +="</th>";
			
			html +="<th>";
			html +="Atendimentos Realizados";
			html +="</th>";
			
			html +="<th>";
			html +="Entrada";
			html +="</th>";
			
			html +="<th>";
			html +="Saida";
			html +="</th>";

			html +="</tr>";
			html +="</thead>";
			html +="</tbody>";
			for (Cliente cliente : reportData.get(empresa).getClientesAtendidos()) {
				if(cliente.getAtendimentos() == null || cliente.getAtendimentos().isEmpty()){
					html +="<tr>";
					html +="<td>";
					html +=cliente.getNome();
					html +="</td>";
					html +="<td colspan='3'>";
					html +="<i>Nao foram realizados atendimentos para este funcionario.</i>";
					html +="</td>";
					html +="</tr>";
				}else{
					html +="<tr>";
					html +="<td>";
					html +=cliente.getNome();
					html +="</td>";
					
					html +="<td>";
					html +="</td>";
					
					html +="<td>";
					html +="</td>";
					
					html +="<td>";
					html +="</td>";
					html +="</tr>";
					
					for (Atendimento atendimento : cliente.getAtendimentos()) {
						html +="<tr>";
						html +="<td>";
						html +="</td>";
						
						html +="<td>";
						html +=atendimento.getTipoAtendimento().getNome();
						html +="</td>";
						
						html +="<td>";
						html +=dateformatter.format(atendimento.getEntrada())+" "+hourFormatter.format(atendimento.getEntrada());
						html +="</td>";
						
						html +="<td>";
						html +=dateformatter.format(atendimento.getSaida())+" "+hourFormatter.format(atendimento.getSaida());
						html +="</td>";
						html +="</tr>";
					}
				}
			}
			/*
			html +="<tr>";
			html +="<td>";
			html +="Cliente ";
			html +="</td>";
			
			html +="<td>";
			html +="Atendimentos Realizados";
			html +="</td>";
			
			html +="<td>";
			html +="Entrada";
			html +="</td>";
			
			html +="<td>";
			html +="Saida";
			html +="</td>";
			html +="</tr>";
			*/
			html +="</tbody>";
			html +="</table>";
			
		}
		
		return html + "</div></body></html>";
	}	
	
	private String addHeaderEmpresaReport(String html) {
		return "<!DOCTYPE html><html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'></head><body><div id='report'><p><span><img width=239 height=87 src='http://www.mellavras.com.br/site/wp-content/uploads/2012/03/logotipo_ok.png'></span></p></span></p>"+html;
	}

	private String addStyleDefinitionsEmpresaReport(String html) {

		return "<style>.table_atendimentos{width: 680px; font-family: Verdana;} .table_atendimentos thead{background-color: #DAEDF5;} .table_atendimentos td{border: 0.5px solid #000030; width: 25%;} .table_atendimentos th{border: 0.5px solid #000030; width: 25%;}</style>"+html;
	}
	

	
	
}
