package wutdahack.oj;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class OrangeJuice implements ModInitializer {

    public static final Item ORANGE_JUICE_ICON = new Item(new Item.Settings());

    public static final ItemGroup ORANGE_JUICE_GROUP = FabricItemGroupBuilder.build(new Identifier("orangejuice", "orange_juice_group"), () -> new ItemStack(ORANGE_JUICE_ICON));

    public static final FoodComponent ORANGE_FOOD = (new FoodComponent.Builder()).hunger(4).saturationModifier(0.3F).statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 3600, 1), 0.7F).snack().build();
    public static final FoodComponent ORANGE_JUICE_FOOD = (new FoodComponent.Builder()).hunger(6).saturationModifier(0.1F).statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 3000), 0.6F).snack().build();


    public static final Item ORANGE = new Item(new Item.Settings().food(ORANGE_FOOD).group(ORANGE_JUICE_GROUP));
    public static final Item ORANGE_JUICE = new Item(new Item.Settings().food(ORANGE_JUICE_FOOD).group(ORANGE_JUICE_GROUP));



    private static final Identifier OAK_LEAVES_LOOT_TABLE_ID = new Identifier("minecraft", "blocks/oak_leaves");
    private static final Identifier BIRCH_LEAVES_LOOT_TABLE_ID = new Identifier("minecraft", "blocks/birch_leaves");



    @Override
    public void onInitialize() {

        Registry.register(Registry.ITEM, new Identifier("orangejuice", "orange_juice_icon"), ORANGE_JUICE_ICON);

        Registry.register(Registry.ITEM, new Identifier("orangejuice", "orange"), ORANGE);
        Registry.register(Registry.ITEM, new Identifier("orangejuice", "orange_juice"), ORANGE_JUICE);

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (OAK_LEAVES_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootTableRange.create(1))
                        .withEntry(ItemEntry.builder(ORANGE)
                        .weight(5)
                        .build());

                supplier.withPool(poolBuilder.build());
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (BIRCH_LEAVES_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootTableRange.create(1))
                        .withEntry(ItemEntry.builder(ORANGE)
                                .weight(5)
                                .build());

                supplier.withPool(poolBuilder.build());
            }
        });

    }
}
