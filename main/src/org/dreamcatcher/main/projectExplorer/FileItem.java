/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.main.projectExplorer;

import java.util.ArrayList;
import org.openide.filesystems.FileObject;
import org.openide.nodes.Index;
import org.openide.nodes.Node;

/**
 *
 * @author slowcoach
 */
public class FileItem  extends Index.ArrayChildren {
    
    
    
    private FileObject parentFolder;
    
    public FileItem(FileObject parent) {
        this.parentFolder = parent;
    }
    
    protected java.util.List<Node> initCollection() {
        FileObject[] number = parentFolder.getChildren();
        ArrayList childrenNodes = new ArrayList( number.length);
        for( int i=0; i<number.length; i++ ) {
            if( !(number[i].isFolder()) ) {
                
                childrenNodes.add( new FileItemNode( number[i] ) );
            }
            else
            {
                childrenNodes.add(new FolderItem(number[i]));
            }
        }
        return childrenNodes;
    }
}
