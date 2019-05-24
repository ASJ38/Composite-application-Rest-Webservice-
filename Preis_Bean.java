package Rest;

import java.io.Serializable;

public class Preis_Bean implements Serializable {
private String preispauschale;
private String KilometerGesamtPreis;

public Preis_Bean() {
}

public String getPreispauschale() {
	return preispauschale;
}

public void setPreispauschale(String preispauschale) {
	this.preispauschale = preispauschale;
}

public String getKilometerGesamtPreis() {
	return KilometerGesamtPreis;
}

public void setKilometerGesamtPreis(String kilometerGesamtPreis) {
	KilometerGesamtPreis = kilometerGesamtPreis;
}

 
}

