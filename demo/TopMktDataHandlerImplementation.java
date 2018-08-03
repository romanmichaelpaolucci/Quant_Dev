package demo;

import ib.controller.ApiController;
import ib.controller.NewTickType;
import ib.controller.Types;

import java.util.ArrayList;

public class TopMktDataHandlerImplementation implements ApiController.ITopMktDataHandler {

    ArrayList<Double> prices = new ArrayList<>();

    @Override
    public void tickPrice(NewTickType tickType, double price, int canAutoExecute) {
        //Do something with the price response
        if(tickType.equals(NewTickType.LAST)) {
            prices.add(0, price);
            System.out.println("Current Price: "+price);
            new EntrySignalA(prices);
        }
    }

    @Override
    public void tickSize(NewTickType tickType, int size) {
        //Do something with the volume response
    }

    @Override
    public void tickString(NewTickType tickType, String value) {
        //Do something with a specific tickType
    }

    @Override
    public void tickSnapshotEnd() {
        //Do something on the end of the snapshot
    }

    @Override
    public void marketDataType(Types.MktDataType marketDataType) {
        //Do something with the type of market data
    }
}
