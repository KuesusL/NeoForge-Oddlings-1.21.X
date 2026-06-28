package net.zeriko.oddlings.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class ReststoneBlock extends Block {

    public ReststoneBlock(Properties properties) {
        super(properties.lightLevel(state -> state.getValue(LIT) ? 9: 0));

        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, false));
    }

    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    int radius = 10;

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        boolean night = level.isNight();
        level.setBlockAndUpdate(pos, state.setValue(LIT, night));

        AABB area = new AABB(pos).inflate(radius);
        List<Player> players = level.getEntitiesOfClass(Player.class, area);

        for(Player player : players) {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20, 0, false, false, true));
            if(night) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20, 0, false, false, true));
            } else {
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 20, 0,false, false, true));
            }
        }
        level.scheduleTick(pos, this, 20);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if(!level.isClientSide) {
            ((ServerLevel) level).scheduleTick(pos, this, 20);
        }
    }
}
