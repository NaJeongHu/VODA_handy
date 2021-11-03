package com.example.voda_handy;

import java.util.ArrayList;

public class ShoppingList {
    private static ShoppingList sShoppingList;

    private String storeName;
    private ArrayList<Menu> mMenus;

    private ShoppingList() { }

    public static ShoppingList getShoppingList() {
        if(sShoppingList == null) {
            sShoppingList = new ShoppingList();
        }
        return sShoppingList;
    }

    //getter and setter
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public ArrayList<Menu> getMenus() {
        if(mMenus == null || mMenus.isEmpty()) return null;
        else return mMenus;
    }

    //method
    public Integer getTotalPrice() {
        if(mMenus == null || mMenus.isEmpty()) {
            return 0;
        }

        Integer totalPrice = 0;
        for(Menu m : mMenus) {
            totalPrice += m.getPrice() * m.getNumber();
        }
        return totalPrice;
    }

    public void reviseShoppingList(Menu menu) {
        for (Menu m : mMenus) {
            if(m.getMenuname().equals(menu.getMenuname())) {
                m.setNumber(menu.getNumber());
                return;
            }
        }
    }

    public void addShoppingList(Menu menu) {
        if(mMenus == null) {
            mMenus = new ArrayList<>();
        }

        for (Menu m : mMenus) {
            if(m.getMenuname().equals(menu.getMenuname())) {
                Integer number = m.getNumber();
                Integer addNumber = menu.getNumber();
                m.setNumber(number + addNumber);
                return;
            }
        }
        mMenus.add(menu);
    }

    public void deleteShoppingList(Menu menu) {
        for (Menu m : mMenus) {
            if(m.getMenuname().equals(menu.getMenuname())) {
                mMenus.remove(m);
                break;
            }
        }
    }

    public void removeShoppingList() {
        mMenus.clear();
    }
}
