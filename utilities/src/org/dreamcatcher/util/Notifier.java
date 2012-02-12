/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.util;

import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

/**
 *
 * @author slowcoach
 */
public class Notifier {

    private Notifier() {
    }

    public static Notifier getInstance() {
        return NotifierHolder.INSTANCE;
    }

    public void giveMessage(String message, int type) {
        NotifyDescriptor d = null;
        switch (type) {
            case 1:
                d = new NotifyDescriptor.Message(message, NotifyDescriptor.INFORMATION_MESSAGE);
                break;
            case 2:
                d = new NotifyDescriptor.Message(message, NotifyDescriptor.WARNING_MESSAGE);
                break;
            case 3:
                d = new NotifyDescriptor.Message(message, NotifyDescriptor.ERROR_MESSAGE);
                break;
            default:
                d = new NotifyDescriptor.Message(message, NotifyDescriptor.INFORMATION_MESSAGE);
                break;
        }
        DialogDisplayer.getDefault().notify(d);

    }

    private static class NotifierHolder {

        private static final Notifier INSTANCE = new Notifier();
    }
}
