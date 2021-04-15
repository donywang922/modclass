package unt.unt;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.lwjgl.system.CallbackI;

public class myitem {
    public static final Item bbb = new Item(new Item.Settings().group(Aa.ITEM_GROUP));//新建一个叫做bbb的物品，新建一个物品设置，设置物品组为FOOD

    public static final Item ns = new SwordItem(ToolMaterials.GOLD, 10000, (float) 0.9, new Item.Settings().group(Aa.ITEM_GROUP));//新建一个叫做bbb的物品，新建一个物品设置，设置物品组为FOOD


    public static final Item mp = new qwq(ToolMaterials.GOLD, 10000,  0.9f, new Item.Settings().group(Aa.ITEM_GROUP));


    public static final Block b = new myblock(FabricBlockSettings.of(Material.STONE).hardness(0.1f).luminance((state)-> state.get(myblock.in)*4));


    public static final Item boww = new BowItem(new Item.Settings().group(Aa.ITEM_GROUP));

    public static final Block bwe = new bwe(FabricBlockSettings.of(Material.STONE));
    public static final Item mbow = new mbow(new Item.Settings().group(Aa.ITEM_GROUP).maxDamage(1000));


    public static void init() {
        qr("bbb", bbb);//注册bbb，新建标签并标注物品属于unt，名叫bbb
        qr("sod", ns);
        qr( "pax", mp);
        qr("boww", boww);
        qr("bwe",bwe);
        qr("mbow",mbow);

        qr("b",b);
    }

    public static void qr(String path,Item item){
        Registry.register(Registry.ITEM, new Identifier("unt", path), item);
    }

    public static void qr(String path,Block block){
        Registry.register(Registry.BLOCK, new Identifier("unt",path),block);
        Registry.register(Registry.ITEM, new Identifier("unt",path),new BlockItem(block,new Item.Settings().group(Aa.ITEM_GROUP)));
    }

}
