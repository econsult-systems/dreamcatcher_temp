/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.filesAPI.Projects;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author slowcoach
 */
class PreproductionConfigs {

    protected static String projectshome = "";

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
            return home.concat(File.separator).concat(getConfigurationName());

        }
        projectshome = projects.getPath();
        System.setProperty("netbeans.projects.dir", projectshome); 
        
        return projectshome;
        

    }

    private static String getConfigurationName() {
        return "PreproductionProjects";
    }

    public Properties getProperties() {
        return System.getProperties();
    }
}
