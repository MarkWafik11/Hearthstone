package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;

public class CurrentHero extends JFrame implements ActionListener{
	private Hero CurrentHero;
	private ArrayList<JButton> HerosButton;
	private ArrayList<Hero> Heros;
	public CurrentHero() throws IOException, CloneNotSupportedException{
		JPanel currentHero = new JPanel();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height);
		currentHero.setBackground(Color.MAGENTA);
		this.add(currentHero);
		this.setTitle("Choose First Hero");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		HerosButton = new ArrayList<JButton>();
		Heros = new ArrayList<Hero>();
		
		ImagePanel heros = new ImagePanel(new ImageIcon("Graphics/Back.png").getImage());
		heros.setLayout(new GridLayout(0,5));
		this.add(heros,BorderLayout.CENTER);
		
		JButton hunter = new JButton();
		HerosButton.add(hunter);
		Heros.add(new Hunter());
		hunter.addActionListener(this);
	    hunter.setRolloverEnabled(true);
	    hunter.setBorderPainted(false);
	    hunter.setFocusPainted(false);
	    hunter.setContentAreaFilled(false);
	    hunter.setIcon(new ImageIcon("Graphics/HunterLogo.png"));
	    hunter.setVisible(true);
	    
		JButton mage = new JButton();
		HerosButton.add(mage);
		Heros.add(new Mage());
	    mage.setRolloverEnabled(true);
	    mage.setFocusPainted(false);
	    mage.setBorderPainted(false);
	    mage.setContentAreaFilled(false);
		mage.setIcon(new ImageIcon("Graphics/MageLogo.png"));
		mage.addActionListener(this);
		
		JButton paladin = new JButton();
		paladin.addActionListener(this);
		HerosButton.add(paladin);
		Heros.add(new Paladin());
	    paladin.setRolloverEnabled(true);
	    paladin.setFocusPainted(false);
	    paladin.setBorderPainted(false);
	    paladin.setContentAreaFilled(false);
		paladin.setIcon(new ImageIcon("Graphics/PaladinLogo.png"));
		paladin.setVisible(true);
	    
		JButton priest = new JButton();
		priest.addActionListener(this);
		HerosButton.add(priest);
		Heros.add(new Priest());
	    priest.setRolloverEnabled(true);
	    priest.setFocusPainted(false);
	    priest.setBorderPainted(false);
	    priest.setContentAreaFilled(false);
		priest.setIcon(new ImageIcon("Graphics/PriestLogo.png"));
		priest.setVisible(true);
		
		JButton warlock = new JButton();
		warlock.addActionListener(this);
		HerosButton.add(warlock);
		Heros.add(new Warlock());
	    warlock.setRolloverEnabled(true);
	    warlock.setFocusPainted(false);
	    warlock.setBorderPainted(false);
	    warlock.setContentAreaFilled(false);
		warlock.setIcon(new ImageIcon("Graphics/WarlockLogo.png"));
		warlock.setVisible(true);
	    
		heros.add(hunter);
		heros.add(mage);
		heros.add(paladin);
		heros.add(priest);
		heros.add(warlock);
		
		this.setVisible(true);
		repaint();
		revalidate();
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		String hero = b.getText();
		Hero CurrentHero = null;
		try {
			CurrentHero = createHero(b);
		} catch (IOException e1) {
			String err=e1.getMessage();
			JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
		} catch (CloneNotSupportedException e1) {
			String err=e1.getMessage();
			JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
		}
		this.CurrentHero =  CurrentHero;
		this.setVisible(false);
	}
	
	public Hero createHero(JButton s) throws IOException, CloneNotSupportedException{
		Hero selected = null;
		if(HerosButton.contains(s)){
			int x = HerosButton.indexOf(s);
			selected = Heros.get(x);
		}

		return selected;
		}

	public Hero getCurrentHero() {
		return CurrentHero;
	}
	

	
   
}

