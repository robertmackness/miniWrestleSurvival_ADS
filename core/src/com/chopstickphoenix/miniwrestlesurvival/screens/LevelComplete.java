package com.chopstickphoenix.miniwrestlesurvival.screens;

import java.text.DecimalFormat;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;
import com.chopstickphoenix.miniwrestlesurvival.utilities.GameData;

public class LevelComplete implements Screen {

	
	//Class utilities
	private BitmapFont font;
	private SpriteBatch batch = new SpriteBatch();
	private Game wrestleRumble;
	private GameData gameData;
	private Sprite spriteBackground;
	private OrthographicCamera camera;
	private DecimalFormat df;
	private Vector3 inputVector3;
	
	//Temp Utility
	//ShapeRenderer shapeRenderer;
	
	//Dynamic Textfields
	private Vector2 scorePosition = new 		Vector2(765,140);
	private Vector2 attackPowerPosition = new 	Vector2(145,310);
	private Vector2 healthPosition = new 		Vector2(145,210);
	private Vector2 energyPosition = new 		Vector2(145,110);
	//HUD
	private Rectangle rectNextLevel = new Rectangle(1110, 0, 170, 128);
	private Rectangle rectBuyAttackPwr = new Rectangle(140, 313, 188, 56);
	private Rectangle rectBuyHealth = new Rectangle(140,213,370,56);
	private Rectangle rectBuyEnergy = new Rectangle (140,113,187,56);
	
	//Temporary Score Handler Variables
	private int intCostAtkPwr;
	private int intCostBeanz;
	private int intCostHealth;
	private String stringCurrentScore;
	
	public LevelComplete (Game game, GameData gameData){
		this.wrestleRumble = game;
		this.gameData = gameData;
	}
	
	@Override
	public void show() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		font = AssetLoader.font;
		spriteBackground = new Sprite(AssetLoader.backgroundLevelComplete);
		gameData.setLevel(gameData.getLevel() +1); //add 1 level
		df = new DecimalFormat("#.##"); //used to round any numbers to 2 decimal points
		inputVector3 = new Vector3();
		//Score handlers
		intCostAtkPwr = (int) (gameData.getCurrentScore() * 0.6);
		intCostBeanz  = (int) (gameData.getCurrentScore() * 0.6);
		intCostHealth  = (int) (gameData.getCurrentScore() * 0.2);
		stringCurrentScore = ""+ gameData.getCurrentScore();
		//Temp
		//shapeRenderer = new ShapeRenderer();
		
	}

	@Override
	public void render(float delta) {
	handleGraphics();
	handleInput();
	}

	private void handleGraphics() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		spriteBackground.draw(batch);
		//Score string
		stringCurrentScore = ""+ gameData.getCurrentScore();
		font.draw(batch, stringCurrentScore, scorePosition.x - font.getBounds(stringCurrentScore).width/2, scorePosition.y - font.getBounds(stringCurrentScore).height/2);
		//atk pwr steak string
		font.draw(batch, "Attack Power Upgrade: "+ intCostAtkPwr, attackPowerPosition.x, attackPowerPosition.y);
		//health vegetables string
		if(gameData.getHealth()<100)font.draw(batch, "Health Restore: "+ intCostHealth, healthPosition.x, healthPosition.y);
		if(gameData.getHealth()==100)font.draw(batch, "Health at 100%", healthPosition.x, healthPosition.y);
		//beanz string
		if(gameData.getCansOfBeans() < 3)font.draw(batch, "Extra fartz: " + intCostBeanz, energyPosition.x, energyPosition.y);
		if(gameData.getCansOfBeans() == 3)font.draw(batch, "3/3 Canz", energyPosition.x, energyPosition.y);
		batch.end();
		
		/*
		//Debug Rectangles
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.rect(1110, 0, 170, 128);
		shapeRenderer.rect(140, 313, 188, 56);
		shapeRenderer.rect(140,213,370,56);
		shapeRenderer.rect(140,113,187,56);
		shapeRenderer.end();
		*/
	}

	
	private void handleInput () {

		if (Gdx.input.isTouched()){
			inputVector3.set(Gdx.input.getX(), Gdx.input.getY(),0);
			camera.unproject(inputVector3);
		}

		if (!Gdx.input.isTouched()){
			if (rectNextLevel.contains(inputVector3.x, inputVector3.y)){
				wrestleRumble.setScreen(new GameScreen(wrestleRumble, gameData));
				System.out.println("Launching new level from LevelComplete Screen");
				inputVector3.set(0, 0, 0);
			}
			if (rectBuyAttackPwr.contains(inputVector3.x, inputVector3.y)){
				if (gameData.getCurrentScore() >= intCostAtkPwr){
					gameData.setAttackPower(gameData.getAttackPower() + 1);
					gameData.setCurrentScore(gameData.getCurrentScore()-intCostAtkPwr);
					AssetLoader.chairCollision.play();
					inputVector3.set(0, 0, 0);
				}
			}
			if (rectBuyHealth.contains(inputVector3.x, inputVector3.y)){
				if (gameData.getCurrentScore() >= intCostHealth && gameData.getHealth() != 100){
					gameData.setHealth(100);
					gameData.setCurrentScore(gameData.getCurrentScore()-intCostHealth);
					AssetLoader.lightning.play();
					inputVector3.set(0, 0, 0);
				}
			}
			if (rectBuyEnergy.contains(inputVector3.x, inputVector3.y)){
				if (gameData.getCurrentScore() >= intCostBeanz && gameData.getCansOfBeans() <= 2){
					gameData.setCansOfBeans(3);
					gameData.setCurrentScore(gameData.getCurrentScore()-intCostBeanz);
					AssetLoader.fart.play();
					inputVector3.set(0, 0, 0);
				}
			}
		}
	}
	
	@Override
	public void resize(int width, int height) {
	
		
	}

	@Override
	public void pause() {
	
		
	}

	@Override
	public void resume() {
	
		
	}

	@Override
	public void hide() {
	
		
	}

	@Override
	public void dispose() {
	batch.dispose();
	
	}
}
