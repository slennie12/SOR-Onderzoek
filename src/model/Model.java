package model;

import java.util.ArrayList;

import controller.Controller;
import view.View;

/**
 * @author Carlo Geertse & Lennart van den Bout
 * The model
 */
public class Model {
	private int data;
	private ArrayList<View> views;
	private ArrayList<Controller> controllers;
	
	public Model()
	{
		data = 77;
		views = new ArrayList<View>();
		controllers = new ArrayList<Controller>();
	}
	
	public int getData()
	{
		return data;
	}
	
	public void setData(int data)
	{
		this.data=data;
	}
	
	public void attach(View view)
	{
		views.add(view);
	}
	
	public void attach(Controller controller)
	{
		controllers.add(controller);
	}
	
	/**
	 * @param args
	 * Main method that initializes the program
	 */
	public static void main(String[] args) {
		Model model = new Model();
		View view = new View(model);
		
	}

}
