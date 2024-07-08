package com.kleiders.minigames;

import com.kleiders.gameengine.GameControls;
import com.kleiders.gameengine.GameObject;
import com.kleiders.gameengine.GameScene;
import com.kleiders.gameengine.PickaxeObject;
import net.minecraft.resources.ResourceLocation;

public class RunnerPlayerObject extends GameObject {
    private boolean onGround = false;

    public RunnerPlayerObject(float x, float y, float width, float height, GameScene scene) {
        super(x, y, width, height, "runner_player", scene);
        this.colliders.add("cactus");
        this.colliders.add("dirt_0");
        this.colliders.add("dirt_1");
        this.colliders.add("nograss_dirt_0");
        this.colliders.add("nograss_dirt_1");
        this.colliders.add("arrow");
        this.speed = 1;
        this.texture = new ResourceLocation("kleiders_game_engine:textures/screens/player_walk_0.png");
        this.width = 9;
        this.visualXOffset = -3.15f;
        this.drawLate = 2;
    }

    @Override
    public boolean canCollide() {
        return true;
    }

    @Override
    public void objectCollide(GameObject otherObject) {
        if (otherObject.type.contains("dirt")) {
            onGround = true;
        }
    }

    private final float gravity = 0.1f;

    @Override
    public void move() {
        if (GameControls.isKeyPressed("A")) {
            this.setVelocityX(-1f);
        }
        if (GameControls.isKeyPressed("D")) {
            this.setVelocityX(1f);
        }
        if (GameControls.isKeyPressed("W") && this.onGround) {
            this.setVelocityY(-2.5f);
        } else if (GameControls.isKeyPressed("S")) {
            this.setVelocityY(this.velocityY + (gravity * 1.5f));
        }
        if (this.velocityY < 3.25f) {
            this.setVelocityY(this.velocityY + gravity);
        }
        this.onGround = false;
        super.move();
    }

    private int textureDuration = 0;
    private int currentTexture = 0;
    private int maxTexture = 1;

    @Override
    public void rawTick() {
        textureDuration += 1;
        if (textureDuration >= 15) {
            textureDuration = 0;
            currentTexture += 1;
            if (currentTexture > maxTexture)
                currentTexture = 0;
            if (!onGround) {
                texture = new ResourceLocation("kleiders_game_engine:textures/screens/player_jump_0.png");
            } else {
                texture = new ResourceLocation("kleiders_game_engine:textures/screens/player_walk_" + currentTexture + ".png");
            }
        }
        super.rawTick();
    }
}
