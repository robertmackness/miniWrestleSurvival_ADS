package com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.handlers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.MainCharacter;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.PowerUp;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.PowerUpCash;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.PowerUpChairs;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.PowerUpFarts;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.PowerUpHealth;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;
import com.chopstickphoenix.miniwrestlesurvival.utilities.GameData;

public class HandlerPowerUps {

	private static GameData gameData;
	private static MainCharacter mainChar;
	private static Array<PowerUp> arrayPowerUps;
	
	public HandlerPowerUps (GameData g){
		gameData = g;
		mainChar = gameData.getMainCharacter();
		//clear previous level stuff
		arrayPowerUps = gameData.getArrayPowerups();
		arrayPowerUps.clear();
		gameData.setPowerupChairModifier(1);
	}
	
	public static void rollTheDice(int enemyX){
		int r = MathUtils.random(1, 25);
		if (r == 24) {
			arrayPowerUps.add(new PowerUpChairs(gameData, enemyX));
		} else if (r == 23){
			arrayPowerUps.add(new PowerUpFarts(gameData, enemyX));
		} else if (r == 22){
			arrayPowerUps.add(new PowerUpHealth(gameData, enemyX));
		} else if (r > 20){
			arrayPowerUps.add(new PowerUpCash(gameData, enemyX));
		}
	}
	
	public void update() {
		for (PowerUp p : arrayPowerUps){
		
			p.update();
			if (p.getRectangle().overlaps(mainChar.getRectangleCollision())){
				p.applyPowerup();
				AssetLoader.coin.play();
			}
			if (p.getIsExpired()){
				arrayPowerUps.removeValue(p, false);
			}
		}
	}
	
	public void draw (SpriteBatch batch){
		for (PowerUp p : arrayPowerUps){
			p.draw(batch);
		}
	}
}
