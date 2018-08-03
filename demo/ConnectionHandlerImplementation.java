package demo;

import ib.controller.ApiController;

import java.util.ArrayList;

public class ConnectionHandlerImplementation implements ApiController.IConnectionHandler {
    @Override
    public void connected() {
        //Do something when connected
        System.out.println("Connected");
    }

    @Override
    public void disconnected() {
        //Do something when disconnected
    }

    @Override
    public void accountList(ArrayList<String> list) {
        //Do something with the account list
    }

    @Override
    public void error(Exception e) {
        //Do something on error
    }

    @Override
    public void message(int id, int errorCode, String errorMsg) {
        //Do something with server messages
    }

    @Override
    public void show(String string) {
        //Do something with paramter
    }
}
