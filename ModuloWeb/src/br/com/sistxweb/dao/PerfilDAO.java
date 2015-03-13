package br.com.sistxweb.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sistxweb.model.Perfil;
import br.com.sistxweb.util.HibernateUtil;

public class PerfilDAO {
	private Session sessao;
	private Transaction transacao;
	
	public Boolean create(Perfil perfil){
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			//
			this.transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public Perfil read(Long id){
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			//
			this.transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public Boolean update(Perfil perfil){
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			//
			this.transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public Boolean delete(Long id){
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			//
			this.transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<Perfil> readList(){
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			//
			this.transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Integer readListCount(){
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			//
			this.transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
