package hazel.relic;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

import java.util.Set;

//This is the swap feature


public class AstralScrap extends Item {
    public AstralScrap(Settings settings) {
        super(settings);
    }

    public static final Item ASTRAL_SCRAP = Registry.register(
            Registries.ITEM,
            Identifier.of("relic-remedies", "astral_scrap"),
            new AstralScrap(new Item.Settings().maxCount(1))
    );

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient) {
            Vec3d start = user.getCameraPosVec(1.0f);
            Vec3d end = start.add(user.getRotationVector().multiply(5.0));
            Box box = user.getBoundingBox().stretch(user.getRotationVector().multiply(5.0)).expand(1.0);

            EntityHitResult entityHit = ProjectileUtil.getEntityCollision(world, user, start, end, box, e -> true);

            if (entityHit != null) {
                Entity target = entityHit.getEntity();
                Vec3d playerPos = user.getPos();
                Vec3d targetPos = target.getPos();

                user.teleport((ServerWorld) world, targetPos.x, targetPos.y, targetPos.z, Set.of(), user.getYaw(), user.getPitch());

                target.teleport((ServerWorld) world, playerPos.x, playerPos.y, playerPos.z, Set.of(), target.getYaw(), target.getPitch());
            }
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        itemStack.decrementUnlessCreative(1, user);
        return TypedActionResult.success(itemStack, world.isClient());
    }
}