package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

import javafx.scene.layout.Border;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import model.heroes.Hero;

public class Field extends JFrame implements ActionListener {
	private JPanel oppHand;
	private JPanel oppField;
	private JPanel currField;
	private JPanel currHand;
	private JButton endTurn;
	private JTextArea currentInfo;
	private JTextArea opponentInfo;
	private JButton useHeroPower;
	private Hero FirstHero;
	private Hero SecondHero;
	private JButton cardNumFirst;
	private JButton cardNumSecond;
	private JButton FirstHeroIcon;
	private JButton SecondHeroIcon;
	private JPanel paneltoaddbutton;
	private JPanel FirstHeroIconPanel;
	private JPanel SecondHeroIconPanel;
	private JButton  yourturn1;	
	private JButton  yourturn2;
	JPanel game ;
	JPanel info; 
	
	
	
	
	
	public JPanel getGame() {
		return game;
	}
	public JPanel getInfo() {
		return info;
	}
	public Image getImg() {
		return img;
	}

	private Image img;
	
	public JPanel getFirstHeroIconPanel() {
		return FirstHeroIconPanel;
	}
	public void setFirstHeroIconPanel(JPanel firstHeroIconPanel) {
		FirstHeroIconPanel = firstHeroIconPanel;
	}
	public JPanel getSecondHeroIconPanel() {
		return SecondHeroIconPanel;
	}
	public void setSecondHeroIconPanel(JPanel secondHeroIconPanel) {
		SecondHeroIconPanel = secondHeroIconPanel;
	}
	public JPanel getPaneltoaddbutton() {
		return paneltoaddbutton;
	}
	public void setPaneltoaddbutton(JPanel paneltoaddbutton) {
		this.paneltoaddbutton = paneltoaddbutton;
	}
	public JButton getUseHeroPower() {
		return useHeroPower;
	}
	public void setUseHeroPower(JButton useHeroPower) {
		this.useHeroPower = useHeroPower;
	}
	public void setFirstHero(Hero firstHero) {
		FirstHero = firstHero;
	}
	public void setSecondHero(Hero secondHero) {
		SecondHero = secondHero;
	}

	
	
	public JPanel getOppHand() {
		return oppHand;
	}
	public void setOppHand(JPanel oppHand) {
		this.oppHand = oppHand;
	}
	public JPanel getCurrHand() {
		return currHand;
	}
	public void setCurrHand(JPanel currHand) {
		this.currHand = currHand;
	}
	public JPanel getOppField() {
		return oppField;
	}
	public void setOppField(JPanel oppField) {
		this.oppField = oppField;
	}
	public JPanel getCurrField() {
		return currField;
	}
	public void setCurrField(JPanel currField) {
		this.currField = currField;
	}

	public JButton getEndTurn() {
		return endTurn;
	}
	public void setEndTurn(JButton endTurn) {
		this.endTurn = endTurn;
	}
	public JTextArea getCurrentInfo() {
		return currentInfo;
	}
	public void setCurrentInfo(String currentInfo) {
		this.currentInfo.setText(currentInfo);
	}
	public JTextArea getOpponentInfo() {
		return opponentInfo;
	}
	public void setOpponentInfo(String opponentInfo) {
		this.opponentInfo.setText(opponentInfo);
	}
	
	
	
	public Field(Hero firstHero, Hero secondHero){
		this.FirstHero = firstHero;
		this.SecondHero = secondHero;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height-48);
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("HearthStone");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		info =new JPanel();
		
		game=new JPanel();
		game.setBackground(Color.black);
		
		
		info.setPreferredSize(new Dimension(this.getWidth()/6,this.getHeight()));
		game.setPreferredSize(new Dimension((this.getWidth()/6)*5,(this.getHeight())-48));
		info.setLayout(new BorderLayout());
		this.add(info,BorderLayout.EAST);
		
		
		oppHand = new ImagePanel(new ImageIcon("Graphics/HandOne.png").getImage());
		oppHand.setBackground(Color.black);
		oppHand.setLayout(new GridLayout(0,10));
		oppHand.setPreferredSize(new Dimension(this.getWidth()*5/6,(this.getHeight()/4)-12));
		//oppHand.setBounds(0, 0, this.getWidth()*5/6, this.getHeight()/4);
		
		
		oppField = new ImagePanel(new ImageIcon("Graphics/FieldOne.png").getImage());
		oppField.setBackground(Color.black);
		oppField.setLayout(new GridLayout(0,7));
		oppField.setPreferredSize(new Dimension(this.getWidth()*5/6,(this.getHeight()/4)-12));
		//oppField.setBounds(0,this.getHeight()*1/4 , this.getWidth()*5/6, this.getHeight()/4);
		
		currField = new ImagePanel(new ImageIcon("Graphics/FieldTwo.png").getImage());
		currField.setBackground(Color.black);
		currField.setLayout(new GridLayout(0,7));
		currField.setPreferredSize(new Dimension(this.getWidth()*5/6,(this.getHeight()/4)-12));
		//currField.setBounds(0,this.getHeight()*2/4 , this.getWidth()*5/6, this.getHeight()/4);
		
		currHand = new ImagePanel(new ImageIcon("Graphics/HandTwo.png").getImage());
		currHand.setBackground(Color.black);
		currHand.setLayout(new GridLayout(0,10));
		currHand.setPreferredSize(new Dimension(this.getWidth()*5/6,(this.getHeight()/4)-12));
		//currHand.setBounds(0,this.getHeight()*3/4 , this.getWidth()*5/6, this.getHeight()/4);
		
		cardNumFirst = new JButton();
		cardNumSecond = new JButton();
		
		
		
		
		
		
		cardNumFirst.setBorderPainted(false);
		cardNumFirst.setFocusPainted(false);
		cardNumSecond.setBorderPainted(false);
		cardNumSecond.setFocusPainted(false);
		
		
		yourturn1=new JButton("Your Turn");
		yourturn1.setBorderPainted(false);
		yourturn1.setFocusPainted(false);
		
		endTurn = new JButton("End Turn");
		endTurn.setSize(info.getWidth()/2,info.getHeight()*2/10);
		
		
		yourturn2=new JButton("Your Turn");
	    yourturn2.setBorderPainted(false);
	    yourturn2.setFocusPainted(false);
			

		cardNumFirst.setSize(info.getWidth()/2,info.getHeight()*2/10);
		cardNumSecond.setSize(info.getWidth()/2,info.getHeight()*2/10);
	 
		
		useHeroPower = new JButton("Use Hero Power");
		this.currentInfo = new JTextArea();
		currentInfo.setSize(info.getWidth(),info.getHeight()*4/10);
		currentInfo.setBackground(Color.lightGray);
		currentInfo.setEditable(false);
		currentInfo.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
		
		paneltoaddbutton = new JPanel();
		paneltoaddbutton.add(endTurn);
		paneltoaddbutton.add(useHeroPower);
		paneltoaddbutton.setBackground(Color.BLACK);
		
		this.opponentInfo = new JTextArea();
		opponentInfo.setEditable(false);
		opponentInfo.setSize(info.getWidth(),info.getHeight()*4/10);
		opponentInfo.setBackground(Color.lightGray);
		opponentInfo.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
		FirstHeroIcon = new JButton(FirstHero.getName());

		//FirstHeroIcon.setBorderPainted();
		
		FirstHeroIcon.setFocusPainted(false);
		FirstHeroIcon.setContentAreaFilled(false);
		
		if(FirstHero.getName().equals("Rexxar")){
			FirstHeroIcon.setIcon(new ImageIcon("Graphics/Hunter.png"));
		}
		else if(FirstHero.getName().equals("Jaina Proudmoore")){
			FirstHeroIcon.setIcon(new ImageIcon("Graphics/Mage.png"));
		}
		else if(FirstHero.getName().equals("Uther Lightbringer")){
			FirstHeroIcon.setIcon(new ImageIcon("Graphics/Paladin.png"));
		}
		else if(FirstHero.getName().equals("Anduin Wrynn")){
			FirstHeroIcon.setIcon(new ImageIcon("Graphics/Priest.png"));
		}
		else if(FirstHero.getName().equals("Gul'dan")){
			FirstHeroIcon.setIcon(new ImageIcon("Graphics/Warlock.png"));
		}
		SecondHeroIcon = new JButton(SecondHero.getName());

		//SecondHeroIcon.setBorderPainted(false);
		SecondHeroIcon.setFocusPainted(false);
		SecondHeroIcon.setContentAreaFilled(false);
		if(SecondHero.getName().equals("Rexxar")){
			SecondHeroIcon.setIcon(new ImageIcon("Graphics/Hunter.png"));
		}
		else if(SecondHero.getName().equals("Jaina Proudmoore")){
			SecondHeroIcon.setIcon(new ImageIcon("Graphics/Mage.png"));
		}
		else if(SecondHero.getName().equals("Uther Lightbringer")){
			SecondHeroIcon.setIcon(new ImageIcon("Graphics/Paladin.png"));
		}
		else if(SecondHero.getName().equals("Anduin Wrynn")){
			SecondHeroIcon.setIcon(new ImageIcon("Graphics/Priest.png"));
		}
		else if(SecondHero.getName().equals("Gul'dan")){
			SecondHeroIcon.setIcon(new ImageIcon("Graphics/Warlock.png"));
		}
		
		FirstHeroIconPanel = new JPanel();
		FirstHeroIconPanel.add(FirstHeroIcon);
		
		SecondHeroIconPanel = new JPanel();
		SecondHeroIconPanel.add(SecondHeroIcon);
		
		JPanel FirstHeroNum= new JPanel();
		FirstHeroNum.add(cardNumFirst);
		
		JPanel SecondHeroNum= new JPanel();
		SecondHeroNum.add(cardNumSecond);
		
		
		JPanel FirstHeroturn= new JPanel();
		FirstHeroturn.add(yourturn1);
		
		
		JPanel SecondHeroturn= new JPanel();
		SecondHeroturn.add(yourturn2);
	
		
		info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
		info.add(SecondHeroIconPanel);
		info.add(SecondHeroNum);
		info.add(SecondHeroturn);
		info.add(opponentInfo);
		info.add(paneltoaddbutton);
		info.add(FirstHeroIconPanel);
		info.add(FirstHeroNum);
		info.add(FirstHeroturn);
		info.add(currentInfo);
		

		currField.setVisible(true);
		oppField.setVisible(true);
		game.add(oppHand);
		game.add(oppField);
		game.add(currField);
		game.add(currHand);
		
		this.add(game);
		
		
		this.setVisible(true);	 
		repaint();
		revalidate();
	}
	public JButton getYourturn1() {
		return yourturn1;
	}
	public JButton getYourturn2() {
		return yourturn2;
	}
	public JButton getCardNumFirst() {
		return cardNumFirst;
	}
	public void setCardNumFirst(JButton cardNumFirst) {
		this.cardNumFirst = cardNumFirst;
	}
	public JButton getCardNumSecond() {
		return cardNumSecond;
	}
	public void setCardNumSecond(JButton cardNumSecond) {
		this.cardNumSecond = cardNumSecond;
	}
	public void setCurrentInfo(JTextArea currentInfo) {
		this.currentInfo = currentInfo;
	}
	public void setOpponentInfo(JTextArea opponentInfo) {
		this.opponentInfo = opponentInfo;
	}
	
	public JButton getFirstHeroIcon() {
		return FirstHeroIcon;
	}
	public void setFirstHeroIcon(JButton firstHeroIcon) {
		FirstHeroIcon = firstHeroIcon;
	}
	public JButton getSecondHeroIcon() {
		return SecondHeroIcon;
	}
	public void setSecondHeroIcon(JButton secondHeroIcon) {
		SecondHeroIcon = secondHeroIcon;
	}
	public Hero getFirstHero() {
		return FirstHero;
	}
	public Hero getSecondHero() {
		return SecondHero;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
