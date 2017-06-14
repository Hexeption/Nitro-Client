package uk.co.hexeption.client.managers;

import com.google.common.collect.Lists;
import uk.co.hexeption.client.mod.Mod;
import uk.co.hexeption.client.mod.mods.Test;
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

        addMods(new Test());
    }

    public void addMods(final Mod... mods) {

        for (final Mod mod : mods) {
            this.MODS.add(mod);
        }
    }

    public List<Mod> getMods() {

        synchronized (this.MODS) {
            return this.MODS;
        }
    }

}
