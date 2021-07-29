import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.util.*;
import java.lang.reflect.Method;

public class home extends JFrame implements ActionListener {  
    
    private JButton b0;

    home() {
        super("Hospital Management System");
		Container con=getContentPane();
        con.setLayout(null);
        con.setBackground(new Color(153, 204, 255));

        JLabel l1;
        l1=new JLabel("File Structures Mini project");
        l1.setBounds(520,50, 1700,150);
        con.add(l1);
        con.setSize(300,300);
        con.setLayout(null);
        con.setVisible(true);
        Font font = new Font("Verdana", Font.BOLD, 35);
        l1.setFont(font);
        l1.setForeground(Color.BLACK);

        JLabel l2;
        l2=new JLabel("B-Tree Implementation");
        l2.setBounds(520,50, 1400,250);
        con.add(l2);
        con.setSize(300,300);
        con.setLayout(null);
        con.setVisible(true);
        Font font2 = new Font("Verdana", Font.BOLD, 25);
        l2.setFont(font2);
        l2.setForeground(Color.BLACK);

        JLabel l3;
        l3=new JLabel("Built by Adarsh Hiremath, Anand Kulkarni, Ananth D");
        l3.setBounds(520,50, 1400,350);
        con.add(l3);
        con.setSize(300,300);
        con.setLayout(null);
        con.setVisible(true);
        Font font3 = new Font("Verdana", Font.PLAIN, 25);
        l3.setFont(font3);
        l3.setForeground(Color.BLACK);        
		
		b0=new JButton("Get Started");
		b0.addActionListener(this);
		b0.setBounds(650,475,175, 50);
        Font font1 = new Font("Verdana", Font.BOLD, 16);
        b0.setFont(font1);
        b0.setForeground(Color.BLACK);
        b0.setBackground(new Color(0, 153, 255));
		
        con.add(b0);
    }

    
    public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b0)
		{
			try 
          {   
            Runtime.getRuntime().exec("cmd /c start A:\\winscript.cmd");   
           } 
           catch (Exception e) 
          {   
              e.printStackTrace();   
           }
		}
        this.dispose();
	}
	
	public static void main(String args[])
	{
        home frame=new home();
        frame.setSize(1650,1080);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setUndecorated(true);
        frame.setVisible(true);
	}
}