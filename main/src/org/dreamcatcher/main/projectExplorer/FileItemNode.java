/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.main.projectExplorer;

import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.io.IOException;
import javax.swing.Action;
import javax.swing.JOptionPane;
import org.openide.actions.CopyAction;
import org.openide.actions.PrintAction;
import org.openide.actions.CutAction;
import org.openide.actions.DeleteAction;
import org.openide.actions.OpenAction;
import org.openide.cookies.OpenCookie;
import org.openide.filesystems.FileObject;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.nodes.NodeTransfer;
import org.openide.util.actions.SystemAction;
import org.openide.util.datatransfer.PasteType;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author slowcoach
 */
public class FileItemNode extends AbstractNode implements OpenCookie{

    private String FILE_ITEM_NODE_ICON = "org/netbeans/myfirstexplorer/marilyn.gif";
    private FileObject file;

    /** Creates a new instance of InstrumentNode */
    public FileItemNode(FileObject key) {
        super(Children.LEAF, Lookups.forPath(key.getPath()));
        this.file = key;
        setDisplayName(key.getName());
        setIconBaseWithExtension(FILE_ITEM_NODE_ICON);
    }
    public PasteType getDropType(Transferable t, final int action, int index) {
        final Node dropNode = NodeTransfer.node( t, 
                DnDConstants.ACTION_COPY_OR_MOVE+NodeTransfer.CLIPBOARD_CUT );
        if( null != dropNode ) {
            final FileItem fileItem = (FileItem)dropNode.getLookup().lookup( FileItem.class );
            if( null != fileItem  && !this.equals( dropNode.getParentNode() )) {
                return new PasteType() {
                    
                    public Transferable paste() throws IOException {
                        getChildren().add( new Node[] { new FileItemNode(file) } );
                        if( (action & DnDConstants.ACTION_MOVE) != 0 ) {
                            dropNode.getParentNode().getChildren().remove( new Node[] {dropNode} );
                        }
                        return null;
                    }
                };
            }
        }
        return null;
    }

    public boolean canCut() {

        return true;
    }

    public boolean canDestroy() {
        return true;
    }

    public boolean canPrint() {
        return true;
    }

    public Action[] getActions(boolean popup) {
        return new Action[]{
                    SystemAction.get(OpenAction.class),
                    SystemAction.get(CutAction.class),
                    SystemAction.get(CopyAction.class),
                    SystemAction.get(PrintAction.class),
                    null,
                    SystemAction.get(DeleteAction.class)};
    }

    @Override
    public void open() {
        JOptionPane.showMessageDialog(null, "You are now opening this file");
    }

    
}
