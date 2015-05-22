package com.chopstickphoenix.miniwrestlesurvival.dynamicObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;

public class Fart {
	
	private int width = 512;
	private int height = 256;
	private Rectangle rectCollision;
	private Sprite spriteExplosion;
	private int numberOfFramesExplosionExists;
	private boolean explosionExpired;
	private int fartDamage = 14;
	private int framesExplosionAlive = 7;
	
	public Fart (Vector2 pos){
		rectCollision = new Rectangle(pos.x - width/2, pos.y, width, height);
		spriteExplosion = new Sprite (AssetLoader.explosion);
		spriteExplosion.setPosition(pos.x - width/2, pos.y - height/5);
		explosionExpired = false;
	}
	
	public void update() {
		numberOfFramesExplosionExists ++;
		
		if (numberOfFramesExplosionExists >= framesExplosionAlive) explosionExpired = true;
	}
	
	public void draw(final SpriteBatch batch){
		spriteExplosion.draw(batch);
	}
	
	public Rectangle getRectangle() {
		return rectCollision;
	}
	
	public boolean getExplosionExpired(){
		return explosionExpired;
	}
	
	public int getDamage(){
		return fartDamage;
	}
}
