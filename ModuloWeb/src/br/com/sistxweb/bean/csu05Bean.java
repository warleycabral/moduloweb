package br.com.sistxweb.bean;

import javax.annotation.PostConstruct;

public class csu05Bean {
	
	@PostConstruct
	public void init(){

	}
	
	public String goCreate(){
		
		return "/views/csu05/create.xhtml";
	}
	
	public String goCancelCreate (){
		
		return "/views/csu05/show.xhtml";
	}
	
	public String goEdit(){
		
		return "/views/csu05/edit.xhtml";
	}
	
	public String goCancelEdit (){
		
		return "/views/csu05/show.xhtml";
	}
	
	public String save(){
		
		return "/views/csu05/show.xhtml";
	}
	
	public String update(){
		
		return "/views/csu05/show.xhtml";
	}
	
	public String delete(){

		return "/views/csu05/show.xhtml";
	}
	
}
