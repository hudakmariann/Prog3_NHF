import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class ReadImage {

	public static void readfile(String fileName) {

		String inputFile = fileName;
		String outputFile = "./copy.ppm";

		BufferedReader reader;

		try (InputStream inputStream = new FileInputStream(inputFile);
				OutputStream outputStream = new FileOutputStream(outputFile);) {

			reader = new BufferedReader(new FileReader(inputFile));

			String[] fileinfo = new String[3];
			int i = 0;
			int linelength = 0;
			int linecount = 0;
			int byteRead;
			String line = reader.readLine();
			 //while ((byteRead = inputStream.read()) != '\n') {}
			linelength += line.length();
			while (Character.isAlphabetic(line.charAt(0)) || Character.isDigit(line.charAt(0))
					|| line.charAt(0) == '#') {

				if (line.charAt(0) != '#') {// komment kiszurese
					fileinfo[i] = line;
				}
				System.out.println("fileinfo = " + i + "  " + fileinfo[i]);
				if (linecount == 0 && line.equals("P6")) {
					System.out.println("Successful identification as valid *.ppm file. Line : " + line);

				} else if (linecount == 0 && (!(line.equals("P6")))) {
					System.out.println("Error: not a valid *.ppm file. Line = " + line);
				}
				
				
						
				line = reader.readLine();
				while ((byteRead = inputStream.read()) != '\n') {}

				System.out.println("Line = " + line);
				if (line.charAt(0) != '#')
					i++;
				linecount++;

			}
			int byteRead2;
			while ((byteRead2 = inputStream.read()) != -1) {
                outputStream.write(byteRead2);
            }

			String[] splited = fileinfo[1].trim().split("\\s+");
			int Width = Integer.valueOf(splited[0]);
			int Height = Integer.valueOf(splited[1]);
			;
			int Depth = Integer.valueOf(fileinfo[2]);

			System.out.println("Width = " + Width);
			System.out.println("Height = " + Height);
			System.out.println("Depth = " + Depth);


			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
