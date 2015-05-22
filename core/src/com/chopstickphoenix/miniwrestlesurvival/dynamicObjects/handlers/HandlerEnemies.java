package com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.handlers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.Enemy;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.Enemy.enumType;
import com.chopstickphoenix.miniwrestlesurvival.utilities.GameData;

public class HandlerEnemies {

	private GameData gameData;
	private Array<Enemy> enemyArray;
	private float enemySpawnInterval = 750000000f;	
	private int enemySpawnLimit;
	private int numberofEnemiesLeftToSpawn = 0;
	private float enemySpawnIntervalDecreaser = 50000000f;
	private float enemyspawnTimer;
	private boolean lastEnemySpawned = false;
	private int screenLeft = 0;
	private int screenRight = 1200;


	public HandlerEnemies (GameData gameData){
		this.gameData = gameData;
		this.enemyArray = gameData.getArrayEnemies();
		enemyArray.clear(); //ensure this is empty from previous levels
		enemySpawnLimit = gameData.getLevel() * 5 + 15;
		numberofEnemiesLeftToSpawn += enemySpawnLimit; //set in show() method
		if (enemySpawnInterval >= 400000000f) enemySpawnInterval -= gameData.getLevel()*enemySpawnIntervalDecreaser;
		if (enemySpawnInterval <= 50000000f) enemySpawnInterval = 50000000f;
		handleEnemySpawns();

	}


	public void handleEnemySpawns(){
		int random = MathUtils.random(Math.max(1, gameData.getLevel()-3), gameData.getLevel()+1); //sets hardest enemy to current level

		if(numberofEnemiesLeftToSpawn > 0){
			if(TimeUtils.nanoTime() - enemyspawnTimer > enemySpawnInterval){
				if (random == 1){
					Enemy e = new Enemy(new Vector2(MathUtils.random(screenLeft, screenRight), 0), enumType.ONE);
					enemyArray.add(e);
				}
				if (random == 2){
					Enemy e = new Enemy(new Vector2(MathUtils.random(screenLeft, screenRight), 0), enumType.TWO);
					enemyArray.add(e);
				}
				if (random == 3){
					Enemy e = new Enemy(new Vector2(MathUtils.random(screenLeft, screenRight), 0), enumType.THREE);
					enemyArray.add(e);
				}
				if (random == 4){
					Enemy e = new Enemy(new Vector2(MathUtils.random(screenLeft, screenRight), 0), enumType.FOUR);
					enemyArray.add(e);
				}
				if (random == 5){
					Enemy e = new Enemy(new Vector2(MathUtils.random(screenLeft, screenRight), 0), enumType.FIVE);
					enemyArray.add(e);
				}
				if (random == 6){
					Enemy e = new Enemy(new Vector2(MathUtils.random(screenLeft, screenRight), 0), enumType.SIX);
					enemyArray.add(e);
				}
				if (random >= 7){
					Enemy e = new Enemy(new Vector2(MathUtils.random(screenLeft, screenRight), 0), enumType.SEVEN);
					enemyArray.add(e);
				}

				enemyspawnTimer = TimeUtils.nanoTime();
				numberofEnemiesLeftToSpawn -= 1;
			}
			if (numberofEnemiesLeftToSpawn == 0){
				lastEnemySpawned = true;
			}
		}
	}

	public boolean getLastEnemySpawned(){
		return lastEnemySpawned;
	}

	public void update(){
		for (Enemy e : enemyArray){
			e.update(gameData.getMainCharacterPosition().x);
		}
	}


	public void render(SpriteBatch batch, float delta) {
		for (Enemy e : enemyArray){
			e.draw(batch, delta);
		}
		
	}
	
}
