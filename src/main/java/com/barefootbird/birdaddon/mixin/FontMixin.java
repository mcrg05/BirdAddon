package com.barefootbird.birdaddon.mixin;

import com.barefootbird.birdaddon.features.impl.skyblock.NameChanger;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Font.class)
public class FontMixin {
    @ModifyVariable(
            method = "prepareText(Ljava/lang/String;FFIZI)Lnet/minecraft/client/gui/Font$PreparedText;",
            at = @At("HEAD"),
            argsOnly = true
    )
    private String replacePrepareTextString(String text) {
        return NameChanger.replaceText(text);
    }

    @ModifyVariable(
            method = "prepareText(Lnet/minecraft/util/FormattedCharSequence;FFIZZI)Lnet/minecraft/client/gui/Font$PreparedText;",
            at = @At("HEAD"),
            argsOnly = true
    )
    private FormattedCharSequence replacePrepareTextSequence(FormattedCharSequence sequence) {
        return NameChanger.replaceSequence(sequence);
    }

    @ModifyVariable(
            method = "width(Ljava/lang/String;)I",
            at = @At("HEAD"),
            argsOnly = true
    )
    private String replaceWidthString(String text) {
        return NameChanger.replaceText(text);
    }

    @ModifyVariable(
            method = "width(Lnet/minecraft/network/chat/FormattedText;)I",
            at = @At("HEAD"),
            argsOnly = true
    )
    private FormattedText replaceWidthText(FormattedText text) {
        return NameChanger.replaceFormattedText(text);
    }

    @ModifyVariable(
            method = "width(Lnet/minecraft/util/FormattedCharSequence;)I",
            at = @At("HEAD"),
            argsOnly = true
    )
    private FormattedCharSequence replaceWidthSequence(FormattedCharSequence sequence) {
        return NameChanger.replaceSequence(sequence);
    }
}
