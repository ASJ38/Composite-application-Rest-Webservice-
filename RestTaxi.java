package Rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Preis")
public class RestTaxi {
	@GET
	@Path("{entfernung}/{country}")
	@Produces("application/json")
	public Response getPauschal(@PathParam("entfernung") int entfernung, @PathParam("country") String country) {

		double PauPreis = 0;
		if (entfernung < 20) {
			PauPreis = 8;
		} else {
			PauPreis = 4.11;
		}

		double PreisProKM = 0.63;
		double PreisKM = 0;
		if (entfernung < 30) {
			PreisProKM = 1.07;
		}
		PreisKM = PreisProKM * entfernung;
		if (country.equals("US Amerika")) {
			PauPreis = PauPreis * 1.18;
			PreisKM = PreisKM * 1.18;
		} else if (country.equals("Russland")) {
			PauPreis = PauPreis * 73.97;
			PreisKM = PreisKM * 73.97;
		} else if (country.equals("Großbritannien")) {
			PauPreis = PauPreis * 0.88;
			PreisKM = PreisKM * 0.88;
		}
		String kmString = String.valueOf(PreisKM);
		String PauString = String.valueOf(PauPreis);
		Preis_Bean preis = new Preis_Bean();
		preis.setPreispauschale(PauString);
		preis.setKilometerGesamtPreis(kmString);

		return Response.status(Response.Status.OK).entity(preis).build();

	}

}
