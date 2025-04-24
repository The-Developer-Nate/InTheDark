package com.mlxoa.inthedark.client.mixin;

import com.mlxoa.inthedark.Main;
import net.minecraft.client.render.WorldRenderer;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = WorldRenderer.class, priority = 1100)
public abstract class MixinWorldRenderer {
    @Inject(at = @At(value = "HEAD"), method = "renderSky", cancellable = true)
    private void renderSky(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(at = @At(value = "HEAD"), method = "renderClouds", cancellable = true)
    private void renderClouds(CallbackInfo ci) {
        ci.cancel();
    }
}