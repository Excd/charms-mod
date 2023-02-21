package com.excd.charmsmod.common.items;

import net.minecraft.world.item.Item;

/**
 * @author Greggory Seamon
 */
public class CharmItem extends Item {

	private boolean modifierApplied;
	private float healthModifier;

	/**
	 * @param itemProperties
	 */
	protected CharmItem(Item.Properties itemProperties, float healthModifier) {
		super(itemProperties);
		this.healthModifier = healthModifier;
	}

	public float getHealthModifier() {
		return healthModifier;
	}

	public void setHealthModifier(float healthModifier) {
		this.healthModifier = healthModifier;
	}

	public boolean isModifierApplied() {
		return modifierApplied;
	}

	public void setModifierApplied(boolean modifierApplied) {
		this.modifierApplied = modifierApplied;
	}
}