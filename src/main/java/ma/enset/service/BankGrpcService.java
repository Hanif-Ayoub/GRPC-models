package ma.enset.service;

import io.grpc.stub.StreamObserver;
import ma.enset.stubs.Bank;
import ma.enset.stubs.BankServiceGrpc;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class BankGrpcService extends BankServiceGrpc.BankServiceImplBase {
    @Override
    public void convert(Bank.ConvertCurrencyRequest request, StreamObserver<Bank.ConvertCurrencyResponse> responseObserver) {
        String currencyFrom=request.getCurrencyFrom();
        String currencyTo=request.getCurrencyTo();
        double amount= request.getAmount();
        Bank.ConvertCurrencyResponse response=Bank.ConvertCurrencyResponse.newBuilder()
                .setCurrencyFrom(currencyFrom)
                .setCurrencyTo(currencyTo)
                .setAmount(amount)
                .setResult(amount*11)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getCurrencyStream(Bank.ConvertCurrencyRequest request, StreamObserver<Bank.ConvertCurrencyResponse> responseObserver) {
        String currencyFrom = request.getCurrencyFrom();
        String currencyTo = request.getCurrencyTo();
        double amount = request.getAmount();
        Scanner myObj = new Scanner(System.in);
        double message;
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            int counter=0;
            @Override
            public void run() {
                Bank.ConvertCurrencyResponse response=Bank.ConvertCurrencyResponse.newBuilder()
                        .setCurrencyFrom(currencyFrom)
                        .setCurrencyTo(currencyTo)
                        .setAmount(amount)
                        .setResult(amount*Math.random()*100)
                        .build();
                responseObserver.onNext(response);
                ++counter;
                if(counter==10){
                    responseObserver.onCompleted();
                    timer.cancel();
                }
            }
        }, 1000, 1000);

    }
    @Override
    public StreamObserver<Bank.ConvertCurrencyRequest> performStream(StreamObserver<Bank.ConvertCurrencyResponse> responseObserver) {
        return new StreamObserver<Bank.ConvertCurrencyRequest>() {
            double sum=0;
            @Override
            public void onNext(Bank.ConvertCurrencyRequest convertCurrencyRequest) {
                sum+=convertCurrencyRequest.getAmount();
            }
            @Override
            public void onError(Throwable throwable) {
            }
            @Override
            public void onCompleted() {
                Bank.ConvertCurrencyResponse response=Bank.ConvertCurrencyResponse.newBuilder()
                        .setResult(sum*10)
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }
    @Override
    public StreamObserver<Bank.ConvertCurrencyRequest> fullCurrencyStream(StreamObserver<Bank.ConvertCurrencyResponse> responseObserver) {
        return new StreamObserver<Bank.ConvertCurrencyRequest>() {
            @Override
            public void onNext(Bank.ConvertCurrencyRequest convertCurrencyRequest) {
                Bank.ConvertCurrencyResponse response=Bank.ConvertCurrencyResponse.newBuilder()
                        .setResult(convertCurrencyRequest.getAmount()*Math.random()*33)
                        .build();
                responseObserver.onNext(response);
            }
            @Override
            public void onError(Throwable throwable) {
            }
            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
