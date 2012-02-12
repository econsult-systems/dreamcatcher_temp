/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.filesAPI.Projects;

import writer.SceneElement;
import org.apache.commons.lang3.StringUtils;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.dreamcatcher.util.Notifier;
import org.openide.cookies.OpenCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;
import writer.XMLManager;
import javax.swing.text.rtf.*;
import org.dreamcatcher.editorsuite.ScriptViewerTopComponent;

/**
 *
 * @author slowcoach
 */
public class ProjectsManager {

    private Map projectcontext = null;
    private FileObject fileobject = null;
    private FileObject HOME_FOLDER_OBJECT = null;
    private FileObject SCRIPT_FOLDER_OBJECT = null;
   
    private OpenCookie oc;
    private FileObject createFolder;
    private FileObject createScriptFolder = null;
     
    private final static String NEWLINE = System.getProperty("line.separator");
    RTFEditorKit rtfKit = new RTFEditorKit();
    public String Script, drmText;
    public int Scenes = 0;
    //public Vector sceneDetails = new Vector<SceneElement>();

    public  ArrayList ScenesList = new ArrayList<SceneElement>();

    ProjectsManager(Map ges) {
        //do something
        this.projectcontext = ges;
    }

    public void setProjectEnv() throws IOException, FileNotFoundException {
        //create the details of the project in the filesytems
        // @TODO get the details of a config file
        ProjectAdapter pa = null;
        Object pname = null, pdesc = null, ppath = null;;
        if (!(this.projectcontext.isEmpty())) {

            pa = new ProjectAdapter((String) this.projectcontext.get("project name"));
            pa.setProjectDescription((String) this.projectcontext.get("Project description"));
            pa.setOriginalScriptpath((String) this.projectcontext.get("project script path"));
        }
        createProject(pa);

    }

    public void openInitialFile(String script, int scenes, ArrayList content) throws DataObjectNotFoundException {

        if (fileobject != null) {
            try {
                //open the script file in editor
                // ScriptEditorDataObject obj = (ScriptEditorDataObject) ScriptEditorDataObject.find (fileobject);
                // oc =  obj.getCookie(OpenCookie.class);
                // oc =  obj.getCookie(OpenCookie.class);

                // if (oc != null) {

                //OPENS SCRIPT IN NETBEANS EDITOR
                //oc.open();


                //OPEN SCRIPT IN CUSTOM EDITOR (ROY)
                new ScriptViewerTopComponent().open(readFile(drmText), scenes,(String) this.projectcontext.get("project name"),ScenesList);

                // }
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }

        }
    }

    public void getConfig() {
    }

    public ProjectAdapter getProjectEnv(String projectname) {
        return new ProjectAdapterFactory().createAdapter(projectname);
    }

    private void createProject(ProjectAdapter pa) {
        try {
            //final string should like this "/home/slowcoach/Preproduction Projects";
            String HOMEFOLDER = PreproductionConfigs.projectsHomeExists() ? PreproductionConfigs.getProjectsHome() : PreproductionConfigs.setProjectsHome();
            HOME_FOLDER_OBJECT = FileUtil.toFileObject(new File(HOMEFOLDER));
            createFolder = HOME_FOLDER_OBJECT.createFolder(pa.getProjectname());
         
            SCRIPT_FOLDER_OBJECT = FileUtil.toFileObject(new File( createFolder.getPath()));
            createScriptFolder = SCRIPT_FOLDER_OBJECT.createFolder("script");
            
            //FileObject toFileObject = FileUtil.toFileObject(new File((String) pa.getOriginalScriptPath()));
             if(pa.getOriginalScriptPath().endsWith(".pdf"))
             { 
                 writeTexttoFile(convertpdftoText(pa.getOriginalScriptPath()), createScriptFolder.getPath() + File.separator + pa.getProjectname() + ".drm");
                 
             }else if(pa.getOriginalScriptPath().endsWith(".rtf")){
                
                 writeTexttoFile(convertRTFtoString(pa.getOriginalScriptPath()), createScriptFolder.getPath() + File.separator + pa.getProjectname() + ".drm");
             }
            fileobject = FileUtil.toFileObject(new File(createScriptFolder.getPath() + File.separator + pa.getProjectname() + ".drm"));
            //new XMLEditor().createSkeleton();
            //System.out.print("\n\n"+createFolder.getPath());

            
            Script = convertRTFtoString(pa.getOriginalScriptPath());
            drmText = createScriptFolder.getPath() + File.separator + pa.getProjectname() + ".drm";


        } catch (IOException e) {

            Notifier.getInstance().giveMessage("The project could not be created because of:\n" + e.getLocalizedMessage(), 3);
            System.out.print("\n\n\n\n" + "at the create \n" + fileobject.getPath());

        } catch (Exception er) {
            Notifier.getInstance().giveMessage("Fatal error :\n" + er.getMessage(), 3);
            // System.exit(1);
        } finally {
            try {
                System.out.println(pa.getOriginalScriptPath());

                XMLManager.getInstance().createSkeleton(createFolder.getPath(), pa.getProjectname(), pa.getProjectDesc(), pa.getOriginalScriptPath(), fileobject.getPath());
                
            } catch (Exception e) {
                System.out.print("\n\n\n" + e.getMessage());
                Notifier.getInstance().giveMessage("The project could not be created because :\n" + e, 3);
                e.printStackTrace();
            }
            //finally
            //{

            // }
        }
    }

    private String convertpdftoText(String fileName) {

        File f = new File(fileName);
        PDFParser parser;
        COSDocument cosDoc = null;
        PDFTextStripper pdfStripper;
        PDDocument pdDoc = null;
        String parsedText = null;

        if (!f.isFile()) {
            Notifier.getInstance().giveMessage("Not a valid File", 3);
            return null;
        }

        try {
            parser = new PDFParser(new FileInputStream(f));
        } catch (Exception e) {
            Notifier.getInstance().giveMessage(e.getMessage(), 3);
            System.out.println("paser error");
            return null;
        }

        try {
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();

            //pdfStripper.setAddMoreFormatting(true);
            //pdfStripper.setIndentThreshold(3);
            //pdfStripper.setPageSeparator("INT");
            //pdfStripper.setSpacingTolerance(3);


            pdDoc = new PDDocument(cosDoc);
            String parsedText_ = pdfStripper.getText(pdDoc);
            parsedText=parsedText_;

           // String parsedText1 = parsedText_.replace("EXT.", "\n\n\nEXT.");
           // String parsedText2 = parsedText1.replace("INT.", "\n\n\nINT.");
            //String parsedText3 = parsedText2.replace("NARRATOR","\n           NARRATOR");
            //parsedText = parsedText2.replace();

            /*String str;

            BufferedReader reader = new BufferedReader(new StringReader(parsedText_));
            StringBuilder stringBuffer = new StringBuilder();

            try {
                while ((str = reader.readLine()) != null) {

                    if (str.length() > 0) {
                        if (StringUtils.isAllUpperCase(str)) {
                            stringBuffer.append(NEWLINE);
                            stringBuffer.append("                ").append(str);
                        } else {
                            stringBuffer.append("     ").append(str);
                        }
                    }
                    stringBuffer.append(NEWLINE);

                    //System.out.println(str.charAt(0));

                }

            } catch (IOException e) {
                e.printStackTrace();
                 System.out.println("readline error");
            }

            parsedText = stringBuffer.toString();*/




        } catch (Exception e) {

            Notifier.getInstance().giveMessage(e.getMessage(), 3);
             System.out.println("error 3 in pdf");
            
        } finally {
            try {
                if (cosDoc != null) {
                    cosDoc.close();
                }
                if (pdDoc != null) {
                    pdDoc.close();
                }
            } catch (Exception e1) {
                Notifier.getInstance().giveMessage(e1.getMessage(), 3);
                 System.out.println("finally error");
            }
            //    return null;
        }
        //System.out.println("Done.");
        
        return formartString(parsedText);
    }

    private String convertRTFtoString(String Filename) {
        String parsedText = "";
        File f = new File(Filename);
        JTextPane text = new JTextPane();

        try {
            rtfKit.read(new FileInputStream(f), text.getStyledDocument(), 0);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } catch (BadLocationException ex) {
        }

        parsedText = text.getText();

        return formartString(parsedText);

    }

    private void writeTexttoFile(String pdfText, String fileName) {

        try {
            PrintWriter pw = new PrintWriter(fileName);
            pw.print(pdfText);
            pw.close();
        } catch (Exception e) {
            Notifier.getInstance().giveMessage(e.getMessage(), 3);
        }
        //System.out.println("Done.");
    }

    private String formartString(String filestring) {

        int count=0;
        String str;
        SceneElement scne = null;
        StringBuilder SceneString = new StringBuilder();

        BufferedReader reader = new BufferedReader(new StringReader(filestring));
        StringBuilder stringBuffer = new StringBuilder();

        try {
            int lines = 0;
            while ((str = reader.readLine()) != null) {

                if (str.length() > 0) //check if the string is a scene header
                {
                    if (StringUtils.substring(str, 0, 4).equalsIgnoreCase("EXT.") || StringUtils.substring(str, 0, 4).equalsIgnoreCase("INT.")) {
                        count++;
                        stringBuffer.append(NEWLINE).append("\t\t" + str).append(NEWLINE + NEWLINE);
                        scne=new SceneElement(StringUtils.substring(str, 4, str.length()).toUpperCase());
                        scne.setSceneType(StringUtils.substring(str, 0, 3).toLowerCase());
                        scne.setSceneNumber(count);
                        int startIndex[] = {lines, 0};
                        scne.setSceneStartIndex(startIndex);
//                        Scenes = Scenes + 1;
                       // sceneDetails.addElement(scne);
                       // ScenesList.add(scne);
                        lines++;
                        //destroy the scne object to allow new values to be set
                        ///modifications
                         if(lines>0) {
                             scne.setSceneContent(SceneString.toString());
                             String [] num=SceneString.toString().split("\n");
                             scne.setSceneSizeInLines(num.length);
                             ScenesList.add(scne);
                             lines=0;SceneString.setLength(0);
                         }
                            lines++;
                            scne = null;
                            SceneString.append(NEWLINE).append("\t\t").append(str).append(NEWLINE);

                    } //check if the string is a title or an actor,depending on script format
                    else if (StringUtils.isAllUpperCase(str)) {
                        stringBuffer.append(NEWLINE);
                        stringBuffer.append("\t").append(str);
                        //hapa
                         SceneString.append(NEWLINE).append("\t").append(str);
                    } else {
                        stringBuffer.append("").append(str);
                        //hapa
                        SceneString.append(str);
                    }
                }
                stringBuffer.append(NEWLINE);
                SceneString.append(NEWLINE);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        String formatedString = stringBuffer.toString();

        return formatedString;
    }

    public String readFile(String filename) {
        try {
            String text = "";
            Charset utf8 = Charset.forName("UTF-8");
            StringBuilder contents = new StringBuilder();
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream(filename);
            // Get the object of DataInputStream
            DataInputStream input = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(input, utf8));


            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                //append lines to string builder
                contents.append(strLine).append(System.getProperty("line.separator"));


            }
            text = contents.toString();
            //Close the input stream
            input.close();

            return text;
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }
}
