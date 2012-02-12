/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.editorsuite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "Project",
id = "org.dreamcatcher.editorsuite.ViewScript")
@ActionRegistration(iconBase = "org/dreamcatcher/editorsuite/ViewScript.png",
displayName = "#CTL_ViewScript")
@ActionReferences({
    @ActionReference(path = "Menu/Project", position = 200),
    @ActionReference(path = "Toolbars/File", position = 350)
})
@Messages("CTL_ViewScript=Script")
public final class ViewScript implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
    }
}
