package islandModel.island;

import islandModel.services.Settings;

public class SizeIsland {
    private final int length;
    private final int width;

    public SizeIsland() {
        this.length = Settings.get().getLengthIsland();
        this.width = Settings.get().getWidthIsland();
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }
}
