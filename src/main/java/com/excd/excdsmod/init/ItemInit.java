package com.excd.excdsmod.init;

import com.excd.excdsmod.ExcdsMod;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author Greggory Seamon
 */
// EventBusSubscriber automatically registers all static methods annotated with @SubscribeEvent.
@Mod.EventBusSubscriber(modid = ExcdsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ItemInit {
	
	// Static reference to Item register.
	public static final DeferredRegister<Item> ITEMS = 
			DeferredRegister.create(ForgeRegistries.ITEMS, ExcdsMod.MODID);
	
	// Register item with name string and supplier object.
	// Should be final/static and conventionally named in all upper-case with underscores.
	// Name string should be lower-case with underscores.
	public static final RegistryObject<Item> SMALL_CHARM = ITEMS.register("small_charm",
			() -> new Item(new Item.Properties()));
	
	// Add items to creative inventory tabs.
	@SubscribeEvent
	public static void buildContents(CreativeModeTabEvent.BuildContents event) {
		// Add items to tools and utilities creative tab.
		if (event.getTab() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			event.accept(SMALL_CHARM);
		}
	}
}