package org.eu.net.jmbrands;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.eu.net.jmbrands.wand.FireWand;


public class WandMod implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final FireWand FIRE_WAND = Registry.register(
            Registries.ITEM,
            new Identifier( "wands", "fire_wand"),
            new FireWand(new FabricItemSettings()
                    .maxCount(1)
                    .fireproof()
                    .rarity(Rarity.EPIC)
            )
    );
    public static final ItemGroup WAND_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(FIRE_WAND))
            .displayName(Text.translatable("itemGroup.wands.wand_group"))
            .build();
    public static final Identifier WAND_GROUP_ID = new Identifier("wands", "wand_group");
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        Registry.register(Registries.ITEM_GROUP,WAND_GROUP_ID ,WAND_GROUP);
        ItemGroupEvents.modifyEntriesEvent(RegistryKey.of(RegistryKeys.ITEM_GROUP,WAND_GROUP_ID)).register(content -> content.add(FIRE_WAND));
    }
}