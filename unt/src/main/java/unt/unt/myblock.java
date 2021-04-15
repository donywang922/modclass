package unt.unt;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class myblock extends Block {

    public static final BooleanProperty HARDENED = BooleanProperty.of("hardened");

    public static final IntProperty in = IntProperty.of("in",0,4);

    public myblock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(HARDENED, false));
        setDefaultState(getStateManager().getDefaultState().with(in, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(HARDENED);
        stateManager.add(in);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if (player.isSneaking()){
            world.setBlockState(pos, state.get(in) >= 4 ? state.with(in, 0) : state.with(in, state.get(in) + 1));
        }else{
            world.setBlockState(pos, state.get(HARDENED) ? state.with(HARDENED, false) : state.with(HARDENED, true));
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(HARDENED) ? VoxelShapes.fullCube() : VoxelShapes.empty();
    }

}
