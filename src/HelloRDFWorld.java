import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

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
