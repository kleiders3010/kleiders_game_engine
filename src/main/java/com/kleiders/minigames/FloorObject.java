package com.kleiders.minigames;

import com.kleiders.gameengine.GameControls;
import com.kleiders.gameengine.GameObject;
import com.kleiders.gameengine.GameScene;
import net.minecraft.resources.ResourceLocation;

public class FloorObject extends GameObject {
    public FloorObject(float x, float y, float width, float height, String type, GameScene scene) {
        super(x, y, width, height, type, scene);
    }

    @Override
    public void move() {
        this.setVelocityX(-0.4f);
        super.move();
        if (this.getX() < -136) {
            new FloorObject(104, this.getY(), 8, 8, this.type, this.scene);
            this.discard();
        }
    }
}
