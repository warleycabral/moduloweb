package br.com.sistxweb.webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.sistxweb.controle.CorridaControle;
import br.com.sistxweb.dao.ClienteDAO;
import br.com.sistxweb.dao.CorridaDAO;
import br.com.sistxweb.dao.TaxistaDAO;
import br.com.sistxweb.model.Cliente;
import br.com.sistxweb.model.Corrida;
import br.com.sistxweb.model.Taxista;

@Path("/corridas") 
public class CorridaResource {  
	
	Corrida corrida;
	CorridaControle controleCorrida = new CorridaControle();
	  
	@GET 
    @Path("{id}")
    @Produces("application/json") 
    public String getCorrida(@PathParam("id") Long id) {
	    corrida = new Corrida();
	    CorridaDAO corriDao = new CorridaDAO();
	    this.corrida = corriDao.read(id);
	    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	    
	    return gson.toJson(corrida); 
    }
	  
  	@POST
    @Path("/inserir")
  	@Produces("application/json") 
  	@Consumes("application/json")
    public String inserirCorrida(Corrida corrida) {
  		Boolean salvou=false;
  		CorridaDAO corriDao = new CorridaDAO();
  		salvou=corriDao.create(corrida);
  		if(salvou){
  			return "Sucesso!";
  		}
  		return "Fracasso";
    }
  	
  	@PUT
    @Path("/atualizar")
  	@Produces("application/json") 
  	@Consumes("application/json")
    public String atualizarCorrida(Corrida corrida) {
  		Boolean salvou=false;
  		corrida=controleCorrida.verificaMudancaStatusCorrida(corrida);
  		CorridaDAO corriDao = new CorridaDAO();
  		salvou=corriDao.update(corrida);
  		if(salvou){
  			return "Sucesso!";
  		}
  		return "Fracasso";
    }
  	
  	@GET
    @Path("/buscarCorridasStatus/{status}")
    @Produces("application/json")
    public String buscarCorridasStatus(@PathParam("status") String status){
  		CorridaDAO corriDao = new CorridaDAO();
  		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
     return gson.toJson(corriDao.readListStatus(status));
    }
  	
  	@GET
    @Path("/buscarCorridasStatusCliente/{status}/{id}")
    @Produces("application/json")
    public String buscarCorridasStatusCliente(@PathParam("status") String status, @PathParam("id") Long id){
  		CorridaDAO corriDao = new CorridaDAO();
  		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
  		Cliente cliente = new Cliente();
  		ClienteDAO cliDao = new ClienteDAO();
  		cliente=cliDao.read(id);
     return gson.toJson(corriDao.readListStatusCliente(cliente, status));
    }
  	
  	@GET
    @Path("/buscarCorridasStatusTaxista/{status}/{id}")
    @Produces("application/json")
    public String buscarCorridasStatusTaxista(@PathParam("status") String status, @PathParam("id") Long id){
  		CorridaDAO corriDao = new CorridaDAO();
  		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
  		Taxista taxista = new Taxista();
  		TaxistaDAO taxDao = new TaxistaDAO();
  		taxista=taxDao.read(id);
     return gson.toJson(corriDao.readListStatusTaxista(taxista, status));
    }
  		  
}
