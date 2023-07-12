package org.eu.net.jmbrands.blockEntities;

import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.eu.net.jmbrands.ImplementedInventory;
import org.eu.net.jmbrands.WandMod;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.eu.net.jmbrands.screens.AltarScreenHandler;
import org.jetbrains.annotations.Nullable;

public class AltarBlockEntity extends LootableContainerBlockEntity implements ImplementedInventory {
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(9, ItemStack.EMPTY);
    public AltarBlockEntity(BlockPos pos, BlockState state) {
        super(WandMod.ALTAR_BLOCK_ENTITY, pos, state);
    }
    @Override
    public DefaultedList<ItemStack> getInvStackList() {
        return items;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.items.clear();
        for (var i = 0; i < this.items.size(); i++) {
            this.items.add(list.get(i));
        }
    }

    @Override
    public ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        //We provide *this* to the screenHandler as our class Implements Inventory
        //Only the Server has the Inventory at the start, this will be synced to the client in the ScreenHandler
        return new AltarScreenHandler(syncId, playerInventory, this);
    }
    @Override
    public Text getContainerName() {
        // for 1.19+
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
        // for earlier versions
        // return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }
    @Override
    public void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, items);
        super.writeNbt(nbt);
    }
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, items);
    }
    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
    public static void tick(World world, BlockPos pos, BlockState state, AltarBlockEntity be) {}

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }
}
