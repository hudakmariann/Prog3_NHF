import java.io.*;

public class WriteImage {
	


		public static void readfile(String fileName) {

			String inputFile = fileName;
			System.out.println(inputFile);
			
			
			try {
			      FileWriter imageWriter = new FileWriter(inputFile);
			      
			      imageWriter.write("P6\n");
			      imageWriter.write("# Processed by ImageProcessing java app\n");
			      imageWriter.write(ReadImage.imgParams.Width + " " + ReadImage.imgParams.Height + "\n" );
			      imageWriter.write(ReadImage.imgParams.Depth + "\n");
			      imageWriter.close();
			      System.out.println("Successfully wrote to the file.");
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
			
		}

}
