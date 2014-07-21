package com.example.leftcenterright;
import java.io.Serializable;
import java.util.Random;

public class LCRDie implements Serializable {
	private char value; //0,2,4 *, 1 = L, 3 = C, 5 = R
	private int i;
	Random r;
	
	public LCRDie()
	{
		r = new Random();
		value = '*';
	}
	
	public void Roll()
	{
		i = r.nextInt(6);
		
		switch (i)
		{
		case 0:
		case 2:
		case 4:
			value = '*';
			break;
		case 1:
			value = 'L';
			break;
		case 3:
			value = 'C';
			break;
		case 5:
			value = 'R';
			break;
		}
	}	

	public char getValue() { return value; }

	public int getIndex() { return i; }
}
