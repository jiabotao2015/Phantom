package Phantom.TerminalAccess;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFCreater {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try (PDDocument doc = new PDDocument()) {
			PDPage page = new PDPage(PDRectangle.A6);
			doc.addPage(page);

			// PDFont font = PDType1Font.HELVETICA_BOLD;
			PDFont font = PDType0Font.load(doc, new File("font.ttf"));

			try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
				contents.beginText();
				contents.setFont(font, 12);
				contents.setLeading(12 * 1.2);
				contents.newLineAtOffset(50,50);
				contents.showText("你好sss");
				contents.newLine();
				contents.showText("哈哈");
				contents.endText();
			}

			doc.save("ads.pdf");

		}
	}
}
