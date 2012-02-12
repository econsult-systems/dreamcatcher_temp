/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.scheduling;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "Project",
id = "org.dreamcatcher.scheduling.ViewSchedule")
@ActionRegistration(iconBase = "org/dreamcatcher/scheduling/schedule.png",
displayName = "#CTL_ViewSchedule")
@ActionReferences({
    @ActionReference(path = "Menu/Project", position = 287),
    @ActionReference(path = "Toolbars/File", position = 387)
})
@Messages("CTL_ViewSchedule=Schedule")
public final class ViewSchedule implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
        ScheduleTopComponent scheduleTopComponent = new ScheduleTopComponent();
        scheduleTopComponent.open();
        scheduleTopComponent.requestActive();
    }
}
