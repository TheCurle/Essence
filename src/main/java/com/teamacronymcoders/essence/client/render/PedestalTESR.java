package com.teamacronymcoders.essence.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.teamacronymcoders.essence.blocks.infusion.tile.InfusionPedestalTile;
import com.teamacronymcoders.essence.utils.EssenceObjectHolders;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;

public class PedestalTESR extends TileEntityRenderer<InfusionPedestalTile> {

    public PedestalTESR(TileEntityRendererDispatcher rendererDispatcher) {
        super(rendererDispatcher);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void render(InfusionPedestalTile tile, float partial, MatrixStack matrixStack, IRenderTypeBuffer buffer, int overlay, int light) {
        BlockState state = tile.getWorld().getBlockState(tile.getPos());
        if (state.getBlock() != EssenceObjectHolders.INFUSION_PEDESTAL) {
            return;
        }
        ItemStack stack = tile.getStack();
        if (!stack.isEmpty() && stack.getCount() > 0) {
            matrixStack.push();
            matrixStack.translate(0.5, 1.07, 0.5);
            matrixStack.scale(0.5f, 0.5f, 0.5f);

            Minecraft mc = Minecraft.getInstance();
            ItemRenderer renderer = mc.getItemRenderer();
            IBakedModel model = renderer.getItemModelWithOverrides(stack, tile.getWorld(), null);

            renderer.renderItem(stack, ItemCameraTransforms.TransformType.NONE, true, matrixStack, buffer, overlay, light, model);
            matrixStack.pop();
        }
    }
}
