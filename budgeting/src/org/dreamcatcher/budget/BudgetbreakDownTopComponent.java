package org.dreamcatcher.budget;

/**
 * 
 * got from package org.dreamcatcher.breakdown; Elements
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class BudgetbreakDownTopComponent extends JDialog{


    private JTextField itemNameTxt;
	private JTextArea descriptionTxtArea;
	private JTextField noOfUnitsTXT;
	private JTextField amountPerUnitTXT;
	private JButton doneButon;
	private JButton cancelButton;
    /** Creates new form Elements */
    public BudgetbreakDownTopComponent(String selected) {
        super();
        setPreferredSize(new Dimension(200, 300));
        setModal(true);
        setContentPane(initComponents(selected));
        setLocation(100, 100);
        setVisible(true);
        
        
        
    }
    private JPanel initComponents(String selected) {

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel header = new JLabel();
		header.setText(selected);
		panel.add(header,BorderLayout.NORTH);
		panel.add(dialogRootPanel(),BorderLayout.CENTER);
		return panel;
    }
    
     public JPanel dialogRootPanel(){
    	
    	JPanel itemsPane = new JPanel();
    	itemsPane.setLayout(new BorderLayout());
    	itemsPane.add(new JToolBar("selected"), BorderLayout.NORTH);
    	itemsPane.add(detailsPanel(), BorderLayout.CENTER);
    	
		return itemsPane;
    }
    private JPanel detailsPanel() {
    	JPanel detailsPane = new JPanel();
    	detailsPane.setLayout(new GridLayout(6, 2));
    	
    	detailsPane.add(new JLabel("Name"));
    	
		itemNameTxt = new JTextField();
		detailsPane.add(itemNameTxt);
		
		detailsPane.add(new JLabel("Description"));
		
		descriptionTxtArea = new JTextArea(10,7);
		detailsPane.add(descriptionTxtArea);
		
		detailsPane.add(new JLabel("Units Used"));
		
		noOfUnitsTXT = new JTextField("0");
		detailsPane.add(noOfUnitsTXT);
		
		detailsPane.add(new JLabel("Amount Per Units"));
		
		amountPerUnitTXT = new JTextField("0");
		detailsPane.add(amountPerUnitTXT);
		
		doneButon = new JButton("Done");
		doneButon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				writeToJTable();
				
			}
		});
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
                                    itemNameTxt.setText("");
				descriptionTxtArea.setText("");
				noOfUnitsTXT.setText("0");
				amountPerUnitTXT.setText("0");
                                }catch(Exception e){
                                    
                                }
                                
				
			}
		});
		
		detailsPane.add(doneButon);
		detailsPane.add(cancelButton);		
		
		return detailsPane;
	}
	protected void writeToJTable() {
		try{
                    String name = itemNameTxt.getText();
                    String description = descriptionTxtArea.getText();
                    int noOfUnits = Integer.parseInt(noOfUnitsTXT.getText());
                    double amountPerUnit = Double.parseDouble(amountPerUnitTXT.getText());
                    double totalAmount = noOfUnits*amountPerUnit;
                
                
	InteractiveForm form = new InteractiveForm();
		form.tableModel.addRowWithItems(form.table.getSelectedRow(),name,description,noOfUnits,amountPerUnit, totalAmount );
			
		}catch(Exception e){
                    e.printStackTrace();
                }
	}
}
    
//	public JTable castTable(){
//    	
//    	Vector<Object> rowOne = new Vector<Object>();
//        rowOne.addElement("Row1-Column1");
//        rowOne.addElement("Row1-Column2");
//        rowOne.addElement("Row1-Column3");
//        rowOne.addElement("");
//        
//        
//        Vector<Object> rowTwo = new Vector<Object>();
//        rowTwo.addElement("Row2-Column1");
//        rowTwo.addElement("Row2-Column2");
//        rowTwo.addElement("Row2-Column3");
//        rowTwo.addElement("");
//        
//        Vector<Vector> rowData = new Vector<Vector>();
//        rowData.addElement(rowOne);
//        rowData.addElement(rowTwo);
//        
//        Vector<String> columnNames = new Vector<String>();
//        columnNames.addElement("Column One");
//        columnNames.addElement("Column Two");
//        columnNames.addElement("Column Three");
//        columnNames.addElement("ACTION");
//        
//        JTable table = new JTable(rowData, columnNames);
//
//        table.setValueAt("aa", 0, 0);
//        return table;
//    }