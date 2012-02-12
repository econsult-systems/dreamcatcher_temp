/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.main.projectExplorer;

import java.awt.Image;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.ImageUtilities;

/**
 *
 * @author slowcoach
 */
public class MainNode extends AbstractNode {
    
    /** Creates a new instance of RootNode */
    public MainNode(Children children) {
        super(children);
    }
    
    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("org/dreamcatcher/main/resources/mainnode.jpg");
    }
    
    @Override
    public Image getOpenedIcon(int type) {
        return ImageUtilities.loadImage("org/dreamcatcher/main/resources/openmain.png");
    }

    
}
