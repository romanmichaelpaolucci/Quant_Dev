package demo;

import ib.controller.Types;

import java.util.ArrayList;

public class EntrySignalA {

    private ArrayList<Double> prices;
    private OrderHandler orderHandler;

    public EntrySignalA(ArrayList<Double> prices){
        this.prices = prices;
        this.orderHandler = new OrderHandler();
        if(prices.size()>1) {
            if (prices.get(0) - prices.get(1) > 3) {
                orderHandler.placeBracketOrder(1000, Types.Action.BUY, 1, 1, 1, .5);
            }
            if(prices.get(0) - prices.get(1) < -3) {
                orderHandler.placeBracketOrder(1000, Types.Action.BUY, 1, 1, 1, .5);
            }
        }
    }

}
