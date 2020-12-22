package net.mcreator.chocolatemilk.procedures;

import java.util.Map;
import net.mcreator.chocolatemilk.ChocolateMilkModElements;
import net.mcreator.chocolatemilk.ChocolateMilkModElements.ModElement.Tag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

@Tag
public class ChoccyMilkFoodEatenProcedure
  extends ChocolateMilkModElements.ModElement {
  public ChoccyMilkFoodEatenProcedure(ChocolateMilkModElements instance) {
    super(instance, 5);
  }
  
  public static void executeProcedure(Map<String, Object> dependencies) {
    if (dependencies.get("entity") == null) {
      if (!dependencies.containsKey("entity"))
        System.err.println("Failed to load dependency entity for procedure ChoccyMilkFoodEaten!"); 
      return;
    } 
    Entity entity = (Entity)dependencies.get("entity");
    if (entity instanceof LivingEntity)
      ((LivingEntity)entity).func_195064_c(new EffectInstance(Effects.field_76424_c, 600, 2)); 
    if (entity instanceof LivingEntity)
      ((LivingEntity)entity).func_195064_c(new EffectInstance(Effects.field_76444_x, 1800, 2)); 
    if (entity instanceof LivingEntity) {
      ((LivingEntity)entity).func_195063_d(Effects.field_76421_d);
    }
    if (entity instanceof LivingEntity) {
      ((LivingEntity)entity).func_195063_d(Effects.field_76419_f);
    }
    if (entity instanceof LivingEntity) {
      ((LivingEntity)entity).func_195063_d(Effects.field_76433_i);
    }
    if (entity instanceof LivingEntity) {
      ((LivingEntity)entity).func_195063_d(Effects.field_76431_k);
    }
    if (entity instanceof LivingEntity) {
      ((LivingEntity)entity).func_195063_d(Effects.field_76440_q);
    }
    if (entity instanceof LivingEntity) {
      ((LivingEntity)entity).func_195063_d(Effects.field_76438_s);
    }
    if (entity instanceof LivingEntity) {
      ((LivingEntity)entity).func_195063_d(Effects.field_76437_t);
    }
    if (entity instanceof LivingEntity) {
      ((LivingEntity)entity).func_195063_d(Effects.field_76436_u);
    }
    if (entity instanceof LivingEntity) {
      ((LivingEntity)entity).func_195063_d(Effects.field_82731_v);
    }
    if (entity instanceof LivingEntity) {
      ((LivingEntity)entity).func_195063_d(Effects.field_188423_x);
    }
    if (entity instanceof LivingEntity) {
      ((LivingEntity)entity).func_195063_d(Effects.field_188424_y);
    }
    if (entity instanceof LivingEntity) {
      ((LivingEntity)entity).func_195063_d(Effects.field_189112_A);
    }
    if (entity instanceof LivingEntity)
      ((LivingEntity)entity).func_195063_d(Effects.field_220309_E); 
  }
}