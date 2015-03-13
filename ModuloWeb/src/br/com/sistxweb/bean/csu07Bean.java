package br.com.sistxweb.bean;

import javax.annotation.PostConstruct;

public class csu07Bean {
	
	@PostConstruct
	public void init(){

	}
	
	public String goCreate(){
		
		return "/views/csu07/create.xhtml";
	}
	
	public String goCancelCreate (){
		
		return "/views/csu07/show.xhtml";
	}
	
	public String goEdit(){
		
		return "/views/csu07/edit.xhtml";
	}
	
	public String goCancelEdit (){
		
		return "/views/csu07/show.xhtml";
	}
	
	public String save(){
		
		return "/views/csu07/show.xhtml";
	}
	
	public String update(){
		
		return "/views/csu07/show.xhtml";
	}
	
	public String delete(){

		return "/views/csu07/show.xhtml";
	}
	
}
