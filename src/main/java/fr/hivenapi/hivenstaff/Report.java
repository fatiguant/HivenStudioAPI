package fr.hivenapi.hivenstaff;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Report implements CommandExecutor {

    private final Plugin plugin;

    public Report(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peut être exécutée que par un joueur !");
            return true;
        }

        Player executor = (Player) sender;

        if (!executor.hasPermission("mod.report")) {
            executor.sendMessage("§cVous n'avez pas la permission d'utiliser cette commande !");
            return true;
        }

        if (args.length < 2) {
            executor.sendMessage("§cUtilisation : /report joueur raison");
            return true;
        }

        Player reportedPlayer = plugin.getServer().getPlayer(args[0]);

        if (reportedPlayer == null) {
            executor.sendMessage("§cLe joueur spécifié n'est pas en ligne !");
            return true;
        }

        StringBuilder reason = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            reason.append(args[i]).append(" ");
        }

        String reportMessage = String.format("§aReport §2» §fJoueur signalé : §b%s, §fRaison : §b%s, §fSignalé par : §b%s",
                reportedPlayer.getName(), reason.toString().trim(), executor.getName());


        executor.sendMessage("§aVotre signalement a été envoyé à l'équipe de modération.");

        return true;
    }
}