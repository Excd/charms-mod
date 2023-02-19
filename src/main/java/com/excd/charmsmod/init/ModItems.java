package com.excd.charmsmod.init;

import com.excd.charmsmod.CharmsMod;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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
@Mod.EventBusSubscriber(modid = CharmsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModItems {
	
	// Static reference to Item register.
	public static final DeferredRegister<Item> ITEMS = 
			DeferredRegister.create(ForgeRegistries.ITEMS, CharmsMod.MODID);
	
	// Register item with name string and supplier object.
	// Should be final/static and conventionally named in all upper-case with underscores.
	// Name string should be lower-case with underscores.
	public static final RegistryObject<Item> WOODEN_CHARM = ITEMS.register("wooden_charm",
			() -> new Item(new Item.Properties()));
	
	// Add items to creative inventory tabs.
	@SubscribeEvent
	public static void buildContents(CreativeModeTabEvent.Register event) {
		event.registerCreativeModeTab(new ResourceLocation(CharmsMod.MODID, "charms"), builder ->
			builder.title(Component.translatable("itemGroup." + CharmsMod.MODID + ".charms"))
			.icon(() -> new ItemStack(WOODEN_CHARM.get()))
			.displayItems((enabledFlags, populator, hasPermissions) -> {
				populator.accept(WOODEN_CHARM.get());
			})
		);
//		// Add items to tools and utilities creative tab.
//		if (event.getTab() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
//			event.accept(WOODEN_CHARM);
//		}
	}
}