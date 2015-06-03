package com.chopstickphoenix.miniwrestlesurvival.dynamicObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;

public class Chair {

	private int velocity = 0;
	private int boundaryY = 0;
	private int gravity = 12;
	private Sprite sprite;
	static final private int WIDTH = 32;
	static final private int HEIGHT = 64; //more room to animate
	public enum enum_chair_state {FLYING, COLLISION, OFF_SCREEN};
	private enum_chair_state chair_state;
	public Rectangle rectangleCollision = new Rectangle();

	//Constructor
	public Chair (Vector2 pos, int velocity){
		this.velocity = velocity;
		sprite = new Sprite(AssetLoader.chairBullet);
		sprite.setBounds(pos.x, pos.y, WIDTH, HEIGHT);
		chair_state = enum_chair_state.FLYING;
		rectangleCollision.setSize(WIDTH, HEIGHT);
		rectangleCollision.setPosition(sprite.getX(), sprite.getY());
	}

	//Update to be called in logicStep() via Render() on GameScreen
	public void update(){
		sprite.setX(sprite.getX()+velocity);
		sprite.setY(sprite.getY() + gravity);
		gravity--;
		rectangleCollision.setPosition(sprite.getX(), sprite.getY());
		if (getrectangleCollision().y < boundaryY){
			setState(chair_state.OFF_SCREEN);
		}
	}
	
	public void draw(final SpriteBatch batch) {
		if(velocity<0){
		sprite.setRotation(sprite.getRotation() + 12);
		sprite.draw(batch);
		}
		if(velocity>0){
			sprite.setRotation(sprite.getRotation() - 12);
			sprite.draw(batch);
			}
		
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int vELOCITY) {
		velocity = vELOCITY;
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public enum_chair_state getState() {
		return chair_state;
	}

	public void setState(enum_chair_state state) {
		chair_state = state;
	}
	public Rectangle getrectangleCollision(){
		return rectangleCollision;
	}

}