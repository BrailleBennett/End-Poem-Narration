package com.brlbennett.endpoemnarration.neoforge;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PoemNarrationSound {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, "end_poem_narration");
    public static final Holder<SoundEvent> MY_SOUND = SOUND_EVENTS.register("poem_narration", SoundEvent::createVariableRangeEvent);
}
