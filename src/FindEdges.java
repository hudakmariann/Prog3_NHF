
public class FindEdges extends HSVBase {
	private double[] surround = new double[8];
	private double szele = 1;
	
	public String toString() { 
        return "FindEdges";
     } 

	public boolean findEdges() {

		double h = 0;
		double s = 0;
		double v = 0;

		HSVData[][] filteredHSV = new HSVData[imgParams.Height][imgParams.Width];

		for (int y = 0; y < imgParams.Height; y++) {
			for (int x = 0; x < imgParams.Width; x++) {

				if ((y != 0) && (x != 0) && (y != imgParams.Height - 1) && (x != imgParams.Width - 1)) {
					surround[0] = imgParams.HSVMatrix[y - 1][x - 1].v;
					surround[1] = imgParams.HSVMatrix[y - 1][x].v;
					surround[2] = imgParams.HSVMatrix[y - 1][x + 1].v;
					surround[3] = imgParams.HSVMatrix[y][x - 1].v;
					surround[4] = imgParams.HSVMatrix[y][x + 1].v;
					surround[5] = imgParams.HSVMatrix[y + 1][x - 1].v;
					surround[6] = imgParams.HSVMatrix[y + 1][x].v;
					surround[7] = imgParams.HSVMatrix[y + 1][x + 1].v;
				} else
					for (int i = 0; i < 8; i++) // kep szelenek lekezelese a tulindexeles elkerulese vegett
						surround[i] = szele;

				double sum = 0;
				for (int i = 0; i < 8; i++) {
					sum += surround[i];
				}
				h = imgParams.HSVMatrix[y][x].h;
				s = 0;
				v = (-8) * imgParams.HSVMatrix[y][x].v + sum;

				filteredHSV[y][x] = new HSVData(h, s, v);

			} // end for x
		} // end for y

		for (int i = 0; i < imgParams.Height; i++) {
			for (int j = 0; j < imgParams.Width; j++) {
				imgParams.HSVMatrix[i][j] = filteredHSV[i][j];
			} // end for j
		} // end for i
		System.out.println("FindEdges ready.");
		return true;
	}

	@Override
	public void execute(double size) {
		if (imgParams.HSVMatrix == null) {

			RGBtoHSV();
		}
		findEdges();
		HSVtoRGB();
	}

}
