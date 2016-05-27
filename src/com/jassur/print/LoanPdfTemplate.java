package com.jassur.print;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class LoanPdfTemplate {

	public static void createPDF(String pathToPDF) {
		HtmlNode html = new HtmlNode("<html>","</html>");
		
		HtmlNode head = new HtmlNode("<head>","</head>");
		
		HtmlNode meta = new HtmlNode("<meta charset=\"UTF-8\">","</meta>");
		head.addNode(meta);
		
		String styleCont = "table { border-collapse: collapse; } table, th, td { border: 1px solid black; }";
		HtmlNode style = new HtmlNode("<style type=\"text/css\" media=\"screen\" >","</style>", styleCont);
		head.addNode(style);
		html.addNode(head);
		
		HtmlNode body = new HtmlNode("<body>","</body>");
		
		/* Params part */
		
		HtmlNode h1Title = new HtmlNode("<h1>","</h1>","Restitution d'un pr&ecirc;t");
		body.addNode(h1Title);
		
		HtmlNode pLastName = new HtmlNode("<p>","</p>","Nom : ");
		body.addNode(pLastName);
		
		HtmlNode pFirstName = new HtmlNode("<p>","</p>","Prenom : ");
		body.addNode(pFirstName);
		
		HtmlNode pAddress = new HtmlNode("<p>","</p>","Adresse : ");
		body.addNode(pAddress);
		
		HtmlNode tblParams = new HtmlNode("<table>","</table>");
		
		HtmlNode tr = new HtmlNode("<tr>","</tr>");
		HtmlNode td1 = new HtmlNode("<td>","</td>", "Montant : 5000 euros");
		tr.addNode(td1);
		HtmlNode td2 = new HtmlNode("<td>","</td>", "Dur&eacute;e : 24 mois");
		tr.addNode(td2);
		tblParams.addNode(tr);
		
		tr = new HtmlNode("<tr>","</tr>");
		td1 = new HtmlNode("<td>","</td>", "Taux d'int&eacute;r&ecirc;t : 1.90%");
		tr.addNode(td1);
		td2 = new HtmlNode("<td>","</td>", "Assurance : 0.35%");
		tr.addNode(td2);
		tblParams.addNode(tr);
		
		tr = new HtmlNode("<tr>","</tr>");
		td1 = new HtmlNode("<td>","</td>", "Mensualit&eacute; hors assurance : 212.5 euros");
		tr.addNode(td1);
		td2 = new HtmlNode("<td>","</td>", "Mensualit&eacute; avec assurance : 212.5 euros");
		tr.addNode(td2);
		tblParams.addNode(tr);
		
		tr = new HtmlNode("<tr>","</tr>");
		td1 = new HtmlNode("<td>","</td>", "Co&ucirc;t Total : 5098 euros");
		tr.addNode(td1);
		td2 = new HtmlNode("<td>","</td>", "Autre : ");
		tr.addNode(td2);
		tblParams.addNode(tr);
		
		body.addNode(tblParams);
		
		/* Loan Table Part */
		
		HtmlNode tblLoan = new HtmlNode("<table>","</table>");
		
		HtmlNode trLoan = new HtmlNode("<tr>","</tr>");
		HtmlNode tdl1 = new HtmlNode("<td>","</td>", "Rang");
		trLoan.addNode(tdl1);
		HtmlNode tdl2 = new HtmlNode("<td>","</td>", "Date");
		trLoan.addNode(tdl2);
		HtmlNode tdl3 = new HtmlNode("<td>","</td>", "Interet");
		trLoan.addNode(tdl3);
		HtmlNode tdl4 = new HtmlNode("<td>","</td>", "Mensualite");
		trLoan.addNode(tdl4);
		HtmlNode tdl5 = new HtmlNode("<td>","</td>", "Assurance");
		trLoan.addNode(tdl5);
		HtmlNode tdl6 = new HtmlNode("<td>","</td>", "Capital restant du");
		trLoan.addNode(tdl6);
		tblLoan.addNode(trLoan);
		
		for (int i = 0; i < 46; i++) {
			trLoan = new HtmlNode("<tr>","</tr>");
			tdl1 = new HtmlNode("<td>","</td>", "X");
			trLoan.addNode(tdl1);
			tdl2 = new HtmlNode("<td>","</td>", "X");
			trLoan.addNode(tdl2);
			tdl3 = new HtmlNode("<td>","</td>", "X");
			trLoan.addNode(tdl3);
			tdl4 = new HtmlNode("<td>","</td>", "X");
			trLoan.addNode(tdl4);
			tdl5 = new HtmlNode("<td>","</td>", "X");
			trLoan.addNode(tdl5);
			tdl6 = new HtmlNode("<td>","</td>", "X");
			tblLoan.addNode(trLoan);
		}
		
		body.addNode(tblLoan);
		
		html.addNode(body);
	
		
		String fullHTML = html.toHTML();
		try {
		    OutputStream file = new FileOutputStream(new File(pathToPDF+"\\monpdf.pdf"));
		    Document document = new Document();
		    PdfWriter writer = PdfWriter.getInstance(document, file);
		    document.open();
		    InputStream is = new ByteArrayInputStream(fullHTML.getBytes());
		    XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
		    document.close();
		    file.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
}
