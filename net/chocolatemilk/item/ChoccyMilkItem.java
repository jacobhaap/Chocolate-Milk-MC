package net.mcreator.chocolatemilk.item;

import java.util.HashMap;
import java.util.Map;
import net.mcreator.chocolatemilk.ChocolateMilkModElements;
import net.mcreator.chocolatemilk.ChocolateMilkModElements.ModElement.Tag;
import net.mcreator.chocolatemilk.procedures.ChoccyMilkFoodEatenProcedure;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.item.UseAction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ObjectHolder;

@Tag
public class ChoccyMilkItem extends ChocolateMilkModElements.ModElement {
  @ObjectHolder("chocolate_milk:choccy_milk")
  public static final Item block = null;
  public ChoccyMilkItem(ChocolateMilkModElements instance) {
    super(instance, 4);
  }

  
  public void initElements() {
    this.elements.items.add(() -> new FoodItemCustom());
  }
  
  public static class FoodItemCustom extends Item { public FoodItemCustom() {
      super((new Item.Properties()).func_200916_a(ItemGroup.field_78039_h).func_200917_a(16).func_208103_a(Rarity.EPIC)
          .func_221540_a((new Food.Builder()).func_221456_a(8).func_221454_a(0.3F).func_221455_b().func_221453_d()));
      setRegistryName("choccy_milk");
    }

    
    public int func_77626_a(ItemStack stack) {
      return 20;
    }

    
    @OnlyIn(Dist.CLIENT)
    public boolean func_77636_d(ItemStack itemstack) {
      return true;
    }

    
    public UseAction func_77661_b(ItemStack itemstack) {
      return UseAction.DRINK;
    }

    
    public SoundEvent func_225519_S__() {
      return SoundEvents.field_187664_bz;
    }

    
    public ItemStack func_77654_b(ItemStack itemstack, World world, LivingEntity entity) {
      ItemStack retval = new ItemStack((IItemProvider)Items.field_151069_bo, 1);
      super.func_77654_b(itemstack, world, entity);
      double x = entity.func_226277_ct_();
      double y = entity.func_226278_cu_();
      double z = entity.func_226281_cx_();
      
      Map<String, Object> $_dependencies = new HashMap<>();
      $_dependencies.put("entity", entity);
      ChoccyMilkFoodEatenProcedure.executeProcedure($_dependencies);
      
      if (itemstack.func_190926_b()) {
        return retval;
      }
      if (entity instanceof PlayerEntity) {
        PlayerEntity player = (PlayerEntity)entity;
        if (!player.func_184812_l_() && !player.field_71071_by.func_70441_a(retval))
          player.func_71019_a(retval, false); 
      } 
      return itemstack;
    } }

}