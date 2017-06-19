/*package Phantom.TerminalAccess;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFTest {

	public static void main(String[] args) {
		//  Auto-generated method stub
		PDDocument doc = null;
		PDPage page = null;

		try {
			doc = new PDDocument();
			page = new PDPage();
			doc.addPage(page);
			PDFont font = PDType1Font.HELVETICA_BOLD;
			PDPageContentStream content = new PDPageContentStream(doc, page);
			
			
			content.beginText();
			content.setFont(font, 12);
			content.moveTextPositionByAmount(100, 700);
			content.drawString("hello");
			content.endText();
			
			content.beginText();
			content.setFont(font, 12);
			content.moveTextPositionByAmount(0, 0);
			content.drawString("aaaassssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssstttttttttttttttttttt");
			content.moveTextPositionByAmount(0, 1);
			content.drawString("bbbb");
			content.endText();
			
			
			content.close();
			doc.save("target\\pdfwithText.pdf");
			doc.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
*/