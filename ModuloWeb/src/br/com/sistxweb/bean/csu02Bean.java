package br.com.sistxweb.bean;

import javax.annotation.PostConstruct;

public class csu02Bean {
	
	@PostConstruct
	public void init(){

	}
	
	public String goCreate(){
		
		return "/views/csu02/create.xhtml";
	}
	
	public String goCancelCreate (){
		
		return "/views/csu02/show.xhtml";
	}
	
	public String goEdit(){
		
		return "/views/csu02/edit.xhtml";
	}
	
	public String goCancelEdit (){
		
		return "/views/csu02/show.xhtml";
	}
	
	public String save(){
		
		return "/views/csu02/show.xhtml";
	}
	
	public String update(){
		
		return "/views/csu02/show.xhtml";
	}
	
	public String delete(){

		return "/views/csu02/show.xhtml";
	}

}
