package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controller;
import model.Model;

public class View implements Observer{
	private Model model;
	private JButton button;
	
	public View(Model model)
	{
		this.model=model;
		model.attach(this);
		initGUI();
		Controller controller = new Controller(model,this);
		model.addObserver(controller);
	}
	
	private void initGUI()
	{
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		button = new JButton();
		panel.add(button);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	public JButton getButton()
	{
		return this.button;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("hij update dingen" + model.getData());		
	}
	
	
}
