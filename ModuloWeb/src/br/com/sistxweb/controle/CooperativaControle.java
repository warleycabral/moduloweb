package br.com.sistxweb.controle;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.SortOrder;

import br.com.sistxweb.dao.CooperativaDAO;
import br.com.sistxweb.model.Cooperativa;

public class CooperativaControle {
	
	public Cooperativa buscarCooperativa(Long id){
		Cooperativa corrida = new Cooperativa();
		CooperativaDAO cooperativaDao = new CooperativaDAO();
		corrida=cooperativaDao.read(id);
		return corrida;
	}
	
	public Integer listarCooperativasCount(String nome){
		int count = 0;
		CooperativaDAO cooperativaDao = new CooperativaDAO();
		count=cooperativaDao.listarCooperativaCount(nome);
		return count;
	}
	
	public List<Cooperativa> listarCooperativas(int first, int pageSize, String sortField, SortOrder sortOrder, String nome){
		List<Cooperativa> listCooperativa = new ArrayList<Cooperativa>();
		CooperativaDAO cooperativaDao = new CooperativaDAO();
		listCooperativa= cooperativaDao.listarCooperativas(first, pageSize, sortField, sortOrder, nome);
		return listCooperativa;
	}
	
}
