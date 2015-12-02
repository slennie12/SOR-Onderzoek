package view;

import java.awt.Dimension;
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
		frame = new JFrame("Jena SOR-Onderzoek");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(700,500));
		button = new JButton("Klik voor ophalen data");
		panel.add(button);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JButton getButton()
	{
		return this.button;
	}
	
	/**
	 * Enorm lelijke methode waar veel te moeilijk over nagedacht is 
	 * en waarschijnlijk in twee regels code kan worden opgelost
	 * 
	 * Haal een erg lange String binnen, wordt gesplit en in een ArrayList gezet die
	 * vervolgens in een ArrayList wordt gezet
	 * 
	 * Vervolgens wordt er in een foreach loop rijen toegevoegd aan de tabel
	 * (Nadat natuurlijk de ArrayList weer terug gezet is naar een Array,
	 *  anders snapt JTable het niet)
	 *  
	 *  Dit resultaat is dan ook wanneer er te weinig tijd in wordt gestoken
	 * 
	 * TODO Fixen waarom colom namen niet wordt weergegeven
	 * TODO Code veel kleiner maken, onnodig groot
	 */
	public void setTable(String tableData){
		
		
		//String formattedData = tableData.replaceAll("\\<.*\\>.", "");
		String formattedData = tableData.replaceAll("\\>", "\\>\n");
		String splittedTableData[] = formattedData.split("\\n");
	//	String strings[];
		int i = 0;
		ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();
		ArrayList<String> data = new ArrayList<String>();;
		for(String hoi : splittedTableData){
			data.add(hoi);
			i++;
			if(i == 3){
				lists.add(data);
				data = new ArrayList<String>();
				i = 0;
				System.out.println(i);
			}
			
		}
		System.out.println(lists);
		String[] coloms = new String[]{"Subject", "Predicate","Object"};
		
		DefaultTableModel tableModel = new DefaultTableModel(coloms, 0);
		for(ArrayList<String> o: lists){
			tableModel.addRow(o.toArray());
		}
		
		JTable table = new JTable(tableModel);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setPreferredSize(new Dimension(700,500));

		//for(splittedTableData.length() < 0)
		//	
		//}
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