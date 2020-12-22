package net.mcreator.chocolatemilk;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.forgespi.language.ModFileScanData;

public class ChocolateMilkModElements
{
  public final List<ModElement> elements = new ArrayList<>();
  public final List<Supplier<Block>> blocks = new ArrayList<>();
  public final List<Supplier<Item>> items = new ArrayList<>();
  public final List<Supplier<Biome>> biomes = new ArrayList<>();
  public final List<Supplier<EntityType<?>>> entities = new ArrayList<>();
  public final List<Supplier<Enchantment>> enchantments = new ArrayList<>();
  public static Map<ResourceLocation, SoundEvent> sounds = new HashMap<>();

  private int messageID;

  public void registerSounds(RegistryEvent.Register<SoundEvent> event) {
    for (Map.Entry<ResourceLocation, SoundEvent> sound : sounds.entrySet())
      event.getRegistry().register(((SoundEvent)sound.getValue()).setRegistryName(sound.getKey())); 
  }
  public ChocolateMilkModElements() { this.messageID = 0; try { ModFileScanData modFileInfo = ModList.get().getModFileById("chocolate_milk").getFile().getScanResult(); Set<ModFileScanData.AnnotationData> annotations = modFileInfo.getAnnotations(); for (ModFileScanData.AnnotationData annotationData : annotations) { if (annotationData.getAnnotationType().getClassName().equals(ModElement.Tag.class.getName())) { Class<?> clazz = Class.forName(annotationData.getClassType().getClassName()); if (clazz.getSuperclass() == ModElement.class)
            this.elements.add(clazz.getConstructor(new Class[] { getClass() }).newInstance(new Object[] { this }));  }  }  }
    catch (Exception e) { e.printStackTrace(); }
     Collections.sort(this.elements); this.elements.forEach(ModElement::initElements); } public <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, PacketBuffer> encoder, Function<PacketBuffer, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) { ChocolateMilkMod.PACKET_HANDLER.registerMessage(this.messageID, messageType, encoder, decoder, messageConsumer);
    this.messageID++; }

  public List<ModElement> getElements() {
    return this.elements;
  }
  
  public List<Supplier<Block>> getBlocks() {
    return this.blocks;
  }
  
  public List<Supplier<Item>> getItems() {
    return this.items;
  }
  
  public List<Supplier<Biome>> getBiomes() {
    return this.biomes;
  }
  
  public List<Supplier<EntityType<?>>> getEntities() {
    return this.entities;
  }
  
  public List<Supplier<Enchantment>> getEnchantments() {
    return this.enchantments;
  }
  
  public static class ModElement
    implements Comparable<ModElement> {
    protected final ChocolateMilkModElements elements;
    protected final int sortid;
    
    public ModElement(ChocolateMilkModElements elements, int sortid) {
      this.elements = elements;
      this.sortid = sortid;
    }
    public void initElements() {}
    public void init(FMLCommonSetupEvent event) {}

    
    public void serverLoad(FMLServerStartingEvent event) {}

    
    @OnlyIn(Dist.CLIENT)
    public void clientLoad(FMLClientSetupEvent event) {}

    
    public int compareTo(ModElement other) {
      return this.sortid - other.sortid;
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    public static @interface Tag {}
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  public static @interface Tag {}
}