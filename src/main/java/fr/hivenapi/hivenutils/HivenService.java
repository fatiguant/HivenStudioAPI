package fr.hivenapi.hivenutils;

import be.alexandre01.dnplugin.plugins.spigot.api.DNSpigotAPI;
import org.bukkit.entity.Player;

public class HivenService {

    public static void endService(String service, Integer sId) {
        DNSpigotAPI.getInstance().getByName(service).getServers().get(sId).stop();
    }
    public static void newService(String service) {
        DNSpigotAPI.getInstance().getByName(service).start();
    }
    public static void serviceConnect(Player p, String service, Integer sId) {
        DNSpigotAPI.getInstance().sendPlayerTo(p, service + "-" + sId);
    }
    public static String serviceName() {
        return DNSpigotAPI.getInstance().getServerName() + "#" + DNSpigotAPI.getInstance().getID();
    }
    public static int networkPlayers() {
        return DNSpigotAPI.getInstance().getDnPlayerManager().getDnPlayers().size();
    }
    public static int servicePlayers(String service, Integer sId) {
        return DNSpigotAPI.getInstance().getByName(service).getServers().get(sId).getPlayers().size();
    }
}
