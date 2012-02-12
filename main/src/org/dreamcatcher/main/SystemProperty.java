package org.dreamcatcher.main;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;


/**
 *
 * @author Roy Rutto
 */
public class SystemProperty {
    
     protected static String projectshome = "";
    public SystemProperty()throws IOException{
        
        
            System.setProperty("netbeans.projects.dir", setProjectsHome());
    }
    
    static String getProjectsHome() {
        return projectshome;
    }

    static boolean projectsHomeExists() {
        return (projectshome.equals("")) ? false : true;
    }

    static String setProjectsHome() throws IOException {
        String home = System.getProperty("user.home");
        FileObject projectsHome = FileUtil.toFileObject(new File(home));

        FileObject projects = null;
        try{
        projects = FileUtil.createFolder(new File(home + File.separator + getConfigurationName()));
        }
        catch(Exception e)
        {
            projectshome=home.concat(File.separator).concat(getConfigurationName());

        }
        projectshome = projects.getPath();
         
        
        return projectshome;
        

    }

    private static String getConfigurationName() {
        return "PreproductionProjects";
    }

    public Properties getProperties() {
        return System.getProperties();
    }
    
}
