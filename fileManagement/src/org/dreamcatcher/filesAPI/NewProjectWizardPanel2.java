/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.filesAPI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;

public class NewProjectWizardPanel2 implements WizardDescriptor.Panel<WizardDescriptor>, PropertyChangeListener {

    private NewProjectVisualPanel2 view = null;
    private WizardDescriptor model = null;
    private boolean isValid = false;
    private ResourceBundle bundle = NbBundle.getBundle(NewProjectWizardPanel2.class);

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    public NewProjectVisualPanel2 getComponent() {
        if (view == null) {
            view = new NewProjectVisualPanel2();
            view.putClientProperty(
                    WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, new Integer(0));
            view.putClientProperty(
                    WizardDescriptor.PROP_AUTO_WIZARD_STYLE, Boolean.TRUE);
            view.putClientProperty(
                    WizardDescriptor.PROP_CONTENT_DISPLAYED, Boolean.TRUE);
            view.putClientProperty(
                    WizardDescriptor.PROP_CONTENT_NUMBERED, Boolean.TRUE);
        }
        return view;
    }

    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return HelpCtx.DEFAULT_HELP;
        // If you have context help:
        // return new HelpCtx(SampleWizardPanel1.class);
    }

    public boolean isValid() {
        // If it is always OK to press Next or Finish, then:
        return true;
        // If it depends on some condition (form filled out...), then:
        // return someCondition();
        // and when this condition changes (last form field filled in...) then:
        // fireChangeEvent();
        // and uncomment the complicated stuff below.
    }

    
    private final Set<ChangeListener> listeners = new HashSet<ChangeListener>(1); // or can use ChangeSupport in NB 6.0
    public final void addChangeListener(ChangeListener l) {
    synchronized (listeners) {
    listeners.add(l);
    }
    }
    public final void removeChangeListener(ChangeListener l) {
    synchronized (listeners) {
    listeners.remove(l);
    }
    }
    protected final void fireChangeEvent() {
    Iterator<ChangeListener> it;
    synchronized (listeners) {
    it = new HashSet<ChangeListener>(listeners).iterator();
    }
    ChangeEvent ev = new ChangeEvent(this);
    while (it.hasNext()) {
    it.next().stateChanged(ev);
    }
    }
     

    // You can use a settings object to keep track of state. Normally the
    // settings object will be the WizardDescriptor, so you can use
    // WizardDescriptor.getProperty & putProperty to store information entered
    // by the user.
    public void readSettings(WizardDescriptor model) {
        this.model = model;
        getComponent().addPropertyChangeListener(this);

    }

    public void storeSettings(WizardDescriptor model) {
        model.putProperty(NewProjectVisualPanel2.PROP_PROJECT_SCRIPT_PATH,
                getComponent().getProjectScript());


    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        boolean oldState = isValid;
        isValid = checkValidity();
        fireChangeEvent(this, oldState, isValid);

    }

    private void setMessage(String message) {
        model.putProperty(WizardDescriptor.PROP_ERROR_MESSAGE, message);
    }

    private boolean checkValidity() {
        return true;
    }

    protected final void fireChangeEvent(
            Object source, boolean oldState, boolean newState) {
        if (oldState != newState) {
            Iterator<ChangeListener> it;
            synchronized (listeners) {
                it = new HashSet<ChangeListener>(listeners).iterator();
            }
            ChangeEvent ev = new ChangeEvent(source);
            while (it.hasNext()) {
                it.next().stateChanged(ev);
            }
        }
    }
    
}
