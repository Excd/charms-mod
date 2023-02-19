package com.excd.excdsmod.common.handlers;

import com.excd.excdsmod.ExcdsMod;
import com.excd.excdsmod.init.ModEffects;
import com.excd.excdsmod.init.ModItems;

import net.minecraft.world.effect.MobEffectInstance;
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
public final class CharmEventHandler {

	@SubscribeEvent
	public static void charmPickup(PlayerEvent.ItemPickupEvent event) {
		ItemStack itemStack = event.getStack();
		
		if (itemStack.getItem().equals(ModItems.WOODEN_CHARM.get())) {
			ExcdsMod.LOGGER.info("Picked up: " + itemStack.getItem());
		}
	}
	
	@SubscribeEvent
	public static void checkCharms(TickEvent.PlayerTickEvent event) {
		Player player = event.player;
		
		if (player.getInventory().contains(new ItemStack(ModItems.WOODEN_CHARM.get()))) {
			player.addEffect(new MobEffectInstance(ModEffects.CHARM_EFFECT.get(), 100));
		}
	}
}