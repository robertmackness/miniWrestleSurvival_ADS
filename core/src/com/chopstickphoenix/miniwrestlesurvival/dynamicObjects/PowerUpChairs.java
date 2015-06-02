package com.chopstickphoenix.miniwrestlesurvival.dynamicObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;
import com.chopstickphoenix.miniwrestlesurvival.utilities.GameData;

public class PowerUpChairs extends PowerUp {

	public PowerUpChairs(GameData gameData, int x){
		super(gameData, x);
		sprite = new Sprite(AssetLoader.powerupChair);
	}
	
	@Override
	public void applyPowerup(){
		super.applyPowerup();
		gameData.setPowerupChairModifier(gameData.getPowerupChairModifier() +1);
	}
	
	
}
