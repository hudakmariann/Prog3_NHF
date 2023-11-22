import java.io.*;

public class ReadImage {

	public static void readfile(String fileName) {

		String inputFile = fileName;
		String outputFile = "./copy.ppm";

		try (InputStream inputStream = new FileInputStream(inputFile);
				OutputStream outputStream = new FileOutputStream(outputFile);) {
			int byteRead = -1;

			/*
			 * while ((byteRead = inputStream.read()) != -1) { outputStream.write(byteRead);
			 * }
			 */

			char[] filetype;
			filetype = new char[3];
			for (int i = 0; i < 3; ++i) {
				filetype[i] = (char) inputStream.read();
			}

			if (filetype[0] != 'P' || filetype[1] != '6') {
				System.out.println("Error: not a valid *.ppm file");

			} else
				System.out.println("Successful identification as valid *.ppm file");

			// comment kiszurese
			char c;

			c = (char) inputStream.read();

			if (c == '#') {
				do {
				
					c = (char) inputStream.read();
				}while (c != '\n');
			}

			// kep meretenek beolvasasa

			char size[] = new char[10];
			char depth[] = new char[5];

			size[0] = c; // ha nem volt komment, a kep merete mar itt kezodik, ezert be kell irni a size
							// tombbe. Ha volt, akkor a \n kerul a size tomb elejere, de azt a sscanf ugysem
							// fogja szamkent beolvasni, ugyhogy nem baj.
			System.out.println("Size[0] = " + size[0]);
			c = (char) inputStream.read();
			
			boolean digit = Character.isDigit(c);
			if (digit) {
				size[1] = c;
				System.out.println("Size[1] = " + size[1]);
				int i = 2;
				while (c != '\n') {

					c = (char) inputStream.read();
					size[i] = c;
					System.out.println("Size[" + i + "] = " + size[i]);
					i++;
				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
