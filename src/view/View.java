package view;

import controller.Controller;
import model.Model;

public class View {
	private Model model;
	
	public View(Model model)
	{
		this.model=model;
		model.attach(this);
		Controller controller = new Controller(model,this);
	}
	
}
