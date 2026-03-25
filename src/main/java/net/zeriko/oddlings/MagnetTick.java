package net.zeriko.oddlings;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@EventBusSubscriber
public class MagnetTick {

    private static final List<ItemEntity> activeItems = new ArrayList<>();

    public static void addActiveItem(ItemEntity item){
        if(!activeItems.contains(item)) {
            activeItems.add(item);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if(player.level().isClientSide) return;

        Iterator<ItemEntity> it = activeItems.iterator();
        while(it.hasNext()) {
            ItemEntity item = it.next();
            double dist = item.distanceTo(player);
            if(dist < 1.0) {
                it.remove();
                continue;
            }
            Vec3 direction = player.position().subtract(item.position()).normalize();
            double speed = Math.min(0.2, dist * 0.05);
            item.setDeltaMovement(item.getDeltaMovement().add(direction.scale(speed)));
        }
    }
}
