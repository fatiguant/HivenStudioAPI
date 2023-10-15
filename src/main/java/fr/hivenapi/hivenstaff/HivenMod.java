package fr.hivenapi.hivenstaff;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HivenMod {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("tp")) {
            if (args.length < 1) {
                sender.sendMessage(ChatColor.RED + "Utilisation : /tp <pseudo>");
                return true;
            }

            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Cette commande ne peut être exécutée que par un joueur.");
                return true;
            }

            Player player = (Player) sender;
            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                player.sendMessage(ChatColor.RED + "Joueur introuvable.");
                return true;
            }

            player.teleport(target.getLocation());
            player.sendMessage(ChatColor.GREEN + "Vous avez été téléporté(e) vers " + target.getName() + ".");
            return true;
        }

        return false;
    }
}