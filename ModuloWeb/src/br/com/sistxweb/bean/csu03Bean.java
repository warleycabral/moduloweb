package br.com.sistxweb.bean;

import javax.annotation.PostConstruct;

public class csu03Bean {
	
	@PostConstruct
	public void init(){

	}
	
	public String goCreate(){
		
		return "/views/csu03/create.xhtml";
	}
	
	public String goCancelCreate (){
		
		return "/views/csu03/show.xhtml";
	}
	
	public String goEdit(){
		
		return "/views/csu03/edit.xhtml";
	}
	
	public String goCancelEdit (){
		
		return "/views/csu03/show.xhtml";
	}
	
	public String save(){
		
		return "/views/csu03/show.xhtml";
	}
	
	public String update(){
		
		return "/views/csu03/show.xhtml";
	}
	
	public String delete(){

		return "/views/csu03/show.xhtml";
	}

}
