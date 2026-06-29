package com.barefootbird.birdaddon.features.impl.skyblock

import com.barefootbird.birdaddon.utils.Category
import com.odtheking.odin.clickgui.settings.impl.BooleanSetting
import com.odtheking.odin.clickgui.settings.impl.StringSetting
import com.odtheking.odin.features.Module
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.FormattedText
import net.minecraft.network.chat.Style
import net.minecraft.util.FormattedCharSequence

object NameChanger : Module(
    name = "NameChanger",
    description = "Visually replaces your IGN in rendered text.",
    category = Category.SKYBLOCK
) {
    private val replaceIgn by BooleanSetting("Replace IGN", true, desc = "Visually replaces your Minecraft username")
    private val ignReplacement by StringSetting("IGN Replacement", "", 64, desc = "Text to show instead of your username")

    @JvmStatic
    fun replaceText(text: String?): String? {
        if (text == null || !enabled) return text

        var replaced: String = text

        if (replaceIgn && ignReplacement.isNotEmpty()) {
            val username = mc.user.name
            if (username.isNotEmpty()) replaced = replaced.replace(username, ignReplacement)
        }

        return replaced
    }

    @JvmStatic
    fun replaceComponent(component: Component?): Component? {
        if (component == null || !enabled) return component

        val replaced = replaceText(component.string) ?: return component
        if (replaced == component.string) return component

        return Component.literal(replaced)
    }

    @JvmStatic
    fun replaceFormattedText(text: FormattedText?): FormattedText? {
        if (text !is Component) return text
        return replaceComponent(text)
    }

    @JvmStatic
    fun replaceSequence(sequence: FormattedCharSequence?): FormattedCharSequence? {
        if (sequence == null || !enabled) return sequence

        val builder = StringBuilder()
        sequence.accept { _, _, codePoint ->
            builder.appendCodePoint(codePoint)
            true
        }

        val replaced = replaceText(builder.toString()) ?: return sequence
        if (replaced == builder.toString()) return sequence

        return FormattedCharSequence.forward(replaced, Style.EMPTY)
    }
}
