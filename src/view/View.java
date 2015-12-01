package view;

import java.io.StringWriter;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import controller.Controller;
import model.DataModel;

public class View implements Observer{
	private DataModel model;
	private JButton button;
	private JFrame frame;
	private JPanel panel;
	
	public View(DataModel model)
	{
		this.model=model;
		model.attach(this);
		initGUI();
		Controller controller = new Controller(model,this);
		model.addObserver(controller);
	}
	
	private void initGUI()
	{
		frame = new JFrame();
		panel = new JPanel();
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
	
	/**
	 * Returns the frame for the 
	 * 
	 * TODO Moet data uit output halen. 
	 * @return
	 */
	public void setTable(){
		

		JTable table = new JTable(2,2); //kan data niet uit outputstream trekken, is nodig voor aanmaken table
		panel.add(table);
	
		frame.repaint();
		
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		String syntax = "N-TRIPLE"; // also try "N-TRIPLE" and "TURTLE"
		StringWriter out = new StringWriter();
		model.getModel().write(out,syntax);
		String result = out.toString();
		System.out.println("hij update dingen" + result);
		setTable();
		//System.out.println(result);
	}
	
	
}