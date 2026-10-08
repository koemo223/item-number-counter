package cn.blockforge.generated.hudicon011fabriclo;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.class_2960;
import net.minecraft.class_304;
import net.minecraft.class_3675;

public final class CounterHudClient implements ClientModInitializer {
    public static int count = 0;

    public static class_304 plusKey;
    public static class_304 minusKey;
    public static class_304 resetKey;

    private static final class_304.class_11900 CATEGORY = class_304.class_11900.method_74698(
            class_2960.method_60655(GeneratedMod.MOD_ID, "keys")
    );

    @Override
    public void onInitializeClient() {
        HudConfig.load();

        plusKey = KeyBindingHelper.registerKeyBinding(new class_304(
                "key.hud_icon_0_1_1_fabric_lo.plus", class_3675.class_307.field_1668,
                class_3675.field_31937, CATEGORY
        ));
        minusKey = KeyBindingHelper.registerKeyBinding(new class_304(
                "key.hud_icon_0_1_1_fabric_lo.minus", class_3675.class_307.field_1668,
                class_3675.field_31941, CATEGORY
        ));
        resetKey = KeyBindingHelper.registerKeyBinding(new class_304(
                "key.hud_icon_0_1_1_fabric_lo.reset", class_3675.class_307.field_1668,
                class_3675.field_31940, CATEGORY
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (plusKey.method_1436()) {
                count++;
            }
            while (minusKey.method_1436()) {
                count--;
            }
            while (resetKey.method_1436()) {
                count = 0;
            }
        });

        HudRenderCallback.EVENT.register((drawContext, tickCounter) ->
                HudRenderer.render(drawContext)
        );
    }
}
