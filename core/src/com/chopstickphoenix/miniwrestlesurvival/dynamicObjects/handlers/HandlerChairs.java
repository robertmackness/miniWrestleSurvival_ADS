package com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.handlers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.Chair;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.MainCharacter;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.MainCharacter.enumAnimationState;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.MainCharacter.enumMainCharOrientation;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;
import com.chopstickphoenix.miniwrestlesurvival.utilities.GameData;

public class HandlerChairs {

	static private GameData gameData;
	static private Array<Chair> arrayChairs;
	static private float chairBulletTimer;
	static private float chairBulletInterval = 250000000;
	static final private int CHAIRBULLET_SPEED = 12;
	static private MainCharacter mainCharacter;
	
	public HandlerChairs(GameData g){
		this.gameData = g;
		arrayChairs = gameData.getArrayChairs();
		mainCharacter = gameData.getMainCharacter();
		arrayChairs.clear(); //clear last level's chairs in mid-air
	}
	
	public void throwChair() {
		
		if(TimeUtils.nanoTime() - chairBulletTimer > chairBulletInterval){
			
			mainCharacter.setAnimationState(enumAnimationState.THROWING_CHAIR);
			//Sound Effect
			AssetLoader.throwChair.play();
			
			System.out.println("GameScreen - throwChair()");
			if (mainCharacter.orientation == enumMainCharOrientation.RIGHT){
				Chair chair = new Chair(mainCharacter.getPosition(), CHAIRBULLET_SPEED);
				arrayChairs.add(chair);
				chairBulletTimer = TimeUtils.nanoTime();
			}
			if (mainCharacter.orientation == enumMainCharOrientation.LEFT){
				Chair chair = new Chair(mainCharacter.getPosition(), -CHAIRBULLET_SPEED);
				arrayChairs.add(chair);
				chairBulletTimer = TimeUtils.nanoTime();
			}
		} 
	}

	public void update(){
		for (Chair b : arrayChairs) {
			b.update();
		}
	}

	public void render(SpriteBatch batch) {
		for (Chair b : arrayChairs) {
			b.draw(batch);
		}
		
	}
	
}
