package fr.hivenapi.hivenig;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MsgManager extends Plugin {

    private Map<UUID, UUID> lastPrivateMessageSender;

    @Override
    public void onEnable() {
        lastPrivateMessageSender = new HashMap<>();

        registerCommand("msg", new PrivateMessageCommand());
        registerCommand("m", new PrivateMessageCommand());
        registerCommand("tell", new PrivateMessageCommand());
        registerCommand("r", new ReplyCommand());
        registerCommand("reply", new ReplyCommand());
    }

    private void registerCommand(String label, Command command) {
        PluginManager pluginManager = getProxy().getPluginManager();
        pluginManager.registerCommand(this, command);
        pluginManager.registerCommand(this, command);
    }

    private class PrivateMessageCommand extends Command {

        public PrivateMessageCommand() {
            super("msg", null, "m", "tell");
        }

        @Override
        public void execute(CommandSender sender, String[] args) {
            if (!(sender instanceof ProxiedPlayer)) {
                sender.sendMessage(ChatColor.RED + "Cette commande doit être exécutée par un joueur !");
                return;
            }

            ProxiedPlayer senderPlayer = (ProxiedPlayer) sender;

            if (args.length < 2) {
                senderPlayer.sendMessage(ChatColor.RED + "Utilisation : /msg <joueur> <message>");
                return;
            }

            ProxiedPlayer targetPlayer = getProxy().getPlayer(args[0]);
            if (targetPlayer == null) {
                senderPlayer.sendMessage(ChatColor.RED + "Joueur introuvable.");
                return;
            }

            if (senderPlayer.equals(targetPlayer)) {
                senderPlayer.sendMessage(ChatColor.RED + "Vous ne pouvez pas vous envoyer des messages privés à vous-même.");
                return;
            }

            String message = String.join(" ", args[0]);
            sendMessage(senderPlayer, targetPlayer, message);
            lastPrivateMessageSender.put(targetPlayer.getUniqueId(), senderPlayer.getUniqueId());
        }
    }

    private class ReplyCommand extends Command {

        public ReplyCommand() {
            super("r", null, "reply");
        }

        @Override
        public void execute(CommandSender sender, String[] args) {
            if (!(sender instanceof ProxiedPlayer)) {
                sender.sendMessage(ChatColor.RED + "Cette commande doit être exécutée par un joueur !");
                return;
            }

            ProxiedPlayer senderPlayer = (ProxiedPlayer) sender;
            UUID lastSenderUUID = lastPrivateMessageSender.get(senderPlayer.getUniqueId());

            if (lastSenderUUID == null) {
                senderPlayer.sendMessage(ChatColor.RED + "Aucun message privé précédent à qui répondre.");
                return;
            }

            ProxiedPlayer targetPlayer = getProxy().getPlayer(lastSenderUUID);
            if (targetPlayer == null) {
                senderPlayer.sendMessage(ChatColor.RED + "Le joueur précédent n'est plus en ligne.");
                return;
            }

            if (args.length < 1) {
                senderPlayer.sendMessage(ChatColor.RED + "Utilisation : /r <message>");
                return;
            }

            String message = String.join(" ", args);
            sendMessage(senderPlayer, targetPlayer, message);
            lastPrivateMessageSender.put(targetPlayer.getUniqueId(), senderPlayer.getUniqueId());
        }
    }

    private void sendMessage(ProxiedPlayer sender, ProxiedPlayer target, String message) {
        sender.sendMessage(ChatColor.YELLOW + "[Moi -> " + target.getName() + "] " + message);
        target.sendMessage(ChatColor.YELLOW + "[" + sender.getName() + " -> Moi] " + message);
    }
}