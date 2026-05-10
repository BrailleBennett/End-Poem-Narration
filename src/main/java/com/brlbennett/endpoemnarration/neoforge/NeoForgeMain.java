package com.brlbennett.endpoemnarration.neoforge;

import com.brlbennett.endpoemnarration.EndPoemNarration;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(value = EndPoemNarration.MOD_ID, dist = Dist.CLIENT)
public class NeoForgeMain {
    public NeoForgeMain(IEventBus modBus) {
        PoemNarrationSound.SOUND_EVENTS.register(modBus);
    }
}
