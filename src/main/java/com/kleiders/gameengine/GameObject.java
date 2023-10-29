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

import org.checkerframework.checker.units.qual.h;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import java.util.List;
import java.util.ArrayList;

public class GameObject {
	public GuiGraphics graphics;
	public List<GameObject> objectsCollided = new ArrayList<>();
	public List<String> colliders = new ArrayList<>();
	public int width, height, visualWidth, visualHeight, visualXOffset, visualYOffset = 0;
	public String type;
	public float scale = 1;
	public double x, y, oldX, oldY, velocityX, velocityY, speed;
	private double tempMoveRepeats;
	public double cycles = 0;
	public GameObject parent = null;
	public GameScene scene;
	public ResourceLocation texture;

	public GameObject(int x, int y, int width, int height, String type, GameScene scene) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.visualWidth = width;
		this.visualHeight = height;
		this.type = type;
		this.scene = scene;
		this.scene.scheduledAddition.add(this);
		this.texture = new ResourceLocation("kleiders_game_engine:textures/screens/" + type + ".png");
	}

	//Handles rendering and movement
	public void rawTick() {
		oldX = this.getX();
		oldY = this.getY();
		this.move();
		this.collide();
	}

	public void render() {
		float finalScale = this.scene.scale * this.scale * this.getGuiScale();
		int ww = (int) (Minecraft.getInstance().getWindow().getGuiScaledWidth() / (2 * finalScale));
		int wh = (int) (Minecraft.getInstance().getWindow().getGuiScaledHeight() / (2 * finalScale));
		int x = (int) this.getX() + this.visualXOffset;
		int y = (int) this.getY() + this.visualYOffset;
		int w = this.visualWidth;
		int h = this.visualHeight;
		this.graphics.pose().pushPose();
		this.graphics.pose().scale(finalScale, finalScale, finalScale);
		this.graphics.blit(texture, ww + x, wh + y, 0, 0, w, h, w, h);
		this.graphics.pose().popPose();
	}

	public boolean canCollide() {
		return false;
	}

	public void collide() {
		if (this.canCollide()) {
			this.collidesStore();
		}
		for (GameObject object : this.objectsCollided) {
			this.objectCollide(object);
		}
	}

	public void move() {
		this.partialMove();
	}

	private void partialMove() {
		this.setX(x + velocityX);
		this.setY(y + velocityY);
		if (velocityX != 0 || velocityY != 0) {
			this.handleCollision();
		}
	}

	public void handleCollision() {
		this.collide();
		for (GameObject object : this.objectsCollided) {
			this.moveCollide(object);
		}
		if (this.objectsCollided.isEmpty()) {
			this.tempMoveRepeats = 0;
		}
	}

	public void objectCollide(GameObject otherObject) {
		//System.out.println(this.colliders);
	}

	public void moveCollide(GameObject otherObject) {
		this.setX(oldX);
		this.setY(oldY);
		this.tempMoveRepeats += 1;
		this.velocityX *= 0.95;
		this.velocityY *= 0.95;
		if (this.tempMoveRepeats > 20) {
			this.tempMoveRepeats = 0;
			return;
		}
		this.partialMove();
	}

	public void collidesStore() {
		this.objectsCollided = new ArrayList<>();
		for (GameObject object : this.scene.objects) {
			if (colliders.contains(object.type) && this.collidesWithObject(object) instanceof GameObject _gmObj) {
				this.objectsCollided.add(_gmObj);
			}
		}
	}

	// Check if this object collides with a specific object.
	public Object collidesWithObject(GameObject other) {
		if (this.getX() < other.getX() + other.getWidth() && this.getX() + this.getWidth() > other.getX() && this.getY() < other.getY() + other.getHeight() && this.getY() + this.getHeight() > other.getY()) {
			return other;
		}
		return null;
	}

	public float getGuiScale() {
		if (Minecraft.getInstance().getWindow().getGuiScale() == 1) {
			return 2;
		}
		if (Minecraft.getInstance().getWindow().getGuiScale() == 2) {
			return 1;
		}
		if (Minecraft.getInstance().getWindow().getGuiScale() == 3) {
			return 0.8f;
		}
		if (Minecraft.getInstance().getWindow().getGuiScale() == 4) {
			return 0.65f;
		}
		if (Minecraft.getInstance().getWindow().getGuiScale() == 5) {
			return 0.4f;
		}
		return 1;
	}

	public void discard() {
		this.scene.scheduledDiscard.add(this);
	}

	//Basic data getters
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setVelocityX(double x) {
		this.velocityX = x;
	}

	public void setVelocityY(double y) {
		this.velocityY = y;
	}

	public void setParent(GameObject parent) {
		this.parent = parent;
	}

	public double getX() {
		double x = this.x;
		if (parent != null)
			x += parent.x;
		return x;
	}

	public double getY() {
		double y = this.y;
		if (parent != null)
			y += parent.y;
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
