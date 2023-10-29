
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.kleiders.gameengine.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import com.kleiders.gameengine.block.BlockClickedBlock;
import com.kleiders.gameengine.KleidersGameEngineMod;

public class KleidersGameEngineModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, KleidersGameEngineMod.MODID);
	public static final RegistryObject<Block> BLOCK_CLICKED = REGISTRY.register("block_clicked", () -> new BlockClickedBlock());
}
