package com.chopstickphoenix.miniwrestlesurvival;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.chopstickphoenix.miniwrestlesurvival.screens.MainMenu;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;
import com.chopstickphoenix.miniwrestlesurvival.utilities.GameData;
import com.chopstickphoenix.miniwrestlesurvival.utilities.MackdaddyInterface;

public class miniWrestleSurvival extends Game {
	private GameData gameData;
	private Music musicLoop;
	public static MackdaddyInterface mackdaddyInterface;
	
	public miniWrestleSurvival (MackdaddyInterface mackdaddyInterface){
		this.mackdaddyInterface = mackdaddyInterface;
	}
	
	@Override
	public void create () {
		AssetLoader.load();
		gameData = new GameData();
		setScreen(new MainMenu(this, gameData));
		musicLoop = AssetLoader.musicLoop;
		musicLoop.setLooping(true);
		musicLoop.play();
	}

	@Override
	public void render () {
		super.render();
		AssetLoader.dispose();
	}
}
