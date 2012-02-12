/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package writer;

/**
 *
 * @author slowcoach
 */
public class ScheduleWriter extends Writer{
    private ScheduleWriter()
    {}
    public void write()
    {}
    public void undo()
    {}
    public void redo()
    {}
    public void removeScheduleItem()
    {}
    public void removeFile()
    {}
    public void addScheduleItem()
    {}
    public static ScheduleWriter getInstance() {
        return ScheduleWriterHolder.INSTANCE;
    }
    
    private static class ScheduleWriterHolder {

        private static final ScheduleWriter INSTANCE = new ScheduleWriter();
    }
    
    
}
