package br.com.sistxweb.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.sistxweb.controle.ClienteControle;
import br.com.sistxweb.controle.CooperativaControle;
import br.com.sistxweb.controle.LoginControle;
import br.com.sistxweb.controle.TaxistaControle;
import br.com.sistxweb.model.Cliente;
import br.com.sistxweb.model.Cooperativa;
import br.com.sistxweb.model.Perfil;
import br.com.sistxweb.model.Taxista;
import br.com.sistxweb.model.Usuario;
import br.com.sistxweb.util.MsgUtil;

public class csu11Bean {
	
	private String login;
	private String senha;
	private LoginControle controle;
	private TaxistaControle controleTax;
	private ClienteControle controleCli;
	private CooperativaControle controleCoop;
	private Usuario usuario;
	
	private Cliente clienteCadastro;
	private Taxista taxistaCadastro;
	
	private String senhaTaxista;
	private String confirmacaoSenhaTaxista;
	
	private String senhaCliente;
	private String confirmacaoSenhaCliente;
	
	private boolean renderizarTaxista;
	private boolean renderizarCliente;
	private boolean renderizarCooperativa;
	
	private String nomeCooperativaBusca;
	private LazyDataModel<Cooperativa> listaCooperativa;
	private Cooperativa cooperativa;
	private Cooperativa cooperativaTemp;
	
	@PostConstruct
	public void init(){
		renderizarTaxista=true;
		renderizarCliente=false;
		login="";
		senha="";
		controle = new LoginControle();
		controleTax = new TaxistaControle();
		controleCoop = new CooperativaControle();
		controleCli = new ClienteControle();
		usuario = new Usuario();
		clienteCadastro = new Cliente();
		taxistaCadastro = new Taxista();
		taxistaCadastro.setCooperativa(new Cooperativa());
		nomeCooperativaBusca="";
		listaCooperativa = inicializaCooperativaList();
		cooperativa = new Cooperativa();
		cooperativaTemp = new Cooperativa();
		senhaTaxista="";
		confirmacaoSenhaTaxista="";
		senhaCliente="";
		confirmacaoSenhaCliente="";
		
	}
	
	public String logar(){
		ContextoBean contextoBean = new ContextoBean();
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    HttpSession session = ((HttpServletRequest)request).getSession();
	    contextoBean=(ContextoBean)session.getAttribute("contextoBean");
	    
		if(!login.equals("") && !senha.equals("")){
			usuario = controle.logarUsuario(login, senha);
			if(contextoBean == null){
				contextoBean = new ContextoBean();
				session.setAttribute("contextoBean", contextoBean);
				contextoBean = (ContextoBean) session.getAttribute("contextoBean");
			}
			if(usuario!=null 
					&& usuario.getId()!=null 
					&& usuario.getId()>0 
					&& usuario instanceof Cliente){
				
				contextoBean.setUsuarioLogado((Cliente) usuario);
				contextoBean.setClienteLogado((Cliente) usuario);
			}else{
				if(usuario!=null 
						&& usuario.getId()!=null 
						&& usuario.getId()>0 
						&& usuario instanceof Taxista){
					contextoBean.setUsuarioLogado((Taxista) usuario);
					contextoBean.setTaxistaLogado((Taxista) usuario);
				}else{
					if(usuario!=null 
							&& usuario.getId()!=null 
							&& usuario.getId()>0 
							&& usuario instanceof Usuario
							&& !(usuario instanceof Taxista)
							&& !(usuario instanceof Cliente)){
						contextoBean.setUsuarioLogado((Usuario) usuario);
					}
				}
			}
		}
		return "/views/index.xhtml";
	}
	
	public String logout(){
		ContextoBean contextoBean = new ContextoBean();
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    HttpSession session = ((HttpServletRequest)request).getSession();
	    contextoBean=(ContextoBean)session.getAttribute("contextoBean");
	    
			if(contextoBean == null){
				contextoBean = new ContextoBean();
				session.setAttribute("contextoBean", contextoBean);
				contextoBean = (ContextoBean) session.getAttribute("contextoBean");
			}
			
			contextoBean.setClienteLogado(null);
			contextoBean.setTaxistaLogado(null);
			contextoBean.setUsuarioLogado(null);
			
		return "/views/index.xhtml";
	}
	
	public String goHome(){
		return "/views/index.xhtml";
	}
	
	public String saveCliente(){
		Perfil perfil = new Perfil();
		perfil.setId(1L);
		clienteCadastro.setPerfil(perfil);
		if(controleCli.saveControle(clienteCadastro)){
			MsgUtil.msgSucesso("inc", clienteCadastro.getNome());
		}
		else{
			MsgUtil.msgErro("inc", clienteCadastro.getNome());
		}
		return "/views/csu11/show.xhtml";
	}
	
	public String saveTaxista(){
		Perfil perfil = new Perfil();
		perfil.setId(2L);
		taxistaCadastro.setPerfil(perfil);
		if(controleTax.saveControle(taxistaCadastro)){
			MsgUtil.msgSucesso("inc", "Gambi");
		}
		else{
			MsgUtil.msgErro("inc", "Gambi");
		}
		return "/views/csu11/show.xhtml";
	}
	
	public void menuChange(ValueChangeEvent menu) throws IOException{
	    String menuSelecionado = (String) menu.getNewValue();
	    if(menuSelecionado.equals("Taxista")){
	    	this.renderizarTaxista=true;
	    	this.renderizarCliente=false;
	    }
	    else{
	    	if(menuSelecionado.equals("Cliente")){
	    		this.renderizarTaxista=false;
		    	this.renderizarCliente=true;
		    }
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
			taxistaCadastro.setCooperativa(cooperativaTemp);
			listaCooperativa = inicializaCooperativaList();
		}
	}
	
	
	
	public String cadastros(){
		return "/views/csu11/show.xhtml";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isRenderizarTaxista() {
		return renderizarTaxista;
	}

	public void setRenderizarTaxista(boolean renderizarTaxista) {
		this.renderizarTaxista = renderizarTaxista;
	}

	public boolean isRenderizarCliente() {
		return renderizarCliente;
	}

	public void setRenderizarCliente(boolean renderizarCliente) {
		this.renderizarCliente = renderizarCliente;
	}

	public boolean isRenderizarCooperativa() {
		return renderizarCooperativa;
	}

	public void setRenderizarCooperativa(boolean renderizarCooperativa) {
		this.renderizarCooperativa = renderizarCooperativa;
	}

	public Cliente getClienteCadastro() {
		return clienteCadastro;
	}

	public void setClienteCadastro(Cliente clienteCadastro) {
		this.clienteCadastro = clienteCadastro;
	}

	public Taxista getTaxistaCadastro() {
		return taxistaCadastro;
	}

	public void setTaxistaCadastro(Taxista taxistaCadastro) {
		this.taxistaCadastro = taxistaCadastro;
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
	
	public String getSenhaCliente() {
		return senhaCliente;
	}

	public void setSenhaCliente(String senhaCliente) {
		this.senhaCliente = senhaCliente;
	}

	public String getConfirmacaoSenhaCliente() {
		return confirmacaoSenhaCliente;
	}

	public void setConfirmacaoSenhaCliente(String confirmacaoSenhaCliente) {
		this.confirmacaoSenhaCliente = confirmacaoSenhaCliente;
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
