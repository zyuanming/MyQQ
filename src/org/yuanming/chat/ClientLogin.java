package org.yuanming.chat;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;



class Client extends JFrame implements ActionListener
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    JButton button_login;
    JButton button_reset;
    JTextField name_field;
    JTextField address_field;
    JTextField port_field;
    
    public Client()
    {
        super("欢迎登录");
        button_login = new JButton("登陆");
        button_reset = new JButton("重置");
        name_field = new JTextField(8);
        address_field = new JTextField(6);
        port_field = new JTextField(8);
        
        JPanel login_panel = new JPanel(new FlowLayout(3, 60, 40));
        login_panel.add(new JLabel("用户名"));
        login_panel.add(name_field);
        login_panel.add(new JLabel("服务器地址"));
        login_panel.add(address_field);
        login_panel.add(new JLabel("端口号"));
        login_panel.add(port_field);
        login_panel.add(button_login);
        login_panel.add(button_reset);
        
        login_panel.setBorder(BorderFactory.createTitledBorder(null, "用户登录", 
                TitledBorder.DEFAULT_JUSTIFICATION, 
                TitledBorder.DEFAULT_POSITION, 
                new java.awt.Font("微软雅黑", 0, 14), 
                java.awt.Color.BLUE));
        
        Container conta = this.getContentPane();
        
        conta.add(login_panel, BorderLayout.CENTER);
        setBounds(100,100,300,350);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // TODO Auto-generated method stub
        
    }
    
}

public class ClientLogin
{
    public static void main(String[] args)
    {
        new Client();
    }
}
