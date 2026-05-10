package com.brlbennett.endpoemnarration.fabric;

import com.brlbennett.endpoemnarration.EndPoemNarration;
import net.fabricmc.api.ClientModInitializer;

public class FabricMain implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EndPoemNarration.initialize();
    }
}
