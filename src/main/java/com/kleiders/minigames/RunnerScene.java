package com.kleiders.minigames;

import com.kleiders.gameengine.GameScene;
import com.kleiders.gameengine.GameObject;
import com.kleiders.gameengine.GameControls;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

import java.util.ArrayList;
import java.util.Comparator;

public class RunnerScene extends GameScene {
	public RunnerScene() {
		super("runner_game");
		int tileSize = 8;
		this.scale = 3f;
		GameControls.cancelPlayerMovement = true;
		new RunnerPlayerObject(-8.5f * tileSize, -1.1f * tileSize, 16, 16, this);
		new GrassObject(0 * tileSize, 0 * tileSize, this);
		//Roof
		for (int index1 = -10; index1 < -6; index1++) {
			for (int index0 = -15; index0 < 15; index0++) {
				if (index1 % 2 == 0) {
					new FloorObject(-8 + (index0 * (tileSize * 2)), tileSize * index1, 8, 8, "nograss_dirt_0", this);
					new FloorObject((index0 * (tileSize * 2)), tileSize * index1, 8, 8, "nograss_dirt_1", this);
				} else {
					new FloorObject((index0 * (tileSize * 2)), tileSize * index1, 8, 8, "nograss_dirt_0", this);
					new FloorObject(-8 + (index0 * (tileSize * 2)), tileSize * index1, 8, 8, "nograss_dirt_1", this);
				}
			}
		}
		//Roof End
		//Middle
		for (int index1 = -6; index1 < 1; index1++) {
			for (int index0 = -15; index0 < 15; index0++) {
				if (index1 % 2 == 0) {
					new GameObject(-8 + (index0 * (tileSize * 2)), tileSize * index1, 8, 8, "blank", this);
					new GameObject((index0 * (tileSize * 2)), tileSize * index1, 8, 8, "blank", this);
				} else {
					new GameObject((index0 * (tileSize * 2)), tileSize * index1, 8, 8, "blank", this);
					new GameObject(-8 + (index0 * (tileSize * 2)), tileSize * index1, 8, 8, "blank", this);
				}
			}
		}
		//Middle End
		//Floor
		for (int index0 = -15; index0 < 15; index0++) {
			new FloorObject(index0 * (tileSize * 2), tileSize, 8, 8, "dirt_0", this);
			new FloorObject(-8 + (index0 * (tileSize * 2)), tileSize, 8, 8, "dirt_1", this);
		}
		for (int index1 = 2; index1 < 6; index1++) {
			for (int index0 = -15; index0 < 15; index0++) {
				if (index1 % 2 == 0) {
					new FloorObject(-8 + (index0 * (tileSize * 2)), tileSize * index1, 8, 8, "nograss_dirt_0", this);
					new FloorObject((index0 * (tileSize * 2)), tileSize * index1, 8, 8, "nograss_dirt_1", this);
				} else {
					new FloorObject((index0 * (tileSize * 2)), tileSize * index1, 8, 8, "nograss_dirt_0", this);
					new FloorObject(-8 + (index0 * (tileSize * 2)), tileSize * index1, 8, 8, "nograss_dirt_1", this);
				}
			}
		}
		//Floor End
		this.active = true;
	}
}
