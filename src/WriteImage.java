import java.io.*;

public class WriteImage {
	


		public static void writefile(String fileName) {

			String outputFile = fileName;
			System.out.println(outputFile);
			
			
			try (OutputStream outputStream = new FileOutputStream(outputFile);){
			      FileWriter imageWriter = new FileWriter(outputFile);
			      int W = ReadImage.imgParams.Width;
			      int H = ReadImage.imgParams.Height;
			      
			      imageWriter.write("P6\n");
			      imageWriter.write("# Processed by ImageProcessing java app\n");
			      imageWriter.write(W + " " + H + "\n" );
			      imageWriter.write(ReadImage.imgParams.Depth + "\n");
			      
			      for (int i = 0; i < H; i++){
			        	for (int j = 0; j < W; j++){
			            for (int k = 0; k<3; k++){
			                 //fread(&p,  sizeof(unsigned char), 1, inputfile) ;
			                 // v = (short)inputStream.read();
			            	//int byteRead2 = inputStream.read();
			            	//int v = byteRead2;
			            	
			                 //PixelData pxData = new PixelData();
			                 switch (k%3) {
			                     case 0: {
			                    	 //imageWriter.write((short)ReadImage.imgParams.RGBMatrix[i][j].r);
			                        //imageWriter.write(Integer.toBinaryString(ReadImage.imgParams.RGBMatrix[i][j].r));
			                    	 outputStream.write(ReadImage.imgParams.RGBMatrix[i][j].b);
			                    	 break;
			                     }
			                     case 1: {
			                    	//imageWriter.write((short)ReadImage.imgParams.RGBMatrix[i][j].g);
			                    	 
			                    	 outputStream.write(ReadImage.imgParams.RGBMatrix[i][j].r);
			                    	 //imageWriter.write(Integer.toBinaryString(ReadImage.imgParams.RGBMatrix[i][j].g));
			                        //System.out.println(pxData.g);
			                        break;
			                     }
			                     case 2: {
			                    	 //imageWriter.write((short)ReadImage.imgParams.RGBMatrix[i][j].b);
			                    	 outputStream.write(ReadImage.imgParams.RGBMatrix[i][j].g);
			                    	// imageWriter.write(Integer.toBinaryString(ReadImage.imgParams.RGBMatrix[i][j].b));
			                        //System.out.println(pxData.b);
			                        break;
			                     }
			                }//end case
			            }// end for k
			           // matrix[i][j] = new PixelData(r,g,b);
			        }//end for j
			    }//end for i 
			      
			      
			      imageWriter.close();
			      System.out.println("Successfully wrote to the file.");
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
			
		}

}
