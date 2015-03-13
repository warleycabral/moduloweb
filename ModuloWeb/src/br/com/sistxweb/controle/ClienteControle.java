package br.com.sistxweb.controle;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.SortOrder;

import br.com.sistxweb.dao.ClienteDAO;
import br.com.sistxweb.model.Cliente;

public class ClienteControle {
	
	public Boolean saveControle(Cliente cliente){
		Boolean salvou=false;
		ClienteDAO clienteDao = new ClienteDAO(); 
		salvou=clienteDao.create(cliente);
		return salvou;
	}
	
	public Boolean updateControle(Cliente cliente){
		Boolean salvou=false;
		ClienteDAO clienteDao = new ClienteDAO(); 
		salvou=clienteDao.update(cliente);
		return salvou;
	}
	
	public Cliente buscarCliente(Long id){
		Cliente corrida = new Cliente();
		ClienteDAO clienteDao = new ClienteDAO();
		corrida=clienteDao.read(id);
		return corrida;
	}
	
	public Integer listarClientesCount(String nome){
		int count = 0;
		ClienteDAO clienteDao = new ClienteDAO();
		count=clienteDao.listarClienteCount(nome);
		return count;
	}
	
	public List<Cliente> listarClientes(int first, int pageSize, String sortField, SortOrder sortOrder, String nome){
		List<Cliente> listCliente = new ArrayList<Cliente>();
		ClienteDAO clienteDao = new ClienteDAO();
		listCliente= clienteDao.listarClientes(first, pageSize, sortField, sortOrder, nome);
		return listCliente;
	}
	
}
