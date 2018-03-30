package com.example.cl1434.lee_ice_cream;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by COREY on 3/20/2017.
 */

public class OrderItem implements Serializable {
    Date date;
    String flavor;
    String size;
    Double cost;

    public OrderItem(Date date, String flavor, String size, Double cost) {
        this.date = date;
        this.flavor = flavor;
        this.size = size;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "date=" + date +
                "\n flavor='" + flavor + '\'' +
                "\n  size='" + size + '\'' +
                "\n cost=" + cost +
                '}';
    }
}
