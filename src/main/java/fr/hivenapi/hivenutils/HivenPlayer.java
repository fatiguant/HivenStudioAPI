package fr.hivenapi.hivenutils;

import fr.hivenapi.Main;
import fr.hivenapi.PlayerStats;
import fr.hivenapi.utils.Title;
import org.bukkit.entity.Player;

import java.util.Random;

public class HivenPlayer {

    public static void coinsGiver(Player p) {
        double min = 0.5;
        double max = 2.0;

        Random random = new Random();
        double rNum = min + (max - min) * random.nextDouble();

        PlayerStats stats = new PlayerStats(p.getUniqueId());
        stats.setCoins(stats.getCoins() + rNum);
        p.sendMessage(Main.getPrefix(b) + " §aVous avez gagné §e§l" + rNum + "§ePièce(s)");
    }

    public static void expGiver(Player p) {
        int min = 1;
        int max = 2;

        Random random = new Random();
        int rNum = min + (max - min) * random.nextInt();

        PlayerStats stats = new PlayerStats(p.getUniqueId());
        stats.setEXPBar(stats.getEXPBar() + rNum);
        if(stats.getEXPBar() >= 100) {
            stats.setEXPBar(stats.getEXPBar() - 100);
            stats.setLVL(stats.getLVL() + 1);
            Title.sendCustomTitle(p, "§b§lNIVEAU SUPERIEUR !", "§7Vous passez désormais au niveau §f§l" + stats.getLVL(), 0, 30, 0);
        }
    }
}
