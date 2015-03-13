package br.com.sistxweb.enuns;

public enum SituCarro {
	Ativo(0),
	Inoperante(1);
	
    public int indice() { return ndx; }   
    private int ndx;  
    private SituCarro(int indice) {  
        ndx = indice;  
    }
    public static String indice(int i){
//    	switch (i) {
//		case 0:
//			return MensagemUtil.getMensagem("Ativo");
//		case 1:
//			return MensagemUtil.getMensagem("Pendente");
//		default:
			return null;
//		} 
    }
}
