
package com.kleiders.gameengine.client.screens;

import org.checkerframework.checker.units.qual.h;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

import com.kleiders.gameengine.GameCore;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class MinigameOverlay {
	public static boolean started;
	public static int tileSize = 8;

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(RenderGuiEvent.Pre event) {
		/*	if (!started) {
				started = true;
				KleidersGameEngineMod.LOGGER.info("test");
				GameScene scene = new GameScene("scene_test");
				scene.active = true;
				new GameObject(0 * tileSize, 0 * tileSize, 8, 8, "rock", scene);
				new GameObject(1 * tileSize, 2 * tileSize, 8, 8, "rock", scene);
				new GameObject(1 * tileSize, 3 * tileSize, 8, 8, "rock", scene);
				new GameObject(6 * tileSize, 3 * tileSize, 8, 8, "pickaxe", scene);
				new PlayerObject(1 * tileSize, 1 * tileSize, 8, 8, "player", scene);
			}
		*/
		int w = event.getWindow().getGuiScaledWidth();
		int h = event.getWindow().getGuiScaledHeight();
		int posX = w / 2;
		int posY = h / 2;
		Level world = null;
		double x = 0;
		double y = 0;
		double z = 0;
		Player entity = Minecraft.getInstance().player;
		if (entity != null) {
			world = entity.level();
			x = entity.getX();
			y = entity.getY();
			z = entity.getZ();
		}
		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.enableBlend();
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		RenderSystem.setShaderColor(1, 1, 1, 1);
		if (true) {
			GameCore.handleGameScenes(event.getGuiGraphics());
		}
		RenderSystem.depthMask(true);
		RenderSystem.defaultBlendFunc();
		RenderSystem.enableDepthTest();
		RenderSystem.disableBlend();
		RenderSystem.setShaderColor(1, 1, 1, 1);
	}
}
