package br.com.sistxweb.bean;

import javax.annotation.PostConstruct;

public class csu06Bean {
	
	@PostConstruct
	public void init(){

	}
	
	public String goCreate(){
		
		return "/views/csu06/create.xhtml";
	}
	
	public String goCancelCreate (){
		
		return "/views/csu06/show.xhtml";
	}
	
	public String goEdit(){
		
		return "/views/csu06/edit.xhtml";
	}
	
	public String goCancelEdit (){
		
		return "/views/csu06/show.xhtml";
	}
	
	public String save(){
		
		return "/views/csu06/show.xhtml";
	}
	
	public String update(){
		
		return "/views/csu06/show.xhtml";
	}
	
	public String delete(){

		return "/views/csu06/show.xhtml";
	}
	
}
