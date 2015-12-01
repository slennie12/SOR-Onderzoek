package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import model.DataModel;
import view.View;

public class Controller implements Observer{
	private DataModel model;
	private View view;
	
	public Controller(DataModel model, View view)
	{
		this.model=model;
		this.view=view;
		model.attach(this);
		view.getButton().addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
            	System.out.println("Hij registreert de click");
                model.performActions();   
            }
        });
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("hij update dingen, wel zonder data verwerking test in de controller");		
	}
}
