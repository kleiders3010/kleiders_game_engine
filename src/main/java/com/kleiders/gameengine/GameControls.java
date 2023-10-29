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

import java.util.Map;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class GameControls {
	public static boolean cancelPlayerMovement;
	public static Map<String, Integer> keyMap = new HashMap<>();
	public static Map<Integer, Boolean> keyByNumberMap = new HashMap<>();
	private static boolean stored = false;
	private static final int[] keyCodes = {32, 39, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 59, 61, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 96, 161, 162, 256,
			257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 280, 281, 282, 283, 284, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 320, 321, 322, 323,
			324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 340, 341, 342, 343, 344, 345, 346, 347, 348};
	private static final String[] keyNames = {"SPACE", "APOSTROPHE", "COMMA", "MINUS", "PERIOD", "SLASH", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "SEMICOLON", "EQUAL", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
			"O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "LEFT_BRACKET", "BACKSLASH", "RIGHT_BRACKET", "GRAVE_ACCENT", "WORLD_1", "WORLD_2", "ESCAPE", "ENTER", "TAB", "BACKSPACE", "INSERT", "DELETE", "RIGHT", "LEFT", "DOWN", "UP",
			"PAGE_UP", "PAGE_DOWN", "HOME", "END", "CAPS_LOCK", "SCROLL_LOCK", "NUM_LOCK", "PRINT_SCREEN", "PAUSE", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12", "F13", "F14", "F15", "F16", "F17", "F18", "F19", "F20",
			"F21", "F22", "F23", "F24", "F25", "KP_0", "KP_1", "KP_2", "KP_3", "KP_4", "KP_5", "KP_6", "KP_7", "KP_8", "KP_9", "KP_DECIMAL", "KP_DIVIDE", "KP_MULTIPLY", "KP_SUBTRACT", "KP_ADD", "KP_ENTER", "KP_EQUAL", "LEFT_SHIFT", "LEFT_CONTROL",
			"LEFT_ALT", "LEFT_SUPER", "RIGHT_SHIFT", "RIGHT_CONTROL", "RIGHT_ALT", "RIGHT_SUPER", "MENU"};

	//Use https://www.glfw.org/docs/latest/group__keys.html as guidance if needed, otherwise use key names, like "W", "A", "S", "D"
	public static boolean isKeyPressed(String key) {
		if (keyMap.containsKey(key) && keyByNumberMap.containsKey(keyMap.get(key))) {
			return keyByNumberMap.get(keyMap.get(key));
		} else {
			System.out.println("Key " + key + " does not exist");
			return false;
		}
	}

	public static boolean isKeyPressedByNumber(Integer key) {
		if (keyByNumberMap.containsKey(key)) {
			return keyByNumberMap.get(key);
		} else {
			System.out.println("Key " + key + " does not exist");
			return false;
		}
	}

	//Movement Stopping and Map initialization
	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class MovementEventListener {
		@SubscribeEvent
		public static void onClientTick(MovementInputUpdateEvent event) {
			if (!stored) {
				stored = true;
				int keyIteration = 0;
				for (int keyCode : keyCodes) {
					keyMap.put(keyNames[keyIteration], keyCode);
					keyByNumberMap.put(keyCode, false);
					keyIteration += 1;
				}
			}
			if (cancelPlayerMovement && !GameCore.scenes.isEmpty()) {
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
	}

	//KeyDetection
	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(InputEvent.Key event) {
			int keyIteration = 0;
			for (int keyCode : keyCodes) {
				if (event.getKey() == keyCode && (event.getAction() == 1 || event.getAction() == 0)) {
					keyMap.put(keyNames[keyIteration], keyCode);
					keyByNumberMap.put(keyCode, event.getAction() == 1);
					keyIteration += 1;
				}
			}
		}
	}
}
