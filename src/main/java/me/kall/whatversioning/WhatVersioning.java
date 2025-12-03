package me.kall.whatversioning;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.platform.InputConstants;
import me.kall.duplicationless.config.JsonConfig;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Mod(WhatVersioning.MOD_ID)
public final class WhatVersioning {
    public static final String MOD_ID = "whatversioning";

    public static final JsonConfig CONFIG = JsonConfig.create(MOD_ID, "1")
            .put("CustomVersioning", Lists.newArrayList("Minecraft 26H2",
                    "Minecraft 114.514",
                    "Minecraft 2/3/4/5",
                    "Minecraft blackwell",
                    "Minecraft operation black ice",
                    "Minecraft mojang is retarded",
                    "Minecraft 26.2",
                    "Minecraft MoForce 576.08",
                    "Minecraft 4™",
                    "Minecraft CopperLake",
                    "Minecraft i25-18900kx",
                    "Minecraft 42.7.04",
                    "Minecraft 0.114.5",
                    "Minecraft CreateOS 1.0.114.6",
                    "Minecraft 845",
                    "Minecraft UI 12.5 Enhanced Edition",
                    "Minecraft：第12话 我最好的史蒂夫",
                    "Minecraft: 终",
                    "Minecraft: It's MyGO!!!!!",
                    "Minecraft KB5063878",
                    "Minecraft Y16S1",
                    "Minecraft 2025 Ver.CN1.14.5G",
                    "Minecraft 9.9.25-114514",
                    "Minecraft 1/2",
                    "Minecraft Studio 581.57"
            ))
            .initialize();

    public static final List<String> VERSIONS = CONFIG.getList("CustomVersioning", String.class);

    public static String CURRENT = VERSIONS.get(ThreadLocalRandom.current().nextInt(VERSIONS.size()));

    public static final KeyMapping CHANGE_TITLE = new KeyMapping(
            "key.whatversioning.change_title",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_F9,
            "category.whatversioning.title"
    );

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT, modid = MOD_ID)
    public static final class ModEvents {
        @SubscribeEvent
        public static void key(FMLClientSetupEvent event) {
            event.enqueueWork(() -> ClientRegistry.registerKeyBinding(CHANGE_TITLE));
        }
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT, modid = MOD_ID)
    public static final class ForgeEvents {
        @SubscribeEvent
        public static void tick(TickEvent.ClientTickEvent event) {
            if (event.phase.equals(TickEvent.Phase.END) && CHANGE_TITLE.consumeClick()) {
                CURRENT = VERSIONS.get(ThreadLocalRandom.current().nextInt(VERSIONS.size()));
                Minecraft.getInstance().updateTitle();
            }
        }
    }
}
