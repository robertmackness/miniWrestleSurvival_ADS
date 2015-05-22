package com.chopstickphoenix.miniwrestlesurvival.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.chopstickphoenix.miniwrestlesurvival.miniWrestleSurvival;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.MainCharacter;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.handlers.HandlerChairs;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.handlers.HandlerCollisions;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.handlers.HandlerEnemies;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.handlers.HandlerFarts;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.handlers.HandlerGameScreenInputandHUD;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.handlers.HandlerLightning;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;
import com.chopstickphoenix.miniwrestlesurvival.utilities.GameData;

public class GameScreen implements Screen {
	//Main class variables and utilities
	private Game wrestleRumble;
	private GameData gameData;
	//Sprites, Fonts and other assets
	private Sprite spriteBackground;
	private BitmapFont font = AssetLoader.font;
	//Camera and SpriteBatch
	private OrthographicCamera camera;
	private SpriteBatch batch;
	//MainCharacter Stuff
	private MainCharacter mainCharacter;
	//Gameplay States
	public static enum enumGameState {RUNNING, LEVELCOMPLETE, GAMEOVER};
	public enumGameState gameState;
	//Handlers
	private HandlerEnemies handlerEnemies;
	private HandlerChairs handlerChairs;
	private HandlerLightning handlerLightning;
	private HandlerFarts handlerFarts;
	//HUD
	private HandlerGameScreenInputandHUD handlerHUD;
	//Fixed Timestep limited to 60FPS to avoid spiral of death on the collision detection
	private float timeAccumulator;
	private float timeFrameRate = 1.0f / 60.0f;	
	//Switches and Timers


	//Constructor
	public GameScreen (Game game, GameData gameData){
		this.wrestleRumble = game;
		this.gameData = gameData;
		gameState = enumGameState.RUNNING;
		miniWrestleSurvival.mackdaddyInterface.loadAdInterstitial();
	}


	@Override
	public void show() {
		//Main class variables and utilities
		spriteBackground = new Sprite(AssetLoader.backgroundGameScreen);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		batch = new SpriteBatch();
		gameState = enumGameState.RUNNING;
		mainCharacter = gameData.getMainCharacter();
		//HUD
		handlerHUD = new HandlerGameScreenInputandHUD(wrestleRumble, gameData, this);
		//Lightning Objects and Behavior
		handlerLightning = new HandlerLightning(gameData);
		//Enemy Objects and Behaviour
		handlerEnemies = new HandlerEnemies(gameData);
		//Chair Objects and Behaviour
		handlerChairs = new HandlerChairs(gameData);
		//Fart stuff
		handlerFarts = new HandlerFarts(gameData);
	}

	@Override
	public void render(float delta) {
		//Encapsulate THE BELOW with a fixed timestep of 60fps		
		timeAccumulator += delta;
		if(timeAccumulator > timeFrameRate){
			if(gameState == enumGameState.RUNNING){
				//Input
				handlerHUD.handleInput();
				//Logic and updates
				handleUpdates();
				//Collision detection
				HandlerCollisions.handleCollisions(gameData, handlerLightning);
				//Graphics
				handleGraphics(delta);
				//Enemy Generation
				handlerEnemies.handleEnemySpawns();
				//TimeStep
				timeAccumulator -= timeFrameRate;
			}
		}
		//#################### see above comment about timestep #########
		if(gameState == enumGameState.LEVELCOMPLETE){
			handleGraphics(delta);
			handlerHUD.handlePausedInput();
			//Heads Up Display
		}
		if(gameState == enumGameState.GAMEOVER){
			handleGraphics(delta);
			handlerHUD.handlePausedInput();
			//Heads Up Display
		}
	}

	private void handleGraphics(float delta) {
		Gdx.gl.glClearColor(0.01f, 0.01f, 0.01f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		camera.position.set(mainCharacter.getRectangleCollision().x, mainCharacter.getRectangleCollision().y + 720/4, 0);
		camera.update();
		//Batch commands begin
		batch.begin();
		batch.draw(spriteBackground, 0, 0);
		handlerChairs.render(batch);
		handlerFarts.render(batch);
		mainCharacter.draw(batch, delta); // draw maincharacter after enemies so he gets 'swamped'
		handlerEnemies.render(batch, delta);
		handlerLightning.render(batch, delta);
		batch.end();
		handlerHUD.drawHUD(); //used to send (batch) as parameter
		//Batch commands end
		camera.update();
	}

	private void handleUpdates() {
		mainCharacter.update();
		handlerChairs.update();
		handlerEnemies.update();
		handlerLightning.update();
		handlerFarts.update();
		mainCharacter.updateHealthbar();
		//check to see if last enemy of the round has been killed
		if(handlerEnemies.getLastEnemySpawned() && gameData.getArrayEnemies().size == 0){
			gameState = enumGameState.LEVELCOMPLETE;
		}
		if(gameData.getHealth() <=0.0){
			gameState = enumGameState.GAMEOVER;
		}
	}

	//Getters
	public HandlerFarts getHandlerFart(){
		return handlerFarts;
	}
	public HandlerChairs getHandlerCHairs(){
		return handlerChairs;
	}
	public OrthographicCamera getCamera(){
		return camera;
	}

	//Inherited from superclass
	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {
		batch.dispose();

	}

}
