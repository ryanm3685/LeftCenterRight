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
	TextView whoIs;
	EditText playerNameField;
	Button playerOKButton, howManyOKButton, newGameButton, loadGameButton;
	LCRGame game;
	int n, i;
	public  final static String SER_KEY = "com.example.leftcenterright.ser";
	Context theContext = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup);
		
		
		
		howMany = (TextView)findViewById(R.id.HowManyLabel);
		numberPlayers = (EditText)findViewById(R.id.numberPlayers);
		whoIs = (TextView)findViewById(R.id.whoIsLabel);
		playerNameField = (EditText)findViewById(R.id.playerNameField);
		playerOKButton = (Button)findViewById(R.id.playerOKButton);
		howManyOKButton = (Button)findViewById(R.id.howManyOKButton);
		newGameButton = (Button)findViewById(R.id.newGameButton);
		loadGameButton = (Button)findViewById(R.id.loadGameButton);
		

		
		//first time OK is clicked
		newGameButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				LayoutInflater li = LayoutInflater.from(theContext);
				View howManyView = li.inflate(R.layout.how_many, null);
 
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						theContext);
 
				// set prompts.xml to alertdialog builder
				alertDialogBuilder.setView(howManyView);
				
				alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK",
				  new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog,int id) {

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
				
	
				
				//now have the OK button add player names
			/*	howManyOKButton.setOnClickListener(new OnClickListener(){
						
					@Override
					public void onClick(View v) {
						
						String s = (numberPlayers.getText()).toString();
						n = Integer.parseInt(s);
						//make sure the value is between 2-7 inclusive
						if (n < 2) n = 2;
						if (n > 7) n = 7;
						
						game = new LCRGame(n);
						
						//fill in player number for who is label
						whoIs.setText("Who is player " + (i + 1));

						i = 0;//index for which player we are adding
						
						
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
							whoIs.setText("Who is player " + (i + 1));
						}		
						
					}
					
					
				});*/
			}
			
		});
	}

}
