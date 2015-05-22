package com.chopstickphoenix.miniwrestlesurvival.dynamicObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;

public class Healthbar {
private Vector2 position;
private Sprite healthbar;
	
public Healthbar(){
healthbar = new Sprite();
}
	
public void update(Vector2 pos, float health){
	if (health>=100)healthbar = AssetLoader.healthBar100;
	else if(health>=95)healthbar = AssetLoader.healthBar95;
	else if(health>=90)healthbar = AssetLoader.healthBar90;
	else if(health>=85)healthbar = AssetLoader.healthBar85;
	else if(health>=80)healthbar = AssetLoader.healthBar80;
	else if(health>=75)healthbar = AssetLoader.healthBar75;
	else if(health>=70)healthbar = AssetLoader.healthBar70;
	else if(health>=65)healthbar = AssetLoader.healthBar65;
	else if(health>=60)healthbar = AssetLoader.healthBar60;
	else if(health>=55)healthbar = AssetLoader.healthBar55;
	else if(health>=50)healthbar = AssetLoader.healthBar50;
	else if(health>=45)healthbar = AssetLoader.healthBar45;
	else if(health>=40)healthbar = AssetLoader.healthBar40;
	else if(health>=35)healthbar = AssetLoader.healthBar35;
	else if(health>=30)healthbar = AssetLoader.healthBar30;
	else if(health>=25)healthbar = AssetLoader.healthBar25;
	else if(health>=20)healthbar = AssetLoader.healthBar20;
	else if(health>=15)healthbar = AssetLoader.healthBar15;
	else if(health>=10)healthbar = AssetLoader.healthBar10;
	else if(health>=5)healthbar = AssetLoader.healthBar5;
	else if(health>=1)healthbar = AssetLoader.healthBar1;
	else if(health==0)healthbar = AssetLoader.healthBar0;
	healthbar.setPosition(pos.x-4, pos.y+120);
}

public void draw(SpriteBatch batch){
	 healthbar.draw(batch);
}

}
