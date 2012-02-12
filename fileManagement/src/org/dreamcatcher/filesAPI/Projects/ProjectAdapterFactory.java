/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.filesAPI.Projects;

/**
 *
 * @author slowcoach
 */
class ProjectAdapterFactory{

    public ProjectAdapter createAdapter(String projectname) {
        return new ProjectAdapter(projectname);
    }
    
}
