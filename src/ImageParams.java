
public class ImageParams {
	int Height;
	int Width;
	int Depth;
	static PixelData RGBMatrix[][];
	static HSVData HSVMatrix[][];

	public ImageParams() {
	}

	public ImageParams(int H, int W, int D, PixelData matrix[][]) {
		Height = H;
		Width = W;
		Depth = D;
		RGBMatrix = new PixelData[Width][Height];
		RGBMatrix = matrix.clone();
		HSVMatrix = null;

	}

	public static void SetHSVMatrix(int H, int W, HSVData[][] matrix) {
		System.out.println("matrix = " + matrix[1][66].v);
		System.out.println("matrix = " + matrix[123][456].v);
		HSVMatrix = new HSVData[H][W];
		HSVMatrix = matrix;

		System.out.println("HSVMatrix set.");

	}

}
