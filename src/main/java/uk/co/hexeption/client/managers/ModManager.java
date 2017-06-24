/*******************************************************************************
 *     Nitro Client
 *     Copyright (C) 2017  Hexeption (Keir Davis)
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
