package com.brlbennett.endpoemnarration;

import lombok.extern.slf4j.Slf4j;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

@Slf4j
public class EndPoemNarration {
    public static final Identifier POEM_NARRATION = Identifier.parse("end-poem-narration:poem-narration");
    public static SoundEvent POEM_NARRATION_SOUND_EVENT = SoundEvent.createVariableRangeEvent(POEM_NARRATION);

    public static void initialize() {
        log.info("Registering End Poem Narration sound.");
        Registry.register(BuiltInRegistries.SOUND_EVENT, EndPoemNarration.POEM_NARRATION, POEM_NARRATION_SOUND_EVENT);
    }
}
