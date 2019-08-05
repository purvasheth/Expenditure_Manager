package com.example.expenses;

/**
 * Created by purva on 21/4/18.
 */

public class ManageCost {
    int _id;
    int cost;

    public ManageCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
