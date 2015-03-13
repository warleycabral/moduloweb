package br.com.sistxweb.bean;



import javax.annotation.PostConstruct;
import javax.el.MethodExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import br.com.sistxweb.controle.ClienteControle;
import br.com.sistxweb.controle.TaxistaControle;
import br.com.sistxweb.model.Cliente;
import br.com.sistxweb.model.Taxista;
import br.com.sistxweb.model.Usuario;
import br.com.sistxweb.util.MsgUtil;

public class ContextoBean {
	private Usuario usuarioLogado;
	private Cliente clienteLogado;
	private Taxista taxistaLogado;
	private ClienteControle controleCli;
	private TaxistaControle controleTax;
	private MenuModel simpleMenuModel;
	
	@PostConstruct
	public void init(){
		usuarioLogado = new Usuario();
		clienteLogado = new Cliente();
		taxistaLogado = new Taxista();
		controleCli = new ClienteControle();
		controleTax = new TaxistaControle();
	}
	
	
	public boolean getExisteUsuarioLogado(){
		
		if((usuarioLogado!=null && usuarioLogado.getId()!=null && usuarioLogado.getId() >0)
				|| (clienteLogado!=null && clienteLogado.getId()!=null && clienteLogado.getId() >0)
				|| (taxistaLogado!=null && taxistaLogado.getId()!=null && taxistaLogado.getId() >0)){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public MethodExpression retornaAcao(String uf){
		FacesContext contexto = FacesContext.getCurrentInstance();
		MethodExpression methodExpression = contexto.getApplication().getExpressionFactory().createMethodExpression(
				contexto.getELContext(), uf , null, new Class[] { ActionEvent.class });
		return methodExpression;
	}
	
	public MenuModel getModel(){
		simpleMenuModel = new DefaultMenuModel();
		
		if(usuarioLogado instanceof Taxista){
			MenuItem menuItem = new MenuItem();
			menuItem.setValue("Meus Dados");
			menuItem.setAjax(false);
			menuItem.setActionExpression(retornaAcao("#{contextoBean.meusDadosTaxista()}"));
			
			Submenu sm = new Submenu();
			sm.setLabel("Cadastro");
			sm.getChildren().add(menuItem);
			
			menuItem = new MenuItem();
			menuItem.setValue("Monitor");
			menuItem.setAjax(false);
			menuItem.setActionExpression(retornaAcao("#{monitorBean.monitor()}"));
	        
			sm.getChildren().add(menuItem);
			
	        simpleMenuModel.addSubmenu(sm);
		}
		
		if(usuarioLogado instanceof Cliente){
			MenuItem menuItem = new MenuItem();
			menuItem.setValue("Meus Dados");
			menuItem.setAjax(false);
			menuItem.setActionExpression(retornaAcao("#{contextoBean.meusDadosCliente()}"));
			
			Submenu sm = new Submenu();
			sm.setLabel("Cadastro");
			sm.getChildren().add(menuItem);
			
			menuItem = new MenuItem();
			menuItem.setValue("Monitor");
			menuItem.setAjax(false);
			menuItem.setActionExpression(retornaAcao("#{monitorBean.monitor()}"));
	        
			sm.getChildren().add(menuItem);
			
	        simpleMenuModel.addSubmenu(sm);
		}
        
		if(usuarioLogado instanceof Usuario
				&& !(usuarioLogado instanceof Taxista)
				&& !(usuarioLogado instanceof Cliente)){
			MenuItem menuItem = new MenuItem();
	        menuItem.setValue("Monitor");
	        menuItem.setAjax(false);
	        menuItem.setActionExpression(retornaAcao("#{monitorBean.monitor()}"));
	        
	        Submenu sm = new Submenu();
			sm.setLabel("Manutenção");
			sm.getChildren().add(menuItem);
			
			menuItem = new MenuItem();
	        menuItem.setValue("Manter Taxistas");
	        menuItem.setAjax(false);
	        menuItem.setActionExpression(retornaAcao("#{csu01Bean.csu01()}"));
	        
	        sm.getChildren().add(menuItem);
	        
	        menuItem = new MenuItem();
	        menuItem.setValue("Relatórios");
	        menuItem.setAjax(false);
	        menuItem.setActionExpression(retornaAcao("#{csu04Bean.csu04()}"));
	        
	        sm.getChildren().add(menuItem);
	        
	        simpleMenuModel.addSubmenu(sm);
		}
		
		MenuItem menuItem = new MenuItem();
        menuItem.setValue("Logout");
        menuItem.setAjax(false);
        menuItem.setActionExpression(retornaAcao("#{csu11Bean.logout()}"));
        simpleMenuModel.addMenuItem(menuItem);
        
		return simpleMenuModel;
	}
	
	public String meusDadosCliente(){
		return "/views/meusDados/cliente/show.xhtml";
	}
	
	public String goEditCliente(){
		return "/views/meusDados/cliente/edit.xhtml";
	}
	
	public String goCancelEditCliente(){
		return "/views/meusDados/cliente/show.xhtml";
	}
	
	public String updateCliente(){
		if(controleCli.updateControle(clienteLogado)){
			MsgUtil.msgSucesso("alt", clienteLogado.getNome());
		}
		else{
			MsgUtil.msgErro("alt", clienteLogado.getNome()	);
		}
		
		return "/views/meusDados/cliente/show.xhtml";
	}
	
	
	
	
	public String meusDadosTaxista(){
		return "/views/meusDados/taxista/show.xhtml";
	}
	
	public String goEditTaxista(){
		return "/views/meusDados/taxista/edit.xhtml";
	}
	
	public String goCancelEditTaxista(){
		return "/views/meusDados/taxista/show.xhtml";
	}
	
	public String updateTaxista(){
		if(controleTax.updateControle(taxistaLogado)){
			MsgUtil.msgSucesso("alt", taxistaLogado.getNome());
		}
		else{
			MsgUtil.msgErro("alt", taxistaLogado.getNome()	);
		}
		
		return "/views/meusDados/taxista/show.xhtml";
	}
	
	

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Cliente getClienteLogado() {
		return clienteLogado;
	}

	public void setClienteLogado(Cliente clienteLogado) {
		this.clienteLogado = clienteLogado;
	}

	public Taxista getTaxistaLogado() {
		return taxistaLogado;
	}

	public void setTaxistaLogado(Taxista taxistaLogado) {
		this.taxistaLogado = taxistaLogado;
	}
	
}
