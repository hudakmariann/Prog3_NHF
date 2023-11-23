
public class HSVData {
	
	double h;
	double s;
	double v;
	
	public HSVData(double hue, double sat, double val) {
		h = hue;
		s = sat;
		v = val;
		
	}
	public HSVData(HSVData hsvData) {
		h = hsvData.h;
		s = hsvData.s;
		v = hsvData.v;
	}
	
	public HSVData() {
		h = 0;
		s = 0;
		v = 0;
	}
	


}
