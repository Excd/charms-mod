package com.excd.charmsmod.common.items;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

/**
 * @author Greggory Seamon
 */
public class CharmItem extends Item {

	private boolean modifierApplied;
	private float healthModifier;
	private AttributeModifier attributeModifier;

	/**
	 * @param itemProperties
	 */
	protected CharmItem(Item.Properties itemProperties) {
		super(itemProperties);
		setHealthModifier(1.0f);
		setAttributeModifier(new AttributeModifier("MaxHealthModifier", 
				getHealthModifier(),
				AttributeModifier.Operation.ADDITION));
	}

	public CharmItem applyModifier(Player player) {
		
		if (!isModifierApplied()) {
			setModifierApplied(true);
			
			if (!player.getAttribute(Attributes.MAX_HEALTH).hasModifier(getAttributeModifier())) {
				player.getAttribute(Attributes.MAX_HEALTH).addTransientModifier(getAttributeModifier());
				System.out.println("Player Max Health: " + player.getMaxHealth());
			}
		}
		
		return this;
	}
	
	public void removeModifier(Player player) {
		
		if (isModifierApplied()) {
			setModifierApplied(false);
			
			if (player.getAttribute(Attributes.MAX_HEALTH).hasModifier(getAttributeModifier())) {
				player.getAttribute(Attributes.MAX_HEALTH).removeModifier(getAttributeModifier());
				System.out.println("Player Max Health: " + player.getMaxHealth());
			}
		}
	}
	
	public float getHealthModifier() {
		return healthModifier;
	}

	public void setHealthModifier(float healthModifier) {
		this.healthModifier = healthModifier;
	}
	
	public AttributeModifier getAttributeModifier() {
		return attributeModifier;
	}

	public void setAttributeModifier(AttributeModifier attributeModifier) {
		this.attributeModifier = attributeModifier;
	}

	public boolean isModifierApplied() {
		return modifierApplied;
	}

	public void setModifierApplied(boolean modifierApplied) {
		this.modifierApplied = modifierApplied;
	}
}