package com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.handlers;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.Chair;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.Chair.enum_chair_state;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.Enemy;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.Enemy.enumEnemyState;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.Fart;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.Lightning;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.MainCharacter;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;
import com.chopstickphoenix.miniwrestlesurvival.utilities.GameData;

public abstract class HandlerCollisions {

	static private GameData gameData;
	static private HandlerLightning handlerLightning;
	static private Array<Enemy> arrayEnemies;
	static private Array<Chair> arrayChairs;
	static private Array<Lightning> arrayLightning;
	static private Array<Fart> arrayFart;
	static private MainCharacter mainCharacter;


	private HandlerCollisions(GameData g){
	}

	public static void handleCollisions(GameData g, HandlerLightning hl){
		gameData = g;
		handlerLightning = hl;
		arrayChairs = gameData.getArrayChairs();
		arrayEnemies = gameData.getArrayEnemies();
		arrayLightning = gameData.getArrayLightning();
		arrayFart = gameData.getArrayFarts();
		mainCharacter = gameData.getMainCharacter();



		Iterator<Enemy> iteratorEnemies = arrayEnemies.iterator();


		while (iteratorEnemies.hasNext()){
			Enemy e = iteratorEnemies.next();


			//remove current enemy if dead
			if(e.state == enumEnemyState.DEAD){
				gameData.setCurrentScore(gameData.getCurrentScore() + (e.getStartingHealth()*2)*HandlerCombo.comboMultiplier()); //TODO check this works comboMultiplier
				HandlerPowerUps.rollTheDice((int) e.getX());
				iteratorEnemies.remove();
				//enemies.removeValue(e, true);
				System.out.println("enemy dead trigger");

				int r = MathUtils.random(0, 100);
				if (r >= 90){
					AssetLoader.voiceHeadshot.play();
					handlerLightning.startLightning();
					handlerLightning.startExplosion(e.getX(), e.getrectangleCollision().y);
				}

			}


			//chairs to current enemy collisions
			Iterator<Chair> iteratorChairBullets = arrayChairs.iterator();
			while (iteratorChairBullets.hasNext()){
				Chair c = iteratorChairBullets.next();
				if (c.getState() == enum_chair_state.OFF_SCREEN){
					iteratorChairBullets.remove();
					//chairBullets.removeValue(c, true);
					HandlerCombo.clearComboCounter(); //TODO make sure this works clearComboCounter
					System.out.println("chair off screen");
				}
				if(c.getrectangleCollision().overlaps(e.getrectangleCollision()) && e.getState() != enumEnemyState.DEAD){
					e.setHealth(e.getHealth() - gameData.getAttackPower());
					iteratorChairBullets.remove();
					if (e.getState() != enumEnemyState.SPAWNING)e.setHitBack();
					if (e.getState() == enumEnemyState.SPAWNING)e.setHitBackSpawning();
					//chairBullets.removeValue(c, true);
					System.out.println("chair hit enemy");	
					//Sound Effect
					AssetLoader.chairCollision.play();
					HandlerCombo.addComboCounter(); //TODO make sure works addComboCounter on chair hit
				} continue;
			}

			//farts to current enemy collisions
			Iterator<Fart> iteratorFarts = arrayFart.iterator();
			while (iteratorFarts.hasNext()){
				Fart fart = iteratorFarts.next();
				if (fart.getRectangle().overlaps(e.getrectangleCollision())){
					e.setHealth(e.getHealth() - fart.getDamage());
				}
				if(fart.getExplosionExpired()){
					System.out.println("GameScreen - Fart Expired");
					iteratorFarts.remove();
				}
			}

			Iterator<Lightning> iteratorLightning = arrayLightning.iterator();
			while(iteratorLightning.hasNext()){
				Lightning l = iteratorLightning.next();
				if(l.getRectangle().overlaps(e.getrectangleCollision())){
					e.setHealth(e.getHealth() - l.getDamage());
				}
				if(l.getExplosionExpired()){
					iteratorLightning.remove();
				}
			}

			//current enemy to maincharacter collisions
			if (e.getrectangleCollision().overlaps(mainCharacter.getRectangleCollision()) && e.getState() != enumEnemyState.SPAWNING){
				e.setState(enumEnemyState.FIGHTING);
				e.setJustFighting(true);
				gameData.setHealth(gameData.getHealth() - e.getDamage());
				HandlerPunchNoises.punchNoise();
			}//reset enemies from FIGHTING to ALIVE without messing up enemies in SPAWNING state
			if (! e.getrectangleCollision().overlaps(mainCharacter.getRectangleCollision())
					&& e.getJustFighting() == true){
				e.setJustFighting(false);
				e.setState(enumEnemyState.ALIVE);

			}


		}
	}

}

