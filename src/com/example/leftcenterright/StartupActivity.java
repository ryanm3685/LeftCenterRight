package com.example.leftcenterright;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StartupActivity extends Activity {
	TextView howMany;
	EditText numberPlayers;
	TextView whoIsLabel;
	EditText playerNameField;
	Button playerOKButton, howManyOKButton, newGameButton, loadGameButton;
	LCRGame game;
	int n, i;
	public  final static String SER_KEY = "com.example.leftcenterright.ser";
	Context theContext;
	LayoutInflater li;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup);
		
		theContext = this;
		li = LayoutInflater.from(theContext);
		
		howMany = (TextView)findViewById(R.id.HowManyLabel);
		
		newGameButton = (Button)findViewById(R.id.newGameButton);
		loadGameButton = (Button)findViewById(R.id.loadGameButton);
		

		
		//first time OK is clicked
		newGameButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				View howManyView = li.inflate(R.layout.how_many, null);
				numberPlayers = (EditText)howManyView.findViewById(R.id.numberPlayers);
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						theContext);
 
				// set prompts.xml to alertdialog builder
				alertDialogBuilder.setView(howManyView);
				
				alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK",
				  new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog,int id) {
				    	
				    	
				    	String s = (numberPlayers.getText()).toString();
				    	System.out.println(s);
						n = Integer.parseInt(s);
						//make sure the value is between 2-7 inclusive
						if (n < 2) n = 2;
						if (n > 7) n = 7;
						
						game = new LCRGame(n);
						i = 0;//index for which player we are addingfdsf
				    	getPlayerNames();//this is where the dialog for entering the player names will be
				    }
				  })
				.setNegativeButton("Cancel",
				  new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog,int id) {
					dialog.cancel();
				    }
				  });

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();
				
			}
			
		});
	}
	
	public void getPlayerNames()
	{
		View playerDialogView = li.inflate(R.layout.player_dialog, null);
		whoIsLabel = (TextView)playerDialogView.findViewById(R.id.whoIsLabel); 
		playerNameField = (EditText)playerDialogView.findViewById(R.id.playerNameField);
		whoIsLabel.setText("Who is player " + (i + 1));
		
		
		AlertDialog.Builder playerDialogBuilder = new AlertDialog.Builder(
				theContext);

		playerDialogBuilder.setView(playerDialogView);
		
		playerDialogBuilder
		.setCancelable(false)
		.setPositiveButton("OK",
		  new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog,int id) {
		    	//todo:  fill this bad boy in!
		    	//fill in player number for who is label
				whoIsLabel.setText("Who is player " + (i + 1));
				
				String s1 = (playerNameField.getText()).toString();
				game.addPlayer(s1);
				if (i == (n - 1))//got everyone's name
				{
					Intent introIntent = new Intent(getApplicationContext(), IntroActivity.class);
					Bundle introBundle = new Bundle();
					introBundle.putSerializable("the_game", game);
					introIntent.putExtras(introBundle);
					startActivity(introIntent);
				}
				else
				{
					i++;
					playerNameField.setText(" ");//reset it for entering the other players
					
					getPlayerNames();
				}		
		    }
		  });

		// create alert dialog
		AlertDialog playerDialog = playerDialogBuilder.create();

		// show it
		playerDialog.show();
	}

}
