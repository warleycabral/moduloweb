package br.com.sistxweb.bean;

import javax.annotation.PostConstruct;

public class csu09Bean {
	
	@PostConstruct
	public void init(){

	}
	
	public String goCreate(){
		
		return "/views/csu09/create.xhtml";
	}
	
	public String goCancelCreate (){
		
		return "/views/csu09/show.xhtml";
	}
	
	public String goEdit(){
		
		return "/views/csu09/edit.xhtml";
	}
	
	public String goCancelEdit (){
		
		return "/views/csu09/show.xhtml";
	}
	
	public String save(){
		
		return "/views/csu09/show.xhtml";
	}
	
	public String update(){
		
		return "/views/csu09/show.xhtml";
	}
	
	public String delete(){

		return "/views/csu09/show.xhtml";
	}
	
}
