package hazel.relic;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;

//This is the swap feature


public class AstralScrap extends Item {
    public AstralScrap(Settings settings) {
        super(settings);
    }

    public static final Item astral_scrap = Registry.register(
            Registries.ITEM,
            Identifier.of("relic-remedies", "astral_scrap"),
            new AstralScrap(new Item.Settings().maxCount(1))
    );

    public TypedActionResult<ItemStack> use(ServerWorld world, PlayerEntity user, Hand hand) {

        if (!world.isClient) {
            BlockHitResult blockHit = (BlockHitResult) user.raycast(5.0, 0.0f, false);

            Vec3d start = user.getCameraPosVec(1.0f);
            Vec3d end = start.add(user.getRotationVector().multiply(5.0));
            Box box = user.getBoundingBox().stretch(user.getRotationVector().multiply(5.0)).expand(1.0);

            EntityHitResult entityHit = ProjectileUtil.getEntityCollision(world, user, start, end, box, e -> true);

            if (entityHit != null) {
                Entity target = entityHit.getEntity();
                Vec3d playerPos = user.getPos();
                Vec3d targetPos = target.getPos();

                user.refreshPositionAfterTeleport(targetPos);
                target.refreshPositionAfterTeleport(playerPos);
            }


        }


        return super.use(world,user,hand);
    }
}