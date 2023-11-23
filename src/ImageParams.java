
public class ImageParams {
	int Height;
	int Width;
	int Depth;
	PixelData RGBMatrix[][];
	
	public ImageParams() {}
	public ImageParams(int H, int W, int D, PixelData matrix[][]) {
		Height = H;
		Width = W;
		Depth = D;
		RGBMatrix= new PixelData[Width][Height];
		RGBMatrix = matrix.clone();
		System.out.println("===================ImageParams====================");
		System.out.println(RGBMatrix[234][125].r);
		System.out.println(RGBMatrix[502][13].r);
		System.out.println(RGBMatrix[302][139].r);
		
	}

}
