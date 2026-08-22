package sircow.noworldbordertint.config;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.notenoughupdates.moulconfig.gui.GuiContext;
import io.github.notenoughupdates.moulconfig.gui.GuiElementComponent;
import io.github.notenoughupdates.moulconfig.gui.MoulConfigEditor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import sircow.noworldbordertint.CommonClass;

import java.lang.reflect.Constructor;

public class ConfigGuiManager {
    public static MoulConfigEditor<NWTConfig> editor = null;

    private static Screen createMoulConfigScreen(Component title, GuiContext context, Screen parent) {
        try {
            Class<?> clazz = Class.forName("io.github.notenoughupdates.moulconfig.platform.MoulConfigScreenComponent");
            Constructor<?> ctor = clazz.getDeclaredConstructor(
                    Component.class,
                    GuiContext.class,
                    Screen.class
            );
            return (Screen) ctor.newInstance(title, context, parent);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create MoulConfigScreenComponent", e);
        }
    }

    public static void openConfigGui(String search) {
        CommonClass.configManager.ensureProcessor();
        if (editor == null) editor = new MoulConfigEditor<>(CommonClass.configManager.processor);
        if (search != null) editor.search(search);

        Screen screen = createMoulConfigScreen(
                Component.empty(),
                new GuiContext(new GuiElementComponent(editor)),
                null
        );

        Minecraft.getInstance().execute(() -> Minecraft.getInstance().setScreen(screen));
    }

    public static Screen createConfigScreen(Screen previousScreen) {
        CommonClass.configManager.ensureProcessor();
        if (editor == null) editor = new MoulConfigEditor<>(CommonClass.configManager.processor);

        return createMoulConfigScreen(
                Component.empty(),
                new GuiContext(new GuiElementComponent(editor)),
                previousScreen
        );
    }

    public static <S> void registerCommands(CommandDispatcher<S> dispatcher) {
        dispatcher.register(LiteralArgumentBuilder.<S>literal("noworldbordertint").executes(context -> {
            openConfigGui(null);
            return 1;
        }));
        dispatcher.register(LiteralArgumentBuilder.<S>literal("nwt").executes(context -> {
            openConfigGui(null);
            return 1;
        }));
        dispatcher.register(LiteralArgumentBuilder.<S>literal("nwbt").executes(context -> {
            openConfigGui(null);
            return 1;
        }));
    }
}
