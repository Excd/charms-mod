package com.excd.charmsmod.common.item;

import net.minecraft.world.item.Item;

/**
 * @author Greggory Seamon
 */
public class CharmItem extends Item {

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
}