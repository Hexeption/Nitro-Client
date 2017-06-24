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

package uk.co.hexeption.client.screen;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class MainMenu extends GuiMainMenu {

    private ResourceLocation nitroLogo = new ResourceLocation("nitro/logo.png");

    private ResourceLocation nitroBackground = new ResourceLocation("nitro/background.png");

    public MainMenu() {
        super();
    }

    @Override
    public void updateScreen() {
    }

    @Override
    public boolean doesGuiPauseGame() {
        return super.doesGuiPauseGame();
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void initGui() {
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    }

    @Override
    public void confirmClicked(boolean result, int id) {
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawBackgroundImage(nitroBackground);

    }

    private void drawBackgroundImage(ResourceLocation res) {
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        this.mc.getTextureManager().bindTexture(res);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2i(0, 0);
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2i(width, 0);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2i(width, height);
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2i(0, height);
        GL11.glEnd();
    }


    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }
}
