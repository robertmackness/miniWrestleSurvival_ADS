package com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.handlers;

import java.text.DecimalFormat;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.chopstickphoenix.miniwrestlesurvival.miniWrestleSurvival;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.MainCharacter.enumAnimationState;
import com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.MainCharacter.enumMainCharOrientation;
import com.chopstickphoenix.miniwrestlesurvival.screens.GameScreen;
import com.chopstickphoenix.miniwrestlesurvival.screens.GameScreen.enumGameState;
import com.chopstickphoenix.miniwrestlesurvival.screens.LevelComplete;
import com.chopstickphoenix.miniwrestlesurvival.screens.MainMenu;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;
import com.chopstickphoenix.miniwrestlesurvival.utilities.GameData;

public class HandlerGameScreenInputandHUD {

	private Game game;
	private GameData gameData;
	private GameScreen gameScreen;
	static final private int MAX_INPUT_FINGERS = 2-1; //pointers indexed in array, first pointer is 0. so fingers is n-1.
	private Vector3 vector3TouchInput;
	private DecimalFormat df = new DecimalFormat("#.##"); //used to round any numbers to 2 decimal points
	private Rectangle rectangleArrowLeftButtonBounds;
	private Rectangle rectangleArrowRightButtonBounds;
	private Rectangle rectangleThrowChairButtonBounds;
	private Rectangle rectangleFartButtonBounds;
	private Rectangle rectangleHUDJump;
	private Vector2 scoreDisplay;
	private Vector2 fartDisplay;
	private Vector2 vector2HUDOverlayPosition;
	private Sprite HUDOverlay;

	private boolean voiceEndLevelPlayed;
	private Sprite spriteLevelComplete;
	private Sprite spriteGameOver;
	private static int screenWidth = 1280;
	private static int screenHeight = 720;
	private OrthographicCamera camera;
	private SpriteBatch batch;

	
	public HandlerGameScreenInputandHUD(Game game, GameData gamedata, GameScreen gScreen){
		this.game = game;
		this.gameData = gamedata;
		this.gameScreen = gScreen;
		//Input Bounding Rectangles
		rectangleArrowLeftButtonBounds = 		new Rectangle(0, 0, 180, 168);
		rectangleArrowRightButtonBounds = 		new Rectangle(180, 00, 180, 168);
		rectangleThrowChairButtonBounds = 		new Rectangle(1140, 0, 120, 120);
		rectangleFartButtonBounds = 			new Rectangle(1020, 0, 120, 120);
		rectangleHUDJump = 						new Rectangle(1140, 140, 120,120);
		scoreDisplay = 							new Vector2(1110, 590);
		fartDisplay = 							new Vector2(960, 60);
		// HUD Sprites
		HUDOverlay = new Sprite(AssetLoader.hudOverlay);
		HUDOverlay.setPosition(0, 0);
		spriteLevelComplete = new Sprite(AssetLoader.levelComplete);
		spriteGameOver = new Sprite(AssetLoader.gameOver);
		spriteLevelComplete.setPosition(screenWidth/2-spriteLevelComplete.getRegionWidth()/2, screenHeight/2-spriteLevelComplete.getHeight()/2);
		spriteGameOver.setPosition(screenWidth/2-spriteGameOver.getRegionWidth()/2, screenHeight/2-spriteGameOver.getHeight()/2);
		//Other
		voiceEndLevelPlayed = false;
		//test - divorce HUD from gamescreen camera and coords
		camera = new OrthographicCamera();
		camera.setToOrtho(false, screenWidth, screenHeight);
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
	}

	public void drawHUD(){
		batch.begin(); //remove if test unsuccessufl

		HUDOverlay.draw(batch);
		AssetLoader.font.draw(batch, "" + gameData.getCurrentScore(), scoreDisplay.x, scoreDisplay.y);
		AssetLoader.font.draw(batch, "" + gameData.getCansOfBeans() + "/3", fartDisplay.x, fartDisplay.y);
		if(gameScreen.gameState == enumGameState.LEVELCOMPLETE){
			spriteLevelComplete.draw(batch);
		} else if (gameScreen.gameState == enumGameState.GAMEOVER){
			spriteGameOver.draw(batch);
		}
		batch.end(); //remove if test unssuccessful
	}


	public void handleInput() { 
		//instead of using an inputHandler or inputAdaptor we use polling for multiple touch points during the render() loop
		// this supports two thumbs gameplay, increase MAX_INPUT_FINGERS if required but do not foresee this happening
		for (int i = 0; i<= MAX_INPUT_FINGERS; i++){
			if (Gdx.input.isTouched(i)){ //check if index pointers 0 to 1 are touched
				vector3TouchInput = new Vector3(Gdx.input.getX(i), Gdx.input.getY(i), 0); //getting x and y of current indexed pointer and storing here
				camera.unproject(vector3TouchInput);
				//gameScreen.getCamera().unproject(vector3TouchInput); //translating them from screen co-ords to game co-ords, as the camera stretches things out

				if (rectangleThrowChairButtonBounds.contains(vector3TouchInput.x, vector3TouchInput.y)){
					gameScreen.getHandlerCHairs().throwChair();	
									}
				if (rectangleArrowLeftButtonBounds.contains(vector3TouchInput.x, vector3TouchInput.y)){
					gameData.getMainCharacter().setVelocity(-gameData.getMainCharacter().getSpeed());
					gameData.getMainCharacter().setOrientation(enumMainCharOrientation.LEFT);
					gameData.getMainCharacter().setAnimationState(enumAnimationState.RUNNING);					
				}
				if (rectangleArrowRightButtonBounds.contains(vector3TouchInput.x, vector3TouchInput.y)){
					gameData.getMainCharacter().setVelocity(gameData.getMainCharacter().getSpeed());
					gameData.getMainCharacter().setOrientation(enumMainCharOrientation.RIGHT);
					gameData.getMainCharacter().setAnimationState(enumAnimationState.RUNNING);	
				}
				if (rectangleFartButtonBounds.contains(vector3TouchInput.x, vector3TouchInput.y)){
					gameScreen.getHandlerFart().useFart(gameData.getMainCharacter().getPosition());
				}
				if (rectangleHUDJump.contains(vector3TouchInput.x, vector3TouchInput.y)){
					gameData.getMainCharacter().jump();
				}
			}
		}
	}
	
	

	public void handlePausedInput(){
		Rectangle rectLevelComplete = new Rectangle(screenWidth/2 - spriteLevelComplete.getWidth()/2,screenHeight/2-spriteLevelComplete.getHeight()/2, spriteLevelComplete.getWidth(),spriteLevelComplete.getHeight());
		Vector3 vector3PausedInput = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);	
		camera.unproject(vector3TouchInput);
		
		if(gameScreen.gameState == enumGameState.LEVELCOMPLETE){
			if(gameData.getHealth() == 100 && !voiceEndLevelPlayed){
				AssetLoader.voiceFlawlessVictory.play();
				voiceEndLevelPlayed = true;				
			}		
			if(gameData.getHealth() < 100 && !voiceEndLevelPlayed){
				int r = MathUtils.random(1, 2);
				if(r == 1)	AssetLoader.voiceGodlike.play();
				if(r == 2)	AssetLoader.voiceUnstoppable.play();
				voiceEndLevelPlayed = true;
			}		
			if (!Gdx.input.isTouched()){
				gameScreen.getCamera().unproject(vector3PausedInput);
				if(rectLevelComplete.contains(vector3PausedInput.x,vector3PausedInput.y)){
					game.setScreen(new LevelComplete(game, gameData));
				}
			}
		}
		
		if(gameScreen.gameState == enumGameState.GAMEOVER){		
			if(!voiceEndLevelPlayed){
				AssetLoader.voiceDenied.play();
				voiceEndLevelPlayed = true;
			}
			if (!Gdx.input.isTouched()){
				gameScreen.getCamera().unproject(vector3PausedInput);
				if(rectLevelComplete.contains(vector3PausedInput.x,vector3PausedInput.y)){
					gameData.resetALL();
					game.setScreen(new MainMenu(game, gameData));
					miniWrestleSurvival.mackdaddyInterface.showAdInterstitial();
				}
			}
		}
	}
}