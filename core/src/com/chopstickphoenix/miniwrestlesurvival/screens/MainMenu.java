package com.chopstickphoenix.miniwrestlesurvival.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;
import com.chopstickphoenix.miniwrestlesurvival.utilities.GameData;

public class MainMenu implements Screen {

	private Game wrestleRumble;
	private GameData gameData;
	private Sprite spriteBackground;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Rectangle rectanglePlayButton;
	private Rectangle rectangleHighScore;
	private Vector3 vector3TouchInput;

	public MainMenu (Game game, GameData gameData){
		this.wrestleRumble = game;
		this.gameData = gameData;
	}

	@Override
	public void show() {
		spriteBackground = new Sprite(AssetLoader.backgroundMainMenu);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		batch = new SpriteBatch();

		rectanglePlayButton = new Rectangle(556, 287, 165, 97);
		rectangleHighScore = new Rectangle(462, 179, 351, 81);
		vector3TouchInput = new Vector3(0, 0, 0);

	}

	@Override
	public void render(float delta) {
		//Graphics
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(spriteBackground, 0, 0);
		batch.end();
		camera.update();

		//Input
		if (Gdx.input.isTouched()){
			vector3TouchInput.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(vector3TouchInput);
		}
		
		if(!Gdx.input.isTouched()){
			if (rectanglePlayButton.contains(vector3TouchInput.x, vector3TouchInput.y)){
				wrestleRumble.setScreen(new Tutorial(wrestleRumble, gameData));
			}
			if (rectangleHighScore.contains(vector3TouchInput.x, vector3TouchInput.y)){
				wrestleRumble.setScreen(new Highscore(wrestleRumble, gameData));
			}
		};
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
