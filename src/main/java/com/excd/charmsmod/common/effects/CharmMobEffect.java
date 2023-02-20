package com.excd.charmsmod.common.effects;

import java.util.ArrayList;

import com.excd.charmsmod.CharmsMod;
import com.excd.charmsmod.common.items.CharmItem;

import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author Greggory Seamon
 */
@Mod.EventBusSubscriber(modid = CharmsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CharmMobEffect extends InstantenousMobEffect {
	
	public ArrayList<CharmItem> CHARMS = new ArrayList<>();
	//public ArrayList<AttributeModifier> activeModifiers = new ArrayList<>();

	/**
	 * @param mobEffectCategory
	 * @param color
	 */
	public CharmMobEffect(MobEffectCategory mobEffectCategory, int color) {
		super(mobEffectCategory, color);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void evaluateCharms(TickEvent.PlayerTickEvent event) {
		Player player = event.player;
		
		if (player.getInventory().items.stream().anyMatch(
				itemStack -> itemStack.getItem() instanceof CharmItem)) {
			
			for (ItemStack itemStack : player.getInventory().items) {
				
				if (itemStack.getItem() instanceof CharmItem) {
					CHARMS.add(((CharmItem)itemStack.getItem()).applyModifier(player));
				}
			}
		}
	}
	
//	@Override
//	public void applyEffectTick(LivingEntity livingEntity, int p_19468_) {
//			
//		Player player = (Player)livingEntity;
//		
//		for (ItemStack itemStack : player.getInventory().items) {
//			
//			if (itemStack.getItem() instanceof CharmItem)
//				((CharmItem)itemStack.getItem()).applyModifier(player);
//		}
//	}
}