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

public class PlayerObject extends GameObject {
	public PlayerObject(int x, int y, int width, int height, String type, GameObject parent) {
		super(x, y, width, height, type, parent);
		this.colliders.add("rock");
		this.colliders.add("pickaxe");
		this.speed = 0.05;
	}

	@Override
	public boolean canCollide() {
		return true;
	}

	@Override
	public void objectCollide(GameObject otherObject) {
		if (!(otherObject instanceof PickaxeObject) && otherObject.type == "pickaxe") {
			GameObject pickaxe = new PickaxeObject(8, 0, width, height, "pickaxe", this);
			pickaxe.visualHeight = 4;
			pickaxe.visualWidth = 4;
			pickaxe.visualYOffset = 3;
			otherObject.discard();
		}
	}

	@Override
	public void partialMove() {
		if (Controls.leftKey) {
			this.direction = "left";
		} else if (Controls.rightKey) {
			this.direction = "right";
		} else if (Controls.upKey) {
			this.direction = "up";
		} else if (Controls.downKey) {
			this.direction = "down";
		} else {
			this.direction = "";
		}
		super.partialMove();
	}
}
