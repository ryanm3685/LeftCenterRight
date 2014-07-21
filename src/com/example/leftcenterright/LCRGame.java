package com.example.leftcenterright;

import java.io.Serializable;

public class LCRGame implements Serializable {
	private static final long serialVersionUID = -1;
	private LCRPlayer center; //the pot of chips
	private LCRPlayer head; //first player added
	private LCRPlayer tail; //last player added
	private LCRPlayer currentPlayer; //whose turn is it?
	private int playersWithChips; //once this number hit 1, that player wins all the chips in the center pot
	private int numberOfPlayers;
	private LCRDie Die1, Die2, Die3;
	
	public LCRGame(int p)
	{
		center = new LCRPlayer("Center");
		numberOfPlayers = p;
		
		//create dice
		Die1 = new LCRDie();
		Die2 = new LCRDie();
		Die3 = new LCRDie();
	}
	
	public void addPlayer(String s)
	{
		LCRPlayer p = new LCRPlayer(s);
		
		if (head == null)
		{
			head = p;
			tail = p;
			currentPlayer = p;
		}
		else
		{
			tail.setNextPlayer(p);
			p.setPrevPlayer(tail);
			
			head.setPrevPlayer(p);
			p.setNextPlayer(head);
			
			tail = p;
		}
	}
	
	public LCRPlayer getCurrentPlayer() { return currentPlayer; }
	public LCRPlayer getHeadPlayer() { return head; }
	public LCRPlayer getTailPlayer() { return tail; }
	public int getNumberOfPlayers() { return numberOfPlayers; }
	public LCRDie getDie1() { return Die1; }
	public LCRDie getDie2() { return Die2; }
	public LCRDie getDie3() { return Die3; }
	
	public void newGame()
	{
		LCRPlayer p = getHeadPlayer();
		do 
		{
			p.addChips(3);
			p = p.getNextPlayer();
		}while (p != getHeadPlayer());
	}
	
	public void advanceToNextPlayer()
	{
		do
		{
			currentPlayer = currentPlayer.getNextPlayer();
		}while (currentPlayer.getChips() == 0);
	}
	
	public void rollDice()
	{
		//roll the number of dice the player has, maximum 3
		handleRoll(Die1);
		if (currentPlayer.getChips() > 1) handleRoll(Die2);
		if (currentPlayer.getChips() > 2) handleRoll(Die3);
		
		//now that we're finished rolling dice and passing chips, go to the next player
		advanceToNextPlayer();
	}
	
	public void handleRoll(LCRDie d)
	{
		d.Roll();
		switch (d.getValue())
		{
		case 'L':
			currentPlayer.giveChips(currentPlayer.getNextPlayer(), 1);
			break;
		case 'C':
			currentPlayer.giveChips(center, 1);
			break;
		case 'R':
			currentPlayer.giveChips(currentPlayer.getPrevPlayer(), 1);
			break;
		}
		
	}

}
