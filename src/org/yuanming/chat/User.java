package org.yuanming.chat;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;



public class User extends JFrame
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    JButton button_send;
    JButton button_clear;
    JTextArea chat_text;
    JTextField send_text;
    JTextArea users_online_list;
    //private Socket socket;
    private static List<String> user_name_list = new LinkedList<String>();
    
    public User(Socket socket,String user_name)
    {
        
       //this.socket = socket;
        user_name_list.add(user_name);
        new ClientOutputThread(socket, this).start();
        new ClientInputThread(socket, this).start();
        
        button_send = new JButton("发送");
        button_clear = new JButton("清屏");
        chat_text = new JTextArea("", 20, 15);
        send_text = new JTextField(30);
        users_online_list = new JTextArea("", 20, 10);
       
        users_online_list.setFocusable(false);
        send_text.setFocusable(true);
        chat_text.setFocusable(false);
        button_send.addActionListener(new sendAction(this));
        button_clear.addActionListener(new clearAction(this));
        
        
        
        //JPanel chat_panel = new JPanel();
        //chat_panel.add(chat_text);
        JScrollPane chat_scroll = new JScrollPane(chat_text);
        chat_scroll.setBorder(BorderFactory.createTitledBorder(null, "聊天内容", 
                TitledBorder.DEFAULT_JUSTIFICATION, 
                TitledBorder.DEFAULT_POSITION, 
                new java.awt.Font("微软雅黑", 0, 12), 
                java.awt.Color.BLUE));
        
        JPanel send_panel = new JPanel();
        send_panel.add(send_text);
        send_panel.add(button_send);
        send_panel.add(button_clear);
        send_panel.setBorder(BorderFactory.createTitledBorder(null, "聊天内容", 
                TitledBorder.DEFAULT_JUSTIFICATION, 
                TitledBorder.DEFAULT_POSITION, 
                new java.awt.Font("微软雅黑", 0, 12), 
                java.awt.Color.BLUE));
        
        JScrollPane jscroll = new JScrollPane(users_online_list);
        jscroll.setBorder(BorderFactory.createTitledBorder(null, "在线用户列表", 
                TitledBorder.DEFAULT_JUSTIFICATION, 
                TitledBorder.DEFAULT_POSITION, 
                new java.awt.Font("微软雅黑", 0, 12), 
                java.awt.Color.BLUE));
        
        Container cont = this.getContentPane();
        cont.add(chat_scroll, BorderLayout.CENTER);
        cont.add(jscroll, BorderLayout.EAST);
        cont.add(send_panel, BorderLayout.SOUTH);
        
        
        setBounds(60, 60, 500, 600);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
}

class sendAction implements ActionListener
{
    private User user;
    public sendAction(User user)
    {
        this.user = user; 
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        user.send_text.setText("");
    } 
}

class clearAction implements ActionListener
{
    private User user;
    public clearAction(User user)
    {
        this.user = user;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        user.chat_text.setText("");
    }
}

