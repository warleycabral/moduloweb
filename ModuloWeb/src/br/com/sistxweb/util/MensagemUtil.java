package br.com.sistxweb.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;

public class MensagemUtil {
	@SuppressWarnings("unused")
	private static final String PACOTE_MENSAGENS_IDIOMAS = "br.com.sistxweb.i18n.mensagens"; 

	public static String getMensagem(String propriedade) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(
				context, "lang");
		return bundle.getString(propriedade);

	}

	public static String getMensagem(String propriedade, Object... parametros) {
		String mensagem = getMensagem(propriedade);
		MessageFormat formatter = new MessageFormat(mensagem);
		return formatter.format(parametros);
	}
}
