import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileNameExtensionFilter;
//import File;
import java.io.*;

public class GUI extends JFrame {
	JButton btnOpen = new JButton("Open Image");
	JButton btnSave = new JButton("Save");
	JButton btnSaveAs = new JButton("Save As");
	JLabel lblFileName = new JLabel("Current Image File");
	GridLayout experimentLayout = new GridLayout(0, 2);

	private String inputfilename;

	public GUI(String name) {
		super(name);
		setResizable(false);
	}

	public void addComponentsToPane(final Container pane) {
		// initGaps();
		final JPanel imageProcControls = new JPanel();
		imageProcControls.setLayout(experimentLayout);
		JPanel controls = new JPanel();
		controls.setLayout(new GridLayout(4, 1));

		JButton btnContrast = new JButton("Modify Contrast");
		imageProcControls.add(btnContrast);
		JSpinner spContrast = new JSpinner(new SpinnerNumberModel(0, -100, 100, 1));
		imageProcControls.add(spContrast);

		JButton btnBrightness = new JButton("Modify Brightness");
		imageProcControls.add(btnBrightness);
		JSpinner spBrightness = new JSpinner(new SpinnerNumberModel(0, -100, 100, 1));
		imageProcControls.add(spBrightness);

		JButton btnSaturation = new JButton("Modify Saturation");
		imageProcControls.add(btnSaturation);
		JSpinner spSaturation = new JSpinner(new SpinnerNumberModel(0, -100, 100, 1));
		imageProcControls.add(spSaturation);

		JButton btnBlur = new JButton("Blur image");
		imageProcControls.add(btnBlur);
		JSpinner spBlur = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
		imageProcControls.add(spBlur);

		JButton btnFindEdges = new JButton("Find Edges");
		imageProcControls.add(btnFindEdges);

		controls.add(btnOpen);
		controls.add(btnSave);
		controls.add(btnSaveAs);
		controls.add(lblFileName);

		btnContrast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Integer p = (Integer) spContrast.getValue();
				double percent = (double) p;
				Contrast contrast = new Contrast(percent);
				System.out.println("Contrast = " + percent);
				contrast.execute(percent);
			}
		});

		btnBrightness.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Integer p = (Integer) spBrightness.getValue();
				double percent = (double) p;
				Brightness brightness = new Brightness(percent);
				System.out.println("Brightness = " + percent);
				brightness.execute(percent);

			}
		});

		btnSaturation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer p = (Integer) spSaturation.getValue();
				double percent = (double) p;
				Saturation saturation = new Saturation(percent);
				System.out.println("Saturation = " + percent);
				saturation.execute(percent);
			}
		});

		btnBlur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer fs = (Integer) spBlur.getValue();
				double fsize = (double) fs;
				Blur blur = new Blur(fsize);
				System.out.println("Blur = " + fsize);
				blur.execute(fsize);
			}
		});

		btnFindEdges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("FindEdges");
				FindEdges findedges = new FindEdges();
				findedges.execute(0);
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
					ReadImage.readfile(fileName);
					inputfilename = fileName;

				}
				
				else
					lblFileName.setText("operation cancelled");
			}

		});

		
		pane.add(imageProcControls, BorderLayout.SOUTH);
		pane.add(new JSeparator(), BorderLayout.CENTER);
		pane.add(controls, BorderLayout.NORTH);
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

		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

}
