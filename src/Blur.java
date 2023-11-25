
public class Blur extends HSVBase {

	int filtersize;

	public Blur(double fs) {
		int filtersize = (int) fs;
	}

	private void blurImage() {

		double szele = 127;
		int kozepe;
		double centerweight;

		if (filtersize < 3) {
			filtersize = 3;
			System.out.println("Warning: the requested factor is out of range. The factor is set to 3.\n ");
		} else if (filtersize > 30) {
			filtersize = 31;
			System.out.println("Warning: the requested factor is out of range. The factor is set to 30.\n ");
		}
		if (filtersize % 2 == 0)
			filtersize++;

		kozepe = (filtersize - 1) / 2;
		centerweight = (filtersize + 1) / 2;

		PixelData[][] filteredIMG = new PixelData[imgParams.Height][imgParams.Width];

		double[][] filter = new double[filtersize][filtersize];

		double filtersum = 0;

		for (int i = 0; i < filtersize; i++) {
			for (int j = 0; j < filtersize; j++) {
				filter[i][j] = centerweight - ((Math.abs(kozepe - i)) + Math.abs(kozepe - j));
				if (filter[i][j] < 1)
					filter[i][j] = 1;

				filtersum += filter[i][j];
			}
		}

		double r, g, b = 0;
		for (int y = 0; y < imgParams.Height; y++) {
			for (int x = 0; x < imgParams.Width; x++) {
				double windowsum_r = 0;
				double windowsum_g = 0;
				double windowsum_b = 0;
				r = 255;
				g = 0;
				b = 0;
				for (int i = 0; i < filtersize; ++i) {
					for (int j = 0; j < filtersize; ++j) {
						if ((y + i > imgParams.Height - 1) || (x + j > imgParams.Width - 1)) {
							r = szele;
							g = szele;
							b = szele;
						} else {
							r = imgParams.RGBMatrix[y + i][x + j].r;
							g = imgParams.RGBMatrix[y + i][x + j].g;
							b = imgParams.RGBMatrix[y + i][x + j].b;
						}

						windowsum_r += r * filter[i][j];
						windowsum_g += g * filter[i][j];
						windowsum_b += b * filter[i][j];
					} // end for j
				} // end for i

				r = windowsum_r / filtersum;
				g = windowsum_g / filtersum;
				b = windowsum_b / filtersum;
				int red = (int) r;
				int green = (int) g;
				int blue = (int) b;

				PixelData px = new PixelData(red, green, blue);
				filteredIMG[y][x] = px;

			} // end for x

		} // end for y

		for (int i = 0; i < imgParams.Height; i++) {
			for (int j = 0; j < imgParams.Width; j++) {
				imgParams.RGBMatrix[i][j] = filteredIMG[i][j];
			} // end for j
		} // end for i
		System.out.println("Blur ready.");
	}

	@Override
	public void execute(double fsize) {
		this.filtersize = (int) fsize;
		blurImage();
	}

}
