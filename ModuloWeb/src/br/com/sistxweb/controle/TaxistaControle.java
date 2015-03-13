package br.com.sistxweb.controle;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.SortOrder;

import br.com.sistxweb.dao.TaxistaDAO;
import br.com.sistxweb.model.Taxista;

public class TaxistaControle {
	
	public Boolean saveControle(Taxista taxista){
		Boolean salvou=false;
		TaxistaDAO taxistaDao = new TaxistaDAO(); 
		salvou=taxistaDao.create(taxista);
		return salvou;
	}
	
	public Boolean updateControle(Taxista taxista){
		Boolean salvou=false;
		TaxistaDAO taxistaDao = new TaxistaDAO(); 
		salvou=taxistaDao.update(taxista);
		return salvou;
	}
	
	public Boolean deleteControle(Taxista taxista){
		Boolean salvou=false;
		TaxistaDAO taxistaDao = new TaxistaDAO(); 
		salvou=taxistaDao.delete(taxista);
		return salvou;
	}
	
	public Taxista buscarTaxista(Long id){
		Taxista corrida = new Taxista();
		TaxistaDAO taxistaDao = new TaxistaDAO();
		corrida=taxistaDao.read(id);
		return corrida;
	}
	
	public Integer listarTaxistasCount(String nome){
		int count = 0;
		TaxistaDAO taxistaDao = new TaxistaDAO();
		count=taxistaDao.listarTaxistaCount(nome);
		return count;
	}
	
	public List<Taxista> listarTaxistas(int first, int pageSize, String sortField, SortOrder sortOrder, String nome){
		List<Taxista> listTaxista = new ArrayList<Taxista>();
		TaxistaDAO taxistaDao = new TaxistaDAO();
		listTaxista= taxistaDao.listarTaxistas(first, pageSize, sortField, sortOrder, nome);
		return listTaxista;
	}
	
}
