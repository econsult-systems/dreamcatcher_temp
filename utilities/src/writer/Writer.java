/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package writer;

import javax.tools.FileObject;

/**
 *
 * @author slowcoach
 */
public class Writer {
    protected FileObject xmlToBeWritten;
    protected String writeMode;
    protected boolean hidden = false;
   // protected Document doc;

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getWriteMode() {
        return writeMode;
    }

    public void setWriteMode(String writeMode) {
        this.writeMode = writeMode;
    }

    public FileObject getXmlToBeWritten() {
        return xmlToBeWritten;
    }

    public void setXmlToBeWritten(FileObject xmlToBeWritten) {
        this.xmlToBeWritten = xmlToBeWritten;
    }
    
    
}
