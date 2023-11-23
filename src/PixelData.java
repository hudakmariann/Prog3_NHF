
public class PixelData {
	int r;
	int g;
	int b;
	
	public PixelData(int red, int gr, int bl) {
		r = red;
		g = gr;
		b = bl;
		
	}
	public PixelData(PixelData pxData) {
		r = pxData.r;
		g = pxData.g;
		b = pxData.b;
	}
	
	public PixelData() {
		r = 0;
		g = 0;
		b = 0;
	}
	

}
