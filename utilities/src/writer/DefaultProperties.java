/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package writer;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author slowcoach
 */
public class DefaultProperties {
    private String _defaultFolder="PreproductionProjects";

    private DefaultProperties() {
    }

    public static DefaultProperties getInstance() {
        return DefaultPropertiesHolder.INSTANCE;
    }
    public FileObject getDefaultProjectsDirectory()
    {
        FileObject createFolder = null;
        String home = System.getProperty("user.home");
        String project = System.getProperty("file.separator");
        project = home+project+_defaultFolder;
        File check = new File(project);

        if(!(check.exists()))
        {
            try {
                createFolder = FileUtil.createFolder(check);
            } catch (IOException ex) {
                Logger.getLogger(DefaultProperties.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            createFolder=FileUtil.toFileObject(check);
        }
        return createFolder;
    }
    public FileObject getProjectConfigs(String projectName)
    {
        return FileUtil.toFileObject(new File("C://dreamcather"+File.pathSeparator+projectName));
    }

    private static class DefaultPropertiesHolder {

        private static final DefaultProperties INSTANCE = new DefaultProperties();
    }
}
