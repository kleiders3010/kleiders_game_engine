package com.kleiders.gameengine.procedures;

import net.minecraft.world.level.LevelAccessor;

import com.kleiders.minigames.RunnerScene;
import com.kleiders.gameengine.KleidersGameEngineMod;
import com.kleiders.gameengine.GameScene;

public class StartGameRightclickedProcedure {
	public static void execute(LevelAccessor world) {
		KleidersGameEngineMod.LOGGER.info("testiado");
		if (world.isClientSide()) {
			GameScene scene = new RunnerScene();
		}
	}
}
