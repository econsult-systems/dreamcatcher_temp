/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dreamcatcher.editorsuite;

import writer.ProjectSettingsException;
import writer.SceneElement;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;
import org.dreamcatcher.breakdown.BreakdownTopComponent;
import org.dreamcatcher.util.APIObject;
import writer.Breakdown;
import org.dreamcatcher.util.Notifier;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import writer.TaggedItem;
import writer.XMLManager;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//org.dreamcatcher.editorsuite//ScriptViewer//EN",
autostore = false)
@TopComponent.Description(preferredID = "sceneViewerTopComponent",
iconBase = "org/dreamcatcher/customEditor/ViewScript.png",
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.dreamcatcher.filesAPI.Projects.sceneViewerTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ScriptViewerAction",
preferredID = "ScriptViewerTopComponent")
public final class ScriptViewerTopComponent extends TopComponent {

    private final InstanceContent content = new InstanceContent();
    public String Script = "", projectName="";
    String[] initStyles = {"bold", "italic", "bold", "small", "large",
        "regular", "button", "regular", "icon",
        "regular"
    };
    private static int actors = 0, animals = 0, cameras = 0, music = 0, sound = 0, makeups = 0, adLabor = 0, vehicles = 0, specialEffects = 0, props = 0, security = 0, wardrobe = 0, animalWranglers = 0, artDepartments = 0, greenerys = 0, ve = 0, sequipment = 0, mechanicalEffects = 0, misc = 0, dress = 0, stunt = 0;
    BreakdownTopComponent breakDown = new BreakdownTopComponent();
    //private Vector totalscenes = new Vector<SceneElement>();
    public DefaultComboBoxModel defaultModel;
    private int sceneNumbers;
    private int currentScene = 0;
    private ArrayList ScenesAppearance = new ArrayList<String>();
    public static ArrayList SceneContent = new ArrayList<SceneElement>();
    private String [] taggedItems;

    public ScriptViewerTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(ScriptViewerTopComponent.class, "CTL_ScriptViewerTopComponent"));
        setToolTipText(NbBundle.getMessage(ScriptViewerTopComponent.class, "HINT_ScriptViewerTopComponent"));
    
    }

    public ScriptViewerTopComponent(String script, int scenes, String name, ArrayList Content) throws ScriptLoadError, TagHighlightException {
        initComponents();
        setName(name);
        setToolTipText(NbBundle.getMessage(ScriptViewerTopComponent.class, "HINT_sceneViewerTopComponent"));

        SceneContent = Content;
        //Bad programming
        //Ensure all elements accessing this will get the correct scenes data
        Breakdown.breakdown(SceneContent);
        Script = script;
        //set pages
        PageableEditorKit page = new PageableEditorKit();
        page.setPageWidth(575);//595
        page.setPageHeight(842);
        page.scenes = scenes;
        sceneNumbers = scenes;
        //totalscenes=v;
        jTextPane1.setEditorKit(page);
        StyledDocument doc = jTextPane1.getStyledDocument();

        Object Scene[] = Content.toArray();
        //String initString = Scene[0].toString();

        try {


            doc.insertString(doc.getLength(), script, doc.getStyle(initStyles[0]));


        } catch (BadLocationException ble) {
            throw new ScriptLoadError("Script could not be loaded correctly" + ble.getMessage());
        }

        jTextPane1.setCaretPosition(0);
        jTextPane1.setComponentPopupMenu(jPopupMenu1);

        jTextPane1.addMouseListener(
                new MouseAdapter() {

                    public void mousePressed(MouseEvent e) {
                        checkForTriggerEvent(e);
                    }

                    public void mouseReleased(MouseEvent e) {
                        checkForTriggerEvent(e);
                    }

                    private void checkForTriggerEvent(MouseEvent e) {
                        if (e.isPopupTrigger()) {
                            jPopupMenu1.show(e.getComponent(),
                                    e.getX(), e.getY());
                        }
                    }
                });


        Font font = new Font("Courier", Font.PLAIN, 12);
        jTextPane1.setFont(font);
        getElements();
        breakDown.open();
        associateLookup(new AbstractLookup(content));

        //set up the initial scene numbers
        
        BtnPreviousScene.setEnabled(false);
        BtnNextScene.setEnabled(false);
        
        projectName=name;
    }

    public void open(String script, int scenes, String name, ArrayList SceneContent) throws ScriptLoadError, TagHighlightException {

        ScriptViewerTopComponent ScriptViewerTopComponent = new ScriptViewerTopComponent(script, scenes, name, SceneContent);
        ScriptViewerTopComponent.open();
        ScriptViewerTopComponent.requestActive();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        Actor = new javax.swing.JMenuItem();
        Animal = new javax.swing.JMenuItem();
        Camera = new javax.swing.JMenuItem();
        Makeup = new javax.swing.JMenuItem();
        Music = new javax.swing.JMenuItem();
        Sound = new javax.swing.JMenuItem();
        Props = new javax.swing.JMenuItem();
        Security = new javax.swing.JMenuItem();
        SpecialEffects = new javax.swing.JMenuItem();
        Vehicle = new javax.swing.JMenuItem();
        Wardrobe = new javax.swing.JMenuItem();
        additionalLabor = new javax.swing.JMenuItem();
        ZAddNew = new javax.swing.JMenuItem();
        animalWrangler = new javax.swing.JMenuItem();
        artDepartment = new javax.swing.JMenuItem();
        greenery = new javax.swing.JMenuItem();
        mechanicalEffect = new javax.swing.JMenuItem();
        miscellaneous = new javax.swing.JMenuItem();
        setDressing = new javax.swing.JMenuItem();
        specialEquipment = new javax.swing.JMenuItem();
        stunts = new javax.swing.JMenuItem();
        visualEffects = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        ToggleSheetView = new javax.swing.JToggleButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        ToggleSceneView = new javax.swing.JToggleButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        BtnPreviousScene = new javax.swing.JButton();
        BtnNextScene = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(Actor, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.Actor.text")); // NOI18N
        Actor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActorActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Actor);

        org.openide.awt.Mnemonics.setLocalizedText(Animal, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.Animal.text")); // NOI18N
        Animal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnimalActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Animal);

        org.openide.awt.Mnemonics.setLocalizedText(Camera, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.Camera.text")); // NOI18N
        Camera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CameraActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Camera);

        org.openide.awt.Mnemonics.setLocalizedText(Makeup, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.Makeup.text")); // NOI18N
        Makeup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MakeupActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Makeup);

        org.openide.awt.Mnemonics.setLocalizedText(Music, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.Music.text")); // NOI18N
        Music.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MusicActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Music);

        org.openide.awt.Mnemonics.setLocalizedText(Sound, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.Sound.text")); // NOI18N
        Sound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SoundActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Sound);

        org.openide.awt.Mnemonics.setLocalizedText(Props, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.Props.text")); // NOI18N
        Props.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PropsActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Props);

        org.openide.awt.Mnemonics.setLocalizedText(Security, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.Security.text")); // NOI18N
        Security.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SecurityActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Security);

        org.openide.awt.Mnemonics.setLocalizedText(SpecialEffects, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.SpecialEffects.text")); // NOI18N
        SpecialEffects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SpecialEffectsActionPerformed(evt);
            }
        });
        jPopupMenu1.add(SpecialEffects);

        org.openide.awt.Mnemonics.setLocalizedText(Vehicle, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.Vehicle.text")); // NOI18N
        Vehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VehicleActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Vehicle);

        org.openide.awt.Mnemonics.setLocalizedText(Wardrobe, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.Wardrobe.text")); // NOI18N
        Wardrobe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WardrobeActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Wardrobe);

        org.openide.awt.Mnemonics.setLocalizedText(additionalLabor, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.additionalLabor.text")); // NOI18N
        additionalLabor.setName("additionalLabor"); // NOI18N
        additionalLabor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                additionalLaborActionPerformed(evt);
            }
        });
        jPopupMenu1.add(additionalLabor);

        org.openide.awt.Mnemonics.setLocalizedText(ZAddNew, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.ZAddNew.text")); // NOI18N
        ZAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZAddNewActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ZAddNew);

        org.openide.awt.Mnemonics.setLocalizedText(animalWrangler, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.animalWrangler.text")); // NOI18N
        animalWrangler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                animalWranglerActionPerformed(evt);
            }
        });
        jPopupMenu1.add(animalWrangler);

        org.openide.awt.Mnemonics.setLocalizedText(artDepartment, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.artDepartment.text")); // NOI18N
        artDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                artDepartmentActionPerformed(evt);
            }
        });
        jPopupMenu1.add(artDepartment);

        org.openide.awt.Mnemonics.setLocalizedText(greenery, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.greenery.text")); // NOI18N
        greenery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                greeneryActionPerformed(evt);
            }
        });
        jPopupMenu1.add(greenery);

        org.openide.awt.Mnemonics.setLocalizedText(mechanicalEffect, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.mechanicalEffect.text")); // NOI18N
        mechanicalEffect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mechanicalEffectActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mechanicalEffect);

        org.openide.awt.Mnemonics.setLocalizedText(miscellaneous, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.miscellaneous.text")); // NOI18N
        miscellaneous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miscellaneousActionPerformed(evt);
            }
        });
        jPopupMenu1.add(miscellaneous);

        org.openide.awt.Mnemonics.setLocalizedText(setDressing, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.setDressing.text")); // NOI18N
        setDressing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setDressingActionPerformed(evt);
            }
        });
        jPopupMenu1.add(setDressing);

        org.openide.awt.Mnemonics.setLocalizedText(specialEquipment, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.specialEquipment.text")); // NOI18N
        specialEquipment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                specialEquipmentActionPerformed(evt);
            }
        });
        jPopupMenu1.add(specialEquipment);

        org.openide.awt.Mnemonics.setLocalizedText(stunts, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.stunts.text")); // NOI18N
        stunts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stuntsActionPerformed(evt);
            }
        });
        jPopupMenu1.add(stunts);

        org.openide.awt.Mnemonics.setLocalizedText(visualEffects, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.visualEffects.text")); // NOI18N
        visualEffects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visualEffectsActionPerformed(evt);
            }
        });
        jPopupMenu1.add(visualEffects);

        jTabbedPane1.setMaximumSize(new java.awt.Dimension(160, 32767));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(56, 66));

        jToolBar1.setRollover(true);
        jToolBar1.setMinimumSize(new java.awt.Dimension(56, 32));

        ToggleSheetView.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(ToggleSheetView, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.ToggleSheetView.text")); // NOI18N
        ToggleSheetView.setFocusable(false);
        ToggleSheetView.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ToggleSheetView.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ToggleSheetView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ToggleSheetViewActionPerformed(evt);
            }
        });
        jToolBar1.add(ToggleSheetView);
        jToolBar1.add(jSeparator1);

        org.openide.awt.Mnemonics.setLocalizedText(ToggleSceneView, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.ToggleSceneView.text")); // NOI18N
        ToggleSceneView.setFocusable(false);
        ToggleSceneView.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ToggleSceneView.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ToggleSceneView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ToggleSceneViewActionPerformed(evt);
            }
        });
        jToolBar1.add(ToggleSceneView);
        jToolBar1.add(jSeparator2);
        jToolBar1.add(jSeparator4);

        org.openide.awt.Mnemonics.setLocalizedText(BtnPreviousScene, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.BtnPreviousScene.text")); // NOI18N
        BtnPreviousScene.setFocusable(false);
        BtnPreviousScene.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnPreviousScene.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnPreviousScene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPreviousSceneActionPerformed(evt);
            }
        });
        jToolBar1.add(BtnPreviousScene);

        org.openide.awt.Mnemonics.setLocalizedText(BtnNextScene, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.BtnNextScene.text")); // NOI18N
        BtnNextScene.setFocusable(false);
        BtnNextScene.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnNextScene.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnNextScene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNextSceneActionPerformed(evt);
            }
        });
        jToolBar1.add(BtnNextScene);

        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jScrollPane2.setViewportView(jTextPane2);

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.jLabel1.text")); // NOI18N

        jList3.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(jList3);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.jLabel2.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .addComponent(jLabel2))))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(ScriptViewerTopComponent.class, "ScriptViewerTopComponent.jPanel2.TabConstraints.tabTitle_1"), jPanel2); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

        private void ActorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActorActionPerformed
            try {
                String actorName = jTextPane1.getSelectedText();
                String[] scenes = {"", ""};
                // TODO add your handling code here:

                populateBreakdown(jTextPane1, actorName, Color.RED);
                breakDown.write(actorName, "ACTOR", process(ScenesAppearance), "An actor Item");
                actors++;
                APIObject obj = new APIObject("Actor", actors);
                content.set(Collections.singleton(obj), null);
            } catch (Exception ex) {
                Notifier.getInstance().giveMessage("No text Selected! Please sselect text to tag.",3);
               
            }
           category();
    }//GEN-LAST:event_ActorActionPerformed

    private void AnimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnimalActionPerformed
        try {
            // TODO add your handling code here:
            //populateBreakdown(jTextPane1, jTextPane1.getSelectedText(), Color.CYAN);
            String animalName = jTextPane1.getSelectedText();
            String[] scenes = {"", ""};
            // TODO add your handling code here:
            animals++;
            populateBreakdown(jTextPane1, animalName, Color.CYAN);
            breakDown.write(animalName, "ANIMAL", process(ScenesAppearance), "Animal on the script");


            APIObject obj = new APIObject("Animal", animals);
            content.set(Collections.singleton(obj), null);
        } catch (Exception ex) {
            Notifier.getInstance().giveMessage("No text selected! Please select text to tag.", 3);
        }
        category();
    }//GEN-LAST:event_AnimalActionPerformed

    private void CameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CameraActionPerformed
        try {
            // TODO add your handling code here:
            //populateBreakdown(jTextPane1, jTextPane1.getSelectedText(), Color.GREEN);
            String cameraName = jTextPane1.getSelectedText();
            String[] scenes = {"", ""};
            // TODO add your handling code here:
            cameras++;
            populateBreakdown(jTextPane1, cameraName, Color.GREEN);
            breakDown.write(cameraName, "CAMERA", process(ScenesAppearance), "A camera Item");


            APIObject obj = new APIObject("Camera", cameras);
            content.set(Collections.singleton(obj), null);
        } catch (Exception ex) {
            Notifier.getInstance().giveMessage(ex.getLocalizedMessage(), 3);
        }
    }//GEN-LAST:event_CameraActionPerformed

    private void MakeupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MakeupActionPerformed
        try {
            // TODO add your handling code here:
            //highlight(jTextPane1, jTextPane1.getSelectedText(), Color.LIGHT_GRAY);
            String makeupName = jTextPane1.getSelectedText();
            String[] scenes = {"", ""};
            // TODO add your handling code here:
            makeups++;
            populateBreakdown(jTextPane1, makeupName, Color.LIGHT_GRAY);
            breakDown.write(makeupName, "MAKEUP", process(ScenesAppearance), "A Make up Item");


            APIObject obj = new APIObject("Makeup", makeups);
            content.set(Collections.singleton(obj), null);
        } catch (Exception ex) {
            Notifier.getInstance().giveMessage("No text selected! Please select text to tag.", 3);
        }

    }//GEN-LAST:event_MakeupActionPerformed

    private void MusicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MusicActionPerformed
        try {
            // TODO add your handling code here:
            // highlight(jTextPane1, jTextPane1.getSelectedText(), Color.MAGENTA);
            String musicName = jTextPane1.getSelectedText();
            String[] scenes = {"", ""};
            // TODO add your handling code here:
            music++;
            populateBreakdown(jTextPane1, musicName, Color.MAGENTA);
            breakDown.write(musicName, "MUSIC", process(ScenesAppearance), "A Music Item");


            APIObject obj = new APIObject("Music", music);
            content.set(Collections.singleton(obj), null);
        } catch (Exception ex) {
            Notifier.getInstance().giveMessage("No text selected! Please select text to tag.", 3);
        }

    }//GEN-LAST:event_MusicActionPerformed

    private void SoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SoundActionPerformed
        try {
            // TODO add your handling code here:
            // highlight(jTextPane1, jTextPane1.getSelectedText(), Color.LIGHT_GRAY);
            String soundName = jTextPane1.getSelectedText();
            String[] scenes = {"", ""};
            // TODO add your handling code here:
            sound++;
            populateBreakdown(jTextPane1, soundName, Color.LIGHT_GRAY);
            breakDown.write(soundName, "SOUND", process(ScenesAppearance), "A Sound Item");


            APIObject obj = new APIObject("Sound", sound);
            content.set(Collections.singleton(obj), null);
        } catch (Exception ex) {
            Notifier.getInstance().giveMessage("No text selected! Please select text to tag.", 3);
        }

    }//GEN-LAST:event_SoundActionPerformed

    private void PropsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PropsActionPerformed
        try {
            // TODO add your handling code here:
            //highlight(jTextPane1, jTextPane1.getSelectedText(), Color.PINK);
            String propName = jTextPane1.getSelectedText();
            String[] scenes = {"", ""};
            // TODO add your handling code here:
            props++;
            populateBreakdown(jTextPane1, propName, Color.PINK);
            breakDown.write(propName, "PROP", process(ScenesAppearance), "A Prop Item");


            APIObject obj = new APIObject("Props", props);
            content.set(Collections.singleton(obj), null);
        } catch (Exception ex) {
            Notifier.getInstance().giveMessage("No text selected! Please select text to tag.", 3);
        }

    }//GEN-LAST:event_PropsActionPerformed

    private void SecurityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SecurityActionPerformed
        try {
            // TODO add your handling code here:
            //highlight(jTextPane1, jTextPane1.getSelectedText(), Color.ORANGE);
            String securityName = jTextPane1.getSelectedText();
            String[] scenes = {"", ""};
            // TODO add your handling code here:
            security++;
            populateBreakdown(jTextPane1, securityName, Color.ORANGE);
            breakDown.write(securityName, "SECURITY", process(ScenesAppearance), "A Security Item");


            APIObject obj = new APIObject("Security", security);
            content.set(Collections.singleton(obj), null);
        } catch (Exception ex) {
            Notifier.getInstance().giveMessage("No text selected! Please select text to tag.", 3);
        }

    }//GEN-LAST:event_SecurityActionPerformed

    private void SpecialEffectsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SpecialEffectsActionPerformed
        try {
            // TODO add your handling code here:
            String specialEffectName = jTextPane1.getSelectedText();
            String[] scenes = {"", ""};
            // TODO add your handling code here:
            specialEffects++;
            populateBreakdown(jTextPane1, specialEffectName, Color.LIGHT_GRAY);
            breakDown.write(specialEffectName, "SPECIALEFFECTS", process(ScenesAppearance), "A Special Effect Item");


            APIObject obj = new APIObject("Special Effects", specialEffects);
            content.set(Collections.singleton(obj), null);
        } catch (Exception ex) {
            Notifier.getInstance().giveMessage("No text selected! Please select text to tag.", 3);
        }

    }//GEN-LAST:event_SpecialEffectsActionPerformed

    private void VehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VehicleActionPerformed
        try {
            // TODO add your handling code here:
            //highlight(jTextPane1, jTextPane1.getSelectedText(), Color.CYAN);
            String vehicleName = jTextPane1.getSelectedText();
            String[] scenes = {"", ""};
            // TODO add your handling code here:
            vehicles++;
            populateBreakdown(jTextPane1, vehicleName, Color.CYAN);
            breakDown.write(vehicleName, "VEHICLE", process(ScenesAppearance), "A Vehicle Item");


            APIObject obj = new APIObject("Vehicles", vehicles);
            content.set(Collections.singleton(obj), null);
        } catch (Exception ex) {
            Notifier.getInstance().giveMessage("No text selected! Please select text to tag.", 3);
        }

    }//GEN-LAST:event_VehicleActionPerformed

    private void WardrobeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WardrobeActionPerformed
        try {
            // TODO add your handling code here:
            //highlight(jTextPane1, jTextPane1.getSelectedText(), Color.YELLOW);
            String wardrobeName = jTextPane1.getSelectedText();
            String[] scenes = {"", ""};
            // TODO add your handling code here:
            wardrobe++;
            populateBreakdown(jTextPane1, wardrobeName, Color.YELLOW);
            breakDown.write(wardrobeName, "WARDROBE", process(ScenesAppearance), "A Wardrobe Item");


            APIObject obj = new APIObject("Wardrobe", wardrobe);
            content.set(Collections.singleton(obj), null);
        } catch (Exception ex) {
            Notifier.getInstance().giveMessage("No text selected! Please select text to tag.", 3);
        }

    }//GEN-LAST:event_WardrobeActionPerformed

private void additionalLaborActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_additionalLaborActionPerformed
    try {
        // TODO add your additional labor handling code here:
        String adLaborName = jTextPane1.getSelectedText();
        String[] scenes = {"", ""};
        // TODO add your handling code here:
        adLabor++;
        populateBreakdown(jTextPane1, adLaborName, Color.LIGHT_GRAY);
        breakDown.write(adLaborName, "ADDITIONALLABOUR", process(ScenesAppearance), "An Additional Labor Item");


        APIObject obj = new APIObject("AdditonalLabor", adLabor);
        content.set(Collections.singleton(obj), null);
    } catch (Exception ex) {
        Notifier.getInstance().giveMessage("No text selected! Please select text to tag.", 3);
    }

}//GEN-LAST:event_additionalLaborActionPerformed

private void ZAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZAddNewActionPerformed
// TODO add your handling code here:
    //Create a new taggavble item
    //String addNewName=jTextPane1.getSelectedText();
    //String [] scenes={"",""};
    // TODO add your handling code here:
    //addNew++;
    //highlight(jTextPane1, addNewName, Color.ORANGE);
    //breakDown.write("Security",addNewName,scenes,"A Security Item");
    //APIObject obj = new APIObject("Security", security);
    //content.set(Collections.singleton (obj), null);
}//GEN-LAST:event_ZAddNewActionPerformed

private void animalWranglerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_animalWranglerActionPerformed
    try {
        // TODO add your handling code here:
        String animalWranglerName = jTextPane1.getSelectedText();
        String[] scenes = {"", ""};
        // TODO add your handling code here:
        animalWranglers++;
        populateBreakdown(jTextPane1, animalWranglerName, Color.BLUE);
        breakDown.write(animalWranglerName, "ANIMALWRANGLER", process(ScenesAppearance), "An animal Wrangler Item");


        APIObject obj = new APIObject("Animal Wrangler", animalWranglers);
        content.set(Collections.singleton(obj), null);
    } catch (Exception ex) {
        Notifier.getInstance().giveMessage("No text selected! Please select text to tag.", 3);
    }
}//GEN-LAST:event_animalWranglerActionPerformed

private void artDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_artDepartmentActionPerformed
    try {
        // TODO add your handling code here:
        String artDepartmentName = jTextPane1.getSelectedText();
        String[] scenes = {"", ""};
        // TODO add your handling code here:
        artDepartments++;
        populateBreakdown(jTextPane1, artDepartmentName, Color.BLUE);
        breakDown.write(artDepartmentName, "ARTDEPARTMENT", process(ScenesAppearance), "An Art department Item");


        APIObject obj = new APIObject("Art Department", artDepartments);
        content.set(Collections.singleton(obj), null);
    } catch (Exception ex) {
        Notifier.getInstance().giveMessage("No text selected! Please select text to tag.", 3);
    }
}//GEN-LAST:event_artDepartmentActionPerformed

private void greeneryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_greeneryActionPerformed
    try {
        // TODO add your handling code here:
        String greeneryName = jTextPane1.getSelectedText();
        String[] scenes = {"", ""};
        // TODO add your handling code here:
        greenerys++;
        populateBreakdown(jTextPane1, greeneryName, Color.BLUE);
        breakDown.write(greeneryName, "GREENERY", process(ScenesAppearance), "A Greenery Item");


        APIObject obj = new APIObject("Greenery", greenerys);
        content.set(Collections.singleton(obj), null);
    } catch (Exception ex) {
        Notifier.getInstance().giveMessage("No text selected! Please select text to tag.", 3);
    }
}//GEN-LAST:event_greeneryActionPerformed

private void mechanicalEffectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mechanicalEffectActionPerformed
    try {
        // TODO add your handling code here:
        String mechEffectName = jTextPane1.getSelectedText();
        String[] scenes = {"", ""};
        // TODO add your handling code here:
        mechanicalEffects++;
        populateBreakdown(jTextPane1, mechEffectName, Color.BLUE);
        breakDown.write(mechEffectName, "MECHANICALEFFECTS", process(ScenesAppearance), "A Mechanical Effect Item");


        APIObject obj = new APIObject("Mechanical Effects", mechanicalEffects);
        content.set(Collections.singleton(obj), null);
    } catch (Exception ex) {
        Notifier.getInstance().giveMessage("No text selected! Please select text to tag.", 3);
    }
}//GEN-LAST:event_mechanicalEffectActionPerformed

private void miscellaneousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miscellaneousActionPerformed
    try {
        // TODO add your handling code here:
        String miscName = jTextPane1.getSelectedText();
        String[] scenes = {"", ""};
        // TODO add your handling code here:
        misc++;
        populateBreakdown(jTextPane1, miscName, Color.BLUE);
        breakDown.write(miscName, "MISCELLANEOUS", process(ScenesAppearance), "A Miscellaneous Item");


        APIObject obj = new APIObject("Miscellaneous", misc);
        content.set(Collections.singleton(obj), null);
    } catch (Exception ex) {
        Notifier.getInstance().giveMessage("No text selected! Please select text to tag.", 3);
    }
}//GEN-LAST:event_miscellaneousActionPerformed

private void setDressingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setDressingActionPerformed
    try {
        // TODO add your handling code here:
        String dressingName = jTextPane1.getSelectedText();
        String[] scenes = {"", ""};
        // TODO add your handling code here:
        dress++;
        populateBreakdown(jTextPane1, dressingName, Color.BLUE);
        breakDown.write(dressingName, "DRESSING", process(ScenesAppearance), "A Dressing Item");


        APIObject obj = new APIObject("Dressing", dress);
        content.set(Collections.singleton(obj), null);
    } catch (Exception ex) {
        Notifier.getInstance().giveMessage("No text selected! Please select text to tag.", 3);
    }
}//GEN-LAST:event_setDressingActionPerformed

private void specialEquipmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specialEquipmentActionPerformed
    try {
        // TODO add your handling code here:
        String specialEquipmentName = jTextPane1.getSelectedText();
        String[] scenes = {"", ""};
        // TODO add your handling code here:
        sequipment++;
        populateBreakdown(jTextPane1, specialEquipmentName, Color.BLUE);
        breakDown.write(specialEquipmentName, "SPECIALEQUIPMENT", process(ScenesAppearance), "A Special Equipment Item");


        APIObject obj = new APIObject("Special Equipment", sequipment);
        content.set(Collections.singleton(obj), null);
    } catch (Exception ex) {
        Notifier.getInstance().giveMessage("No text selected! Please select text to tag.", 3);
    }
}//GEN-LAST:event_specialEquipmentActionPerformed

private void stuntsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stuntsActionPerformed
    try {
        // TODO add your handling code here:
        String stuntName = jTextPane1.getSelectedText();
        String[] scenes = {"", ""};
        // TODO add your handling code here:
        stunt++;
        populateBreakdown(jTextPane1, stuntName, Color.BLUE);
        breakDown.write(stuntName, "STUNT", process(ScenesAppearance), "A Stunt Item");


        APIObject obj = new APIObject("Stunts", stunt);
        content.set(Collections.singleton(obj), null);
    } catch (Exception ex) {
        Notifier.getInstance().giveMessage("No text selected! Please select text to tag.", 3);
    }
}//GEN-LAST:event_stuntsActionPerformed

private void visualEffectsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visualEffectsActionPerformed
    try {
        // TODO add your handling code here:
        String veName = jTextPane1.getSelectedText();
        String[] scenes = {"", ""};
        // TODO add your handling code here:
        ve++;
        populateBreakdown(jTextPane1, veName, Color.BLUE);
        breakDown.write(veName, "VISUALEFFECT", process(ScenesAppearance), "A Visual Effects Item");


        APIObject obj = new APIObject("Visual Effects", ve);
        content.set(Collections.singleton(obj), null);
    } catch (Exception ex) {
        Notifier.getInstance().giveMessage("No text selected! Please select text to tag.", 3);
    }
}//GEN-LAST:event_visualEffectsActionPerformed

    private void ToggleSheetViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ToggleSheetViewActionPerformed
        // TODO add your handling code here:
        
    BtnPreviousScene.setEnabled(false);
    BtnNextScene.setEnabled(false);
    ToggleSceneView.setSelected(false);
    ToggleSheetView.setSelected(true);
    StyledDocument doc = jTextPane1.getStyledDocument();
    try {
        doc.remove(0, doc.getLength());
        doc.insertString(doc.getLength(), Script, doc.getStyle(initStyles[0]));
        jTextPane1.setCaretPosition(0);
    } catch (BadLocationException ble) {
        //throw new ScriptLoadError("Script could not be loaded correctly"+ble.getMessage());
    }
    APIObject apiobj = new APIObject("STATUS");
    apiobj.setToggle("disable");
    content.set(Collections.singleton(apiobj), null);
    getElements();
    }//GEN-LAST:event_ToggleSheetViewActionPerformed

    private void ToggleSceneViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ToggleSceneViewActionPerformed
        // TODO add your handling code here:
        
        
    BtnNextScene.setEnabled(true);
    ToggleSceneView.setSelected(true);
    ToggleSheetView.setSelected(false);
    StyledDocument doc = jTextPane1.getStyledDocument();
    Object Scene[] = SceneContent.toArray();
    SceneElement sceneelement = (SceneElement) Scene[0];
    String initString = sceneelement.getSceneContent();


    try {
        doc.remove(0, doc.getLength());
        doc.insertString(doc.getLength(), initString, doc.getStyle(initStyles[0]));
        jTextPane1.setCaretPosition(0);
    } catch (BadLocationException ble) {
        //throw new ScriptLoadError("Script could not be loaded correctly"+ble.getMessage());
    }
    APIObject apiobj = new APIObject("STATUS");
    apiobj.setToggle("enable");
    content.set(Collections.singleton(apiobj), null);
    getElements();
    }//GEN-LAST:event_ToggleSceneViewActionPerformed

    private void BtnPreviousSceneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPreviousSceneActionPerformed
        // TODO add your handling code here:
        BtnNextScene.setEnabled(true);
        if (currentScene <= 1) {
        BtnPreviousScene.setEnabled(false);
        BtnNextScene.setEnabled(true);
    } else {
        BtnPreviousScene.setEnabled(true);
    }
    StyledDocument doc = jTextPane1.getStyledDocument();
    Object Scene[] = SceneContent.toArray();
    SceneElement sceneelement = (SceneElement) Scene[currentScene - 1];
    String initString = sceneelement.getSceneContent();
    currentScene = currentScene == 0 ? currentScene : currentScene - 1;


    try {
        doc.remove(0, doc.getLength());
        doc.insertString(doc.getLength(), initString, doc.getStyle(initStyles[0]));
        jTextPane1.setCaretPosition(0);


    } catch (BadLocationException ble) {
        //throw new ScriptLoadError("Script could not be loaded correctly"+ble.getMessage());
    }
    //addChangesToElement(sceneelement);
    sceneelement = (SceneElement) Scene[currentScene + 1];
    APIObject apiobjct = new APIObject(sceneelement);
    content.set(Collections.singleton(apiobjct), null);
    getElements();
    }//GEN-LAST:event_BtnPreviousSceneActionPerformed

    private void BtnNextSceneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNextSceneActionPerformed
        // TODO add your handling code here:
        
         StyledDocument doc = jTextPane1.getStyledDocument();
    Object Scene[] = SceneContent.toArray();
    SceneElement sceneelement = (SceneElement) Scene[currentScene + 1];
    String initString = sceneelement.getSceneContent();

    currentScene = currentScene == Scene.length - 1 ? currentScene : currentScene + 1;
    BtnPreviousScene.setEnabled(true);
    if (currentScene == Scene.length - 1) {
        BtnNextScene.setEnabled(false);
        BtnPreviousScene.setEnabled(true);
    } else {
        BtnNextScene.setEnabled(true);
    }


    try {
        doc.remove(0, doc.getLength());
        doc.insertString(doc.getLength(), initString, doc.getStyle(initStyles[0]));
        jTextPane1.setCaretPosition(0);

    } catch (BadLocationException ble) {
        //throw new ScriptLoadError("Script could not be loaded correctly"+ble.getMessage());
    }

    //addChangesToElement(sceneelement);
    sceneelement = (SceneElement) Scene[currentScene - 1];
    APIObject apiobjct = new APIObject(sceneelement);
    content.set(Collections.singleton(apiobjct), null);
    getElements();
    }//GEN-LAST:event_BtnNextSceneActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Actor;
    private javax.swing.JMenuItem Animal;
    private javax.swing.JButton BtnNextScene;
    private javax.swing.JButton BtnPreviousScene;
    private javax.swing.JMenuItem Camera;
    private javax.swing.JMenuItem Makeup;
    private javax.swing.JMenuItem Music;
    private javax.swing.JMenuItem Props;
    private javax.swing.JMenuItem Security;
    private javax.swing.JMenuItem Sound;
    private javax.swing.JMenuItem SpecialEffects;
    private javax.swing.JToggleButton ToggleSceneView;
    private javax.swing.JToggleButton ToggleSheetView;
    private javax.swing.JMenuItem Vehicle;
    private javax.swing.JMenuItem Wardrobe;
    private javax.swing.JMenuItem ZAddNew;
    private javax.swing.JMenuItem additionalLabor;
    private javax.swing.JMenuItem animalWrangler;
    private javax.swing.JMenuItem artDepartment;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JMenuItem greenery;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JList jList3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem mechanicalEffect;
    private javax.swing.JMenuItem miscellaneous;
    private javax.swing.JMenuItem setDressing;
    private javax.swing.JMenuItem specialEquipment;
    private javax.swing.JMenuItem stunts;
    private javax.swing.JMenuItem visualEffects;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
        defaultModel = new DefaultComboBoxModel();
        for (int i = 0; i < SceneContent.size(); i++) {
            try {
                SceneElement item = (SceneElement) SceneContent.get(i);
                defaultModel.addElement(item.getSceneName());
                if (!(XMLManager.getInstance().writeSceneDetail(item))) {
                    throw new SceneBreakdownException("The scenes could not be correctly broken down");
                }
            } catch (ProjectSettingsException ex) {
                Notifier.getInstance().giveMessage(ex.getLocalizedMessage(), 3);
            } catch (SceneBreakdownException sb) {
                Notifier.getInstance().giveMessage(sb.getLocalizedMessage(), 3);
            }
        }
        // APIObject obj = new APIObject(defaultModel);
        APIObject obj2 = new APIObject(SceneContent);
        // content.set(Collections.singleton(obj), null);
        content.set(Collections.singleton(obj2), null);
        breakdownReport(projectName);
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
        breakDown.close();
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    private void populateBreakdown(JTextPane jTextPane1, String[] stringsToTag, Color GRAY) throws TagHighlightException {
        for (int i = 0; i < stringsToTag.length; i++) {
            populateBreakdown(jTextPane1, stringsToTag[i], GRAY);
        }
    }

    private void populateSceneBreakdown(JTextComponent textComp, String pattern, Color color) throws TagHighlightException {
        Document doc = textComp.getDocument();
        String text;
        int number = 0;
        try {
            text = doc.getText(0, doc.getLength());
        } catch (BadLocationException ex) {
            throw new TagHighlightException(ex.getMessage());
        }

        //   Vector<String> appearances = new Vector();
        // if (pattern.startsWith("EXT") || pattern.startsWith("INT") || pattern.startsWith("INT/EXT") || pattern.startsWith("EXT/INT")) {
        int position = 0;
        //mark each occurrence as a scene
        //while ((position = text.indexOf(pattern, position)) >= 0) {
        // Create highlighter using private painter and apply around pattern
        //       appearances.addElement(pattern +"  "+ number);
        // position += pattern.length();
        //   number++;
        //}
        //  this.totalscenes=appearances;

        //} else
        try {

            Highlighter hilite = textComp.getHighlighter();
            int pos = 0;
            // Search for pattern
            while ((pos = text.indexOf(pattern, pos)) >= 0) {
                // Create highlighter using private painter and apply around pattern
                hilite.addHighlight(pos, pos + pattern.length(), new MyHighlightPainter(color));
                pos += pattern.length();
            }
        } catch (BadLocationException e) {
            throw new TagHighlightException("Text highlight error: " + e.getMessage());
        }

    }

    private void populateSceneBreakdown(JTextPane jTextPane1, String[] stringsToTag, Color GRAY) throws TagHighlightException {
        for (int i = 0; i < stringsToTag.length; i++) {
            populateSceneBreakdown(jTextPane1, stringsToTag[i], GRAY);
        }
    }

    private ArrayList<String> countOccurenceInScene(String pattern) {
        //Count the number of scenes that an item appears

        int number = 0;
        Object Scene[] = SceneContent.toArray();
        ArrayList<String> sceneapp = new ArrayList<String>();
        for (int i = 0; i < Scene.length; i++) {
            SceneElement sceneelement = (SceneElement) Scene[i];
            String initString = sceneelement.getSceneContent();
            //Look through each of these strings
            if (initString.contains(pattern.toUpperCase()) || initString.contains(pattern.toLowerCase())) {
                sceneapp.add(String.valueOf(i));
            }
        }
        return sceneapp;
    }

    private String[] process(ArrayList ScenesAppearance) {
        //Simple way to deal with arraylist
        String scenes[];
        scenes = new String[ScenesAppearance.toArray().length];
        for (int i = 0; i < ScenesAppearance.toArray().length; i++) {
            scenes[i] = (String) ScenesAppearance.get(i);
        }
        return scenes;
    }



    class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {

        public MyHighlightPainter(Color color) {
            super(color);
        }
    }

    // Creates highlights around all occurrences of pattern in textComp
    public void populateBreakdown(JTextComponent textComp, String pattern, Color color) throws TagHighlightException {
        // First remove all old highlights
        //removeHighlights(textComp);
        ScenesAppearance = countOccurenceInScene(pattern);
        Document doc = textComp.getDocument();
        String text;
        int number = 0;
        try {
            text = doc.getText(0, doc.getLength());
        } catch (BadLocationException ex) {
            throw new TagHighlightException("No text Selected! Please sselect text to tag.");
        }

        try {

            Highlighter hilite = textComp.getHighlighter();
            int pos = 0;
            // Search for pattern
            while ((pos = text.indexOf(pattern, pos)) >= 0) {
                // Create highlighter using private painter and apply around pattern
                hilite.addHighlight(pos, pos + pattern.length(), new MyHighlightPainter(color));
                pos += pattern.length();
            }
        } catch (BadLocationException e) {
            throw new TagHighlightException("Text highlight error: " + e.getMessage());
        }
        // }
    }
    
    
    public void getElements(){
        try{
            //Highlights scene headings
            String[] stringsToTag = {"INT.", "EXT."};
            populateSceneBreakdown(jTextPane1, stringsToTag, Color.LIGHT_GRAY);
            
             
            
            for(int i=0; i<SceneContent.size(); i++){
                SceneElement item = (SceneElement) SceneContent.get(i);
                populateSceneBreakdown(jTextPane1, item.getSceneName(), Color.LIGHT_GRAY);
            }
           
            //HighlighTaggedItems
            ArrayList<TaggedItem> tElements = new ArrayList<TaggedItem>();
            tElements= XMLManager.getInstance().getAllTaggedElements();
            for(int i=0; i<tElements.size(); i++){
                if ("SCENE".equals(tElements.get(i).getItemType())){
                    populateSceneBreakdown(jTextPane1, tElements.get(i).getItemName(), Color.LIGHT_GRAY);
                } else 
                if ("ACTOR".equals(tElements.get(i).getItemType())){
                    populateSceneBreakdown(jTextPane1, tElements.get(i).getItemName(), Color.RED);
                } else 
                if ("ANIMAL".equals(tElements.get(i).getItemType())){
                    populateSceneBreakdown(jTextPane1, tElements.get(i).getItemName(), Color.GREEN);
                }else 
                if ("CAMERA".equals(tElements.get(i).getItemType())){
                    populateSceneBreakdown(jTextPane1, tElements.get(i).getItemName(), Color.LIGHT_GRAY);
                }else 
                if ("MUSIC".equals(tElements.get(i).getItemType())){
                    populateSceneBreakdown(jTextPane1, tElements.get(i).getItemName(), Color.MAGENTA);
                }else 
                if ("SOUND".equals(tElements.get(i).getItemType())){
                    populateSceneBreakdown(jTextPane1, tElements.get(i).getItemName(), Color.CYAN);
                }else 
                if ("PROP".equals(tElements.get(i).getItemType())){
                    populateSceneBreakdown(jTextPane1, tElements.get(i).getItemName(), Color.CYAN);
                }else 
                if ("SECURITY".equals(tElements.get(i).getItemType())){
                    populateSceneBreakdown(jTextPane1, tElements.get(i).getItemName(),Color.LIGHT_GRAY);
                }else 
                if ("VEHICLE".equals(tElements.get(i).getItemType())){
                    populateSceneBreakdown(jTextPane1, tElements.get(i).getItemName(), Color.CYAN);
                }else 
                if ("WARDROBE".equals(tElements.get(i).getItemType())){
                    populateSceneBreakdown(jTextPane1, tElements.get(i).getItemName(), Color.YELLOW);
                }else 
                if ("ADDITIONALLABOUR".equals(tElements.get(i).getItemType())){
                    populateSceneBreakdown(jTextPane1, tElements.get(i).getItemName(), Color.LIGHT_GRAY);
                }else 
                if ("ANIMALWRANGLER".equals(tElements.get(i).getItemType())){
                    populateSceneBreakdown(jTextPane1, tElements.get(i).getItemName(), Color.BLUE);
                }else 
                if ("ARTDEPARTMENT".equals(tElements.get(i).getItemType())){
                    populateSceneBreakdown(jTextPane1, tElements.get(i).getItemName(), Color.BLUE);
                }else 
                if ("GREENERY".equals(tElements.get(i).getItemType())){
                    populateSceneBreakdown(jTextPane1, tElements.get(i).getItemName(), Color.BLUE);
                }else 
                if ("MECHANICALEFFECTS".equals(tElements.get(i).getItemType())){
                    populateSceneBreakdown(jTextPane1, tElements.get(i).getItemName(), Color.CYAN);
                }else 
                if ("MISCELLANEOUS".equals(tElements.get(i).getItemType())){
                    populateSceneBreakdown(jTextPane1, tElements.get(i).getItemName(), Color.CYAN);
                }else 
                if ("DRESSING".equals(tElements.get(i).getItemType())){
                    populateSceneBreakdown(jTextPane1, tElements.get(i).getItemName(), Color.YELLOW);
                }else 
                if ("SPECIALEQUIPMENT".equals(tElements.get(i).getItemType())){
                    populateSceneBreakdown(jTextPane1, tElements.get(i).getItemName(), Color.CYAN);
                }else 
                if ("STUNT".equals(tElements.get(i).getItemType())){
                    populateSceneBreakdown(jTextPane1, tElements.get(i).getItemName(), Color.BLUE);
                }else 
                if ("VISUALEFFECT".equals(tElements.get(i).getItemType())){
                    populateSceneBreakdown(jTextPane1, tElements.get(i).getItemName(), Color.PINK);
                }
           
            }
        }catch (Exception e) {
            //throw new TagHighlightException("Text highlight error: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }
    private void breakdownReport(String projectName){
        DefaultListModel listModel= new DefaultListModel();
        DefaultListModel catlistModel= new DefaultListModel();
        String heading = projectName;
        String Headings="";
        String Characters="Character";
        String Locations="Location";
        String Props="Props";
        
        try{
       //fill Scenes List
        ArrayList<SceneElement> sceneElements = new ArrayList<SceneElement>();
        jList1.setModel(listModel);
        listModel.add(0, "ALL");
         
        sceneElements= XMLManager.getInstance().getAllSceneItems();
        for(int i=0; i<sceneElements.size(); i++){
            populateSceneBreakdown(jTextPane1, sceneElements.get(i).getSceneName(), Color.GRAY);
            String sceneHeading = new Integer(i+1).toString()+". "+ sceneElements.get(i).getSceneName();
            Headings= Headings+sceneElements.get(i).getSceneType()+" - "+sceneElements.get(i).getSceneName()
                +"\n"
                +Characters
                 +"\n"
                +Locations
                 +"\n"
                +Props;
        
            int modelsize = jList1.getModel().getSize();
            listModel.add(modelsize, sceneHeading);   
        }
        
         ArrayList<TaggedItem> tElements = new ArrayList<TaggedItem>();
         tElements= XMLManager.getInstance().getAllTaggedElements();
         jList3.setModel(catlistModel);
         catlistModel.add(0, "ALL");
         for(int i=0; i<tElements.size(); i++){
             String category = tElements.get(i).getItemType();
             
             if (!getModelIndex(category, catlistModel)){
                  int modelsize = jList3.getModel().getSize();
                 catlistModel.add(modelsize, tElements);
             }
             
         }
        }catch (Exception e) {
            //throw new TagHighlightException("Text highlight error: " + e.getMessage());
            System.out.println(e.getMessage());
        }
         
        
        
        
        StyledDocument doc = jTextPane2.getStyledDocument();
        Style style = doc.addStyle("table", null);
        //StyleConstants.setComponent(style, getTableComponent());

        try {
            doc.insertString(doc.getLength(), heading+"\n\n"+Headings
                   , doc.getStyle("table"));
         }
        catch (BadLocationException ex) {
        }
    
    }
    
    private boolean getModelIndex(String string, DefaultListModel model) {
        int itemIndex = model.getSize();
        boolean exists=false;
        for (int i = 0; i < model.getSize(); i++) {
            String value = model.getElementAt(i).toString();
           
            if (string.equals(value)) {

                exists = true;
             
            }

        }
         return exists;
    }
    
    private void category(){
        try{
         DefaultListModel catlistModel= new DefaultListModel();
        ArrayList<TaggedItem> tElements = new ArrayList<TaggedItem>();
         tElements= XMLManager.getInstance().getAllTaggedElements();
         jList3.setModel(catlistModel);
         catlistModel.add(0, "ALL");
         for(int i=0; i<tElements.size(); i++){
             String category = tElements.get(i).getItemType();
             
             if (!getModelIndex(category, catlistModel)){
                  int modelsize = jList3.getModel().getSize();
                 catlistModel.add(modelsize, tElements.get(i).getItemType());
             }
         
    }}catch (Exception e){}
    }
}
