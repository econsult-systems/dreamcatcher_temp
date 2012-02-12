/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package writer;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
//import org.openide.cookies.EditorCookie;


import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author slowcoach
 */
public class XMLManager {

    private static final String TAGGEDITEMSTAG = "taggedItems";
    private static final String SCENE_TAG = "sceneItems";
    private static final String NAME_TAG = "name";
    private static final String OTHER_TAG = "other";
    private static final String DEFAULT_DESCRIPTION = "Description";

    private XMLManager() {
    }
    private static Document dom = null;
    private static final String TAG_PATH = ".tagConf.xml";
    public static String currentProjectTagPath = "";

    public static String getTAG_PATH() {
        return TAG_PATH;
    }

    private boolean checkExists() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void createDocument() throws ProjectSettingsException {

        //get an instance of factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            //get an instance of builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //create an instance of DOM
            dom = db.newDocument();

        } catch (ParserConfigurationException pce) {
            //dump it
            //System.out.println("Error while trying to instantiate DocumentBuilder " + pce);
            throw new ProjectSettingsException(pce.getMessage());
            // System.exit(1);
        }

    }

    public void getItems(String category) {
    }

    public ArrayList getAllTaggedElements() throws ProjectSettingsException {
        //Get all the tagged items as an array of tagged elements
        ArrayList<TaggedItem> tElements = new ArrayList<TaggedItem>();
        NodeList nodelist = null;
        NodeList nodesOfInterest;
        NodeList nodeOfInterest;
        if (currentProjectTagPath.equals("")) {
            throw new ProjectSettingsException("The file containing project setting could not be found");
        } else {
            getDocument(getCurrentTagPath());
            NodeList elementById = dom.getDocumentElement().getElementsByTagName(getTaggedItemsTag());
            if (elementById.getLength() > 0) {
                Node node = elementById.item(0);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    nodesOfInterest = node.getChildNodes();
                    for (int j = 0; j < nodesOfInterest.getLength(); j++) {

                        if (nodesOfInterest.item(j).hasChildNodes()) {
                            nodeOfInterest = nodesOfInterest.item(j).getChildNodes();

                            TaggedItem ti = new TaggedItem(nodeOfInterest.item(1).getTextContent());
                            ti.setItemType(nodesOfInterest.item(j).getNodeName());
                            ti.setOtherDesc(nodeOfInterest.item(5).getTextContent());
                            ti.setScenes(nodeOfInterest.item(3).getTextContent());
                            tElements.add(ti);
                        }
                    }

                }
            }
        }
        return tElements;
    }
      public ArrayList getAllSceneItems() throws ProjectSettingsException {
        //Get all the tagged items as an array of tagged elements
        ArrayList<SceneElement> tElements = new ArrayList<SceneElement>();
        NodeList nodelist = null;
        NodeList nodesOfInterest;
        NodeList nodeOfInterest;
        if (currentProjectTagPath.equals("")) {
            throw new ProjectSettingsException("The file containing project setting could not be found");
        } else {
            getDocument(getCurrentTagPath());
            NodeList elementById = dom.getDocumentElement().getElementsByTagName(getSceneTag());
            if (elementById.getLength() > 0) {
                Node node = elementById.item(0);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    nodesOfInterest = node.getChildNodes();
                    for (int j = 1; j < nodesOfInterest.getLength(); j++) {
                
                        
                        if (nodesOfInterest.item(j).hasChildNodes()) {
                            nodeOfInterest = nodesOfInterest.item(j).getChildNodes();

                            SceneElement ti = new SceneElement(nodeOfInterest.item(3).getTextContent());
                            
                            ti.setSceneType(nodeOfInterest.item(4).getNodeValue());
                            //ti.setSceneType(nodeOfInterest.item(4).getTextContent());
                            tElements.add(ti);
                        }
                    }

                }
            }
        }
        return tElements;
    }

    public void createSkeleton(String path, String pname, String desc, String OPath, String CPath) throws ProjectSettingsException {
        createDocument();
        if (!dom.equals(null)) {
            //configure the requisite path
            String pathValue = path + File.separator + TAG_PATH;
            currentProjectTagPath = pathValue;
            createDefaults(pname, desc, OPath, CPath);
            // System.out.print("\n\n\n\n\n\n\n\n\n\n"+dom.getXmlVersion());
            if (dom.hasChildNodes()) {
                printToFile(pathValue);
            } else {
                throw new ProjectSettingsException("An error occured in creating project settings");
            }

        }

    }

    private void printToFile(String file) throws ProjectSettingsException {

        try {
            //print
            OutputFormat format = new OutputFormat(dom);
            format.setIndenting(true);

            //to generate output to console use this serializer
            //XMLSerializer serializer = new XMLSerializer(System.out, format);


            //to generate a file output use fileoutputstream instead of system.out
            XMLSerializer serializer = new XMLSerializer(
                    new FileOutputStream(new File(file)), format);

            serializer.serialize(dom);

        } catch (IOException ie) {
            //ie.printStackTrace();
            throw new ProjectSettingsException("The Project settings could not be created" + "\n" + ie.getMessage());
        }
    }

    /**
     * Writes a given xml dom object to the file given
     * @param file
     * @param doc
     *
     */
    private void printToFile(String file, Document doc) throws ProjectSettingsException {

        try {
            //print
            OutputFormat format = new OutputFormat(doc);

            format.setIndenting(true);

            //to generate output to console use this serializer
            //XMLSerializer serializer = new XMLSerializer(System.out, format);


            //to generate a file output use fileoutputstream instead of system.out
            XMLSerializer serializer = new XMLSerializer(
                    new FileOutputStream(new File(file)), format);

            serializer.serialize(doc);

        } catch (IOException ie) {
            //   ie.printStackTrace();
            throw new ProjectSettingsException("The Project settings could not be created: " + ie.getMessage());
        } catch (Exception e) {
            throw new ProjectSettingsException("The Project settings could not be created: " + e.getMessage());
        } finally {
            clearDom();
        }

    }

    private void createDefaults(String pname, String desc, String OPath, String CPath) {

        Element e = null;
        Element Root = dom.createElement("project");
        Element root = dom.createElement("projectProperties");
        Element rootTag = dom.createElement(getTaggedItemsTag());
        Comment comment = dom.createComment("All the tagged items");


        rootTag.appendChild(comment);

        Element rootScenes = dom.createElement(getSceneTag());
        Comment scenecomment = dom.createComment("All the scenes identified in the script");
        rootScenes.appendChild(scenecomment);

        //create element project name
        Comment createComment = dom.createComment("This is the file containing all the tagged values in a script");
        e = dom.createElement("projectName");
        Node n = dom.createCDATASection(pname);
        e.appendChild(n);
        root.appendChild(e);
        //create element project description
        e = dom.createElement("projectDescription");
        Node text = dom.createCDATASection(desc);
        e.appendChild(text);
        root.appendChild(e);
        e = dom.createElement("projectScript");
        text = dom.createCDATASection(OPath);
        e.appendChild(text);
        root.appendChild(e);
        e = dom.createElement("projectTextScript");
        text = dom.createCDATASection(CPath);
        e.appendChild(text);
        root.appendChild(e);
        Root.appendChild(createComment);
        Root.appendChild(rootTag);
        Root.appendChild(root);
        Root.appendChild(rootScenes);
        dom.appendChild(Root);


    }

    public void createScriptDetails() {
        Element e = null;
    }

    public boolean tag(String itemname, String type, String[] scenes) throws ProjectSettingsException {
        return tag(itemname, type, scenes, getDefaultDescription());
    }

    public boolean tag(String itemname, String type, String[] scenes, String OtherInfo) throws ProjectSettingsException {
        //Add the tagged item to the xml document
        TaggedItem ti = null;
        Element root = null;
        Element node = null;
        if (isTagged(itemname, type)) {
            return false;
        } else {
            //add the item to the tagables in the xml
            //return a true value if its successful

            getDocument(getCurrentTagPath());
            //Element doc = dom.getDocumentElement();
            //Pointing to document tagged items element
            root = (Element) dom.getDocumentElement().getElementsByTagName(getTaggedItemsTag()).item(0);
            //create child element having tagName=type
            ti = new TaggedItem(itemname);
            ti.setItemType(type);
            ti.setOtherDesc(OtherInfo);
            ti.setScenes(toString(scenes));
            Element childElement = createNodeStructure(ti);
            root.appendChild(childElement);
            //Add the attribute to the child
            printToFile(currentProjectTagPath, dom);
            return true;
        }
    }

    public void getItemDetails(String item) {
        //details of a particular item
    }

    public boolean isTagged(String itemname, String type) throws ProjectSettingsException {
        if (getTagItem(itemname, type) == null) {
            return false;
        } else {

            return true;
        }
    }

    public String getCurrentTagPath() {
        return currentProjectTagPath;
    }

    private void getDocument(String xmlfile) throws ProjectSettingsException {

        try {
            DocumentBuilder dbf = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            //Using factory get an instance of document builder
            //DocumentBuilder db = dbf.newDocumentBuilder();
            //parse using builder to get DOM representation of the XML file
            dom = dbf.parse(xmlfile);

        } catch (SAXException sae) {
            throw new ProjectSettingsException("Could not read project settings:\n" + sae.getMessage());
        } catch (ParserConfigurationException pce) {
            throw new ProjectSettingsException("Could not read project settings:\n" + pce.getMessage());
            //System.exit(1);
        } catch (IOException ioe) {
            throw new ProjectSettingsException("Could not find project settings:\n" + ioe.getMessage());
        } catch (Exception e) {
            throw new ProjectSettingsException("Unknown Error" + e.getLocalizedMessage());
        }

    }

    private SceneElement nodeToScene(Node node) {
        SceneElement selement = null;
        Element actualItem = (Element) node;
        NodeList namedItemLE = actualItem.getChildNodes();
        String textContent = namedItemLE.item(3).getTextContent();

        //Create a scene element
        selement = new SceneElement(textContent);
        selement.setSceneNumber(Integer.parseInt(namedItemLE.item(1).getTextContent()));
        selement.setSceneType(namedItemLE.item(3).getTextContent());
        selement.setSceneNumberOfSetups(Integer.parseInt(namedItemLE.item(7).getTextContent()));
        selement.setSceneSizeInLines(Integer.parseInt(namedItemLE.item(6).getTextContent()));
        return selement;
    }

    private Node getTagItem(String itemname, String type) throws ProjectSettingsException {
        Node namedItem = null;
        NodeList nodelist = null;
        TaggedItem ti = new TaggedItem(type);
        NodeList elementById = getXMLDom(ti);
        for (int i = 0; i < elementById.getLength(); i++) {
            Node node = elementById.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                nodelist = element.getElementsByTagName(type);
                if (nodelist != null || element.getElementsByTagName(type).getLength() > 0) {
                    for (int j = 0; j < nodelist.getLength(); j++) {
                        Element actualItem = (Element) nodelist.item(j);
                        NodeList namedItemLE = actualItem.getChildNodes();
                        String textContent = namedItemLE.item(1).getTextContent();
                        //check if the names match to avoid double tagging
                        if (textContent.equalsIgnoreCase(itemname)) {
                            namedItem = actualItem;
                            break;
                        }

                    }
                }

                break;
            }
        }
        //getElementById(itemname);


        return namedItem;
    }

    public String[] getNodeProperties(Node node) {
        String[] properties = new String[3];

        if (!(node == null)) {
            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                Node datanode = node.getChildNodes().item(i);
                properties[i] = datanode.getTextContent();

            }
            // properties[0]=node.getFirstChild().getTextContent();
            //properties[0]=node.get
        }
        return properties;
    }

    private void clearDom() {
        dom = null;
    }

    private void refreshDom() throws ProjectSettingsException {
        getDocument(currentProjectTagPath);
    }

    public static XMLManager getInstance() {
        return XMLManagerHolder.getXMLManager();
    }

    private String getTaggedItemsTag() {
        return TAGGEDITEMSTAG;
    }

    private String getSceneTag() {
        return SCENE_TAG;
    }

    private String getNameTag() {
        return NAME_TAG;
    }

    private String getOtherTag() {
        return OTHER_TAG;
    }

    private String toString(String[] scenes) {
        String string = "";
        if (scenes.length > 0) {
            for (String scene : scenes) {
                string += scene + ",";
            }
            return string;
        }
        return "0";
    }

    private String getDefaultDescription() {
        return DEFAULT_DESCRIPTION;
    }

    public boolean writeSceneDetail(SceneElement item) throws ProjectSettingsException {
        //write the details of the scenes to the XML file containing other project settings
        getDocument(getCurrentTagPath());
        Element root = (Element) dom.getDocumentElement().getElementsByTagName(getSceneItemsTag()).item(0);
        //create child element having tagName=type
        Element childElement = createNodeStructure(item);
        //Append the node element to the root element
        root.appendChild(childElement);
        //Add all to the actual file
        printToFile(currentProjectTagPath, dom);
        return true;

    }

    public SceneElement getScene(String name) throws ProjectSettingsException {
        //TODO change this implementation to the better one
        SceneElement selement = new SceneElement(name);
        //Get a scene element and give the details
        Node namedItem = null;
        NodeList nodelist = null;
        NodeList elementById = getXMLDom(selement);
        for (int i = 0; i < elementById.getLength(); i++) {
            Node node = elementById.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                nodelist = element.getElementsByTagName("SCENE");
                if (nodelist != null || element.getElementsByTagName("SCENE").getLength() > 0) {
                    for (int j = 0; j < nodelist.getLength(); j++) {
                        Element actualItem = (Element) nodelist.item(j);
                        NodeList namedItemLE = actualItem.getChildNodes();
                        String textContent = namedItemLE.item(3).getTextContent();
                        //check if the names match to avoid double tagging
                        if (textContent.equalsIgnoreCase(name)) {
                            //Create a scene element
                            selement = new SceneElement(name);
                            selement.setSceneNumber(Integer.parseInt(namedItemLE.item(1).getTextContent()));
                            selement.setSceneType(namedItemLE.item(3).getTextContent());
                            selement.setSceneNumberOfSetups(Integer.parseInt(namedItemLE.item(7).getTextContent()));
                            selement.setSceneSizeInLines(Integer.parseInt(namedItemLE.item(6).getTextContent()));
                            break;
                        }
                    }
                }

                break;
            }
        }
        //getElementById(itemname);


        return selement;
    }

    /*public boolean editSceneElement(SceneElement element) throws ProjectSettingsException {
    boolean state = false;
    getXMLDom(element);


    return state;
    }*/
    public boolean editElement(Object object) throws ProjectSettingsException {
        boolean state = false;
        getDocument(getCurrentTagPath());
        Element createNodeStructure = null;
        Element root;
        if (object instanceof SceneElement) {
            SceneElement obj = (SceneElement) object;
            Node sceneNode = getSceneNode(obj.getSceneName());
            if (sceneNode != null) {
                root = (Element) dom.getDocumentElement().getElementsByTagName(getSceneItemsTag()).item(0);
                createNodeStructure = createNodeStructure(object);
                root.replaceChild(createNodeStructure, sceneNode);

            }
        } else if (object instanceof TaggedItem) {
            TaggedItem ti = (TaggedItem) object;
            Node tagItem = getTagItem(ti.getItemName(), ti.getItemType());
            if (tagItem != null) {
                root = (Element) dom.getDocumentElement().getElementsByTagName(getTaggedItemsTag()).item(0);
                createNodeStructure = createNodeStructure(object);
                root.replaceChild(createNodeStructure, tagItem);
            }

        }

        printToFile(currentProjectTagPath, dom);
        state = true;
        return state;
    }

    private String getSceneItemsTag() {
        return this.SCENE_TAG;
    }

    private Element createNodeStructure(Object object) throws ProjectSettingsException {
        Element nodeElement = null;
        if (object instanceof TaggedItem) {
            Element root = (Element) dom.getDocumentElement().getElementsByTagName(getTaggedItemsTag()).item(0);
            //create an items node structure
            TaggedItem tiS = (TaggedItem) object;
            Element childElement = dom.createElement(tiS.getItemType());
            Element node = dom.createElement(getNameTag());
            Node text = dom.createCDATASection(tiS.getItemName());
            node.appendChild(text);
            childElement.appendChild(node);
            //create the scenes tag
            node = dom.createElement(getSceneTag());
            text = dom.createCDATASection(tiS.getScenes());
            node.appendChild(text);
            childElement.appendChild(node);
            //create any other relevant information
            node = dom.createElement(getOtherTag());
            text = dom.createCDATASection(tiS.getOtherDesc());
            node.appendChild(text);
            childElement.appendChild(node);
            nodeElement = childElement;
        } else if (object instanceof SceneElement) {
            //create a SceneElements node structure
            Element root = (Element) dom.getDocumentElement().getElementsByTagName(getSceneItemsTag()).item(0);
            SceneElement scneobject = (SceneElement) object;
            Element childElement = dom.createElement("SCENE");
            Element node = dom.createElement("number");
            Node text = dom.createCDATASection(String.valueOf(scneobject.getSceneNumber()));
            node.appendChild(text);
            childElement.appendChild(node);
            //create the name tag
            node = dom.createElement("name");
            text = dom.createCDATASection(scneobject.getSceneName());
            node.appendChild(text);
            childElement.appendChild(node);
            //create the type tag
            node = dom.createElement("type");
            text = dom.createCDATASection(scneobject.getSceneType());
            node.appendChild(text);
            childElement.appendChild(node);
            //create the startindex tag
            node = dom.createElement("startindex");
            text = dom.createCDATASection(scneobject.getSceneStartIndex().toString());
            node.appendChild(text);
            childElement.appendChild(node);
            //create the end index tag
            node = dom.createElement("endindex");
            text = dom.createCDATASection(scneobject.getSceneEndIndex().toString());
            node.appendChild(text);
            childElement.appendChild(node);
            //create the lines tag
            node = dom.createElement("lines");
            text = dom.createCDATASection(String.valueOf(scneobject.getSceneSizeInLines()));
            node.appendChild(text);
            childElement.appendChild(node);
            //create setups tags
            node = dom.createElement("setups");
            text = dom.createCDATASection(String.valueOf(scneobject.getSceneNumberOfSetups()));
            node.appendChild(text);
            childElement.appendChild(node);
            //create shootdays tags
            node = dom.createElement("shootdays");
            text = dom.createCDATASection(String.valueOf(scneobject.getShootDays()));
            node.appendChild(text);
            childElement.appendChild(node);
            nodeElement = childElement;

        } else {
            //Unknownn Item type so we cannot edit it
            //Indicate at the log level
        }
        return nodeElement;
    }

    private NodeList getXMLDom(Object obj) throws ProjectSettingsException {
        NodeList elementById = null;
        getDocument(getCurrentTagPath());
        if (currentProjectTagPath.equals("")) {
            throw new ProjectSettingsException("The file containing project setting could not be found");
        } else {
            if (obj instanceof SceneElement) {
                elementById = dom.getDocumentElement().getElementsByTagName(getSceneTag());

            } else if (obj instanceof TaggedItem) {
                elementById = dom.getDocumentElement().getElementsByTagName(getTaggedItemsTag());
            }
            return elementById;
        }
    }

    private Node getSceneNode(String string) throws ProjectSettingsException {
        Node namedItem = null;
        NodeList nodelist = null;
        SceneElement selement = new SceneElement(string);
        NodeList elementById = getXMLDom(selement);
        for (int i = 0; i < elementById.getLength(); i++) {
            Node node = elementById.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                nodelist = element.getElementsByTagName("SCENE");
                if (nodelist != null || element.getElementsByTagName("SCENE").getLength() > 0) {
                    for (int j = 0; j < nodelist.getLength(); j++) {
                        Element actualItem = (Element) nodelist.item(j);
                        NodeList namedItemLE = actualItem.getChildNodes();
                        String textContent = namedItemLE.item(3).getTextContent();
                        //check if the names match to avoid double tagging
                        if (textContent.equalsIgnoreCase(string)) {
                            //Create a scene element
                            namedItem = actualItem;
                            break;
                        }
                    }
                }

                break;
            }
        }
        //getElementById(itemname);


        return namedItem;
    }

    private static class XMLManagerHolder {

        private static XMLManager INSTANCE;

        private static synchronized XMLManager getXMLManager() {
            if (XMLManagerHolder.INSTANCE == null) {
                XMLManagerHolder.INSTANCE = new XMLManager();
            }
            return XMLManagerHolder.INSTANCE;
        }
    }
}
