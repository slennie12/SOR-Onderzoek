import java.io.InputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

/**
 * @author Carlo Geertse
 *
 */
public class JenaTutorialRoundTwo {

	// some definitions
	static String personURI = "http://somewhere/CarloGeertse";
	static String givenName = "Carlo";
	static String surName = "Geertse";
	static String fullName = givenName + " " + surName;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//rdfWriteTest();
		rdfReadTest();
	}
	
	public static void rdfWriteTest()
	{
		// create an empty Model
		Model model = ModelFactory.createDefaultModel();
		
		// create the resource
		Resource carloName = model.createResource()
                	.addProperty(VCARD.Given, givenName)
                	.addProperty(VCARD.Family, surName);
		Resource carloGeertse =
				model.createResource(personURI)
					.addProperty(VCARD.FN, fullName)
					.addProperty(VCARD.N, carloName);
		
		
		//Printing RDF using a statement iterator
		/*StmtIterator iter = model.listStatements();
		while(iter.hasNext())
		{
			Statement stmt      = iter.nextStatement();  // get next statement
		    Resource  subject   = stmt.getSubject();     // get the subject
		    Property  predicate = stmt.getPredicate();   // get the predicate
		    RDFNode   object    = stmt.getObject();      // get the object

		    System.out.print(subject.toString());
		    System.out.print(", " + predicate.toString() + ", ");
		    if (object instanceof Resource) {
		       System.out.print(object.toString());
		    } else {
		        // object is a literal
		        System.out.print(" \"" + object.toString() + "\"");
		    }

		    System.out.println(" .");
		}*/
		
		model.write(System.out, "N-TRIPLES");
	}
	
	public static void rdfReadTest()
	{
		String inputFileName = "lib/RDF-test.xml";
		// create an empty model
		Model model = ModelFactory.createDefaultModel();
		// use the FileManager to find the input file
		InputStream in = FileManager.get().open(inputFileName);
		if (in == null) {
		    throw new IllegalArgumentException(
		                                 "File: " + inputFileName + " not found");
		}
		
		// read the RDF/XML file
		model.read(in, null);
		// write it to standard out
		model.write(System.out);
	}

}
