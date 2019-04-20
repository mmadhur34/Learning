package com.home.madhur.riskbook;


public class CentralisedRiskBook extends com.home.madhur.riskbook.CentralRiskBookGrpc.CentralRiskBookImplBase {
//    @Override
    public void submitOrder(Madhur.Position position, Madhur.Order order){

        order = Madhur.Order.newBuilder()
                .setOrderDir(position.getPosDir())
//                .setOrderPrice(mdProvider.getMaretData(position.getRic()))
                .setOrderQty(position.getQty())
                .setOrderTyp(Madhur.OrderTyp.LIMIT)
                .setRic(position.getRic())
                .build();
    }
//    @Override
//    enableBook()
}
