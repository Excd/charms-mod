package com.excd.excdsmod;

import com.excd.excdsmod.init.ItemInit;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

/**
 * @author Greggory Seamon
 */
@Mod(ExcdsMod.MODID)
public final class ExcdsMod {

    public static final String MODID = "excdsmod";				// Public mod id reference.
    public static final Logger LOGGER = LogUtils.getLogger();	// slf4j logger reference.

    public ExcdsMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);		// Register setup method for modloading.
        
        ItemInit.ITEMS.register(modEventBus);		// Register items to mod event bus.

        MinecraftForge.EVENT_BUS.register(this);	// Register class to core Forge event bus.
    }

    private void setup(final FMLCommonSetupEvent event) {
    	// Setup code.
        LOGGER.info("HELLO WORLD!");
    }
}