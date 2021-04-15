package unt.unt;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class qwq extends PickaxeItem {

    public String a = "aaa" + new Random().nextInt(3);
    //初始化a，不要留空，因为不确玩家是先右键还是先看 要避免任何可能返回空的情况

    protected qwq(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText(a));
        //上节课写的工具提示(tooltip)，让它等于刚刚的变量a
    }

    //在右键时
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.getStackInHand(hand).damage(1, (LivingEntity)user, (p) -> p.sendToolBreakStatus(user.getActiveHand()));
        a = "aaa" + new Random().nextInt(3);
        //a是一个字符串(string)，让a保存“aaa”加上一个随机数
        //random是java的随机生成器，这里获取一个随机数，范围为0-2  随机数的取值范围和列表(list)有点像
        //现在a可能等于"aaa0","aaa1","aaa2"
        return TypedActionResult.success(user.getStackInHand(hand));
        //返回 动作结果.成功
    }

    //在右键方块时
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity user = context.getPlayer();
        Hand hand = context.getHand();
        World world = context.getWorld();
        //检查user是不是空
        assert user != null;

        ItemStack itemStack = user.getStackInHand(hand);
        //四个变量

        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
        //播放声音


        if (!world.isClient) {//检查是否为客户端世界，如果不是就执行  不是客户端就是服务器，生成实体只能在服务器进行
            EggEntity eggEntity = new EggEntity(world, user);
            //新建一个鸡蛋实体
            eggEntity.setItem(itemStack);
            eggEntity.setProperties(user, user.pitch, user.yaw, 0.0F, 1.5F, 1.0F);
            world.spawnEntity(eggEntity);
            //把实体生成在世界上
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        //玩家动作，使用

        return ActionResult.SUCCESS;
        //返回 动作结果.成功
    }

    //在对实体右键时
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        entity.damage(DamageSource.LAVA,10000);
        //对实体造成10000点伤害，伤害源是火焰
        return ActionResult.SUCCESS;
        //返回 动作结果.成功
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        new StatusEffectInstance(StatusEffects.ABSORPTION,100,5);
    }
}
