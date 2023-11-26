import java.io.*;

public class ReadImage {

	private static boolean checkFileType(String fileType) {
		if (fileType.equals("P6"))
			return true;
		else
			return false;
	}

	public static boolean readfile(String fileName) throws invalidFileTypeException {

		String inputFile = fileName;

		BufferedReader reader;

		try (InputStream inputStream = new FileInputStream(inputFile);) {

			reader = new BufferedReader(new FileReader(inputFile));

			String[] fileinfo = new String[3];
			int n = 0;
			int linecount = 0;
			int byteRead;
			String line = reader.readLine();
			while (Character.isAlphabetic(line.charAt(0)) || Character.isDigit(line.charAt(0))
					|| line.charAt(0) == '#') {

				if (line.charAt(0) != '#') {// komment kiszurese
					fileinfo[n] = line;
				}

				if (linecount == 0) {
					
					if (checkFileType(line) == false) {
						//a dobott kivetelt csak azert nem itt kapom el, mert a Junit testben tesztelem a kivételdobást, és ha itt elkapom, oda nem jut el a dobott kivétel
						
						//try {
						System.out.println("System out: invalid *.ppm file.");
						invalidFileTypeException ivft = new invalidFileTypeException(
								"Exception: invalid *.ppm file.");
						throw ivft;
						//}

					//itthagyom kikommentezve, amit a JUnit teszt miatt kiszedtem
						
					/* catch (invalidFileTypeException ex) {
				            System.out.println("Caught");
				 
				            // Print the message from MyException object
				            System.out.println(ex.getMessage());
				        }*/
					}
					else
						System.out.println("Successful identification as valid *.ppm file by ReadImage.");

				}

				line = reader.readLine();
				while ((byteRead = inputStream.read()) != '\n') {
				}

				if (line.charAt(0) != '#')
					n++;
				linecount++;

			}

			String[] splited = fileinfo[1].trim().split("\\s+");
			int Width = Integer.valueOf(splited[0]);
			int Height = Integer.valueOf(splited[1]);
			int Depth = Integer.valueOf(fileinfo[2]);

			// ez olvassa be kulon a binaris reszt

			PixelData px = new PixelData(0, 0, 0); // inicializalom a matrixot, kulonben szol a fordito, hogy nincs
													// inicializalva
			PixelData[][] matrix = new PixelData[Width][Height];
			for (int h = 0; h < Height; h++) {
				for (int w = 0; w < Width; w++) {
					matrix[h][w] = px;
				}
			}

			System.out.println("Reading binary data from ppm file completed.");

			int r = 255;
			int g = 255;
			int b = 255;

			for (int i = 0; i < Height; i++) {
				for (int j = 0; j < Width; j++) {
					for (int k = 0; k < 3; k++) {

						int byteRead2 = inputStream.read();
						int v = byteRead2;
						switch (k % 3) {
						case 0: {
							r = v;

							break;
						}
						case 1: {
							g = v;

							break;
						}
						case 2: {
							b = v;

							break;
						}
						}// end case
					} // end for k
					matrix[i][j] = new PixelData(r, g, b);
				} // end for j
			} // end for i

			System.out.println("Initial RGB matrix ready.");

			HSVBase.imgParams = new ImageParams(Width, Height, Depth, matrix);
			reader.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		// JUnit teszthez megnezem, letezik-e az RGBMatrix, es nem maradt-e minden 0
		// benne
		if (HSVBase.imgParams.RGBMatrix == null) {
			System.out.println("false");
			return false;

		}

		for (int i = 0; i < HSVBase.imgParams.Height; i++) {
			for (int j = 0; j < HSVBase.imgParams.Width; j++) {
				if (HSVBase.imgParams.RGBMatrix[i][j].r > 0 || HSVBase.imgParams.RGBMatrix[i][j].g > 0
						|| HSVBase.imgParams.RGBMatrix[i][j].b > 0)
					System.out.println("true");
				return true;
			}
		}
		System.out.println("false");
		return false;

	}

}
