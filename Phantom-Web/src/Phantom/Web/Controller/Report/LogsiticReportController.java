package Phantom.Web.Controller.Report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipOutputStream;

//import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
//import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogsiticReportController {
	
	@RequestMapping(value = "/Logistic")
	public String MainMapUrl(){
		return "VehicleMonitor/Logistic";
	}
	
	@RequestMapping(value = "/Logistic/export")
	public void report(HttpServletResponse response, HttpServletRequest request) throws IOException{
		response.setContentType("application/x-download");
		/*String value = new String("返程货物跟单.pdf".getBytes("gb2312"), "iso8859-1");*/
		response.setHeader("Content-Disposition", "attachment; filename="+"xxx.pdf");
		OutputStream output = response.getOutputStream();
		
		try (PDDocument doc = new PDDocument()) {
			PDPage page = new PDPage(PDRectangle.A6);
			doc.addPage(page);

			PDFont font = PDType1Font.HELVETICA_BOLD;
			//PDFont font = PDType0Font.load(doc, new File("font.ttf"));

			try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
				contents.beginText();
				contents.setFont(font, 12);
				contents.setLeading(12 * 1.2);
				contents.newLineAtOffset(50,50);
				contents.showText("aaa");
				contents.newLine();
				contents.showText("bbb");
				contents.endText();
			}
			/*ByteArrayOutputStream pdfoutput=new ByteArrayOutputStream();*/
			//FileOutputStream fos = new FileOutputStream();
			

			doc.save(output);
			/*ZipOutputStream zipOut = new ZipOutputStream(pdfoutput);
			zipOut.putNextEntry(new ZipEntry("wuliu.zip"));
			//zipOut.write(pdfoutput);
			//zipOut.
			
			parse(pdfoutput);*/
		}
		
		
	}

	public ByteArrayInputStream parse(ByteArrayOutputStream pdfoutput) {
		//  Auto-generated method stub
		ByteArrayOutputStream   baos=new   ByteArrayOutputStream();
        baos=(ByteArrayOutputStream) pdfoutput;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream;
		
	}

}
