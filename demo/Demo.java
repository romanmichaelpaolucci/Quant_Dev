package demo;

import ib.controller.ApiController;
import ib.controller.NewContract;
import ib.controller.Types;

public class Demo {

    //We will need an instance of our connection handler implementation
    static LoggerImplementation inLogger = new LoggerImplementation();
    static LoggerImplementation outLogger = new LoggerImplementation();
    static ConnectionHandlerImplementation connectionHandler = new ConnectionHandlerImplementation();
    static TopMktDataHandlerImplementation topMktDataHandlerImplementation = new TopMktDataHandlerImplementation();
    static ApiController apiController = new ApiController(connectionHandler, inLogger, outLogger);
    static OrderHandler orderHandler = new OrderHandler();

    public static void main(String _[]){
        apiController.connect("localhost", 7497, 0);
        apiController.reqTopMktData(initializeContract(), "", false, topMktDataHandlerImplementation);
        orderHandler.placeBracketOrder(200, Types.Action.BUY, 1, 1, 1, .5);
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

}
