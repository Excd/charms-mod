package com.excd.excdsmod.common.handlers;

import com.excd.excdsmod.ExcdsMod;
import com.excd.excdsmod.init.ItemInit;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author Greggory Seamon
 */
@Mod.EventBusSubscriber(modid = ExcdsMod.MODID)
public final class ItemPickupEventHandler {

	@SubscribeEvent
	public static void itemPickup(PlayerEvent.ItemPickupEvent event) {
		ItemStack itemStack = event.getStack();
		
		if (itemStack.getItem().equals(ItemInit.WOODEN_CHARM.get())) {
			ExcdsMod.LOGGER.info("Picked up: " + itemStack.getItem());
		}
	}
	
	@SubscribeEvent
	public static void checkCharms(TickEvent.PlayerTickEvent event) {
		Player player = event.player;
		
		if (player.getInventory().contains(new ItemStack(ItemInit.WOODEN_CHARM.get()))) {
			System.out.println("Inventory contains: " + ItemInit.WOODEN_CHARM.get());
		}
	}
}