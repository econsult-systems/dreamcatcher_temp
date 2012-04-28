/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.scheduling;

import java.util.ArrayList;
import writer.SceneElement;
import writer.XMLManager;

/**
 *
 * @author Roy Rutto
 */
public class getBreakdown {
    ArrayList scenedetails =new ArrayList();
    
    public ArrayList details(){
     try{
       //fill Scenes List
     
        ArrayList<SceneElement> sceneElements = new ArrayList<SceneElement>();
        sceneElements= XMLManager.getInstance().getAllSceneItems();
        
        
 
        for(int i=0; i<sceneElements.size(); i++){
            SceneElement item = (SceneElement) sceneElements.get(i);
           scenedetails.add(item.getSceneName());
        }
        }catch (Exception e) {
            
            System.out.println(e.getMessage());
        }
     return scenedetails;
    }
}
