/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.budget;

import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;



import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/*import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;*/

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//org.dreamcatcher.budget//budget//EN",
autostore = false)
@TopComponent.Description(preferredID = "budgetTopComponent",
iconBase = "org/dreamcatcher/budget/budget.png",
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.dreamcatcher.budget.budgetTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_budgetAction",
preferredID = "budgetTopComponent")
public class budgetTopComponent extends TopComponent {


    // Instance attributes used in this example
    public static final String[] columnNames = {
        "  ", "","In Use", "Name","Description","Calculation","Units","Total"
    };
    protected JTable table;
    protected JScrollPane scroller;
    protected BreakDownForBudget tableModel;
    private JTextField searchTextField;
    private ArrayList<String> globalLabels;

    public budgetTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(budgetTopComponent.class, "CTL_budgetTopComponent"));
        //setToolTipText(NbBundle.getMessage(budgetTopComponent.class, "HINT_budgetTopComponent"));
        putClientProperty(TopComponent.PROP_MAXIMIZATION_DISABLED, Boolean.TRUE);
        setSize(300, 200);
        setBackground(Color.gray);
        
        //topPanel.setLayout( new BorderLayout() );
		
		//define scrool panes
		//topPanel.add(iconsToolBar(),BorderLayout.NORTH);
		//topPanel.add( upDownSplit(), BorderLayout.CENTER );
        //topPanel.add(new InteractiveForm());
        
         initComponent();
       
            }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        topPanel = new javax.swing.JPanel();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(budgetTopComponent.class, "budgetTopComponent.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 161, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(139, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
    
    
    public void initComponent() {
        tableModel = new BreakDownForBudget(columnNames);
        tableModel.addTableModelListener(new budgetTopComponent.InteractiveTableModelListener());
        
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
        
        
//        hidden.setCellRenderer(new InteractiveRenderer(BreakDownForBudget.ARTIST_INDEX));
        
        

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
					tableModel.addEmptyRow(row);
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
                if ((budgetTopComponent.this.tableModel.getRowCount() - 1) == row &&
                   !budgetTopComponent.this.tableModel.hasEmptyRow())
                {
                    budgetTopComponent.this.tableModel.addEmptyRow(table.getSelectedRow());
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

   
	     
	      
}
