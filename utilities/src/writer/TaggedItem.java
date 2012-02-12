/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package writer;

/**
 *
 * @author slowcoach
 */
public class TaggedItem {

    private String _itemType = "";
    private String _itemName = "";
    private String _scenes = "";
    private String _otherDesc = "";

    public TaggedItem(String name) {
        setItemName(name);
    }

    public String getItemName() {
        return _itemName;
    }

    public final void setItemName(String _itemName) {
        this._itemName = _itemName;
    }

    public String getItemType() {
        return _itemType;
    }

    public void setItemType(String _itemType) {
        this._itemType = _itemType;
    }

    public String getOtherDesc() {
        return _otherDesc;
    }

    public void setOtherDesc(String _otherDesc) {
        this._otherDesc = _otherDesc;
    }

    public String getScenes() {
        return _scenes;
    }

    public void setScenes(String _scenes) {
        this._scenes = _scenes;
    }
}
