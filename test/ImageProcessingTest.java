import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import static org.junit.Assert.assertTrue;

public class ImageProcessingTest {
	

	/*@Test
	public void exceptionTest() {
		String fileName = System.getProperty("user.dir")+ "/" + "LenaBadType.ppm";
		Exception exception = assertThrows(invalidFileTypeException.class, () -> {
			ReadImage.readfile(fileName);
	    });

	    String expectedMessage = "Exception: invalid *.ppm file.";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}*/
	
	@Test
	public void exceptionTest() {
		String fileName = System.getProperty("user.dir")+ "/" + "LenaBadType.ppm";
		boolean caught = false;
		try {
			ReadImage.readfile(fileName);
			//fail( "My method didn't throw when I expected it to" );
		} catch (invalidFileTypeException expectedException) {
		caught = true;	
		System.out.println("exception caught!");
		
		}
		finally {
		Assert.assertTrue(caught);
		}
	}
	
	
	
	@Test
	public void openFileTest() {
		boolean result = false;
		String fileName = System.getProperty("user.dir")+ "\\" + "Lena.ppm";  //Windowson  \ a szeparator
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		System.out.println(fileName);
		File file = new File (fileName);
		if (file.exists()) {
			try {
				result = ReadImage.readfile(fileName);
				} catch (invalidFileTypeException ivft) {}
		}
		else {
			fileName = System.getProperty("user.dir")+ "/" + "Lena.ppm";  //Linuxon meg ez
			file = new File (fileName);
			if (file.exists()) {
				try {
					result = ReadImage.readfile(fileName);
					} catch (invalidFileTypeException ivft) {}
			}
			else {
				System.out.println(fileName + " does not exist!");
			}
		}
		Assert.assertTrue(result);
	}
	
	@Before
	public void prepForHSVTest() {
		boolean result = false;
		String fileName = System.getProperty("user.dir")+ "\\" + "Lena.ppm";  //Windowson  \ a szeparator
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		System.out.println(fileName);
		File file = new File (fileName);
		if (file.exists()) {
			try {
				result = ReadImage.readfile(fileName);
				} catch (invalidFileTypeException ivft) {}
		}
		else {
			fileName = System.getProperty("user.dir")+ "/" + "Lena.ppm";  //Linuxon meg ez
			file = new File (fileName);
			if (file.exists()) {
				try {
					result = ReadImage.readfile(fileName);
					} catch (invalidFileTypeException ivft) {}
			}
			else {
				System.out.println(fileName + " does not exist!");
			}
		}
	
	}
	
	@Test
	public void HSVTest() {
		
		boolean result = HSVBase.RGBtoHSV();
		Assert.assertTrue(result);
	}
	
	@Test
	public void RGBTest() {
		
		boolean result = HSVBase.RGBtoHSV();
		Assert.assertTrue(result);
	}
	
	
	
	@Before
	public void prepForContrastTest() {
		boolean result = false;
		String fileName = System.getProperty("user.dir")+ "\\" + "Lena.ppm";  //Windowson  \ a szeparator
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		System.out.println(fileName);
		File file = new File (fileName);
		if (file.exists()) {
			try {
				result = ReadImage.readfile(fileName);
				} catch (invalidFileTypeException ivft) {}
		}
		else {
			fileName = System.getProperty("user.dir")+ "/" + "Lena.ppm";  //Linuxon meg ez
			file = new File (fileName);
			if (file.exists()) {
				try {
					result = ReadImage.readfile(fileName);
					} catch (invalidFileTypeException ivft) {}
			}
			else {
				System.out.println(fileName + " does not exist!");
			}
		}
		HSVBase.RGBtoHSV();
	}
	
	@Test 
	public void ContrastTest() {
		Contrast contrast = new Contrast (100);
		double diff = contrast.setContrast();
		boolean result = diff!=0;
		Assert.assertTrue(result);
	}
	
	
	@Before
	public void prepForBrightnessTest() {
		boolean result = false;
		String fileName = System.getProperty("user.dir")+ "\\" + "Lena.ppm";  //Windowson  \ a szeparator
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		System.out.println(fileName);
		File file = new File (fileName);
		if (file.exists()) {
			try {
				result = ReadImage.readfile(fileName);
				} catch (invalidFileTypeException ivft) {}
		}
		else {
			fileName = System.getProperty("user.dir")+ "/" + "Lena.ppm";  //Linuxon meg ez
			file = new File (fileName);
			if (file.exists()) {
				try {
					result = ReadImage.readfile(fileName);
					} catch (invalidFileTypeException ivft) {}
			}
			else {
				System.out.println(fileName + " does not exist!");
			}
		}
		HSVBase.RGBtoHSV();
	}
	
	
	@Test 
	public void BrightnessTest() {
		Brightness brightness = new Brightness (100);
		double diff = brightness.setBrightness();
		boolean result = diff!=0;
		Assert.assertTrue(result);
	}
	
	
	@Before
	public void prepForSaturationTest() {
		boolean result = false;
		String fileName = System.getProperty("user.dir")+ "\\" + "Lena.ppm";  //Windowson  \ a szeparator
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		System.out.println(fileName);
		File file = new File (fileName);
		if (file.exists()) {
			try {
				result = ReadImage.readfile(fileName);
				} catch (invalidFileTypeException ivft) {}
		}
		else {
			fileName = System.getProperty("user.dir")+ "/" + "Lena.ppm";  //Linuxon meg ez
			file = new File (fileName);
			if (file.exists()) {
				try {
					result = ReadImage.readfile(fileName);
					} catch (invalidFileTypeException ivft) {}
			}
			else {
				System.out.println(fileName + " does not exist!");
			}
		}
		HSVBase.RGBtoHSV();
	}
	
	@Test 
	public void SaturationTest() {
		Saturation saturation = new Saturation (100);
		double diff = saturation.setSaturation();
		boolean result = diff!=0;
		Assert.assertTrue(result);
	}
	
	@Test 
	public void BlurTest() {
		Blur blur = new Blur(15);
		boolean result = blur.blurImage(15);
		Assert.assertTrue(result);
	}
	
	@Test 
	public void FindEdgesTest() {
		FindEdges findedges = new FindEdges();
		boolean result = findedges.findEdges();
		Assert.assertTrue(result);
	}
	
	
	@Test
	public void writeToFileTest() {
		String fileName = System.getProperty("user.dir")+ "\\" + "JUnitTestProba.ppm";
		WriteImage writeimage= new WriteImage();
		boolean result = writeimage.writefile(fileName);
		Assert.assertTrue(result);
		
	}
	

}
