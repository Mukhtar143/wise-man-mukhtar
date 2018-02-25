package http;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class httpClient {

	public static void main(String[] args) {
		
		
        String host = "localhost";
        int port = 8888;
        String filename = "./project.txt";
        String savelocation = "./dir";
        
        try {
			Socket s = new Socket(host, port);
			
			
			PrintStream writer = new PrintStream(s.getOutputStream());
			writer.println("GET /"+filename+" HTTP/1.1");
			writer.println("Host:localhost");
			writer.println("connection:keep-alive");
			writer.println();
			writer.flush();
			
			InputStream in = s.getInputStream();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			  
			String firstLineOfResponse = reader.readLine();//HTTP/1.1 200 OK
			System.out.println(firstLineOfResponse);
			String secondLineOfResponse = reader.readLine();//Content-Type:text/html
			System.out.println(secondLineOfResponse);
			String threeLineOfResponse = reader.readLine();//Content-Length:
			System.out.println(threeLineOfResponse);
			String fourLineOfResponse = reader.readLine();//blank line
			System.out.println(fourLineOfResponse);
			if(firstLineOfResponse.indexOf("200")!=-1)
			{
				//success
			       byte[] b = new byte[1024];
			       OutputStream out = new FileOutputStream(savelocation+"/"+filename);
			       int len = in.read(b);
			       while(len!=-1)
			       {		    	   
			    	   out.write(b, 0, len);
			    	   len = in.read(b);
			       }
			        
			       in.close();
			       out.close();
				
			}
			else
			{
				//output error message
				StringBuffer result = new StringBuffer();
		        String line;
		        while ((line = reader.readLine()) != null) {
		            result.append(line);
		        }
		        reader.close();
		        System.out.println(result);
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
       


	}

}
