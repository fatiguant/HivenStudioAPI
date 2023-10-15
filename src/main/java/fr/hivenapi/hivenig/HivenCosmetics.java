package fr.hivenapi.hivenig;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class HivenCosmetics extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        getCommand("c").setExecutor(this);
        getCommand("cosmetics").setExecutor(this);
        getCommand("admin").setExecutor(this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("c") || command.getName().equalsIgnoreCase("cosmetics")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                openCosmeticsMenu(player);
            } else {
                sender.sendMessage("Cette commande doit être exécutée par un joueur !");
            }
            return true;
        } else if (command.getName().equalsIgnoreCase("admin") && args.length > 1 && args[0].equalsIgnoreCase("cosmetics")
                && args[1].equalsIgnoreCase("give") && args.length == 4) {
            Player targetPlayer = Bukkit.getPlayer(args[2]);
            if (targetPlayer != null) {
                String cosmeticName = args[3];
                giveCosmetic(targetPlayer, cosmeticName);
                sender.sendMessage("Le cosmétique " + cosmeticName + " a été donné à " + targetPlayer.getName() + ".");
            } else {
                sender.sendMessage("Le joueur spécifié n'est pas en ligne !");
            }
            return true;
        }
        return false;
    }

    private void openCosmeticsMenu(Player player) {
        Inventory menu = Bukkit.createInventory(null, 9, "Menu Cosmétiques");
        menu.addItem(createMenuItem(Material.ENDER_CHEST, "Gadget"));
        menu.addItem(createMenuItem(Material.REDSTONE, "Particules"));
        menu.addItem(createMenuItem(Material.BONE, "Pets"));

        player.openInventory(menu);
    }

    private ItemStack createMenuItem(Material material, String displayName) {
        ItemStack item = new ItemStack(material);
        return item;
    }

    private void giveCosmetic(Player player, String cosmeticName) {

    }
}