/**
 * 
 */
package org.dreamcatcher.budget;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import org.openide.util.Exceptions;
import writer.ProjectSettingsException;
import writer.TaggedItem;
import writer.XMLManager;

/**
 * @author Gathoka
 *
 */

public class BreakDownForBudget extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public static final int TITLE_INDEX = 0;
    public static final int ARTIST_INDEX = 1;
    //public static final int ALBUM_INDEX = 2;
    //public static final int HIDDEN_INDEX = 3;
    public static final int INUSE_INDEX = 2;
    public static final int NAME_INDEX = 3;
    public static final int DESCRIPTION_INDEX = 4;
    public static final int UNIT_INDEX = 5;
    public static final int CALCLULATION_INDEX = 6;
    public static final int VALUES_INDEX = 7;
    

    protected String[] columnNames;
    protected Vector dataVector;
    final DecimalFormat formatter;
    
    public BreakDownForBudget(String[] columnNames) {
        this.columnNames = columnNames;
        dataVector = new Vector();
        formatter = new DecimalFormat("###,##0.00");
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }
    public boolean isCellEditable(int row, int column) {
        if (column == 0 || column == 1) return false;
        else return true;
    }

    public Class getColumnClass(int column) {
        switch (column) {
            case TITLE_INDEX:
            case ARTIST_INDEX:
            //case ALBUM_INDEX:
               return String.class;
            case VALUES_INDEX:
            	return Number.class;
            case NAME_INDEX:// = 5;
            	return String.class;
            default:
               return Object.class;
        }
    }

    public Object getValueAt(int row, int column) {
        StandardBudget record = (StandardBudget)dataVector.get(row);
        switch (column) {
            case TITLE_INDEX:
               return record.getTitle();
            case ARTIST_INDEX:
               return record.getArtist();
//            case ALBUM_INDEX:
//               return record.getAlbum();
            case INUSE_INDEX:
                return record.getInUse();
            case NAME_INDEX:
                return record.getName();
            case DESCRIPTION_INDEX:
                return record.getDescription();
            case UNIT_INDEX:
                return record.getUnits();
            case CALCLULATION_INDEX:
                return record.getCalculation();
            case VALUES_INDEX:
                return record.getValues();
            default:
               return new Object();
        }
    }

    public void setValueAt(Object value, int row, int column) {
        StandardBudget record = (StandardBudget)dataVector.get(row);
        switch (column) {
            case TITLE_INDEX:
               record.setTitle((String)value);
               break;
            case ARTIST_INDEX:
               record.setArtist((String)value);
               break;
//            case ALBUM_INDEX:
//               record.setAlbum((String)value);
//               break;
            case INUSE_INDEX:
            	record.setInUse((String)value);
                break;
            case NAME_INDEX:
            	record.setName((String)value);
                break;
            case DESCRIPTION_INDEX:
            	//record.setDescription((String)value);
            	Double d = null;
                if (value instanceof Double) {
                  d = (Double) value;
                } else {
                  try {
                    d = new Double(((Number) formatter
                        .parse((String) value)).doubleValue());
                  } catch (ParseException ex) {
                    d = new Double(0.0);
                  }
                }
                //rowVector.setElementAt(d, col);
                record.setDescription(d.toString());
                break;
            case UNIT_INDEX:
            	record.setUnits((String)value);
                break;
            case CALCLULATION_INDEX:
            	record.setCalculation((String)value);
                break;
            case VALUES_INDEX:
            	//record.setValues((String)value);
                Double d2 = null;
                if (value instanceof Double) {
                  d2 = (Double) value;
                } else {
                  try {
                    d2 = new Double(((Number) formatter
                        .parse((String) value)).doubleValue());
                  } catch (ParseException ex) {
                    d2 = new Double(0.0);
                  }
                }
                //rowVector.setElementAt(d, col);
                record.setValues(d2.toString());
                break;
            default:
               System.out.println("invalid index");
        }
        fireTableCellUpdated(row, column);
    }

    public int getRowCount() {
        return dataVector.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public boolean hasEmptyRow() {
        if (dataVector.size() == 0) return false;
        StandardBudget standardBudgetItem = (StandardBudget)dataVector.get(dataVector.size() - 1);
        if (standardBudgetItem.getTitle().trim().equals("") &&
           standardBudgetItem.getArtist().trim().equals("") &&
           ((String) standardBudgetItem.getAlbum()).trim().equals("")&&
           standardBudgetItem.getInUse().trim().equals("")&&
           standardBudgetItem.getUnits().trim().equals("")&&
           standardBudgetItem.getValues().trim().equals("")&&
           standardBudgetItem.getName().trim().equals("")&&
           standardBudgetItem.getDescription().trim().equals("")&&
           standardBudgetItem.getCalculation().trim().equals(""))
        {
           return true;
        }
        else return false;
    }
public void addRowWithItems(int g, String name, String description, int noOfUnits, double amountPerUnit, double totalValue){
    	dataVector.add(g+1, new StandardBudget(name,description,noOfUnits,amountPerUnit, totalValue));
        fireTableRowsInserted(
           dataVector.size() - 1,
           dataVector.size() - 1);
    }
    
    public void populateFromScript(int g){
        
        try {
            //.setModel(Utility.arrayListToTable(columns,Breakdown.getTaggedItems(String.valueOf(jComboBox1.getSelectedItem()))));
            ArrayList scriptItems = XMLManager.getInstance().getAllTaggedElements();
            for(int i=0;i<scriptItems.size();i++){
                //System.out.println(scriptItems.get(i).toString());
               TaggedItem ti=((TaggedItem)scriptItems.get(i));
               System.out.println(ti.getItemName().toString());
               dataVector.add(g+1, new StandardBudget(ti.getItemName().toString(),ti.getOtherDesc().toString(),ti.getScenes().toString()));
        fireTableRowsInserted(
           dataVector.size() - 1,
           dataVector.size() - 1);
            }
         
        } catch (ProjectSettingsException ex) {
            Exceptions.printStackTrace(ex);
        }
    	
    }
    
    public void addEmptyRow(int g) {
        dataVector.add(g+1, new StandardBudget());
        fireTableRowsInserted(
           dataVector.size() - 1,
           dataVector.size() - 1);
    }
    
    public void removeOneRow(int row){
    	dataVector.remove(row);
        fireTableRowsDeleted(row,row);
    }
    
//    /calculate rows
    public void calculateTotals(TableModel ml) {
        if (ml == null)
          return;
        double total = 0.0;
        for (int i = 0; i < getRowCount(); i++) {
          total += Double.parseDouble((String) ( ml.getValueAt(i, getColumnCount()-1)));
        }
        //addEmptyRow(getRowCount());
      
        ml.setValueAt(new Double(total), getRowCount()-1, getColumnCount()-1);
      }
    
    
}
