/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package writer;

import org.openide.filesystems.FileObject;

/**
 *
 * @author slowcoach
 */
public class writerDefaults {
    
    private writerDefaults() {
    }
    public FileObject getWriteFile(Writer writer) throws UnSupportedWriterException
    {
        if(writer instanceof ScheduleWriter)
        {
            return null;
        }
        else if (writer instanceof TagableItemWriter)
        {
            return null;
        }
        else
        {
            throw new UnSupportedWriterException("Unsupported writer configuration");
        }
    }
    
    public static writerDefaults getInstance() {
        return writerDefaultsHolder.INSTANCE;
    }
    
    private static class writerDefaultsHolder {

        private static final writerDefaults INSTANCE = new writerDefaults();
    }
}
