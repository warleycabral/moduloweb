package br.com.sistxweb.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;

import br.com.sistxweb.model.Taxista;
import br.com.sistxweb.util.HibernateUtil;

public class TaxistaDAO {
	private Session sessao;
	private Transaction transacao;
	
	public Boolean create(Taxista taxista){
		Boolean salvou=false;
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(taxista);
			this.transacao.commit();
			salvou=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salvou;
	}
	
	public Taxista read(Long id){
		Taxista taxista = new Taxista();
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Taxista.class);
			filtro.add(Restrictions.eq("id", id));
			filtro.setFetchMode("cooperativa", FetchMode.JOIN);
			taxista = (Taxista) filtro.uniqueResult();
			this.transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taxista;
	}
	
	public Boolean update(Taxista taxista){
		Boolean retorno=false;
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(taxista);
			this.transacao.commit();
			retorno=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	public Boolean delete(Taxista taxista){
		Boolean retorno=false;
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.delete(taxista);
			this.transacao.commit();
			retorno=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	@SuppressWarnings("unchecked")
	public List<Taxista> listarTaxistas(int first, int pageSize, String sortField, SortOrder sortOrder, String nome){
		List<Taxista> taxistaList = new ArrayList<Taxista>();
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Taxista.class);
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
			taxistaList = filtro.list();
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
				System.out.println("ERRO - Taxista/Listar: " + e.getMessage());
			}
		}
		return taxistaList;
	}
	
	@SuppressWarnings("unchecked")
	public Integer listarTaxistaCount(String nome){
		int count=0;
		List<Taxista> list = new ArrayList<Taxista>();
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Taxista.class);
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
				System.out.println("ERRO - Taxista/Listar Count: " + e.getMessage());
			}
		}
		return count;
	}
	
	public Taxista logar(Taxista taxista){
		Taxista taxistaLogado=null;
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Taxista.class);
			filtro.add(Restrictions.eq("login", taxista.getLogin()));
			filtro.add(Restrictions.eq("senha", taxista.getSenha()));
			taxistaLogado = (Taxista) filtro.uniqueResult();
			this.transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taxistaLogado;
	}
	
	public List<Taxista> readList(){
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
