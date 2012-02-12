/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.budget;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Gathoka
 */
public class BugdetDetailsTableView extends JTable{
    //Object[][] data;
    
    public BugdetDetailsTableView(){        
   
    Object[][] data = { { "A", 5 }, { "B", 2 }, { "C", 4 }, { "D", 8 } };
    String columnNames[] = { "Item", "Value" };
    
    TableModel model = new DefaultTableModel(data, columnNames) {
      public Class<?> getColumnClass(int column) {
        return getValueAt(0, column).getClass();
      }
    };
    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
    setRowSorter(sorter);
    }    
}
