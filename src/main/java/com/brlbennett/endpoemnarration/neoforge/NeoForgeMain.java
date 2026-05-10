package com.brlbennett.endpoemnarration.neoforge;

import lombok.extern.slf4j.Slf4j;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Slf4j
@Mod(value = "end_poem_narration", dist = Dist.CLIENT)
public class NeoForgeMain {
    public NeoForgeMain(IEventBus modBus) {
        log.info("Registering End Poem Narration sound.");
        PoemNarrationSound.SOUND_EVENTS.register(modBus);
    }
}
