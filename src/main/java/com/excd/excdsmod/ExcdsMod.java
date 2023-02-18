package com.excd.excdsmod;

import com.mojang.logging.LogUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

/**
 * @author Greggory Seamon
 */
@Mod(ExcdsMod.MODID)
public class ExcdsMod {
	
    public static final String MODID = "excdsmod";				// Public mod id reference.
    
    private static final Logger LOGGER = LogUtils.getLogger();	// slf4j logger reference.

    public ExcdsMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the setup method for modloading.
        modEventBus.addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
    	// Some common setup code.
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }
}