
public class Saturation extends HSVBase {
	private double percent_;

	public Saturation(double p) {
		percent_ = p;
	}
	
	 public String toString() { 
         return "Saturation";
      } 

	 
	 private double mean() {  //JUnit teszthez
			double result = 0;
			int i = 0;
			int j = 0;
			for ( i = 0; i < imgParams.Height; i++) {
				for ( j = 0; j < imgParams.Width; j++) {
					result += imgParams.HSVMatrix[i][j].s;
				} // end for j
			} // end for i
			return (result/(i*j));
		}
	 
	public double setSaturation() {
		
		double meanBefore = mean(); //JUnit teszthez

		if (percent_ < -100)
			percent_ = -100;
		else if (percent_ > 100)
			percent_ = 100;
		percent_ /= 100;
		for (int i = 0; i < imgParams.Height; i++) {
			for (int j = 0; j < imgParams.Width; j++) {
				imgParams.HSVMatrix[i][j].s = imgParams.HSVMatrix[i][j].s * (1 + percent_);
			} // end for j
		} // end for i
		
		double meanAfter = mean(); 
		return (meanBefore - meanAfter); //JUnit teszthez, hogy lassuk, valtozott-e
	}

	@Override
	public void execute(double percent) {
		if (imgParams.HSVMatrix == null) {
			System.out.println("RGBtoHSV called.");
			RGBtoHSV();
		}
		this.percent_ = percent;
		setSaturation();
		HSVtoRGB();
	}

}
