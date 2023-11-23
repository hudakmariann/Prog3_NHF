import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;

public class ReadImage {

	public static void readfile(String fileName) {

		String inputFile = fileName;
		String outputFile = "./copy.ppm";

		BufferedReader reader;

		try (InputStream inputStream = new FileInputStream(inputFile);
				OutputStream outputStream = new FileOutputStream(outputFile);) {

			reader = new BufferedReader(new FileReader(inputFile));

			String[] fileinfo = new String[3];
			int n = 0;
			//int linelength = 0;
			int linecount = 0;
			int byteRead;
			String line = reader.readLine();
			 //while ((byteRead = inputStream.read()) != '\n') {}
			//linelength += line.length();
			while (Character.isAlphabetic(line.charAt(0)) || Character.isDigit(line.charAt(0))
					|| line.charAt(0) == '#') {

				if (line.charAt(0) != '#') {// komment kiszurese
					fileinfo[n] = line;
				}
				System.out.println("fileinfo = " + n + "  " + fileinfo[n]);
				if (linecount == 0 && line.equals("P6")) {
					System.out.println("Successful identification as valid *.ppm file. Line : " + line);

				} else if (linecount == 0 && (!(line.equals("P6")))) {
					System.out.println("Error: not a valid *.ppm file. Line = " + line);
				}
				
				
						
				line = reader.readLine();
				while ((byteRead = inputStream.read()) != '\n') {}

				System.out.println("Line = " + line);
				if (line.charAt(0) != '#')
					n++;
				linecount++;

			}
			

			String[] splited = fileinfo[1].trim().split("\\s+");
			int Width = Integer.valueOf(splited[0]);
			int Height = Integer.valueOf(splited[1]);
			;
			int Depth = Integer.valueOf(fileinfo[2]);
			
			
			//ez olvassa be kulon a binaris reszt
			/*while ((byteRead2 = inputStream.read()) != -1) {
                outputStream.write(byteRead2);
            }*/

			System.out.println("Width = " + Width);
			System.out.println("Height = " + Height);
			System.out.println("Depth = " + Depth);
			
			PixelData px = new PixelData(255,127,0);
			PixelData[][] matrix = new PixelData[Width][Height];
			for (int h = 0; h < Height; h++) {
				for (int w = 0; w < Width; w++) {
					matrix[h][w] = px;
				}
			}
			System.out.println("Ready1");
			
			
			//int byteRead2 = -1;
			//short v = -1;
			//String str1 = "";
			/*
			while ((byteRead2 = inputStream.read()) != -1) {
				System.out.println(byteRead2);
			}*/
			int r = 255;
        	int g = 255;
        	int b = 255;
        	
			
		        for (int i = 0; i < Height; i++){
		        	for (int j = 0; j < Width; j++){
		            for (int k = 0; k<3; k++){
		                 //fread(&p,  sizeof(unsigned char), 1, inputfile) ;
		                 // v = (short)inputStream.read();
		            	int byteRead2 = inputStream.read();
		            	int v = byteRead2;
		            	
		                 //PixelData pxData = new PixelData();
		                 switch (k%3) {
		                     case 0: {
		                    	r = v;
		                        //System.out.println(pxData.r);
		                        break;
		                     }
		                     case 1: {
		                    	 g = v;
		                        //System.out.println(pxData.g);
		                        break;
		                     }
		                     case 2: {
		                    	b = v;
		                        //System.out.println(pxData.b);
		                        break;
		                     }
		                }//end case
		                 
		                 
		            }// end for k
		            matrix[i][j] = new PixelData(r,g,b);
		        }//end for j
		    }//end for i 
		        
			

			System.out.println("Ready2"); 
			
			System.out.println(matrix[234][125].r);
			System.out.println(matrix[502][13].r);
			System.out.println(matrix[302][139].r);
			System.out.println("=======================================");
			String str = "";
			for (int h = 0; h < Height; h++) {
				
				for (int w = 0; w < Width; w++) {
					 str= ("[" + h +"]" + "[" + w + " ] " + "("+ matrix[h][w].r+","+matrix[h][w].g+","+ matrix[h][w].b +")");
					//System.out.println(matrix[h][w].r);
					
				}
				//outputStream.write('\n');
				System.out.println(str);
				//str+="\n";
				
				//outputStream.write(str.getBytes(Charset.forName("UTF-8")));
				
			}


			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
