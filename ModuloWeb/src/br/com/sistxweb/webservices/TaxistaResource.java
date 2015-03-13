package br.com.sistxweb.webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.sistxweb.dao.TaxistaDAO;
import br.com.sistxweb.model.Taxista;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/taxistas") 
public class TaxistaResource {  
	
	Taxista taxista;  
	  
	@GET 
    @Path("{id}")
    @Produces("application/json") 
    public String getTaxista(@PathParam("id") Long id) {
	    taxista = new Taxista();
	    TaxistaDAO taxDao = new TaxistaDAO();
	    this.taxista = taxDao.read(id);
	    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	    
	    return gson.toJson(taxista); 
    }
	
	@POST
    @Path("/logar")
  	@Produces("application/json")
    public Response logarTaxista(Taxista taxista) {
		Taxista taxistaLogado=new Taxista();
  		TaxistaDAO taxDao = new TaxistaDAO();
  		taxistaLogado=taxDao.logar(taxista);
  		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
 	    
 	    
 	   if(taxistaLogado.getId()!=null){
 			return Response.status(200).entity(""+gson.toJson(taxistaLogado)).build();
 		}
 	  return Response.status(404).entity("").build();
    }
	  
  	@POST
    @Path("/inserir")
  	@Produces("application/json") 
  	@Consumes("application/json")
    public String inserirTaxista(Taxista taxista) {
  		Boolean salvou=false;
  		TaxistaDAO taxDao = new TaxistaDAO();
  		salvou=taxDao.create(taxista);
  		if(salvou){
  			return "Sucesso!";
  		}
  		return "Fracasso";
    }
  	
  	@PUT
    @Path("/atualizar")
  	@Produces("application/json") 
  	@Consumes("application/json")
    public String atualizarTaxista(Taxista taxista) {
  		Boolean salvou=false;
  		TaxistaDAO taxDao = new TaxistaDAO(); 
  		salvou=taxDao.update(taxista);
  		if(salvou){
  			return "Sucesso!";
  		}
  		return "Fracasso";
    }
  	
  	@GET
    @Path("/buscarTaxistasPendentes")
    @Produces("application/json")
    public String buscarTaxistasPendentes(){
  		TaxistaDAO taxDao = new TaxistaDAO();
  		System.out.println("Veio Aqui");
  		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
  		
     return gson.toJson(taxDao.readList());
    }
	  
}
