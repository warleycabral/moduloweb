package br.com.sistxweb.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.sistxweb.model.Usuario;
import br.com.sistxweb.util.HibernateUtil;

public class LoginDAO {
	private Session sessao;
	private Transaction transacao;
	
	public Usuario loginUsuario(String login, String senha){
		Usuario usuario = new Usuario();
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Usuario.class);
			filtro.add(Restrictions.eq("login", login));
			filtro.add(Restrictions.eq("senha", senha));
			usuario = (Usuario) filtro.uniqueResult();
			this.transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
}
