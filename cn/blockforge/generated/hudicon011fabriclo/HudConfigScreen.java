package cn.blockforge.generated.hudicon011fabriclo;

import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_342;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import net.minecraft.class_7923;

public final class HudConfigScreen extends class_437 {
    private static final String[] POSITIONS = {
            "top_left", "top_center", "top_right",
            "center_left", "center", "center_right",
            "bottom_left", "bottom_center", "bottom_right"
    };

    private final class_437 parent;
    private class_342 xField;
    private class_342 yField;
    private class_342 iconField;
    private HudConfig working;
    private int positionIndex;

    public HudConfigScreen(class_437 parent) {
        super(class_2561.method_43471("screen.hud_icon_0_1_1_fabric_lo.title"));
        this.parent = parent;
        this.working = HudConfig.get();
        this.positionIndex = indexOf(working.position);
    }

    @Override
    protected void method_25426() {
        int center = this.field_22789 / 2;
        xField = new class_342(this.field_22793, center - 155, 62, 95, 20,
                class_2561.method_43471("screen.hud_icon_0_1_1_fabric_lo.x"));
        yField = new class_342(this.field_22793, center - 50, 62, 95, 20,
                class_2561.method_43471("screen.hud_icon_0_1_1_fabric_lo.y"));
        iconField = new class_342(this.field_22793, center - 155, 108, 300, 20,
                class_2561.method_43471("screen.hud_icon_0_1_1_fabric_lo.icon"));
        xField.method_1852(Integer.toString(working.offsetX));
        yField.method_1852(Integer.toString(working.offsetY));
        iconField.method_1852(working.iconId);
        method_37063(xField);
        method_37063(yField);
        method_37063(iconField);

        method_37063(class_4185.method_46430(positionText(), button -> {
            positionIndex = (positionIndex + 1) % POSITIONS.length;
            working.position = POSITIONS[positionIndex];
            button.method_25355(positionText());
        }).method_46434(center + 55, 62, 100, 20).method_46431());

        method_37063(class_4185.method_46430(class_2561.method_43471("screen.hud_icon_0_1_1_fabric_lo.scale_down"), button -> {
            working.scale = Math.max(0.5f, working.scale - 0.25f);
        }).method_46434(center - 155, 145, 95, 20).method_46431());
        method_37063(class_4185.method_46430(class_2561.method_43471("screen.hud_icon_0_1_1_fabric_lo.scale_up"), button -> {
            working.scale = Math.min(4.0f, working.scale + 0.25f);
        }).method_46434(center - 50, 145, 95, 20).method_46431());
        method_37063(class_4185.method_46430(class_2561.method_43471("screen.hud_icon_0_1_1_fabric_lo.choose_icon"), button ->
                field_22787.method_1507(new IconPickerScreen(this, id -> {
                    working.iconId = id;
                    iconField.method_1852(id);
                }))
        ).method_46434(center + 55, 145, 100, 20).method_46431());

        method_37063(class_4185.method_46430(class_2561.method_43471("screen.hud_icon_0_1_1_fabric_lo.save"), button -> {
            readFields();
            HudConfig.get().iconId = working.iconId;
            HudConfig.get().position = working.position;
            HudConfig.get().offsetX = working.offsetX;
            HudConfig.get().offsetY = working.offsetY;
            HudConfig.get().scale = working.scale;
            HudConfig.save();
            method_25419();
        }).method_46434(center - 155, this.field_22790 - 32, 145, 20).method_46431());
        method_37063(class_4185.method_46430(class_2561.method_43471("screen.hud_icon_0_1_1_fabric_lo.cancel"), button -> method_25419())
                .method_46434(center + 10, this.field_22790 - 32, 145, 20).method_46431());
    }

    private void readFields() {
        try { working.offsetX = Integer.parseInt(xField.method_1882()); } catch (NumberFormatException ignored) { }
        try { working.offsetY = Integer.parseInt(yField.method_1882()); } catch (NumberFormatException ignored) { }
        String id = iconField.method_1882().trim();
        if (class_7923.field_41178.method_17966(class_2960.method_12829(id)).isPresent()) working.iconId = id;
        working.sanitize();
    }

    private class_2561 positionText() {
        return class_2561.method_43469("screen.hud_icon_0_1_1_fabric_lo.position", positionIndex + 1);
    }

    private static int indexOf(String position) {
        for (int i = 0; i < POSITIONS.length; i++) if (POSITIONS[i].equals(position)) return i;
        return 0;
    }

    @Override
    public void method_25419() {
        field_22787.method_1507(parent);
    }

    @Override
    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        method_25420(context, mouseX, mouseY, delta);
        context.method_27534(this.field_22793, this.field_22785, this.field_22789 / 2, 15, 0xFFFFFFFF);
        context.method_27535(this.field_22793, class_2561.method_43471("screen.hud_icon_0_1_1_fabric_lo.position_label"), this.field_22789 / 2 - 155, 49, 0xFFFFFFFF);
        context.method_27535(this.field_22793, class_2561.method_43471("screen.hud_icon_0_1_1_fabric_lo.icon_label"), this.field_22789 / 2 - 155, 95, 0xFFFFFFFF);
        context.method_27535(this.field_22793, class_2561.method_43469("screen.hud_icon_0_1_1_fabric_lo.scale", String.format("%.2f", working.scale)), this.field_22789 / 2 - 155, 132, 0xFFFFFFFF);
        context.method_27535(this.field_22793, class_2561.method_43471("screen.hud_icon_0_1_1_fabric_lo.preview"), 12, 188, 0xFFFFFFFF);

        int previewX = 12;
        int previewY = 202;
        class_1792 item = class_7923.field_41178.method_17966(class_2960.method_12829(working.iconId)).orElse(net.minecraft.class_1802.field_8403);
        context.method_51427(new class_1799(item), previewX, previewY);
        context.method_25303(this.field_22793, Integer.toString(CounterHudClient.count), previewX + 18, previewY + 4, working.color);
        context.method_27535(this.field_22793, class_2561.method_43471("screen.hud_icon_0_1_1_fabric_lo.hint"), 12, 230, 0xFFBBBBBB);
        super.method_25394(context, mouseX, mouseY, delta);
    }
}
