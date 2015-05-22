package com.chopstickphoenix.miniwrestlesurvival.dynamicObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;


public class Enemy {
	private Vector2 position;
	private float velocity;
	private int health;
	private int startingHealth;
	private Rectangle rectangleCollision = new Rectangle();
	private int WIDTH = 64;
	private int HEIGHT = 128;
	public enum enumType {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN};
	public enumType type;
	public enum enumEnemyState {SPAWNING, ALIVE, FIGHTING, DEAD};
	public enumEnemyState state;
	public enum enumEnemyOrientation {LEFT, RIGHT};
	public enumEnemyOrientation orientation;
	private int spawnClimbingSpeed = 1;
	private boolean justFighting = false;
	private float damage;
	private boolean hitBack;
	private boolean hitBackActive;
	private int hitBackFrameTimer;
	private float elapsedTime;
	//RenderAssets
	private Animation animClimb;
	private Animation animRightRun;
	private Animation animRightFight;
	private Animation animLeftRun;
	private Animation animLeftFight;
	
	
	// climb, fight x2, run x2
	// stationary x 2

	//Constructor
	public Enemy(Vector2 position, enumType type){
		this.position = position;
		this.type = type;
		this.state = state.SPAWNING;
		setHealthAndVelocity(type);
		setDrawAssets(type);
		rectangleCollision.setSize(WIDTH, HEIGHT);
		rectangleCollision.setPosition(position);
		hitBack = false;
		hitBackActive = false;
		
	}

	private void setDrawAssets(enumType type) {
		switch (type){
		case ONE: 
			animClimb = new Animation(0.2f, AssetLoader.e1Climb.getRegions());
			animRightRun = new Animation(0.05f, AssetLoader.e1RightRun.getRegions());
			animRightFight = new Animation(0.1f, AssetLoader.e1RightFight.getRegions());
			animLeftRun = new Animation(0.05f, AssetLoader.e1LeftRun.getRegions());
			animLeftFight = new Animation(0.1f, AssetLoader.e1LeftFight.getRegions());
			break;
		case TWO: 
			animClimb = new Animation(0.2f, AssetLoader.e2Climb.getRegions());
			animRightRun = new Animation(0.05f, AssetLoader.e2RightRun.getRegions());
			animRightFight = new Animation(0.1f, AssetLoader.e2RightFight.getRegions());
			animLeftRun = new Animation(0.05f, AssetLoader.e2LeftRun.getRegions());
			animLeftFight = new Animation(0.1f, AssetLoader.e2LeftFight.getRegions());
			break;
		case THREE:  
			animClimb = new Animation(0.2f, AssetLoader.e3Climb.getRegions());
			animRightRun = new Animation(0.05f, AssetLoader.e3RightRun.getRegions());
			animRightFight = new Animation(0.1f, AssetLoader.e3RightFight.getRegions());
			animLeftRun = new Animation(0.05f, AssetLoader.e3LeftRun.getRegions());
			animLeftFight = new Animation(0.1f, AssetLoader.e3LeftFight.getRegions());
			break;
		case FOUR:  
			animClimb = new Animation(0.2f, AssetLoader.e4Climb.getRegions());
			animRightRun = new Animation(0.05f, AssetLoader.e4RightRun.getRegions());
			animRightFight = new Animation(0.1f, AssetLoader.e4RightFight.getRegions());
			animLeftRun = new Animation(0.05f, AssetLoader.e4LeftRun.getRegions());
			animLeftFight = new Animation(0.1f, AssetLoader.e4LeftFight.getRegions());
			break;
		case FIVE: 	 
			animClimb = new Animation(0.2f, AssetLoader.e5Climb.getRegions());
			animRightRun = new Animation(0.05f, AssetLoader.e5RightRun.getRegions());
			animRightFight = new Animation(0.1f, AssetLoader.e5RightFight.getRegions());
			animLeftRun = new Animation(0.05f, AssetLoader.e5LeftRun.getRegions());
			animLeftFight = new Animation(0.1f, AssetLoader.e5LeftFight.getRegions());
			break;
		case SIX: 	 
			animClimb = new Animation(0.2f, AssetLoader.e6Climb.getRegions());
			animRightRun = new Animation(0.05f, AssetLoader.e6RightRun.getRegions());
			animRightFight = new Animation(0.1f, AssetLoader.e6RightFight.getRegions());
			animLeftRun = new Animation(0.05f, AssetLoader.e6LeftRun.getRegions());
			animLeftFight = new Animation(0.1f, AssetLoader.e6LeftFight.getRegions());
			break;
		case SEVEN: 	 
			animClimb = new Animation(0.2f, AssetLoader.e7Climb.getRegions());
			animRightRun = new Animation(0.05f, AssetLoader.e7RightRun.getRegions());
			animRightFight = new Animation(0.1f, AssetLoader.e7RightFight.getRegions());
			animLeftRun = new Animation(0.05f, AssetLoader.e7LeftRun.getRegions());
			animLeftFight = new Animation(0.1f, AssetLoader.e7LeftFight.getRegions());
			break;
		}
	}
	
	//Update to be called in logicStep() via Render() on GameScreen
	public void update(float mainCharX){
		setOrientation(mainCharX);
		
		if (getHealth() <= 0){
			state = state.DEAD;
			System.out.println("Enemy Class - state set to DEAD");
		}
		
		if (hitBack && state != enumEnemyState.SPAWNING) {

			if(hitBackFrameTimer >= 1){
				if (getOrientation() == enumEnemyOrientation.LEFT){
					rectangleCollision.setX(rectangleCollision.getX() + 2);
				}
				if (getOrientation() == enumEnemyOrientation.RIGHT){
					rectangleCollision.setX(rectangleCollision.getX() - 2);
				}
				
				if(hitBackFrameTimer >7){
					rectangleCollision.setY(rectangleCollision.getY() + 2);
				} else {
					rectangleCollision.setY(rectangleCollision.getY() - 2);
				}
				
				hitBackFrameTimer --;
			} 
			if( hitBackFrameTimer <= 0){
				hitBack = false;
				}
			}
		
		
		if(state == enumEnemyState.FIGHTING){
			
		}
		else if (state == enumEnemyState.ALIVE && !hitBack){
			if(getOrientation() == enumEnemyOrientation.RIGHT){
				rectangleCollision.setX(rectangleCollision.getX()+velocity);//the enemy only needs velocity on the x axis
			}
			if(getOrientation() == enumEnemyOrientation.LEFT){
				rectangleCollision.setX(rectangleCollision.getX()-velocity);//add x values if facing right, subtract if left
			}
		}
		else if (state == enumEnemyState.SPAWNING){
			rectangleCollision.setY(rectangleCollision.getY() + spawnClimbingSpeed);
			if(rectangleCollision.getY() > 220){
				rectangleCollision.setY(220);
				state = enumEnemyState.ALIVE;
			}
		}
	}
	
	private void setHealthAndVelocity(enumType type){
		switch (type){
		case ONE: 		health = 2;  startingHealth = 2;	velocity = MathUtils.random(1,3); damage = 0.1f; break;
		case TWO: 		health = 4;  startingHealth = 4;	velocity = MathUtils.random(2,3); damage = 0.25f; break;
		case THREE: 	health = 6;  startingHealth = 6;	velocity = MathUtils.random(2,3); damage = 0.4f; break;
		case FOUR: 		health = 8;  startingHealth = 8;	velocity = MathUtils.random(2,3); damage = 0.7f; break;
		case FIVE: 		health = 10; startingHealth = 10;	velocity = MathUtils.random(2,3); damage = 1f; break;
		case SIX: 		health = 12; startingHealth = 12;	velocity = MathUtils.random(1,4); damage = 1.5f; break;
		case SEVEN: 	health = 14; startingHealth = 14;	velocity = MathUtils.random(1,4); damage = 2f; break;
		}
	}
	
	public void draw(final SpriteBatch batch, float delta) {
		
		float x = rectangleCollision.getX() - 20;
		float y = rectangleCollision.getY();
		elapsedTime += delta;
		
		if (state == enumEnemyState.FIGHTING){
			System.out.println("enemy fighting");
			if(getOrientation() == enumEnemyOrientation.RIGHT){
				batch.draw(animRightFight.getKeyFrame(elapsedTime, true), x, y);
			}
			if(getOrientation() == enumEnemyOrientation.LEFT){
				batch.draw(animLeftFight.getKeyFrame(elapsedTime, true), x, y);
			}
		}
		else if (state == enumEnemyState.SPAWNING){
			batch.draw(animClimb.getKeyFrame(elapsedTime, true), x, y);
		}
		else if (state == enumEnemyState.ALIVE || state == enumEnemyState.DEAD){

			if(getOrientation() == enumEnemyOrientation.RIGHT){
				batch.draw(animRightRun.getKeyFrame(elapsedTime, true), x, y);
			}
			if(getOrientation() == enumEnemyOrientation.LEFT){
				batch.draw(animLeftRun.getKeyFrame(elapsedTime, true), x, y);
			}
		}	

		
	}
	
	private void setOrientation(float mainCharX){
		if (mainCharX > rectangleCollision.getX()){
			orientation = orientation.RIGHT;
		}
		if (mainCharX < rectangleCollision.getX()){
			orientation = orientation.LEFT;
		}
	}
	
	//GETTERS and SETTERS
	public float getDamage(){
		return damage;
	}
	
	public enumEnemyOrientation getOrientation(){
		return orientation;
	}
	public float getX() {
		return rectangleCollision.getX();
	}
	public void setHealth(int d){
		this.health = d;
	}
	public int getHealth(){
		return health;
	}
	public Rectangle getrectangleCollision(){
		return rectangleCollision;
	}
	public void setX(float x){
		rectangleCollision.setX(x);
	}
	public void setState(enumEnemyState e){
		state = e;
	}
	public enumEnemyState getState(){
		return state;
	}
	public boolean getJustFighting(){
		return justFighting;
	}
	public void setJustFighting(boolean b){
		justFighting = b;
	}
	public int getStartingHealth(){
		return startingHealth;
	}
	
	public void setHitBack (){
		if (hitBackFrameTimer == 0){
			hitBack = true;
			hitBackFrameTimer = 14;
		}
	}
	public void setHitBackSpawning(){
		if (orientation == enumEnemyOrientation.LEFT){
			rectangleCollision.setX(rectangleCollision.getX() + 5);
		} else {
			rectangleCollision.setX(rectangleCollision.getX() - 5);
		}
	}
}
