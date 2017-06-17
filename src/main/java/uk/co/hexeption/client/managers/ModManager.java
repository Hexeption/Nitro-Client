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

package uk.co.hexeption.client.managers;

import com.google.common.collect.Lists;
import org.reflections.Reflections;
import uk.co.hexeption.client.mod.Mod;
import uk.co.hexeption.client.utils.LogHelper;

import java.util.List;

public class ModManager {

    private final List<Mod> MODS = Lists.newCopyOnWriteArrayList();

    public void init() {

        initMods();
        LogHelper.info(String.format("Mods Loaded: %s!", MODS.size()));
        for (Mod mod : MODS) {
            LogHelper.info(String.format("%s Loaded!", mod.getName()));
        }
    }

    private void initMods() {

        new Reflections("uk.co.hexeption.client.mod.mods").getSubTypesOf(Mod.class).forEach(mod -> {
            try {
                this.MODS.add(mod.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    public List<Mod> getMods() {

        synchronized (this.MODS) {
            return this.MODS;
        }
    }

}
