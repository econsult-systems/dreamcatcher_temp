/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.selection;

import java.util.Collection;
import java.util.Date;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Roy Rutto
 */
public class UpdateCombo {
   private Vector element=null;
   //private int amount=0;
    private DefaultComboBoxModel<String> model;


   public UpdateCombo(Vector componentStrings) {
   prepareModel(componentStrings);
   }

   public Vector getElement() {
      return element;
   }

    private void prepareModel(Vector element) {
        //Add stuff to the model

        DefaultComboBoxModel<String> jcombo = new DefaultComboBoxModel<String>(element) {

                                 /* @Override
                                  public void setSelectedItem(Object o) {
                                      throw new UnsupportedOperationException("Not supported yet.");
                                  }

                                  @Override
                                  public Object getSelectedItem() {
                                      throw new UnsupportedOperationException("Not supported yet.");
                                  }

                                  @Override
                                  public int getSize() {
                                      throw new UnsupportedOperationException("Not supported yet.");
                                  }

                                  @Override
                                  public String getElementAt(int i) {
                                      throw new UnsupportedOperationException("Not supported yet.");
                                  }

                                  @Override
                                  public void addListDataListener(ListDataListener ll) {
                                      throw new UnsupportedOperationException("Not supported yet.");
                                  }

                                  @Override
                                  public void removeListDataListener(ListDataListener ll) {
                                      throw new UnsupportedOperationException("Not supported yet.");
                                  }*/
                              };
        /*for(int i=0;i<element.size();i++)
        {
            //add the elements to the model
        jcombo.addElement(element.elementAt(i).toString());
        }*/ //not necessary
        model = jcombo;

    }
    public Object getModel()
    {
        return model;
    }

}
