package com.excd.excdsmod.init;

import com.excd.excdsmod.ExcdsMod;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author Greggory Seamon
 */
public final class ItemInit {

	// Static reference to Item register.
	public static final DeferredRegister<Item> ITEMS = 
			DeferredRegister.create(ForgeRegistries.ITEMS, ExcdsMod.MODID);
}
