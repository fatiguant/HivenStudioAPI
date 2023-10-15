package fr.hivenapi.hivenig;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickSystem implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("[!] Cette commande peut être utilisée uniquement par les joueurs !");
            return true;
        }

        Player player = (Player) sender;

        String newUsername = generateRandomUsername(player);

        player.setDisplayName(newUsername);
        player.setPlayerListName(newUsername);

        player.sendMessage("§aNick §8» §aVotre pseudonyme a été modifié avec succès !");

        return true;
    }

    private String generateRandomUsername(Player p) {
        String originalUsername = p.getName();

        String prefix = "§7";
        String suffix = String.valueOf(System.currentTimeMillis() % 1000);

        return prefix + "_" + originalUsername + "_" + suffix;
    }
}