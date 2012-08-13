package org.dreamcatcher.budget;

/**
 * 
 * got from package org.dreamcatcher.breakdown; Elements
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.dreamcatcher.util.Utility;
import org.openide.util.Exceptions;
import writer.Breakdown;
import writer.ProjectSettingsException;

public class BudgetbreakDownTopComponent extends JDialog{

     public static final String[] columnNames = { "Scene No","Scene Title","Units Used","day","Time","Location", "Amount" };
public  BreakDownForBudget tableModel;
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
       //final String columnNames[] = { "Scene No","Scene Title","Units Used","day","Time","Location", "Amount" };
       
       
       
   
       tableModel = new BreakDownForBudget(columnNames);
       //tableModel.addTableModelListener(new InteractiveForm.InteractiveTableModelListener());
       
       JTable table = new JTable();
       table = new JTable(){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void editingStopped(ChangeEvent e) {
                try {
					super.editingStopped(e);
					//calculateRowValue(getModel());
					tableModel.calculateTotals(getModel());
					repaint();
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
              }
            };
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
        table.setRowSorter(sorter);
        //String [] col ={"Name","Category","Scenes"};
        
        
        table.setModel(tableModel);
        table.setSurrendersFocusOnKeystroke(true);
        if (!tableModel.hasEmptyRow()) {
            tableModel.addEmptyRow(table.getSelectedRow());
        }
        

        //scroller = new javax.swing.JScrollPane(table);
        //table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));
        
        TableColumn hidden = table.getColumnModel().getColumn(0);
        //hidden.setMinWidth(20);
        //hidden.setPreferredWidth(20);
        ///hidden.setMaxWidth(20);
        
        TableColumn col2 = table.getColumnModel().getColumn(1);
        //col2.setMinWidth(20);
        //col2.setPreferredWidth(20);
        //col2.setMaxWidth(20);

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
        
        model = new DefaultListModel();
        list = new JList(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent evt) {
                if (evt.getValueIsAdjusting()) {
                    return;
                }
                //System.out.println("Selected from " + evt.getLastIndex());
                //list.getSelectedValue();
                populateDetailsTable(evt.getLastIndex());
            }

            
        });
        JScrollPane pane = new JScrollPane(list);
        pane.setMinimumSize(new Dimension(300, 400));
        JButton addButton = new JButton("Add Element");
        addButton.setMaximumSize(new Dimension(50,30));
        JButton removeButton = new JButton("Remove Element");
        removeButton.setMaximumSize(new Dimension(50,30));
        try {
            //rrayList items = Breakdown.getTaggedItems("");
            for (int i = 0; i < Breakdown.getTaggedItems("Actor").size(); i++){
                try{
                     model.addElement(Utility.budgetItems(2,Breakdown.getTaggedItems("Actor"))[0].toString());
                }catch(Exception e){
                    e.printStackTrace();
                }
               
            }
        } catch (ProjectSettingsException ex) {
            Exceptions.printStackTrace(ex);
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
    
    public void populateDetailsTable(int lastIndex) {
                itemNameTxt.setText("eterter");
                descriptionTxtArea.setText("erterter");
                
                //add row to the table
                
                
                
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