package unt.unt;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Aa implements ModInitializer {

    public static final ScreenHandlerType<sh> sh = ScreenHandlerRegistry.registerSimple(new Identifier("unt","sh"),sh::new);

    public static BlockEntityType<mbe> mbet;

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier("unt", "itemgroup"),
            () -> new ItemStack(myitem.bbb));

    @Override
    public void onInitialize() {
        myitem.init();
        mbet = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("unt","mbe"), BlockEntityType.Builder.create(mbe::new, myitem.bwe).build(null));
    }
}
//assets.namespace.models.item
//item.unt.bbb
