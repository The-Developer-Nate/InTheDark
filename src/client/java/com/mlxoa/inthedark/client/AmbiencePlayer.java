package com.mlxoa.inthedark.client;

import com.jcraft.jorbis.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class AmbiencePlayer {
    private final MinecraftClient mc;
    private final Random random = new Random();
    private SoundInstance currentSound;
    private SoundInstance currentNoise;
    private int noiseDelay = random.nextInt(3600)*20;
    private boolean wasPaused = false;

    private final String[] ambience = {
            "rain0",
            "generic0",
            "forest0",
            "forest1"
    };

    private final String[] caveNoises = {
            "ambience0",
            "ambience1"
    };

    public AmbiencePlayer(MinecraftClient client) {
        mc = client;
    }

    public boolean isv(BlockPos pos) {
        if (mc.world == null) return false;
        return !mc.world.isSkyVisible(pos);
    }

    public boolean isInCave(BlockPos pos) {
        return
                !(isv(pos) && isv(pos.north()) && isv(pos.north(2))
                && isv(pos.east()) && isv(pos.east(2))
                && isv(pos.south()) && isv(pos.south(2))
                && isv(pos.west()) && isv(pos.west(2))
                && isv(pos.east().north()) && isv(pos.south().east()) && isv(pos.west().south()) && isv(pos.north().west()));
    }

    public void Tick() {
        if (mc.player == null || mc.world == null) return;
        if (isInCave(mc.player.getBlockPos()) && isInCave(mc.player.getBlockPos().up())) {
            mc.getSoundManager().updateSoundVolume(SoundCategory.MUSIC, 1F);
            if (currentSound != null && mc.getSoundManager().isPlaying(currentSound)) {
                return;
            }
            currentSound = PositionedSoundInstance.music(
                    SoundEvent.of(
                            Identifier.of("inthedark", ambience[random.nextInt(ambience.length)])
                    )
            );
            mc.getSoundManager().play(currentSound);
        } else {
            mc.getSoundManager().stop(currentSound);
            if (currentNoise != null && mc.getSoundManager().isPlaying(currentNoise)) return;
            if (noiseDelay > 0) { noiseDelay--; return; }
            currentNoise = PositionedSoundInstance.ambient(
                    SoundEvent.of(
                            Identifier.of("inthedark", caveNoises[random.nextInt(caveNoises.length)])
                    )
            );
            mc.getSoundManager().play(currentNoise);
            noiseDelay = random.nextInt(3600)*20;
        }
    }
}
