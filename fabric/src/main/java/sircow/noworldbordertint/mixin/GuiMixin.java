package sircow.noworldbordertint.mixin;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sircow.noworldbordertint.NoWorldborderTint;

@Mixin(Gui.class)
public class GuiMixin {
    @Shadow public float vignetteBrightness;
    @Shadow @Final private static Identifier VIGNETTE_LOCATION;
    @Inject(method = "extractVignette", at = @At("HEAD"), cancellable = true)
    private void sir_cow$modifyVignette(GuiGraphicsExtractor graphics, Entity camera, CallbackInfo ci) {
        if (NoWorldborderTint.config.mainCategory.hideTint) {
            int i;
            float h = this.vignetteBrightness;
            h = Mth.clamp(h, 0.0F, 1.0F);
            i = ARGB.colorFromFloat(1.0F, h, h, h);

            graphics.blit(
                    RenderPipelines.VIGNETTE,
                    VIGNETTE_LOCATION, 0, 0, 0.0F, 0.0F,
                    graphics.guiWidth(),
                    graphics.guiHeight(),
                    graphics.guiWidth(),
                    graphics.guiHeight(),
                    i
            );
            ci.cancel();
        }
    }
}
