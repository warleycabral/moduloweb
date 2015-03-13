package br.com.sistxweb.bean;

import javax.annotation.PostConstruct;

public class csu08Bean {
	
	@PostConstruct
	public void init(){

	}
	
	public String goCreate(){
		
		return "/views/csu08/create.xhtml";
	}
	
	public String goCancelCreate (){
		
		return "/views/csu08/show.xhtml";
	}
	
	public String goEdit(){
		
		return "/views/csu08/edit.xhtml";
	}
	
	public String goCancelEdit (){
		
		return "/views/csu08/show.xhtml";
	}
	
	public String save(){
		
		return "/views/csu08/show.xhtml";
	}
	
	public String update(){
		
		return "/views/csu08/show.xhtml";
	}
	
	public String delete(){

		return "/views/csu08/show.xhtml";
	}
	
}
