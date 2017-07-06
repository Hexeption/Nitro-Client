package uk.co.hexeption.client.font;

import java.awt.*;
import java.io.InputStream;

public enum FontLoader {
    INSTANCE;

    public final MinecraftFontRenderer standard12 = loadFonts("roboto-bold.ttf", Font.PLAIN, 12);

    public final MinecraftFontRenderer standard14 = loadFonts("roboto-bold.ttf", Font.PLAIN, 14);

    public final MinecraftFontRenderer standard20 = loadFonts("roboto-bold.ttf", Font.PLAIN, 20);

    public final MinecraftFontRenderer standard25 = loadFonts("roboto-bold.ttf", Font.PLAIN, 25);

    private static MinecraftFontRenderer loadFonts(String path, int style, float size) {

        path = "/assets/nitro/fonts/" + path;

        try {
            InputStream inputStream = FontLoader.class.getResourceAsStream(path);

            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            font = font.deriveFont(style, size);

            return new MinecraftFontRenderer(font, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new MinecraftFontRenderer(new Font("Comic Sans MS", Font.PLAIN, (int) size), true);
    }

}
