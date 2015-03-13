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
import br.com.sistxweb.util.HibernateUtil;

public class ClienteDAO {
	private Session sessao;
	private Transaction transacao;
	
	public Boolean create(Cliente cliente){
		boolean retorno=false;
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(cliente);
			this.transacao.commit();
			retorno = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	public Cliente read(Long id){
		Cliente cliente = new Cliente();
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Cliente.class);
			filtro.add(Restrictions.eq("id", id));
			cliente = (Cliente) filtro.uniqueResult();
			this.transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cliente;
	}
	
	public Cliente logar(Cliente cliente){
		Cliente clienteLogado=null;
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Cliente.class);
			filtro.add(Restrictions.eq("login", cliente.getLogin()));
			filtro.add(Restrictions.eq("senha", cliente.getSenha()));
			clienteLogado = (Cliente) filtro.uniqueResult();
			System.out.println("pegou"+clienteLogado.getId());
			this.transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clienteLogado;
	}
	public Boolean update(Cliente cliente){
		boolean retorno=false;
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(cliente);
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
	public List<Cliente> readList(){
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
	public List<Cliente> listarClientes(int first, int pageSize, String sortField, SortOrder sortOrder, String nome){
		List<Cliente> list = new ArrayList<Cliente>();
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Cliente.class);
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
				System.out.println("ERRO - Cliente/Listar: " + e.getMessage());
			}
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public Integer listarClienteCount(String nome){
		int count=0;
		List<Cliente> list = new ArrayList<Cliente>();
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Cliente.class);
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
				System.out.println("ERRO - Cliente/Listar Count: " + e.getMessage());
			}
		}
		return count;
	}
}
