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
	public PlayerObject(int x, int y, int width, int height, String type, GameScene scene) {
		super(x, y, width, height, type, scene);
		this.colliders.add("rock");
		this.colliders.add("pickaxe");
		this.speed = 1;
	}

	@Override
	public boolean canCollide() {
		return true;
	}

	@Override
	public void objectCollide(GameObject otherObject) {
		if (!(otherObject instanceof PickaxeObject) && otherObject.type == "pickaxe") {
			GameObject pickaxe = new PickaxeObject(8, 0, width, height, "pickaxe", this.scene);
			pickaxe.setParent(this);
			pickaxe.visualHeight = 4;
			pickaxe.visualWidth = 4;
			pickaxe.visualYOffset = 3;
			otherObject.discard();
		}
	}

	@Override
	public void move() {
		if (GameControls.isKeyPressed("A")) {
			this.setVelocityX(-speed);
			this.setVelocityY(0);
		} else if (GameControls.isKeyPressed("D")) {
			this.setVelocityX(speed);
			this.setVelocityY(0);
		} else if (GameControls.isKeyPressed("W")) {
			this.setVelocityX(0);
			this.setVelocityY(-speed);
		} else if (GameControls.isKeyPressed("S")) {
			this.setVelocityX(0);
			this.setVelocityY(speed);
		} else {
			this.setVelocityX(0);
			this.setVelocityY(0);
		}
		super.move();
	}
}
