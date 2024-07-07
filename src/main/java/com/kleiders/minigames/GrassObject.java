package com.kleiders.minigames;

import net.minecraft.resources.ResourceLocation;

import com.kleiders.gameengine.GameScene;
import com.kleiders.gameengine.GameObject;

public class GrassObject extends GameObject {
	public GrassObject(float x, float y, float width, float height, String type, GameScene scene) {
		super(x, y, width, height, type, scene);
		this.texture = new ResourceLocation("kleiders_game_engine:textures/screens/grass_0.png");
		this.drawLate = 1;
	}

	public GrassObject(float x, float y, GameScene scene) {
		this(x, y, 8, 8, "grass", scene);
	}

	private int textureDuration = 0;
	private int currentTexture = 0;
	private int maxTexture = 1;

	@Override
	public void rawTick() {
		textureDuration += 1;
		if (textureDuration >= 15) {
			textureDuration = 0;
			currentTexture += 1;
			if (currentTexture > maxTexture)
				currentTexture = 0;
			texture = new ResourceLocation("kleiders_game_engine:textures/screens/grass_" + currentTexture + ".png");
		}
		super.rawTick();
	}
}
