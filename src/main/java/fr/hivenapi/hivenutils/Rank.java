package fr.hivenapi.hivenutils;

import fr.hivenstudio.PlayerStats;
import org.bukkit.entity.Player;

public enum Rank {

    ADMIN("§4Admin ", "Admin", "A"),
    RESP("§cResponsable ", "Responsable", "B"),
    MODP("§5Modérateur+ ", "Moderateur+", "C"),
    MOD("§6Modérateur ", "Moderateur", "D"),
    ASSISTANT("§9Assistant ", "Assistant", "E"),
    BUILDER("§aBuilder ", "Builder", "F"),
    DEV("§2Développeur ", "Developpeur", "G"),
    YOUTUBER("§6Influenceur ", "Influenceur", "H"),
    AMI("§fAmi ", "Ami", "I"),
    LEGEND("§cLégende ", "Légende", "J"),
    VIPPP("§bVIP++ ", "VIP++", "K"),
    VIPP("§eVIP+ ", "VIP+", "L"),
    VIP("§9VIP ", "VIP", "M"),
    JOUEUR("§7", "Joueur", "N");

    private String dName;
    private String display;
    private String ePower;
    Rank(String Prefix, String DisplayName, String Power) {
        this.dName = Prefix;
        this.display = DisplayName;
        this.ePower = Power;

    }

    public String getRankName() {
        return dName;
    }

    public String getPrefix() {
        return display;
    }

    public String rankPlayer(Player p) {
        PlayerStats pStats = new PlayerStats(p.getUniqueId());
        return getByRankName(pStats.getRank()).getPrefix();
    }

    public String getPower() {
        return ePower;
    }

    public static Rank getByRankName(String rankName) {
        for (Rank rank : values()) {
            if (rank.getRankName().equalsIgnoreCase(rankName)) {
                return rank;
            }
        }
        return null; // or throw an exception if desired
    }
}
