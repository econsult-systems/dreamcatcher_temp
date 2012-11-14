/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package writer;

/**
 *
 * @author slowcoach
 */
public class SceneElement {
    private String _sceneName="";
    private int _sceneNumber=0;
    private int [] _sceneStartIndex={};
    private int [] _sceneEndIndex={};
    private String _sceneType="";
    private int _sceneSizeInLines=0;
    private int _sceneSizeInEigths=0;
    private int _sceneNumberOfSetups=0;
    private String _sceneContent;
    private boolean _isDay;
    private int _shootDays;
    private String _sceneTime;

    public SceneElement(String toUpperCase) {
        setSceneName(toUpperCase);
    }

    public String getSceneContent() {
        return _sceneContent;
    }

    public void setSceneContent(String _sceneContent) {
        this._sceneContent = _sceneContent;
    }

    public int[] getSceneEndIndex() {
        return _sceneEndIndex;
    }

    public void setSceneEndIndex(int[] _sceneEndIndex) {
        this._sceneEndIndex = _sceneEndIndex;
    }

    public String getSceneName() {
        return _sceneName;
    }

    public final void setSceneName(String _sceneName) {
        this._sceneName = _sceneName;
    }

    public int getSceneNumber() {
        return _sceneNumber;
    }

    public void setSceneNumber(int _sceneNumber) {
        this._sceneNumber = _sceneNumber;
    }

    public int getSceneNumberOfSetups() {
        return _sceneNumberOfSetups;
    }

    public void setSceneNumberOfSetups(int _sceneNumberOfSetups) {
        this._sceneNumberOfSetups = _sceneNumberOfSetups;
    }

    public int getSceneSizeInEigths() {
        return _sceneSizeInEigths;
    }

    public void setSceneSizeInEigths(int _sceneSizeInEigths) {
        this._sceneSizeInEigths = _sceneSizeInEigths;
    }

    public int getSceneSizeInLines() {
        return _sceneSizeInLines;
    }

    public void setSceneSizeInLines(int _sceneSizeInLines) {
        this._sceneSizeInLines = _sceneSizeInLines;
    }

    public int[] getSceneStartIndex() {
        return _sceneStartIndex;
    }

    public void setSceneStartIndex(int[] _sceneStartIndex) {
        this._sceneStartIndex = _sceneStartIndex;
    }

    public String getSceneType() {
        return _sceneType;
    }

    public void setSceneType(String _sceneType) {
        this._sceneType = _sceneType;
    }

    public boolean isDay() {
        return this._isDay;
    }
    public void setDay(boolean bool)
    {
        this._isDay=bool;
    }

    public int getShootDays() {
        return _shootDays;
    }

    public void setShootDays(int _shootDays) {
        this._shootDays = _shootDays;
    }
    
    public String getSceneTime() {
        return _sceneTime;
    }

    public void setSceneTime(String _sceneTime) {
        this._sceneTime = _sceneTime;
    }
    


}
