package org.yuanming.chat;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

public class ServerInputThread extends Thread
{
    private Socket socket;
    private static List<String> users_info_list;
    private static List<Socket> socket_list = new LinkedList<Socket>();
    //private static List<InputStream> is_list = new LinkedList<InputStream>();
    private ServerListener serverListener;
    
    public ServerInputThread(Socket socket,ServerListener serverListener)
    {   
        this.socket = socket;
        socket_list.add(socket);
        this.socket = socket;
        socket_list.add(socket);
        
    }
    
    @Override
    public void run()
    {
        try
        {
            InputStream is = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int length = is.read(buffer);
            String str = new String(buffer, 0, length);
            
            
            /**
             * 判断传来的用户名是否已经存在
             */
            if(users_info_list.contains(str))
            {
                //出错处理！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
                JOptionPane.showMessageDialog( null,"用户名相同，请重新输入 !");
            }
            else
            {
                users_info_list.add(str);
                //打印新上线的用户的名字
                serverListener.area.append(str); 
            }  
            //is.close();
            while(true)
            {
                byte[] buffer1 = new byte[1024];
                int length1 = is.read(buffer1);
                String str1 = new String(buffer1, 0, length1);
                for(Socket socket : socket_list)
                {
                    socket.getOutputStream().write(str1.getBytes());
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
