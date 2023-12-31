
public class Contrast extends HSVBase {

	private double percent_;

	public Contrast(double p) {
		percent_ = p;

	}
	
	public String toString() { 
        return "Contrast";
     } 

	private double maxval() {
		double max = 0.0;
		for (int i = 0; i < imgParams.Height; i++) {
			for (int j = 0; j < imgParams.Width; j++) {
				if (imgParams.HSVMatrix[i][j].v > max)
					max = imgParams.HSVMatrix[i][j].v;

			} // end for j
		} // end for i

		return max;
	}

	private double minval() {
		double min = 255.0;
		for (int i = 0; i < imgParams.Height; i++) {
			for (int j = 0; j < imgParams.Width; j++) {
				if (imgParams.HSVMatrix[i][j].v < min)
					min = imgParams.HSVMatrix[i][j].v;
			} // end for j
		} // end for i

		return min;
	}

	
	
	public double setContrast() {  
		int h = imgParams.Height;
		int w = imgParams.Width;

		double max = maxval();
		double min = minval();
		double diffBefore = max - min;  //JUnit testhez, hogy megnezzuk, valtozott-e a kulonbseg a lefutas utan

		if (percent_ < -100)
			percent_ = -100;
		else if (percent_ > 100)
			percent_ = 100;

		percent_ /= 100;

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				imgParams.HSVMatrix[i][j].v = ((imgParams.HSVMatrix[i][j].v - min)
						* (((1 + percent_) * max) / (max - min)) + min * (1 - percent_));
			} // end for j
		} // end for i
		System.out.println("Contrast ready.");
		
		max = maxval();
		min = minval();
		double diffAfter = max-min;
		return diffBefore - diffAfter;
		
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
