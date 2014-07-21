package com.example.leftcenterright;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class IntroActivity extends Activity {
	LCRGame game;
	TextView NameLabel, ScoreLabel, CenterLabel, Die1, Die2, Die3, ResultLabel;
	Button Roll;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);

		//unpack game object sent from startup screen intent
		game = (LCRGame)getIntent().getSerializableExtra("the_game");
		NameLabel = (TextView)findViewById(R.id.NameLabel);
		ScoreLabel = (TextView)findViewById(R.id.ScoreLabel);
		CenterLabel = (TextView)findViewById(R.id.CenterLabel);
		Die1 = (TextView)findViewById(R.id.Die1);
		Die2 = (TextView)findViewById(R.id.Die2);
		Die3 = (TextView)findViewById(R.id.Die3);
		Roll = (Button)findViewById(R.id.rollbutton);
		ResultLabel = (TextView)findViewById(R.id.ResultLabel);

		//initialize the game
		game.newGame();
		redraw();
		
		Roll.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				rollDice();
				redraw();
			}});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_intro, menu);
		return true;
	}
	
	public void redraw()
	{
		NameLabel.setText(game.getCurrentPlayer().getName());
		ScoreLabel.setText(game.getCurrentPlayer().getChips()+"");
		CenterLabel.setText(game.getCenter().getChips()+"");
		ResultLabel.setText(game.getResult());
		
		//only show dice that will actually be thrown
		if (game.getCurrentPlayer().getChips() < 3)
			Die3.setVisibility(View.INVISIBLE);
		else
			Die3.setVisibility(View.VISIBLE);
			Die3.setText(game.getDie3().getValue()+"");
		
		if (game.getCurrentPlayer().getChips() < 2)
			Die2.setVisibility(View.INVISIBLE);
		else
			Die2.setVisibility(View.VISIBLE);
			Die2.setText(game.getDie2().getValue()+"");
		
		//of course Die1 will be shown, or this player's turn is skipped!
		Die1.setText(game.getDie1().getValue()+"");
	}
	
	public void rollDice()
	{
		game.rollDice();		
	}


}
