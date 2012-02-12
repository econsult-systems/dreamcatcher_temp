/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.filesAPI;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import org.dreamcatcher.filesAPI.Projects.ProjectsManager;
import org.dreamcatcher.filesAPI.Projects.ProjectsManagerFactory;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.cookies.OpenCookie;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

// An example action demonstrating how the wizard could be called from within
// your code. You can move the code below wherever you need, or register an action:
@ActionID(category = "...", id = "org.dreamcatcher.filesAPI.NewProjectWizardAction")
@ActionRegistration(iconBase = "org/dreamcatcher/filesAPI/newproject.png",
displayName = "#CTL_NewProject")
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1300, separatorAfter = 1350),
    @ActionReference(path = "Toolbars/File", position = 300),
    @ActionReference(path = "Shortcuts", name = "D-N")
})
@Messages("CTL_NewProject=New Project")
public final class NewProjectWizardAction implements ActionListener {

    private WizardDescriptor.Panel[] panels;

    public @Override
    void actionPerformed(ActionEvent e) {
        WizardDescriptor wizardDescriptor = new WizardDescriptor(getPanels());
        // {0} will be replaced by WizardDesriptor.Panel.getComponent().getName()
        wizardDescriptor.setTitleFormat(new MessageFormat("{0}"));
        wizardDescriptor.setTitle("New Project Details ");
        Dialog dialog = DialogDisplayer.getDefault().createDialog(wizardDescriptor);
        dialog.setVisible(true);
        dialog.toFront();
        boolean cancelled = wizardDescriptor.getValue() != WizardDescriptor.FINISH_OPTION;
        if (!cancelled) {
            // do something
            Map<String, Object> props = wizardDescriptor.getProperties();
            //JOptionPane.showMessageDialog(null, props);
            ProjectsManager projectmanager = ProjectsManagerFactory.create(props);
            try {
                projectmanager.setProjectEnv();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
            } finally {
                try {
                    // JOptionPane.showMessageDialog(null, "Opening it in editor");

                    projectmanager.openInitialFile(projectmanager.Script, projectmanager.Scenes, projectmanager.ScenesList);

                } catch (DataObjectNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());

                }
            }
        }
    }

    /**
     * Initialize panels representing individual wizard's steps and sets
     * various properties for them influencing wizard appearance.
     */
    private WizardDescriptor.Panel[] getPanels() {
        if (panels == null) {
            panels = new WizardDescriptor.Panel[]{
                new NewProjectWizardPanel1(),
                new NewProjectWizardPanel2()
            };
            String[] steps = new String[panels.length];
            for (int i = 0; i < panels.length; i++) {
                Component c = panels[i].getComponent();
                // Default step name to component name of panel. Mainly useful
                // for getting the name of the target chooser to appear in the
                // list of steps.
                steps[i] = c.getName();
                if (c instanceof JComponent) { // assume Swing components
                    JComponent jc = (JComponent) c;
                    // Sets step number of a component
                    // TODO if using org.openide.dialogs >= 7.8, can use WizardDescriptor.PROP_*:
                    jc.putClientProperty("WizardPanel_contentSelectedIndex", new Integer(i));
                    // Sets steps names for a panel
                    jc.putClientProperty("WizardPanel_contentData", steps);
                    // Turn on subtitle creation on each step
                    jc.putClientProperty("WizardPanel_autoWizardStyle", Boolean.TRUE);
                    // Show steps on the left side with the image on the background
                    jc.putClientProperty("WizardPanel_contentDisplayed", Boolean.TRUE);
                    // Turn on numbering of all steps
                    jc.putClientProperty("WizardPanel_contentNumbered", Boolean.TRUE);
                }
            }
        }
        return panels;
    }
}
