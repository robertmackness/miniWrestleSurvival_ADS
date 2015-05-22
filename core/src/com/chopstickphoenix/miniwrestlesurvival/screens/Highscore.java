package com.chopstickphoenix.miniwrestlesurvival.screens;

import java.nio.ByteBuffer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.chopstickphoenix.miniwrestlesurvival.miniWrestleSurvival;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;
import com.chopstickphoenix.miniwrestlesurvival.utilities.GameData;

public class Highscore implements Screen {

	private Game wrestleRumble;
	private GameData gameData;
	private Sprite background;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private BitmapFont font = AssetLoader.fontLarge;
	private String highScoreString;
	private Vector3 vector3TouchInput;
	private Rectangle rectangleBackButton;
	private Rectangle rectangleShareButton;
	private Rectangle rectSwarm;
	private FileHandle fh;
	
	public Highscore (Game game, GameData gameData){
		this.wrestleRumble = game;
		this.gameData = gameData;
	}
	
	
	@Override
	public void show() {
	background = new Sprite(AssetLoader.backgroundHighScore);
	batch = new SpriteBatch();
	camera = new OrthographicCamera();
	camera.setToOrtho(false, 1280, 720);
	highScoreString = ""+gameData.getHighScore();
	rectangleBackButton = new Rectangle (1078, 0, 202, 90);
	rectangleShareButton = new Rectangle (535, 122, 202, 67);
	rectSwarm = new Rectangle(900, 334, 156, 156);
	vector3TouchInput = new Vector3(0,0,0);
	
	}

	@Override
	public void render(float delta) {
		//Graphics
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(background, 0, 0);
		font.draw(batch, highScoreString, 1280/2 - font.getBounds(highScoreString).width/2, 430);

		batch.end();
		
		camera.update();
		
		//Input
		if (Gdx.input.isTouched()){
			vector3TouchInput.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(vector3TouchInput);
		}
		if (!Gdx.input.isTouched()){
			if (rectangleBackButton.contains(vector3TouchInput.x, vector3TouchInput.y)){
				wrestleRumble.setScreen(new MainMenu(wrestleRumble, gameData));
			}
			
			else if (rectangleShareButton.contains(vector3TouchInput.x, vector3TouchInput.y)) {
				vector3TouchInput = new Vector3(0,0,0);
				miniWrestleSurvival.mackdaddyInterface.shareToast();
				try{

					fh = new FileHandle(Gdx.files.getExternalStoragePath() + "Wrestling" + ".png");
					System.out.println(fh + " - old deleted");
					fh.delete();
					Pixmap pixmap = getScreenshot(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
					PixmapIO.writePNG(fh, pixmap);
					System.out.println(fh + " - new created");
					pixmap.dispose();
				}catch (Exception e){
					System.out.println(e);
				}
				miniWrestleSurvival.mackdaddyInterface.share(fh.toString());
				return;
			}
			else if (rectSwarm.contains(vector3TouchInput.x, vector3TouchInput.y)) {
				miniWrestleSurvival.mackdaddyInterface.swarmConnect();				
			vector3TouchInput.set(0, 0, 0);
			}
		}
	}

	private static Pixmap getScreenshot(int x, int y, int w, int h, boolean yDown){
	    final Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(x, y, w, h);
	    ByteBuffer pixels = pixmap.getPixels();
	    final int numBytes = w * h * 4;
	    byte[] lines = new byte[numBytes];
	    if (yDown) {
	        // Flip the pixmap upside down
	        int numBytesPerLine = w * 4;
	        for (int i = 0; i < h; i++) {
	            pixels.position((h - i - 1) * numBytesPerLine);
	            pixels.get(lines, i * numBytesPerLine, numBytesPerLine);
	        }
	        pixels.clear();
	        pixels.put(lines);
	    }
	    return pixmap;
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
