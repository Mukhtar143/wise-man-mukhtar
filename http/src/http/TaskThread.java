package http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.channels.FileChannel;

public class TaskThread extends Thread{
	
	private Socket s;
	
	public TaskThread(Socket s)
	{
		this.s = s;
	}
	
	@Override
	public void run() {
		 FileInputStream in = null;
		 OutputStream os = null;		
		 BufferedReader reader=null;
		 PrintStream writer =null;
		try {
			os = s.getOutputStream();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
			  String firstLineOfRequest;
			  firstLineOfRequest = reader.readLine();
			  
			  String uri = firstLineOfRequest.split(" ")[1];

			    writer = new PrintStream(s.getOutputStream());
		        writer.println("HTTP/1.1 200 OK");
				if(uri.endsWith(".html"))
				{
					   writer.println("Content-Type:text/html");			       					
				}
				else if(uri.endsWith(".jpg"))
				{
					 writer.println("Content-Type:image/jpeg");	
			       
				}	
				else
				{
					writer.println("Content-Type:application/octet-stream");
				}

				in = new FileInputStream("./dir/project.txt");
				
				
		        writer.println("Content-Length:" + in.available());
		        
		        writer.flush();

				 
	            byte[] b = new byte[1024];
	            int len = 0;
	            len = in.read(b);
	            while(len!=-1)
	            {
	        	   os.write(b, 0, len);
	        	   len =  in.read(b);
	            }
	            os.flush();
	           
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
				 writer.println("HTTP/1.1 404 Not Found");	
				 writer.println("Content-Type:text/plain");	
				 writer.println("Content-Length:7");	
				 writer.println();

				 
				  writer.print("http server is ready");
				  writer.flush();	
		}
		finally
		{
               try {
            	   if(in!=null)
            	   {
            			in.close();
            	   }
            	   if(os!=null)
            	   {        
    		           os.close();
            	   }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	           
	         
		}
		
	}

}
