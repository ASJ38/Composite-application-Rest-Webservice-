package Rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import com.sap.persistence.j.Customer;
import com.sap.persistence.j.Jc;
import com.sap.persistence.j.Jcus;
import meinp.TaxiImple;
import meinp.TaxiImpleService;

/**
 * Servlet implementation class Fahrten_Serv
 */
@WebServlet("/Fahrten_Serv")
public class Fahrten_Serv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Fahrten_Serv() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int Tag = Integer.parseInt(request.getParameter("Tag_der_Fahrt"));

		PrintWriter out = response.getWriter();

		out.println("Fahrten am: " + Tag + " Jahres");
String w‰hrung=" ";
		for (TagesFahrten_Bean tagesfahrt : combiList(Tag)) {
			w‰hrung ="EUR";
if(tagesfahrt.getCustomer().getCountry().equals("US Amerika")) {
	w‰hrung="USD umgerechnet";
}
else if(tagesfahrt.getCustomer().getCountry().equals("Russland")) {
	w‰hrung="RUB umgerechnet";
}
else if(tagesfahrt.getCustomer().getCountry().equals("Groﬂbritannien")) {
	w‰hrung="GBP umgerechnet";	
}
			out.println("<p>Fahrt des Kunden: " + tagesfahrt.getCustomer().getName() + " mit der Id: "
					+ tagesfahrt.getCustomer().getCusId() + "|| aus " + tagesfahrt.getCustomer().getAddress() + " "
					+ tagesfahrt.getCustomer().getCountry() + "|| Von: " + tagesfahrt.getTaxifahrten().getVon() + " nach "
					+ tagesfahrt.getTaxifahrten().getNach() + "|| Entfernung "
					+ tagesfahrt.getTaxifahrten().getEntfernung() + "Km|| " + tagesfahrt.getPPreis()+" Preise in: "+w‰hrung + "</p>");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	public static ArrayList<TagesFahrten_Bean> combiList(int tag) {
		TaxiImpleService Tservice = new TaxiImpleService();
		TaxiImple Tport = Tservice.getTaxiImplePort();
		Tport.filtern(tag);
		Jc Cservice = new Jc();
		Jcus Cport = Cservice.getJcusPort();
		ArrayList<TagesFahrten_Bean> tagesfahrten = new ArrayList<TagesFahrten_Bean>();
		List<Customer> CustomerListe = new ArrayList<Customer>();
		CustomerListe = Cport.findAllCUSTOMER();
	
		for (int i = 0; i < Tport.filtern(tag).size(); i++) {
			for (int j = 0; j < CustomerListe.size(); j++) {
				if (Tport.filtern(tag).get(i).getKundennummer() == CustomerListe.get(j).getCusId()) {
				tagesfahrten.add(new TagesFahrten_Bean(CustomerListe.get(j), Tport.filtern(tag).get(i), tag, "0"));
				}

				for (TagesFahrten_Bean tagesfahrt : tagesfahrten) {

					tagesfahrt.setPPreis(tagesfahrt.getPPreis());
				}
			}
		}
		return tagesfahrten;

	}
}
