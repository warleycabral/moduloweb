package br.com.sistxweb.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;

import br.com.sistxweb.model.Cliente;
import br.com.sistxweb.model.Cooperativa;
import br.com.sistxweb.util.HibernateUtil;

public class CooperativaDAO {
	private Session sessao;
	private Transaction transacao;
	
	public Boolean create(Cooperativa cooperativa){
		Boolean retorno=false;
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(cooperativa);
			this.transacao.commit();
			retorno = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	public Cooperativa read(Long id){
		Cooperativa cooperativa = new Cooperativa();
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Cooperativa.class);
			filtro.add(Restrictions.eq("id", id));
			cooperativa = (Cooperativa) filtro.uniqueResult();
			this.transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cooperativa;
	}
	public Boolean update(Cooperativa cooperativa){
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
	@SuppressWarnings("unchecked")
	public List<Cooperativa> readList(){
		List<Cooperativa> listaCooperativa = new ArrayList<Cooperativa>();
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Cooperativa.class);
			listaCooperativa = filtro.list();
			this.transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaCooperativa;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cooperativa> listarCooperativas(int first, int pageSize, String sortField, SortOrder sortOrder, String nome){
		List<Cooperativa> list = new ArrayList<Cooperativa>();
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Cooperativa.class);
			filtro.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			filtro.add(Restrictions.ilike("nome", "%"+nome+"%"));
			filtro.setFirstResult(first);
			filtro.setMaxResults(pageSize);
			if (sortField!=null){
				if (sortOrder.equals(SortOrder.ASCENDING)){
					filtro.addOrder(Order.asc(sortField));
				} else if (sortOrder.equals(SortOrder.DESCENDING)){
					filtro.addOrder(Order.desc(sortField));
				}
			}
			list = filtro.list();
			this.transacao.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (this.transacao.isActive()){
				this.transacao.rollback();
			}
		} finally {
			try {
				if (this.sessao.isOpen()){
					this.sessao.close();
				}
			} catch (Throwable e){
				System.out.println("ERRO - Cooperativa/Listar: " + e.getMessage());
			}
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public Integer listarCooperativaCount(String nome){
		int count=0;
		List<Cooperativa> list = new ArrayList<Cooperativa>();
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Cooperativa.class);
			filtro.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			filtro.add(Restrictions.ilike("nome", "%"+nome+"%"));
			list = filtro.list();
			count=	list.size();
			this.transacao.commit();
		} catch (Throwable e) {
			if (this.transacao.isActive()){
				this.transacao.rollback();
			}
		} finally {
			try {
				if (this.sessao.isOpen()){
					this.sessao.close();
				}
			} catch (Throwable e){
				System.out.println("ERRO - Cooperativa/Listar Count: " + e.getMessage());
			}
		}
		return count;
	}
}
