package io.github.braillebennett.endpoemnarration.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.braillebennett.endpoemnarration.EndPoemNarration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.WinScreen;
import net.minecraft.sounds.SoundSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.Reader;

@Mixin(WinScreen.class)
public class WinScreenMixin {
    @Inject(at = @At("HEAD"), method = "addPoemFile")
    private void narratePoem(Reader reader, CallbackInfo ci) {
        Minecraft.getInstance().player.playNotifySound(EndPoemNarration.POEM_NARRATION_SOUND_EVENT, SoundSource.VOICE, 1f, 1f);
    }

    @ModifyReturnValue(at = @At("RETURN"), method = "calculateScrollSpeed")
    private float lockScrollSpeed(float original) {
        return 1f;
    }
}
