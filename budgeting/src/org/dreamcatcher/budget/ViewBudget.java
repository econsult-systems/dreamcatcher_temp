/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.budget;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "File",
id = "org.dreamcatcher.budget.budgetTopComponentMenu")
@ActionRegistration(displayName = "#CTL_budgetTopComponentMenu"
        ,iconBase = "org/dreamcatcher/budget/budget.png")
@ActionReferences({
    @ActionReference(path = "Menu/Project", position = 300, separatorAfter = 350),
    @ActionReference(path = "Toolbars/File", position = 400)
})
@Messages("CTL_budgetTopComponentMenu=Budget")
public final class ViewBudget implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
        budgetTopComponent budgetTopComponent = new budgetTopComponent();
        budgetTopComponent.open();
        budgetTopComponent.requestActive();
    }
}
