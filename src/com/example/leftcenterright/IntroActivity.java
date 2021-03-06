package com.example.leftcenterright;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class IntroActivity extends Activity {
	LCRGame game;
	TextView NameLabel, ScoreLabel, CenterLabel, ResultLabel;
	ImageView[] theImages = new ImageView[3];
	Button Roll;
	Context theContext;
	LayoutInflater li;
	boolean quitStatus; //will we be quitting this program?
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
		
		theContext = this;
		li = LayoutInflater.from(theContext);

		//unpack game object sent from startup screen intent
		game = (LCRGame)getIntent().getSerializableExtra("the_game");
		NameLabel = (TextView)findViewById(R.id.NameLabel);
		ScoreLabel = (TextView)findViewById(R.id.ScoreLabel);
		CenterLabel = (TextView)findViewById(R.id.CenterLabel);

		Roll = (Button)findViewById(R.id.rollbutton);
		ResultLabel = (TextView)findViewById(R.id.ResultLabel);
		
		theImages[0] = (ImageView)findViewById(R.id.imageView1);
		theImages[1] = (ImageView)findViewById(R.id.imageView2);
		theImages[2]= (ImageView)findViewById(R.id.imageView3);

		
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
		
		//take care of the status dialog
		View resultsView = li.inflate(R.layout.status_display, null);
		TextView ResultLabel = (TextView)resultsView.findViewById(R.id.ResultLabel);
		ResultLabel.setText(game.getResult());
		
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				theContext);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(resultsView);
		
		alertDialogBuilder
		.setCancelable(false)
		.setPositiveButton("OK",
		  new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog,int id) {
		    	
		    	dialog.cancel();
		    }
		  });

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
		
		
				
		if (game.getPlayersWithChips() == 1)//create intent to go to score screen
		{
			Intent scoreIntent = new Intent(getApplicationContext(), ScoreActivity.class);
			Bundle scoreBundle = new Bundle();
			scoreBundle.putSerializable("a_game", game);
			scoreIntent.putExtras(scoreBundle);
			startActivity(scoreIntent);
			
			game.newGame();
		}
		
		//only show dice that will actually be thrown
		if (game.getCurrentPlayer().getChips() < 3)
		{
			setImage(2, false);
		}
		else
		{
			setImage(2, true);
		}
		
		if (game.getCurrentPlayer().getChips() < 2)
		{
			setImage(1, false);
		}
		else
		{
			setImage(1, true);
		}
		//of course Die1 will be shown, or this player's turn is skipped!
		setImage(0, true);
	}
	
	public void rollDice()
	{
		game.rollDice();		
	}
	
	public void setImage(int i, boolean isVisible)
	{
		if (!isVisible)
		{
			theImages[i].setVisibility(View.INVISIBLE);
		}
		else
		{
	
			switch ((game.getDie(i)).getValue())
			{
			case 'L':
				theImages[i].setImageResource(R.drawable.left);
				break;
			case 'C':
				theImages[i].setImageResource(R.drawable.center);
				break;
			case 'R':
				theImages[i].setImageResource(R.drawable.right);
				break;
			case '*':
				theImages[i].setImageResource(R.drawable.nothing);
				break;
			}
			
			theImages[i].setVisibility(View.VISIBLE);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	     if (keyCode == KeyEvent.KEYCODE_BACK) {
	     //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
	    	 
	    	//bring up a dialog asking if the user wants to save
	    	View quitView = li.inflate(R.layout.quit_dialog, null);
	 		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
	 				theContext);

	 		// set prompts.xml to alertdialog builder
	 		alertDialogBuilder.setView(quitView);
	 		
	 		alertDialogBuilder
	 		.setCancelable(false)
	 		.setNegativeButton("Yes",
	 		  new DialogInterface.OnClickListener() {
	 		    public void onClick(DialogInterface dialog,int id) {
	 		    	
	 		    	game.saveGame();//save this game and then quit
	 		    	quitStatus = true;
	 		    	finish();
	 		    }
	 		  })
	 		  .setNeutralButton("No",
	 		  new DialogInterface.OnClickListener() {
	 		    public void onClick(DialogInterface dialog,int id) {
	 		    	
	 		    	dialog.cancel();
	 		    	quitStatus = true;
	 		    	finish();
	 		    }
	 		  })
	 		  .setPositiveButton("Cancel",
	 		  new DialogInterface.OnClickListener() {
	 		    public void onClick(DialogInterface dialog,int id) {
	 		    	
	 		    	dialog.cancel();
	 		    	quitStatus = false;
	 		    }
	 		  });

	 		// create alert dialog
	 		AlertDialog alertDialog = alertDialogBuilder.create();

	 		// show it
	 		alertDialog.show();
	    	//quit if we need to 
	 		/*if (quitStatus) return super.onKeyDown(keyCode, event); 
	 		else return true;*/
	 		//return super.onKeyDown(keyCode, event); 
	     }
	     return super.onKeyDown(keyCode, event);    
	}///

}
