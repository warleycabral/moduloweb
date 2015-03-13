package br.com.sistxweb.controle;

import br.com.sistxweb.dao.LoginDAO;
import br.com.sistxweb.model.Usuario;

public class LoginControle {
	
	public Usuario logarUsuario(String login, String senha){
		Usuario usuario = new Usuario();
		LoginDAO corriDAO = new LoginDAO();
		usuario=corriDAO.loginUsuario(login, senha);
		return usuario;
	}
	
}
