package com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.MainCharacter;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;
import com.chopstickphoenix.miniwrestlesurvival.utilities.GameData;


public class HandlerPiano {

	private GameData gameData;

	private MainCharacter mainChar;
	private Rectangle rectStationaryZone;
	private int rectStationaryWidth = 480;
	private int rectStationaryHeight = 420;
	private int counter;
	private int counterThreshold = 240;
	private static int pianoSpeed = 13;
	private static int pianoDamage = 50;
	private Array<Piano> arrayPianos;
	private Array<Explosion> arrayExplosions;
	private int arenaFloor = 220;
	private int pianoRotationSpeed = 5;


	public HandlerPiano(GameData g){
		this.gameData = g;
		mainChar = gameData.getMainCharacter();
		rectStationaryZone = new Rectangle(mainChar.getPosition().x - 160, mainChar.getPosition().y, rectStationaryWidth, rectStationaryHeight);
		arrayPianos = new Array<Piano>();
		arrayExplosions = new Array<Explosion>();
	}
		
		
		public void update(){
			
			//Check to see if the character has stayed in the same area for 240 frames (aka 3 seconds), if so, spawn a piano and add to array
			if (rectStationaryZone.overlaps(mainChar.getRectangleCollision())){
				counter ++;
				if (counter > counterThreshold){
					createPiano();
					counter = 0;
				}
			} else if (!rectStationaryZone.overlaps(mainChar.getRectangleCollision())){
				rectStationaryZone.setPosition(mainChar.getRectangleCollision().x - 160, mainChar.getRectangleCollision().y);
				counter = 0;
			}
			
			//update pianos and check for collissions. If collided, do damage and animate an explosion.
			for (Piano p : arrayPianos){
				p.update();
				if (p.rectCollision.overlaps(mainChar.getRectangleCollision())){
					gameData.setHealth(gameData.getHealth() - pianoDamage);
					createExplosion(p.rectCollision.x+32, p.rectCollision.y);
					arrayPianos.removeValue(p, false);
				} else if (p.rectCollision.y <= arenaFloor){
					createExplosion(p.rectCollision.x+32, p.rectCollision.y);
					arrayPianos.removeValue(p, false);
				}
			}

		}
	
		public void draw(SpriteBatch batch, float delta){
			for (Piano p : arrayPianos){
				p.draw(batch);
			}
			for (Explosion e : arrayExplosions){
				e.draw(batch, delta);
				if (e.getIsFinished()){
					arrayExplosions.removeValue(e, false);
				}
				
			}
		}

		private void createPiano(){
			arrayPianos.add(new Piano());
		}
		
		private void createExplosion(float x, float y){
			arrayExplosions.add(new Explosion(x, y));
		}
		
		
		//		Piano Inner Class |||||   Piano Inner Class |||||   Piano Inner Class |||||   Piano Inner Class |||||
		// 		Piano Inner Class |||||   Piano Inner Class |||||   Piano Inner Class |||||   Piano Inner Class |||||   
		private class Piano {
			
			private Sprite spritePiano;
			private Rectangle rectCollision;

			public Piano() {
				spritePiano = new Sprite(AssetLoader.piano);
				rectCollision = new Rectangle(mainChar.getPosition().x - spritePiano.getWidth()/2, 760, spritePiano.getWidth(), spritePiano.getHeight());
			}
			
			public void update() {
				rectCollision.y -= pianoSpeed;
			}
			
			public void draw(SpriteBatch batch) {
				spritePiano.setPosition(rectCollision.x, rectCollision.y-40);
				spritePiano.setRotation(spritePiano.getRotation()+ pianoRotationSpeed);
				spritePiano.draw(batch);
			}

		}
		//	    Piano Inner Class |||||   Piano Inner Class |||||   Piano Inner Class |||||   Piano Inner Class |||||   
		//		Piano Inner Class |||||   Piano Inner Class |||||   Piano Inner Class |||||   Piano Inner Class |||||
		
		
		//		Explosion Inner Class   ||||    Explosion Inner Class   ||||    Explosion Inner Class   ||||    Explosion Inner Class   ||||    
		//		Explosion Inner Class   ||||    Explosion Inner Class   ||||    Explosion Inner Class   ||||    Explosion Inner Class   ||||    
		private class Explosion {
			
			private Animation explosion;
			private float animationTimer;
			private float x;
			private float y;
			private boolean isFinished;
			
			public Explosion (float x, float y){
				explosion = new Animation(1/60f, AssetLoader.explosionLarge.getRegions());
				this.x = x;
				this.y = y;
				AssetLoader.explosionLargeSound.play();
			}
			public void draw(SpriteBatch batch, float delta){
				animationTimer += delta;
				batch.draw(explosion.getKeyFrame(animationTimer), x, y);
				isFinished = explosion.isAnimationFinished(animationTimer);
			}
			public boolean getIsFinished(){
				return isFinished;
			}
		}
		//		Explosion Inner Class   ||||    Explosion Inner Class   ||||    Explosion Inner Class   ||||    Explosion Inner Class   ||||    
		//		Explosion Inner Class   ||||    Explosion Inner Class   ||||    Explosion Inner Class   ||||    Explosion Inner Class   ||||    
	}
