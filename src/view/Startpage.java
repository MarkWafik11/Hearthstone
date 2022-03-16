package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Startpage extends JFrame implements ActionListener {
	  
	   private JPanel panel;
	   private JButton startButton;
	
	public JPanel getPanel() {
		return panel;
	}
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	public JButton getStartButton() {
		return startButton;
	}
	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}
	public Startpage(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height);
		this.setTitle("HearthStone");
		setVisible(true);;
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		ImagePanel panel = new ImagePanel(new ImageIcon("Graphics/Logo.png").getImage());

		
	    JButton startButton = new JButton();
	    startButton.addActionListener(this);
	    startButton.setHorizontalTextPosition(JButton.CENTER);
	    
	    startButton.setRolloverEnabled(true);
	    startButton.setFocusPainted(false);
	    startButton.setBorderPainted(false);
	    startButton.setContentAreaFilled(false);
	    startButton.setPreferredSize(new Dimension(10,250));
	    startButton.setIcon(new ImageIcon("Graphics/StartButton.png"));
	    startButton.addActionListener(this);
	    startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
	    panel.add(Box.createVerticalGlue());
	    panel.add(startButton);
	    panel.add(Box.createVerticalGlue());
	    
	    
	    startButton.setVisible(true);
	    this.add(panel);
		repaint();
		revalidate();
	    

	    
		
	}

	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText().equals("Start Game")){
			this.setVisible(false);
		}
		this.setVisible(false);

		
	}

	}
