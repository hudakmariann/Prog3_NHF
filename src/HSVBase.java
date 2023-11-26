
public abstract class HSVBase {
	
	

	
	public static ImageParams imgParams;
	
	//normál esetben az imgParams-on kjvül semmi nem lenne static
	//de a Junit test másképp nem tud ezekhez hozzáférni, mert nem példányosítható (abstract osztály)
	//amúgy sem public hanem protected lenne itt a legtöbb metódus
	
	
	public  abstract void execute (double d);

	private static double minRGB(double r, double g, double b) {
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

	private static double maxRGB(double r, double g, double b) {
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
	
		
	protected  static boolean  RGBtoHSV(){ //protected void lenne, de a JUnit test miatt kell public, meg boolean
	    double min, max, diff;
	    double r, g, b;
	    double h,s,v;
	    
	    HSVData px = new HSVData(0,0,0);
		HSVData[][] HSVmatrix = new HSVData[imgParams.Height][imgParams.Width];
		for (int i = 0; i < imgParams.Height; i++) {
			for (int j = 0; j < imgParams.Width; j++) {
				HSVmatrix[i][j] = px;
			}
		}


	    for (int i = 0; i < imgParams.Height; i++){
	        for (int j = 0; j < imgParams.Width; j++){

	            r = (double)imgParams.RGBMatrix[i][j].r/256;
	            g = (double)imgParams.RGBMatrix[i][j].g/256;
	            b = (double)imgParams.RGBMatrix[i][j].b/256;

	            max = maxRGB(r,g,b);
	            min = minRGB(r,g,b);
	            diff = max - min;

	            if (max == 0)
	                s = 0;
	            else
	               s = diff / max;

	            if (diff == 0){
	               h = 0;
	               s = 0;
	            }

	            if (max == r)
	                    h = ((g - b) /diff);
	            else
	            if (max == g)
	                h = (2 + (b - r) / diff);
	            else
	                h = (4 + (r - g) / diff);

	            if (h < 0)
	                h=0;
	            h /= 6.0;
	            v = max;
	            
	            HSVmatrix[i][j] = new HSVData(h,s,v);
	        }//end for j
	        
	    }//end for i
	   
	    ImageParams.SetHSVMatrix( imgParams.Height,  imgParams.Width, HSVmatrix);
	    System.out.println("RGBtoHSV Ready.");
	    
	    // JUnit teszthez megnezem, letezik-e a HSVMatrix, es nem maradt-e minden 0
		// benne
		if (HSVBase.imgParams.HSVMatrix == null) {
			System.out.println("false");
			return false;

		}

		for (int i = 0; i < HSVBase.imgParams.Height; i++) {
			for (int j = 0; j < HSVBase.imgParams.Width; j++) {
				if (HSVBase.imgParams.HSVMatrix[i][j].h > 0 || HSVBase.imgParams.HSVMatrix[i][j].s > 0
						|| HSVBase.imgParams.HSVMatrix[i][j].v > 0)
					System.out.println("true");
				return true;
			}
		}
		System.out.println("false");
		return false;

	}
	
	
	
	
	public static boolean  HSVtoRGB(){ //ez is csak a JUnit test miatt public static boolean
	    
	    double h=0, s=0, v=0;
	    double r=0, g=0, b=0;
	   

	    for (int i = 0; i < imgParams.Height; i++){
	        for (int j = 0; j < imgParams.Width; j++){

	    // RGB-HSV konverziohoz hasznalt forras: https://lodev.org/cgtutor/color.html

	            h = imgParams.HSVMatrix[i][j].h;
	            s = imgParams.HSVMatrix[i][j].s;
	            v = imgParams.HSVMatrix[i][j].v;

	            if(s == 0) {
	            r = v;
	            g = v;
	            b = v;
	            }
	            else
	            {
	                double f, p, q, t; 
	               
	                h *= 6; 
	                int n = (int)(h);
	                f = h - n; 
	                p = v * (1.0 - s);
	                q = v * (1.0 - (s * f));
	                t = v * (1.0 - (s * (1.0 - f)));
	                switch(n)
	                {
	                    case 0:{
	                        r = v;
	                        g = t;
	                        b = p;
	                        break;
	                    }
	                    case 1: {
	                        r = q;
	                        g = v;
	                        b = p;
	                        break;
	                    }
	                    case 2: {
	                        r = p;
	                        g = v;
	                        b = t;
	                        break;
	                    }
	                    case 3: {
	                        r = p;
	                        g = q;
	                        b = v;
	                        break;
	                    }
	                    case 4: {
	                        r = t;
	                        g = p;
	                        b = v;
	                        break;
	                    }
	                    case 5: {
	                        r = v;
	                        g = p;
	                        b = q;
	                        break;
	                    } //end case
	                } //end switch
	            } // end else

	            if (r < 0) r = 0;
	            if (r>1)  r = 1;
	            if (g < 0) g = 0;
	            if (g>1)  g = 1;
	            if (b < 0) b = 0;
	            if (b>1)  b = 1;


	           imgParams.RGBMatrix[i][j].r = (int)(r * 255.0);
	           imgParams.RGBMatrix[i][j].g = (int)(g * 255.0);
	           imgParams.RGBMatrix[i][j].b = (int)(b * 255.0);
	        

	            }//end for j
	        }//end for i
	    System.out.println("HSVtoRGB ready.");
	    
	    
	    if (HSVBase.imgParams.RGBMatrix == null) {
			System.out.println("false");
			return false;

		}

	    //itt a JUnit teszthez ellenőrzöm, hogy valid, 0-255 közötti értékek jöttek-e ki
	    
		for (int i = 0; i < HSVBase.imgParams.Height; i++) {
			for (int j = 0; j < HSVBase.imgParams.Width; j++) {
				if ((HSVBase.imgParams.RGBMatrix[i][j].r > 0 && HSVBase.imgParams.RGBMatrix[i][j].r < 256) 
						|| (HSVBase.imgParams.RGBMatrix[i][j].g > 0 && HSVBase.imgParams.RGBMatrix[i][j].g < 256) 
						|| (HSVBase.imgParams.RGBMatrix[i][j].b > 0 && HSVBase.imgParams.RGBMatrix[i][j].b < 256)) 
					System.out.println("true");
				return true;
			}
		}
		System.out.println("false");
		return false;

	}

	

}






