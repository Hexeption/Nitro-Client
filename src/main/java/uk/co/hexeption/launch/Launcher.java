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
