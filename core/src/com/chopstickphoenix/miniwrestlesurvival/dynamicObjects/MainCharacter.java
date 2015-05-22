package com.chopstickphoenix.miniwrestlesurvival.dynamicObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;
import com.chopstickphoenix.miniwrestlesurvival.utilities.GameData;


public class MainCharacter {

	//graphics
	private TextureAtlas atlasRunningRight = AssetLoader.mainCharacterRunningRight;
	private Animation animationRunningRight = new Animation(1/60f, atlasRunningRight.getRegions());
	private TextureAtlas atlasRunningLeft = AssetLoader.mainCharacterRunningLeft;;
	private Animation animationRunningLeft = new Animation(1/60f, atlasRunningLeft.getRegions());;
	
	private TextureAtlas atlasThrowchairRight = AssetLoader.mainCharacterThrowChairRight;
	private Animation animationRightThrow = new Animation(1/60f, atlasThrowchairRight.getRegions());
	private TextureAtlas atlasThrowchairLeft = AssetLoader.mainCharacterThrowChairLeft;
	private Animation animationLeftThrow = new Animation(1/60f, atlasThrowchairLeft.getRegions());
	
	private Sprite spriteStationaryRight = new Sprite(AssetLoader.mainCharacterStationaryRight);
	private Sprite spriteStationaryLeft = new Sprite(AssetLoader.mainCharacterStationaryLeft);
	private Sprite spriteFartRight = new Sprite(AssetLoader.mainCharacterFartRight);
	private Sprite spriteFartLeft = new Sprite(AssetLoader.mainCharacterFartLeft);
	//Main Character States
	private enum enumState {ALIVE, DEAD};
	private enumState state;
	public enum enumMainCharOrientation {LEFT, RIGHT};
	public enumMainCharOrientation orientation;
	public enum enumAnimationState {THROWING_CHAIR, FARTING, STATIONARY, RUNNING, JUMPING};
	private enumAnimationState animationState;
	//Position and Collision
	private Rectangle rectangleCollision = new Rectangle();
	private int screenEdgeLeft = 1;
	private int screenEdgeRight = 1280-96; //minus size of char
	private int footstepCounter;
	static final public Vector2 START_POSITION  = new Vector2(400, 220);
	private int velocity = 0;
	private int jumpVelocity;
	static final private int WIDTH = 64;
	static final private int HEIGHT = 128;
	static final private int MAINCHAR_SPEED = 10;
	static final private int GRAVITY = 8;
	//Frame Counters and Timers
	private int intJumpingFrameCounter;
	private float elapsedTime;
	private int stationaryFrameCounter = 0;
	private boolean isJumping;
	//Health and other
	private float health;
	private Healthbar healthBar;
	private GameData data;
	
	//Constructor
	public MainCharacter(float health, int attackPower, int energyDrinks, GameData data){
		this.state = enumState.ALIVE;
		this.health = health;
		this.data = data;
		orientation = enumMainCharOrientation.RIGHT;
		animationState = enumAnimationState.STATIONARY;
		rectangleCollision.setSize(WIDTH, HEIGHT);
		rectangleCollision.setPosition(START_POSITION);
		isJumping = false;
		healthBar = new Healthbar();
	}

	//Update to be called in logicStep() via Render() on GameScreen
	public void update(){
		rectangleCollision.setX(rectangleCollision.getX()+velocity);
		if (rectangleCollision.getX() < screenEdgeLeft) {
			rectangleCollision.setX(screenEdgeLeft);
		}
		if(rectangleCollision.getX() > screenEdgeRight) { 
			rectangleCollision.setX(screenEdgeRight);
		}
				
		//state updater here that sets to stationary if wrestler hasn't done anything for a while
		if(stationaryFrameCounter >= 15){
			setAnimationState(enumAnimationState.STATIONARY);
			System.out.println("Animation - Stationary Triggered");
			stationaryFrameCounter = 0;
			footstepCounter = 5;
		}
		
		//Jumping function
		if(isJumping){
			if(jumpVelocity >= GRAVITY){
				rectangleCollision.y += (jumpVelocity - GRAVITY);
				jumpVelocity -= GRAVITY;
			} else if (jumpVelocity < GRAVITY && rectangleCollision.y != START_POSITION.y){
				rectangleCollision.y -= GRAVITY;
			}
			
			if (rectangleCollision.y == START_POSITION.y){
				isJumping = false;
			}
		}
		

		// This is important, as due to multi-touch input handling we cannot poll for a button release setting velocity to 0
		// within the GameScreen handleInput() method; as any un-pressed indexed pointers will trigger it
		setVelocity(0); 
	}
	
	
	public void updateHealthbar(){
		healthBar.update(data.getMainCharacterPosition(), data.getHealth());
	}
	
	public void draw(final SpriteBatch batch, float delta) {
		
		//set position of animations and sprites
		float x = getRectangleCollision().x-20; //-16 to account for sprite size/animations
		float y = getRectangleCollision().y;
		elapsedTime += delta;
		
		//when this hits x, character set to stationary
		stationaryFrameCounter += 1;
		
		healthBar.draw(batch);
		
		if(getOrientation() == enumMainCharOrientation.RIGHT){
			
			if(getAnimationState() == enumAnimationState.RUNNING){
				batch.draw(animationRunningRight.getKeyFrame(elapsedTime, true), x,y);
				System.out.println("Animation - Running");
				if (footstepCounter >= 5){
					AssetLoader.footStep.play();
					footstepCounter = 0;
				}
				footstepCounter ++;
			}
			if(getAnimationState() == enumAnimationState.STATIONARY){
				spriteStationaryRight.setPosition(x, y);
				spriteStationaryRight.draw(batch);					
			}
			if(getAnimationState() == enumAnimationState.FARTING){
				spriteFartRight.setPosition(x, y);
				spriteFartRight.draw(batch);
				System.out.println("Animation - Fart");
			}
			if(getAnimationState() == enumAnimationState.JUMPING){
				spriteFartRight.setPosition(x, y);
				spriteFartRight.draw(batch);
				System.out.println("Animation - Jumping");
			}
			if(getAnimationState() == enumAnimationState.THROWING_CHAIR){
				batch.draw(animationRightThrow.getKeyFrame(elapsedTime, false), x,y);
				System.out.println("Animation - Throw Chair");
			}
		}
		if(getOrientation() == enumMainCharOrientation.LEFT){
			if(getAnimationState() == enumAnimationState.RUNNING){
				batch.draw(animationRunningLeft.getKeyFrame(elapsedTime, true), x,y);
				System.out.println("Animation - Running");
				if (footstepCounter >= 5){
					AssetLoader.footStep.play();
					footstepCounter = 0;
				}
				footstepCounter ++;
			}
			if(getAnimationState() == enumAnimationState.STATIONARY){
					spriteStationaryLeft.setPosition(x, y);
					spriteStationaryLeft.draw(batch);
			}
			if(getAnimationState() == enumAnimationState.FARTING){
					spriteFartLeft.setPosition(x, y);
					spriteFartLeft.draw(batch);
					System.out.println("Animation - Fart");
			}
			if(getAnimationState() == enumAnimationState.JUMPING){
				spriteFartLeft.setPosition(x, y);
				spriteFartLeft.draw(batch);
				System.out.println("Animation - Jumping");
		}
			if(getAnimationState() == enumAnimationState.THROWING_CHAIR){
				batch.draw(animationLeftThrow.getKeyFrame(elapsedTime, false), x,y);
					System.out.println("Animation - Throw Chair");
			}
		}
	}

	public void jump(){
		if (rectangleCollision.y == START_POSITION.y){
			isJumping = true;
			jumpVelocity = 56;
			AssetLoader.jump.play();
		}

	}
	
	//GETTERS and SETTERS
	public int getSpeed(){
		return MAINCHAR_SPEED;
	}
	public Vector2 getPosition() {
		return new Vector2(rectangleCollision.getX(), rectangleCollision.getY());
	}
		public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	public int getVelocity(){
		return velocity;
	}
	public void setOrientation(enumMainCharOrientation orientation) {
		this.orientation = orientation;
	}
	public enumMainCharOrientation getOrientation(){
		return orientation;
	}
	public Rectangle getRectangleCollision(){
		return rectangleCollision;
	}
	public float getHealth(){
		return health;
	}
	public void setDamage(float i){
		health -= i;
	}

	public enumAnimationState getAnimationState() {
		return animationState;
	}

	public void setAnimationState(enumAnimationState animationState) {
		this.animationState = animationState;
	}
}
