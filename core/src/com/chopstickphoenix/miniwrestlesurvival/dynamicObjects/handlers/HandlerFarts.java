package com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.handlers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.Fart;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.MainCharacter;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.MainCharacter.enumAnimationState;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;
import com.chopstickphoenix.miniwrestlesurvival.utilities.GameData;

public class HandlerFarts {
	private GameData gameData;
	private MainCharacter mainCharacter;
	static private final float fartInterval = 500000000;
	static private float fartTimer;	
	static private Array<Fart> arrayFarts;


	public HandlerFarts(GameData g){
		this.gameData = g;
		this.mainCharacter = gameData.getMainCharacter();
		this.arrayFarts = gameData.getArrayFarts();
		fartTimer = TimeUtils.nanoTime();
	}
	
	
	public void useFart(Vector2 pos) {
		
		if (TimeUtils.nanoTime() - fartTimer > fartInterval 
				&& gameData.getCansOfBeans() >= 1){
			System.out.println("GameScreen - Fart Used");
			//Sound Effect
			AssetLoader.fart.play();
			mainCharacter.setAnimationState(enumAnimationState.FARTING);
			Fart fart = new Fart(pos);
			arrayFarts.add(fart);
			gameData.setCansOfBeans(gameData.getCansOfBeans()-1);
			fartTimer = TimeUtils.nanoTime();
		}
	}
	
	public void update(){
		for (Fart n : arrayFarts){
			n.update();
		}
	}
	
	public void render(SpriteBatch batch){
		for (Fart n : arrayFarts){
			n.draw(batch);
		}
	}
	
}
