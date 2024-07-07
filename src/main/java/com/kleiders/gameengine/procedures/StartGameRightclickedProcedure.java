package com.kleiders.gameengine.procedures;

import net.minecraft.world.level.LevelAccessor;

import com.kleiders.minigames.GrassObject;
import com.kleiders.gameengine.KleidersGameEngineMod;
import com.kleiders.gameengine.GameScene;
import com.kleiders.gameengine.GameObject;
import com.kleiders.gameengine.GameControls;

public class StartGameRightclickedProcedure {
	public static void execute(LevelAccessor world) {
		KleidersGameEngineMod.LOGGER.info("testiado");
		if (world.isClientSide()) {
			int tileSize = 8;
			GameScene scene = new GameScene("scene_test");
			scene.scale = 3f;
			GameControls.cancelPlayerMovement = true;
			scene.active = true;
			new GrassObject(0 * tileSize, 0 * tileSize, scene);
			for (int index0 = -20; index0 < 20; index0++) {
				new GameObject(index0 * (tileSize * 2), tileSize, 8, 8, "dirt_0", scene);
				new GameObject(-8 + (index0 * (tileSize * 2)), tileSize, 8, 8, "dirt_1", scene);
			}
		}
	}
}
