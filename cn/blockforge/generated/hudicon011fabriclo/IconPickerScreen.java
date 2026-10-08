package cn.blockforge.generated.hudicon011fabriclo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.class_11909;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_7923;

public final class IconPickerScreen extends class_437 {
    private final class_437 parent;
    private final Consumer<String> onChoose;
    private final List<class_2960> ids = new ArrayList<>();
    private double scroll;

    public IconPickerScreen(class_437 parent, Consumer<String> onChoose) {
        super(class_2561.method_43471("screen.hud_icon_0_1_1_fabric_lo.icon_picker"));
        this.parent = parent;
        this.onChoose = onChoose;
        ids.addAll(class_7923.field_41178.method_10235());
        ids.sort(Comparator.comparing(class_2960::toString));
    }

    @Override
    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        method_25420(context, mouseX, mouseY, delta);
        context.method_27534(this.field_22793, this.field_22785, this.field_22789 / 2, 12, 0xFFFFFFFF);
        context.method_27534(this.field_22793,
                class_2561.method_43471("screen.hud_icon_0_1_1_fabric_lo.icon_picker_hint"), this.field_22789 / 2, 28, 0xFFCCCCCC);
        int cell = 62;
        int columns = Math.max(1, (this.field_22789 - 20) / cell);
        int top = 44;
        int rows = (this.field_22790 - 58) / 42;
        int firstRow = Math.max(0, (int) scroll);
        int first = firstRow * columns;
        context.method_44379(0, top, this.field_22789, this.field_22790 - 26);
        for (int i = first; i < ids.size() && i < first + rows * columns; i++) {
            int local = i - first;
            int col = local % columns;
            int row = local / columns;
            int x = 10 + col * cell;
            int y = top + row * 42;
            class_1792 item = class_7923.field_41178.method_17966(ids.get(i)).orElse(null);
            if (item == null) continue;
            context.method_51427(new class_1799(item), x + 2, y);
            String id = ids.get(i).toString();
            String shortId = id.length() > 9 ? id.substring(id.indexOf(':') + 1) : id;
            context.method_51433(this.field_22793, shortId, x, y + 19, 0xFFFFFFFF, true);
        }
        context.method_44380();
        context.method_27534(this.field_22793,
                class_2561.method_43471("screen.hud_icon_0_1_1_fabric_lo.back"), this.field_22789 / 2, this.field_22790 - 18, 0xFFFFFFFF);
    }

    @Override
    public boolean method_25402(class_11909 click, boolean doubled) {
        if (click.method_74245() == 0 && click.comp_4799() >= 44 && click.comp_4799() < this.field_22790 - 26) {
            int cell = 62;
            int columns = Math.max(1, (this.field_22789 - 20) / cell);
            int col = (int) ((click.comp_4798() - 10) / cell);
            int row = (int) ((click.comp_4799() - 44) / 42);
            if (col >= 0 && col < columns && row >= 0) {
                int index = ((int) scroll) * columns + row * columns + col;
                if (index >= 0 && index < ids.size()) {
                    onChoose.accept(ids.get(index).toString());
                    field_22787.method_1507(parent);
                    return true;
                }
            }
        }
        if (click.method_74245() == 0 && click.comp_4799() >= this.field_22790 - 30) {
            field_22787.method_1507(parent);
            return true;
        }
        return super.method_25402(click, doubled);
    }

    @Override
    public boolean method_25401(double mouseX, double mouseY, double horizontal, double vertical) {
        int cell = 62;
        int columns = Math.max(1, (this.field_22789 - 20) / cell);
        int maxRows = Math.max(0, (ids.size() + columns - 1) / columns - 1);
        scroll = Math.max(0, Math.min(maxRows, scroll - vertical * 3));
        return true;
    }

    @Override
    public void method_25419() {
        field_22787.method_1507(parent);
    }
}
