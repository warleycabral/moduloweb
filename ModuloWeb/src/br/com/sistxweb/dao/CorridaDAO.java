package br.com.sistxweb.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;

import br.com.sistxweb.model.Cliente;
import br.com.sistxweb.model.Corrida;
import br.com.sistxweb.model.Taxista;
import br.com.sistxweb.util.HibernateUtil;

public class CorridaDAO {
	private Session sessao;
	private Transaction transacao;
	
	public Boolean create(Corrida corrida){
		Boolean retorno=false;
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(corrida);
			this.transacao.commit();
			retorno = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	public Corrida read(Long id){
		Corrida corrida = new Corrida();
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Corrida.class);
			filtro.add(Restrictions.eq("id", id));
			filtro.setFetchMode("cliente", FetchMode.JOIN);
			filtro.setFetchMode("taxista", FetchMode.JOIN);
			corrida = (Corrida) filtro.uniqueResult();
			this.transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			System.out.println("Pegou o cliente? "+corrida.getCliente().getNome());
		} catch (Exception e) {
			System.out.println("DEU PAU");
		}
		return corrida;
	}
	
	public Boolean update(Corrida corrida){
		Boolean retorno=false;
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(corrida);
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
	public List<Corrida> readList(){
		List<Corrida> listaCorrida = new ArrayList<Corrida>();
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Corrida.class);
			filtro.setFetchMode("cliente", FetchMode.JOIN);
			listaCorrida = filtro.list();
			this.transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(Corrida c : listaCorrida){
			System.out.println("rua dos carinhas..."+c.getRua());
		}
		return listaCorrida;
	}
	
	@SuppressWarnings("unchecked")
	public List<Corrida> readListStatusCliente(Cliente cliente, String status){
		List<Corrida> listaCorrida = new ArrayList<Corrida>();
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			StringBuilder hql = new StringBuilder();
			hql.append("SELECT C FROM Corrida AS C JOIN C.cliente AS CL WHERE CL.id = :idCliente " +
					"and C.status like :status ");
			Query query = this.sessao.createQuery(hql.toString());
			query.setLong("idCliente", cliente.getId());
			query.setString("status", status);
			listaCorrida = query.list();
			this.transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(Corrida c : listaCorrida){
			System.out.println("rua dos carinhas..."+c.getRua());
		}
		return listaCorrida;
	}
	
	@SuppressWarnings("unchecked")
	public List<Corrida> readListStatusTaxista(Taxista taxista, String status){
		List<Corrida> listaCorrida = new ArrayList<Corrida>();
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			StringBuilder hql = new StringBuilder();
			hql.append("SELECT C FROM Corrida AS C JOIN C.taxista AS T WHERE T.id = :idTaxista " +
					"and C.status like :status ");
			Query query = this.sessao.createQuery(hql.toString());
			query.setLong("idTaxista", taxista.getId());
			query.setString("status", status);
			listaCorrida = query.list();
			this.transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaCorrida;
	}
	
	@SuppressWarnings("unchecked")
	public List<Corrida> readListStatus(String status){
		List<Corrida> listaCorrida = new ArrayList<Corrida>();
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Corrida.class);
			filtro.add(Restrictions.eq("status", status));
			filtro.setFetchMode("cliente", FetchMode.JOIN);
			listaCorrida = filtro.list();
			this.transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaCorrida;
	}
	
	@SuppressWarnings("unchecked")
	public List<Corrida> readListTaxista(Taxista taxista){
		List<Corrida> listaCorrida = new ArrayList<Corrida>();
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Corrida.class);
			filtro.add(Restrictions.eq("taxista", taxista));
			filtro.setFetchMode("cliente", FetchMode.JOIN);
			filtro.setFetchMode("taxista", FetchMode.JOIN);
			listaCorrida = filtro.list();
			this.transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaCorrida;
	}
	
	@SuppressWarnings("unchecked")
	public List<Corrida> readListCliente(Cliente cliente){
		List<Corrida> listaCorrida = new ArrayList<Corrida>();
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Corrida.class);
			filtro.add(Restrictions.eq("cliente", cliente));
			filtro.setFetchMode("cliente", FetchMode.JOIN);
			filtro.setFetchMode("taxista", FetchMode.JOIN);
			listaCorrida = filtro.list();
			this.transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaCorrida;
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
	
	
	public Corrida buscarCorrida(Long id) {
		Corrida corrida = new Corrida();
			try{
				this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
				this.transacao = this.sessao.beginTransaction();
				Criteria filtro = this.sessao.createCriteria(Corrida.class);
				filtro.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				filtro.add(Restrictions.eq("id", id));
				filtro.setMaxResults(1);
				corrida = (Corrida) filtro.uniqueResult();
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
			return corrida;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<Corrida> listarCorrida(int first, int pageSize, String sortField, SortOrder sortOrder, String status) {
			List<Corrida> corridaList = new ArrayList<Corrida>();
			try {
				this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
				this.transacao = this.sessao.beginTransaction();
				Criteria filtro = this.sessao.createCriteria(Corrida.class);
				filtro.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				filtro.add(Restrictions.eq("status", status));
				filtro.setFirstResult(first);
				filtro.setMaxResults(pageSize);
				if (sortField!=null){
					if (sortOrder.equals(SortOrder.ASCENDING)){
						filtro.addOrder(Order.asc(sortField));
					} else if (sortOrder.equals(SortOrder.DESCENDING)){
						filtro.addOrder(Order.desc(sortField));
					}
				}
				corridaList = filtro.list();
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
					System.out.println("ERRO - Corrida/Listar: " + e.getMessage());
				}
			}
			return corridaList;
	}
	
	@SuppressWarnings({ "unchecked" })
	public Integer listarCorridaCount(String status) {
			int count=0;
			List<Corrida> list = new ArrayList<Corrida>();
			try {
				this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
				this.transacao = this.sessao.beginTransaction();
				Criteria filtro = this.sessao.createCriteria(Corrida.class);
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
					System.out.println("ERRO - Corrida/Listar Count: " + e.getMessage());
				}
			}
			return count;
	}
	
}
