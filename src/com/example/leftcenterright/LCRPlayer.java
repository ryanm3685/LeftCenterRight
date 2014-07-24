package com.example.leftcenterright;

import java.io.Serializable;

public class LCRPlayer implements Serializable {
	private static final long serialVersionUID = -2;
	private String name;
	private int score; //total running score
	private int chips; //how many chips
	private LCRPlayer nextPlayer, prevPlayer;
	
	public LCRPlayer(String name)
	{
		this.name = name;
		score = 9;
		chips = 0;
	}
	
	public String getName() { return name; }
	public void setScore(int i) { score = i; }
	public int getScore() { return score; }
	public int getChips() { return chips; }
	
	public void setNextPlayer(LCRPlayer p) { nextPlayer = p; }
	public LCRPlayer getNextPlayer() { return nextPlayer; }
	
	public void setPrevPlayer(LCRPlayer p) { prevPlayer = p; }
	public LCRPlayer getPrevPlayer() { return prevPlayer; }
	
	public void addChips(int c) { chips += c; }
	public void subtractChips(int c) { chips -= c; }
	
	public void giveChips(LCRPlayer p, int c)
	{
		p.addChips(c);
		this.subtractChips(c);
	}
}
