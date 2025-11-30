package wootrevived.bloodmagicwootaddon.upgrades;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import wayoftime.bloodmagic.common.item.IBindable;
import wayoftime.bloodmagic.common.tile.TileAltar;
import wayoftime.bloodmagic.core.data.Binding;
import wayoftime.bloodmagic.core.data.SoulNetwork;
import wayoftime.bloodmagic.core.data.SoulTicket;
import wayoftime.bloodmagic.impl.BloodMagicAPI;
import wayoftime.bloodmagic.ritual.types.RitualWellOfSuffering;
import wayoftime.bloodmagic.util.helper.NetworkHelper;
import wootrevived.api.WootUpgradeItem;
import wootrevived.api.enums.UpgradeNoVariant;
import wootrevived.api.interfaces.WootDropsProperties;
import wootrevived.api.registrations.WootUpgradeItemRegistration;
import wootrevived.bloodmagicwootaddon.BloodMagicWootAddon;
import wootrevived.bloodmagicwootaddon.config.AddonConfig;

import java.util.List;
import java.util.UUID;

public class BloodCollector extends WootUpgradeItem<UpgradeNoVariant> implements IBindable {
    public static String ALTAR_POS_TAG = "Altar";

    public BloodCollector() {
        super(new Properties(), UpgradeNoVariant.NONE);
    }

    @Override
    public void modifyDrops(@NotNull WootDropsProperties properties, @NotNull CompoundTag itemTag) {
        if(!itemTag.contains(ALTAR_POS_TAG))
            return;

        Tag bindingTag = itemTag.get("binding");
        if (bindingTag == null || bindingTag.getId() != 10)
            return;

        Binding binding = new Binding(new UUID(0, 0), "");
        binding.deserializeNBT((CompoundTag) bindingTag);

        SoulNetwork network = NetworkHelper.getSoulNetwork(binding);

        BlockPos pos = BlockPos.of(itemTag.getLong(ALTAR_POS_TAG));
        BlockEntity be = properties.getHeartLevel().getBlockEntity(pos);

        if(!(be instanceof TileAltar altar))
            return;

        int cost = AddonConfig.BLOOD_COLLECTOR_COST.get();

        int currentEssence = network.getCurrentEssence();

        if(currentEssence < cost){
            network.causeNausea();
            return;
        }

        network.syphon(SoulTicket.block(properties.getHeartLevel(), properties.getHeartPos(), cost));

        ResourceLocation entityId = ForgeRegistries.ENTITY_TYPES.getKey(properties.getFactoryMob().getEntityType());

        if(BloodMagicAPI.INSTANCE.getBlacklist().getSacrifice().contains(entityId))
            return;

        int lifeEssenceRatio = BloodMagicAPI.INSTANCE.getValueManager().getSacrificial().getOrDefault(entityId, RitualWellOfSuffering.SACRIFICE_AMOUNT);

        if(lifeEssenceRatio <= 0)
            return;

        LivingEntity entity = properties.getEntity();
        if (entity != null && entity.isBaby())
            lifeEssenceRatio *= 0.5F;

        altar.sacrificialDaggerCall(lifeEssenceRatio, true);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        if(!stack.hasTag())
            return false;

        return stack.getTag().contains(ALTAR_POS_TAG);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag)
    {
        if (!stack.hasTag())
            return;

        CompoundTag tag = stack.getTag();
        if (tag.contains(ALTAR_POS_TAG)){
            BlockPos pos = BlockPos.of(tag.getLong(ALTAR_POS_TAG));
            tooltip.add(Component.translatable("tooltip.bloodmagicwootaddon.altar_binded", pos.getX(), pos.getY(), pos.getZ()).withStyle(ChatFormatting.GRAY));
        } else {
            tooltip.add(Component.translatable("tooltip.bloodmagicwootaddon.altar_not_binded").withStyle(ChatFormatting.GRAY));
        }

        Binding binding = getBinding(stack);
        if (binding != null)
            tooltip.add(Component.translatable("tooltip.bloodmagic.currentOwner", binding.getOwnerName()).withStyle(ChatFormatting.GRAY));
    }

    /* Upgrade Item registration */

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.Keys.ITEMS, BloodMagicWootAddon.MOD_ID);

    public static void register(WootUpgradeItemRegistration registration){
        ITEMS.register(registration.getWootEventBus());
        registration.register(BLOOD_COLLECTOR_ITEM);
    }

    public static final String BLOOD_COLLECTOR_TAG = "blood_collector_upgrade";
    public static final RegistryObject<BloodCollector> BLOOD_COLLECTOR_ITEM = ITEMS.register(BLOOD_COLLECTOR_TAG, BloodCollector::new);
}
