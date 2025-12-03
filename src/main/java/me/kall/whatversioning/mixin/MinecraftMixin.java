package me.kall.whatversioning.mixin;

import me.kall.whatversioning.WhatVersioning;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Inject(method = "createTitle", at = @At("HEAD"), cancellable = true)
    private void customVersioning(CallbackInfoReturnable<String> cir) {
        cir.setReturnValue(WhatVersioning.CURRENT);
    }
}
