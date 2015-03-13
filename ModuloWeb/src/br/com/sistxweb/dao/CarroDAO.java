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

import br.com.sistxweb.model.Carro;
import br.com.sistxweb.util.HibernateUtil;

public class CarroDAO {
	private Session sessao;
	private Transaction transacao;
	
	public Boolean create(Carro carro){
		Boolean retorno=false;
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(carro);
			this.transacao.commit();
			retorno = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	public Carro read(Long id){
		Carro carro = new Carro();
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Carro.class);
			filtro.add(Restrictions.eq("id", id));
			filtro.setFetchMode("cliente", FetchMode.JOIN);
			carro = (Carro) filtro.uniqueResult();
			this.transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carro;
	}
	
	public Boolean update(Carro carro){
		Boolean retorno=false;
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(carro);
			this.transacao.commit();
			retorno=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
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
	public List<Carro> readList(){
		List<Carro> listaCarro = new ArrayList<Carro>();
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Carro.class);
//			filtro.setFetchMode("cliente", FetchMode.JOIN);
			listaCarro = filtro.list();
			this.transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaCarro;
	}
	
	@SuppressWarnings("unchecked")
	public List<Carro> readListStatus(String status){
		List<Carro> listaCarro = new ArrayList<Carro>();
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Carro.class);
			filtro.add(Restrictions.eq("status", status));
//			filtro.setFetchMode("cliente", FetchMode.JOIN);
			listaCarro = filtro.list();
			this.transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaCarro;
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
	
	
	public Carro buscarCarro(Long id) {
		Carro carro = new Carro();
			try{
				this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
				this.transacao = this.sessao.beginTransaction();
				Criteria filtro = this.sessao.createCriteria(Carro.class);
				filtro.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				filtro.add(Restrictions.eq("id", id));
				filtro.setMaxResults(1);
				carro = (Carro) filtro.uniqueResult();
				this.transacao.commit();
			} catch(Throwable e){
				System.out.println(e.getMessage());
				if (this.transacao.isActive()){
					this.transacao.rollback();
				}
			} finally {
				try {
					if (this.sessao.isOpen()){
						this.sessao.close();
					}
				} catch (Throwable e){
					System.out.println("ERRO - Exame/buscarExame: " + e.getMessage());
				}
			}
			return carro;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<Carro> listarCarro(int first, int pageSize, String sortField, SortOrder sortOrder, String status) {
			List<Carro> carroList = new ArrayList<Carro>();
			try {
				this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
				this.transacao = this.sessao.beginTransaction();
				Criteria filtro = this.sessao.createCriteria(Carro.class);
				filtro.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//				filtro.add(Restrictions.eq("status", status));
				filtro.setFirstResult(first);
				filtro.setMaxResults(pageSize);
				if (sortField!=null){
					if (sortOrder.equals(SortOrder.ASCENDING)){
						filtro.addOrder(Order.asc(sortField));
					} else if (sortOrder.equals(SortOrder.DESCENDING)){
						filtro.addOrder(Order.desc(sortField));
					}
				}
				carroList = filtro.list();
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
					System.out.println("ERRO - Carro/Listar: " + e.getMessage());
				}
			}
			return carroList;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Integer listarCarroCount(String status) {
			int count=0;
			List<Carro> list = new ArrayList<Carro>();
			try {
				this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
				this.transacao = this.sessao.beginTransaction();
				Criteria filtro = this.sessao.createCriteria(Carro.class);
				filtro.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				filtro.add(Restrictions.eq("status", status));
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
					System.out.println("ERRO - Carro/Listar Count: " + e.getMessage());
				}
			}
			return count;
	}
	
}
