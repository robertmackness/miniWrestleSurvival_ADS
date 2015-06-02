package com.chopstickphoenix.miniwrestlesurvival.dynamicObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;
import com.chopstickphoenix.miniwrestlesurvival.utilities.GameData;

public class PowerUpCash extends PowerUp {

	public PowerUpCash(GameData gameData, int x){
		super(gameData, x);
		sprite = new Sprite(AssetLoader.powerupCash);
	}
	
	@Override
	public void applyPowerup(){
		super.applyPowerup();
		gameData.setCurrentScore(gameData.getCurrentScore() + 200 * gameData.getLevel());
	}
	
	
}
