package io.github.braillebennett.endpoemnarration.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.braillebennett.endpoemnarration.EndPoemNarration;
import lombok.extern.slf4j.Slf4j;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.WinScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Slf4j
@Mixin(value = WinScreen.class, priority = 999)
abstract class WinScreenMixin {
    @Shadow
    @Final
    private boolean poem;

    @Shadow
    private List<FormattedCharSequence> lines;

    @Shadow
    private float scroll;

    @Unique
    private boolean hasPlayed = false;

    @Unique
    private double savedMusicVolume;

    @Unique
    private final double poemMusicVolume = 0.20;

    @Unique
    private int poemLength;

    @Inject(at = @At("HEAD"), method = "init")
    private void narratePoem(CallbackInfo ci) {
        Minecraft client = Minecraft.getInstance();
        if (!hasPlayed && poem) {
            savedMusicVolume = client.options.getSoundSourceVolume(SoundSource.MUSIC);
            if (savedMusicVolume > poemMusicVolume) {
                client.options.getSoundSourceOptionInstance(SoundSource.MUSIC).set(poemMusicVolume);
            }
            log.info("Playing the poem narration.");
            client.player.playNotifySound(EndPoemNarration.POEM_NARRATION_SOUND_EVENT, SoundSource.VOICE, 1f, 1f);
            hasPlayed = true;
            if (client.options.getSoundSourceVolume(SoundSource.VOICE) < 0.60) {
                log.warn("Your speech/voice sound source may be to quiet too properly hear the poem narration");
            }
        } else if (hasPlayed && poem) {
            log.error("The narration failed to start because it is already playing, or has already played this time.");
        }
    }

    @ModifyReturnValue(at = @At("RETURN"), method = "calculateScrollSpeed")
    private float lockScrollSpeed(float original) {
        return inPoem() ? 0.416F : original;
    }

    @Inject(at = @At("TAIL"), method = "tick")
    private void restoreMusicVolumeForCredits(CallbackInfo ci) {
        if (!inPoem()) {
            restoreMusicVolume();
        }
    }

    @Inject(at = @At("HEAD"), method = "respawn")
    private void forceRestoreMusicVolume(CallbackInfo ci) {
        hasPlayed = false;
        restoreMusicVolume();
    }

    @ModifyReturnValue(at = @At("RETURN"), method = "getNarrationMessage")
    private Component suppressPoemTTS(Component original) {
        return poem ? CommonComponents.EMPTY : original;
    }

    @Inject(at = @At("TAIL"), method = "addPoemLines")
    private void capturePoemSize(String text, CallbackInfo ci) {
        poemLength = lines.size();
    }

    @Unique
    private boolean inPoem() {
        int windowHeight = Minecraft.getInstance().getWindow().getHeight();
        return poem && scroll <= poemLength + windowHeight;
    }

    @Unique
    private void restoreMusicVolume() {
        if (savedMusicVolume > poemMusicVolume) {
            Minecraft.getInstance().options.getSoundSourceOptionInstance(SoundSource.MUSIC).set(savedMusicVolume);
            log.info("Music volume was restored");
            savedMusicVolume = 0.0;
        }
    }
}
