package demo;

import ib.controller.*;

import java.util.ArrayList;
import java.util.List;

public class OrderHandler implements ApiController.ILiveOrderHandler, ApiController.IOrderHandler {

    //This is taken straight from the API Documentation, with some minor modifications
    private static List<NewOrder> BracketOrder(int parentOrderId, Types.Action action, int quantity, double limitPrice, double takeProfitLimitPrice, double stopLossPrice) {
        //This will be our main or "parent" order
        NewOrder parent = new NewOrder();
        parent.orderId(parentOrderId);
        parent.action(action);
        parent.orderType(OrderType.LMT);
        parent.totalQuantity(quantity);
        parent.lmtPrice(limitPrice);
        //The parent and children orders will need this attribute set to false to prevent accidental executions.
        //The LAST CHILD will have it set to true.
        parent.transmit(false);

        NewOrder takeProfit = new NewOrder();
        takeProfit.orderId(parent.orderId() + 1);
        takeProfit.action(action.equals(Types.Action.BUY) ? Types.Action.SELL : Types.Action.BUY);
        takeProfit.orderType(OrderType.LMT);
        takeProfit.totalQuantity(quantity);
        takeProfit.lmtPrice(takeProfitLimitPrice);
        takeProfit.parentId(parentOrderId);
        takeProfit.transmit(false);

        NewOrder stopLoss = new NewOrder();
        stopLoss.orderId(parent.orderId() + 2);
        stopLoss.action(action.equals(Types.Action.BUY) ? Types.Action.SELL : Types.Action.BUY);
        stopLoss.orderType(OrderType.STP);
        //Stop trigger price
        stopLoss.auxPrice(stopLossPrice);
        stopLoss.totalQuantity(quantity);
        stopLoss.parentId(parentOrderId);
        //In this case, the low side order will be the last child being sent. Therefore, it needs to set this attribute to true
        //to activate all its predecessors
        stopLoss.transmit(true);

        List<NewOrder> bracketOrder = new ArrayList<>();
        bracketOrder.add(parent);
        bracketOrder.add(takeProfit);
        bracketOrder.add(stopLoss);

        return bracketOrder;
    }

    static NewContract initializeContract(){
        NewContract nq = new NewContract();
        nq.localSymbol("NQU8");
        nq.secType(Types.SecType.FUT);
        nq.exchange("GLOBEX");
        nq.symbol("NQ");
        nq.currency("USD");
        nq.multiplier("20");
        return nq;
    }

    public void placeBracketOrder(int parentOrderId, Types.Action action, int quantity, double limitPrice, double takeProfitLimitPrice, double stopLossPrice){
        List<NewOrder> bracketOrder = BracketOrder(parentOrderId,action,quantity,limitPrice,takeProfitLimitPrice,stopLossPrice);
        for(NewOrder o : bracketOrder) {
            Demo.apiController.placeOrModifyOrder(initializeContract(), o,this);
        }
    }

    @Override
    public void orderState(NewOrderState orderState) {

    }

    @Override
    public void orderStatus(OrderStatus status, int filled, int remaining, double avgFillPrice, long permId, int parentId, double lastFillPrice, int clientId, String whyHeld) {

    }

    @Override
    public void handle(int errorCode, String errorMsg) {

    }

    @Override
    public void openOrder(NewContract contract, NewOrder order, NewOrderState orderState) {

    }

    @Override
    public void openOrderEnd() {

    }

    @Override
    public void orderStatus(int orderId, OrderStatus status, int filled, int remaining, double avgFillPrice, long permId, int parentId, double lastFillPrice, int clientId, String whyHeld) {

    }

    @Override
    public void handle(int orderId, int errorCode, String errorMsg) {

    }
}
