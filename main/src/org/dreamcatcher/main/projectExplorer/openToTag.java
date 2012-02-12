/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dreamcatcher.main.projectExplorer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.openide.loaders.DataObject;

import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(
    category="Edit",
    id="org.dreamcatcher.main.projectExplorer.openToTag"
)
@ActionRegistration(
    displayName="#CTL_openToTag"
)
@ActionReferences({
  @ActionReference(path="Loaders/text/drmextension/Actions", position=0)
})
@Messages("CTL_openToTag=Open to tag")
public final class openToTag implements ActionListener {
    private final DataObject context;

    public openToTag(DataObject context) {
        this.context = context;
    }

    public void actionPerformed(ActionEvent ev) {
        // TODO use context
    }
}
