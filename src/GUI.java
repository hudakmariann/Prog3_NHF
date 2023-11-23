

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
//import java.io.*;

public class GUI extends JFrame{
	//static final String gapList[] = {"0", "10", "15", "20"};
    //final static int maxGap = 20;
    //JComboBox horGapComboBox;
    //JComboBox verGapComboBox;
    JButton btnOpen = new JButton("Open Image");
    JButton btnSave = new JButton("Save");
    JButton btnSaveAs = new JButton("Save As");
    JLabel lblFileName = new JLabel("Current Image File");
    GridLayout experimentLayout = new GridLayout(0,2);
     
    public GUI(String name) {
        super(name);
        setResizable(false);
    }
     
    /*public void initGaps() {
        horGapComboBox = new JComboBox(gapList);
        verGapComboBox = new JComboBox(gapList);
    }*/
     
    public void addComponentsToPane(final Container pane) {
        //initGaps();
        final JPanel imageProcControls = new JPanel();
        imageProcControls.setLayout(experimentLayout);
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(4,1));
         
        //Set up components preferred size
        /*JButton b = new JButton("Just fake button");
        Dimension buttonSize = b.getPreferredSize();
        imageProcControls.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5)+maxGap,
                (int)(buttonSize.getHeight() * 3.5)+maxGap * 2));*/
         
        //Add buttons to experiment with Grid Layout
        JButton btnContrast = new JButton("Modify Contrast");
        imageProcControls.add(btnContrast);
        JSpinner spContrast = new JSpinner(new SpinnerNumberModel(0,-100,100,1));
        imageProcControls.add(spContrast);
       
        JButton btnBrightness = new JButton("Modify Brightness");
        imageProcControls.add(btnBrightness);
        JSpinner spBrightness = new JSpinner(new SpinnerNumberModel(0,-100,100,1));
        imageProcControls.add(spBrightness);
        
        JButton btnSaturation = new JButton("Modify Saturation");
        imageProcControls.add(btnSaturation);
        JSpinner spSaturation = new JSpinner(new SpinnerNumberModel(0,-100,100,1));
        imageProcControls.add(spSaturation);
        
        JButton btnBlur = new JButton("Blur image");
        imageProcControls.add(btnBlur);
        JSpinner spBlur = new JSpinner(new SpinnerNumberModel(0,0,20,1));
        imageProcControls.add(spBlur);
        
        JButton btnFindEdges = new JButton("Find Edges");
        imageProcControls.add(btnFindEdges);
        

         
        //Add controls to set up horizontal and vertical gaps
        /*controls.add(new Label("Horizontal gap:"));
        controls.add(new Label("Vertical gap:"));
        controls.add(new Label(" "));*/
        controls.add(btnOpen);
        controls.add(btnSave);
        controls.add(btnSaveAs);
        controls.add(lblFileName);
         
        //Process the Apply gaps button press
        
        btnContrast.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Get the horizontal gap value
                System.out.println("Contrast = " + spContrast.getValue());
            }
        });
        
        btnBrightness.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Get the horizontal gap value
                System.out.println("Brightness = " + spBrightness.getValue());
            }
        });
        
        btnSaturation.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Get the horizontal gap value
                System.out.println("Saturation = " + spSaturation.getValue());
            }
        });
        
        btnBlur.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Get the horizontal gap value
                System.out.println("Blur = " + spBlur.getValue());
            }
        });
        
        btnFindEdges.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Get the horizontal gap value
                System.out.println("FindEdges");
            }
        });
        
        btnSaveAs.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFileChooser saveAsFileChooser = new JFileChooser(FileSystemView.getFileSystemView());
                
                // Open the save dialog
                //saveAsFileChooser.showSaveDialog(null);
                
                
                int r = saveAsFileChooser.showSaveDialog(null);

    			// if the user selects a file
    			if (r == JFileChooser.APPROVE_OPTION)

    			{
    				// set the label to the path of the selected file
    				//lblFileName.setText(openFileChooser.getSelectedFile().getAbsolutePath());
    				//lblFileName.setText(saveAsFileChooser.getSelectedFile().getName());
    				String fileName = saveAsFileChooser.getSelectedFile().getAbsolutePath();
    				WriteImage.readfile(fileName);
    				
    				//ReadImage.readfile(fileName);
    			

    			}
    			// if the user cancelled the operation
    			else
    				lblFileName.setText("the user cancelled the operation");
    		}
                
                
            
        });
        
        btnOpen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFileChooser openFileChooser = new JFileChooser(FileSystemView.getFileSystemView());
                
                // Open the save dialog
                int r = openFileChooser.showOpenDialog(null);

        			// if the user selects a file
        			if (r == JFileChooser.APPROVE_OPTION)

        			{
        				// set the label to the path of the selected file
        				//lblFileName.setText(openFileChooser.getSelectedFile().getAbsolutePath());
        				lblFileName.setText(openFileChooser.getSelectedFile().getName());
        				String fileName = openFileChooser.getSelectedFile().getAbsolutePath();
        				ReadImage.readfile(fileName);
	
        			}
        			// if the user cancelled the operation
        			else
        				lblFileName.setText("the user cancelled the operation");
        		}
            
        });
        
       /* applyButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Get the horizontal gap value
                String horGap = (String)horGapComboBox.getSelectedItem();
                //Get the vertical gap value
                String verGap = (String)verGapComboBox.getSelectedItem();
                //Set up the horizontal gap value
                experimentLayout.setHgap(Integer.parseInt(horGap));
                //Set up the vertical gap value
                experimentLayout.setVgap(Integer.parseInt(verGap));
                //Set up the layout of the buttons
                experimentLayout.layoutContainer(imageProcControls);
            }
        });*/
        pane.add(imageProcControls, BorderLayout.SOUTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.NORTH);
    }
     
    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        GUI frame = new GUI("ImageProcessing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
     
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
         
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
	
	

}
