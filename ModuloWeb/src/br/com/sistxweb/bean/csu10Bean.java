package br.com.sistxweb.bean;

import javax.annotation.PostConstruct;

public class csu10Bean {
	
	@PostConstruct
	public void init(){

	}
	
	public String goCreate(){
		
		return "/views/csu10/create.xhtml";
	}
	
	public String goCancelCreate (){
		
		return "/views/csu10/show.xhtml";
	}
	
	public String goEdit(){
		
		return "/views/csu10/edit.xhtml";
	}
	
	public String goCancelEdit (){
		
		return "/views/csu10/show.xhtml";
	}
	
	public String save(){
		
		return "/views/csu10/show.xhtml";
	}
	
	public String update(){
		
		return "/views/csu10/show.xhtml";
	}
	
	public String delete(){

		return "/views/csu10/show.xhtml";
	}
	
}
