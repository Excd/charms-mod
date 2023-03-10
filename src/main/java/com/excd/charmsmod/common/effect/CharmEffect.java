package com.excd.charmsmod.common.effect;

import java.util.UUID;

import com.excd.charmsmod.common.item.CharmItem;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * @author Greggory Seamon
 */
public class CharmEffect {

	private final UUID attributeModifierId;
	private float totalHealthModifier;
	private AttributeModifier attributeModifier;
	
	public CharmEffect() {
		this.attributeModifierId = Mth.createInsecureUUID(RandomSource.createNewThreadLocalInstance());
		this.totalHealthModifier = 0.0f;
		this.attributeModifier = newAttributeModifier();
	}
	
	public void evaluateCharms(Player player) {
			
		setTotalHealthModifier(0.0f);
		
		for (ItemStack itemStack : player.getInventory().items) {
			
			if (itemStack.getItem() instanceof CharmItem) {
				
				setTotalHealthModifier(getTotalHealthModifier() +
						((CharmItem)itemStack.getItem()).getHealthModifier());
			}
		}
		
		if (getAttributeModifier().getAmount() != getTotalHealthModifier()) {
			setAttributeModifier(newAttributeModifier());
			
			updateModifier(player);
			System.out.println("---Player Max Health: " + player.getMaxHealth());
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
	}
	
	private void removeModifier(Player player) {
		player.getAttribute(Attributes.MAX_HEALTH).removeModifier(getAttributeModifier());
		
		if (player.getHealth() >= player.getMaxHealth()) {
			player.setHealth(player.getMaxHealth());
		}
	}

	private AttributeModifier newAttributeModifier() {
		return new AttributeModifier(
				getAttributeModifierId(),
				"MaxHealthModifier",
				getTotalHealthModifier(),
				AttributeModifier.Operation.ADDITION);
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