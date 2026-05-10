package com.brlbennett.endpoemnarration.neoforge;

import com.brlbennett.endpoemnarration.EndPoemNarration;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = EndPoemNarration.MOD_ID, dist = Dist.CLIENT)
public class NeoForgeMain {
    public NeoForgeMain() {
        EndPoemNarration.initialize();
    }
}
