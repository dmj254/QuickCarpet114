package quickcarpet.mixin.lightningKillsDropsFix;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import quickcarpet.settings.Settings;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {

    @Shadow private int age;

    public ItemEntityMixin(EntityType<?> entityType_1, World world_1) { super(entityType_1, world_1); }

    @Override
    public void onStruckByLightning(ServerWorld world, LightningEntity lightning) {
        if (Settings.lightningKillsDropsFix) {
            if (this.age > 8) { //Only kill item if its older then 8 ticks
                super.onStruckByLightning(world, lightning);
            }
        } else {
            super.onStruckByLightning(world, lightning);
        }
    }
}
