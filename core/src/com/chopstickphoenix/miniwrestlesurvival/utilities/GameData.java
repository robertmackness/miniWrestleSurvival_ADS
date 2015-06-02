package com.chopstickphoenix.miniwrestlesurvival.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.chopstickphoenix.miniwrestlesurvival.miniWrestleSurvival;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.Chair;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.Enemy;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.Fart;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.Lightning;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.MainCharacter;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.PowerUp;

public class GameData {

	private MainCharacter mainCharacter;
	private float health;
	private int cansOfBeans;
	private int attackPower;
	private int score;
	private int level;
	private static Preferences prefs;
	private Array<Enemy> arrayEnemies;
	private Array<Chair> arrayChairs;
	private Array<Lightning> arrayLightning;
	private Array<Fart> arrayFarts;
	private Array<PowerUp> arrayPowerups;
	private int powerUpChairModifier;
	
	public GameData(){
		health = 100;
		cansOfBeans = 3;
		attackPower = 1;
		score = 0;
		level = 1;
		prefs = Gdx.app.getPreferences("wrestleRumble");
		arrayEnemies =  new Array<Enemy>();
		arrayChairs = new Array<Chair>();
		arrayLightning = new Array<Lightning>();
		arrayFarts = new Array<Fart>();
		arrayPowerups = new Array<PowerUp>();
		mainCharacter = new MainCharacter(getHealth(), getAttackPower(), getCansOfBeans(), this);
		powerUpChairModifier = 1;
	}

	public void setCurrentScore(int score) {
		this.score = score;
		if (getHighScore() < getCurrentScore()){
			setHighScore(getCurrentScore());
			}
	}


	public void resetALL(){
		setCurrentScore(0);
		setLevel(1);
		setCansOfBeans(3);
		setAttackPower(1);
		setHealth(100);
	}
	
	//Getters and Setters
	public Array<PowerUp> getArrayPowerups(){
		return arrayPowerups;
	}
	public Array<Enemy> getArrayEnemies(){
		return arrayEnemies;
	}
	public Array<Chair> getArrayChairs(){
		return arrayChairs;
	}
	public Array<Lightning> getArrayLightning(){
		return arrayLightning;
	}
	public Array<Fart> getArrayFarts(){
		return arrayFarts;
	}
	public void setHighScore(int score){
		prefs.putInteger("highScore", score);
		prefs.flush();
	}
	public static int getHighScore(){
		int score = prefs.getInteger("highScore"); 
		return score;
	}
	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public int getCansOfBeans() {
		return cansOfBeans;
	}

	public void setCansOfBeans(int energyDrinks) {
		this.cansOfBeans = energyDrinks;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	public int getCurrentScore() {
		return score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
		miniWrestleSurvival.mackdaddyInterface.submitSwarmScore();
	}
	public MainCharacter getMainCharacter(){
		return mainCharacter;
	}
	public Vector2 getMainCharacterPosition(){
		return mainCharacter.getPosition();
	}
	public int getPowerupChairModifier(){
		return powerUpChairModifier;
	}
	public void setPowerupChairModifier(int i){
		powerUpChairModifier = i;
	}
}
