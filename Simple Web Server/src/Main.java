import java.io.*;
import java.net.*;
import java.nio.file.Files;

public class Main {
    public static void main(String [] args) throws Exception{

        try(ServerSocket serverSocket = new ServerSocket(8080, 50, InetAddress.getByName("0.0.0.0"))){
            
            System.out.println("Server connected on port 8080"+"\n"+"Waiting for connection...");

            while(true){
                try(Socket client = serverSocket.accept()) {
                    System.out.println("Debug , got new message: " + client.toString());
                    //Read the request
                    BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String line = "";
                    StringBuilder request = new StringBuilder();

                    while(!(line = br.readLine()).trim().isEmpty()) //Continue unless find a blank line
                    {
                    request.append(line+"\r\n");
                    }
                    System.out.println("--REQUEST--");
                    System.out.println(request.toString());


                    //Choose how to respond
                    File image = new File("helloWorld.png");

                    try(OutputStream clientOutput = client.getOutputStream()){
                    clientOutput.write(("HTTP/1.1 200 OK\r\n").getBytes()); //Because write accepts bytes as parameter
                    clientOutput.write(("\r\n").getBytes()); //To write a HTTP answer we have to write the header and the content both separated by a blank line
                    clientOutput.write(Files.readAllBytes(image.toPath()));
                    clientOutput.flush();
                    }

                    }


                }
            }
        }
    }

