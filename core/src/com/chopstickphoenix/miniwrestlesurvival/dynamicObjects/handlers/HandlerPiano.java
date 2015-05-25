package com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.handlers;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.MainCharacter;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;
import com.chopstickphoenix.miniwrestlesurvival.utilities.GameData;


public class HandlerPiano {

	private GameData gameData;
	private Sprite piano;
	private Animation explosion;
	private MainCharacter mainChar;
	private Rectangle rectCollision;
	private Rectangle rectStationaryZone;
	private int counter;
	private int counterThreshold;
	private static int pianoSpeed = 10;
	private static int pianoDamage = 50;
	private boolean boolPianoDrop;
	private boolean boolExplosion;
	private float animationTimer;


	public HandlerPiano(GameData g){
		this.gameData = g;
		mainChar = gameData.getMainCharacter();
		piano = new Sprite(AssetLoader.piano);
		explosion = new Animation(1/60f, AssetLoader.explosionLarge.getRegions());
		rectCollision = new Rectangle(mainChar.getPosition().x - piano.getWidth()/2, 720, piano.getWidth(), piano.getHeight());
		rectStationaryZone = new Rectangle(mainChar.getPosition().x - 160, mainChar.getPosition().y, 320, 420);
		
		
	}

	public void update(){
		piano.setPosition(rectCollision.x, rectCollision.y);
		
		if (mainChar.getRectangleCollision().overlaps(rectStationaryZone)){
			counter++;

			if(counter >= 240){
				boolPianoDrop = true;
			}
		}else if (!mainChar.getRectangleCollision().overlaps(rectStationaryZone)){
			rectStationaryZone.setPosition(mainChar.getPosition().x - 160, mainChar.getPosition().y);
			counter = 0;
			counter ++;
		}
	}

	private void dropPiano(){
		rectCollision.setY(rectCollision.getY() - pianoSpeed);
		if (rectCollision.overlaps(mainChar.getRectangleCollision())){
			gameData.setHealth(gameData.getHealth() - pianoDamage);
			explode();
		}
	}

	private void explode(){
		
	}
}
