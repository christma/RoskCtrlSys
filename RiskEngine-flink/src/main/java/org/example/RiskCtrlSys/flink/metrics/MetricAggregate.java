package org.example.RiskCtrlSys.flink.metrics;


import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.example.RIskCtrlSys.modle.EventPO;
import org.example.RiskCtrlSys.flink.jobs.aggregate.acc.AccAggregate;
import org.example.RiskCtrlSys.flink.jobs.aggregate.acc.AccAggregateFactory;
import org.example.riskCtrlSys.utils.common.CommonUtil;


/**
 * AggregateFunction<IN,ACC,OUT>
 * IN: EventPO
 * ACC: Tuple3<EventPO,累加结果，事件数量>
 * OUT: Tuple2<EventPO,计算结果>
 */


public class MetricAggregate implements AggregateFunction<EventPO, Tuple3<EventPO, Double, Integer>, Tuple2<EventPO, Double>> {

    @Override
    public Tuple3<EventPO, Double, Integer> createAccumulator() {
        return Tuple3.of(null, 0.0, 0);
    }

    @Override
    public Tuple3<EventPO, Double, Integer> add(EventPO eventPO, Tuple3<EventPO, Double, Integer> acc) {

        String mainDim = acc.f0.getMetricsConfPO().getMain_dim();
        String accAggregate = acc.f0.getMetricsConfPO().getAcc_aggregate();

        String po_value_before = (String) CommonUtil.getFieldValue(eventPO, mainDim);
        String po_value_after = (String) CommonUtil.getFieldValue(acc.f0, mainDim);

        AccAggregate ag = AccAggregateFactory.getAggregate(accAggregate);
        Double res = ag.aggregate(accAggregate, po_value_before, po_value_after);


        Double sum = res + acc.f1;
        Integer eventCounts = 1 + acc.f2;
        return Tuple3.of(eventPO, sum, eventCounts);
    }

    @Override
    public Tuple2<EventPO, Double> getResult(Tuple3<EventPO, Double, Integer> acc) {

        String type = acc.f0.getMetricsConfPO().getMetric_agg_type();
        Double res = 0.0;
        if (type.equals("sum")) {
            res = acc.f1;
        } else if (type.equals("avg")) {
            res = acc.f1 / acc.f2;
        } else {
            throw new RuntimeException();
        }
        return Tuple2.of(acc.f0, res);
    }

    @Override
    public Tuple3<EventPO, Double, Integer> merge(Tuple3<EventPO, Double, Integer> eventPODoubleIntegerTuple3, Tuple3<EventPO, Double, Integer> acc1) {
        return null;
    }
}
