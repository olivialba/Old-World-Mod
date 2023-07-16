package net.alba.oldworld.entity.projectiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class OldWorldProjectile extends ProjectileEntity {
    public double accelX;
    public double accelY;
    public double accelZ;

    protected OldWorldProjectile(EntityType<? extends OldWorldProjectile> entityType, World world) {
        super((EntityType<? extends ProjectileEntity>)entityType, world);
    }

    public OldWorldProjectile(EntityType<? extends OldWorldProjectile> type, double x, double y, double z, double directX, double directY, double directZ, World world) {
        this(type, world);
        this.refreshPositionAndAngles(x, y, z, this.getYaw(), this.getPitch());
        this.refreshPosition();
        double d = Math.sqrt(directX * directX + directY * directY + directZ * directZ);
        if (d != 0.0) {
            this.accelX = directX / d * 0.1;
            this.accelY = directY / d * 0.1;
            this.accelZ = directZ / d * 0.1;
        }
    }

    public OldWorldProjectile(EntityType<? extends OldWorldProjectile> type, LivingEntity owner, double directionX, double directionY, double directionZ, World world) {
        this(type, owner.getX(), owner.getY(), owner.getZ(), directionX, directionY, directionZ, world);
        this.setOwner(owner);
        this.setRotation(owner.getYaw(), owner.getPitch());
    }

    @Override
    public void tick() {
        HitResult hitResult;
        Entity entity = this.getOwner();
        if (!this.world.isClient && (entity != null && entity.isRemoved() || !this.world.isChunkLoaded(this.getBlockPos()))) {
            this.discard();
            return;
        }
        super.tick();
        if (this.isBurning()) {
            this.setOnFireFor(1);
        }
        if ((hitResult = ProjectileUtil.getCollision(this, this::canHit)).getType() != HitResult.Type.MISS) {
            this.onCollision(hitResult);
        }
        this.checkBlockCollision();
        Vec3d vec3d = this.getVelocity();
        double posX = this.getX() + vec3d.x;
        double posY = this.getY() + vec3d.y;
        double posZ = this.getZ() + vec3d.z;
        ProjectileUtil.setRotationFromVelocity(this, 0.2f);
        float resistance = this.getDrag();
        if (this.isTouchingWater()) {
            for (int i = 0; i < 4; i++) {
                this.world.addParticle(ParticleTypes.BUBBLE, posX - vec3d.x * 0.25, posY - vec3d.y * 0.25, posZ - vec3d.z * 0.25, vec3d.x, vec3d.y, vec3d.z);
            }
            resistance = 0.85f;
        }
        this.setVelocity(vec3d.add(this.accelX, this.accelY, this.accelZ).multiply(resistance));
        if (this.getTrail()) {
            this.spawnParticleTrail(posX, posY, posZ);
        }
        this.setPosition(posX, posY, posZ);
    }

    /**
     * Spawn particle every tick if {@code getTrail()} return true.
     * 
     * <p>Example: this.world.addParticle(ParticleTypes.FLAME, d, e + 0.3, f, 0.0, 0.0, 0.0);
     */
    protected void spawnParticleTrail(double d, double e, double f) {
    }

    /**
     * Should the entity have a particle trail.
     */
    protected boolean getTrail() {
        return true;
    }

    @Override
    public float getTargetingMargin() {
        return 1.0f;
    }

    @Override
    public float getBrightnessAtEyes() {
        return 1.5f;
    }

    protected boolean isBurning() {
        return false;
    }

    protected float getDrag() {
        return 0.95f;
    }

    @Override
    public boolean canHit() {
        return false;
    }

    @Override
    protected boolean canHit(Entity entity) {
        return super.canHit(entity) && !entity.noClip;
    }

    @Override
    public boolean shouldRender(double distance) {
        double d = this.getBoundingBox().getAverageSideLength() * 4.0;
        if (Double.isNaN(d)) {
            d = 4.0;
        }
        return distance < (d *= 64.0) * d;
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.put("power", this.toNbtList(this.accelX, this.accelY, this.accelZ));
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        NbtList nbtList;
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("power", NbtElement.LIST_TYPE) && (nbtList = nbt.getList("power", NbtElement.DOUBLE_TYPE)).size() == 3) {
            this.accelX = nbtList.getDouble(0);
            this.accelY = nbtList.getDouble(1);
            this.accelZ = nbtList.getDouble(2);
        }
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        Entity entity = this.getOwner();
        int i = entity == null ? 0 : entity.getId();
        return new EntitySpawnS2CPacket(this.getId(), this.getUuid(), this.getX(), this.getY(), this.getZ(), this.getPitch(), this.getYaw(), this.getType(), i, new Vec3d(this.accelX, this.accelY, this.accelZ), 0.0);
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        double velX = packet.getVelocityX();
        double velY = packet.getVelocityY();
        double velZ = packet.getVelocityZ();
        double mag = Math.sqrt(velX * velX + velY * velY + velZ * velZ);
        if (mag != 0.0) {
            this.accelX = velX / mag * 0.1;
            this.accelY = velY / mag * 0.1;
            this.accelZ = velZ / mag * 0.1;
        }
    }

    @Override
    protected void initDataTracker() {
    }
}
