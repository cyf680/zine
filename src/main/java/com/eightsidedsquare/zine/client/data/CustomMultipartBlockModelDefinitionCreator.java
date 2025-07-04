package com.eightsidedsquare.zine.client.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.block.Block;
import net.minecraft.client.data.BlockModelDefinitionCreator;
import net.minecraft.client.render.model.BlockStateModel;
import net.minecraft.client.render.model.json.BlockModelDefinition;
import net.minecraft.client.render.model.json.MultipartModelComponent;
import net.minecraft.client.render.model.json.MultipartModelCondition;
import net.minecraft.client.render.model.json.MultipartModelConditionBuilder;

import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;

public class CustomMultipartBlockModelDefinitionCreator implements BlockModelDefinitionCreator {

    private final Block block;
    private final BlockModelDefinition.Multipart multipart = new BlockModelDefinition.Multipart(new ObjectArrayList<>());

    private CustomMultipartBlockModelDefinitionCreator(Block block) {
        this.block = block;
    }

    private CustomMultipartBlockModelDefinitionCreator(Block block, List<MultipartModelComponent> components) {
        this(block);
        for (MultipartModelComponent component : components) {
            component.selector().ifPresent(this::validate);
            this.multipart.selectors().add(component);
        }
    }

    public static CustomMultipartBlockModelDefinitionCreator create(Block block) {
        return new CustomMultipartBlockModelDefinitionCreator(block);
    }

    @Override
    public Block getBlock() {
        return this.block;
    }

    private void validate(MultipartModelCondition condition) {
        condition.instantiate(this.block.getStateManager());
    }

    public CustomMultipartBlockModelDefinitionCreator with(MultipartModelCondition condition, BlockStateModel.Unbaked model) {
        this.validate(condition);
        this.multipart.selectors().add(new MultipartModelComponent(Optional.of(condition), model));
        return this;
    }

    public CustomMultipartBlockModelDefinitionCreator with(MultipartModelConditionBuilder conditionBuilder, BlockStateModel.Unbaked model) {
        return this.with(conditionBuilder.build(), model);
    }

    public CustomMultipartBlockModelDefinitionCreator with(BlockStateModel.Unbaked model) {
        return this.with(new MultipartModelConditionBuilder(), model);
    }

    public CustomMultipartBlockModelDefinitionCreator apply(UnaryOperator<BlockStateModel.Unbaked> operator) {
        List<MultipartModelComponent> components = new ObjectArrayList<>();
        for (MultipartModelComponent selector : this.multipart.selectors()) {
            components.add(new MultipartModelComponent(selector.selector(), operator.apply(selector.model())));
        }
        return new CustomMultipartBlockModelDefinitionCreator(this.block, components);
    }

    @Override
    public BlockModelDefinition createBlockModelDefinition() {
        return new BlockModelDefinition(Optional.empty(), Optional.of(this.multipart));
    }
}
