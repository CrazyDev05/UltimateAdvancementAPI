package com.fren_gor.ultimateAdvancementAPI.commands.commandAPI_v6_3_1;

import com.fren_gor.ultimateAdvancementAPI.AdvancementMain;
import com.fren_gor.ultimateAdvancementAPI.AdvancementTab;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.BooleanArgument;
import dev.jorel.commandapi.arguments.EntitySelectorArgument;
import dev.jorel.commandapi.arguments.EntitySelectorArgument.EntitySelector;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

import static com.fren_gor.ultimateAdvancementAPI.commands.commandAPI_v6_3_1.AdvancementArgument_v6_3_1.getAdvancementArgument;
import static com.fren_gor.ultimateAdvancementAPI.commands.commandAPI_v6_3_1.AdvancementTabArgument_v6_3_1.getAdvancementTabArgument;

@RequiredArgsConstructor
public class UltimateAdvancementAPICommand_v6_3_1 {

    public static final String PERMISSION_MAIN = "ultimateadvancementapi.command";
    public static final String PERMISSION_CRITERIA = "ultimateadvancementapi.criteria";
    public static final String PERMISSION_CRITERIA_GET = "ultimateadvancementapi.criteria.get";
    public static final String PERMISSION_CRITERIA_SET = "ultimateadvancementapi.criteria.set";
    public static final String PERMISSION_GRANT = "ultimateadvancementapi.grant";
    public static final String PERMISSION_GRANT_ALL = "ultimateadvancementapi.grant.all";
    public static final String PERMISSION_GRANT_TAB = "ultimateadvancementapi.grant.tab";
    public static final String PERMISSION_GRANT_ONE = "ultimateadvancementapi.grant.one";
    public static final String PERMISSION_REVOKE = "ultimateadvancementapi.revoke";
    public static final String PERMISSION_REVOKE_ALL = "ultimateadvancementapi.revoke.all";
    public static final String PERMISSION_REVOKE_TAB = "ultimateadvancementapi.revoke.tab";
    public static final String PERMISSION_REVOKE_ONE = "ultimateadvancementapi.revoke.one";

    private final AdvancementMain main;

    @SuppressWarnings("unchecked")
    public void register() {
        CommandAPICommand mainCommand = new CommandAPICommand("ultimateadvancementapi").withPermission(PERMISSION_MAIN).withAliases("uladvapi", "uladv", "uaapi").executes((sender, args) -> {
            sender.sendMessage(ChatColor.RED + "Usage: /ultimateadvancementapi <criteria|grant|revoke> ...");
        });

        CommandAPICommand grant = new CommandAPICommand("grant").withPermission(PERMISSION_GRANT).executes((sender, args) -> {
            sender.sendMessage(ChatColor.RED + "Usage: /ultimateadvancementapi grant <all|tab|one> ...");
        });

        grant.withSubcommand(new CommandAPICommand("all").withPermission(PERMISSION_GRANT_ALL).executesPlayer((Player player, Object[] args) -> grantAll(player, player, true)).executes((sender, args) -> {
            sender.sendMessage(ChatColor.RED + "Usage: /ultimateadvancementapi grant all <player> [giveRewards]");
        }));
        grant.withSubcommand(new CommandAPICommand("all").withPermission(PERMISSION_GRANT_ALL).withArguments(new EntitySelectorArgument("player", EntitySelector.MANY_PLAYERS)).executes((CommandSender sender, Object[] args) -> grantAll(sender, (Collection<Player>) args[0], true)));
        grant.withSubcommand(new CommandAPICommand("all").withPermission(PERMISSION_GRANT_ALL).withArguments(new EntitySelectorArgument("player", EntitySelector.MANY_PLAYERS), new BooleanArgument("giveRewards")).executes((CommandSender sender, Object[] args) -> grantAll(sender, (Collection<Player>) args[0], (boolean) args[1])));

        grant.withSubcommand(new CommandAPICommand("tab").withPermission(PERMISSION_GRANT_TAB).executesPlayer((player, args) -> {
            player.sendMessage(ChatColor.RED + "Usage: /ultimateadvancementapi grant tab <advancementTab> [player] [giveRewards]");
        }).executes((sender, args) -> {
            sender.sendMessage(ChatColor.RED + "Usage: /ultimateadvancementapi grant tab <advancementTab> <player> [giveRewards]");
        }));
        grant.withSubcommand(new CommandAPICommand("tab").withPermission(PERMISSION_GRANT_TAB).withArguments(getAdvancementTabArgument(main, "advancementTab")).executesPlayer((Player player, Object[] args) -> grantTab(player, (AdvancementTab) args[0], player, true)));
        grant.withSubcommand(new CommandAPICommand("tab").withPermission(PERMISSION_GRANT_TAB).withArguments(getAdvancementTabArgument(main, "advancementTab"), new EntitySelectorArgument("player", EntitySelector.MANY_PLAYERS)).executes((CommandSender sender, Object[] args) -> grantTab(sender, (AdvancementTab) args[0], (Collection<Player>) args[1], true)));
        grant.withSubcommand(new CommandAPICommand("tab").withPermission(PERMISSION_GRANT_TAB).withArguments(getAdvancementTabArgument(main, "advancementTab"), new EntitySelectorArgument("player", EntitySelector.MANY_PLAYERS), new BooleanArgument("giveRewards")).executes((CommandSender sender, Object[] args) -> grantTab(sender, (AdvancementTab) args[0], (Collection<Player>) args[1], (boolean) args[2])));

        grant.withSubcommand(new CommandAPICommand("one").withPermission(PERMISSION_GRANT_ONE).executesPlayer((player, args) -> {
            player.sendMessage(ChatColor.RED + "Usage: /ultimateadvancementapi grant one <advancement> [player] [giveRewards]");
        }).executes((sender, args) -> {
            sender.sendMessage(ChatColor.RED + "Usage: /ultimateadvancementapi grant one <advancement> <player> [giveRewards]");
        }));
        grant.withSubcommand(new CommandAPICommand("one").withPermission(PERMISSION_GRANT_ONE).withArguments(getAdvancementArgument(main, "advancement")).executesPlayer((Player player, Object[] args) -> grantOne(player, (Advancement) args[0], player, true)));
        grant.withSubcommand(new CommandAPICommand("one").withPermission(PERMISSION_GRANT_ONE).withArguments(getAdvancementArgument(main, "advancement"), new EntitySelectorArgument("player", EntitySelector.MANY_PLAYERS)).executes((CommandSender sender, Object[] args) -> grantOne(sender, (Advancement) args[0], (Collection<Player>) args[1], true)));
        grant.withSubcommand(new CommandAPICommand("one").withPermission(PERMISSION_GRANT_ONE).withArguments(getAdvancementArgument(main, "advancement"), new EntitySelectorArgument("player", EntitySelector.MANY_PLAYERS), new BooleanArgument("giveRewards")).executes((CommandSender sender, Object[] args) -> grantOne(sender, (Advancement) args[0], (Collection<Player>) args[1], (boolean) args[2])));

        CommandAPICommand revoke = new CommandAPICommand("revoke").withPermission(PERMISSION_REVOKE).executes((sender, args) -> {
            sender.sendMessage(ChatColor.RED + "Usage: /ultimateadvancementapi revoke <all|tab|one> ...");
        });

        revoke.withSubcommand(new CommandAPICommand("all").withPermission(PERMISSION_REVOKE_ALL).executesPlayer((Player player, Object[] args) -> revokeAll(player, player, false)).executes((sender, args) -> {
            sender.sendMessage(ChatColor.RED + "Usage: /ultimateadvancementapi revoke all <player> [hideTab]");
        }));
        revoke.withSubcommand(new CommandAPICommand("all").withPermission(PERMISSION_REVOKE_ALL).withArguments(new EntitySelectorArgument("player", EntitySelector.MANY_PLAYERS)).executes((CommandSender sender, Object[] args) -> revokeAll(sender, (Collection<Player>) args[0], false)));
        revoke.withSubcommand(new CommandAPICommand("all").withPermission(PERMISSION_REVOKE_ALL).withArguments(new EntitySelectorArgument("player", EntitySelector.MANY_PLAYERS), new BooleanArgument("hideTabs")).executes((CommandSender sender, Object[] args) -> revokeAll(sender, (Collection<Player>) args[0], (boolean) args[1])));

        revoke.withSubcommand(new CommandAPICommand("tab").withPermission(PERMISSION_REVOKE_TAB).executesPlayer((player, args) -> {
            player.sendMessage(ChatColor.RED + "Usage: /ultimateadvancementapi revoke tab <advancementTab> [player] [hideTab]");
        }).executes((sender, args) -> {
            sender.sendMessage(ChatColor.RED + "Usage: /ultimateadvancementapi revoke tab <advancementTab> <player> [hideTab]");
        }));
        revoke.withSubcommand(new CommandAPICommand("tab").withPermission(PERMISSION_REVOKE_TAB).withArguments(getAdvancementTabArgument(main, "advancementTab")).executesPlayer((Player player, Object[] args) -> revokeTab(player, (AdvancementTab) args[0], player, false)));
        revoke.withSubcommand(new CommandAPICommand("tab").withPermission(PERMISSION_REVOKE_TAB).withArguments(getAdvancementTabArgument(main, "advancementTab"), new EntitySelectorArgument("player", EntitySelector.MANY_PLAYERS)).executes((CommandSender sender, Object[] args) -> revokeTab(sender, (AdvancementTab) args[0], (Collection<Player>) args[1], false)));
        revoke.withSubcommand(new CommandAPICommand("tab").withPermission(PERMISSION_REVOKE_TAB).withArguments(getAdvancementTabArgument(main, "advancementTab"), new EntitySelectorArgument("player", EntitySelector.MANY_PLAYERS), new BooleanArgument("hideTab")).executes((CommandSender sender, Object[] args) -> revokeTab(sender, (AdvancementTab) args[0], (Collection<Player>) args[1], (boolean) args[2])));

        revoke.withSubcommand(new CommandAPICommand("one").withPermission(PERMISSION_REVOKE_ONE).executesPlayer((player, args) -> {
            player.sendMessage(ChatColor.RED + "Usage: /ultimateadvancementapi revoke one <advancement> [player]");
        }).executes((sender, args) -> {
            sender.sendMessage(ChatColor.RED + "Usage: /ultimateadvancementapi revoke one <advancement> <player>");
        }));
        revoke.withSubcommand(new CommandAPICommand("one").withPermission(PERMISSION_REVOKE_ONE).withArguments(getAdvancementArgument(main, "advancement")).executesPlayer((Player player, Object[] args) -> revokeOne(player, (Advancement) args[0], player)));
        revoke.withSubcommand(new CommandAPICommand("one").withPermission(PERMISSION_REVOKE_ONE).withArguments(getAdvancementArgument(main, "advancement"), new EntitySelectorArgument("player", EntitySelector.MANY_PLAYERS)).executes((CommandSender sender, Object[] args) -> revokeOne(sender, (Advancement) args[0], (Collection<Player>) args[1])));

        CommandAPICommand criteria = new CommandAPICommand("criteria").withPermission(PERMISSION_CRITERIA).executes((sender, args) -> {
            sender.sendMessage(ChatColor.RED + "Usage: /ultimateadvancementapi criteria <get|set> ...");
        });

        criteria.withSubcommand(new CommandAPICommand("get").withPermission(PERMISSION_CRITERIA_GET).executesPlayer((player, args) -> {
            player.sendMessage(ChatColor.RED + "Usage: /ultimateadvancementapi criteria get <advancement> [player]");
        }).executes((sender, args) -> {
            sender.sendMessage(ChatColor.RED + "Usage: /ultimateadvancementapi criteria get <advancement> <player>");
        }));
        criteria.withSubcommand(new CommandAPICommand("get").withPermission(PERMISSION_CRITERIA_GET).withArguments(getAdvancementArgument(main, "advancement")).executesPlayer((Player player, Object[] args) -> getCriteria(player, (Advancement) args[0], player)));
        criteria.withSubcommand(new CommandAPICommand("get").withPermission(PERMISSION_CRITERIA_GET).withArguments(getAdvancementArgument(main, "advancement"), new EntitySelectorArgument("player", EntitySelector.MANY_PLAYERS)).executes((CommandSender sender, Object[] args) -> getCriteria(sender, (Advancement) args[0], (Collection<Player>) args[1])));

        criteria.withSubcommand(new CommandAPICommand("set").withPermission(PERMISSION_CRITERIA_SET).executesPlayer((player, args) -> {
            player.sendMessage(ChatColor.RED + "Usage: /ultimateadvancementapi criteria set <advancement> <criteria> [player] [giveRewards]");
        }).executes((sender, args) -> {
            sender.sendMessage(ChatColor.RED + "Usage: /ultimateadvancementapi criteria set <advancement> <criteria> <player> [giveRewards]");
        }));
        criteria.withSubcommand(new CommandAPICommand("set").withPermission(PERMISSION_CRITERIA_SET).withArguments(getAdvancementArgument(main, "advancement"), new IntegerArgument("criteria", 0)).executesPlayer((Player player, Object[] args) -> setCriteria(player, (Advancement) args[0], (int) args[1], player, true)));
        criteria.withSubcommand(new CommandAPICommand("set").withPermission(PERMISSION_CRITERIA_SET).withArguments(getAdvancementArgument(main, "advancement"), new IntegerArgument("criteria", 0), new EntitySelectorArgument("player", EntitySelector.MANY_PLAYERS)).executes((CommandSender sender, Object[] args) -> setCriteria(sender, (Advancement) args[0], (int) args[1], (Collection<Player>) args[2], true)));
        criteria.withSubcommand(new CommandAPICommand("set").withPermission(PERMISSION_CRITERIA_SET).withArguments(getAdvancementArgument(main, "advancement"), new IntegerArgument("criteria", 0), new EntitySelectorArgument("player", EntitySelector.MANY_PLAYERS), new BooleanArgument("giveRewards")).executes((CommandSender sender, Object[] args) -> setCriteria(sender, (Advancement) args[0], (int) args[1], (Collection<Player>) args[2], (boolean) args[3])));

        mainCommand.withSubcommand(criteria);
        mainCommand.withSubcommand(grant);
        mainCommand.withSubcommand(revoke);
        mainCommand.register();
    }

    private void grantAll(CommandSender sender, Player player, boolean giveRewards) throws WrapperCommandSyntaxException {
        grantAll(sender, List.of(player), giveRewards);
    }

    private void grantAll(CommandSender sender, Collection<Player> players, boolean giveRewards) throws WrapperCommandSyntaxException {
        validatePlayerArgument(players);
        for (AdvancementTab m : main.getTabs()) {
            if (m.isActive()) {
                for (Advancement a : m.getAdvancements()) {
                    for (Player p : players)
                        a.grant(p, giveRewards);
                }
            }
        }
        for (Player p : players)
            sender.sendMessage(ChatColor.GREEN + "All advancement has been unlocked to " + ChatColor.YELLOW + p.getName());
    }

    private void grantTab(CommandSender sender, AdvancementTab tab, Player player, boolean giveRewards) throws WrapperCommandSyntaxException {
        grantTab(sender, tab, List.of(player), giveRewards);
    }

    private void grantTab(CommandSender sender, AdvancementTab tab, Collection<Player> players, boolean giveRewards) throws WrapperCommandSyntaxException {
        validatePlayerArgument(players);
        if (!tab.isActive()) {
            CommandAPI.fail("Advancement tab is not active.");
        }
        for (Advancement a : tab.getAdvancements()) {
            for (Player p : players)
                a.grant(p, giveRewards);
        }
        for (Player p : players)
            sender.sendMessage(ChatColor.GREEN + "All advancement of tab " + ChatColor.YELLOW + tab + ChatColor.GREEN + " has been unlocked to " + ChatColor.YELLOW + p.getName());
    }

    private void grantOne(CommandSender sender, Advancement advancement, Player player, boolean giveRewards) throws WrapperCommandSyntaxException {
        grantOne(sender, advancement, List.of(player), giveRewards);
    }

    private void grantOne(CommandSender sender, Advancement advancement, Collection<Player> players, boolean giveRewards) throws WrapperCommandSyntaxException {
        validatePlayerArgument(players);
        for (Player p : players) {
            advancement.getAdvancementTab().showTab(p);
            advancement.grant(p, giveRewards);
            sender.sendMessage(ChatColor.GREEN + "Advancement " + ChatColor.YELLOW + advancement.getKey() + ChatColor.GREEN + " has been unlocked to " + ChatColor.YELLOW + p.getName());
        }
    }

    private void revokeAll(CommandSender sender, Player player, boolean hideTabs) throws WrapperCommandSyntaxException {
        revokeAll(sender, List.of(player), hideTabs);
    }

    private void revokeAll(CommandSender sender, Collection<Player> players, boolean hideTabs) throws WrapperCommandSyntaxException {
        validatePlayerArgument(players);
        for (AdvancementTab m : main.getTabs()) {
            var advancements = m.getAdvancements();
            for (Player p : players) {
                for (Advancement a : advancements) {
                    a.revoke(p);
                }
                if (hideTabs)
                    m.hideTab(p);
            }
        }
        for (Player p : players)
            sender.sendMessage(ChatColor.GREEN + "All advancement has been revoked to " + ChatColor.YELLOW + p.getName());
    }

    private void revokeTab(CommandSender sender, AdvancementTab tab, Player player, boolean hideTab) throws WrapperCommandSyntaxException {
        revokeTab(sender, tab, List.of(player), hideTab);
    }

    private void revokeTab(CommandSender sender, AdvancementTab tab, Collection<Player> players, boolean hideTab) throws WrapperCommandSyntaxException {
        validatePlayerArgument(players);
        if (!tab.isActive()) {
            CommandAPI.fail("Advancement tab is not active.");
        }
        var advancements = tab.getAdvancements();
        for (Player p : players) {
            for (Advancement a : tab.getAdvancements()) {
                a.revoke(p);
            }
            if (hideTab)
                tab.hideTab(p);
            sender.sendMessage(ChatColor.GREEN + "All advancement of tab " + ChatColor.YELLOW + tab + ChatColor.GREEN + " has been revoked to " + ChatColor.YELLOW + p.getName());
        }
    }

    private void revokeOne(CommandSender sender, Advancement advancement, Player player) throws WrapperCommandSyntaxException {
        revokeOne(sender, advancement, List.of(player));
    }

    private void revokeOne(CommandSender sender, Advancement advancement, Collection<Player> players) throws WrapperCommandSyntaxException {
        validatePlayerArgument(players);
        for (Player p : players) {
            advancement.revoke(p);
            sender.sendMessage(ChatColor.GREEN + "Advancement " + ChatColor.YELLOW + advancement + ChatColor.GREEN + " has been revoked to " + ChatColor.YELLOW + p.getName());
        }
    }

    private int getCriteria(CommandSender sender, Advancement advancement, Player player) throws WrapperCommandSyntaxException {
        return getCriteria(sender, advancement, List.of(player));
    }

    private int getCriteria(CommandSender sender, Advancement advancement, Collection<Player> players) throws WrapperCommandSyntaxException {
        validatePlayerArgument(players);
        int criteria = 0;
        for (Player p : players) {
            criteria = advancement.getTeamCriteria(p);
            sender.sendMessage(ChatColor.YELLOW + p.getName() + ChatColor.GREEN + " criteria is " + ChatColor.YELLOW + criteria + '/' + advancement.getMaxCriteria());
        }
        return criteria;
    }

    private void setCriteria(CommandSender sender, Advancement advancement, int criteria, Player player, boolean giveRewards) throws WrapperCommandSyntaxException {
        setCriteria(sender, advancement, criteria, List.of(player), giveRewards);
    }

    private void setCriteria(CommandSender sender, Advancement advancement, int criteria, Collection<Player> players, boolean giveRewards) throws WrapperCommandSyntaxException {
        validatePlayerArgument(players);
        for (Player p : players) {
            criteria = Math.min(advancement.getMaxCriteria(), criteria);
            advancement.setCriteriaTeamProgression(p, criteria, giveRewards);
            sender.sendMessage(ChatColor.GREEN + "Set criteria " + ChatColor.YELLOW + criteria + '/' + advancement.getMaxCriteria() + ChatColor.GREEN + " for " + ChatColor.YELLOW + p.getName());
        }
    }

    private static void validatePlayerArgument(@NotNull Collection<Player> players) throws WrapperCommandSyntaxException {
        if (players.size() == 0) {
            CommandAPI.fail("No player has been provided.");
        }
    }
}
