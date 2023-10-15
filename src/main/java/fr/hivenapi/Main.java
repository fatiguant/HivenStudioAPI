package fr.hivenapi;

import fr.hivenapi.hivenig.HivenCosmetics;
import fr.hivenapi.hivenig.NickSystem;
import fr.hivenapi.hivenutils.HivenPlayer;
import fr.hivenapi.hivenutils.HivenService;
import fr.hivenapi.utils.TeamTags;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(this, this);
        getCommand("nick").setExecutor(new NickSystem());
        //getCommand("report").setExecutor(new Report());
    }

    @EventHandler
    public void rankDonate(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        PlayerStats playerDB = new PlayerStats(p.getUniqueId());
        if(playerDB.getName() != null) {
            p.setCustomNameVisible(true);

            String rankPower = "";
            String rankPrefix = "";
            if(playerDB.getRank().equals("Admin")) {
                rankPower = "A";
                rankPrefix = "§4Admin ";
            } else if(playerDB.getRank().equals("Responsable")) {
                rankPower = "B";
                rankPrefix = "§cResponbsale ";
            } else if(playerDB.getRank().equals("Moderateur+")) {
                rankPower = "C";
                rankPrefix = "§5Modérateur+ ";
            } else if(playerDB.getRank().equals("Moderateur")) {
                rankPower = "D";
                rankPrefix = "§6Modérateur ";
            } else if(playerDB.getRank().equals("Assistant")) {
                rankPower = "E";
                rankPrefix = "§9Assistant ";
            } else if(playerDB.getRank().equals("Builder")) {
                rankPower = "F";
                rankPrefix = "§aBuilder ";
            } else if(playerDB.getRank().equals("Developpeur")) {
                rankPower = "G";
                rankPrefix = "§2Développeur ";
            } else if(playerDB.getRank().equals("Influenceur")) {
                rankPower = "H";
                rankPrefix = "§6Influenceur ";
            } else if(playerDB.getRank().equals("Ami")) {
                rankPower = "I";
                rankPrefix = "§fAmi ";
            } else if(playerDB.getRank().equals("Legende")) {
                rankPower = "J";
                rankPrefix = "§cLégende ";
            } else if(playerDB.getRank().equals("VIP++")) {
                rankPower = "K";
                rankPrefix = "§bVIP++ ";
            } else if(playerDB.getRank().equals("VIP+")) {
                rankPower = "L";
                rankPrefix = "§eVIP+ ";
            } else if(playerDB.getRank().equals("VIP")) {
                rankPower = "M";
                rankPrefix = "§9VIP ";
            } else if(playerDB.getRank().equals("Joueur")) {
                rankPower = "N";
                rankPrefix = "§7";
            }
            TeamTags.setNameTag(p, rankPower, rankPrefix);
            p.setCustomName(rankPrefix + p.getName());
            p.setPlayerListName(rankPrefix + p.getName());
        } else {
            playerDB.setName(p.getName());
            playerDB.setRank("Joueur");
            playerDB.setCoins(50);
            playerDB.setLVL(1);
            playerDB.setEXPBar(0);

            TeamTags.setNameTag(p, "O", "§7");
            p.setCustomName("§7" + p.getName());
            p.setPlayerListName("§7" + p.getName());
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        PlayerStats playerDB = new PlayerStats(p.getUniqueId());

        String rankPrefix = "";
        if(playerDB.getRank().equals("Admin")) {
            rankPrefix = "§4Admin ";
        } else if(playerDB.getRank().equals("Responsable")) {
            rankPrefix = "§cResp ";
        } else if(playerDB.getRank().equals("Moderateur+")) {
            rankPrefix = "§5Mod+ ";
        } else if(playerDB.getRank().equals("Moderateur")) {
            rankPrefix = "§dMod ";
        } else if(playerDB.getRank().equals("Assistant")) {
            rankPrefix = "§3Assistant ";
        } else if(playerDB.getRank().equals("Builder")) {
            rankPrefix = "§aBuilder ";
        } else if(playerDB.getRank().equals("Developpeur")) {
            rankPrefix = "§2Dev ";
        } else if(playerDB.getRank().equals("Youtuber")) {
            rankPrefix = "§6Youtuber ";
        } else if(playerDB.getRank().equals("Streamer")) {
            rankPrefix = "§5Streamer ";
        } else if(playerDB.getRank().equals("Ami")) {
            rankPrefix = "§fAmi ";
        } else if(playerDB.getRank().equals("Legende")) {
            rankPrefix = "§cLégende ";
        } else if(playerDB.getRank().equals("VIP++")) {
            rankPrefix = "§bVIP++ ";
        } else if(playerDB.getRank().equals("VIP+")) {
            rankPrefix = "§eVIP+ ";
        } else if(playerDB.getRank().equals("VIP")) {
            rankPrefix = "§9VIP ";
        } else if(playerDB.getRank().equals("Joueur")) {
            rankPrefix = "§7";
        }
        e.setFormat(rankPrefix + p.getName() + " §8» §f" + e.getMessage());
    }

    public static String getPrefix() {
        return "§3HivenStudio §8»";
    }
}
