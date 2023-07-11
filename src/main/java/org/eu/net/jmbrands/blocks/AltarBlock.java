package org.eu.net.jmbrands.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;


public class AltarBlock extends Block {

    public static final VoxelShape BOTTOM_SHAPE = Block.createCuboidShape(0.0d, 0.0d, 0.0d, 16.0d, 1.0d, 16.0d);
    public static final VoxelShape MIDDLE_SHAPE = Block.createCuboidShape(5.0d, 1.0d, 5.0d, 11.0d, 8.0d, 11.0d);
    public static final VoxelShape TOP_SHAPE = Block.createCuboidShape(0.0d, 8.0d, 0.0d, 16.0d, 12.0d, 16.0d);
    public static final VoxelShape SHAPE = VoxelShapes.union(BOTTOM_SHAPE, MIDDLE_SHAPE, TOP_SHAPE);
    public AltarBlock(Settings settings) {
        super(settings);
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

}
