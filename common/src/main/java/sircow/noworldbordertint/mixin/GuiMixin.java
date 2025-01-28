package sircow.noworldbordertint.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
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
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(
                GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO
        );

        float f2 = this.vignetteBrightness;
        f2 = Mth.clamp(f2, 0.0F, 1.0F);
        guiGraphics.setColor(f2, f2, f2, 1.0F);

        guiGraphics.blit(
                VIGNETTE_LOCATION,
                0,
                0,
                -90,
                0.0F,
                0.0F,
                guiGraphics.guiWidth(),
                guiGraphics.guiHeight(),
                guiGraphics.guiWidth(),
                guiGraphics.guiHeight()
        );
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();

        ci.cancel();
    }
}