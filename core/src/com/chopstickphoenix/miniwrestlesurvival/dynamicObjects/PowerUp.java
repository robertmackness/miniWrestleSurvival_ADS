package com.chopstickphoenix.miniwrestlesurvival.dynamicObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.chopstickphoenix.miniwrestlesurvival.utilities.GameData;

public class PowerUp {

	protected Sprite sprite;
	protected Rectangle rectangle;
	protected int intX;
	protected int intY = 240;
	protected static final int upperY = 260;
	protected static final int lowerY = 220;
	protected static final float width = 64;
	protected static final float height = 64;
	protected boolean isGoingUp;
	protected static GameData gameData;
	protected float timer;
	protected static float timerLimit = 5000000000f;
	protected boolean isExpired;

	protected PowerUp(GameData g, int x){
		rectangle = new Rectangle();
		this.intX = x;
		gameData = g;
		rectangle.setPosition(intX, intY);
		rectangle.setSize(width, height);
		timer = TimeUtils.nanoTime();
	}
	public void update(){

		if (intY == upperY){
			isGoingUp = false;
		} else if (intY  == lowerY){
			isGoingUp = true;
		}

		if (isGoingUp){
			intY++;
		} else if (!isGoingUp){
			intY--;
		}

		rectangle.setPosition(intX, intY);
	}
	public void applyPowerup(){
	rectangle = null;
	sprite = null;
	isExpired = true;
	}
	public void draw(SpriteBatch batch){
		batch.draw(sprite, rectangle.x, rectangle.y);
	}
	public Rectangle getRectangle(){
		return rectangle;
	}
	public boolean getIsExpired(){
		return isExpired;
	}
}
