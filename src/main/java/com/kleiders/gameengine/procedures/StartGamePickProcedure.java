package com.kleiders.gameengine.procedures;

import net.minecraft.world.level.LevelAccessor;

import com.kleiders.gameengine.PlayerObject;
import com.kleiders.gameengine.KleidersGameEngineMod;
import com.kleiders.gameengine.GameScene;
import com.kleiders.gameengine.GameObject;

public class StartGamePickProcedure {
	public static void execute(LevelAccessor world) {
		KleidersGameEngineMod.LOGGER.info("testiado");
		if (world.isClientSide()) {
			int tileSize = 8;
			GameScene scene = new GameScene("scene_test_two");
			scene.active = true;
			new GameObject(0 * tileSize, 0 * tileSize, 8, 8, "rock", scene);
			new GameObject(1 * tileSize, 2 * tileSize, 8, 8, "rock", scene);
			new GameObject(1 * tileSize, 3 * tileSize, 8, 8, "rock", scene);
			new GameObject(6 * tileSize, 3 * tileSize, 8, 8, "pickaxe", scene);
			new PlayerObject(1 * tileSize, 1 * tileSize, 8, 8, "player", scene);
			scene.scale = 2f;
		}
	}
}
