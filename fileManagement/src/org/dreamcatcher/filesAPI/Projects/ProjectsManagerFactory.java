/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.filesAPI.Projects;

import java.util.Map;

/**
 *
 * @author slowcoach
 */
public class ProjectsManagerFactory {
    public static ProjectsManager create(Map ges) {
        return new ProjectsManager(ges);
    }
    
}
