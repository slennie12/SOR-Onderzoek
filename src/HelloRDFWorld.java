
/**
 * @author Carlo Geertse
 * A class to test Apache Jena code
 */
public class HelloRDFWorld {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Model m = ModelFactory.createDefaultModel();
		String NS = "http://example.com/test/";
		Resource r = m.createResource(NS + "r");
		Property p = m.createProperty(NS + "p");
		
		r.addProperty(p, "hello world", XSDDatatype.XSDstring);
		
		m.write(System.out, "Turtle");
	}

}
