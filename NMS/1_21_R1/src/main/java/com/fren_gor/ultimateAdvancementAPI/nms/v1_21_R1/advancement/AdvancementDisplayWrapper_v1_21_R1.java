package com.fren_gor.ultimateAdvancementAPI.nms.v1_21_R1.advancement;

import com.fren_gor.ultimateAdvancementAPI.nms.v1_21_R1.Util;
import com.fren_gor.ultimateAdvancementAPI.nms.wrappers.advancement.AdvancementDisplayWrapper;
import com.fren_gor.ultimateAdvancementAPI.nms.wrappers.advancement.AdvancementFrameTypeWrapper;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.PlainTextContents.LiteralContents;
import net.minecraft.resources.ResourceLocation;
import org.bukkit.craftbukkit.v1_21_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_21_R1.util.CraftChatMessage;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class AdvancementDisplayWrapper_v1_21_R1 extends AdvancementDisplayWrapper {

    private final DisplayInfo display;
    private final AdvancementFrameTypeWrapper frameType;

    public AdvancementDisplayWrapper_v1_21_R1(@NotNull ItemStack icon, @NotNull String title, @NotNull String description, @NotNull AdvancementFrameTypeWrapper frameType, float x, float y, boolean showToast, boolean announceChat, boolean hidden, @Nullable String backgroundTexture) {
        ResourceLocation background = backgroundTexture == null ? null : ResourceLocation.parse(backgroundTexture);
        this.display = new DisplayInfo(CraftItemStack.asNMSCopy(icon), Util.fromString(title), Util.fromString(description), Optional.ofNullable(background), (AdvancementType) frameType.toNMS(), showToast, announceChat, hidden);
        this.display.setLocation(x, y);
        this.frameType = frameType;
    }

    @Override
    @NotNull
    public ItemStack getIcon() {
        return CraftItemStack.asBukkitCopy(display.getIcon());
    }

    @Override
    @NotNull
    public String getTitle() {
        return CraftChatMessage.fromComponent(display.getTitle());
    }

    @Override
    @NotNull
    public String getDescription() {
        return CraftChatMessage.fromComponent(display.getDescription());
    }

    @Override
    @NotNull
    public AdvancementFrameTypeWrapper getAdvancementFrameType() {
        return frameType;
    }

    @Override
    public float getX() {
        return display.getX();
    }

    @Override
    public float getY() {
        return display.getY();
    }

    @Override
    public boolean doesShowToast() {
        return display.shouldShowToast();
    }

    @Override
    public boolean doesAnnounceToChat() {
        return display.shouldAnnounceChat();
    }

    @Override
    public boolean isHidden() {
        return display.isHidden();
    }

    @Override
    @Nullable
    public String getBackgroundTexture() {
        Optional<ResourceLocation> r = display.getBackground();
        return r.isEmpty() ? null : r.toString();
    }

    @Override
    @NotNull
    public DisplayInfo toNMS() {
        return display;
    }
}