package com.example.leftcenterright;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class ScoreActivity extends Activity{
	TextView nameTable, scoreTable;
	LCRGame game;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
		
		//unpack game object sent from startup screen intent
		game = (LCRGame)getIntent().getSerializableExtra("a_game");
		nameTable = (TextView)findViewById(R.id.nameTable);//theTable is where scores are displayed
		scoreTable = (TextView)findViewById(R.id.scoreTable);//theTable is where scores are displayed

		
		String nameString = "";
		String scoreString = "";
		//traverse through every player in the game, and populate the table
		LCRPlayer p = game.getHeadPlayer();
		do 
		{
			nameString += p.getName() + "\n";
			scoreString += p.getScore() + "\n";
			
			p = p.getNextPlayer();
		}while (p != game.getHeadPlayer());
		
		nameTable.setText(nameString);
		scoreTable.setText(scoreString);
		
	}
	
}