package com.chopstickphoenix.miniwrestlesurvival.dynamicObjects.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.chopstickphoenix.miniwrestlesurvival.utilities.AssetLoader;

public class HandlerPunchNoises {

	private static float punchTimer = TimeUtils.nanoTime();
	private static float punchInterval;
	
	private HandlerPunchNoises(){
		//this is a static class
	}
	
	public static void punchNoise(){
		punchInterval = MathUtils.random(75000000, 250000000);
		
		if (TimeUtils.nanoTime() - punchTimer > punchInterval){
			AssetLoader.punchSound.play(1, MathUtils.random(0.51f, 1.9f), 0);
			punchTimer = TimeUtils.nanoTime();
		}
	}
	
}
