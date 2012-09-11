package org.yuanming.chat;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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
class Server extends JFrame implements ActionListener
{
   
    JList onlineList;
    JButton button;
    JTextField text;
    JTextArea area;
    JLabel label;
    
    public Server()
    {
        super("服务器");
        onlineList = new JList();
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
        JScrollPane jscroll = new JScrollPane(onlineList);
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
        
    }
}

public class ServerListener
{
    public static void main(String[] args)
    {
        new Server();
    }
}







  
  

