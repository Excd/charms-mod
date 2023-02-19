package com.excd.charmsmod.common.effects;

import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

/**
 * @author Greggory Seamon
 */
public class CharmMobEffect extends InstantenousMobEffect {
	
	private boolean isEffectApplied = false;
	
	/**
	 * @param p_19451_
	 * @param p_19452_
	 */
	public CharmMobEffect(MobEffectCategory p_19451_, int p_19452_) {
		super(p_19451_, p_19452_);
	}
	
	@Override
	public void applyEffectTick(LivingEntity p_19467_, int p_19468_) {
		
		if (!isEffectApplied) {
			p_19467_.getAttribute(Attributes.MAX_HEALTH).addTransientModifier(
					new AttributeModifier("MaxHealth", 1.0f, AttributeModifier.Operation.ADDITION));
			
			isEffectApplied = true;
			System.out.println("Player Max Health: " + p_19467_.getMaxHealth());
		}
	}
}