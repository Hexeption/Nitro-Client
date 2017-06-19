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

package uk.co.hexeption.client.events;

import net.minecraft.client.multiplayer.WorldClient;

public class EventWorld {

    public EventWorld() {

    }

    public static class Load {

        private final WorldClient world;

        public Load(WorldClient worldClient) {

            this.world = worldClient;
        }

        public WorldClient getWorld() {

            return world;
        }
    }

    public static class Unload {

    }
}
