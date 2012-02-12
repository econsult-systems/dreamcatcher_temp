/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.util;

import writer.Breakdown;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import writer.SceneElement;

/**
 *
 * @author Roy Rutto,slowcoach
 */
public class APIObject {

    private final Date date = new Date();
    private static int count = 0;
    private int index = 0;
    private String element = "No Elements";
    private int amount = 0;
    private DefaultComboBoxModel Defaultmodel = new DefaultComboBoxModel();
    private ArrayList<SceneElement> _sceneElements = new ArrayList<SceneElement>();
    private String _tobeChanged;
    private String _toggle;
    private int _num;
    private SceneElement _sceneElement=new SceneElement("");

    public APIObject(String Element, int Number) {
        index = count++;
        element = Element;
        amount = Number;
    }

    public APIObject(ArrayList<SceneElement> SceneContent) {
        _sceneElements = SceneContent;
        for (int i = 0; i < SceneContent.size(); i++) {

            SceneElement item = (SceneElement) SceneContent.get(i);
            this.Defaultmodel.addElement(item.getSceneName());
        }

    }

    public APIObject(String toBeChanged) {
        this._tobeChanged = toBeChanged;
    }

    public APIObject(DefaultComboBoxModel model) {
        this.Defaultmodel = model;

    }
    public APIObject(SceneElement sceneDetails)
    {
        this._sceneElement = sceneDetails;
    }

    public Date getDate() {
        return date;
    }

    public int getIndex() {
        return index;
    }

    public SceneElement getSceneElement() {
        return _sceneElement;
    }


    public String getElement() {
        return element;
    }

    public DefaultComboBoxModel getModelElement() {
        return this.Defaultmodel;

    }

    public int getNumberOfScenes() {
        return _sceneElements.size();
    }
    /*
     * Returns the number of different types of scenes
     *
     * in this order
     *
     * Iternal scenes
     *
     * External scenes
     *
     * Day Scenes
     *
     * Night Scenes
     *
     * Internal day scenes
     *
     * External day scenes
     *
     * Internal night scenes
     *
     * External night scenes
     */

    public int[] getNumberOfScenesInDetail() {
        int[] scnes = {0, 0, 0, 0, 0, 0, 0};
        Breakdown.breakdown(this._sceneElements);
        return scnes;
    }

    public int getNumber() {
        return amount;
    }

    public String toString() {
        return index + " - " + date;
    }

    public void setToggle(String state) {
        this._toggle = state;
    }

    public String getToBeChanged() {
        return this._tobeChanged;
    }
    public String getToggle()
    {
        return this._toggle;
    }

    public void setSceneNumber(int count) {
        this._num=count;
    }
    public int getSceneNumber()
    {
        return this._num;
    }
}
