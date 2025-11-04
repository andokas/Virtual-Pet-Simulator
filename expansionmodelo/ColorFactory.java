package expansionmodelo;

public class ColorFactory {
	private static ColorFactory miColorFactory = new ColorFactory();
	
	private ColorFactory() {}
	
	public static ColorFactory getColorFactory() {
		return miColorFactory;
	}
	
	public Color crearColor(String color) {
		Color c = null;
		if (color.equals("rojo")) {
			c = new Rojo();
		}
		if (color.equals("negro")) {
			c = new Negro();
		}
		return c;
	}
}
