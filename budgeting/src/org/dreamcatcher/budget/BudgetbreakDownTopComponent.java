package org.dreamcatcher.budget;

/**
 * 
 * got from package org.dreamcatcher.breakdown; Elements
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import writer.Breakdown;

public class BudgetbreakDownTopComponent extends JDialog{


    private JTextField itemNameTxt;
	private JTextArea descriptionTxtArea;
	private JTextField noOfUnitsTXT;
	private JTextField amountPerUnitTXT;
	private JButton doneButon;
	private JButton cancelButton;
        int counter = 15;
        DefaultListModel model;
    private JList list;
    private JLabel shootingDays;
    private JTextField totalTextField;
    /** Creates new form Elements */
    public BudgetbreakDownTopComponent(String selected) {
        super();
        setPreferredSize(new Dimension(400, 300));
        setMinimumSize(new Dimension(900, 600));
        setMaximumSize(new Dimension(900, 600));
        setModal(true);
        setContentPane(initComponents(selected));
        
        Toolkit toolkit =  Toolkit.getDefaultToolkit ();
        Dimension dim = toolkit.getScreenSize();
        
        setLocation(dim.width/5, dim.height/8);
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
    	itemsPane.add(calCulationArea(), BorderLayout.CENTER);
        itemsPane.add(budgetItems(),BorderLayout.WEST);
    	
		return itemsPane;
    }
    private JPanel calCulationArea(){
        JPanel calculationPanel = new JPanel();
        calculationPanel.setLayout(new BorderLayout());
        calculationPanel.add(detailsPanel(), BorderLayout.NORTH);
        calculationPanel.add(detailsTablelPanel(), BorderLayout.CENTER);
        return calculationPanel;
    }
    
    private JPanel detailsTablelPanel() {  
        
       JPanel returnPanel = new JPanel();
       returnPanel.setBorder(new TitledBorder(""));
       returnPanel.setLayout(new BorderLayout());
       final String columnNames[] = { "Scene No","Scene Title","Units Used","day","Time","Location", "Amount" };
       Object rowData[][] = { { "", "", "", "","","",""},{ "", "", "", "","","",""},{ "", "", "", "","","",""},
       { "", "", "", "","","",""},{ "", "", "", "","","",""},{ "", "", "", "","","",""}};
       
       JTable table = new JTable(rowData, columnNames);
       table.setSelectionForeground(Color.lightGray);
           table.setRowHeight(24);       
         JScrollPane scrollPane = new JScrollPane(table);
         
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
		
                returnPanel.add(scrollPane, BorderLayout.CENTER);
                
                JPanel buttonsPanel = new JPanel();
                buttonsPanel.setLayout(new GridLayout(5, 2));
                buttonsPanel.add(new JLabel(" "));
                buttonsPanel.add(new JLabel(" "));
                JLabel totalAmountLabel = new JLabel("Total amount");
                totalAmountLabel.setHorizontalTextPosition(JLabel.RIGHT);
                buttonsPanel.add(totalAmountLabel);
                totalTextField = new JTextField();
                //totalTextField.setBackground(Color.magenta);
                buttonsPanel.add(totalTextField);
                buttonsPanel.add(new JLabel(" "));
                buttonsPanel.add(new JLabel(" "));
                
		buttonsPanel.add(doneButon);
		buttonsPanel.add(cancelButton);
                
                returnPanel.add(buttonsPanel,BorderLayout.SOUTH);
                
        return returnPanel;
    }
    private JPanel detailsPanel() {
    	JPanel detailsPane = new JPanel();
    	detailsPane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        
    	
    	JLabel nameLabel = new JLabel("Name");
        //c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        detailsPane.add(nameLabel, c);
    	
		itemNameTxt = new JTextField(20);
                c.fill = GridBagConstraints.HORIZONTAL;
                c.weightx = 0.5;
                c.gridx = 1;
                c.gridy = 0;
                c.fill = GridBagConstraints.HORIZONTAL;
		detailsPane.add(itemNameTxt, c);
		
		JLabel descriptionLabel = new JLabel("Description");
                c.weightx = 0.5;
                c.gridx = 0;
                c.gridy = 1; 
                c.ipady = 40;      //make this component tall
                c.weightx = 0.0;
                //c.insets = new Insets(10,0,0,0);
                //c.weighty = 1.0;
                detailsPane.add(descriptionLabel,c);
		
		descriptionTxtArea = new JTextArea(7,20);
                descriptionTxtArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
                c.gridx = 1;
                c.gridy = 1;
                c.insets = new Insets(10,0,0,0);
                c.fill = GridBagConstraints.HORIZONTAL;
		detailsPane.add(descriptionTxtArea,c);		
		
               
                TitledBorder topBorder = BorderFactory.createTitledBorder("Budget Item");
                topBorder.setTitlePosition(TitledBorder.TOP);
       
                detailsPane.setBorder(topBorder); 
		
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

    private JPanel budgetItems() {
        JPanel budgetItemsPanel = new JPanel();
        budgetItemsPanel .setLayout(new BorderLayout());
        
        model = new DefaultListModel();; 
        list = new JList(model);
        JScrollPane pane = new JScrollPane(list);
        pane.setMinimumSize(new Dimension(300, 400));
        JButton addButton = new JButton("Add Element");
        addButton.setMaximumSize(new Dimension(50,30));
        JButton removeButton = new JButton("Remove Element");
        removeButton.setMaximumSize(new Dimension(50,30));
        
        //rrayList items = Breakdown.getTaggedItems("");
        for (int i = 0; i < 15; i++){
            model.addElement("Element " + i);
            
            
        }
        addButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {       
          Object newBudgetElement = JOptionPane.showInputDialog("Enter new Budget Element");
          if(newBudgetElement.toString().equalsIgnoreCase(null)){
             
          }else{
               model.addElement(newBudgetElement.toString());      
          }
         
      }
    });
        
        removeButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (model.getSize() > 0)
           if(list.getSelectedIndex()<0){
               JOptionPane.showMessageDialog(rootPane, "You have not selected any item to remove");
           }else{
               model.removeElementAt(list.getSelectedIndex());
           }
          
      }
    });
        
        budgetItemsPanel.add(pane, BorderLayout.NORTH);
    budgetItemsPanel.add(addButton, BorderLayout.WEST);
    budgetItemsPanel.add(removeButton, BorderLayout.EAST);
        
        return budgetItemsPanel;
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