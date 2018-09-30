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

public class ExtractorDoc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 File file = null;
	        //WordExtractor extractor = null;
	        try
	        {

	            //file = new File("Sample Doc.docx");
	            file = new File("test.docx");
	        	//file = new File("No.1.QURAN.docx");
	        	
	            FileInputStream fis = new FileInputStream(file.getAbsolutePath());

	            XWPFDocument document = new XWPFDocument(fis);
	            List<XWPFParagraph> paragraphs = document.getParagraphs();

	            int num = 1;
	            String head = null;
	            String sub = null;
	            String arab =  null;
	            String trans = null;
	            String[] hadith=null;
	            String[] surah=null;
	            String refer=null;
	            String type=null;
	            System.out.println("Total no of paragraph in Docx : "+paragraphs.size());

                List<Record> records = new ArrayList<Record>();
	            for (XWPFParagraph para : paragraphs) {
	                XWPFStyle style = document.getStyles().getStyle(para.getStyleID());
	                //System.out.println(para.getText());
	                int pos = 0;
	                boolean first = true;
	                for (XWPFRun run : para.getRuns()) {
	                  System.out.println(para.getText());
	                  System.out.println("Current run IsBold : " + run.isBold());
	                  System.out.println("Current run IsItalic : " + run.isItalic());
	                  System.out.println("Current Font Size : " + run.getFontSize());
	                  System.out.println("Current Font Name : " + run.getFontName());
	                  if (first==true) {
	                	  if (run.getFontSize() == 22) {
	                		  head = para.getText();
	                	  }
	                	  else if (run.getFontSize() == 20) {
	                		  sub = para.getText();
	                	  }
	                	  else if ((run.getFontSize() == 18 || run.getFontSize() == 17 ) && run.getFontName().equalsIgnoreCase("Traditional Arabic")) {
	                		  if (trans != null) {
	                		  records.add(new Record(head, sub, arab, trans));}
	                		  arab=null;
	                		  trans=null;
	                		  arab = para.getText();
	                		  /*
	                		  hadith=arab.split("\\[");
	                		  System.out.println("Array Size" + hadith.length);
	                		  System.out.println(hadith[1]);
	                		  if(hadith.length>1) {
	                			  type="hadith";
	                			  refer=hadith[1]; //.replace("["," ");
	                			  System.out.println("Refer" + refer);
	                		   }
	                		  else {
	                			  surah=arab.split( "\\)" );
	                			  type="surah";
	                			  refer=surah[0].replace("("," ");
	                		  }
	                		  */
	                	  }
	                	  else if (run.getFontSize() == 18 && run.getFontName().equalsIgnoreCase("Jameel Noori Nastaleeq")) {
	                		  if (trans==null) {
	                			  trans=para.getText();
	                		  }
	                		  else {
	                		  trans = trans+ "****"+ para.getText();}
	                		  //System.out.print(refer +"  ");
	                		  //System.out.println(type + "   ");
	                		  //System.out.println(" ***********Record Entered *********" );
	                		  //trans=para.getText();
	                		  //records.add(new Record(head, sub, arab, trans));
	                	  }
	                  }
	                  first = false;
	                }
	              }
	            System.out.println("/////////////////////////////////////////////");
	            PrintWriter writer;
				writer = new PrintWriter("Records.tsv", "UTF-8");
			    for (Record rec: records) {
	            	System.out.println(rec.Display());
	            	writer.println(rec.Display());
	            }
			    writer.close();
	              fis.close();
	              	
	          
            } catch (Exception e) {
                e.printStackTrace();
            }

	}
}
