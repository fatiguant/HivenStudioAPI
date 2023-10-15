 package fr.hivenapi;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerStats {
    private UUID uuid;
    public File playerFile;
    private FileConfiguration playerConfig;

    public PlayerStats(UUID uuid) {
        this.uuid = uuid;
        setupPlayerFile();
    }

    private void setupPlayerFile() {
        playerFile = new File("../../../HivenStudio/PlayerDB/" + uuid+ ".yml");

        if (!playerFile.exists()) {
            try {
                playerFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        playerConfig = YamlConfiguration.loadConfiguration(playerFile);
    }

    public void savePlayerFile() {
        try {
            playerConfig.save(playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setName(String name) { // set une variable
        playerConfig.set("Pseudo", name);
        savePlayerFile();
    }
    public void setRank(String rank) { // set une variable
        playerConfig.set("Rank", rank);
        savePlayerFile();
    }

    public void setCoins(double coins) {
        playerConfig.set("Coins", coins);
        savePlayerFile();
    }
    public void setLVL(double level) {
        playerConfig.set("Level", level);
        savePlayerFile();
    }
    public void setEXPBar(int pourcentagelvl) {
        playerConfig.set("EXP", pourcentagelvl);
        savePlayerFile();
    }

    public String getName() {
        return playerConfig.getString("Pseudo");
    }
    public String getRank() {
        return playerConfig.getString("Rank");
    }
    public int getCoins() {
        return playerConfig.getInt("Coins");
    }
    public int getLVL() {
        return playerConfig.getInt("Level");
    }
    public int getEXPBar() {
        return playerConfig.getInt("EXP");
    }


}