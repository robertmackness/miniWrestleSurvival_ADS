package com.chopstickphoenix.miniwrestlesurvival.dynamicObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;
import com.chopstickphoenix.miniwrestlesurvival.utilities.GameData;

public class PowerUpFarts extends PowerUp {

	public PowerUpFarts(GameData gameData, int x){
		super(gameData, x);
		sprite = new Sprite(AssetLoader.powerupFarts);
	}
	
	@Override
	public void applyPowerup(){
		super.applyPowerup();
		gameData.setCansOfBeans(3);
	}
	
	
}
