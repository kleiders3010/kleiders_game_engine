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

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.GuiGraphics;

import java.util.List;
import java.util.ArrayList;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class GameCore {
	public static List<GameScene> scenes = new ArrayList<>();
	public static List<GameScene> scheduledDiscard = new ArrayList<>();
	public static List<GameScene> scheduledAddition = new ArrayList<>();

	//	public GameCore() {
	//	}
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(RenderGuiEvent.Pre event) {
		GameCore.handleGameScenes(event.getGuiGraphics());
	}

	public static void handleGameScenes(GuiGraphics graphics) {
		for (GameScene added : scheduledAddition) {
			scenes.add(added);
		}
		for (GameScene discarded : scheduledDiscard) {
			scenes.remove(discarded);
		}
		scheduledDiscard = new ArrayList<>();
		scheduledAddition = new ArrayList<>();
		for (GameScene scene : scenes) {
			if (scene.active) {
				scene.handleScene(graphics);
			}
		}
	}
}
