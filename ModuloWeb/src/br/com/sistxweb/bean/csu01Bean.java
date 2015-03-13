package br.com.sistxweb.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.sistxweb.controle.CooperativaControle;
import br.com.sistxweb.controle.TaxistaControle;
import br.com.sistxweb.model.Cooperativa;
import br.com.sistxweb.model.Perfil;
import br.com.sistxweb.model.Taxista;
import br.com.sistxweb.util.MsgUtil;

public class csu01Bean {
	
	private Taxista taxista;
	private TaxistaControle controleTax;
	private String nomeTaxistaBusca;
	private LazyDataModel<Taxista> listaTaxista;
	private Taxista taxistaTemp;
	
	private String senhaTaxista;
	private String confirmacaoSenhaTaxista;
	
	private String nomeCooperativaBusca;
	private LazyDataModel<Cooperativa> listaCooperativa;
	private Cooperativa cooperativa;
	private Cooperativa cooperativaTemp;
	
	private CooperativaControle controleCoop;
	
	@PostConstruct
	public void init(){
		taxista = new Taxista();
		taxistaTemp = new Taxista();
		controleTax = new TaxistaControle();
		nomeTaxistaBusca = "";
		listaTaxista = inicializaTaxistaList();
		listaCooperativa = inicializaCooperativaList();
		cooperativa = new Cooperativa();
		cooperativaTemp = new Cooperativa();
		senhaTaxista="";
		confirmacaoSenhaTaxista="";
		controleCoop = new CooperativaControle();
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
	
	@SuppressWarnings("serial")
	public void filtrarCooperativas(){
		listaCooperativa = new LazyDataModel<Cooperativa>() {
	        @Override
	        public List<Cooperativa> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
	        	
               return controleCoop.listarCooperativas(first, pageSize, sortField, sortOrder, nomeCooperativaBusca);
	        }
	        
	        @Override  
	        public Cooperativa getRowData(String rowKey)  
	        {  
	        	try {
	        		cooperativaTemp = controleCoop.buscarCooperativa(Long.parseLong(rowKey));
		            return cooperativaTemp;
	        	} catch (NumberFormatException e){
	        		return new Cooperativa();
	        	}
	        }   
	          
	        @Override  
	        public String getRowKey(Cooperativa objeto)  
	        {  
	        	if (objeto!=null)
	        		return ""+objeto.getId();
	        	return "";
	        }
	    };
	    listaCooperativa.setRowCount(controleCoop.listarCooperativasCount(nomeCooperativaBusca));
	    listaCooperativa.setPageSize(5);
	}
	
	public void selecionaCooperativa(){
		if(cooperativaTemp!=null && cooperativaTemp.getId()!=null){
			taxista.setCooperativa(cooperativaTemp);
			listaCooperativa = inicializaCooperativaList();
		}
	}
	
	public void limparLista(){
		listaTaxista = inicializaTaxistaList();
		listaCooperativa = inicializaCooperativaList();
	}
	
	public String csu01(){
		init();
		return "/views/csu01/show.xhtml";
	}
	
	public String goCreate(){
		taxista = new Taxista();
		return "/views/csu01/create.xhtml";
	}
	
	public String goCancelCreate (){
		
		return "/views/csu01/show.xhtml";
	}
	
	public String goEdit(){
		
		return "/views/csu01/edit.xhtml";
	}
	
	public String goCancelEdit (){
		
		return "/views/csu01/show.xhtml";
	}
	
	public String save(){
		Perfil perfil = new Perfil();
		perfil.setId(2L);
		taxista.setPerfil(perfil);
		if(controleTax.saveControle(taxista)){
			MsgUtil.msgSucesso("inc", taxista.getNome());
		}
		else{
			MsgUtil.msgErro("inc", taxista.getNome()	);
		}
		return "/views/csu01/show.xhtml";
	}
	
	public String update(){
		if(controleTax.updateControle(taxista)){
			MsgUtil.msgSucesso("alt", taxista.getNome());
		}
		else{
			MsgUtil.msgErro("alt", taxista.getNome()	);
		}
		
		return "/views/csu01/show.xhtml";
	}
	
	public boolean getShowEdit(){
		if(taxista!=null && taxista.getId()!=null && taxista.getId()>0){
			return true;
		}
		return false;
	}
	
	public boolean getShowDelete(){
		if(taxista!=null && taxista.getId()!=null && taxista.getId()>0){
			return true;
		}
		return false;
	}
	
	public String delete(){
		if(controleTax.deleteControle(taxista)){
			MsgUtil.msgSucesso("exc", taxista.getNome());
		}
		else{
			MsgUtil.msgErro("exc", taxista.getNome()	);
		}
		taxista=new Taxista();
		return "/views/csu01/show.xhtml";
	}

	public Taxista getTaxista() {
		return taxista;
	}

	public void setTaxista(Taxista taxista) {
		this.taxista = taxista;
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

	public Taxista getTaxistaTemp() {
		return taxistaTemp;
	}

	public void setTaxistaTemp(Taxista taxistaTemp) {
		this.taxistaTemp = taxistaTemp;
	}
	
	public String getSenhaTaxista() {
		return senhaTaxista;
	}

	public void setSenhaTaxista(String senhaTaxista) {
		this.senhaTaxista = senhaTaxista;
	}

	public String getConfirmacaoSenhaTaxista() {
		return confirmacaoSenhaTaxista;
	}

	public void setConfirmacaoSenhaTaxista(String confirmacaoSenhaTaxista) {
		this.confirmacaoSenhaTaxista = confirmacaoSenhaTaxista;
	}
	
	public TaxistaControle getControleTax() {
		return controleTax;
	}

	public void setControleTax(TaxistaControle controleTax) {
		this.controleTax = controleTax;
	}

	public String getNomeCooperativaBusca() {
		return nomeCooperativaBusca;
	}

	public void setNomeCooperativaBusca(String nomeCooperativaBusca) {
		this.nomeCooperativaBusca = nomeCooperativaBusca;
	}

	public LazyDataModel<Cooperativa> getListaCooperativa() {
		return listaCooperativa;
	}

	public void setListaCooperativa(LazyDataModel<Cooperativa> listaCooperativa) {
		this.listaCooperativa = listaCooperativa;
	}

	public Cooperativa getCooperativa() {
		return cooperativa;
	}

	public void setCooperativa(Cooperativa cooperativa) {
		this.cooperativa = cooperativa;
	}

	public Cooperativa getCooperativaTemp() {
		return cooperativaTemp;
	}

	public void setCooperativaTemp(Cooperativa cooperativaTemp) {
		this.cooperativaTemp = cooperativaTemp;
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
	public static LazyDataModel<Cooperativa> inicializaCooperativaList() {
		LazyDataModel<Cooperativa> modelList = new LazyDataModel<Cooperativa>() {
			@Override
			public List<Cooperativa> load(int arg0, int arg1, String arg2,
					SortOrder arg3, Map<String, String> arg4) {
				return new ArrayList<Cooperativa>();
			}

			@Override
			public Cooperativa getRowData(String rowKey) {
				return new Cooperativa();
			}

			@Override
			public String getRowKey(Cooperativa objeto) {
				if (objeto != null)
					return "" + objeto.getId();
				return "";
			}
		};
		modelList.setPageSize(5);
		return modelList;
	}

}
