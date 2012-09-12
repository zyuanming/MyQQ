package org.yuanming.chat;


import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientInputThread extends Thread
{
    private Socket socket;
    private User user;

    public ClientInputThread(Socket socket, User user)
    {
        this.socket = socket;
        this.user = user;
    }

    @Override
    public void run()
    {
        try
        {
            InputStream is = socket.getInputStream();
            while(true)
            {
                byte[] buffer = new byte[1024];
                
                int length = is.read(buffer);
                
                String str = new String(buffer, 0, length);
                
                user.chat_text.append(str);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
