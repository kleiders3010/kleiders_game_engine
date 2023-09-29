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

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import java.util.List;
import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class GameObject {
	public static List<GameObject> objects = new ArrayList<>();
	public static List<GameObject> scheduledDiscard = new ArrayList<>();
	public static List<GameObject> scheduledAddition = new ArrayList<>();
	public static GuiGraphics graphics;
	public static float globalScale = 1.751f;
	public List<GameObject> objectsCollided = new ArrayList<>();
	public List<String> colliders = new ArrayList<>();
	public int x, y, width, height, visualWidth, visualHeight, visualXOffset, visualYOffset = 0;
	public String type;
	public float scale = 1;
	public double speed = 0;
	public double cycles = 0;
	public String direction = "";
	public GameObject parent = null;

	public GameObject(int x, int y, int width, int height, String type, GameObject parent) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.visualWidth = width;
		this.visualHeight = height;
		this.type = type;
		this.parent = parent;
		scheduledAddition.add(this);
	}

	public static void handleScene(GuiGraphics graphics) {
		GameObject.graphics = graphics;
		for (GameObject added : scheduledAddition) {
			objects.add(added);
		}
		for (GameObject discarded : scheduledDiscard) {
			objects.remove(discarded);
		}
		scheduledDiscard = new ArrayList<>();
		scheduledAddition = new ArrayList<>();
		for (GameObject object : objects) {
			object.rawTick();
		}
	}

	//Handles rendering and movement
	public void rawTick() {
		float finalScale = this.globalScale * this.scale * this.getGuiScale();
		int ww = (int) (Minecraft.getInstance().getWindow().getGuiScaledWidth() / (2 * finalScale));
		int wh = (int) (Minecraft.getInstance().getWindow().getGuiScaledHeight() / (2 * finalScale));
		int x = this.getX() + this.visualXOffset;
		int y = this.getY() + this.visualYOffset;
		int w = this.visualWidth;
		int h = this.visualHeight;
		this.graphics.pose().pushPose();
		this.graphics.pose().scale(finalScale, finalScale, finalScale);
		this.graphics.blit(new ResourceLocation("kleiders_game_engine:textures/screens/" + type + ".png"), ww + x, wh + y, 0, 0, w, h, w, h);
		this.graphics.pose().popPose();
		this.partialMove();
		this.collide();
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

	public void partialMove() {
		double amount = speed;
		while (amount > 0) {
			cycles += Math.min(1, amount);
			amount -= cycles;
			if (cycles >= 1) {
				cycles = 0;
				this.move();
			}
		}
	}

	public void move() {
		int oldX = this.getX();
		int oldY = this.getY();
		if (direction == "left") {
			this.setX(this.getX() - 1);
		}
		if (direction == "right") {
			this.setX(this.getX() + 1);
		}
		if (direction == "up") {
			this.setY(this.getY() - 1);
		}
		if (direction == "down") {
			this.setY(this.getY() + 1);
		}
		if (direction != "") {
			this.collide();
			for (GameObject object : this.objectsCollided) {
				this.moveCollide(oldX, oldY, object);
			}
		}
	}

	public void objectCollide(GameObject otherObject) {
		System.out.println(this.colliders);
	}

	public void moveCollide(int oldX, int oldY, GameObject otherObject) {
		this.setX(oldX);
		this.setY(oldY);
	}

	public void collidesStore() {
		this.objectsCollided = new ArrayList<>();
		for (GameObject object : objects) {
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
		scheduledDiscard.add(this);
	}

	//Basic data getters
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		int x = this.x;
		if (parent != null)
			x += parent.x;
		return x;
	}

	public int getY() {
		int y = this.y;
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
