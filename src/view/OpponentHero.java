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

public class OpponentHero extends JFrame implements ActionListener{
	private Hero OpponentHero;
	private ArrayList<JButton> HerosButton;
	private ArrayList<Hero> Heros;
	public OpponentHero() throws IOException, CloneNotSupportedException{
		JPanel opponentHero = new JPanel();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height);
		
		this.setTitle("Choose Second Hero");
		opponentHero.setBackground(Color.MAGENTA);
		this.add(opponentHero);
		HerosButton = new ArrayList<JButton>();
		Heros = new ArrayList<Hero>();
		
		ImagePanel heros = new ImagePanel(new ImageIcon("Graphics/Back.png").getImage());
		heros.setLayout(new GridLayout(0,5));
		this.add(heros,BorderLayout.CENTER);
		
		JButton hunter = new JButton();
		hunter.addActionListener(this);
		HerosButton.add(hunter);
		Heros.add(new Hunter());
		hunter.setIcon(new ImageIcon("Graphics/HunterLogo.png"));
		hunter.addActionListener(this);
	    hunter.setRolloverEnabled(true);
	    hunter.setFocusPainted(false);
	    hunter.setBorderPainted(false);
	    hunter.setContentAreaFilled(false);

	    
		JButton mage = new JButton();
		mage.addActionListener(this);
		HerosButton.add(mage);
		Heros.add(new Mage());
		mage.setIcon(new ImageIcon("Graphics/MageLogo.png"));
	    mage.setRolloverEnabled(true);
	    mage.setFocusPainted(false);
	    mage.setBorderPainted(false);
	    mage.setContentAreaFilled(false);;
		
		JButton paladin = new JButton();
		paladin.addActionListener(this);
		HerosButton.add(paladin);
		Heros.add(new Paladin());
		paladin.setIcon(new ImageIcon("Graphics/PaladinLogo.png"));
	    paladin.setRolloverEnabled(true);
	    paladin.setFocusPainted(false);
	    paladin.setBorderPainted(false);
	    paladin.setContentAreaFilled(false);
	    
		JButton priest = new JButton();
		priest.addActionListener(this);
		HerosButton.add(priest);
		Heros.add(new Priest());
		priest.setIcon(new ImageIcon("Graphics/PriestLogo.png"));
	    priest.setRolloverEnabled(true);
	    priest.setFocusPainted(false);
	    priest.setBorderPainted(false);
	    priest.setContentAreaFilled(false);
	    
		JButton warlock = new JButton();
		HerosButton.add(warlock);
		Heros.add(new Warlock());
		warlock.setIcon(new ImageIcon("Graphics/WarlockLogo.png"));
		warlock.addActionListener(this);
	    warlock.setRolloverEnabled(true);
	    warlock.setFocusPainted(false);
	    warlock.setBorderPainted(false);
	    warlock.setContentAreaFilled(false);
	    
		heros.add(hunter);
		heros.add(mage);
		heros.add(paladin);
		heros.add(priest);
		heros.add(warlock);
		
		
		this.setVisible(true);
		repaint();
		revalidate();
		
		
	}
	public Hero getOpponentHero() {
		return OpponentHero;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		String hero = b.getText();
		Hero OpponentHero = null;
		try {
			OpponentHero = createHero(b);
		} catch (IOException e1) {
			String err=e1.getMessage();
			JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
		} catch (CloneNotSupportedException e1) {
			String err=e1.getMessage();
			JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
		}
		this.OpponentHero=OpponentHero;
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

	

}