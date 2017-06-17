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

package uk.co.hexeption.launch;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Launcher implements ITweaker {

    private ArrayList<String> list = new ArrayList<>();


    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {

        this.list.addAll(args);

        if (!args.contains("--version") && profile != null) {
            this.list.add("--version");
            this.list.add(profile);
        }
        if (!args.contains("--assetDir") && assetsDir != null) {
            this.list.add("--assetDir");
            this.list.add(assetsDir.getAbsolutePath());
        }
        if (!args.contains("--gameDir") && gameDir != null) {
            this.list.add("--gameDir");
            this.list.add(gameDir.getAbsolutePath());
        }
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {

        MixinBootstrap.init();

        Mixins.addConfiguration("mixins.client.json");

        MixinEnvironment.getDefaultEnvironment().setSide(MixinEnvironment.Side.CLIENT);

    }

    @Override
    public String getLaunchTarget() {

        return "net.minecraft.client.main.Main";
    }

    @Override
    public String[] getLaunchArguments() {

        return this.list.toArray(new String[this.list.size()]);
    }

}
