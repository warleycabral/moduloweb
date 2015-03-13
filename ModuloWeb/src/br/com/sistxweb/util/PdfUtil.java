package br.com.sistxweb.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.context.FacesContext;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class PdfUtil extends PdfPageEventHelper{
	
	private String tituloRelatorio;
	private String nomeCurtoUsuarioLogado;
	private String dataHoraRelatorio;
	private Font fonteBranca = FontFactory.getFont("Arial", 12, Font.BOLD, new BaseColor(255,255,255));
	private Font fonteHeader = FontFactory.getFont("Arial", 18, Font.BOLD, new BaseColor(96, 96, 96));
	private Font fonteFooter = FontFactory.getFont("Arial", 7, Font.BOLD, new BaseColor(128, 128, 128));
	private ArrayList<PdfPCell> celulas;
	
	public void setCelulas(ArrayList<PdfPCell> celulas) {
		this.celulas = celulas;
	}
	
	public PdfUtil(ArrayList<PdfPCell> celulasHeader) {
		super();
		try {
			nomeCurtoUsuarioLogado = "usuario logado for what?";
    	} catch (NullPointerException e){
    		nomeCurtoUsuarioLogado = "Sem Usuário Logado";
    	}
		celulas = celulasHeader;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		dataHoraRelatorio = formatter.format(new Date());
	}
	
	public PdfUtil() {
		super();
		try {
			nomeCurtoUsuarioLogado = "usuário logado for what?";
    	} catch (NullPointerException e){
    		nomeCurtoUsuarioLogado = "Sem Usuário Logado";
    	}
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		dataHoraRelatorio = formatter.format(new Date());
	}

	public String getTituloRelatorio() {
		return tituloRelatorio;
	}

	public void setTituloRelatorio(String tituloRelatorio) {
		this.tituloRelatorio = tituloRelatorio;
	}

	public void onStartPage(PdfWriter writer, Document doc){
        PdfPTable header = new PdfPTable(100);
        header.setTotalWidth(doc.getPageSize().getWidth());
        
        PdfPCell titulo = new PdfPCell(new Paragraph(tituloRelatorio, fonteHeader));
    	titulo.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	titulo.setVerticalAlignment(Element.ALIGN_BOTTOM);
    	titulo.setColspan(65);
    	titulo.setPadding(10f);
    	titulo.setPaddingBottom(17f);
    	titulo.setBorder(0);
    	
    	PdfPCell paginacao = new PdfPCell(new Paragraph("" + doc.getPageNumber(), fonteBranca));
    	paginacao.setColspan(5);
    	paginacao.setHorizontalAlignment(Element.ALIGN_CENTER);
    	paginacao.setVerticalAlignment(Element.ALIGN_MIDDLE);
    	paginacao.setBackgroundColor(new BaseColor(000, 000, 000));
    	paginacao.setBorder(0);
    	
    	header.addCell(espacamento(5));
    	header.addCell(espacamento(20));	
    	header.addCell(titulo);
    	header.addCell(paginacao);
    	header.addCell(espacamento(5));
    	header.addCell(espacamento(5));
    	header.addCell(linhaHeader());
    	header.addCell(espacamento(5));
    	
    	for(PdfPCell p:celulas){
    		header.addCell(p);
    	}
    	header.writeSelectedRows(0,-1, 0, (doc.getPageSize().getHeight()-10), writer.getDirectContent());
   }
	
    public void onEndPage(PdfWriter writer, Document doc){
    	PdfPTable footerTbl = new PdfPTable(100);
        footerTbl.setTotalWidth(doc.getPageSize().getWidth());

        PdfPCell cellNomeCurtoUsuarioLogado = new PdfPCell(new Paragraph(nomeCurtoUsuarioLogado, fonteFooter));
        cellNomeCurtoUsuarioLogado.setHorizontalAlignment(Element.ALIGN_LEFT);
        cellNomeCurtoUsuarioLogado.setBorder(0);
        cellNomeCurtoUsuarioLogado.setColspan(30);
        
        PdfPCell cellPagina = new PdfPCell(new Paragraph("" + doc.getPageNumber() , fonteFooter));
        cellPagina.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellPagina.setColspan(30);
        cellPagina.setBorder(0);
        
        PdfPCell cellDataHoraRelatorio = new PdfPCell(new Paragraph(dataHoraRelatorio, fonteFooter));
        cellDataHoraRelatorio.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cellDataHoraRelatorio.setColspan(30);
        cellDataHoraRelatorio.setBorder(0);
        
        footerTbl.addCell(linhaFooter());
        footerTbl.addCell(espacamento(5));
        footerTbl.addCell(cellNomeCurtoUsuarioLogado);
        footerTbl.addCell(cellPagina);
        footerTbl.addCell(cellDataHoraRelatorio);
        footerTbl.addCell(espacamento(5));

        footerTbl.writeSelectedRows(0,-1, 0, (doc.bottomMargin() + 10),  writer.getDirectContent());
    }

    	
    public PdfPCell espacamento(Integer espaco){
    	PdfPCell cell = new PdfPCell();
    	cell.setColspan(espaco);
    	cell.setBorder(0);
    	return cell;
    }
    
    private PdfPCell linhaHeader(){
    	PdfPCell linha = new PdfPCell(new Paragraph(new Chunk("")));
    	linha.setColspan(90);
    	linha.setHorizontalAlignment(Element.ALIGN_CENTER);
    	linha.setVerticalAlignment(Element.ALIGN_TOP);
    	linha.setBorder(0);
    	linha.setBorderWidthTop(1);
    	return linha;
    }
    
    private PdfPCell linhaFooter(){
    	PdfPCell linha = new PdfPCell(new Paragraph(new Chunk(new LineSeparator(0.4f, 95, BaseColor.BLACK, Element.ALIGN_LEFT, 0))));
    	linha.setPaddingLeft(20);
    	linha.setColspan(100);
    	linha.setHorizontalAlignment(Element.ALIGN_CENTER);
    	linha.setVerticalAlignment(Element.ALIGN_TOP);
    	linha.setBorder(0);
    	return linha;
    }
    
    public PdfPCell geraCelulaFiltroCampoBusca(String conteudo, Boolean primeira){
		PdfPCell celula = new PdfPCell(new Paragraph(conteudo + ": ", FontFactory.getFont("Arial", 10, Font.BOLD, new BaseColor(000,000,000))));
		celula.setBackgroundColor(new BaseColor(255,255,0));
		celula.setColspan(90);
		celula.setBorder(0);
		if (primeira)
			celula.setPaddingTop(5f);
		return celula;
	}
	
	public PdfPCell geraCelulaFiltroValor(String conteudo){
		PdfPCell celula = new PdfPCell(new Paragraph(conteudo));
		celula.setBackgroundColor(new BaseColor(255,255,0));
		celula.setColspan(90);
		celula.setBorder(0);
		return celula;
	}
}
