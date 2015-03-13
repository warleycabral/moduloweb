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

import br.com.sistxweb.dao.CarroDAO;
import br.com.sistxweb.model.Carro;

@Path("/carros") 
public class CarroResource {  
	
	Carro carro;  
	  
	@GET 
    @Path("{id}")
    @Produces("application/json") 
    public String getCarro(@PathParam("id") Long id) {
	    carro = new Carro();
	    CarroDAO taxDao = new CarroDAO();
	    this.carro = taxDao.read(id);
	    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	    
	    return gson.toJson(carro); 
    }
	  
  	@POST
    @Path("/inserir")
  	@Produces("application/json") 
  	@Consumes("application/json")
    public String inserirCarro(Carro carro) {
  		Boolean salvou=false;
  		CarroDAO taxDao = new CarroDAO();
  		salvou=taxDao.create(carro);
  		if(salvou){
  			return "Sucesso!";
  		}
  		return "Fracasso";
    }
  	
  	@PUT
    @Path("/atualizar")
  	@Produces("application/json") 
  	@Consumes("application/json")
    public String atualizarCarro(Carro carro) {
  		Boolean salvou=false;
  		CarroDAO taxDao = new CarroDAO(); 
  		salvou=taxDao.update(carro);
  		if(salvou){
  			return "Sucesso!";
  		}
  		return "Fracasso";
    }
  	
  	@GET
    @Path("/buscarCarrosPendentes")
    @Produces("application/json")
    public String buscarCarrosPendentes(){
  		CarroDAO taxDao = new CarroDAO();
  		System.out.println("Veio Aqui");
  		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
  		
     return gson.toJson(taxDao.readList());
    }
	  
}
