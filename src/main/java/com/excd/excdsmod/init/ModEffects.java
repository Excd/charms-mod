package com.excd.excdsmod.init;

import com.excd.excdsmod.ExcdsMod;

import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author Greggory Seamon
 */
@Mod.EventBusSubscriber(modid = ExcdsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModEffects {

	public static final DeferredRegister<MobEffect> MOB_EFFECTS =
			DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ExcdsMod.MODID);
	
	public static final RegistryObject<MobEffect> CHARM_EFFECT = MOB_EFFECTS.register("charm_effect",
			() -> new InstantenousMobEffect(MobEffectCategory.BENEFICIAL, 16262179));
}