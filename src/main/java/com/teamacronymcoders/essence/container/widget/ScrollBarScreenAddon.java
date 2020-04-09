package com.teamacronymcoders.essence.container.widget;

import com.hrznstudio.titanium.client.screen.asset.IAssetProvider;
import net.minecraft.client.gui.screen.Screen;

public class ScrollBarScreenAddon extends BaseScrollableScreenAddon {

    protected ScrollBarScreenAddon(int posX, int posY) {
        super(0, 0, 0, 0, 0, 0, 0);
    }

    @Override
    public int getXSize() {
        return 0;
    }

    @Override
    public int getYSize() {
        return 0;
    }

    @Override
    public void drawBackgroundLayer(Screen screen, IAssetProvider iAssetProvider, int i, int i1, int i2, int i3, float v) {

    }

    @Override
    public void drawForegroundLayer(Screen screen, IAssetProvider iAssetProvider, int i, int i1, int i2, int i3) {

    }

    @Override
    protected int getMaxElements() {
        return 0;
    }

    @Override
    protected int getFocusedElements() {
        return 0;
    }
}
