package net.alba.oldworld.client.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

@Environment(EnvType.CLIENT)
public class CrystalModel extends EntityModel<Entity> {
	private final ModelPart base;

	public CrystalModel(ModelPart root) {
		this.base = root.getChild("base");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData base = modelPartData.addChild("base", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		base.addChild("cube4_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -11.0F, 0.0F, -0.6109F, 0.5236F, -0.9599F));
		base.addChild("cube3_r1", ModelPartBuilder.create().uv(0, 6).cuboid(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -11.0F, 0.0F, 0.0F, 0.0F, 0.7854F));
		base.addChild("cube1_r1", ModelPartBuilder.create().uv(0, 6).cuboid(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -11.0F, 0.0F, -1.5708F, -0.7854F, 1.5708F));
		base.addChild("cube2_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -11.0F, 0.0F, -0.6109F, -0.5236F, 0.9599F));
		return TexturedModelData.of(modelData, 16, 16);
	}
	
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		base.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}