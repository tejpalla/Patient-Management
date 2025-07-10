package com.pm.billingservice.grpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class BillingGrpcService extends 
                BillingServiceImplBase{
        private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);
    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest,
            StreamObserver<BillingResponse> responseObserver){
        //stream observer is used earn multiple responses to the client, back and forth send data 
        //live dashboards
        
        log.info("createBillingAccount request received {}", billingRequest.toString());
        

        //Business Login - e.g save to database, perform calculations etc

        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("12345")
                .setStatus("ACTIVE")
                .build();

        responseObserver.onNext(response);
        //can write multiple responses
        responseObserver.onCompleted();
    }
}
