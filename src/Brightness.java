
public class Brightness extends HSVBase {
	private double percent_;

	public Brightness(double p) {
		percent_ = p;
	}
	
	public String toString() { 
        return "Brightness";
     } 

	private void setBrightness() {

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
