package uk.co.hexeption.client.event.events;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import uk.co.hexeption.client.event.Event;

public class EventRenderLayers extends Event {

    private final EntityLivingBase entityLivingBase;

    private final LayerRenderer layerRenderer;

    public EventRenderLayers(Type type, EntityLivingBase entityLivingBase, LayerRenderer layerRenderer) {

        super(type);
        this.entityLivingBase = entityLivingBase;
        this.layerRenderer = layerRenderer;
    }

    public EntityLivingBase getEntityLivingBase() {

        return entityLivingBase;
    }

    public LayerRenderer getLayerRenderer() {

        return layerRenderer;
    }
}
