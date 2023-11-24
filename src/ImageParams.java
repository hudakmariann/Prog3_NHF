
public class ImageParams {
	int Height;
	int Width;
	int Depth;
	static PixelData RGBMatrix[][];
	static HSVData HSVMatrix[][];
	
	public ImageParams() {}
	public ImageParams(int H, int W, int D, PixelData matrix[][]) {
		Height = H;
		Width = W;
		Depth = D;
		RGBMatrix= new PixelData[Width][Height];
		RGBMatrix = matrix.clone();
		HSVMatrix = null;
		
		System.out.println("===================ImageParams====================");
		System.out.println(RGBMatrix[234][125].r);
		System.out.println(RGBMatrix[502][13].r);
		System.out.println(RGBMatrix[302][139].r);
		
	}
	public static  void SetHSVMatrix(int H, int W, HSVData[][] matrix) {
		System.out.println("matrix = " + matrix[1][66].v);
		System.out.println("matrix = " + matrix[123][456].v);
		HSVMatrix= new HSVData[H][W];
		HSVMatrix = matrix;
	
		System.out.println("HSVMatrix cloned." + HSVMatrix[1][66].v);
		
	}

}
