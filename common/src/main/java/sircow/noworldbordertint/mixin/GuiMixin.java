package sircow.noworldbordertint.mixin;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {
    @Shadow
    public float vignetteBrightness;
    @Shadow @Final
    private static ResourceLocation VIGNETTE_LOCATION;
    @Inject(method = "renderVignette", at = @At("HEAD"), cancellable = true)
    private void sir_cow$modifyVignette(GuiGraphics guiGraphics, Entity entity, CallbackInfo ci) {
        int i;
        float h = this.vignetteBrightness;
        h = Mth.clamp(h, 0.0F, 1.0F);
        i = ARGB.colorFromFloat(1.0F, h, h, h);

        guiGraphics.blit(
                RenderPipelines.VIGNETTE,
                VIGNETTE_LOCATION, 0, 0, 0.0F, 0.0F,
                guiGraphics.guiWidth(),
                guiGraphics.guiHeight(),
                guiGraphics.guiWidth(),
                guiGraphics.guiHeight(),
                i
        );
        ci.cancel();
    }
}
