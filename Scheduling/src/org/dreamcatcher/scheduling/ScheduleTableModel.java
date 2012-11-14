package org.dreamcatcher.scheduling;
//-*- mode:java; encoding:utf8n; coding:utf-8 -*-
// vim:set fileencoding=utf-8:
//http://terai.xrea.jp/Swing/TableRowHeader.html
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class ScheduleTableModel extends DefaultTableModel {
    private static final ColumnContext[] columnArray = {
        //new ColumnContext("No.",     Integer.class, false),
        new ColumnContext("SCENE",    String.class,  false),
        new ColumnContext("PAGE COUNT",    String.class,  false),
        new ColumnContext("DAY/NIGHT",    String.class,  false),
        new ColumnContext("INT/EXT", String.class,  false),
        new ColumnContext("SET", String.class,  false),
        new ColumnContext("CAST", String.class,  false),
        new ColumnContext("EST. TIME", String.class,  false)
        
    };
    private int number = 0;
    private final DefaultListModel rowListModel;
    public ScheduleTableModel(DefaultListModel lm) {
        super();
        rowListModel = lm;
    }
    public void addRow(SceneRow t) {

        Object[] obj = {t.getName(), t.getPageCount(),"DAY", "INT","1", "1,2,3,4,5","0.00"};

        super.addRow(obj);
        rowListModel.addElement("row"+number);
        number++;
    }
    public void removeRow(int index) {
        super.removeRow(index);
        rowListModel.remove(index);
    }
    @Override public boolean isCellEditable(int row, int col) {
        return columnArray[col].isEditable;
    }
    @Override public Class<?> getColumnClass(int modelIndex) {
        return columnArray[modelIndex].columnClass;
    }
    @Override public int getColumnCount() {
        return columnArray.length;
    }
    @Override public String getColumnName(int modelIndex) {
        return columnArray[modelIndex].columnName;
    }
    private static class ColumnContext {
        public final String  columnName;
        public final Class   columnClass;
        public final boolean isEditable;
        public ColumnContext(String columnName, Class columnClass, boolean isEditable) {
            this.columnName = columnName;
            this.columnClass = columnClass;
            this.isEditable = isEditable;
        }
    }
}
class SceneRow{
    private String name, pagecount, time, location, scene_set, cast , est_time;
    public SceneRow(String name, String pagecount, String time, String location, String scene_set, String cast , String est_time) {
        this.name = name;
        this.pagecount = pagecount;
        this.time = time;
        this.location = location;
        this.scene_set = scene_set;
        this.cast = cast;
        this.est_time = est_time;
    }
    public void setName(String str) {
        name = str;
    }
    public void setPageCount(String str) {
        pagecount = str;
    }
    public void setTime(String str) {
        time = str;
    }
    public void setLocation(String str) {
       location = str;
    }
    public void setSceneSet(String str) {
        scene_set = str;
    }
    public void setCast(String str) {
        cast = str;
    }
    public void setEstTime(String str) {
        est_time = str;
    }
    public String getName() {
        return name;
    }
    public String getPageCount() {
        return pagecount;
    }
    public String getTime() {
        return time;
    }
    public String getLocation() {
        return location;
    }
    public String getSceneSet() {
        return scene_set;
    }
    public String getCast() {
        return cast;
    }
    public String getEstTime() {
        return est_time;
    }
    
    
}
