package ps.system.internationalization;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class Language {

	public static Locale POLISH = new Locale.Builder().setLanguage("pl").setRegion("PL").build();
	public static Locale SPANISH = new Locale.Builder().setLanguage("es").build();
	public static Locale ROMANIAN = new Locale.Builder().setLanguage("ro").setRegion("RO").build();
	
	private Locale[] languagesSupported = { Locale.ENGLISH, Locale.FRENCH, POLISH, SPANISH, ROMANIAN };

	public Locale[] getLanguagesSupported() {
		return languagesSupported;
	}
	
	private ResourceBundle rb;
	
	public ResourceBundle getLanguageBundle() {
		return rb;
	}

	public Language() {
		Locale.setDefault(Locale.ENGLISH);
		loadLocale(Locale.getDefault());
	}
	
	public void loadLocale(Locale requestedLocale) {
		ResourceBundle.clearCache();
		rb = ResourceBundle.getBundle("LanguageBundle", requestedLocale);
		enumerateKeys();
	}

	public void enumerateKeys() {
		Enumeration<String> bundleKeys = rb.getKeys();

		while (bundleKeys.hasMoreElements()) {
		    String key = (String)bundleKeys.nextElement();
		    String value = rb.getString(key);
		    System.out.println("key = " + key + ", " + "value = " + value);
		}
	}
}