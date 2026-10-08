package cn.blockforge.generated.hudicon011fabriclo;

import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_7923;

public final class HudRenderer {
    private HudRenderer() {}

    public static void render(class_332 context) {
        HudConfig config = HudConfig.get();
        class_310 client = class_310.method_1551();
        if (client.field_1690.field_1842 || client.field_1755 != null && client.field_1755.method_25421()) {
            return;
        }

        class_1792 item = class_7923.field_41178.method_17966(class_2960.method_12829(config.iconId))
                .orElse(net.minecraft.class_1802.field_8403);
        class_1799 stack = new class_1799(item);
        String value = Integer.toString(CounterHudClient.count);
        int textWidth = client.field_1772.method_1727(value);
        float scale = config.scale;
        int widgetWidth = Math.round((18 + textWidth) * scale);
        int widgetHeight = Math.round(16 * scale);
        int x = getX(config.position, context.method_51421(), widgetWidth, config.offsetX);
        int y = getY(config.position, context.method_51443(), widgetHeight, config.offsetY);

        var matrices = context.method_51448();
        matrices.pushMatrix();
        matrices.translate(x, y);
        matrices.scale(scale, scale);
        context.method_51427(stack, 0, 0);
        if (config.shadow) {
            context.method_27535(client.field_1772, class_2561.method_43470(value), 18, 4, config.color);
        } else {
            context.method_51439(client.field_1772, class_2561.method_43470(value), 18, 4, config.color, false);
        }
        matrices.popMatrix();
    }

    private static int getX(String position, int width, int widgetWidth, int offset) {
        if (position.endsWith("right")) return width - widgetWidth - offset;
        if (position.endsWith("center") || position.equals("center")) return (width - widgetWidth) / 2 + offset;
        return offset;
    }

    private static int getY(String position, int height, int widgetHeight, int offset) {
        if (position.startsWith("bottom")) return height - widgetHeight - offset;
        if (position.startsWith("center") || position.equals("center")) return (height - widgetHeight) / 2 + offset;
        return offset;
    }
}
