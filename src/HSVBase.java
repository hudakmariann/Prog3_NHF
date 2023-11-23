
public class HSVBase {

	
	public static ImageParams imgParams;
	double h, s, v;

	double minRGB(double r, double g, double b) {
		double[] rgb = new double[3];
		rgb[0] = r;
		rgb[1] = g;
		rgb[2] = b;
		double min = 255;
		for (int i = 0; i < 3; ++i) {
			if (rgb[i] < min)
				min = rgb[i];
		}
		return min;
	}

	double maxRGB(double r, double g, double b) {
		double[] rgb = new double[3];
		rgb[0] = r;
		rgb[1] = g;
		rgb[2] = b;
		double max = 0;
		for (int i = 0; i < 3; ++i) {
			if (rgb[i] > max)
				max = rgb[i];
		}
		return max;
	}
	
	public void RGBtoHSV(ImageParams  imgdata,  PixelData  matrix){
	    double min, max, diff;
	    HSVData[][] HSVmatrix = new HSVData[imgParams.Height][imgParams.Width];
	    double r, g, b;



	    /*for (int y = 1; y < imgParams.Height; ++y)
	        HSVmatrix[y] = HSVmatrix[0] + y * imgParams.Height;*/

	    for (int i = 0; i < imgParams.Height; i++){
	        for (int j = 0; j < imgParams.Width; j++){

	            r = (double)imgParams.RGBMatrix[i][j].r/256;
	            g = (double)imgParams.RGBMatrix[i][j].g/256;
	            b = (double)imgParams.RGBMatrix[i][j].b/256;

	            max = maxRGB(r,g,b);
	            min = minRGB(r,g,b);
	            diff = max - min;

	            if (max == 0)
	                HSVmatrix[i][j].s = 0;
	            else
	                HSVmatrix[i][j].s = diff / max;

	            if (diff == 0){
	                HSVmatrix[i][j].h = 0;
	                HSVmatrix[i][j].s = 0;
	            }

	            if (max == r)
	                     HSVmatrix[i][j].h = (g - b) /diff;
	            else
	            if (max == g)
	                HSVmatrix[i][j].h = 2 + (b - r) / diff;
	            else
	                HSVmatrix[i][j].h = 4 + (r - g) / diff;

	            if (HSVmatrix[i][j].h < 0)
	                HSVmatrix[i][j].h=0;
	            HSVmatrix[i][j].h /= 6;
	            HSVmatrix[i][j].v = max;
	        }//end for j
	    }//end for i
	    //return HSVmatrix;
	    ImageParams.SetHSVMatrix( imgParams.Height,  imgParams.Width, HSVmatrix);
	}

	

}






