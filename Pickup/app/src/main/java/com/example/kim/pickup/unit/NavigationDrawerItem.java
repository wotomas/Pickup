package com.example.kim.pickup.unit;

/**
 * Created by kim on 2015-12-13.
 */
public class NavigationDrawerItem {
    private String title;
    private int icon;
    private String Count = "0";
    private boolean isCounterVisible = false;

    public NavigationDrawerItem(boolean isCounterVisible, String title, int icon, String count) {
        this.isCounterVisible = isCounterVisible;
        this.title = title;
        this.icon = icon;
        Count = count;
    }

    public NavigationDrawerItem(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCounterVisible() {
        return isCounterVisible;
    }

    public void setCounterVisible(boolean isCounterVisible) {
        this.isCounterVisible = isCounterVisible;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
