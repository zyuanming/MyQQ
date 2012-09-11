package org.yuanming.chat;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;



public class ClientLogin extends JFrame
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
    
    public ClientLogin()
    {
        super("欢迎登录");
        button_login = new JButton("登陆");
        button_reset = new JButton("重置");
        name_field = new JTextField(8);
        address_field = new JTextField(6);
        port_field = new JTextField(8);
        
        button_login.addActionListener(new loginAction(this));
        button_reset.addActionListener(new resetAction(this));
        
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
}

class loginAction implements ActionListener
{
    private ClientLogin clientLogin;
    public loginAction(ClientLogin clientLogin)
    {
        this.clientLogin = clientLogin;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            String str = new String(clientLogin.port_field.getText());
            if(str.contains("1234567890"))
            {
                int port_number = Integer.parseInt(str);
                if(port_number > 0 && port_number <= 65535)
                {
                    try
                    {
                        Socket socket = new Socket("192.168.1.100", port_number);
                        new User(socket, clientLogin.name_field.getText());
                        
                        ByteArrayOutputStream f = new ByteArrayOutputStream();
                        
                        str = clientLogin.name_field.getText();
                        
                        byte[] buffer = str.getBytes();
                        
                        f.write(buffer);
                        
                        OutputStream os = socket.getOutputStream();
                        
                        f.writeTo(os);
                        
                        f.close();
                        os.close();
                        System.exit(0);  
                    }
                    catch (IOException e1)
                    {
                        e1.printStackTrace();
                    }
                }
                else
                {
                    //new JDialog(this, "只能是0到65535的数字之间");
                    JOptionPane.showMessageDialog( null,"只能是0到65535的数字之间!");
                }
                
            }
            else
            {
                //new JDialog(this, "只能是0到65535的数字之间");
                JOptionPane.showMessageDialog( null,"只能是0到65535的数字之间!");
            }
        }
        catch (NumberFormatException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}

class resetAction implements ActionListener
{
    private ClientLogin clientLogin;
    
    public resetAction(ClientLogin clientLogin)
    {
        this.clientLogin = clientLogin;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        clientLogin.address_field.setText("");
        clientLogin.name_field.setText("");
        clientLogin.port_field.setText("");
    }
}
