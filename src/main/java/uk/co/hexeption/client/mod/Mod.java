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

package uk.co.hexeption.client.mod;

import org.lwjgl.input.Keyboard;
import uk.co.hexeption.client.event.EventListener;
import uk.co.hexeption.client.managers.EventManager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class Mod implements EventListener {

    private String name = getClass().getAnnotation(ModInfo.class).name();

    private String description = getClass().getAnnotation(ModInfo.class).description();

    private Category category = getClass().getAnnotation(ModInfo.class).category();

    private int bind = getClass().getAnnotation(ModInfo.class).bind();

    private boolean state;


    public boolean getState() {

        return state;
    }

    public void setState(boolean state) {

        onToggle();
        if (state) {
            this.state = true;
            onEnable();
            EventManager.register(this);
        } else {
            this.state = false;
            onDisable();
        }

    }

    public void toggle() {

        setState(!this.state);
    }

    private void onToggle() {

    }

    private void onDisable() {

    }

    private void onEnable() {

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public Category getCategory() {

        return category;
    }

    public void setCategory(Category category) {

        this.category = category;
    }

    public int getBind() {

        return bind;
    }

    public void setBind(int bind) {

        this.bind = bind;
    }

    public String getKeyName() {

        return getBind() == -1 ? "-1" : Keyboard.getKeyName(getBind());
    }

    public enum Category {

        TEST(0x2384542);

        public int color;

        Category(int color) {

            this.color = color;
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface ModInfo {

        String name();

        String description();

        Category category();

        int bind() default Keyboard.KEY_NONE;
    }

}
