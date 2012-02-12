/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.calender;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "Project",
id = "org.dreamcatcher.calender.ViewCalender")
@ActionRegistration(iconBase = "org/dreamcatcher/calender/calender.png",
displayName = "#CTL_ViewCalender")
@ActionReferences({
    @ActionReference(path = "Menu/Project", position = 275),
    @ActionReference(path = "Toolbars/File", position = 381)
})
@Messages("CTL_ViewCalender=Calender")
public final class ViewCalender implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
        CalenderTopComponent calenderTopComponent = new CalenderTopComponent();
        calenderTopComponent.open();
        calenderTopComponent.requestActive();
    }
}
