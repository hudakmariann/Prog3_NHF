
public class Brightness extends HSVBase {
	private double percent_;

	public Brightness(double p) {
		percent_ = p;
	}
	
	public String toString() { 
        return "Brightness";
     } 
	
	private double mean() {
		double result = 0;
		int i = 0;
		int j = 0;
		for ( i = 0; i < imgParams.Height; i++) {
			for ( j = 0; j < imgParams.Width; j++) {
				result += imgParams.HSVMatrix[i][j].v;
			} // end for j
		} // end for i
		return (result/(i*j));
	}

	public double setBrightness() {
		
		double meanBefore = mean();

		if (percent_ < -100)
			percent_ = -100;
		else if (percent_ > 100)
			percent_ = 100;

		percent_ /= 100;

		for (int i = 0; i < imgParams.Height; i++) {
			for (int j = 0; j < imgParams.Width; j++) {
				imgParams.HSVMatrix[i][j].v = imgParams.HSVMatrix[i][j].v * (1 + percent_);
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
		setBrightness();
		HSVtoRGB();
		System.out.println("Brightness ready.");
	}

}
