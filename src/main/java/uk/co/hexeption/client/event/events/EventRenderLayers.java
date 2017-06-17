/*******************************************************************************
 *             DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *                     Version 2, December 2004
 *
 *  Copyright (C) 2004 Sam Hocevar <sam@hocevar.net>
 *
 *  Everyone is permitted to copy and distribute verbatim or modified
 *  copies of this license document, and changing it is allowed as long
 *  as the name is changed.
 *
 *             DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *    TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
 *
 *   0. You just DO WHAT THE FUCK YOU WANT TO.
 *
 ******************************************************************************/

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
