package metal.ougabouga.world.block;

import metal.ougabouga.world.block.entity.BasketBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BasketBlock extends BaseEntityBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	private static final VoxelShape HORIZONTAL = Shapes.or(Shapes.or(Block.box(7.0F, 10.0F, 3.0F, 9.0F, 14.0F, 13.0F)), Block.box(1.0F, 0.0F, 3.0F, 15.0F, 10.0F, 13.0F));
	private static final VoxelShape VERTICAL = Shapes.or(Shapes.or(Block.box(3.0F, 10.0F, 7.0F, 13.0F, 14.0F, 9.0F)), Block.box(3.0F, 0.0F, 1.0F, 13.0F, 10.0F, 15.0F));
	
	protected BasketBlock(Properties pProperties) {
		super(pProperties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}
	
	@Override
	public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
		if (pLevel.isClientSide) {
			return InteractionResult.SUCCESS;
		} else {
			BlockEntity blockentity = pLevel.getBlockEntity(pPos);
			
			if (blockentity instanceof BasketBlockEntity) {
				pPlayer.openMenu((BasketBlockEntity)blockentity);
			}
			
			return InteractionResult.CONSUME;
		}
	}
	
	@Override
	public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
		if (!pState.is(pNewState.getBlock())) {
			BlockEntity blockentity = pLevel.getBlockEntity(pPos);
			
			if (blockentity instanceof Container) {
				Containers.dropContents(pLevel, pPos, (Container)blockentity);
			}
			
			super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
		}
	}
	
	@Override
	public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {
		if (!pLevel.isClientSide()) {
			pLevel.destroyBlock(pPos, false, pEntity);
		}
		
		super.fallOn(pLevel, pState, pPos, pEntity, pFallDistance);
	}
	
	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new BasketBlockEntity(pPos, pState);
	}
	
	protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
		return !pState.getCollisionShape(pLevel, pPos).getFaceShape(Direction.UP).isEmpty()
				|| pState.isFaceSturdy(pLevel, pPos, Direction.UP);
	}
	
	@Override
	public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
		BlockPos blockpos = pPos.below();
		BlockState rootState = pLevel.getBlockState(blockpos);
		return !rootState.getCollisionShape(pLevel, pPos).getFaceShape(Direction.UP).isEmpty() || rootState.isFaceSturdy(pLevel, pPos, Direction.UP);
	}
	
	@Override
	public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
		return !pState.canSurvive(pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
	}
	
	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		Direction direction = pState.getValue(FACING);
		
		switch (direction) {
		case NORTH, SOUTH -> {
			return HORIZONTAL;
		}
		case EAST, WEST -> {
			return VERTICAL;
		}
		default -> {
			return HORIZONTAL;
		}
		}
	}
	
	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}
	
	@Override
	public BlockState rotate(BlockState pState, Rotation pRotation) {
		return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
	}
	
	@Override
	public BlockState mirror(BlockState pState, Mirror pMirror) {
		return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(FACING);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
	}
}
