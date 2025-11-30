package wootrevived.bloodmagicwootaddon.events;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wayoftime.bloodmagic.common.tile.TileAltar;
import wayoftime.bloodmagic.util.handler.event.GenericHandler;
import wayoftime.bloodmagic.util.helper.PlayerHelper;
import wootrevived.bloodmagicwootaddon.BloodMagicWootAddon;
import wootrevived.bloodmagicwootaddon.upgrades.BloodCollector;

@Mod.EventBusSubscriber(modid = BloodMagicWootAddon.MOD_ID)
public class PlayerBlockInteract {
    @SubscribeEvent
    public static void onBlockInteract(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();

        if (PlayerHelper.isFakePlayer(player))
            return;

        ItemStack held = event.getItemStack();

        if (held.getItem() != BloodCollector.BLOOD_COLLECTOR_ITEM.get())
            return;

        Level level = event.getLevel();
        BlockEntity be = level.getBlockEntity(event.getPos());

        if (!(be instanceof TileAltar))
            return;

        CompoundTag itemTag = held.getOrCreateTag();
        itemTag.putLong(BloodCollector.ALTAR_POS_TAG, event.getPos().asLong());
        new GenericHandler().onInteract(new PlayerInteractEvent.RightClickItem(player, event.getHand()));

        player.swing(event.getHand());

        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.CONSUME);
    }
}
