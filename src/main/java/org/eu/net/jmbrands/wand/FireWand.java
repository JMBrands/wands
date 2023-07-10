package org.eu.net.jmbrands.wand;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FireWand extends Wand {

    public final int power = 1;
    public FireWand(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        Vec3d r = playerEntity.getRotationVec(1.0F);
        double f = r.x;
        double g = r.y;
        double h = r.z;
        if (playerEntity.getWorld() instanceof ServerWorld) {
            FireballEntity fireballEntity = new FireballEntity(world, playerEntity, f, g, h, power);
            fireballEntity.setPos(playerEntity.getX(),playerEntity.getY()+1.0D,playerEntity.getZ());
            world.spawnEntity(fireballEntity);
        }
        playerEntity.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.0F);
        playerEntity.getItemCooldownManager().set(this,40);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}

