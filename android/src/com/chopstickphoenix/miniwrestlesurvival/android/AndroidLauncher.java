package com.chopstickphoenix.miniwrestlesurvival.android;

import java.io.File;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.chopstickphoenix.miniwrestlesurvival.miniWrestleSurvival;
import com.chopstickphoenix.miniwrestlesurvival.utilities.GameData;
import com.chopstickphoenix.miniwrestlesurvival.utilities.MackdaddyInterface;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.swarmconnect.Swarm;
import com.swarmconnect.SwarmLeaderboard;


/*
GOOGLE stuff
Ad unit name: default
Ad unit ID: ca-app-pub-3729576601854244/7117890012
*/



public class AndroidLauncher extends AndroidApplication implements MackdaddyInterface {
	
	private InterstitialAd interstitial;
	private  AdRequest adRequest;
	
	
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new miniWrestleSurvival(this), config);
		
		//Create the screen on
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		//Swarm
		Swarm.setActive(this);
		 
		//Admob
		interstitial = new InterstitialAd(this);
	    interstitial.setAdUnitId("ca-app-pub-3729576601854244/7117890012");
	    adRequest = new AdRequest.Builder().addTestDevice("D25AD0A36877106B87DC9865E9C5CD05").build();

	}

	@Override
	public void shareToast(){
		
		this.runOnUiThread(new Runnable() {
			  public void run() {
					Toast.makeText(AndroidLauncher.this, "Creating Screenshot", Toast.LENGTH_SHORT).show();
			  }
			});
	}
	
	@Override
	public void share(String fileHandle) {
			
		try {
			File myFile = new File(fileHandle);
			MimeTypeMap mime = MimeTypeMap.getSingleton();
			String ext=myFile.getName().substring(myFile.getName().lastIndexOf(".")+1);
			String type = mime.getMimeTypeFromExtension(ext);
			Intent sharingIntent = new Intent("android.intent.action.SEND");
			sharingIntent.setType(type);
			sharingIntent.putExtra("android.intent.extra.STREAM",Uri.fromFile(myFile));   
			startActivity(Intent.createChooser(sharingIntent,"Brag using"));
		}
		catch(Exception e){
			System.out.println("" + e);
		} 
	}

	@Override
	public void swarmConnect() {
			if (Swarm.isLoggedIn()) {
				 submitSwarmScore();
				//This pauses a second before showing the leaderboards, allowing the connection to re-start
				Timer.schedule(new Task(){
					@Override
					public void run() {
						Swarm.showLeaderboards();
					}
				}, 2f);
			} else { 
				initialiseSwarm();
			}
		}
	
	private void initialiseSwarm() {
		 Swarm.init(this, 17671, "4ddda816df06618cee59ffffac2c06d0");
		 Swarm.setAllowGuests(true);
		 submitSwarmScore();
		 Timer.schedule(new Task(){
				@Override
				public void run() {
						Swarm.showLeaderboards();
				}
			}, 1f);
	}

	@Override
	public void submitSwarmScore() {

		if (Swarm.isLoggedIn()) {
			//submit to Swarm
			SwarmLeaderboard.submitScore(19581, (int) GameData.getHighScore());
		}

	}
	
	public void onResume() {
	    super.onResume();
	    Swarm.setActive(this);
	}

	public void onPause() {
	    super.onPause();
	    Swarm.setInactive(this);
	}

@Override
public void onBackPressed(){
	showAdInterstitial();
	super.onBackPressed();
}
	
	@Override
	public void showAdInterstitial() {
			 runOnUiThread(new Runnable() {
			@Override
			public void run() {

				if (interstitial.isLoaded()){
					 interstitial.show();
				} else {
					Log.v("AdMob", "Tried to show, no ad to show");
				}
			}
		}); 
		Log.v("AdMob", "Show ad"); 
	}


	@Override
	public void loadAdInterstitial() {
		 runOnUiThread(new Runnable() {
			@Override
			public void run() {

				if (!interstitial.isLoaded()){
					interstitial.loadAd(adRequest);
					Log.v("AdMob", "Load ad");
				} else {
					Log.v("AdMob", "Ad already loaded");
				}
			}
		}); 

		Log.v("AdMob", "Load ad"); 
	}
}
