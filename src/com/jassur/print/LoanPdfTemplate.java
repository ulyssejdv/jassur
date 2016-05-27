package com.jassur.print;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.jassur.model.Address;
import com.jassur.model.Client;
import com.jassur.model.Loan;

public class LoanPdfTemplate {

	public static void createPDF(String pathToPDF, Client c, Loan l) {
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
		
		HtmlNode pLastName = new HtmlNode("<p>","</p>","Nom : "+c.getLastName());
		body.addNode(pLastName);
		
		HtmlNode pFirstName = new HtmlNode("<p>","</p>","Prenom : "+c.getFirstName());
		body.addNode(pFirstName);
		
		Address a = c.getAddress();
		HtmlNode pAddress = new HtmlNode("<p>","</p>","Adresse : "+a.getStreet()+" "+a.getCity()+" "+a.getZip()+" "+a.getCountry());
		body.addNode(pAddress);
		
		HtmlNode tblParams = new HtmlNode("<table>","</table>");
		
		HtmlNode tr = new HtmlNode("<tr>","</tr>");
		HtmlNode td1 = new HtmlNode("<td>","</td>", "Montant : "+l.getAmount()+" euros");
		tr.addNode(td1);
		HtmlNode td2 = new HtmlNode("<td>","</td>", "Dur&eacute;e : "+l.getTotalDuration()+" mois");
		tr.addNode(td2);
		tblParams.addNode(tr);
		
		tr = new HtmlNode("<tr>","</tr>");
		td1 = new HtmlNode("<td>","</td>", "Taux d'int&eacute;r&ecirc;t : "+l.getRates().get(0).getInterestRate()+"%");
		tr.addNode(td1);
		td2 = new HtmlNode("<td>","</td>", "Assurance : "+l.getInsurance()+"%");
		tr.addNode(td2);
		tblParams.addNode(tr);
		
		tr = new HtmlNode("<tr>","</tr>");
		td1 = new HtmlNode("<td>","</td>", "Mensualit&eacute; hors assurance : "+l.getRates().get(0).getMonthlyPayment()+" euros");
		tr.addNode(td1);
		td2 = new HtmlNode("<td>","</td>", "Mensualit&eacute; avec assurance : 0 euros");
		tr.addNode(td2);
		tblParams.addNode(tr);
		
		tr = new HtmlNode("<tr>","</tr>");
		td1 = new HtmlNode("<td>","</td>", "Co&ucirc;t Total : "+l.getTotalAmount()+" euros");
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
		
		HtmlNode tdl7 = new HtmlNode("<td>","</td>", "Capital amorti");
		trLoan.addNode(tdl7);
		
		HtmlNode tdl4 = new HtmlNode("<td>","</td>", "Assurance");
		trLoan.addNode(tdl4);
		
		HtmlNode tdl5 = new HtmlNode("<td>","</td>", "Mensualite");
		trLoan.addNode(tdl5);
		
		HtmlNode tdl6 = new HtmlNode("<td>","</td>", "Capital restant du");
		trLoan.addNode(tdl6);
		
		tblLoan.addNode(trLoan);
		
		
		String pattern = "0.00";
	    DecimalFormat decimalFormat = new DecimalFormat(pattern);
	    
	    SimpleDateFormat formater = null;
        formater = new SimpleDateFormat("MM/yy");
        DateFormat df = DateFormat.getDateInstance();
        
        Date date = null;
		try {
			date = formater.parse("01/01/2016");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    
	    
	    int totalDuration = l.getTotalDuration();
	    double interest = 0;
	    double insurance = l.insurancePerMonth();
	    double remainingCapital = l.getAmount();
	    double monthlyPayment = l.getRates().get(0).getMonthlyPayment();
	    
    	for (int i = 1; i <= totalDuration; i++) {
	    	
	    	// calculation for next round
	    	remainingCapital = l.remainingCapital(remainingCapital,interest,monthlyPayment);
	    	interest = l.interestPerMonth(remainingCapital);
	    	double principal = l.principal(monthlyPayment, interest);
	    	date = addMonth(date, 1);
	    	
	    	trLoan = new HtmlNode("<tr>","</tr>");
	    	
			tdl1 = new HtmlNode("<td>","</td>", String.valueOf(i));
			trLoan.addNode(tdl1);
			
			tdl2 = new HtmlNode("<td>","</td>", formater.format(date));
			trLoan.addNode(tdl2);
			
			tdl3 = new HtmlNode("<td>","</td>", decimalFormat.format((double)interest));
			trLoan.addNode(tdl3);
			
			tdl3 = new HtmlNode("<td>","</td>", decimalFormat.format((double)principal));
			trLoan.addNode(tdl3);
			
			tdl4 = new HtmlNode("<td>","</td>", String.valueOf(insurance));
			trLoan.addNode(tdl4);
			
			tdl5 = new HtmlNode("<td>","</td>", String.valueOf(monthlyPayment));
			trLoan.addNode(tdl5);
			
			tdl6 = new HtmlNode("<td>","</td>", decimalFormat.format((double)remainingCapital));
			trLoan.addNode(tdl6);
			
			tblLoan.addNode(trLoan);
	    	
	    }
		
		
		body.addNode(tblLoan);
		
		html.addNode(body);
	
		
		String fullHTML = html.toHTML();
		try {
		    OutputStream file = new FileOutputStream(new File(pathToPDF+"monpdf.pdf"));
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
	
	public static Date addMonth(Date date, int nbMonth) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, nbMonth);
		return cal.getTime(); 
	}
}
