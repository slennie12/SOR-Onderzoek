package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.DataModel;

public class View implements Observer{
	private DataModel model;
	private JButton button;
	private JFrame frame;
	private JPanel panel;
	private JTable table;
	
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
	public void setTable(String tableData){
		
		
		//String formattedData = tableData.replaceAll("\\<.*\\>.", "");
		String formattedData = tableData.replaceAll("\\>", "\\>\n");
		String splittedTableData[] = formattedData.split("\\n");
	//	String strings[];
		int i = 0;
		ArrayList<List> lists = new ArrayList<List>();
		for(splittedTableData.length < 0)
			
		}
		String[] colNames = {"Subject", "Predicate","Object"};
		panel.add(new JTextField(tableData));
		DefaultTableModel model = new DefaultTableModel(colNames, 4);
		JTable table = new JTable(model); //kan data niet uit outputstream trekken, is nodig voor aanmaken table
		panel.add(table);
		frame.pack();
		frame.repaint();
		
		
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
/*		String syntax = "N-TRIPLE"; // also try "N-TRIPLE" and "TURTLE"
		StringWriter out = new StringWriter();
		model.getModel().write(out,syntax);
		String result = out.toString();
		System.out.println("hij update dingen" + result);*/
		setTable(model.writeRDFToView());
		//System.out.println(result);
	}
	
	
}