package com.brlbennett.endpoemnarration.fabric;

import lombok.extern.slf4j.Slf4j;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

@Slf4j
public class FabricMain implements ClientModInitializer {
    public static final Identifier POEM_NARRATION = Identifier.fromNamespaceAndPath("end_poem_narration", "poem_narration");
    public static SoundEvent POEM_NARRATION_SOUND_EVENT = SoundEvent.createVariableRangeEvent(POEM_NARRATION);

    @Override
    public void onInitializeClient() {
        log.info("Registering End Poem Narration sound.");
        Registry.register(BuiltInRegistries.SOUND_EVENT, POEM_NARRATION, POEM_NARRATION_SOUND_EVENT);
    }
}
