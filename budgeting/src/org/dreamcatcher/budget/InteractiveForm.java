/**
 * 
 */
package org.dreamcatcher.budget;
import org.dreamcatcher.util.Utility;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.lang.model.util.Elements;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import org.openide.util.Exceptions;
import writer.Breakdown;
import writer.ProjectSettingsException;
import writer.XMLManager;


/**
 * @author Gathoka
 *
 */
public class InteractiveForm extends JPanel {
	public static final String[] columnNames = {
        "  ", "","In Use", "Name","Description","Calculation","Units","Total"
    };

    public  JTable table;
    protected JScrollPane scroller;
     public  InteractiveTableModel tableModel;

	private JTextField searchTextField;

	private ArrayList<String> globalLabels;

    public InteractiveForm() {
        initComponent();
    }

    public void initComponent() {
        tableModel = new InteractiveTableModel(columnNames);
        tableModel.addTableModelListener(new InteractiveForm.InteractiveTableModelListener());
        
        table = new JTable(){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void editingStopped(ChangeEvent e) {
                try {
					super.editingStopped(e);
					calculateRowValue(getModel());
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
        

        scroller = new javax.swing.JScrollPane(table);
        table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));
        
        TableColumn hidden = table.getColumnModel().getColumn(0);
        hidden.setMinWidth(20);
        hidden.setPreferredWidth(20);
        hidden.setMaxWidth(20);
        
        TableColumn col2 = table.getColumnModel().getColumn(1);
        col2.setMinWidth(20);
        col2.setPreferredWidth(20);
        col2.setMaxWidth(20);

        setLayout(new BorderLayout());
        
        //tool tip panel
        JToolBar toolbars = new JToolBar();
        
        //Globals sorts
        globalLabels =  new ArrayList<String>();
        globalLabels.add("All");
        globalLabels.add("Cast");
        globalLabels.add("crew");
        globalLabels.add("Production");
    
        String lables = globalLabels.toString();
        String []labels = lables.split(",");
        //JDK 7 needed for fot JCombobox parameter
        JComboBox sortMenu = new JComboBox(labels);
        sortMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				
				JComboBox cb = (JComboBox)event.getSource();
		        String selection = (String)cb.getSelectedItem();
				
		        //JOptionPane.showMessageDialog(null, selection);
		        shorterTableView(selection);
			}
		});
        
        toolbars.add(sortMenu);
        
        JButton addButton = new JButton("add row");
        addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
		          
				int totalRows= tableModel.getRowCount();
				//new InteractiveRenderer(row);
				if(table.getSelectedRow()+1==tableModel.getRowCount()&&tableModel.getRowCount()!=1 ){
//					//new InteractiveRenderer(row);
					JOptionPane.showMessageDialog(null, "Select the row above");
			}else{
//					//new InteractiveRenderer(row);
					tableModel.populateFromScript(row);//addEmptyRow(row);
				}
				
		       
				
			}
		});
        toolbars.add(addButton);
        
        JButton deleteRowBTN = new JButton("Delete row");
        deleteRowBTN.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();  
                if (row < 0) {  
                    JOptionPane.showMessageDialog(null, "Select a row to delete", "Error", JOptionPane.ERROR_MESSAGE);  
                }  
                else {  
                	tableModel.removeOneRow(row);
                }
			}
		});
        toolbars.add(deleteRowBTN);
        
        JButton printBTN = new JButton("Print budget");
        printBTN.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
			          //MessageFormat headerFormat = new MessageFormat("{0}");
			         // MessageFormat footerFormat = new MessageFormat("- {0} -");
			          table.print(JTable.PrintMode.FIT_WIDTH);
			          //table.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
			        } catch (PrinterException pe) {
			          System.err.println("Error printing: " + pe.getMessage());
			        }
				
			}
		});
        toolbars.add(printBTN);
        
        JButton exportToExcelBTN = new JButton("Export to excel");
        exportToExcelBTN.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent evt) {

          try {
           ExcelExporter exp = new ExcelExporter();
           exp.exportTable(table, new File("C://Users/Gathoka/Desktop/budget.xls"));
          } catch (IOException ex) {
           System.out.println(ex.getMessage());
           ex.printStackTrace();

          }
         }
        });
        
        toolbars.add(exportToExcelBTN);
        
        
        //search area
        searchTextField = new JTextField("Search");
        toolbars.add(searchTextField);
        
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				search();
				
			}
		});
        toolbars.add(searchButton);
        
        add(toolbars,BorderLayout.NORTH);
        add(scroller, BorderLayout.CENTER);
    }

    //this method update the table depending on the item selected
    protected void shorterTableView(String selection) {

		//JOptionPane.showMessageDialog(null, selection);
    	new BudgetbreakDownTopComponent(selection);		
	}

	public void highlightLastRow(int row) {
        int lastrow = tableModel.getRowCount();
        if (row == lastrow - 1) {
            table.setRowSelectionInterval(lastrow - 1, lastrow - 1);
        } else {
            table.setRowSelectionInterval(row + 1, row + 1);
        }

        table.setColumnSelectionInterval(0, 0);
    }

    class InteractiveRenderer extends DefaultTableCellRenderer {
        protected int interactiveColumn;

        public InteractiveRenderer(int interactiveColumn) {
            this.interactiveColumn = interactiveColumn;
        }

        public Component getTableCellRendererComponent(JTable table,
           Object value, boolean isSelected, boolean hasFocus, int row,
           int column)
        {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (column == interactiveColumn && hasFocus) {
                if ((InteractiveForm.this.tableModel.getRowCount() - 1) == row &&
                   !InteractiveForm.this.tableModel.hasEmptyRow())
                {
                    InteractiveForm.this.tableModel.addEmptyRow(table.getSelectedRow());
                }

                highlightLastRow(row);
            }

            return c;
        }
    }

    public class InteractiveTableModelListener implements TableModelListener {
        public void tableChanged(TableModelEvent evt) {
            if (evt.getType() == TableModelEvent.UPDATE) {
                int column = evt.getColumn();
                int row = evt.getFirstRow();
                System.out.println("row: " + row + " column: " + column);
                table.setColumnSelectionInterval(column + 1, column + 1);
                table.setRowSelectionInterval(row, row);
                
            }
        }
    }
    
    
   // returns highlighted row
    class ColoredTableCellRenderer extends DefaultTableCellRenderer
{
    public Component getTableCellRendererComponent
        (JTable table, Object value, boolean selected, boolean focused, int row, int column)
    {
        setEnabled(table == null || table.isEnabled()); 

        if (row==table.getSelectedRow()){
            setBackground(Color.green);
        }
        else{
            setBackground(null);
        }
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);

        return this;
    }
}
     
    public void search(){
    	 String SearchText = searchTextField.getText();
           System.out.println(SearchText);
                 
    			if(!SearchText.trim().equalsIgnoreCase("")){
    			
    			for(int row = 0; row < table.getRowCount(); row++)
    			for(int col = 0; col < table.getColumnCount(); col++){
    			   Object next = table.getValueAt(row, col);
    			if(next.equals(SearchText)){
    				showSearchResults(row, col);
    				
    				return;
    				
    				}
    				
    		}
       	}
     		else{
     			JOptionPane.showMessageDialog(null, "Empty search not allowed", "Error", JOptionPane.ERROR_MESSAGE);
    			}
     }
     
    public void showSearchResults(int row, int col)
    {
    	 Rectangle r = table.getCellRect(row, col, false);
    	 table.scrollRectToVisible(r);
    	 table.repaint();
    } 
     
    public void calculateRowValue(TableModel ml){
     	if (ml == null)
             return;
           double total = 0.0;
           //for (int i = 0; i < getRowCount(); i++) {
           //System.out.println(table.getColumnCount()-3);
           //System.out.println(table.getColumnCount()-6);
           
           
           double rowvalue = 0;
		double timesvalue = 0;
		try {
			
			String nullRowValue = (String) ml.getValueAt(table.getSelectedRow(), 
			  		 table.getColumnCount()-2);
			if (nullRowValue.equalsIgnoreCase(null)){
				rowvalue = 1;
			}else {
				rowvalue = Double.parseDouble((String) ml.getValueAt(table.getSelectedRow(), 
				  		 table.getColumnCount()-3));
			}
			
			String nullTimesValue = (String) ml.getValueAt(table.getSelectedRow(), 
					 table.getColumnCount()-6);
			if(nullTimesValue.equalsIgnoreCase(null)){
				timesvalue = 1;
			}else{
				timesvalue = Double.parseDouble((String) ml.getValueAt(table.getSelectedRow(), 
					 table.getColumnCount()-6));
			}

			   
			   total =  rowvalue*timesvalue;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.print(e.getLocalizedMessage());
			total =  0;
		}
           
             
           //}
           ml.setValueAt(new Double(total), 
        		   table.getSelectedRow(), 
        		   table.getColumnCount()-1);
     }

//    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//            JFrame frame = new JFrame("Project Name budget");
//            frame.addWindowListener(new WindowAdapter() {
//                public void windowClosing(WindowEvent evt) {
//                    System.exit(0);
//                }
//            });
//            frame.getContentPane().add(new InteractiveForm());
//            frame.pack();
//            frame.setVisible(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
