import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    public static void main(String [] args) throws Exception{

        try(ServerSocket serverSocket = new ServerSocket(8080)){
            System.out.println("Server connected on port 8080"+"\n"+"Waiting for connection...");

            while(true){
                try(Socket client = serverSocket.accept()){
                    //Open InputStream
                    BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String line = "";
                    StringBuilder request = new StringBuilder();
                    while(!(line = br.readLine()).trim().isEmpty()){
                    request.append(line+"\r\n");
                    }
                    System.out.println("--REQUEST--"+"\n");
                    System.out.println(request);

                    OutputStream clientOutput = client.getOutputStream();
                    clientOutput.write(("HTTP/1.1 200 OK\r\n").getBytes());
                    clientOutput.write(("\r\n").getBytes());
                    clientOutput.write(("HELLO WORLD").getBytes());
                    clientOutput.flush();
                }
            }
        }
    }
}
