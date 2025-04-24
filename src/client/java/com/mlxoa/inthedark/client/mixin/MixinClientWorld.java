package com.mlxoa.inthedark.client.mixin;

import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ClientWorld.class)
public abstract class MixinClientWorld {
    @Inject(at = @At("HEAD"), method = "getSkyBrightness", cancellable = true)
    private void getSkyBrightness(float tickDelta, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(0F);
    }
}
