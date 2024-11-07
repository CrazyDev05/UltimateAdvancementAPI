package com.fren_gor.ultimateAdvancementAPI.advancement.display;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.RootAdvancement;
import com.fren_gor.ultimateAdvancementAPI.nms.wrappers.advancement.AdvancementDisplayWrapper;
import com.google.common.base.Preconditions;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.ComponentBuilder.FormatRetention;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * The {@code FancyAdvancementDisplay} class provides a more flexible api to advancements than {@link AdvancementDisplay}.
 * through the use of {@link BaseComponent}s for the title and description.
 */
public class ComponentAdvancementDisplay extends AdvancementDisplay {
    protected final BaseComponent componentTitle;
    protected final List<BaseComponent> componentDescription;
    protected final ChatColor defaultColor;

    /**
     * Creates a new {@code ComponentAdvancementDisplay}.
     * <p>The default color of the title and description is {@code frame.getColor()}.
     * <p>The advancement is positioned by the x and y coordinates in the advancement GUI. The origin is placed in the
     * upper-left corner of the advancement GUI. The x-axis points to the right (as usual), whereas the y-axis points downward.
     * Thus, the x and y coordinates must be positive.
     *
     * @param icon The material of the advancement's icon in the advancement GUI.
     * @param title The title of the advancement.
     * @param frame The shape of the advancement frame in the advancement GUI.
     * @param showToast Whether the toast notification should be sent on advancement grant.
     * @param announceChat Whether the advancement completion message should be sent on advancement grant.
     * @param x The advancement x coordinate. Must be not negative.
     * @param y The advancement y coordinate. Must be not negative.
     * @param description The description of the advancement.
     */
    public ComponentAdvancementDisplay(@NotNull Material icon, @NotNull BaseComponent title, @NotNull AdvancementFrameType frame, boolean showToast, boolean announceChat, float x, float y, @NotNull BaseComponent... description) {
        this(icon, title, frame, showToast, announceChat, x, y, Arrays.asList(description));
    }

    /**
     * Creates a new {@code ComponentAdvancementDisplay}.
     * <p>The default color of the title and description is {@code frame.getColor()}.
     * <p>The advancement is positioned by the x and y coordinates in the advancement GUI. The origin is placed in the
     * upper-left corner of the advancement GUI. The x-axis points to the right (as usual), whereas the y-axis points downward.
     * Thus, the x and y coordinates must be positive.
     *
     * @param icon The material of the advancement's icon in the advancement GUI.
     * @param title The title of the advancement.
     * @param frame The shape of the advancement frame in the advancement GUI.
     * @param showToast Whether the toast notification should be sent on advancement grant.
     * @param announceChat Whether the advancement completion message should be sent on advancement grant.
     * @param x The advancement x coordinate. Must be not negative.
     * @param y The advancement y coordinate. Must be not negative.
     * @param description The description of the advancement.
     */
    public ComponentAdvancementDisplay(@NotNull Material icon, @NotNull BaseComponent title, @NotNull AdvancementFrameType frame, boolean showToast, boolean announceChat, float x, float y, @NotNull List<BaseComponent> description) {
        this(new ItemStack(Objects.requireNonNull(icon, "Icon is null.")), title, frame, showToast, announceChat, x, y, description);
    }

    /**
     * Creates a new {@code ComponentAdvancementDisplay}.
     * <p>The default color of the title and description is {@code frame.getColor()}.
     * <p>The advancement is positioned by the x and y coordinates in the advancement GUI. The origin is placed in the
     * upper-left corner of the advancement GUI. The x-axis points to the right (as usual), whereas the y-axis points downward.
     * Thus, the x and y coordinates must be positive.
     *
     * @param icon The advancement's icon in the advancement GUI.
     * @param title The title of the advancement.
     * @param frame The shape of the advancement frame in the advancement GUI.
     * @param showToast Whether the toast notification should be sent on advancement grant.
     * @param announceChat Whether the advancement completion message should be sent on advancement grant.
     * @param x The advancement x coordinate. Must be not negative.
     * @param y The advancement y coordinate. Must be not negative.
     * @param description The description of the advancement.
     */
    public ComponentAdvancementDisplay(@NotNull ItemStack icon, @NotNull BaseComponent title, @NotNull AdvancementFrameType frame, boolean showToast, boolean announceChat, float x, float y, @NotNull BaseComponent... description) {
        this(icon, title, frame, showToast, announceChat, x, y, Arrays.asList(description));
    }

    /**
     * Creates a new {@code ComponentAdvancementDisplay}.
     * <p>The default color of the title and description is {@code frame.getColor()}.
     * <p>The advancement is positioned by the x and y coordinates in the advancement GUI. The origin is placed in the
     * upper-left corner of the advancement GUI. The x-axis points to the right (as usual), whereas the y-axis points downward.
     * Thus, the x and y coordinates must be positive.
     *
     * @param icon The advancement's icon in the advancement GUI.
     * @param title The title of the advancement.
     * @param frame The shape of the advancement frame in the advancement GUI.
     * @param showToast Whether the toast notification should be sent on advancement grant.
     * @param announceChat Whether the advancement completion message should be sent on advancement grant.
     * @param x The advancement x coordinate. Must be not negative.
     * @param y The advancement y coordinate. Must be not negative.
     * @param description The description of the advancement.
     */
    public ComponentAdvancementDisplay(@NotNull ItemStack icon, @NotNull BaseComponent title, @NotNull AdvancementFrameType frame, boolean showToast, boolean announceChat, float x, float y, @NotNull List<BaseComponent> description) {
        this(icon, title, frame, showToast, announceChat, x, y, Objects.requireNonNull(frame, "AdvancementFrameType is null.").getColor(), description);
    }

    /**
     * Creates a new {@code ComponentAdvancementDisplay}.
     * <p>The advancement is positioned by the x and y coordinates in the advancement GUI. The origin is placed in the
     * upper-left corner of the advancement GUI. The x-axis points to the right (as usual), whereas the y-axis points downward.
     * Thus, the x and y coordinates must be positive.
     *
     * @param icon The advancement's icon in the advancement GUI.
     * @param title The title of the advancement.
     * @param frame The shape of the advancement frame in the advancement GUI.
     * @param showToast Whether the toast notification should be sent on advancement grant.
     * @param announceChat Whether the advancement completion message should be sent on advancement grant.
     * @param x The advancement x coordinate. Must be not negative.
     * @param y The advancement y coordinate. Must be not negative.
     * @param defaultColor The default color of the title and description.
     * @param description The description of the advancement.
     */
    public ComponentAdvancementDisplay(@NotNull ItemStack icon, @NotNull BaseComponent title, @NotNull AdvancementFrameType frame, boolean showToast, boolean announceChat, float x, float y, @NotNull ChatColor defaultColor, @NotNull BaseComponent... description) {
        this(icon, title, frame, showToast, announceChat, x, y, defaultColor, Arrays.asList(description));
    }

    public ComponentAdvancementDisplay(@NotNull ItemStack icon, @NotNull BaseComponent title, @NotNull AdvancementFrameType frame, boolean showToast, boolean announceChat, float x, float y, @NotNull ChatColor defaultColor, @NotNull List<BaseComponent> description) {
        super(icon, "", frame, showToast, announceChat, x, y, defaultColor, List.of());
        Preconditions.checkNotNull(title, "Title is null.");
        Preconditions.checkNotNull(description, "Description is null.");

        this.defaultColor = defaultColor;
        componentTitle = title;
        componentDescription = Collections.unmodifiableList(description);
        chatTitle[0] = title;
        chatDescription[0] = merge(title, description, defaultColor);
    }

    @Override
    @NotNull
    public String getTitle() {
        return componentTitle.toLegacyText();
    }

    @Override
    @Unmodifiable
    public List<String> getDescription() {
        return componentDescription.stream()
                .map(line -> line.toLegacyText())
                .toList();
    }

    @Override
    @NotNull
    public AdvancementDisplayWrapper getNMSWrapper(@NotNull Advancement advancement) {
        Preconditions.checkNotNull(advancement, "Advancement is null.");
        try {
            String title = toString(componentTitle, defaultColor);
            String description = toString(componentDescription, defaultColor);
            if (advancement instanceof RootAdvancement root) {
                return AdvancementDisplayWrapper.craft(icon, title, description, frame.getNMSWrapper(), x, y, root.getBackgroundTexture());
            } else {
                return AdvancementDisplayWrapper.craft(icon, title, description, frame.getNMSWrapper(), x, y);
            }
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @NotNull
    public String getCompactDescription() {
        return String.join("\n", getDescription());
    }

    public static class Builder extends AdvancementDisplayBuilder<ComponentAdvancementDisplay.Builder, ComponentAdvancementDisplay> {
        /**
         * The component title of the advancement.
         */
        protected final BaseComponent componentTitle;

        /**
         * The default color of the title and description.
         */
        protected ChatColor defaultColor = frame.getColor();

        /**
         * The component description of the advancement.
         */
        protected List<BaseComponent> componentDescription = List.of();
        private boolean manuallySetDefaultColor = false;

        /**
         * Creates a new {@code AdvancementDisplayBuilder}.
         * <p>By default, the advancement display returned by {@link #build()} won't show both the toast message and
         * the announcement message in the chat upon advancement completion.
         * <p>The default {@code frame} is {@link AdvancementFrameType#TASK}.
         *
         * @param icon The material of the advancement's icon in the advancement GUI.
         * @param title The title of the advancement.
         */
        public Builder(@NotNull Material icon, @NotNull BaseComponent title) {
            this(new ItemStack(icon), title);
        }

        /**
         * Creates a new {@code AdvancementDisplayBuilder}.
         * <p>By default, the advancement display returned by {@link #build()} won't show both the toast message and
         * the announcement message in the chat upon advancement completion.
         * <p>The default {@code frame} is {@link AdvancementFrameType#TASK}.
         *
         * @param icon The advancement's icon in the advancement GUI.
         * @param title The title of the advancement.
         */
        public Builder(@NotNull ItemStack icon, @NotNull BaseComponent title) {
            super(icon, title.toLegacyText());
            componentTitle = title;
        }

        /**
         * {@inheritDoc}
         * <p>If {@link #defaultColor(ChatColor) builder.defaultColor(...)} hasn't been called yet (or if it will never be called),
         * also sets the {@link #defaultColor default color} to {@link AdvancementFrameType#getColor() frame.getColor()}.
         */
        @Override
        @NotNull
        public Builder frame(@NotNull AdvancementFrameType frame) {
            super.frame(frame); // Also checks frame is not null
            if (!manuallySetDefaultColor) {
                this.defaultColor = frame.getColor();
            }
            return this;
        }

        /**
         * Sets the default color of the title and description.
         *
         * @param defaultColor The default color of the title and description.
         * @return This builder.
         */
        @NotNull
        public Builder defaultColor(@NotNull ChatColor defaultColor) {
            this.defaultColor = Objects.requireNonNull(defaultColor, "Default color is null.");
            manuallySetDefaultColor = true;
            return this;
        }

        /**
         * Sets the description of the advancement.
         *
         * @param description The description of the advancement.
         * @return This builder.
         */
        @NotNull
        public Builder description(@NotNull String... description) {
            this.componentDescription = Arrays.stream(description)
                    .map(ComponentAdvancementDisplay::parseOrString)
                    .toList();
            return this;
        }

        /**
         * Sets the description of the advancement.
         *
         * @param description The description of the advancement.
         * @return This builder.
         */
        @NotNull
        public Builder description(@NotNull List<String> description) {
            this.componentDescription = description.stream()
                    .map(ComponentAdvancementDisplay::parseOrString)
                    .toList();
            return this;
        }

        /**
         * Sets the description of the advancement.
         *
         * @param description The description of the advancement.
         * @return This builder.
         */
        @NotNull
        public Builder componentDescription(@NotNull BaseComponent... description) {
            this.componentDescription = List.of(description);
            return this;
        }

        /**
         * Sets the description of the advancement.
         *
         * @param description The description of the advancement.
         * @return This builder.
         */
        @NotNull
        public Builder componentDescription(@NotNull List<BaseComponent> description) {
            this.componentDescription = List.copyOf(description);
            return this;
        }

        /**
         * Gets the description of the advancement.
         *
         * @return The description of the advancement.
         */
        @Override
        @NotNull
        @Unmodifiable
        public List<String> getDescription() {
            return getComponentDescription().stream()
                    .map(line -> line.toLegacyText())
                    .toList();
        }

        /**
         * Gets the component title of the advancement.
         *
         * @return The component title of the advancement.
         */
        @NotNull
        public BaseComponent getComponentTitle() {
            return componentTitle;
        }

        /**
         * Gets the component description of the advancement.
         *
         * @return The component description of the advancement.
         */
        @NotNull
        public List<BaseComponent> getComponentDescription() {
            return componentDescription;
        }

        @Override
        @NotNull
        public ComponentAdvancementDisplay build() {
            return new ComponentAdvancementDisplay(icon, componentTitle, frame, showToast, announceChat, x, y, defaultColor, componentDescription);
        }
    }

    @NotNull
    private static BaseComponent parseOrString(@NotNull String string) {
        BaseComponent component = null;
        try {
            component = ComponentSerializer.deserialize(string);
        } catch (Exception ignored) {}
        return component != null ? component : new TextComponent(string);
    }

    @NotNull
    private static BaseComponent merge(@Nullable BaseComponent title, @NotNull List<BaseComponent> components, @NotNull ChatColor defaultColor) {
        ComponentBuilder builder = new ComponentBuilder();
        if (title != null) {
            builder.append(color(title, defaultColor), FormatRetention.NONE);
            if (!components.isEmpty()) {
                builder.append("\n", FormatRetention.NONE);
            }
        }

        for (int i = 0; i < components.size(); i++) {
            builder.append(color(components.get(i), defaultColor), FormatRetention.NONE);
            if (i < components.size() - 1) {
                builder.append("\n", FormatRetention.NONE);
            }
        }
        return builder.build();
    }

    @NotNull
    private static String toString(@NotNull List<BaseComponent> components, @NotNull ChatColor defaultColor) {
        return toString(merge(null, components, defaultColor), defaultColor);
    }

    @NotNull
    private static String toString(@NotNull BaseComponent component, @NotNull ChatColor defaultColor) {
        return ComponentSerializer.toString(color(component, defaultColor));
    }

    @NotNull
    private static BaseComponent color(@NotNull BaseComponent component, @NotNull ChatColor color) {
        if (!component.hasStyle() || !component.getStyle().hasColor())
            component.setColor(color);
        return component;
    }
}
