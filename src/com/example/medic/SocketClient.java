package com.example.medic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

import android.os.StrictMode;
import android.util.Log;

public class SocketClient {
	private String serverIp;
	private int serverSocket;
	private boolean isConnected;
	private String JSONResult = "";
	private Socket currentSocket;
	private int Tabletid = -1;
	private String questionnaireResults = "";
	private String stringTabletId = "";
	  	
	public SocketClient(String ip, int socket){
		serverIp = ip;
		if (socket >= 0 && socket <= 9999){serverSocket = socket;}
		else{ Log.d("AndroidSocket", "Socket is not in correct format, it is: " + socket); }
		Thread cThread = new Thread(new SocketThread());  
         cThread.start();  
   }
	
	//returns JSON received from Server
	public String getJSON(){
		return JSONResult;
	}
	    
	public boolean getIsConnected(){
		return isConnected;
	}
	
	public void sendQResults(String results){
		questionnaireResults = results;
	}

	// Used to connect to socket & obtain JSON in another thread
	  public class SocketThread implements Runnable {  
		  private PrintStream printStreamToServer;
		  private BufferedReader buffedReader;
		  private String currentServerResponse;
		  
		  
          public void run() {  
        	  
              try {  
                  InetAddress serverAddress = InetAddress.getByName(serverIp);  
                  Log.d("Android Socket", "Connecting to Server");  
                   currentSocket = new Socket(serverAddress,  
                          serverSocket);  
                   isConnected = true;
                                   
                  //Block of statements below establish client-server relationship by sending TabletId to server
                      printStreamToServer = new PrintStream(currentSocket.getOutputStream());
                      InputStreamReader inputReader = new InputStreamReader(currentSocket.getInputStream());
      				  buffedReader = new BufferedReader(inputReader);
                      //printStreamToServer.println(Tabletid);
      				  //printStreamToServer.flush();
                	  //checkTabletId();
                	  
                
                  // At this stage, we are awaiting server to send JSON
                   readLineFromServer();
          		   while (currentServerResponse != null){
          				JSONResult += currentServerResponse;
          				readLineFromServer();
          			}
                    Log.d("Android Socket", "C: JSON Received");  
                while (isConnected){
                	
                     //Allows us to send results once they are input into questionnaireResults
                     if (questionnaireResults != ""){
                    	 printStreamToServer.println(questionnaireResults);
                    	 printStreamToServer.flush();
                     }
                     
                }
                    
                    
                    
                 } catch (Exception e) {  
                  Log.e("ClientActivity", "C: Error", e);  
                }  
          }  
      
          /*private void checkTabletId(){
        	  Tabletid == -1 implies unassigned tablet id, & therefore requires the 
			   android client to listen & await to be assigned one from the server 
        	  if (Tabletid == -1){
	    		  try{currentServerResponse = buffedReader.readLine();}
	    			catch (Exception e) {Log.e("ClientActivity", "S: Error", e);}  
	    			
	    			while (currentServerResponse != null){
	    				stringTabletId = stringTabletId + currentServerResponse; 
	    				
	    			}
	    			//Parses & assigns tablet id, while clearing our serverResponse cache
	    			Tabletid = Integer.parseInt(stringTabletId);
	    			currentServerResponse = "";
        	  }
    	  }*/
          
          private void readLineFromServer(){
        	  try{
        		  currentServerResponse = buffedReader.readLine();
        	  }	catch (Exception e) {
				Log.e("ClientActivity", "S: Error", e);
        	  }       	  
          }
	  
	  }  
	  
	  
  }
	