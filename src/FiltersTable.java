// Packages to import
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FiltersTable  {
	// frame
	JFrame f;
	// Table
	JTable j;
	
	String[][] data;

	// Constructor
	FiltersTable(String[][] dat)
	{
		// Frame initialization
		f = new JFrame();

		// Frame Title
		f.setTitle("Filters");
		data = dat;

		// Data to be displayed in the JTable
		/*String[][] data = {
			{ "Brightness", "70" },
			{ "Contrast", "42" }
		};*/
		

		// Column Names
		String[] columnNames = {"FilterName","Value" };

		// Initializing the JTable
		j = new JTable(data, columnNames);
		j.setBounds(30, 40, 200, 300);

		// adding it to JScrollPane
		JScrollPane sp = new JScrollPane(j);
		f.add(sp);
		// Frame Size
		f.setSize(500, 200);
		// Frame Visible = true
		f.setVisible(true);
	}

	
}

