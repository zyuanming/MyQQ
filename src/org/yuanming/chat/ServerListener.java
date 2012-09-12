package org.yuanming.chat;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


/**
 * 完成服务器端的界面和事件监听
 * @author 1
 *
 */
public class ServerListener extends JFrame implements ActionListener
{
   
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    JButton button;
    JTextField text;         //端口号
    JTextArea area;          //当前在线用户
    JLabel label;
    
    public ServerListener()
    {
        super("服务器");
        area = new JTextArea();
        text = new JTextField(10);
        button = new JButton("开始监听");
        label = new JLabel("停止中");
        
        button.addActionListener(this);
        

        JPanel p = new JPanel();
        JPanel pp = new JPanel();
        

        /**
         * 位于面板最上方，端口选择
         */
        p.add(new JLabel("端口号："));
        p.add(text);
        p.add(button);
        
        
        /**
         * 位于面板最下方，显示服务器的状态信息
         */
        pp.add(new JLabel("服务器状态"));
        pp.add(label);
        
        p.setBorder(BorderFactory.createTitledBorder(null, "服务器信息", 
                TitledBorder.DEFAULT_JUSTIFICATION, 
                TitledBorder.DEFAULT_POSITION, 
                new java.awt.Font("微软雅黑", 0, 12), 
                java.awt.Color.BLUE));
        Container con = this.getContentPane();

        /**
         * 在线用户列表的显示
         */
        JScrollPane jscroll = new JScrollPane(area);
        jscroll.setBorder(BorderFactory.createTitledBorder(null, "在线用户列表", 
                TitledBorder.DEFAULT_JUSTIFICATION, 
                TitledBorder.DEFAULT_POSITION, 
                new java.awt.Font("微软雅黑", 0, 12), 
                java.awt.Color.BLUE));
        
        /**
         * 整个面板的构造和组成
         */
        con.add(p, BorderLayout.NORTH);
        con.add(jscroll, BorderLayout.CENTER);
        con.add(pp, BorderLayout.SOUTH);
        
        /**
         * 设置面板整体的大小，可视，不可调整大小
         */
        setBounds(60, 60, 350, 500);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
       String str = new String(text.getText());
       //String[] str_array = str.split()
       /*if(str.contains("1234567890"))
       {*/
           int port_number = Integer.parseInt(str);
           this.label.setText("监听中");
           this.button.setText("停止服务器");
           this.button.setEnabled(false);
          // if(port_number > 0 && port_number <= 65535)
          // {
               try
               {
                   ServerSocket serverSocket = new ServerSocket(port_number);
                   while(true)
                   {
                       Socket socket = serverSocket.accept();
                       new ServerInputThread(socket, this).start();
                   }
               }
               catch (IOException e1)
               {
                   e1.printStackTrace();
               }
          // }
          /* else
           {
               //new JDialog(this, "只能是0到65535的数字之间");
               JOptionPane.showMessageDialog( null,"只能是0到65535的数字之间!");
           }
           
       }
       else
       {
           //new JDialog(this, "只能是0到65535的数字之间");
           JOptionPane.showMessageDialog( null,"只能是0到65535的数字之间!");
       }*/
    }
}
