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

public class PickaxeObject extends GameObject {
	public PickaxeObject(int x, int y, int width, int height, String type, GameObject parent) {
		super(x, y, width, height, type, parent);
		this.colliders.add("rock");
	}

	@Override
	public boolean canCollide() {
		return true;
	}

	@Override
	public void rawTick() {
		super.rawTick();
	}

	@Override
	public void objectCollide(GameObject otherObject) {
		if (otherObject.type == "rock") {
			otherObject.discard();
		}
		System.out.println(GameObject.scheduledDiscard);
	}

}
