import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.filechooser.FileNameExtensionFilter;
//import File;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTable;

public class GUI extends JFrame {
	JButton btnOpen = new JButton("Open Image");
	JButton btnSave = new JButton("Save");
	JButton btnSaveAs = new JButton("Save As");
	JLabel lblFileName = new JLabel("Current Image File");
	GridLayout experimentLayout = new GridLayout(0, 2);

	private String inputfilename;

	private static HashMap<HSVBase, Double> FilterMap;

	public GUI(String name) {
		super(name);
		setResizable(false);

		// Filters = new ArrayList<HSVBase>();
		FilterMap = new HashMap<HSVBase, Double>();

	}

	public void addComponentsToPane(final Container pane) {
		// initGaps();
		final JPanel imageProcControls = new JPanel();
		imageProcControls.setLayout(experimentLayout);
		JPanel controls = new JPanel();
		controls.setLayout(new GridLayout(4, 1));

		JButton btnContrast = new JButton("Modify Contrast");
		btnContrast.setEnabled(false);
		imageProcControls.add(btnContrast);
		JSpinner spContrast = new JSpinner(new SpinnerNumberModel(0, -100, 100, 1));
		imageProcControls.add(spContrast);

		JButton btnBrightness = new JButton("Modify Brightness");
		btnBrightness.setEnabled(false);
		imageProcControls.add(btnBrightness);
		JSpinner spBrightness = new JSpinner(new SpinnerNumberModel(0, -100, 100, 1));
		imageProcControls.add(spBrightness);

		JButton btnSaturation = new JButton("Modify Saturation");
		btnSaturation.setEnabled(false);
		imageProcControls.add(btnSaturation);
		JSpinner spSaturation = new JSpinner(new SpinnerNumberModel(0, -100, 100, 1));
		imageProcControls.add(spSaturation);

		JButton btnBlur = new JButton("Blur image");
		btnBlur.setEnabled(false);
		imageProcControls.add(btnBlur);
		JSpinner spBlur = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
		imageProcControls.add(spBlur);

		JButton btnFindEdges = new JButton("Find Edges");
		btnFindEdges.setEnabled(false);
		imageProcControls.add(btnFindEdges);
		
		JButton btnApply = new JButton("Apply Filters");
		btnApply.setEnabled(false);
		imageProcControls.add(btnApply);

		controls.add(btnOpen);
		btnSave.setEnabled(false);
		controls.add(btnSave);
		btnSaveAs.setEnabled(false);
		controls.add(btnSaveAs);
		controls.add(lblFileName);

		btnContrast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Integer p = (Integer) spContrast.getValue();
				double percent = (double) p;
				Contrast contrast = new Contrast(percent);
				System.out.println("Contrast = " + percent);
				//contrast.execute(percent);
				
				FilterMap.put(contrast,  percent);
				btnApply.setEnabled(true);
			}
		});

		btnBrightness.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Integer p = (Integer) spBrightness.getValue();
				double percent = (double) p;
				Brightness brightness = new Brightness(percent);
				System.out.println("Brightness = " + percent);
				//brightness.execute(percent);
				
				FilterMap.put(brightness,  percent);
				btnApply.setEnabled(true);

			}
		});

		btnSaturation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer p = (Integer) spSaturation.getValue();
				double percent = (double) p;
				Saturation saturation = new Saturation(percent);
				System.out.println("Saturation = " + percent);
				//saturation.execute(percent);
				
				FilterMap.put(saturation,  percent);
				btnApply.setEnabled(true);
			}
		});

		btnBlur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer fs = (Integer) spBlur.getValue();
				double fsize = (double) fs;
				Blur blur = new Blur(fsize);
				System.out.println("Blur = " + fsize);
				//blur.execute(fsize);
				
				FilterMap.put(blur,  fsize);
				btnApply.setEnabled(true);
			}
		});

		btnFindEdges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("FindEdges");
				FindEdges findedges = new FindEdges();
				//findedges.execute(101.0);
				
				FilterMap.put(findedges,  101.0);
				btnApply.setEnabled(true);
			}
		});
		
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Apply");
				showTable();
				
				//itt az iteracio a kollekcion vegig
				for (HashMap.Entry<HSVBase, Double> entry : FilterMap.entrySet()) {
					if(entry.getValue()!=0.0) {
						System.out.println(entry.getKey().toString());
					entry.getKey().execute(entry.getValue());
					}
					
					
				}
			}
		});


		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to overwrite the input image?");
				if (input == 0)
					WriteImage.writefile(inputfilename);
			}
		});

		btnSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser saveAsFileChooser = new JFileChooser(FileSystemView.getFileSystemView());
				saveAsFileChooser.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".ppm", "ppm");
				saveAsFileChooser.addChoosableFileFilter(filter);
				
				int r = saveAsFileChooser.showSaveDialog(null);

				
				if (r == JFileChooser.APPROVE_OPTION){
					String fileName = saveAsFileChooser.getSelectedFile().getAbsolutePath();
					fileName += "." + filter.getExtensions()[0];
					WriteImage.writefile(fileName);
				}
				
				else
					lblFileName.setText("the user cancelled the operation");
			}

		});

		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser openFileChooser = new JFileChooser(FileSystemView.getFileSystemView());

				openFileChooser.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".ppm", "ppm");
				openFileChooser.setFileFilter(filter);

				int r = openFileChooser.showOpenDialog(null);

				
				if (r == JFileChooser.APPROVE_OPTION)

				{
					
					lblFileName.setText(openFileChooser.getSelectedFile().getName());
					String fileName = openFileChooser.getSelectedFile().getAbsolutePath();
					try {
					ReadImage.readfile(fileName);
					} catch (invalidFileTypeException ivft) {}
					inputfilename = fileName;
					
					btnContrast.setEnabled(true);
					btnBrightness.setEnabled(true);
					btnSaturation.setEnabled(true);
					btnBlur.setEnabled(true);
					btnFindEdges.setEnabled(true);
					btnSave.setEnabled(true);
					btnSaveAs.setEnabled(true);

				}
				
				else
					lblFileName.setText("operation cancelled");
			}

		});

		
		pane.add(imageProcControls, BorderLayout.SOUTH);
		pane.add(new JSeparator(), BorderLayout.CENTER);
		pane.add(controls, BorderLayout.NORTH);
	}

	public void showTable() {

		String[][] data2 = new String[10][2];

		int i = 0;
		for (HashMap.Entry<HSVBase, Double> entry : FilterMap.entrySet()) {
			HSVBase filter = entry.getKey();
			data2[i][0] = entry.getKey().toString();
			data2[i][1] = entry.getValue().toString();
			i++;
		}

		FiltersTable filters = new FiltersTable(data2);

	}

	private static void createAndShowGUI() {
		GUI frame = new GUI("ImageProcessing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set up the content pane.
		frame.addComponentsToPane(frame.getContentPane());
		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {

		try {
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
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

		//String[][] data = { { "Brightness", "70" }, { "Contrast", "42" } };
		
		
		
		
		
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

	}

}
