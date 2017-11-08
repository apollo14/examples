package js.f;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.transform.stream.StreamResult;

public class ClasspathFileReader {
	private String fileName;

	public ClasspathFileReader(String fileName) {
		super();
		this.fileName = fileName;
	}
	
	public String read(){
		String result = "";
		String line = "";
		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))){
			while((line=br.readLine()) != null){
				result = result + line;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioe){
			ioe.printStackTrace();
		}
		return result;
	}

}
