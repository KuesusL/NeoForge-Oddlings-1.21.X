package net.zeriko.oddlings.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.zeriko.oddlings.Oddlings;

public class ModTags {

    public static class Blocks {
        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Oddlings.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> ODD_TRANSFORMABLE = createTag("odd_transformable");
        public static final TagKey<Item> ODD_ASHES_TRANSFORMABLE = createTag("odd_ashes_transformable");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Oddlings.MOD_ID, name));
        }
    }
}
