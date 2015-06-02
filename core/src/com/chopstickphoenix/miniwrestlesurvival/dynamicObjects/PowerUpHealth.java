package com.chopstickphoenix.miniwrestlesurvival.dynamicObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;
import com.chopstickphoenix.miniwrestlesurvival.utilities.GameData;

public class PowerUpHealth extends PowerUp {

	public PowerUpHealth(GameData gameData, int x){
		super(gameData, x);
		sprite = new Sprite(AssetLoader.powerupHealth);
	}
	
	@Override
	public void applyPowerup(){
		super.applyPowerup();
		if(gameData.getHealth() < 50){
			gameData.setHealth(gameData.getHealth() + 50);
		} else {
			gameData.setHealth(100); 
		}
	}
	
	
}
