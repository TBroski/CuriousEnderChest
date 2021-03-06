package potatocult.curious_ender_chest;

import net.minecraft.block.Blocks;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkHooks;
import top.theillusivec4.curios.api.CuriosAPI;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_F;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("curious_ender_chest")
public class CuriousEnderChest {

    // Directly reference a log4j logger.
    //private static final Logger LOGGER = LogManager.getLogger();
    public static KeyBinding OPEN_ENDERCHEST = new KeyBinding("key.curious_ender_chest.open_enderchest.desc", GLFW_KEY_F, "key.categories.gameplay");

    public CuriousEnderChest() {
        MinecraftForge.EVENT_BUS.register(this);
        ClientRegistry.registerKeyBinding(OPEN_ENDERCHEST);
    }

    @SubscribeEvent
    public void HandleKey(InputEvent.KeyInputEvent event) {

    }

    public static void OpenEnderChestGUI(ServerPlayerEntity entity) {
        if (CuriousEnderChest.OPEN_ENDERCHEST.isKeyDown()) {
            if (CuriosAPI.getCurioEquipped(Item.getItemFromBlock(Blocks.ENDER_CHEST), entity).isPresent()) {
                NetworkHooks.openGui(entity, new SimpleNamedContainerProvider((id, playerInventory, player) -> {
                    return ChestContainer.createGeneric9X3(id, playerInventory, entity.getInventoryEnderChest());
                }, new TranslationTextComponent("container.enderchest")));
            }
        }
    }
}
