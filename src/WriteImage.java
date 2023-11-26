import java.io.*;

public class WriteImage {

	public static boolean writefile(String fileName) {
		boolean result = false; //JUnit teszthez
		String outputFile = fileName;
		System.out.println(outputFile);

		try (OutputStream outputStream = new FileOutputStream(outputFile);) {
			FileWriter imageWriter = new FileWriter(outputFile);
			int W = HSVBase.imgParams.Width;
			int H = HSVBase.imgParams.Height;

			imageWriter.write("P6\n");
			imageWriter.write("# Processed by ImageProcessing java app\n");
			imageWriter.write(W + " " + H + "\n");
			imageWriter.write(HSVBase.imgParams.Depth + "\n");

			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					for (int k = 0; k < 3; k++) {

						switch (k % 3) {
						case 0: {
							outputStream.write(HSVBase.imgParams.RGBMatrix[i][j].b);
							break;
						}
						case 1: {
							outputStream.write(HSVBase.imgParams.RGBMatrix[i][j].r);
							break;
						}
						case 2: {
							outputStream.write(HSVBase.imgParams.RGBMatrix[i][j].g);
							break;
						}
						}// end case
					} // end for k
				} // end for j
			} // end for i

			imageWriter.close();
			System.out.println("Writing to file completed");
			result = true;
			
		} catch (IOException e) {
			System.out.println("Error occured while writing to file.");
			e.printStackTrace();
		}
		return result;
	}

}
