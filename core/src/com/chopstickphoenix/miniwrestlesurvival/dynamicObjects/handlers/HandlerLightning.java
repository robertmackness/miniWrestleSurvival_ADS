package com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.handlers;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.Lightning;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;
import com.chopstickphoenix.miniwrestlesurvival.utilities.GameData;

public class HandlerLightning {
	private static GameData gameData;
	private static Array<Lightning> arrayLightning;
	private static final int lightningInterval = 175000000;
	private static boolean lightning;
	private static boolean boolExplosionSmall;
	private static float explosionXPosition;
	private static float explosionYPosition;
	private static int lightningCounter;
	private static float lightningTimer;
	private static float lightningDelay;
	private static Animation explosionSmall;
	private static float floatRuntime;
	
	
	public HandlerLightning (GameData g){
		this.gameData = g;
		this.arrayLightning = gameData.getArrayLightning();
		arrayLightning.clear(); //clear any leftover lightning from previous level
		lightning = false;
		boolExplosionSmall = false;
		lightningTimer = TimeUtils.nanoTime();
		explosionSmall = new Animation(1/5f, AssetLoader.explosionSmall.getRegions());
		explosionSmall.setPlayMode(Animation.PlayMode.NORMAL);
		
	}

	public void startLightning() {
		lightning = true;

		lightningCounter = MathUtils.random(5, 7);
		lightningDelay = MathUtils.random(500000000f, 900000000f);
		lightningTimer = TimeUtils.nanoTime() + lightningDelay;
		
	}
	
	public void startExplosion(float posX, float posY) {
		boolExplosionSmall = true;
		this.explosionXPosition = posX;
		this.explosionYPosition = posY;
		
	}
		
	public void handleLightning(){
		
		if(lightning){

			if (TimeUtils.nanoTime() - lightningTimer > lightningInterval
						&& lightningCounter >=1){
				
				lightningCounter--;
				Lightning l = new Lightning();
				arrayLightning.add(l);
				lightningTimer = TimeUtils.nanoTime();
				
				if(lightningCounter == 0) lightning=false;
			}
			
		}
	}

	public void render(SpriteBatch batch, float delta) {
		for (Lightning l : arrayLightning){
			l.draw(batch);
		}
		
		if (boolExplosionSmall){
			floatRuntime += delta;
			batch.draw(explosionSmall.getKeyFrame(floatRuntime), explosionXPosition + 16, explosionYPosition + 64);
			if (explosionSmall.isAnimationFinished(floatRuntime)){
				boolExplosionSmall = false;
				floatRuntime = 0;
			}
		}
	}
	
	
	public void update(){
		handleLightning();
		for (Lightning l : arrayLightning){
			l.update();
		}
	}
}
