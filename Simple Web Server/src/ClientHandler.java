import java.net.*;
public class ClientHandler implements Runnable{
    private Socket socket ;

    public ClientHandler(Socket socket){
        this.socket = socket; //Accept connection
    }

    @Override
    public void run(){

    }
}
