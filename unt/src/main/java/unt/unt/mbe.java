package unt.unt;


import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;

public class mbe extends LootableContainerBlockEntity {
    public DefaultedList<ItemStack> list = DefaultedList.ofSize(12,ItemStack.EMPTY);

    public mbe() {
        super(Aa.mbet);
    }

    public void use(ItemStack item) {

        //第一步，判断玩家有没有拿着东西
        if (item.isEmpty()) {//如果玩家没有拿东西 item是传入值

            for (ItemStack Item:list){
                if (!Item.isEmpty()) {//如果i不为空
                    //第三步，把i里的东西丢出来
                    ItemScatterer.spawn(this.world, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), Item);//在世界上方块实体的位置生成物品
                    Item.decrement(Item.getCount());
                    break;
                }
            }

        } else {//如果玩家拿了东西
            for (int num=0;num<12;num++){
                if (list.get(num).getItem() == item.getItem()) {
                    list.get(num).increment(item.getCount());
                    item.decrement(item.getCount());
                }
            }
            for (int num=0;num<12;num++){
                if (list.get(num).isEmpty()) {
                    list.set(num, item.copy());
                    item.decrement(item.getCount());
                }
            }
        }
        markDirty();
    }

    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        Inventories.fromTag(tag, list);
        markDirty();
    }

    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        Inventories.toTag(tag, list);
        return tag;
    }

    @Override
    protected Text getContainerName() {

        return new TranslatableText("aaa");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {

//        return new GenericContainerScreenHandler(ScreenHandlerType.GENERIC_9X1,syncId,playerInventory,this,1);
        return new sh(syncId,playerInventory,this);
    }

    protected DefaultedList<ItemStack> getInvStackList() {
        return this.list;
    }

    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.list = list;
    }

    @Override
    public int size() {
        return 12;
    }
}
