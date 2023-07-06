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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExampleMod implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("test");
    public static final CustomItem TEST_ITEM = Registry.register(Registries.ITEM, new Identifier("test", "test_item"), new CustomItem(new FabricItemSettings()));
    public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(TEST_ITEM))
            .displayName(Text.translatable("itemGroup.test.test_group"))
            .build();
    public static final Identifier ITEM_GROUP_ID = new Identifier("test", "test_group");
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        LOGGER.info("Hello Fabric world!");
        Registry.register(Registries.ITEM_GROUP,ITEM_GROUP_ID ,ITEM_GROUP);
        ItemGroupEvents.modifyEntriesEvent(RegistryKey.of(RegistryKeys.ITEM_GROUP,ITEM_GROUP_ID)).register(content -> content.add(TEST_ITEM));
    }
}