package Controller;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Spell;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;
import view.CurrentHero;
import view.Field;
import view.OpponentHero;
import view.Startpage;
import engine.Game;
import engine.GameListener;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;


@SuppressWarnings("serial")
public class Controller extends JFrame implements ActionListener, GameListener {
	private Game model;
	private Field view;
	private ArrayList<JButton> Deck;
	
	private Clip clip;
	
	private ArrayList<JButton> FirstHeroMinionsButtonsinField;
	private ArrayList<Minion> FirstHeroMinionsArrayinField;

	private ArrayList<JButton> SecondHeroMinionsButtonsinField;
	private ArrayList<Minion> SecondHeroMinionsArrayinField;
	
	boolean flag;
	
	private ArrayList<JButton> FirstHeroMinionsButtons;
	private ArrayList<Minion> FirstHeroMinionsArray;
	
	private ArrayList<JButton> FirstHeroSpellsButtons;
	private ArrayList<Spell> FirstHeroSpellsArray;
	
	private ArrayList<JButton> SecondHeroMinionsButtons;
	private ArrayList<Minion> SecondHeroMinionsArray;
	
	private ArrayList<JButton> SecondHeroSpellsButtons;
	private ArrayList<Spell> SecondHeroSpellsArray;
	
	private Minion m;
	private Spell spell;
	private Hero hero;
    private Minion mtarget;
	
	
	public Controller() throws FullHandException, CloneNotSupportedException, IOException{
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Graphics/welcome.wav").getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);  
		} catch (UnsupportedAudioFileException e) {
			String err=e.getMessage();
			JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			String err=e.getMessage();
			JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
		} catch (LineUnavailableException e) {
			String err=e.getMessage();
			JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
		}
		Startpage page = new Startpage();
		while(page.isVisible()){
			
		}
		CurrentHero page1 = new CurrentHero();
		while(page1.isVisible()){
		}
		OpponentHero page2 = new OpponentHero();
		
		while(page2.isVisible()){
		}
		flag=false;
		Hero hero1 = page1.getCurrentHero();
		Hero hero2 = page2.getOpponentHero();
		model = new Game(hero1,hero2);
		try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Graphics/Gameplay.wav").getAbsoluteFile());
				clip.close();
				clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);  
				} catch (UnsupportedAudioFileException e) {
					String err=e.getMessage();
					JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					String err=e.getMessage();
					JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
				} catch (LineUnavailableException e) {
					String err=e.getMessage();
					JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
				}
		
		view = new Field(page1.getCurrentHero(),page2.getOpponentHero());
		view.getEndTurn().addActionListener(this);		
		FirstHeroMinionsButtons = new ArrayList<JButton>();
		FirstHeroMinionsArray = new ArrayList<Minion>();
		FirstHeroSpellsButtons = new ArrayList<JButton>();
		FirstHeroSpellsArray = new ArrayList<Spell>();

		SecondHeroMinionsButtons = new ArrayList<JButton>();
		SecondHeroMinionsArray = new ArrayList<Minion>();
		SecondHeroSpellsButtons = new ArrayList<JButton>();
		SecondHeroSpellsArray = new ArrayList<Spell>();
		
		 FirstHeroMinionsButtonsinField=new ArrayList<JButton>();
		 FirstHeroMinionsArrayinField=new ArrayList<Minion>();

		 SecondHeroMinionsButtonsinField=new ArrayList<JButton>();;
		 SecondHeroMinionsArrayinField=new ArrayList<Minion>();
		 m=null;
		 spell=null;
		 view.getFirstHeroIcon().addActionListener(this);
		 view.getSecondHeroIcon().addActionListener(this);
		 view.getUseHeroPower().addActionListener(this);
			model.setListener(this);
		
	
		for(int i = 0 ; i < model.getCurrentHero().getHand().size() ; i++){
			JButton b;
			if(model.getCurrentHero().getHand().get(i) instanceof Minion){
				b=new JButton();
				String s=("<html>Name: " + model.getCurrentHero().getHand().get(i).getName() + 
				        "<br />Mana Cost: " + model.getCurrentHero().getHand().get(i).getManaCost() +
				        "<br />Rarity: " + model.getCurrentHero().getHand().get(i).getRarity() +
				        "<br />Attack: " +((Minion) model.getCurrentHero().getHand().get(i)).getAttack() + 
				        "<br />CurrentHP: " +((Minion) model.getCurrentHero().getHand().get(i)).getCurrentHP()+ 
				        "<br />Taunt: " + ((Minion) model.getCurrentHero().getHand().get(i)).isTaunt() +
				        "<br />Divine: " +((Minion) model.getCurrentHero().getHand().get(i)).isDivine() + 
				        "<br />Charge: " + !((Minion) model.getCurrentHero().getHand().get(i)).isSleeping())+"</html>";
				b.setText(s);
				
				b.setPreferredSize(new Dimension(0,45));
				if( model.getCurrentHero().equals(model.getFirstHero())){
					FirstHeroMinionsArray.add((Minion) model.getCurrentHero().getHand().get(i));
				    FirstHeroMinionsButtons.add(b);}
				else{
					SecondHeroMinionsArray.add((Minion) model.getCurrentHero().getHand().get(i));
				    SecondHeroMinionsButtons.add(b);}
			b.addActionListener(this);
			
			}
				else{
				b=new JButton();
				String s=("<html>Name: "+model.getCurrentHero().getHand().get(i).getName() + 
						"<br />Mana Cost: "+ model.getCurrentHero().getHand().get(i).getManaCost() +
						"<br />Rarity: " + model.getCurrentHero().getHand().get(i).getRarity())+"</html>";
				b.setText(s);
				
				if(model.getCurrentHero().equals(model.getFirstHero())){
					FirstHeroSpellsArray.add((Spell) model.getCurrentHero().getHand().get(i));
				    FirstHeroSpellsButtons.add(b);}
				else{
					SecondHeroSpellsArray.add((Spell) model.getCurrentHero().getHand().get(i));
				    SecondHeroSpellsButtons.add(b);
				}
					   
				b.addActionListener(this);
				b.setPreferredSize(new Dimension(0,0));
				}
			
			if(model.getCurrentHero().equals(model.getFirstHero()))
			   view.getCurrHand().add(b);
			else
				
			   view.getOppHand().add(b);
		}
		
		for(int i = 0 ; i < model.getOpponent().getHand().size() ; i++){
			JButton b;
			
			if(model.getOpponent().getHand().get(i) instanceof Minion){
				b=new JButton();
				String s="";
				b.setText(s);
				b.setIcon(new ImageIcon("Graphics/CardBack.png"));
				b.setRolloverEnabled(true);
			    b.setBorderPainted(false);
			    b.setContentAreaFilled(false);
				if(model.getOpponent().equals(model.getFirstHero())){
					FirstHeroMinionsArray.add((Minion) model.getOpponent().getHand().get(i));
				    FirstHeroMinionsButtons.add(b);}
				else{
					SecondHeroMinionsArray.add((Minion) model.getOpponent().getHand().get(i));
				    SecondHeroMinionsButtons.add(b);
				    }
				
				b.addActionListener(this);
				
			}
				else{
				b = new JButton();
				b.setIcon(new ImageIcon("Graphics/CardBack.png"));
				b.setRolloverEnabled(true);
			    b.setBorderPainted(false);
			    b.setContentAreaFilled(false);
				if(model.getOpponent().equals(model.getFirstHero())){
					FirstHeroSpellsArray.add((Spell) model.getOpponent().getHand().get(i));
				    FirstHeroSpellsButtons.add(b);}
				else{
					SecondHeroSpellsArray.add((Spell) model.getOpponent().getHand().get(i));
				    SecondHeroSpellsButtons.add(b);
				}
					   
				b.addActionListener(this);
				b.setPreferredSize(new Dimension(0,0));
				}
			if(model.getOpponent().equals(model.getFirstHero()))
				view.getCurrHand().add(b);
			else
				view.getOppHand().add(b);
		}
		if(model.getFirstHero().equals(model.getCurrentHero())){
			view.getYourturn1().setBackground(Color.green);
			view.getYourturn2().setBackground(Color.RED);
			view.getYourturn1().setText("Your Turn");
			view.getYourturn2().setText("Opponent's Turn");
		}
		else if(model.getSecondHero().equals(model.getCurrentHero())){
			view.getYourturn2().setBackground(Color.green);
			view.getYourturn1().setBackground(Color.RED);
			view.getYourturn2().setText("Your Turn");
			view.getYourturn1().setText("Opponent's Turn");
		}
		view.getCurrentInfo().setText(model.getFirstHero().getHeroInfo());
		view.getOpponentInfo().setText(model.getSecondHero().getHeroInfo());
		view.getCardNumFirst().setText("Cards in Deck: "+ model.getFirstHero().getDeck().size());
		view.getCardNumSecond().setText("Cards in Deck: "+model.getSecondHero().getDeck().size());
		 view.repaint();
		 view.revalidate();
		
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();


		// I am checking at the beginning if i am pressing the End Turn button
		if(b.getText().equals(view.getEndTurn().getText())){
			System.out.println("endturn");
			try {
				model.endTurn();
				view.getCurrentInfo().setText(model.getFirstHero().getHeroInfo());
				view.getOpponentInfo().setText(model.getSecondHero().getHeroInfo());
			} catch (FullHandException e1) {
				String err=e1.getMessage();
				JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
				
			} catch (CloneNotSupportedException e1) {
				String err=e1.getMessage();
				JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
			}
			
			m=null;
			mtarget=null;
			spell=null;
			hero=null;
			flag = false;
			updateview();
			updateview2();
			updateFieldview();
			updateFieldview2();
			view.revalidate();
			view.repaint();
			view.getCurrentInfo().setText(model.getFirstHero().getHeroInfo());
			view.getOpponentInfo().setText(model.getSecondHero().getHeroInfo());
			view.getCardNumFirst().setText("Cards in Deck: "+ model.getFirstHero().getDeck().size());
			view.getCardNumSecond().setText("Cards in Deck: "+model.getSecondHero().getDeck().size());
			if(model.getFirstHero().equals(model.getCurrentHero())){
				view.getYourturn1().setBackground(Color.green);
				view.getYourturn2().setBackground(Color.RED);
				view.getYourturn1().setText("Your Turn");
				view.getYourturn2().setText("Opponent's Turn");
			}
			else if(model.getSecondHero().equals(model.getCurrentHero())){
				view.getYourturn2().setBackground(Color.green);
				view.getYourturn1().setBackground(Color.RED);
				view.getYourturn2().setText("Your Turn");
				view.getYourturn1().setText("Opponent's Turn");
			}
			return;
			
		}
		
		
		//Checking whether the clicked button is first hero
		if(b.equals(view.getFirstHeroIcon())){
			hero = model.getFirstHero();
		}
		//checking if the clicked button is the second hero
		else if (b.equals(view.getSecondHeroIcon())){
			hero = model.getSecondHero();
		}
		
		// checking if the clicked button is a spell in the first hero hand or not
		
		else if (FirstHeroSpellsButtons.contains(b)&&model.getCurrentHero().equals(model.getFirstHero())){
			int x = FirstHeroSpellsButtons.indexOf(b);
			spell = FirstHeroSpellsArray.get(x);
		}
		else if (FirstHeroSpellsButtons.contains(b)&&m==null){
			  JOptionPane.showMessageDialog(null,"You can not do any action in your opponent's turn","Error", JOptionPane.ERROR_MESSAGE);
			
			return;
			
		}
		// checking if the clicked button is a spell in the second hero hand
		else if (SecondHeroSpellsButtons.contains(b)&&model.getCurrentHero().equals(model.getSecondHero())){
			int x = SecondHeroSpellsButtons.indexOf(b);
			spell = SecondHeroSpellsArray.get(x);
		}
		else if (SecondHeroSpellsButtons.contains(b)&&m==null){
			
			JOptionPane.showMessageDialog(null,"You can not do any action in your opponent's turn","Error", JOptionPane.ERROR_MESSAGE);
			return;
			
		}
		else if(FirstHeroMinionsButtonsinField.contains(b)&&m==null){
			int x = FirstHeroMinionsButtonsinField.indexOf(b);
			m = FirstHeroMinionsArrayinField.get(x);
		}
		else if(SecondHeroMinionsButtonsinField.contains(b)&&m==null){
			int x = SecondHeroMinionsButtonsinField.indexOf(b);
			m = SecondHeroMinionsArrayinField.get(x);
		}
		else if(FirstHeroMinionsButtonsinField.contains(b)){
			int x = FirstHeroMinionsButtonsinField.indexOf(b);
			mtarget = FirstHeroMinionsArrayinField.get(x);
		}
		else if(SecondHeroMinionsButtonsinField.contains(b)){
			int x = SecondHeroMinionsButtonsinField.indexOf(b);
			mtarget = SecondHeroMinionsArrayinField.get(x);
		}
		else if(FirstHeroMinionsButtons.contains(b)&& m!=null){
			int x = FirstHeroMinionsButtons.indexOf(b);
			mtarget = FirstHeroMinionsArray.get(x);
		}
		else if (FirstHeroSpellsButtons.contains(b)&&model.getCurrentHero().equals(model.getSecondHero())){
			JOptionPane.showMessageDialog(null,"You can not attack a minion that your opponent has not summoned yet","Error", JOptionPane.ERROR_MESSAGE);
            m=null; 
			return;	
		}
		else if (SecondHeroSpellsButtons.contains(b)&&model.getCurrentHero().equals(model.getFirstHero())){
			JOptionPane.showMessageDialog(null,"You can not attack a minion that your opponent has not summoned yet","Error", JOptionPane.ERROR_MESSAGE);
             m=null;
			return;	
		}
		else if (FirstHeroSpellsButtons.contains(b)&&model.getCurrentHero().equals(model.getFirstHero())){
			JOptionPane.showMessageDialog(null,"You can not attack a friendly minion","Error", JOptionPane.ERROR_MESSAGE);
            m=null; 
			return;	
		}
		else if (SecondHeroSpellsButtons.contains(b)&&model.getCurrentHero().equals(model.getSecondHero())){
			JOptionPane.showMessageDialog(null,"You can not attack a friendly minion","Error", JOptionPane.ERROR_MESSAGE);
            m=null; 
			return;	
		}
		
		
		
		else if(SecondHeroMinionsButtons.contains(b)&& m!=null){
			int x = SecondHeroMinionsButtons.indexOf(b);
			mtarget = SecondHeroMinionsArray.get(x);
		}
		
		else if(FirstHeroMinionsButtons.contains(b)&& spell!=null){
			int x = FirstHeroMinionsButtons.indexOf(b);
			m = FirstHeroMinionsArray.get(x);
		}
		else if(SecondHeroMinionsButtons.contains(b)&& spell!=null){
			int x = SecondHeroMinionsButtons.indexOf(b);
			m = SecondHeroMinionsArray.get(x);
		}
		
		if(b.getText().equals("Use Hero Power") && hero == null && m ==null){
				if(model.getCurrentHero() instanceof Hunter){
					try {
						if(model.getCurrentHero().equals(model.getFirstHero())){
							model.getFirstHero().useHeroPower();
						}
						else{
							model.getSecondHero().useHeroPower();
						}
						updateview();
						updateview2();
						updateFieldview();
						updateFieldview2();
						view.getCurrentInfo().setText(model.getFirstHero().getHeroInfo());
						view.getOpponentInfo().setText(model.getSecondHero().getHeroInfo());
						view.getCardNumFirst().setText("Cards in Deck: "+ model.getFirstHero().getDeck().size());
						view.getCardNumSecond().setText("Cards in Deck: "+model.getSecondHero().getDeck().size());
							
					} catch (NotEnoughManaException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (HeroPowerAlreadyUsedException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (NotYourTurnException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullHandException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullFieldException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (CloneNotSupportedException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else if (model.getCurrentHero() instanceof Warlock){
					try {
						if(model.getCurrentHero().equals(model.getFirstHero())){
							model.getFirstHero().useHeroPower();
						}
						else{
							model.getSecondHero().useHeroPower();
						}
						updateview();
						updateview2();
						updateFieldview();
						updateFieldview2();
						view.getCurrentInfo().setText(model.getFirstHero().getHeroInfo());
						view.getOpponentInfo().setText(model.getSecondHero().getHeroInfo());
						view.getCardNumFirst().setText("Cards in Deck: "+ model.getFirstHero().getDeck().size());
						view.getCardNumSecond().setText("Cards in Deck: "+model.getSecondHero().getDeck().size());
					} catch (NotEnoughManaException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (HeroPowerAlreadyUsedException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (NotYourTurnException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullHandException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullFieldException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (CloneNotSupportedException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else if (model.getCurrentHero() instanceof Paladin){
					try {
						if(model.getCurrentHero().equals(model.getFirstHero())){
							model.getFirstHero().useHeroPower();
						}
						else{
							model.getSecondHero().useHeroPower();
						}
						updateview();
						updateview2();
						updateFieldview();
						updateFieldview2();
						view.getCurrentInfo().setText(model.getFirstHero().getHeroInfo());
						view.getOpponentInfo().setText(model.getSecondHero().getHeroInfo());
						view.getCardNumFirst().setText("Cards in Deck: "+ model.getFirstHero().getDeck().size());
						view.getCardNumSecond().setText("Cards in Deck: "+model.getSecondHero().getDeck().size());
					} catch (NotEnoughManaException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (HeroPowerAlreadyUsedException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (NotYourTurnException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullHandException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullFieldException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (CloneNotSupportedException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					}				
			}
			else if(model.getCurrentHero() instanceof Mage){
				JOptionPane.showMessageDialog(null,"Please Choose a Minion or a Hero","HearthStone", JOptionPane.INFORMATION_MESSAGE);
				flag= true;
			}
			else if (model.getCurrentHero() instanceof Priest){
				JOptionPane.showMessageDialog(null,"Please Choose a Minion or a Hero","HearthStone", JOptionPane.INFORMATION_MESSAGE);
				flag = true;
			}
		}
		else if(flag == true && hero == null && m != null){
			if(model.getCurrentHero() instanceof Mage){
					try {
						if(model.getCurrentHero().equals(model.getFirstHero())){
							((Mage) model.getFirstHero()).useHeroPower(m);
						}
						else{
							((Mage) model.getSecondHero()).useHeroPower(m);
						}
						updateview();
						updateview2();
						updateFieldview();
						updateFieldview2();
						view.getCurrentInfo().setText(model.getFirstHero().getHeroInfo());
						view.getOpponentInfo().setText(model.getSecondHero().getHeroInfo());
						view.getCardNumFirst().setText("Cards in Deck: "+ model.getFirstHero().getDeck().size());
						view.getCardNumSecond().setText("Cards in Deck: "+model.getSecondHero().getDeck().size());
					} catch (NotEnoughManaException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (HeroPowerAlreadyUsedException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (NotYourTurnException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullHandException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullFieldException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (CloneNotSupportedException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
						}
				}
				else if (model.getCurrentHero() instanceof Priest){
					try {
						if(model.getCurrentHero().equals(model.getFirstHero())){
							((Priest) model.getFirstHero()).useHeroPower(m);
						}
						else{
							((Priest) model.getSecondHero()).useHeroPower(m);
						}
						updateview();
						updateview2();
						updateFieldview();
						updateFieldview2();
						view.getCurrentInfo().setText(model.getFirstHero().getHeroInfo());
						view.getOpponentInfo().setText(model.getSecondHero().getHeroInfo());
						view.getCardNumFirst().setText("Cards in Deck: "+ model.getFirstHero().getDeck().size());
						view.getCardNumSecond().setText("Cards in Deck: "+model.getSecondHero().getDeck().size());
					} catch (NotEnoughManaException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (HeroPowerAlreadyUsedException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (NotYourTurnException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullHandException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullFieldException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (CloneNotSupportedException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
						}
					}
			flag = false;
			m=null;
		}
		else if(flag == true && hero != null && m == null){
			if(model.getCurrentHero() instanceof Mage){
					try {
						if(model.getCurrentHero().equals(model.getFirstHero())){
							((Mage) model.getFirstHero()).useHeroPower(hero);
						}
						else{
							((Mage) model.getSecondHero()).useHeroPower(hero);
						}
						updateview();
						updateview2();
						updateFieldview();
						updateFieldview2();
						view.getCurrentInfo().setText(model.getFirstHero().getHeroInfo());
						view.getOpponentInfo().setText(model.getSecondHero().getHeroInfo());
						view.getCardNumFirst().setText("Cards in Deck: "+ model.getFirstHero().getDeck().size());
						view.getCardNumSecond().setText("Cards in Deck: "+model.getSecondHero().getDeck().size());
					} catch (NotEnoughManaException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (HeroPowerAlreadyUsedException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (NotYourTurnException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullHandException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullFieldException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (CloneNotSupportedException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				else if (model.getCurrentHero() instanceof Priest){
					try {
						if(model.getCurrentHero().equals(model.getFirstHero())){
							((Priest) model.getFirstHero()).useHeroPower(hero);
						}
						else{
							((Priest) model.getSecondHero()).useHeroPower(hero);
						}
						updateview();
						updateview2();
						updateFieldview();
						updateFieldview2();
						view.getCurrentInfo().setText(model.getFirstHero().getHeroInfo());
						view.getOpponentInfo().setText(model.getSecondHero().getHeroInfo());
						view.getCardNumFirst().setText("Cards in Deck: "+ model.getFirstHero().getDeck().size());
						view.getCardNumSecond().setText("Cards in Deck: "+model.getSecondHero().getDeck().size());
					} catch (NotEnoughManaException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (HeroPowerAlreadyUsedException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (NotYourTurnException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullHandException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (FullFieldException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (CloneNotSupportedException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
						}
					}
			flag = false;
			hero=null;
		}
				
					
			
			
		
		
		//////////////////////////////
		/////////////////////////////
		//Check whether I have already saved Minion or not
				if((m != null &&mtarget!=null&&spell==null)||(m!=null &&hero!=null&&spell==null)){				
						try {
							System.out.println("I entered the FirstHeroMinionsButtonsinField");
							
							if(FirstHeroMinionsArrayinField.contains(m)){
								if(hero==null)
									model.getFirstHero().attackWithMinion(m, mtarget);
								else
									model.getFirstHero().attackWithMinion(m, hero);
								
								
							}
							else if(SecondHeroMinionsArrayinField.contains(m)){
								if(hero==null)
									model.getSecondHero().attackWithMinion(m, mtarget);
								else
									model.getSecondHero().attackWithMinion(m, hero);
							}
							//updateview3();
							
						} catch (CannotAttackException e1) {
							String err=e1.getMessage();
							JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
						} catch (NotYourTurnException e1) {
							String err=e1.getMessage();
							JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
							
						} catch (TauntBypassException e1) {
							String err=e1.getMessage();
							JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
						} catch (InvalidTargetException e1) {
							String err=e1.getMessage();
							JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
						} catch (NotSummonedException e1) {
							String err=e1.getMessage();
							JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
						}
						m = null;
						mtarget=null;
						hero=null;
						updateFieldview();
						updateFieldview2();
						view.getCurrentInfo().setText(model.getFirstHero().getHeroInfo());
						view.getOpponentInfo().setText(model.getSecondHero().getHeroInfo());
						view.revalidate();
						view.repaint();
					}				
				// I am sure that i do not have a saved Minion ----> this is the first minion to choose
				else if (m == null && spell==null){
					// Check whether the minion that i pressed is in the FirstHero Hand
					if(FirstHeroMinionsButtons.contains(b)){
						int x = FirstHeroMinionsButtons.indexOf(b);
						Minion m2 = FirstHeroMinionsArray.get(x);
						try {
							System.out.println("I entered the FirstHeroMinionsButton");
							model.getFirstHero().playMinion(m2);
							view.getCurrField().add(b);
							FirstHeroMinionsArrayinField.add(m2);
							FirstHeroMinionsButtonsinField.add(b);
							updateview();
							view.getCurrentInfo().setText(model.getFirstHero().getHeroInfo());
							view.getOpponentInfo().setText(model.getSecondHero().getHeroInfo());
							view.invalidate();
							view.validate();
							view.repaint();
							}
						catch (NotYourTurnException e1) {
							String err=e1.getMessage();
							JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
							} 
						catch (NotEnoughManaException e1) {
							String err=e1.getMessage();
							JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
							} 
						catch (FullFieldException e1) {
							String err=e1.getMessage();
							JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
							} 
						}
					//I successfully checked whether the pressed Minion is in the First Hero Hand or not and if so, I made the Play minion Action
					//Lets now check whether the pressed Minion is in the Second Hero Hand or not :) ...
					else if(SecondHeroMinionsButtons.contains(b)){
						int x = SecondHeroMinionsButtons.indexOf(b);
						Minion m2 = SecondHeroMinionsArray.get(x);
						try {
							System.out.println("I entered the SecondHeroMinionsButtons");
							model.getSecondHero().playMinion(m2);
							view.getOppField().add(b);
							SecondHeroMinionsButtonsinField.add(b);
							SecondHeroMinionsArrayinField.add(m2);
							
							updateview2();
							view.getCurrentInfo().setText(model.getFirstHero().getHeroInfo());
							view.getOpponentInfo().setText(model.getSecondHero().getHeroInfo());
							view.revalidate();
							view.repaint();
							}
						catch (NotYourTurnException e1) {
							String err=e1.getMessage();
							JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
							} 
						catch (NotEnoughManaException e1) {
							String err=e1.getMessage();
							JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
							} 
						catch (FullFieldException e1) {
							String err=e1.getMessage();
							JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					
					
				}
				else if (m==null&&spell!=null){
					
					if (spell instanceof FieldSpell){
						 try {
							 if(FirstHeroSpellsArray.contains(spell))
								 model.getFirstHero().castSpell((FieldSpell) spell);
							 else if(SecondHeroSpellsArray.contains(spell))
								 model.getSecondHero().castSpell((FieldSpell) spell);
							    updateview();
								updateview2();
								updateFieldview();
								updateFieldview2();
								view.getCurrentInfo().setText(model.getFirstHero().getHeroInfo());
								view.getOpponentInfo().setText(model.getSecondHero().getHeroInfo());
								view.revalidate();
								view.repaint();
							 
						 	} catch (NotYourTurnException e1) {
								 String err=e1.getMessage();
								 JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
							 } catch (NotEnoughManaException e1) {
								 String err=e1.getMessage();
								 JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
								 }
						 spell=null;
						 }
				else if (spell instanceof AOESpell){
					try {
						if(FirstHeroSpellsArray.contains(spell))
							model.getFirstHero().castSpell((AOESpell) spell,model.getOpponent().getField());
						else if(SecondHeroSpellsArray.contains(spell))
							model.getSecondHero().castSpell((AOESpell) spell,model.getOpponent().getField());						
						updateview();
						updateview2();
						updateFieldview();
						updateFieldview2();
						view.getCurrentInfo().setText(model.getFirstHero().getHeroInfo());
						view.getOpponentInfo().setText(model.getSecondHero().getHeroInfo());
						view.revalidate();
						view.repaint();
					} catch (NotYourTurnException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,
							      "Error", JOptionPane.ERROR_MESSAGE);
					} catch (NotEnoughManaException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					}
					spell=null;
				}
				else if (this.spell instanceof MinionTargetSpell||this.spell instanceof LeechingSpell)
					JOptionPane.showMessageDialog(null,"Choose A Minion Target","Error", JOptionPane.ERROR_MESSAGE);
					
				else if((this.spell instanceof HeroTargetSpell))	
					JOptionPane.showMessageDialog(null,"Choose A Hero Target","Error", JOptionPane.ERROR_MESSAGE);
					
				}
				else if(m!=null&&spell!=null){
					if (this.spell instanceof MinionTargetSpell){							
						try {
						System.out.print("Minion target spell spell first hero");
						if(FirstHeroSpellsArray.contains(spell))
							model.getFirstHero().castSpell((MinionTargetSpell)this.spell, m);
						else if(SecondHeroSpellsArray.contains(spell))
							model.getSecondHero().castSpell((MinionTargetSpell)this.spell, m);
						updateview();
						updateview2();
						updateFieldview();
						updateFieldview2();
						view.getCurrentInfo().setText(model.getFirstHero().getHeroInfo());
						view.getOpponentInfo().setText(model.getSecondHero().getHeroInfo());
						view.revalidate();
						view.repaint();
						
						   } catch (NotYourTurnException e1) {
							String err=e1.getMessage();
							JOptionPane.showMessageDialog(null,err, "Error", JOptionPane.ERROR_MESSAGE);
						} catch (NotEnoughManaException e1) {
							String err=e1.getMessage();
							JOptionPane.showMessageDialog(null,err, "Error", JOptionPane.ERROR_MESSAGE);
						} catch (InvalidTargetException e1) {
							String err=e1.getMessage();
							JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
						}
					
						spell=null;
						m=null;
						}
				if (this.spell instanceof LeechingSpell){							
					try {	
						if(FirstHeroSpellsArray.contains(spell))
							model.getFirstHero().castSpell((LeechingSpell) this.spell, m);
						else if(SecondHeroSpellsArray.contains(spell))
							model.getSecondHero().castSpell((LeechingSpell) this.spell, m);
						updateview();
						updateview2();
						updateFieldview();
						updateFieldview2();
						view.getCurrentInfo().setText(model.getFirstHero().getHeroInfo());
						view.getOpponentInfo().setText(model.getSecondHero().getHeroInfo());
						view.revalidate();
						view.repaint();
					
					} catch (NotYourTurnException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} catch (NotEnoughManaException e1) {
						String err=e1.getMessage();
						JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
					} 
					spell=null;
					m=null;
				}
				}
				else if(spell!=null&&hero!=null)
					if(this.spell instanceof HeroTargetSpell){
		        		
		        			try {
		        				if(b.getText().equals(model.getFirstHero().getName()))
		        					model.getSecondHero().castSpell((HeroTargetSpell)this.spell,model.getFirstHero());
		        				else if(SecondHeroSpellsArray.contains(spell))
		        					model.getFirstHero().castSpell((HeroTargetSpell)this.spell,model.getSecondHero());
		        				;
		        				updateview();
		        				updateview2();
		        				updateFieldview();
		        				updateFieldview2();
		        				view.getCurrentInfo().setText(model.getFirstHero().getHeroInfo());
		        				view.getOpponentInfo().setText(model.getSecondHero().getHeroInfo());
		        				view.revalidate();
		        				view.repaint();
							} catch (NotYourTurnException e1) {
								String err=e1.getMessage();
  								JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
							} catch (NotEnoughManaException e1) {
								String err=e1.getMessage();
								JOptionPane.showMessageDialog(null,err,"Error", JOptionPane.ERROR_MESSAGE);
							}
		        			spell=null;
							m=null;
		        		}
//			if(model.getFirstHero().getCurrentHP() == 0 || model.getSecondHero().getCurrentHP() == 0){
//				onGameOver();
//			}
			view.getCurrentInfo().setText(model.getFirstHero().getHeroInfo());
			view.getOpponentInfo().setText(model.getSecondHero().getHeroInfo());
			
				
	}
		
	
	public  void updateFieldview(){
        view.getCurrField().removeAll();
		FirstHeroMinionsArrayinField.clear();
	    FirstHeroMinionsButtonsinField.clear();
		
		for(int i = 0 ; i < model.getFirstHero().getField().size() ; i++){
			JButton b = null;
			if(model.getFirstHero().getField().get(i) instanceof Minion){
				b=new JButton();
				String s=("<html>Name: " + model.getFirstHero().getField().get(i).getName() + 
				        "<br />Mana Cost: " + model.getFirstHero().getField().get(i).getManaCost() +
				        "<br />Rarity: " + model.getFirstHero().getField().get(i).getRarity() +
				        "<br />Attack: " +((Minion) model.getFirstHero().getField().get(i)).getAttack() + 
				        "<br />CurrentHP: " +((Minion) model.getFirstHero().getField().get(i)).getCurrentHP()+ 
				        "<br />Taunt: " + ((Minion) model.getFirstHero().getField().get(i)).isTaunt() +
				        "<br />Divine: " +((Minion) model.getFirstHero().getField().get(i)).isDivine() +
				        "<br />Charge: " + !((Minion) model.getFirstHero().getField().get(i)).isSleeping())+"</html>";
				b.setText(s);	
				FirstHeroMinionsArrayinField.add((Minion)model.getFirstHero().getField().get(i));
				FirstHeroMinionsButtonsinField.add(b);}
			
			
							
				b.addActionListener(this);
				view.getCurrField().add(b);}
			
				}	
	
	public  void updateFieldview2(){
        view.getOppField().removeAll();
		SecondHeroMinionsArrayinField.clear();
	    SecondHeroMinionsButtonsinField.clear();
		
		for(int i = 0 ; i < model.getSecondHero().getField().size() ; i++){
			JButton b = null;
			if(model.getSecondHero().getField().get(i) instanceof Minion){
				b=new JButton();
				String s=("<html>Name: " + model.getSecondHero().getField().get(i).getName() + 
				        "<br />Mana Cost: " + model.getSecondHero().getField().get(i).getManaCost() +
				        "<br />Rarity: " + model.getSecondHero().getField().get(i).getRarity() +
				        "<br />Attack: " +((Minion) model.getSecondHero().getField().get(i)).getAttack() + 
				        "<br />CurrentHP: " +((Minion) model.getSecondHero().getField().get(i)).getCurrentHP()+ 
				        "<br />Taunt: " + ((Minion) model.getSecondHero().getField().get(i)).isTaunt() +
				        "<br />Divine: " +((Minion) model.getSecondHero().getField().get(i)).isDivine() +
				        "<br />Charge: " + !((Minion) model.getSecondHero().getField().get(i)).isSleeping())+"</html>";
				b.setText(s);	
						
					SecondHeroMinionsArrayinField.add((Minion)model.getSecondHero().getField().get(i));
				    SecondHeroMinionsButtonsinField.add(b);}
			
			
							
				b.addActionListener(this);
				view.getOppField().add(b);}
			
				}

	public void onGameOver() {

		if(model.getFirstHero().getCurrentHP()==0){
			JOptionPane.showMessageDialog(null,"Congratulations !! \n Player 2 is the Winner","HearthStone", JOptionPane.INFORMATION_MESSAGE);
				
		}
		else{
			JOptionPane.showMessageDialog(null,"Congratulations !! \n Player 1 is the Winner","HearthStone", JOptionPane.INFORMATION_MESSAGE);
			
		}
		view.dispose();
		clip.stop();
		System.exit(0);
		
	}

	public ArrayList<JButton> getDeck() {
		return Deck;
	}

	public void setDeck(ArrayList<JButton> deck) {
		Deck = deck;
	}
	
    public  void updateview(){
        view.getCurrHand().removeAll();
		FirstHeroMinionsArray.clear();
	    FirstHeroMinionsButtons.clear();
		
		for(int i = 0 ; i < model.getFirstHero().getHand().size() ; i++){
			JButton b=new JButton();;
			if(model.getFirstHero().getHand().get(i) instanceof Minion){
				if(model.getFirstHero().equals(model.getCurrentHero())){
					String s=("<html>Name: " + model.getFirstHero().getHand().get(i).getName() + 
				        "<br />Mana Cost: " + model.getFirstHero().getHand().get(i).getManaCost() +
				        "<br />Rarity: " + model.getFirstHero().getHand().get(i).getRarity() +
				        "<br />Attack: " +((Minion) model.getFirstHero().getHand().get(i)).getAttack() + 
				        "<br />CurrentHP: " +((Minion) model.getFirstHero().getHand().get(i)).getCurrentHP()+ 
				        "<br />Taunt: " + ((Minion) model.getFirstHero().getHand().get(i)).isTaunt() +
				        "<br />Divine: " +((Minion) model.getFirstHero().getHand().get(i)).isDivine() + 
				        "<br />Charge: " + !((Minion) model.getFirstHero().getHand().get(i)).isSleeping())+"</html>";
					b.setText(s);
					
					
					}
				
				else {
					b.setText("");
					b.setIcon(new ImageIcon("Graphics/CardBack.png"));
				    b.setRolloverEnabled(true);
				    b.setBorderPainted(false);
				    b.setContentAreaFilled(false);
				}		
					FirstHeroMinionsArray.add((Minion)model.getFirstHero().getHand().get(i));
				    FirstHeroMinionsButtons.add(b);}
			else{
				if(model.getFirstHero().equals(model.getCurrentHero())){
						String s=("<html>Name: "+model.getFirstHero().getHand().get(i).getName() + 
							"<br />Mana Cost: "+ model.getFirstHero().getHand().get(i).getManaCost() +
							"<br />Rarity: " + model.getFirstHero().getHand().get(i).getRarity())+"</html>";
						b.setText(s);
						
				}		
				else {
					b.setText("");
					b.setIcon(new ImageIcon("Graphics/CardBack.png"));
					b.setRolloverEnabled(true);
				    b.setBorderPainted(false);
				    b.setContentAreaFilled(false);
					
				}	
				FirstHeroSpellsArray.add((Spell) model.getFirstHero().getHand().get(i));
				FirstHeroSpellsButtons.add(b);
				
				}
			 
			 b.addActionListener(this);
			 view.getCurrHand().add(b);}
				}
			
	public  void updateview2(){
		view.getOppHand().removeAll();
		SecondHeroMinionsArray.clear();
	    SecondHeroMinionsButtons.clear();
		
		for(int i = 0 ; i < model.getSecondHero().getHand().size() ; i++){
			JButton b=new JButton();
			if(model.getSecondHero().getHand().get(i) instanceof Minion){
				if(model.getSecondHero().equals(model.getCurrentHero())){	
					String s=("<html>Name: " + model.getSecondHero().getHand().get(i).getName() + 
					        "<br />Mana Cost: " + model.getSecondHero().getHand().get(i).getManaCost() +
					        "<br />Rarity: " + model.getSecondHero().getHand().get(i).getRarity() +
					        "<br />Attack: " +((Minion) model.getSecondHero().getHand().get(i)).getAttack() + 
					        "<br />CurrentHP: " +((Minion) model.getSecondHero().getHand().get(i)).getCurrentHP()+ 
					        "<br />Taunt: " + ((Minion) model.getSecondHero().getHand().get(i)).isTaunt() +
					        "<br />Divine: " +((Minion) model.getSecondHero().getHand().get(i)).isDivine() + 
					        "<br />Charge: " + !((Minion) model.getSecondHero().getHand().get(i)).isSleeping())+"</html>";
						b.setText(s);
						
				 }
				else{
					b.setText("");
					b.setIcon(new ImageIcon("Graphics/CardBack.png"));
					b.setRolloverEnabled(true);
				    b.setBorderPainted(false);
				    b.setContentAreaFilled(false);
				}
				  SecondHeroMinionsArray.add((Minion) model.getSecondHero().getHand().get(i));
				  SecondHeroMinionsButtons.add(b);
				}
		
			else{
					if(model.getSecondHero().equals(model.getCurrentHero())){
						String s=("<html>Name: "+model.getSecondHero().getHand().get(i).getName() + 
							"<br />Mana Cost: "+ model.getSecondHero().getHand().get(i).getManaCost() +
							"<br />Rarity: " + model.getSecondHero().getHand().get(i).getRarity())+"</html>";
						b.setText(s);
						//b.setFont(new Font("Arial", Font.PLAIN, 12));	
				      }			
					else {
						b.setText("");
						b.setIcon(new ImageIcon("Graphics/CardBack.png"));
						b.setRolloverEnabled(true);
					    b.setBorderPainted(false);
					    b.setContentAreaFilled(false);
					}
						
					SecondHeroSpellsArray.add((Spell) model.getSecondHero().getHand().get(i));
					SecondHeroSpellsButtons.add(b);
				}
				
				b.addActionListener(this);
				view.getOppHand().add(b);
			}		     
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws FullHandException, CloneNotSupportedException, IOException {
		Controller c = new Controller();
	}

}

 

