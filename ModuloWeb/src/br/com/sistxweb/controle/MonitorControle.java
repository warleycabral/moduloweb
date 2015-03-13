package br.com.sistxweb.controle;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.SortOrder;

import br.com.sistxweb.dao.CorridaDAO;
import br.com.sistxweb.model.Corrida;

public class MonitorControle {
	
	public Corrida buscarCorrida(Long id){
		Corrida poupanca = new Corrida();
		CorridaDAO poupDao = new CorridaDAO();
		poupanca=poupDao.buscarCorrida(id);
		return poupanca;
	}
	
	public Integer listarCorridaCount(String status){
		int count = 0;
		CorridaDAO poupDao = new CorridaDAO();
		count=poupDao.listarCorridaCount(status);
		return count;
	}
	
	public List<Corrida> listarCorrida(int first, int pageSize, String sortField, SortOrder sortOrder, String status){
		List<Corrida> listPoupancaBancos = new ArrayList<Corrida>();
		CorridaDAO poupDao = new CorridaDAO();
		listPoupancaBancos= poupDao.listarCorrida(first, pageSize, sortField, sortOrder, status);
		return listPoupancaBancos;
	}
}
