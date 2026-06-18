package sircow.noworldbordertint.mixin;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Hud;
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

@Mixin(Hud.class)
public class HudMixin {
    @Shadow public float vignetteBrightness;
    @Shadow @Final private static Identifier VIGNETTE_LOCATION;

    @Inject(method = "extractVignette", at = @At("HEAD"), cancellable = true)
    private void nwbt$modifyVignette(GuiGraphicsExtractor graphics, Entity camera, CallbackInfo ci) {
        if (NoWorldborderTint.config.mainCategory.hideTint) {
            float brightness = Mth.clamp(this.vignetteBrightness, 0.0F, 1.0F);
            int color = ARGB.colorFromFloat(1.0F, brightness, brightness, brightness);

            graphics.blit(RenderPipelines.VIGNETTE, VIGNETTE_LOCATION, 0, 0, 0.0F, 0.0F, graphics.guiWidth(), graphics.guiHeight(), graphics.guiWidth(), graphics.guiHeight(), color);
            ci.cancel();
        }
    }
}