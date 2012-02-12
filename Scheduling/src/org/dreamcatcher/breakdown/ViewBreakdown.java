/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.breakdown;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "Project",
id = "org.dreamcatcher.breakdown.ViewBreakdown")
@ActionRegistration(iconBase = "org/dreamcatcher/breakdown/breakdown.png",
displayName = "#CTL_ViewBreakdown")
@ActionReferences({
    @ActionReference(path = "Menu/Project", position = 250),
    @ActionReference(path = "Toolbars/File", position = 375)
})
@Messages("CTL_ViewBreakdown=Breakdown Sheet")
public final class ViewBreakdown implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
        BreakdownTopComponent breakdownTopComponent = new  BreakdownTopComponent();
        breakdownTopComponent.open();
        breakdownTopComponent.requestActive();
    }
}
