/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.scheduling;

import java.text.SimpleDateFormat;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import writer.Breakdown;
import java.util.ArrayList;
import java.util.Calendar;
import writer.ProjectSettingsException;
import writer.TaggedItem;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//org.dreamcatcher.scheduling//Schedule//EN",
autostore = false)
@TopComponent.Description(preferredID = "ScheduleTopComponent",
iconBase = "org/dreamcatcher/scheduling/schedule.png",
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.dreamcatcher.scheduling.ScheduleTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ScheduleAction",
preferredID = "ScheduleTopComponent")
public final class ScheduleTopComponent extends TopComponent {
    private boolean DEBUG = false;
    ArrayList CastMembers;

    public ScheduleTopComponent(){
        initComponents();
        setName(NbBundle.getMessage(ScheduleTopComponent.class, "CTL_ScheduleTopComponent"));
        setToolTipText(NbBundle.getMessage(ScheduleTopComponent.class, "HINT_ScheduleTopComponent"));


        PageableEditorKit page = new PageableEditorKit();
        page.setPageWidth(900);//595
        page.setPageHeight(842);
        jTextPane1.setEditorKit(page);
         try {
             CastMembers=Breakdown.getTaggedItems("ACTOR");
        } catch (ProjectSettingsException ex) {
            Exceptions.printStackTrace(ex);
        }
        createTable();


    }



    private JScrollPane getTableComponent(){

         JTable table = new JTable(tablemodel());
        return new JScrollPane(table);
    }

    private void createTable(){

        StyledDocument doc = jTextPane1.getStyledDocument();
        Style style = doc.addStyle("table", null);
        StyleConstants.setComponent(style, getTableComponent());

        try {
            doc.insertString(doc.getLength(), "Table", doc.getStyle("table"));
         }
        catch (BadLocationException ex) {
        }
    }
String dateNow[]={"1","2","3","4","5","6","7"};
public void getdates(){
     
    Calendar currentDate = Calendar.getInstance();
    SimpleDateFormat formatter= new SimpleDateFormat("MM/dd");
    for(int i =0; i<7; i++){
   
    dateNow[i]=formatter.format(currentDate.getTime());
    currentDate.add(Calendar.DATE, 1);
    }
    
} 




  public TableModel tablemodel(){
      
      getdates();
      TableModel catalog = null;

        String [] columnNames={ "Month/Day", dateNow[0],dateNow[1],dateNow[2],dateNow[3],dateNow[4],dateNow[5],dateNow[6],};
        
        
        

        String[][] data = null;
        TaggedItem dataItem;
        try {
             CastMembers=Breakdown.getTaggedItems("ACTOR");
        } catch (ProjectSettingsException ex) {
            Exceptions.printStackTrace(ex);
        }
        finally
        {
            data = new String[ CastMembers.size()+2][8];
            for(int i=0;i< (CastMembers.size()+2);i++)
            {
                
                
                    if(i==0){
                        data[i][0]="Day of Week";
                        data[i][1]="Mon";
                        data[i][2]="Tue";
                        data[i][3]="WEd";
                        data[i][4]="Thu";
                        data[i][5]="Fri";
                        data[i][6]="Sat";
                        data[i][7]="Sun";
                        }
                    
                    if(i==1){data[i][0]="Shooting Day";
                    
                    data[i][1]="1";
                    data[i][2]="2";
                    data[i][3]="3";
                    data[i][4]="4";
                    data[i][5]="5";
                    data[i][6]="6";
                    data[i][7]="7";
                    }
                if(i>=2){
                    dataItem = (TaggedItem)  CastMembers.get(i-2);
                    data[i][0]=dataItem.getItemName();
                    data[i][1]=null;
                    data[i][2]=null;
                    data[i][3]=null;
                    data[i][4]=null;
                    data[i][5]=null;
                    data[i][6]=null;
                    data[i][7]=null;
                }
            }
             catalog=new DefaultTableModel(data, columnNames);
           
  }  return catalog;
  
  }





    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        jToolBar1.setRollover(true);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/dreamcatcher/scheduling/print.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(ScheduleTopComponent.class, "ScheduleTopComponent.jButton2.text")); // NOI18N
        jToolBar1.add(jButton2);
        jToolBar1.add(jSeparator1);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/dreamcatcher/scheduling/schedule.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ScheduleTopComponent.class, "ScheduleTopComponent.jLabel1.text")); // NOI18N
        jToolBar1.add(jLabel1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Actor (Cast Members)", "Animals", "Cameras", "Music", "Sound", "Makeups", "adLabor", "Vehicles", "SspecialEffects", "Props", "Security", "Wardrobe", "Animal Wranglers", "Art Departments", "Greenerys", "Special Equipment", "Mechanica lEffects", "Misc", "Dress", "Stunt" }));
        jToolBar1.add(jComboBox1);
        jToolBar1.add(jSeparator3);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/dreamcatcher/scheduling/calender.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(ScheduleTopComponent.class, "ScheduleTopComponent.jButton1.text")); // NOI18N
        jToolBar1.add(jButton1);

        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JToolBar jToolBar1;
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
}
