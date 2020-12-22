package net.mcreator.chocolatemilk;

import java.util.function.Supplier;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("chocolate_milk")
public class ChocolateMilkMod
{
  public static final Logger LOGGER = LogManager.getLogger(ChocolateMilkMod.class);
  private static final String PROTOCOL_VERSION = "1";
  public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation("chocolate_milk", "chocolate_milk"), () -> "1", "1"::equals, "1"::equals);
  public ChocolateMilkModElements elements;
  
  public ChocolateMilkMod() {
    this.elements = new ChocolateMilkModElements();
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
    FMLJavaModLoadingContext.get().getModEventBus().register(this);
    MinecraftForge.EVENT_BUS.register(this);
  }
  
  private void init(FMLCommonSetupEvent event) {
    this.elements.getElements().forEach(element -> element.init(event));
  }
  
  @SubscribeEvent
  public void serverLoad(FMLServerStartingEvent event) {
    this.elements.getElements().forEach(element -> element.serverLoad(event));
  }
  
  @SubscribeEvent
  @OnlyIn(Dist.CLIENT)
  public void clientLoad(FMLClientSetupEvent event) {
    this.elements.getElements().forEach(element -> element.clientLoad(event));
  }
  
  @SubscribeEvent
  public void registerBlocks(RegistryEvent.Register<Block> event) {
    event.getRegistry().registerAll((IForgeRegistryEntry[])this.elements.getBlocks().stream().map(Supplier::get).toArray(x$0 -> new Block[x$0]));
  }
  
  @SubscribeEvent
  public void registerItems(RegistryEvent.Register<Item> event) {
    event.getRegistry().registerAll((IForgeRegistryEntry[])this.elements.getItems().stream().map(Supplier::get).toArray(x$0 -> new Item[x$0]));
  }
  
  @SubscribeEvent
  public void registerBiomes(RegistryEvent.Register<Biome> event) {
    event.getRegistry().registerAll((IForgeRegistryEntry[])this.elements.getBiomes().stream().map(Supplier::get).toArray(x$0 -> new Biome[x$0]));
  }
  
  @SubscribeEvent
  public void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
    event.getRegistry().registerAll((IForgeRegistryEntry[])this.elements.getEntities().stream().map(Supplier::get).toArray(x$0 -> new EntityType[x$0]));
  }
  
  @SubscribeEvent
  public void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
    event.getRegistry().registerAll((IForgeRegistryEntry[])this.elements.getEnchantments().stream().map(Supplier::get).toArray(x$0 -> new Enchantment[x$0]));
  }
  
  @SubscribeEvent
  public void registerSounds(RegistryEvent.Register<SoundEvent> event) {
    this.elements.registerSounds(event);
  }
}