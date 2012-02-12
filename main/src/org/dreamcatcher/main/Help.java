/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "Help",
id = "org.dreamcatcher.main.Help")
@ActionRegistration(displayName = "#CTL_Help")
@ActionReferences({
    @ActionReference(path = "Menu/Help", position = 100, separatorAfter = 150),
    @ActionReference(path = "Shortcuts", name = "WINDOWS F1")
})
@Messages("CTL_Help=Help")
public final class Help implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "No helpset is available at the moment");
                                                              // TODO implement action body
    }
}
