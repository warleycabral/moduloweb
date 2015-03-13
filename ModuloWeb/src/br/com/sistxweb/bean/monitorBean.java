package br.com.sistxweb.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.sistxweb.controle.MonitorControle;
import br.com.sistxweb.model.Corrida;

public class monitorBean {
	
	LazyDataModel<Corrida> listaNovasCorridas;
	LazyDataModel<Corrida> listaAndamentoCorridas;
	Corrida corridaNovaTemp;
	Corrida corridaAndamentoTemp;
	MonitorControle controle;
	
	@PostConstruct
	public void init(){
		listaNovasCorridas = inicializaCorridaList();
		listaAndamentoCorridas = inicializaCorridaList();
		controle = new MonitorControle();
		corridaNovaTemp = new Corrida();
		corridaAndamentoTemp = new Corrida();
	}
	
	public String monitor(){
		init();
		return "/views/monitor/show.xhtml";
	}
	
	@SuppressWarnings("serial")
	public void listarNovasCorridas(){
		listaNovasCorridas = new LazyDataModel<Corrida>() {
	        @Override
	        public List<Corrida> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
	        	
               return controle.listarCorrida(first, pageSize, sortField, sortOrder, "Pendente");
	        }
	        
	        @Override  
	        public Corrida getRowData(String rowKey)  
	        {  
	        	try {
	        		corridaNovaTemp = controle.buscarCorrida(Long.parseLong(rowKey));
		            return corridaNovaTemp;
	        	} catch (NumberFormatException e){
	        		return new Corrida();
	        	}
	        }   
	          
	        @Override  
	        public String getRowKey(Corrida objeto)  
	        {  
	        	if (objeto!=null)
	        		return ""+objeto.getId();
	        	return "";
	        }
	    };
	    listaNovasCorridas.setRowCount(controle.listarCorridaCount("Pendente"));
	    listaNovasCorridas.setPageSize(5);
	}
	
	@SuppressWarnings("serial")
	public void listarAndamentoCorridas(){
		listaAndamentoCorridas = new LazyDataModel<Corrida>() {
	        @Override
	        public List<Corrida> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
	        	
               return controle.listarCorrida(first, pageSize, sortField, sortOrder, "Andamento");
	        }
	        
	        @Override  
	        public Corrida getRowData(String rowKey)  
	        {  
	        	try {
	        		corridaAndamentoTemp = controle.buscarCorrida(Long.parseLong(rowKey));
		            return corridaAndamentoTemp;
	        	} catch (NumberFormatException e){
	        		return new Corrida();
	        	}
	        }   
	          
	        @Override  
	        public String getRowKey(Corrida objeto)  
	        {  
	        	if (objeto!=null)
	        		return ""+objeto.getId();
	        	return "";
	        }
	    };
	    listaAndamentoCorridas.setRowCount(controle.listarCorridaCount("Andamento"));
	    listaAndamentoCorridas.setPageSize(5);
	}
	
	public String goCreate(){
		return "/views/monitor/create.xhtml";
	}
	
	public String goCancelCreate (){
		
		return "/views/monitor/show.xhtml";
	}
	
	public String goEdit(){
		
		return "/views/monitor/edit.xhtml";
	}
	
	public String goCancelEdit (){
		
		return "/views/monitor/show.xhtml";
	}
	
	public String save(){
		return "/views/monitor/show.xhtml";
	}
	
	public String update(){
		
		return "/views/monitor/show.xhtml";
	}
	
	public String delete(){

		return "/views/monitor/show.xhtml";
	}
	
	public LazyDataModel<Corrida> getListaNovasCorridas() {
		return listaNovasCorridas;
	}

	public void setListaNovasCorridas(LazyDataModel<Corrida> listaCorridas) {
		this.listaNovasCorridas = listaCorridas;
	}

	public Corrida getCorridaNovaTemp() {
		return corridaNovaTemp;
	}

	public void setCorridaNovaTemp(Corrida corridaTemp) {
		this.corridaNovaTemp = corridaTemp;
	}
	
	

	public LazyDataModel<Corrida> getListaAndamentoCorridas() {
		return listaAndamentoCorridas;
	}

	public void setListaAndamentoCorridas(
			LazyDataModel<Corrida> listaAndamentoCorridas) {
		this.listaAndamentoCorridas = listaAndamentoCorridas;
	}

	public Corrida getCorridaAndamentoTemp() {
		return corridaAndamentoTemp;
	}

	public void setCorridaAndamentoTemp(Corrida corridaAndamentoTemp) {
		this.corridaAndamentoTemp = corridaAndamentoTemp;
	}

	@SuppressWarnings("serial")
	public static LazyDataModel<Corrida> inicializaCorridaList() {
		LazyDataModel<Corrida> modelList = new LazyDataModel<Corrida>() {
			@Override
			public List<Corrida> load(int arg0, int arg1, String arg2,
					SortOrder arg3, Map<String, String> arg4) {
				return new ArrayList<Corrida>();
			}

			@Override
			public Corrida getRowData(String rowKey) {
				return new Corrida();
			}

			@Override
			public String getRowKey(Corrida objeto) {
				if (objeto != null)
					return "" + objeto.getId();
				return "";
			}
		};
		modelList.setPageSize(5);
		return modelList;
	}
}
