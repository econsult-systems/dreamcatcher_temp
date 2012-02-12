/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.filesAPI.Projects;

/**
 *
 * @author slowcoach
 */
class ProjectAdapter {
    private String projectname="";
    private String projectlocation="";
    private String PROJECT_FOLDER="";
    private String PROJECT_HOME_OS="";
    private String Projectcreator="";
    private String projectcreationttimeanddate="";
    private String projectDesc;
    private String OriginalScriptPath;

    ProjectAdapter(String projectsname) {
        projectname=projectsname;
    }

    public String getPROJECT_FOLDER() {
        return PROJECT_FOLDER;
    }

    public void setPROJECT_FOLDER(String PROJECT_FOLDER) {
        this.PROJECT_FOLDER = PROJECT_FOLDER;
    }

    public String getPROJECT_HOME_OS() {
        return PROJECT_HOME_OS;
    }

    public void setPROJECT_HOME_OS(String PROJECT_HOME_OS) {
        this.PROJECT_HOME_OS = PROJECT_HOME_OS;
    }

    public String getProjectcreator() {
        return Projectcreator;
    }

    public void setProjectcreator(String Projectcreator) {
        this.Projectcreator = Projectcreator;
    }

    public String getProjectcreationttimeanddate() {
        return projectcreationttimeanddate;
    }

    public void setProjectcreationttimeanddate(String projectcreationttimeanddate) {
        this.projectcreationttimeanddate = projectcreationttimeanddate;
    }

    public String getProjectlocation() {
        return projectlocation;
    }

    public void setProjectlocation(String projectlocation) {
        this.projectlocation = projectlocation;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    void setProjectDescription(String string) {
        this.projectDesc=string;
    }

    void setOriginalScriptpath(String get) {
        this.OriginalScriptPath=get;
    }

    public String getOriginalScriptPath() {
        return OriginalScriptPath;
    }

    public String getProjectDesc() {
        return projectDesc;
    }
    
    
}
