package cn.blockforge.generated.hudicon011fabriclo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class HudConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE = FabricLoader.getInstance().getConfigDir()
            .resolve("hud_icon_counter.json");

    public String iconId = "minecraft:iron_pickaxe";
    public String position = "top_left";
    public int offsetX = 8;
    public int offsetY = 8;
    public float scale = 1.0f;
    public int color = 0xFFFF5555;
    public boolean shadow = true;

    private static HudConfig instance = new HudConfig();

    public static HudConfig get() {
        return instance;
    }

    public static void load() {
        try {
            if (Files.exists(FILE)) {
                instance = GSON.fromJson(Files.readString(FILE), HudConfig.class);
                if (instance == null) {
                    instance = new HudConfig();
                }
            }
        } catch (Exception ignored) {
            instance = new HudConfig();
        }
        instance.sanitize();
    }

    public static void save() {
        instance.sanitize();
        try {
            Files.createDirectories(FILE.getParent());
            Files.writeString(FILE, GSON.toJson(instance));
        } catch (IOException ignored) {
            // 配置无法写入时仍然保留本次游戏内设置。
        }
    }

    public void sanitize() {
        if (iconId == null || iconId.isBlank()) iconId = "minecraft:iron_pickaxe";
        if (position == null || !isPosition(position)) position = "top_left";
        offsetX = Math.max(0, Math.min(2000, offsetX));
        offsetY = Math.max(0, Math.min(2000, offsetY));
        scale = Math.max(0.5f, Math.min(4.0f, scale));
        if ((color & 0xFF000000) == 0) color = 0xFFFF5555;
    }

    public static boolean isPosition(String value) {
        return value.equals("top_left") || value.equals("top_center") || value.equals("top_right")
                || value.equals("center_left") || value.equals("center") || value.equals("center_right")
                || value.equals("bottom_left") || value.equals("bottom_center") || value.equals("bottom_right");
    }
}
