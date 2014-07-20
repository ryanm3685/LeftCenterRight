package com.example.leftcenterright;

import java.io.Serializable;
import java.util.Iterator;

public class LCRGame implements Serializable {
	private static final long serialVersionUID = -1;
	private LCRPlayer center; //the pot of chips
	private LCRPlayer head; //first player added
	private LCRPlayer tail; //last player added
	private LCRPlayer currentPlayer; //whose turn is it?
	private int playersWithChips; //once this number hit 1, that player wins all the chips in the center pot
	private int numberOfPlayers;
	
	public LCRGame(int p)
	{
		center = new LCRPlayer("Center");
		numberOfPlayers = p;
	}
	
	public void addPlayer(String s)
	{
		LCRPlayer p = new LCRPlayer(s);
		
		if (head == null)
		{
			head = p;
			tail = p;
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
	
	public void advanceToNextPlayer()
	{
		do
		{
			currentPlayer = currentPlayer.getNextPlayer();
		}while (currentPlayer.getChips() == 0);
	}

}
