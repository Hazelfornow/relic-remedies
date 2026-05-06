package hazel.relic.registry;

import devv.capozi.zip.common.index.Registrar;
import hazel.relic.RelicRemedies;
import hazel.relic.AstralScrap;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class RelicsItems {
    public static Registrar<Item>itemRegistrar = new Registrar<Item>(RelicRemedies.MOD_ID, Registries.ITEM);
    public static final Item ASTRAL_SCRAP = itemRegistrar.add(Identifier.of(RelicRemedies.MOD_ID, "astral_scrap"), new AstralScrap(new Item.Settings()));
    public static void innit() {
        itemRegistrar.setRegistries(itemRegistrar.entries, itemRegistrar.registry_consumer);
    }
}
