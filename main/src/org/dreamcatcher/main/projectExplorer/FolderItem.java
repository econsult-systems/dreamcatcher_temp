/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.main.projectExplorer;


import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.io.IOException;
import java.util.List;
import javax.swing.Action;
import org.openide.actions.DeleteAction;
import org.openide.actions.NewAction;
import org.openide.actions.PasteAction;
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
class FolderItem extends AbstractNode {
    private FileObject fileObject;

    public FolderItem(FileObject fileObject ) {
        
        super( new FileItem(fileObject), Lookups.singleton(fileObject) );
        this.fileObject=fileObject;
        setDisplayName( fileObject.getName());
        setIconBaseWithExtension("org/netbeans/myfirstexplorer/marilyn_category.gif");
    }
    public PasteType getDropType(Transferable t, final int action, int index) {
        final Node dropNode = NodeTransfer.node( t, 
                DnDConstants.ACTION_COPY_OR_MOVE+NodeTransfer.CLIPBOARD_CUT );
        if( null != dropNode ) {
            final FileItem fileItem = (FileItem)dropNode.getLookup().lookup( FileItem.class );
            if( null != fileItem  && !this.equals( dropNode.getParentNode() )) {
                return new PasteType() {
                    
                    public Transferable paste() throws IOException {
                        getChildren().add( new Node[] { new FileItemNode(fileObject) } );
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
    public Cookie getCookie(Class clazz) {
        Children ch = getChildren();
        
        if (clazz.isInstance(ch)) {
            return (Cookie) ch;
        }
        
        return super.getCookie(clazz);
    }
    protected void createPasteTypes(Transferable t, List s) {
        super.createPasteTypes(t, s);
        PasteType paste = getDropType( t, DnDConstants.ACTION_COPY, -1 );
        if( null != paste )
            s.add( paste );
    }
    @Override
    public Action[] getActions(boolean context) {
        return new Action[] {
            SystemAction.get( NewAction.class ),
            SystemAction.get( PasteAction.class ),
        null,
        SystemAction.get(DeleteAction.class)};
    }
    public boolean canDestroy() {
        return true;
    }
    
    
}
