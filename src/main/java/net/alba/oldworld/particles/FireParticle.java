package net.alba.oldworld.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class FireParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;
  
    FireParticle(ClientWorld world, double x, double y, double z, double velX, double velY, double velZ, SpriteProvider spriteProvider) {
        super(world, x, y, z);
        this.spriteProvider = spriteProvider; //Sets the sprite provider from above to the sprite provider in the constructor parameters
        this.maxAge = 17; //100 ticks = 5 seconds
        this.scale = 0.25f;
        this.velocityX = velX; 
        this.velocityY = velY;
        this.velocityZ = velZ;
        this.x = x; //The x from the constructor parameters
        this.y = y;
        this.z = z;
        this.gravityStrength = 0; //Allows the particle to slowly fall
        this.collidesWithWorld = false;
        this.alpha = 1.0f; //Setting the alpha to 1.0f means there will be no opacity change until the alpha value is changed;
        this.setSpriteForAge(spriteProvider); //Required
    }

    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        this.prevAngle = this.angle; //required for rotating the particle
        if (this.age++ >= this.maxAge || this.alpha <= 0.0F) { //Despawns the particle if the age has reached the max age, or if the scale is 0
            this.markDead(); //Despawns the particle
        } 
        else {
            if (this.age > 8) {
                this.alpha -= 0.05f;
            }
            this.velocityX += (double)(this.random.nextFloat() / 1000.0F * (float)(this.random.nextBoolean() ? 1 : -1));
            this.velocityY += (double)(this.random.nextFloat() / 1000.0F * (float)(this.random.nextBoolean() ? 1 : -1));
            this.velocityZ += (double)(this.random.nextFloat() / 1000.0F * (float)(this.random.nextBoolean() ? 1 : -1));
            this.setSpriteForAge(this.spriteProvider); //Animates the particle if needed
            this.move(this.velocityX, this.velocityY, this.velocityZ);
        }
    }

    @Override
    public ParticleTextureSheet getType() {
        //Particle texture sheet method
        //Allows for the particle to determine its texture "type" to a degree
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        //The factory used in a particle's registry
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double x, double y, double z, double velX, double velY, double velZ) {
            return new FireParticle(clientWorld, x, y, z, velX, velY, velZ, this.spriteProvider);
        }
    }
}
