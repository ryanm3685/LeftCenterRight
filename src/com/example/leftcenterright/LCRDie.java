package com.example.leftcenterright;
import java.util.Random;

public class LCRDie {
	private char value; //0,2,4 *, 1 = L, 3 = C, 5 = R
	Random r;
	
	public LCRDie()
	{
		r = new Random(6);
	}
	
	public char Roll()
	{
		char c = '*';
		int i = r.nextInt();
		
		switch (i)
		{
		case 0:
		case 2:
		case 4:
			c = '*';
			break;
		case 1:
			c = 'L';
			break;
		case 3:
			c = 'C';
			break;
		case 5:
			c = 'R';
			break;
		}
		
		return c;
	}
}
