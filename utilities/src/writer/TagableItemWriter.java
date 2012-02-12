/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package writer;

/**
 *
 * @author slowcoach
 */
public class TagableItemWriter extends Writer{
    private TagableItemWriter()
    {}
    
    public void write()
    {}
    public void undo()
    {}
    public void redo()
    {}
    public void removeTagableItem()
    {}
    public void removeFile()
    {}
    public void addTagableItem()
    {}
    public static TagableItemWriter getInstance() {
        return TagableItemWriterHolder.INSTANCE;
    }

    public void write(String string) {
        
    }
    
    private static class TagableItemWriterHolder {

        private static final TagableItemWriter INSTANCE = new TagableItemWriter();
    }
    
}
