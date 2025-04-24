package com.mlxoa.inthedark.client.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.FogShape;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BackgroundRenderer.class)
public abstract class MixinBackgroundRenderer {
    @Inject(at = @At("HEAD"), method = "applyFog", cancellable = true)
    private static void getShaderFogStart(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
        RenderSystem.setShaderFogStart(0);
        RenderSystem.setShaderFogEnd(25);
        RenderSystem.setShaderFogShape(FogShape.SPHERE);
        RenderSystem.setShaderFogColor(0, 0, 0, 1);
        ci.cancel();
    }

    @Inject(at = @At("HEAD"), method = "render", cancellable = true)
    private static void render(Camera camera, float tickDelta, ClientWorld world, int viewDistance, float skyDarkness, CallbackInfo ci) {
        RenderSystem.clearColor(0, 0, 0, 0.0F);
        ci.cancel();
    }
}
