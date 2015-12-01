package model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Observable;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

import controller.Controller;
import view.View;

/**
 * @author Carlo Geertse & Lennart van den Bout
 * The model
 */
public class DataModel extends Observable{
	int i;
	private Model RDFModel;
	private ArrayList<View> views;
	private ArrayList<Controller> controllers;
	
	public DataModel()
	{
		i=0;
		views = new ArrayList<View>();
		controllers = new ArrayList<Controller>();
		RDFModel = ModelFactory.createDefaultModel();
		readRDF();
	}
	
	public Model getModel()
	{
		return RDFModel;
	}
	
	public void setModel(Model model)
	{
		this.RDFModel=model;
	}
	
	public void attach(View view)
	{
		views.add(view);
	}
	
	public void attach(Controller controller)
	{
		controllers.add(controller);
	}
	
	public void performActions()
	{
		String givenName = "test";
		String surName = "piet" + i;
		Resource carloName = RDFModel.createResource()
            	.addProperty(VCARD.Given, givenName)
            	.addProperty(VCARD.Family, surName);
		setChanged();
		notifyObservers();
		i++;
	}
	
	public void readRDF()
	{
		String inputFileName = "lib/RDF-test.xml";
		// use the FileManager to find the input file
		InputStream in = FileManager.get().open(inputFileName);
		if (in == null) {
		    throw new IllegalArgumentException(
		                                 "File: " + inputFileName + " not found");
		}
		
		// read the RDF/XML file
		RDFModel.read(in, null);
		// write it to standard out
		RDFModel.write(System.out,"N-TRIPLES");
	}
	
	
	
	
	/**
	 * @param args
	 * Main method that initializes the program
	 */
	public static void main(String[] args) {
		DataModel model = new DataModel();
		View view = new View(model);
		model.addObserver(view);
	}

}
