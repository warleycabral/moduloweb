package br.com.sistxweb.bean;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.sistxweb.controle.ClienteControle;
import br.com.sistxweb.controle.CorridaControle;
import br.com.sistxweb.controle.TaxistaControle;
import br.com.sistxweb.model.Cliente;
import br.com.sistxweb.model.Corrida;
import br.com.sistxweb.model.Taxista;
import br.com.sistxweb.util.PdfUtil;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class csu04Bean {
	
	private Date dataRelatorio;
	private CorridaControle controleCorr;
	private TaxistaControle controleTax;
	private ClienteControle controleCli;
	private String statusBusca; 
	private String nomeTaxistaBusca;
	private LazyDataModel<Taxista> listaTaxista;
	private Taxista taxista;
	private Taxista taxistaTemp;
	
	private String nomeClienteBusca;
	private LazyDataModel<Cliente> listaCliente;
	private Cliente cliente;
	private Cliente clienteTemp;
	
	@PostConstruct
	public void init(){
		controleCorr = new CorridaControle();
		controleTax = new TaxistaControle();
		controleCli = new ClienteControle();
		dataRelatorio = new Date();
		statusBusca="";
		taxista = new Taxista();
		taxistaTemp = new Taxista();
		nomeTaxistaBusca="";
		listaTaxista = inicializaTaxistaList();
		nomeClienteBusca="";
		listaCliente = inicializaClienteList();
		cliente = new Cliente();
		clienteTemp = new Cliente();
	}
	
	public String csu04(){
		init();
		return "/views/csu04/show.xhtml";
	}
	
	public String goCreate(){
		
		return "/views/csu04/create.xhtml";
	}
	
	public String goCancelCreate (){
		
		return "/views/csu04/show.xhtml";
	}
	
	public String goEdit(){
		
		return "/views/csu04/edit.xhtml";
	}
	
	public String goCancelEdit (){
		
		return "/views/csu04/show.xhtml";
	}
	
	public String save(){
		
		return "/views/csu04/show.xhtml";
	}
	
	public String update(){
		
		return "/views/csu04/show.xhtml";
	}
	
	public String delete(){

		return "/views/csu04/show.xhtml";
	}
	
	@SuppressWarnings("serial")
	public void filtrarClientes(){
		listaCliente = new LazyDataModel<Cliente>() {
	        @Override
	        public List<Cliente> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
	        	
               return controleCli.listarClientes(first, pageSize, sortField, sortOrder, nomeClienteBusca);
	        }
	        
	        @Override  
	        public Cliente getRowData(String rowKey)  
	        {  
	        	try {
	        		clienteTemp = controleCli.buscarCliente(Long.parseLong(rowKey));
		            return clienteTemp;
	        	} catch (NumberFormatException e){
	        		return new Cliente();
	        	}
	        }   
	          
	        @Override  
	        public String getRowKey(Cliente objeto)  
	        {  
	        	if (objeto!=null)
	        		return ""+objeto.getId();
	        	return "";
	        }
	    };
	    listaCliente.setRowCount(controleCli.listarClientesCount(nomeTaxistaBusca));
	    listaCliente.setPageSize(5);
	}
	
	public void selecionaCliente(){
		if(clienteTemp!=null && clienteTemp.getId()!=null){
			cliente = clienteTemp;
			listaCliente = inicializaClienteList();
		}
	}
	
	@SuppressWarnings("serial")
	public void filtrarTaxistas(){
		listaTaxista = new LazyDataModel<Taxista>() {
	        @Override
	        public List<Taxista> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
	        	
               return controleTax.listarTaxistas(first, pageSize, sortField, sortOrder, nomeTaxistaBusca);
	        }
	        
	        @Override  
	        public Taxista getRowData(String rowKey)  
	        {  
	        	try {
	        		taxistaTemp = controleTax.buscarTaxista(Long.parseLong(rowKey));
		            return taxistaTemp;
	        	} catch (NumberFormatException e){
	        		return new Taxista();
	        	}
	        }   
	          
	        @Override  
	        public String getRowKey(Taxista objeto)  
	        {  
	        	if (objeto!=null)
	        		return ""+objeto.getId();
	        	return "";
	        }
	    };
	    listaTaxista.setRowCount(controleTax.listarTaxistasCount(nomeTaxistaBusca));
	    listaTaxista.setPageSize(5);
	}
	
	public void selecionaTaxista(){
		if(taxistaTemp!=null && taxistaTemp.getId()!=null){
			taxista = taxistaTemp;
			listaTaxista = inicializaTaxistaList();
		}
	}
	
	private String formataData(Date data){
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			return formatter.format(data);
		} catch (Exception e){
			return " - ";
		}
	}
	
	private String formataHora(Date data){
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
			return formatter.format(data);
		} catch (Exception e){
			return " - ";
		}
	}
	
	private String testaParam(String param){
		try{
			if (param.trim().equals(""))
				return " - ";
			return param;
		} catch (NullPointerException e){
			return " - ";
		}
	}
	
	public String gerarPDF() throws DocumentException, IOException{
		List<Corrida> corridas = new ArrayList<Corrida>();
		@SuppressWarnings("unused")
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		@SuppressWarnings("unused")
		SimpleDateFormat formatter = new SimpleDateFormat("H:mm");
		@SuppressWarnings("unused")
		String dataTemp = "";
		String temp = "";
		
		Document doc = new Document(PageSize.A4);
		doc.setMargins(30, 30, 100, 30);
		
		Font fonteRelatorio = FontFactory.getFont("Arial", 8.7f, 0, BaseColor.BLACK);
		
		PdfUtil page = new PdfUtil();
        page.setTituloRelatorio("Relatório de Corridas "+statusBusca);
        
        //Criação das células do header
        ArrayList<PdfPCell> listaCelulas = new ArrayList<PdfPCell>();
        listaCelulas.add(page.espacamento(5));
        listaCelulas.add(page.geraCelulaFiltroCampoBusca("Relatório Gerado em", true));
        listaCelulas.add(page.espacamento(5));
        listaCelulas.add(page.espacamento(5));
        listaCelulas.add(page.geraCelulaFiltroValor(testaParam(formataData(dataRelatorio)) + " às " + testaParam(formataHora(dataRelatorio))));
        listaCelulas.add(page.espacamento(5));
        page.setCelulas(listaCelulas);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/pdf");     
        response.setHeader("Content-disposition", "attachment;filename="+"relatorioCorridas"+statusBusca+".pdf");

        PdfWriter pdfWriter = PdfWriter.getInstance(doc, response.getOutputStream());
        pdfWriter.setPageEvent(page);
        
        doc.open();
		
		
		if (!statusBusca.equals("")){
			corridas = controleCorr.listarCorridas(statusBusca);
		}
		else{
			if(taxista!=null && taxista.getId()!= null){
				corridas = controleCorr.listarCorridas(taxista);
			}
			else{
				if(cliente!=null && cliente.getId()!= null){
					corridas = controleCorr.listarCorridas(cliente);
				}
			}
		}
		
		PdfPTable content = new PdfPTable(100);
        content.setWidthPercentage(100);
        int cont=0;
		for(Corrida corrida : corridas){
			
			if(taxista!=null && taxista.getId()!= null && cont==0){
				PdfPCell spacer = new PdfPCell(new Paragraph(" ",fonteRelatorio));
				spacer.setColspan(100);
				spacer.setHorizontalAlignment(Element.ALIGN_CENTER);
				spacer.setVerticalAlignment(Element.ALIGN_CENTER);
				spacer.setBorder(0);
		  		content.addCell(spacer);
		  		if(taxista!=null && taxista.getId()!= null){
					temp += "Taxista.: ";
					temp += corrida.getTaxista().getNome() + " ";
				}
				PdfPCell ident1 = new PdfPCell(new Paragraph(temp,fonteRelatorio));
				ident1.setColspan(100);
				ident1.setHorizontalAlignment(Element.ALIGN_LEFT);
				ident1.setVerticalAlignment(Element.ALIGN_TOP);
				ident1.setBorder(0);
				content.addCell(ident1);
//				dataTemp = f.format(corrida.getHoraInicio());
				cont++;
			}
			
			if(cliente!=null && cliente.getId()!= null && cont==0){
				PdfPCell spacer = new PdfPCell(new Paragraph(" ",fonteRelatorio));
				spacer.setColspan(100);
				spacer.setHorizontalAlignment(Element.ALIGN_CENTER);
				spacer.setVerticalAlignment(Element.ALIGN_CENTER);
				spacer.setBorder(0);
		  		content.addCell(spacer);
		  		if(cliente!=null && cliente.getId()!= null){
					temp += "Cliente.: ";
					temp += corrida.getCliente().getNome() + " ";
				}
				PdfPCell ident1 = new PdfPCell(new Paragraph(temp,fonteRelatorio));
				ident1.setColspan(100);
				ident1.setHorizontalAlignment(Element.ALIGN_LEFT);
				ident1.setVerticalAlignment(Element.ALIGN_TOP);
				ident1.setBorder(0);
				content.addCell(ident1);
//				dataTemp = f.format(corrida.getHoraInicio());
				cont++;
			}
			
			temp = "Rua.: ";
			
			temp += corrida.getRua() + ", "+ corrida.getNumero()+".\n";
			
			temp += "Bairro.: ";
			
			temp += corrida.getBairro() + " ";
			
			temp += "Cidade.: ";
	      	
			temp += corrida.getCidade() + " ";

			temp += "Hora Solicitação.: ";
			
			if(!"".equals(corrida.getHoraSolicitacao())){
				temp += corrida.getHoraSolicitacao()+" ";
			}
			else{
				temp += "N/A ";
			}
			
			temp +="Hora Início.: "+" ";
			if(!"".equals(corrida.getHoraInicio())){
				temp += corrida.getHoraInicio() + ".\n";
			}
			else{
				temp += "Não Iniciada"+".\n";
			}
			
			
			// adicionando um parágrafo ao documento
	  		PdfPCell spacer = new PdfPCell(new Paragraph(" ",fonteRelatorio));
			spacer.setColspan(5);
			spacer.setHorizontalAlignment(Element.ALIGN_CENTER);
			spacer.setVerticalAlignment(Element.ALIGN_CENTER);
			spacer.setBorder(0);
	  		content.addCell(spacer);
			PdfPCell acessoCell = new PdfPCell(new Paragraph(temp,fonteRelatorio));
			acessoCell.setColspan(95);
			acessoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			acessoCell.setVerticalAlignment(Element.ALIGN_TOP);
			acessoCell.setBorder(0);
			content.addCell(acessoCell);
		}
		
  		doc.add(content);

        doc.close();
        FacesContext.getCurrentInstance().responseComplete();
        
		return null;
	}
	
	public void limparParametrosRelatorio(){
		cliente = new Cliente();
		taxista = new Taxista();
		statusBusca ="";
	}

	public String getStatusBusca() {
		return statusBusca;
	}

	public void setStatusBusca(String statusBusca) {
		this.statusBusca = statusBusca;
	}
	
	public String getNomeTaxistaBusca() {
		return nomeTaxistaBusca;
	}

	public void setNomeTaxistaBusca(String nomeTaxistaBusca) {
		this.nomeTaxistaBusca = nomeTaxistaBusca;
	}

	public LazyDataModel<Taxista> getListaTaxista() {
		return listaTaxista;
	}

	public void setListaTaxista(LazyDataModel<Taxista> listaTaxista) {
		this.listaTaxista = listaTaxista;
	}

	public Taxista getTaxista() {
		return taxista;
	}

	public void setTaxista(Taxista taxista) {
		this.taxista = taxista;
	}

	public Taxista getTaxistaTemp() {
		return taxistaTemp;
	}

	public void setTaxistaTemp(Taxista taxistaTemp) {
		this.taxistaTemp = taxistaTemp;
	}
	
	public String getNomeClienteBusca() {
		return nomeClienteBusca;
	}

	public void setNomeClienteBusca(String nomeClienteBusca) {
		this.nomeClienteBusca = nomeClienteBusca;
	}

	public LazyDataModel<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(LazyDataModel<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getClienteTemp() {
		return clienteTemp;
	}

	public void setClienteTemp(Cliente clienteTemp) {
		this.clienteTemp = clienteTemp;
	}

	@SuppressWarnings("serial")
	public static LazyDataModel<Taxista> inicializaTaxistaList() {
		LazyDataModel<Taxista> modelList = new LazyDataModel<Taxista>() {
			@Override
			public List<Taxista> load(int arg0, int arg1, String arg2,
					SortOrder arg3, Map<String, String> arg4) {
				return new ArrayList<Taxista>();
			}

			@Override
			public Taxista getRowData(String rowKey) {
				return new Taxista();
			}

			@Override
			public String getRowKey(Taxista objeto) {
				if (objeto != null)
					return "" + objeto.getId();
				return "";
			}
		};
		modelList.setPageSize(5);
		return modelList;
	}
	
	@SuppressWarnings("serial")
	public static LazyDataModel<Cliente> inicializaClienteList() {
		LazyDataModel<Cliente> modelList = new LazyDataModel<Cliente>() {
			@Override
			public List<Cliente> load(int arg0, int arg1, String arg2,
					SortOrder arg3, Map<String, String> arg4) {
				return new ArrayList<Cliente>();
			}

			@Override
			public Cliente getRowData(String rowKey) {
				return new Cliente();
			}

			@Override
			public String getRowKey(Cliente objeto) {
				if (objeto != null)
					return "" + objeto.getId();
				return "";
			}
		};
		modelList.setPageSize(5);
		return modelList;
	}
	
}
