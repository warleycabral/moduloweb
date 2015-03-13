package br.com.sistxweb.controle;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.SortOrder;

import br.com.sistxweb.dao.CorridaDAO;
import br.com.sistxweb.model.Cliente;
import br.com.sistxweb.model.Corrida;
import br.com.sistxweb.model.Taxista;
import br.com.sistxweb.util.DateToString;


public class CorridaControle {
	
	public Corrida buscarCorrida(Long id){
		Corrida corrida = new Corrida();
		CorridaDAO corriDAO = new CorridaDAO();
		corrida=corriDAO.read(id);
		return corrida;
	}
	
	public Integer listarCorridaCount(String status){
		int count = 0;
		CorridaDAO corriDAO = new CorridaDAO();
		count=corriDAO.listarCorridaCount(status);
		return count;
	}
	
	public List<Corrida> listarCorridas(int first, int pageSize, String sortField, SortOrder sortOrder, String status){
		List<Corrida> listCorridas = new ArrayList<Corrida>();
		CorridaDAO poupDao = new CorridaDAO();
		listCorridas= poupDao.listarCorrida(first, pageSize, sortField, sortOrder, status);
		return listCorridas;
	}
	
	public List<Corrida> listarCorridas(String statusBusca){
		List<Corrida> listCorridas = new ArrayList<Corrida>();
		CorridaDAO poupDao = new CorridaDAO();
		listCorridas= poupDao.readListStatus(statusBusca);
		return listCorridas;
	}
	
	public List<Corrida> listarCorridas(Taxista taxista){
		List<Corrida> listCorridas = new ArrayList<Corrida>();
		CorridaDAO poupDao = new CorridaDAO();
		listCorridas= poupDao.readListTaxista(taxista);
		return listCorridas;
	}
	
	public List<Corrida> listarCorridas(Cliente cliente){
		List<Corrida> listCorridas = new ArrayList<Corrida>();
		CorridaDAO poupDao = new CorridaDAO();
		listCorridas= poupDao.readListCliente(cliente);
		return listCorridas;
	}
	
	public Corrida verificaMudancaStatusCorrida(Corrida corrida){
		CorridaDAO dao = new CorridaDAO();
		Corrida fromBanco = new Corrida();
		fromBanco=dao.buscarCorrida(corrida.getId());
		if(corrida.getStatus().equals("ConfirmadaCliente") && fromBanco.getStatus().equals("ConfirmadaTaxista")){
			corrida.setStatus("Andamento");
			corrida.setHoraInicio(DateToString.currentTimeString());
		}
		else{
			if(corrida.getStatus().equals("ConfirmadaTaxista") && fromBanco.getStatus().equals("ConfirmadaCliente")){
				corrida.setStatus("Andamento");
				corrida.setHoraInicio(DateToString.currentTimeString());
			}
			else{
				if(corrida.getStatus().equals("ConcluidaTaxista") && fromBanco.getStatus().equals("ConcluidaCliente")){
					corrida.setStatus("Concluida");
					corrida.setHoraFim(DateToString.currentTimeString());
				}
				else{
					if(corrida.getStatus().equals("ConcluidaCliente") && fromBanco.getStatus().equals("ConcluidaTaxista")){
						corrida.setStatus("Concluida");
						corrida.setHoraFim(DateToString.currentTimeString());
					}
				}
			}
		}
		return corrida;
	}
}
