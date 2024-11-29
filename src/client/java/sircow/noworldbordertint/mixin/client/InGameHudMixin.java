package sircow.noworldbordertint.mixin.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Shadow public float vignetteDarkness;
    @Shadow @Final private static Identifier VIGNETTE_TEXTURE;

    @Inject(method = "renderVignetteOverlay", at = @At("HEAD"), cancellable = true)
    private void sir_cow$modifyVignette(DrawContext context, Entity entity, CallbackInfo ci) {
        int i;
        float h = this.vignetteDarkness;
        h = MathHelper.clamp(h, 0.0F, 1.0F);
        i = ColorHelper.fromFloats(1.0F, h, h, h);

        context.drawTexture(
                RenderLayer::getVignette, VIGNETTE_TEXTURE, 0, 0, 0.0F, 0.0F,
                context.getScaledWindowWidth(),
                context.getScaledWindowHeight(),
                context.getScaledWindowWidth(),
                context.getScaledWindowHeight(),
                i
        );
        ci.cancel();
    }
}
