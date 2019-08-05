package com.example.expenses;

/**
 * Created by purva on 21/4/18.
 */

public class Items {
    int _id;
    int cost;
    String date;
    String cause;
    String detail;

    @Override
    public String toString() {
        return cost + " " + cause + " " + detail + " " + date + '\n';
    }



    public int get_id() {
        return _id;
    }

    public int getCost() {
        return cost;
    }

    public String getDate() {
        return date;
    }

    public String getCause() {
        return cause;
    }

    public String getDetail() {
        return detail;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
