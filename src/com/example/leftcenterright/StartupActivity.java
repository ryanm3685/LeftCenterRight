package com.example.leftcenterright;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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
	Button OKButton;
	LCRGame game;
	int n, i;
	public  final static String SER_KEY = "com.example.leftcenterright.ser";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup);
		
		
		
		howMany = (TextView)findViewById(R.id.HowManyLabel);
		numberPlayers = (EditText)findViewById(R.id.numberPlayers);
		whoIs = (TextView)findViewById(R.id.whoIsLabel);
		playerNameField = (EditText)findViewById(R.id.playerNameField);
		OKButton = (Button)findViewById(R.id.OKButton);
		
		
		
		
		//start by having whoIs and playerNameField invisible until
		//we've gotten the number of players
		whoIs.setVisibility(View.INVISIBLE);
		playerNameField.setVisibility(View.INVISIBLE);
		
		//first time OK is clicked
		OKButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String s = (numberPlayers.getText()).toString();
				n = Integer.parseInt(s);
				game = new LCRGame(n);
				
				//now make the invisible stuff visible, and "how many players stuff" invisible
				howMany.setVisibility(View.INVISIBLE);
				numberPlayers.setVisibility(View.INVISIBLE);
				whoIs.setVisibility(View.VISIBLE);
				playerNameField.setVisibility(View.VISIBLE);
				i = 0;//index for which player we are adding
				
				//now have the OK button add player names
				OKButton.setOnClickListener(new OnClickListener(){
						
					@Override
					public void onClick(View v) {
						
						// TODO Auto-generated method stub
						String s = (playerNameField.getText()).toString();
						game.addPlayer(s);
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
						}		
						
					}
					
					
				});
			}
			
		});
	}




	public void addPlayer()
	{
		
	}

}
