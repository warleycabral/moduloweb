package br.com.sistxweb.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sistxweb.model.Telefone;
import br.com.sistxweb.util.HibernateUtil;

public class TelefoneDAO {
	private Session sessao;
	private Transaction transacao;
	
	public Boolean create(Telefone telefone){
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
	public Telefone read(Long id){
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
	public Boolean update(Telefone telefone){
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
	public List<Telefone> readList(){
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
