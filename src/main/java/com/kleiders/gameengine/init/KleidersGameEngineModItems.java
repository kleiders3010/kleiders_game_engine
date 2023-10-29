
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.kleiders.gameengine.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import com.kleiders.gameengine.item.StartGameItem;
import com.kleiders.gameengine.KleidersGameEngineMod;

public class KleidersGameEngineModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, KleidersGameEngineMod.MODID);
	public static final RegistryObject<Item> START_GAME = REGISTRY.register("start_game", () -> new StartGameItem());
	public static final RegistryObject<Item> BLOCK_CLICKED = block(KleidersGameEngineModBlocks.BLOCK_CLICKED);

	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
