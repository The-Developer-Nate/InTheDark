package com.mlxoa.inthedark.client.mixin;

import com.mlxoa.inthedark.client.AmbiencePlayer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.MusicTracker;
import net.minecraft.client.sound.SoundInstance;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(value = MusicTracker.class)
public abstract class MixinMusicTracker {
    private AmbiencePlayer ambiencePlayer;

    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(at = @At("HEAD"), method = "tick", cancellable = true)
    private void tick(CallbackInfo ci) {
        if (ambiencePlayer == null) {
            ambiencePlayer = new AmbiencePlayer(client);
        }
        if (client.player != null) {
            ambiencePlayer.Tick();
            ci.cancel();
        }
    }
}
