package dev.s7a.base64;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

@SuppressWarnings("unused")
public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        check("stone", new ItemStack(Material.STONE, 5));
    }

    private void check(String name, String base64, boolean match) {
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        builder.append(": ");
        if (base64.length() < 32) {
            builder.append(base64);
        } else {
            builder.append(base64.substring(0, 32).replace("\n", ""));
            builder.append("...");
        }
        builder.append(" -> ");
        if (match) {
            builder.append("success");
            getLogger().info(builder.toString());
        } else {
            builder.append("failure");
            getLogger().warning(builder.toString());
        }
    }

    private void check(String name, ItemStack itemStack) {
        String base64 = Base64ItemStack.encode(itemStack);
        check(name, base64, Objects.equals(Base64ItemStack.decode(base64), itemStack));
    }
}
