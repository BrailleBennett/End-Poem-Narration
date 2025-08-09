package io.github.braillebennett.endpoemnarration;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndPoemNarration implements ClientModInitializer {
    public static final String MOD_ID = "end-poem-narration";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final ResourceLocation POEM_NARRATION = ResourceLocation.parse("end-poem-narration:poem-narration");
    public static SoundEvent POEM_NARRATION_SOUND_EVENT = SoundEvent.createVariableRangeEvent(POEM_NARRATION);
    //public static final ResourceLocation POEM_NARRATION = new ResourceLocation(MOD_ID, "poem_narration"); // path
    //public static final SoundEvent POEM_NARRATION_SOUND_EVENT = SoundEvent.createVariableRangeEvent(POEM_NARRATION);

    @Override
    public void onInitializeClient() {
        Registry.register(BuiltInRegistries.SOUND_EVENT, EndPoemNarration.POEM_NARRATION, POEM_NARRATION_SOUND_EVENT);
    }
}
