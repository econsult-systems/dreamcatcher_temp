/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.main;

import java.awt.Dimension;
import javax.swing.JSeparator;

/**
 *
 * @author Roy Rutto
 */
public class VerticalSeparator extends JSeparator {
 
    public VerticalSeparator() {
        super(JSeparator.VERTICAL);
    }
 
    @Override
    public Dimension getMaximumSize() {
        return new Dimension(getPreferredSize().width, super.getMaximumSize().height);
    }
 
    @Override
    public Dimension getSize() {
        return new Dimension(getPreferredSize().width, super.getSize().height);
    }
}
