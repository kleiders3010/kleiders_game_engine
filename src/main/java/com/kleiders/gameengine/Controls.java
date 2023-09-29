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
import net.minecraftforge.client.event.MovementInputUpdateEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class Controls {
	public static String playerDirection = "";
	public static boolean leftKey = false;
	public static boolean rightKey = false;
	public static boolean upKey = false;
	public static boolean downKey = false;

	//Movement Stopping
	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class MovementEventListener {
		@SubscribeEvent
		public static void onClientTick(MovementInputUpdateEvent event) {
			event.getInput().leftImpulse = 0;
			event.getInput().forwardImpulse = 0;
			event.getInput().up = false;
			event.getInput().down = false;
			event.getInput().left = false;
			event.getInput().right = false;
			event.getInput().jumping = false;
			event.getInput().shiftKeyDown = false;
		}
	}

	//KeyDetection
	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(InputEvent.Key event) {
			if (event.getKey() == 65 && event.getAction() == 1) {
				leftKey = true;
			}
			if (event.getKey() == 68 && event.getAction() == 1) {
				rightKey = true;
			}
			if (event.getKey() == 87 && event.getAction() == 1) {
				upKey = true;
			}
			if (event.getKey() == 83 && event.getAction() == 1) {
				downKey = true;
			}
			if (event.getKey() == 65 && event.getAction() == 0) {
				leftKey = false;
			}
			if (event.getKey() == 68 && event.getAction() == 0) {
				rightKey = false;
			}
			if (event.getKey() == 87 && event.getAction() == 0) {
				upKey = false;
			}
			if (event.getKey() == 83 && event.getAction() == 0) {
				downKey = false;
			}
		}
	}
}
