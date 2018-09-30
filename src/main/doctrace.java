package main;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;

public class doctrace {
	public static void main(String[] args) {
	File file = null;
    //WordExtractor extractor = null;
    try
    {
        //file = new File("test.docx");
        file = new File("No.1.QURAN.docx");
    	
        FileInputStream fis = new FileInputStream(file.getAbsolutePath());

        XWPFDocument document = new XWPFDocument(fis);
        List<XWPFParagraph> paragraphs = document.getParagraphs();

        
        System.out.println("Total no of paragraph in Docx : "+paragraphs.size());
        String head = null;
        String sub = null;
        String arab =  null;
        String trans = null;
        
        PrintWriter writer;
        writer = new PrintWriter("doctest.tsv", "UTF-8");
        List<Record> records = new ArrayList<Record>();
        for (XWPFParagraph para : paragraphs) {
            XWPFStyle style = document.getStyles().getStyle(para.getStyleID());
            boolean first = true;
            for (XWPFRun run : para.getRuns()) {
              if(first==true && run.getFontName()!= null) {
            	  if (run.getFontSize() == 22) {
            		  if (trans != null) {
                		  records.add(new Record(head, sub, arab, trans));
                		  trans=null;
                		  arab=null;
                		  }
            		  head = para.getText();
            		  first = false;
            	  }
            	  else if (run.getFontSize() == 20) {
            		  if (trans != null) {
                		  records.add(new Record(head, sub, arab, trans));
                		  trans=null;
                		  arab=null;
                		  }
            		  sub = para.getText();
            		  first = false;
            	  }
            	  else if ((run.getFontSize() == 18 ) && run.getFontName().equalsIgnoreCase("Traditional Arabic")) {
            		  if (trans != null) {
            		  records.add(new Record(head, sub, arab, trans));
            		  trans=null;
            		  arab=null;
            		  }
            		  
            		  arab = para.getText();

            		  first = false;
            		  //writer.println(para.getText()+"\t"+run.isBold()+"\t"+run.isItalic()+"\t"+ run.getFontSize()+"\t"+run.getFontName()+"\t"+run.getColor());
            		  }
            	  else if (run.getFontSize() == 18 && run.getFontName().equalsIgnoreCase("Jameel Noori Nastaleeq")) {
            		  if (trans==null) {
            			  trans=para.getText();
            			  first = false;
            		  }
            		  else {
            		  trans = trans+ "****"+ para.getText();
            		  first = false;}
            	  }
            }
        }
       }
        System.out.println("/////////////////////////////////////////////");
       
		writer = new PrintWriter("Records.tsv", "UTF-8");
	    for (Record rec: records) {
        	//System.out.println(rec.Display());
        	writer.println(rec.Display());
        }
        writer.close();
        fis.close();
        
    }catch (Exception e) {
        e.printStackTrace();
    }
}
}
