package com.chopstickphoenix.miniwrestlesurvival.dynamicObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;

public class Lightning {
	
	private int width = 32;
	private int height = 480;
	private Rectangle rectCollision;
	private Sprite spriteLightning;
	private int numberOfFramesExplosionExists;
	private boolean lightningExpired;
	private int fartDamage = 50;
	private int framesExplosionAlive = 7;
	
	public Lightning(){
		rectCollision = new Rectangle(MathUtils.random(16, 1200), 220, width, height);
		
		int r = MathUtils.random(1, 3);
		if (r == 1)	spriteLightning = new Sprite (AssetLoader.lightning1);
		if (r == 2)	spriteLightning = new Sprite (AssetLoader.lightning2);
		if (r == 3)	spriteLightning = new Sprite (AssetLoader.lightning3);
		
		spriteLightning.setPosition(rectCollision.x, rectCollision.y);
		AssetLoader.lightning.play();
		lightningExpired = false;
	}
	
	public void update() {
		numberOfFramesExplosionExists ++;
		
		if (numberOfFramesExplosionExists >= framesExplosionAlive) lightningExpired = true;
	}
	
	public void draw(final SpriteBatch batch){
		spriteLightning.draw(batch);
	}
	
	public Rectangle getRectangle() {
		return rectCollision;
	}
	
	public boolean getExplosionExpired(){
		return lightningExpired;
	}
	
	public int getDamage(){
		return fartDamage;
	}
}
