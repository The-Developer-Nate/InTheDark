package com.mlxoa.inthedark.mixin;

import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = World.class)
public abstract class MixinWorld {
    @Inject(at = @At("HEAD"), method = "isDay", cancellable = true)
    private void isDay(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    @Inject(at = @At("HEAD"), method = "isNight", cancellable = true)
    private void isNight(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
