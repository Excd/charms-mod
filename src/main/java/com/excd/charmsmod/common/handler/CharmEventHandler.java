package com.excd.charmsmod.common.handler;

import com.excd.charmsmod.CharmsMod;
import com.excd.charmsmod.common.effect.CharmEffect;
import com.excd.charmsmod.common.item.CharmItem;
import com.excd.charmsmod.init.ModItems;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

/**
 * @author Greggory Seamon
 */
@Mod.EventBusSubscriber(modid = CharmsMod.MODID)
public final class CharmEventHandler {
	
	private static CharmEffect charmEffectInstance;
	
	@SubscribeEvent
	public static void charmPickup(PlayerEvent.ItemPickupEvent event) {
		ItemStack itemStack = event.getStack();
		
		if (itemStack.getItem().equals(ModItems.WOODEN_CHARM.get())) {
			System.out.println("Picked up: " + itemStack.getItem());
		}
	}
	
	@SubscribeEvent
	public static void checkCharms(TickEvent.PlayerTickEvent event) {		
		
		if (event.side == LogicalSide.SERVER) {
			Player player = event.player;
			
			if (player.getInventory().items.stream().anyMatch(
					itemStack -> itemStack.getItem() instanceof CharmItem)) {
				
				if (charmEffectInstance == null)
					charmEffectInstance = new CharmEffect();
			}
			else {
				
				if (charmEffectInstance != null)  {
					charmEffectInstance.evaluateCharms(player);
					charmEffectInstance = null;
				}
			}
			
			if (charmEffectInstance != null) 
				charmEffectInstance.evaluateCharms(player);
		}
	}
}