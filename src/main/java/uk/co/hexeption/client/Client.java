package uk.co.hexeption.client;

import uk.co.hexeption.client.managers.ModManager;
import uk.co.hexeption.client.utils.LogHelper;

public enum Client {

    INSTANCE;

    public final ModManager modManager = new ModManager();


    public void start() {

        LogHelper.info("Loading Mods...");
        modManager.init();

        Runtime.getRuntime().addShutdownHook(new Thread(this::end));
    }

    public void end() {

    }

}
