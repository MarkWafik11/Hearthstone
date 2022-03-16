package Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;



public class ErrorMessage extends JFrame {
	private JTextArea text;
	public JTextArea getText() {
		return text;
	}
	public void setText(JTextArea text) {
		this.text = text;
	}
	public ErrorMessage(){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(300,150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
        text = new JTextArea();
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setEditable(false);       
        add(text);
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);		
		setResizable(false);
		this.setVisible(true);
		
		repaint();
		revalidate();
		
	}
	
	public static void main(String[] args) {
	     JOptionPane.showMessageDialog(null, "There's a bug on you!",
	      "Hey!", JOptionPane.ERROR_MESSAGE);

	  }
	
	   
	}
	
