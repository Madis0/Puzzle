package net.puzzlemc.gui;

import net.fabricmc.loader.api.FabricLoader;
import net.irisshaders.iris.api.v0.IrisApi;
import net.irisshaders.iris.api.v0.IrisApiConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.puzzlemc.gui.screen.widget.PuzzleWidget;

public class IrisCompat {
    public static void init() {
        if (FabricLoader.getInstance().isModLoaded("iris")) {
            PuzzleApi.addToGraphicsOptions(new PuzzleWidget(Text.of("Iris")));
            PuzzleApi.addToGraphicsOptions(new PuzzleWidget(Text.of("Enable Shaders"), (button) -> button.setMessage(IrisApi.getInstance().getConfig().areShadersEnabled() ? PuzzleClient.YES : PuzzleClient.NO), (button) -> {
                IrisApiConfig irisConfig = IrisApi.getInstance().getConfig();
                irisConfig.setShadersEnabledAndApply(!irisConfig.areShadersEnabled());
            }));
            PuzzleApi.addToGraphicsOptions(new PuzzleWidget(new TranslatableText("options.iris.shaderPackSelection.title"), (button) -> button.setMessage(Text.of("OPEN")), (button) -> {
                MinecraftClient client = MinecraftClient.getInstance();
                client.setScreen((Screen) IrisApi.getInstance().openMainIrisScreenObj(client.currentScreen));
            }));
        }
    }
}
