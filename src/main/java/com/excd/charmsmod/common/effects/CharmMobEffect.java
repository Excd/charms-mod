package com.excd.charmsmod.common.effects;

import java.util.UUID;

import com.excd.charmsmod.CharmsMod;
import com.excd.charmsmod.common.items.CharmItem;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

/**
 * @author Greggory Seamon
 */
@Mod.EventBusSubscriber(modid = CharmsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CharmMobEffect extends InstantenousMobEffect {

	private float totalHealthModifier;
	private AttributeModifier attributeModifier;
	private final UUID attributeModifierId;
	
	/**
	 * @param mobEffectCategory
	 * @param color
	 */
	public CharmMobEffect(MobEffectCategory mobEffectCategory, int color) {
		super(mobEffectCategory, color);
		
		setTotalHealthModifier(0.0f);
		
		attributeModifierId = Mth.createInsecureUUID(RandomSource.createNewThreadLocalInstance());
		setAttributeModifier(new AttributeModifier(
				this.attributeModifierId,
				"MaxHealthModifier",
				getTotalHealthModifier(),
				AttributeModifier.Operation.ADDITION));
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void evaluateCharms(TickEvent.PlayerTickEvent event) {
		
		if (event.side == LogicalSide.SERVER) {
			Player player = event.player;
			
			setTotalHealthModifier(0.0f);
			
			for (ItemStack itemStack : player.getInventory().items) {
				
				if (itemStack.getItem() instanceof CharmItem) {
					
					setTotalHealthModifier(getTotalHealthModifier() +
							((CharmItem)itemStack.getItem()).getHealthModifier());
				}
			}
			
			if (getAttributeModifier().getAmount() != getTotalHealthModifier()) {
				setAttributeModifier(new AttributeModifier(
						getAttributeModifierId(),
						"MaxHealthModifier",
						getTotalHealthModifier(),
						AttributeModifier.Operation.ADDITION));
				
				updateModifier(player);
			}
		}
	}
	
	private void updateModifier(Player player) {
		
		if (player.getAttribute(Attributes.MAX_HEALTH).hasModifier(getAttributeModifier())) {
			removeModifier(player);
		}
		
		applyModifier(player);
	}

	private void applyModifier(Player player) {
		player.getAttribute(Attributes.MAX_HEALTH).addTransientModifier(getAttributeModifier());
		
		System.out.println("Modifier added!\nPlayer Max Health: " + player.getMaxHealth());
	}
	
	private void removeModifier(Player player) {
		player.getAttribute(Attributes.MAX_HEALTH).removeModifier(getAttributeModifier());
		
		if (player.getHealth() >= player.getMaxHealth()) {
			player.setHealth(player.getMaxHealth());
		}
		
		System.out.println("Modifier removed!\nPlayer Max Health: " + player.getMaxHealth());
	}

	public float getTotalHealthModifier() {
		return totalHealthModifier;
	}

	public void setTotalHealthModifier(float totalHealthModifier) {
		this.totalHealthModifier = totalHealthModifier;
	}

	public AttributeModifier getAttributeModifier() {
		return attributeModifier;
	}

	public void setAttributeModifier(AttributeModifier attributeModifier) {
		this.attributeModifier = attributeModifier;
	}

	public UUID getAttributeModifierId() {
		return attributeModifierId;
	}
}