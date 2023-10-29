/**
 * The code of this mod element is always locked.
 *
 * You can register new events in this class too.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser -> New... and make sure to make the class
 * outside com.kleiders.gameengine as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
 *
 * This class will be added in the mod root package.
*/
package com.kleiders.gameengine;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import java.util.List;
import java.util.ArrayList;

public class GameScene {
	public long desiredFPS = 60; // Desired frame rate (e.g., 60 FPS)
	public List<GameObject> objects = new ArrayList<>();
	public List<GameObject> scheduledDiscard = new ArrayList<>();
	public List<GameObject> scheduledAddition = new ArrayList<>();
	public long currentFPS;
	public float scale = 1f;
	public String sceneId;
	public boolean active;
	private int frames;
	private long nanosecondsPerFrame = 1000000000 / desiredFPS; // Nanoseconds per frame
	private long lastTime = System.nanoTime();
	private long accumulatedTime = 0;
	private long currentTime;
	private long worldTime;

	public GameScene(String sceneId) {
		this.sceneId = sceneId;
		for (GameScene scene : GameCore.scenes) {
			if (scene.sceneId == sceneId) {
				GameCore.scheduledDiscard.add(scene);
			}
		}
		GameCore.scheduledAddition.add(this);
	}

	public void handleScene(GuiGraphics graphics) {
		boolean shouldTick = false;
		for (GameObject added : scheduledAddition) {
			objects.add(added);
		}
		for (GameObject discarded : scheduledDiscard) {
			objects.remove(discarded);
		}
		scheduledDiscard = new ArrayList<>();
		scheduledAddition = new ArrayList<>();
		currentTime = System.nanoTime();
		long elapsedTime = currentTime - lastTime;
		accumulatedTime += elapsedTime;
		// Process as many frames as needed to catch up with the accumulated time
		while (accumulatedTime >= nanosecondsPerFrame) {
			this.frames++;
			// Your game logic here
			if (Minecraft.getInstance().level.dayTime() != worldTime) {
				currentFPS = frames;
				frames = 0;
				worldTime = Minecraft.getInstance().level.dayTime();
			}
			shouldTick = true;
			accumulatedTime -= nanosecondsPerFrame;
		}
		lastTime = currentTime;
		for (GameObject object : objects) {
			object.graphics = graphics;
			object.render();
			if (shouldTick) {
				object.rawTick();
			}
		}
	}
}
