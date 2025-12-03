package me.kall.whatversioning.mixin;

import me.kall.whatversioning.WhatVersioning;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.ThreadLocalRandom;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Inject(method = "createTitle", at = @At("HEAD"), cancellable = true)
    private void customVersioning(CallbackInfoReturnable<String> cir) {
        if (WhatVersioning.VERSIONS.isEmpty()) return;
        cir.setReturnValue(WhatVersioning.VERSIONS.get(ThreadLocalRandom.current().nextInt(WhatVersioning.VERSIONS.size())));
    }
}
