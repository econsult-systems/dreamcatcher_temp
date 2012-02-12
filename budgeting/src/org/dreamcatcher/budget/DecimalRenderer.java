/**
 * 
 */
package org.dreamcatcher.budget;

import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author Gathoka
 *
 */
class DecimalRenderer extends DefaultTableCellRenderer {
	  DecimalFormat formatter;

	  DecimalRenderer(String pattern) {
	    this(new DecimalFormat(pattern));
	  }

	  DecimalRenderer(DecimalFormat formatter) {
	    this.formatter = formatter;
	    setHorizontalAlignment(JLabel.RIGHT);
	  }

	  public void setValue(Object value) {
	    setText((value == null) ? "" : formatter.format(((Double) value)
	        .doubleValue()));
	  }
	}