package org.eu.net.jmbrands;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.eu.net.jmbrands.screens.AltarScreen;
import org.eu.net.jmbrands.screens.AltarScreenHandler;

@Environment(EnvType.CLIENT)
public class WandModClient implements ClientModInitializer {/*
    public static final ScreenHandlerType<AltarScreenHandler> ALTAR_SCREEN_HANDLER;

    static {
        ALTAR_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier("wands", "altar"), AltarScreenHandler::new);
    }*/
    @Override
    public void onInitializeClient() {
        HandledScreens.register(WandMod.ALTAR_SCREEN_HANDLER, AltarScreen::new);
    }
}