package org.example.RiskCtrlSys.flink.jobs.aggregate.acc;

import org.example.RiskCtrlSys.flink.utils.ParameterConstantsUtils;

import java.util.Locale;

public class AccAggregateFactory {

    public static AccAggregate getAggregate(String acc_aggregate) {

        String _package_path = ParameterConstantsUtils.PACKAGE_AGGREGATE_ACC;
        String package_path = _package_path +
                ".Acc" +
                acc_aggregate.substring(0, 1).toUpperCase() +
                acc_aggregate.substring(1).toLowerCase();
        try {
            return (AccAggregate) Class.forName(package_path).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
