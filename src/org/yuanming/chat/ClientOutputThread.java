package org.yuanming.chat;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ClientOutputThread extends Thread
{
    private Socket socket;
    private User user;
    
    public ClientOutputThread(Socket socket, User user)
    {
        this.user = user;
        this.socket = socket;
    }
    
    @Override
    public void run()
    {
        try
        {
            OutputStream os = socket.getOutputStream();
            ByteArrayOutputStream f = new ByteArrayOutputStream();
            while(true)
            {  
                String str1 = user.send_text.getText();
                
                byte[] buffer = str1.getBytes();
                
                f.write(buffer);
                
                f.writeTo(os);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
