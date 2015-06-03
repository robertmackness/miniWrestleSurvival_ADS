package com.chopstickphoenix.miniwrestlesurvival.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	//Backgrounds
	static public TextureRegion backgroundMainMenu, backgroundGameScreen, backgroundLevelComplete, backgroundHighScore, backgroundTutorial, backgroundArena;
	//Misc
	static public BitmapFont font, fontLarge;
	//HUD
	static public TextureRegion levelComplete, gameOver, buyButton;
	static public TextureRegion combo1, combo2, combo3, combo4, combo5, combo6, combo7, combo8, combo9, combo10;
	static public TextureRegion hudOverlay;
	static public TextureAtlas combo;
	//Dynamic Game Objects
	static public TextureRegion chairBullet, explosion, lightning1, lightning2, lightning3, piano,
								powerupHealth, powerupChair, powerupCash, powerupFarts;
	static public TextureAtlas explosionSmall, explosionLarge, animationDollar;
	//Main Character Assets
	static public TextureAtlas mainCharacterRunningRight, mainCharacterThrowChairRight,
									mainCharacterThrowChairLeft, mainCharacterRunningLeft;
	static public TextureRegion mainCharacterStationaryRight, mainCharacterStationaryLeft, mainCharacterFartRight, mainCharacterFartLeft;
	//Enemy Assets
	static public TextureAtlas e1Climb, e1RightFight, e1LeftFight, e1RightRun, e1LeftRun,
							   e2Climb, e2RightFight, e2LeftFight, e2RightRun, e2LeftRun,
							   e3Climb, e3RightFight, e3LeftFight, e3RightRun, e3LeftRun,
							   e4Climb, e4RightFight, e4LeftFight, e4RightRun, e4LeftRun,
							   e5Climb, e5RightFight, e5LeftFight, e5RightRun, e5LeftRun,
							   e6Climb, e6RightFight, e6LeftFight, e6RightRun, e6LeftRun,
							   e7Climb, e7RightFight, e7LeftFight, e7RightRun, e7LeftRun;
	//Healthbar
	static public Sprite healthBar0, healthBar1, healthBar5, healthBar10, healthBar15, healthBar20, healthBar25, healthBar30,
								healthBar35, healthBar40, healthBar45, healthBar50, healthBar55, healthBar60, healthBar65, healthBar70,
								healthBar75, healthBar80, healthBar85, healthBar90, healthBar95, healthBar100;

	//Sounds
	static public Sound throwChair, chairCollision, fart, footStep, voiceDenied, voiceFlawlessVictory, coin,
						voiceGodlike, voiceHeadshot, voiceUnstoppable, lightning, jump, cashRegister, explosionLargeSound, punchSound;
	static public Music musicLoop;
	
	static public void load() {
		//Scenery
		backgroundGameScreen = new TextureRegion( new Texture (Gdx.files.internal("backgrounds/wrestleRumbleGameScreen.png")));
		backgroundHighScore = new TextureRegion( new Texture (Gdx.files.internal("backgrounds/wrestleRumbleHighScore.png")));
		backgroundMainMenu = new TextureRegion(new Texture(Gdx.files.internal("backgrounds/wrestleRumbleMainMenu.png")));
		backgroundLevelComplete = new TextureRegion(new Texture(Gdx.files.internal("backgrounds/wrestleRumbleLevelUpScreen.png")));
		backgroundTutorial  = new TextureRegion(new Texture(Gdx.files.internal("backgrounds/wrestleRumbleTutorial.png")));
		backgroundArena = new TextureRegion(new Texture(Gdx.files.internal("backgrounds/wrestlingRingUpgradeBackGround.png")));
		//Misc
		font = new BitmapFont(Gdx.files.internal("Fonts/font.fnt"));
		fontLarge = new BitmapFont(Gdx.files.internal("Fonts/fontLarge.fnt"));
		//HUD
		hudOverlay = new TextureRegion(new Texture (Gdx.files.internal("hud/HUDOverlay.png")));
		levelComplete = new TextureRegion(new Texture (Gdx.files.internal("hud/levelComplete.png")));
		gameOver = new TextureRegion(new Texture (Gdx.files.internal("hud/gameOver.png")));
		combo1 = new TextureRegion (new Texture (Gdx.files.internal("hud/combo1.png")));
		combo2 = new TextureRegion (new Texture (Gdx.files.internal("hud/combo2.png")));
		combo3 = new TextureRegion (new Texture (Gdx.files.internal("hud/combo3.png")));
		combo4 = new TextureRegion (new Texture (Gdx.files.internal("hud/combo4.png")));
		combo5 = new TextureRegion (new Texture (Gdx.files.internal("hud/combo5.png")));
		combo6 = new TextureRegion (new Texture (Gdx.files.internal("hud/combo6.png")));
		combo7 = new TextureRegion (new Texture (Gdx.files.internal("hud/combo7.png")));
		combo8 = new TextureRegion (new Texture (Gdx.files.internal("hud/combo8.png")));
		combo9 = new TextureRegion (new Texture (Gdx.files.internal("hud/combo9.png")));
		combo10 = new TextureRegion (new Texture (Gdx.files.internal("hud/combo10.png")));
		combo = new TextureAtlas(Gdx.files.internal("hud/comboAnim.pack"));
		buyButton = new TextureRegion (new Texture (Gdx.files.internal("hud/buyButton.png")));
		//Dynamic Game Objects
		explosion = new TextureRegion(new Texture(Gdx.files.internal("dynamicObjects/explosion.png")));
		chairBullet = new TextureRegion(new Texture(Gdx.files.internal("dynamicObjects/chair.png")));
		piano = new TextureRegion(new Texture(Gdx.files.internal("dynamicObjects/piano.png")));
		lightning1 = new TextureRegion(new Texture(Gdx.files.internal("dynamicObjects/lightning1.png")));
		lightning2 = new TextureRegion(new Texture(Gdx.files.internal("dynamicObjects/lightning2.png")));
		lightning3 = new TextureRegion(new Texture(Gdx.files.internal("dynamicObjects/lightning3.png")));
		explosionSmall = new TextureAtlas(Gdx.files.internal("animations/explosions/explosionSmall.pack"));
		explosionLarge = new TextureAtlas(Gdx.files.internal("animations/explosions/explosionLarge.pack"));
		animationDollar = new TextureAtlas(Gdx.files.internal("animations/dollar/animationDollar.pack"));
		powerupHealth = new TextureRegion(new Texture(Gdx.files.internal("dynamicObjects/powerupHealth.png")));
		powerupChair = new TextureRegion(new Texture(Gdx.files.internal("dynamicObjects/powerupChairs.png")));
		powerupCash = new TextureRegion(new Texture(Gdx.files.internal("dynamicObjects/powerupCash.png")));
		powerupFarts = new TextureRegion(new Texture(Gdx.files.internal("dynamicObjects/powerupFarts.png")));
		//Main Character Assets
		mainCharacterRunningLeft = new TextureAtlas(Gdx.files.internal("animations/mainCharacter/mainLeftRun.pack"));
		mainCharacterRunningRight = new TextureAtlas(Gdx.files.internal("animations/mainCharacter/mainRightRun.pack"));
		mainCharacterStationaryLeft = new TextureRegion (new Texture (Gdx.files.internal("animations/mainCharacter/mainLeftStationary.png")));
		mainCharacterStationaryRight = new TextureRegion (new Texture (Gdx.files.internal("animations/mainCharacter/mainRightStationary.png")));
		mainCharacterThrowChairRight = new TextureAtlas (Gdx.files.internal("animations/mainCharacter/mainRightThrow.pack"));;
		mainCharacterThrowChairLeft = new TextureAtlas (Gdx.files.internal("animations/mainCharacter/mainLeftThrow.pack"));;
		mainCharacterFartRight = new TextureRegion (new Texture (Gdx.files.internal("animations/mainCharacter/mainRightFart.png")));
		mainCharacterFartLeft = new TextureRegion (new Texture (Gdx.files.internal("animations/mainCharacter/mainLeftFart.png")));
		//Sounds
		throwChair = Gdx.audio.newSound(Gdx.files.internal("sounds/throwChair.wav"));
		chairCollision = Gdx.audio.newSound(Gdx.files.internal("sounds/chairCollision.wav"));
		fart = Gdx.audio.newSound(Gdx.files.internal("sounds/fart.wav"));
		footStep= Gdx.audio.newSound(Gdx.files.internal("sounds/footstep.wav"));
		musicLoop = Gdx.audio.newMusic(Gdx.files.internal("sounds/musicLoop.mp3"));
		voiceDenied = Gdx.audio.newSound(Gdx.files.internal("sounds/voiceDenied.mp3"));
		voiceFlawlessVictory = Gdx.audio.newSound(Gdx.files.internal("sounds/voiceGodlike.mp3")); 
		voiceGodlike = Gdx.audio.newSound(Gdx.files.internal("sounds/voiceGodlike.mp3"));
		voiceHeadshot = Gdx.audio.newSound(Gdx.files.internal("sounds/voiceHeadshot.mp3"));
		voiceUnstoppable = Gdx.audio.newSound(Gdx.files.internal("sounds/voiceGodlike.mp3"));
		lightning = Gdx.audio.newSound(Gdx.files.internal("sounds/lightning.mp3"));
		jump = Gdx.audio.newSound(Gdx.files.internal("sounds/jump.wav"));
		cashRegister = Gdx.audio.newSound(Gdx.files.internal("sounds/cashRegister.mp3"));
		explosionLargeSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosionLarge.mp3"));
		punchSound = Gdx.audio.newSound(Gdx.files.internal("sounds/comboSound.mp3"));
		coin = Gdx.audio.newSound(Gdx.files.internal("sounds/coin.wav"));
		//Enemies
		e1Climb = new TextureAtlas(Gdx.files.internal("animations/enemy1/e1Climb.pack"));
		e1RightFight = new TextureAtlas(Gdx.files.internal("animations/enemy1/e1RightFight.pack"));
		e1LeftFight = new TextureAtlas(Gdx.files.internal("animations/enemy1/e1LeftFight.pack"));
		e1RightRun = new TextureAtlas(Gdx.files.internal("animations/enemy1/e1RightRun.pack"));
		e1LeftRun = new TextureAtlas(Gdx.files.internal("animations/enemy1/e1LeftRun.pack"));
		
		e2Climb = new TextureAtlas(Gdx.files.internal("animations/enemy2/e2Climb.pack"));
		e2RightFight = new TextureAtlas(Gdx.files.internal("animations/enemy2/e2RightFight.pack"));
		e2LeftFight = new TextureAtlas(Gdx.files.internal("animations/enemy2/e2LeftFight.pack"));
		e2RightRun = new TextureAtlas(Gdx.files.internal("animations/enemy2/e2RightRun.pack"));
		e2LeftRun = new TextureAtlas(Gdx.files.internal("animations/enemy2/e2LeftRun.pack"));
		
		e3Climb = new TextureAtlas(Gdx.files.internal("animations/enemy3/e3Climb.pack"));
		e3RightFight = new TextureAtlas(Gdx.files.internal("animations/enemy3/e3RightFight.pack"));
		e3LeftFight = new TextureAtlas(Gdx.files.internal("animations/enemy3/e3LeftFight.pack"));
		e3RightRun = new TextureAtlas(Gdx.files.internal("animations/enemy3/e3RightRun.pack"));
		e3LeftRun = new TextureAtlas(Gdx.files.internal("animations/enemy3/e3LeftRun.pack"));
				
		e4Climb = new TextureAtlas(Gdx.files.internal("animations/enemy4/e4Climb.pack"));
		e4RightFight = new TextureAtlas(Gdx.files.internal("animations/enemy4/e4RightFight.pack"));
		e4LeftFight = new TextureAtlas(Gdx.files.internal("animations/enemy4/e4LeftFight.pack"));
		e4RightRun = new TextureAtlas(Gdx.files.internal("animations/enemy4/e4RightRun.pack"));
		e4LeftRun = new TextureAtlas(Gdx.files.internal("animations/enemy4/e4LeftRun.pack"));
		
		e5Climb = new TextureAtlas(Gdx.files.internal("animations/enemy5/e5Climb.pack"));
		e5RightFight = new TextureAtlas(Gdx.files.internal("animations/enemy5/e5RightFight.pack"));
		e5LeftFight = new TextureAtlas(Gdx.files.internal("animations/enemy5/e5LeftFight.pack"));
		e5RightRun = new TextureAtlas(Gdx.files.internal("animations/enemy5/e5RightRun.pack"));
		e5LeftRun = new TextureAtlas(Gdx.files.internal("animations/enemy5/e5LeftRun.pack"));
		
		e6Climb = new TextureAtlas(Gdx.files.internal("animations/enemy6/e6Climb.pack"));
		e6RightFight = new TextureAtlas(Gdx.files.internal("animations/enemy6/e6RightFight.pack"));
		e6LeftFight = new TextureAtlas(Gdx.files.internal("animations/enemy6/e6LeftFight.pack"));
		e6RightRun = new TextureAtlas(Gdx.files.internal("animations/enemy6/e6RightRun.pack"));
		e6LeftRun = new TextureAtlas(Gdx.files.internal("animations/enemy6/e6LeftRun.pack"));
		
		e7Climb = new TextureAtlas(Gdx.files.internal("animations/enemy7/e7Climb.pack"));
		e7RightFight = new TextureAtlas(Gdx.files.internal("animations/enemy7/e7RightFight.pack"));
		e7LeftFight = new TextureAtlas(Gdx.files.internal("animations/enemy7/e7LeftFight.pack"));
		e7RightRun = new TextureAtlas(Gdx.files.internal("animations/enemy7/e7RightRun.pack"));
		e7LeftRun = new TextureAtlas(Gdx.files.internal("animations/enemy7/e7LeftRun.pack"));
		
		//Healthbar
		healthBar0 = new Sprite (new TextureRegion(new Texture(Gdx.files.internal("healthbar/healthBar0.png"))));
		healthBar1 = new Sprite (new TextureRegion(new Texture(Gdx.files.internal("healthbar/healthBar1.png"))));
		healthBar5 = new Sprite (new TextureRegion(new Texture(Gdx.files.internal("healthbar/healthBar5.png"))));
		healthBar10 = new Sprite (new TextureRegion(new Texture(Gdx.files.internal("healthbar/healthBar10.png"))));
		healthBar15 = new Sprite (new TextureRegion(new Texture(Gdx.files.internal("healthbar/healthBar15.png"))));
		healthBar20 = new Sprite (new TextureRegion(new Texture(Gdx.files.internal("healthbar/healthBar20.png"))));
		healthBar25 = new Sprite (new TextureRegion(new Texture(Gdx.files.internal("healthbar/healthBar25.png"))));
		healthBar30 = new Sprite (new TextureRegion(new Texture(Gdx.files.internal("healthbar/healthBar30.png"))));
		healthBar35 = new Sprite (new TextureRegion(new Texture(Gdx.files.internal("healthbar/healthBar35.png"))));
		healthBar40 = new Sprite (new TextureRegion(new Texture(Gdx.files.internal("healthbar/healthBar40.png"))));
		healthBar45 = new Sprite (new TextureRegion(new Texture(Gdx.files.internal("healthbar/healthBar45.png"))));
		healthBar50 = new Sprite (new TextureRegion(new Texture(Gdx.files.internal("healthbar/healthBar50.png"))));
		healthBar55 = new Sprite (new TextureRegion(new Texture(Gdx.files.internal("healthbar/healthBar55.png"))));
		healthBar60 = new Sprite (new TextureRegion(new Texture(Gdx.files.internal("healthbar/healthBar60.png"))));
		healthBar65 = new Sprite (new TextureRegion(new Texture(Gdx.files.internal("healthbar/healthBar65.png"))));
		healthBar70 = new Sprite (new TextureRegion(new Texture(Gdx.files.internal("healthbar/healthBar70.png"))));
		healthBar75 = new Sprite (new TextureRegion(new Texture(Gdx.files.internal("healthbar/healthBar75.png"))));
		healthBar80 = new Sprite (new TextureRegion(new Texture(Gdx.files.internal("healthbar/healthBar80.png"))));
		healthBar85 = new Sprite (new TextureRegion(new Texture(Gdx.files.internal("healthbar/healthBar85.png"))));
		healthBar90 = new Sprite (new TextureRegion(new Texture(Gdx.files.internal("healthbar/healthBar90.png"))));
		healthBar95 = new Sprite (new TextureRegion(new Texture(Gdx.files.internal("healthbar/healthBar95.png"))));
		healthBar100 = new Sprite (new TextureRegion(new Texture(Gdx.files.internal("healthbar/healthBar100.png"))));
	}

	public static void dispose() {
	}

}
