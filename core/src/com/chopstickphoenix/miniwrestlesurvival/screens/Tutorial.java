package com.chopstickphoenix.miniwrestlesurvival.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;
import com.chopstickphoenix.miniwrestlesurvival.utilities.GameData;

public class Tutorial implements Screen {

	private Game wrestleRumble;
	private GameData gameData;
	private Sprite sprite;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private boolean boolInputClear;
	
	public Tutorial (Game game, GameData gameData){
		this.wrestleRumble = game;
		this.gameData = gameData;
	}
	@Override
	public void show() {
		sprite = new Sprite(AssetLoader.backgroundTutorial);
		camera = new OrthographicCamera();
		camera. setToOrtho(false, 1280, 720);
		boolInputClear = false;
		batch =  new SpriteBatch();
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(sprite, 0, 0);
		batch.end();
		camera.update();
		handleInput();
		
	}

	
	
	private void handleInput() {
		if (!boolInputClear){	
			if (!Gdx.input.isTouched()){
				boolInputClear = true;
			}
		}
		else if (boolInputClear){
			if (Gdx.input.isTouched()){
				wrestleRumble.setScreen(new GameScreen(wrestleRumble, gameData));
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
		
		
	}

}
