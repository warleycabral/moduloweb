package br.com.sistxweb.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MsgUtil {
	public static void msgInfo(String str){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,str,null));
	}
	
	public static void msgWarn(String str){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,str,null));
	}
	
	public static void msgError(String str){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,str,null));
	}
	
	public static void msgFatal(String str){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,str,null));
	}
	
	public static void msgInfoInter(String str){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,MensagemUtil.getMensagem(str),null));
	}
	
	public static void msgInfoInter(String str, Object... parametros){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,MensagemUtil.getMensagem(str, parametros),null));
	}

	public static void msgErrorInter(String str){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,MensagemUtil.getMensagem(str),null));
	}
	
	public static void msgErrorInter(String str, Object... parametros){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,MensagemUtil.getMensagem(str, parametros),null));
	}
	
	public static void msgSucesso(String acao, String chaveCandidata){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,MensagemUtil.getMensagem("MsgSucesso", defineTipoSucesso(acao), chaveCandidata),null));
	}
	
	public static void msgErro(String acao, String chaveCandidata){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,MensagemUtil.getMensagem("MsgErro", defineTipoErro(acao), chaveCandidata),null));
	}
	
	private static String defineTipoSucesso(String tmp){
		if (tmp.equalsIgnoreCase("inc"))
			return MensagemUtil.getMensagem("incluido");
		if (tmp.equalsIgnoreCase("alt"))
			return MensagemUtil.getMensagem("alterado");
		if (tmp.equalsIgnoreCase("exc"))
			return MensagemUtil.getMensagem("excluido");
		if (tmp.equalsIgnoreCase("ati"))
			return MensagemUtil.getMensagem("ativado");
		if (tmp.equalsIgnoreCase("des"))
			return MensagemUtil.getMensagem("desativado");
		if (tmp.equalsIgnoreCase("apr"))
			return MensagemUtil.getMensagem("aprovado");
		if (tmp.equalsIgnoreCase("rep"))
			return MensagemUtil.getMensagem("reprovado");
		if (tmp.equalsIgnoreCase("sus"))
			return MensagemUtil.getMensagem("suspenso");
		if (tmp.equalsIgnoreCase("rea"))
			return MensagemUtil.getMensagem("reativado");
		if (tmp.equalsIgnoreCase("fin"))
			return MensagemUtil.getMensagem("finalizado");
		if (tmp.equalsIgnoreCase("can"))
			return MensagemUtil.getMensagem("cancelado");
		if (tmp.equalsIgnoreCase("dec"))
			return MensagemUtil.getMensagem("declinado");
		if (tmp.equalsIgnoreCase("ral"))
			return MensagemUtil.getMensagem("realizado");
		if (tmp.equalsIgnoreCase("dis"))
			return MensagemUtil.getMensagem("dispensado");
		if (tmp.equalsIgnoreCase("rev"))
			return MensagemUtil.getMensagem("revisado");
		return "";
	}
	
	private static String defineTipoErro(String tmp){
		if (tmp.equalsIgnoreCase("inc"))
			return MensagemUtil.getMensagem("incluir");
		if (tmp.equalsIgnoreCase("alt"))
			return MensagemUtil.getMensagem("alterar");
		if (tmp.equalsIgnoreCase("exc"))
			return MensagemUtil.getMensagem("excluir");
		if (tmp.equalsIgnoreCase("ati"))
			return MensagemUtil.getMensagem("ativar");
		if (tmp.equalsIgnoreCase("des"))
			return MensagemUtil.getMensagem("desativar");
		if (tmp.equalsIgnoreCase("apr"))
			return MensagemUtil.getMensagem("aprovar");
		if (tmp.equalsIgnoreCase("rep"))
			return MensagemUtil.getMensagem("reprovar");
		if (tmp.equalsIgnoreCase("sus"))
			return MensagemUtil.getMensagem("suspender");
		if (tmp.equalsIgnoreCase("rea"))
			return MensagemUtil.getMensagem("reativar");
		if (tmp.equalsIgnoreCase("fin"))
			return MensagemUtil.getMensagem("finalizar");
		if (tmp.equalsIgnoreCase("can"))
			return MensagemUtil.getMensagem("cancelar");
		if (tmp.equalsIgnoreCase("dec"))
			return MensagemUtil.getMensagem("declinar");
		if (tmp.equalsIgnoreCase("ral"))
			return MensagemUtil.getMensagem("realizar");
		if (tmp.equalsIgnoreCase("dis"))
			return MensagemUtil.getMensagem("dispensar");
		if (tmp.equalsIgnoreCase("rev"))
			return MensagemUtil.getMensagem("revisar");
		return "";
	}
}