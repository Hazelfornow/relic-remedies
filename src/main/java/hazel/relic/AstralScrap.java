package hazel.relic;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;


public class AstralScrap extends Item {
    public AstralScrap(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(ServerWorld world, PlayerEntity user, Hand hand) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.crosshairTarget != null) {
            HitResult.Type type = client.crosshairTarget.getType();

            if (type == HitResult.Type.BLOCK) {
                BlockHitResult blockHit = (BlockHitResult) client.crosshairTarget;
                return TypedActionResult.pass(user.getStackInHand(hand));
            } else if (type == HitResult.Type.ENTITY) {
                EntityHitResult entityHit = (EntityHitResult) client.crosshairTarget;
                Vec3d todo = user.getPos();
                Vec3d victim = entityHit.getEntity().getPos();
                user.teleportTo(new TeleportTarget(world,victim,Vec3d.ZERO,user.getHeadYaw(),user.getPitch(),TeleportTarget.NO_OP));
                entityHit.getEntity().teleportTo(new TeleportTarget(world,todo,Vec3d.ZERO,entityHit.getEntity().getHeadYaw(),entityHit.getEntity().getPitch(),TeleportTarget.NO_OP));;
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
