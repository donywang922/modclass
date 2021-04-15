package unt.unt.client;

import com.google.common.collect.Maps;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.ModelPredicateProvider;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class AaClient implements ClientModInitializer {


    private static void register(Item item, Identifier id, ModelPredicateProvider provider) {

        try {
            Class<?> c = Class.forName("net.minecraft.client.item.ModelPredicateProviderRegistry");
            Field f = c.getDeclaredField("ITEM_SPECIFIC");
            f.setAccessible(true);
            ((Map) ((Map) (f.get(c))).computeIfAbsent(item, (itemx) -> Maps.newHashMap())
            ).put(id, provider);
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onInitializeClient() {

    }
}
