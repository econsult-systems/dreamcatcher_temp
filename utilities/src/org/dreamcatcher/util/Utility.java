/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.util;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import writer.SceneElement;
import writer.TaggedItem;

/**
 *
 * @author slowcoach
 */
public class Utility {

    public static TableModel arrayListToTable(String[] columns, ArrayList allTaggedElements) {
        TableModel catalog = null;
        String[][] data = null;
        String[] columnNames = columns;
        if(columnNames.length>3)
        {
            SceneElement dataItem;

        data = new String[allTaggedElements.size()][7];
        for (int i = 0; i < allTaggedElements.size(); i++) {
            dataItem = (SceneElement) allTaggedElements.get(i);

            data[i][0] = String.valueOf(dataItem.getSceneNumber());
            data[i][1] = dataItem.getSceneName();
            data[i][2] = dataItem.getSceneType();
            data[i][3] = dataItem.isDay()?"DAY":"NIGHT";
            data[i][4] = String.valueOf(dataItem.getSceneNumberOfSetups());
            data[i][5] = String.valueOf(dataItem.getSceneSizeInEigths());
            data[i][6] = String.valueOf(dataItem.getShootDays());
        }
        catalog = new DefaultTableModel(data, columnNames);
        }
        else{
        TaggedItem dataItem;

        data = new String[allTaggedElements.size()][3];
        for (int i = 0; i < allTaggedElements.size(); i++) {
            dataItem = (TaggedItem) allTaggedElements.get(i);

            data[i][0] = dataItem.getItemName();
            data[i][1] = dataItem.getItemType().toLowerCase();
            data[i][2] = dataItem.getScenes();
        }
        catalog = new DefaultTableModel(data, columnNames);
        }
        return catalog;
    }
}
