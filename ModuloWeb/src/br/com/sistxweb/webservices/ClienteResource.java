package br.com.sistxweb.webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.sistxweb.dao.ClienteDAO;
import br.com.sistxweb.model.Cliente;

@Path("/clientes") 
public class ClienteResource {  
	
	Cliente cliente;  
	  
	@GET 
    @Path("{id}")
    @Produces("application/json") 
    public String getCorrida(@PathParam("id") Long id) {
	    cliente = new Cliente();
	    ClienteDAO cliDao = new ClienteDAO();
	    this.cliente = cliDao.read(id);
	    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	    
	    return gson.toJson(cliente); 
    }
	
	@POST
    @Path("/logar")
  	@Produces("application/json")
    public Response logarCliente(Cliente cliente) {
  		Cliente clienteLogado=new Cliente();
  		ClienteDAO cliDao = new ClienteDAO();
  		clienteLogado=cliDao.logar(cliente);
  		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
 	    
 	    
 	   if(clienteLogado.getId()!=null){
 			return Response.status(200).entity(""+gson.toJson(clienteLogado)).build();
 		}
 	  return Response.status(404).entity("").build();
    }
	
  	@POST
    @Path("/inserir")
  	@Produces("application/json") 
  	@Consumes("application/json")
    public String inserirCliente(Cliente cliente) {
  		Boolean salvou=false;
  		ClienteDAO cliDao = new ClienteDAO();
  		salvou=cliDao.create(cliente);
  		if(salvou){
  			return "Sucesso!";
  		}
  		return "Fracasso";
    }
  	
  	@PUT
    @Path("/atualizar")
  	@Produces("application/json") 
  	@Consumes("application/json")
    public String atualizarCliente(Cliente cliente) {
  		Boolean salvou=false;
  		ClienteDAO cliDao = new ClienteDAO(); 
  		salvou=cliDao.update(cliente);
  		if(salvou){
  			return "Sucesso!";
  		}
  		return "Fracasso";
    }
  	
  	@GET
    @Path("/buscarClientesPendentes")
    @Produces("application/json")
    public String buscarClientesPendentes(){
  		ClienteDAO cliDao = new ClienteDAO();
  		System.out.println("Veio Aqui");
  		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
  		
     return gson.toJson(cliDao.readList());
    }
	  
}
