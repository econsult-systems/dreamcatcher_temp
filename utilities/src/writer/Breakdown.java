/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package writer;

import java.util.ArrayList;
import writer.SceneElement;
import writer.TaggedItem;

/**
 *
 * @author slowcoach
 */
public class Breakdown {

    private static ArrayList<SceneElement> SCENES_ARRAY;
    private static ArrayList<TaggedItem> TAGGED_ELEMENTS_ARRAY;

    public static void breakdown(ArrayList<SceneElement> _sceneElements) {
        Breakdown.SCENES_ARRAY = _sceneElements;
    }

    public static int getNumberOfInternalScenes() {
        return getInternalScenes().size();
    }

    public static int getNumberOfExternalScenes() {
        return getExternalScenes().size();
    }

    public static int getNumberOfAllOtherScenes() {
        return Breakdown.SCENES_ARRAY.size() - ((getNumberOfInternalScenes() + getNumberOfExternalScenes()));
    }

    public static int getNumberOfAllDayScenes() {
        return getDayScenes().size();
    }

    public static int getNumberOfAllNightScenes() {
        return getNightScenes().size();
    }

    public static int getNumberOfAllOtherScenesTwo() {
        return Breakdown.SCENES_ARRAY.size() - (getNumberOfAllDayScenes() + getNumberOfAllNightScenes());
    }

    private static ArrayList getScenes(String type) {
        ArrayList<SceneElement> internal = new ArrayList<SceneElement>();

        SceneElement se;
        for (int i = 0; i < SCENES_ARRAY.size(); i++) {
            se = SCENES_ARRAY.get(i);
            if (type.equalsIgnoreCase("INT") || type.equalsIgnoreCase("EXT")) {
                if (se.getSceneType().equalsIgnoreCase(type)) {
                    internal.add(se);

                }

            } else if (type.equalsIgnoreCase("DAY") || type.equalsIgnoreCase("NIGHT")) {
                if (type.equalsIgnoreCase("DAY")) {
                    if (se.isDay()) {
                        internal.add(se);
                    }
                } else if (type.equalsIgnoreCase("NIGHT")) {
                    if (!(se.isDay())) {
                        internal.add(se);
                    }
                }
            }
        }
        return internal;
    }

    public static ArrayList getInternalScenes() {
        return getScenes("INT");

    }

    public static ArrayList getExternalScenes() {
        return getScenes("EXT");
    }

    public static ArrayList getDayScenes() {
        return getScenes("DAY");
    }

    public static ArrayList getNightScenes() {
        return getScenes("NIGHT");
    }

    public static ArrayList getAllScenes() {
        return Breakdown.SCENES_ARRAY;
    }

    public static SceneElement getSceneWithNumber(int sceneNumber) {
        return getScene(String.valueOf(sceneNumber), "number");
    }

    public static SceneElement getSceneWithName(String sceneName) {
        return getScene(sceneName, "name");
    }

    private static SceneElement getScene(String valueOf, String searchtype) {
        SceneElement selement = null;
        for (int i = 0; i < SCENES_ARRAY.size(); i++) {
            SceneElement se = SCENES_ARRAY.get(i);
            if (searchtype.equalsIgnoreCase("number")) {
                if (String.valueOf(se.getSceneNumber()).equalsIgnoreCase(valueOf)) {
                    selement = se;
                }
            } else if (searchtype.equalsIgnoreCase("name")) {
                if (se.getSceneName().equalsIgnoreCase(valueOf)) {
                    selement = se;
                }
            }
            //else if(searchtype.equalsIgnoreCase("content"))
            //{}
            //else if(searchtype.equalsIgnoreCase("startIndex"))
        }
        return selement;
    }

    public static ArrayList getTaggedItems(String category) throws ProjectSettingsException {
        ArrayList<TaggedItem> myCategory = new ArrayList<TaggedItem>();
        ArrayList tagged = XMLManager.getInstance().getAllTaggedElements();
        for (int i = 0; i < tagged.size(); i++) {
            TaggedItem ti = (TaggedItem) tagged.get(i);
            if (ti.getItemType().equalsIgnoreCase(category)) {
                myCategory.add(ti);
            }

        }

        return myCategory;
    }

    public static int getNumberOfActorsPerScenes(String scene) {
        return getActorsPerScene(scene).size();
    }

    private static ArrayList getActorsPerScene(String scene) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static int getNumberOfScenesPerActor(String actor) {
        return getScenesPerActor().size();
    }

    private static ArrayList getScenesPerActor() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
