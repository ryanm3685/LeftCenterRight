import com.example.leftcenterright.LCRGame;
import com.example.leftcenterright.LCRPlayer;



public class LCRTest {

	public static void main(String args[])
	{
		LCRGame g = new LCRGame(3);
		
		g.addPlayer("Hank");
		g.addPlayer("Ryan");
		g.addPlayer("Colin");
		g.addPlayer("Geno");
		g.addPlayer("Ed");
		
		LCRPlayer p = g.getHeadPlayer();
		do 
		{
			System.out.println(p.getName());
			p = p.getNextPlayer();
		}while (p != g.getHeadPlayer());
	}
}
