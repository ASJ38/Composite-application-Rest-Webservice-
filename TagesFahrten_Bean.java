package Rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sap.persistence.j.Customer;
import com.sap.persistence.j.FindAllCUSTOMER;
import com.sap.persistence.j.FindCUSTOMERByCusId;
import com.sap.persistence.j.Jc;
import com.sap.persistence.j.Jcus;

import meinp.TaxiImple;
import meinp.TaxiImpleService;
import meinp.Taxifahrten;

public class TagesFahrten_Bean implements Serializable {

	private int Tag_der_Fahrt;
	private String PPreis;
	private String Kilometerpreis;
	private Customer customer;
	private Taxifahrten taxifahrten;
	private TagesFahrten_Bean tagesFahrtenBean;

	public TagesFahrten_Bean() {

	}

	public TagesFahrten_Bean getTagesFahrtenBean() {
		return tagesFahrtenBean;
	}

	public void setTagesFahrtenBean(TagesFahrten_Bean tagesFahrtenBean) {
		this.tagesFahrtenBean = tagesFahrtenBean;
	}

	public int getTag_der_Fahrt() {
		return Tag_der_Fahrt;
	}

	public TagesFahrten_Bean(Customer customer, Taxifahrten taxifahrten, int tag_der_Fahrt, String preis) {

		Tag_der_Fahrt = tag_der_Fahrt;
		PPreis = preis;
		this.customer = customer;
		this.taxifahrten = taxifahrten;
	}

//hier wird mein Client benutzt
	private String berechneFahrt() {

		String myBasePath = "http://localhost:8205/Finale-Abgabe/jaxrs/";
		String myResourcePath = "Preis/"+ this.getTaxifahrten().getEntfernung()+ "/"
				+ this.getCustomer().getCountry();
		Client client = ClientBuilder.newClient();
		WebTarget webtarget = client.target(myBasePath + myResourcePath);
		Invocation.Builder invoB = webtarget.request(MediaType.APPLICATION_JSON);
		Response response = invoB.get();

		if (response.hasEntity()) {
			String preis = response.readEntity(String.class);

			return preis;
		}

		return "888";
	}

	public int tag() {
		return Tag_der_Fahrt;
	}

	public void setTag_der_Fahrt(int tag_der_Fahrt) {
		Tag_der_Fahrt = tag_der_Fahrt;
	}

	public String getPPreis() {

		return berechneFahrt();
	}

	public void setPPreis(String pPreis) {
		PPreis = pPreis;
	}

	public String getKilometerpreis() {
		return Kilometerpreis;
	}

	public void setKilometerpreis(String kilometerpreis) {
		Kilometerpreis = kilometerpreis;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Taxifahrten getTaxifahrten() {
		return taxifahrten;
	}

	public void setTaxifahrten(Taxifahrten taxifahrten) {
		this.taxifahrten = taxifahrten;
	}

}
