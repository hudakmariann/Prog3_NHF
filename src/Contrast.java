
public class Contrast extends HSVBase {
	
	private double percent_;
	
	public Contrast(double p) {
		percent_ = p;
		
	}
	
	
	private double maxval(){
	     double max = 0.0;
	     for (int i = 0; i < imgParams.Height; i++){
	        for (int j = 0; j < imgParams.Width; j++){
	        if (imgParams.HSVMatrix[i][j].v > max)
	             max = imgParams.HSVMatrix[i][j].v;
	        
	        }//end for j
	    }//end for i
	     System.out.println("v max = " + max);
	    return max;
	}

	private double minval(){
	    double min = 255.0;
	     for (int i = 0; i < imgParams.Height; i++){
	        for (int j = 0; j < imgParams.Width; j++){
	        if (imgParams.HSVMatrix[i][j].v < min)
	             min = imgParams.HSVMatrix[i][j].v;
	        }//end for j
	    }//end for i
	     System.out.println("v min = " + min);
	    return min;
	}

	private void setContrast(){
		int h = imgParams.Height;
		int w = imgParams.Width;
		System.out.println("SetContrast HSV Matrix : " + imgParams.HSVMatrix[200][300].v);
	    
	    double max = maxval();
	    double min = minval();
	    System.out.println("minval utan HSV Matrix : " + imgParams.HSVMatrix[200][300].v);
	    
	    
	    if (percent_ < -100)
	        percent_ = -100;
	    else
	    if (percent_ > 100)
	        percent_ = 100;
	    
	    //System.out.println("percent check utan HSV Matrix : " + imgParams.HSVMatrix[200][300].v);
	    
	    percent_ /=100;
	   // System.out.println("percent szazzal osztas utan HSV Matrix : " + imgParams.HSVMatrix[100][200].v);
	    
	     for (int i = 0; i < h; i++){
	        for (int j = 0; j < w; j++){
	        	//System.out.println("v before = " + imgParams.HSVMatrix[i][j].v);
	        	imgParams.HSVMatrix[i][j].v = ((imgParams.HSVMatrix[i][j].v-min)*(((1+percent_)*max)/(max-min))+min*(1-percent_));
	        	//System.out.println("v after = " + imgParams.HSVMatrix[i][j].v);
	        }//end for j
	    }//end for i
	     //System.out.println("for ciklus utan HSV Matrix : " + imgParams.HSVMatrix[200][300].v);
	     
	     /*for (int i = 0; i < h; i++){
		        for (int j = 0; j < w; j++){
		        	System.out.print(imgParams.HSVMatrix[i][j].h + " ");
		        }
		        System.out.print("\n");
	}*/
	}
	
	@Override
	public void execute(double percent) {
		if (imgParams.HSVMatrix == null) {
			System.out.println("RGBtoHSV called.");
			RGBtoHSV();
		}
		this.percent_ = percent;
		setContrast();
		HSVtoRGB();
	
	}
	

}
