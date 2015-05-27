package com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.handlers;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;

public class HandlerCombo {

	private static int comboCounter;
	private static int previousComboMultiplier = 1;
	private static int posX = 1108 - 164/2;
	private static int posY = 514;
	private static Animation comboAnim = new Animation(1/60f, AssetLoader.combo.getRegions());
	private static float animationTimerComboAnim;
	private static boolean playCombo;
	private static int comboX = 1280/2 - 152/2;
	private static int comboY = 300;

	private HandlerCombo(){
		//Make sure this cannot be instantiated
	}

	public static void addComboCounter(){
		comboCounter++;
	}

	public static void clearComboCounter(){
		comboCounter = 0;
		previousComboMultiplier = 1;
		animationTimerComboAnim = 0;
		playCombo = false;
	}

	public static int comboMultiplier(){
		float f = (float) comboCounter/8.0f;
		int i = (int) f * 1;

		if (i == 0) {
			return 1;
		} else if (i >10){
			return 10;
		} else {
			return i;
		}
	}
	
	public static void draw(SpriteBatch batch, float delta){
		
		
		if (previousComboMultiplier < comboMultiplier()){
			previousComboMultiplier = comboMultiplier();
			AssetLoader.comboSound.play();
			playCombo = true;
		}
		
		if (playCombo){
			animationTimerComboAnim += delta;
			batch.draw(comboAnim.getKeyFrame(animationTimerComboAnim), comboX, comboY);
			if (comboAnim.isAnimationFinished(animationTimerComboAnim)){
				playCombo = false;
				animationTimerComboAnim = 0;
			}
		}
		
		if(comboMultiplier() == 1){
			batch.draw(AssetLoader.combo1, posX, posY);
		} else if (comboMultiplier() == 2){
			batch.draw(AssetLoader.combo2, posX, posY);
		} else if (comboMultiplier() == 3){
			batch.draw(AssetLoader.combo3, posX, posY);
		} else if (comboMultiplier() == 4){
			batch.draw(AssetLoader.combo4, posX, posY);
		} else if (comboMultiplier() == 5){
			batch.draw(AssetLoader.combo5, posX, posY);
		} else if (comboMultiplier() == 6){
			batch.draw(AssetLoader.combo6, posX, posY);
		} else if (comboMultiplier() == 7){
			batch.draw(AssetLoader.combo7, posX, posY);
		} else if (comboMultiplier() == 8){
			batch.draw(AssetLoader.combo8, posX, posY);
		} else if (comboMultiplier() == 9){
			batch.draw(AssetLoader.combo9, posX, posY);
		} else if (comboMultiplier() == 10){
			batch.draw(AssetLoader.combo10, posX, posY);
		} 
		
	}

	
}
