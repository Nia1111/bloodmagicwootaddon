package wootrevived.bloodmagicwootaddon.mixins;

import guideme.compiler.PageCompiler;
import guideme.document.flow.LytFlowParent;
import guideme.libs.mdast.mdx.model.MdxJsxElementFields;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wootrevived.bloodmagicwootaddon.config.AddonConfig;
import wootrevived.woot.guide.ConfigPropertyCompiler;
import wootrevived.woot.util.render.WootContainerScreen;

@Mixin(ConfigPropertyCompiler.class)
public class ConfigPropertyCompilerMixin {
    @Inject(method = "compile", at = @At("HEAD"), cancellable = true, remap = false)
    private void bloodmagicwootaddon$compile(PageCompiler compiler, LytFlowParent parent, MdxJsxElementFields el, CallbackInfo ci)
    {
        String key = el.getAttributeString("key", "");

        if(!key.equals("addon.bloodmagic.blood_collector_cost"))
            return;

        parent.appendText(WootContainerScreen.formatInteger(AddonConfig.BLOOD_COLLECTOR_COST.get()));
        ci.cancel();
    }
}
