package com.ait.apex.aggregator;

import com.ait.apex.row.DataType;
import com.ait.apex.row.RowMeta;

import java.util.ArrayList;
import java.util.List;

public class AggregationHelper {
	
	public List<AggregationSchema> createAggregationSchema(RowMeta originalSchema, List<AggregationMetrics> aggregationMetrics) throws NoSuchFieldException, IllegalAccessException {
		
		List<AggregationSchema> schemaList = new ArrayList<>();
		
		for (AggregationMetrics aggregationMeta : aggregationMetrics) {
			
			AggregationSchema schema = new AggregationSchema();

			schema.keySchema = originalSchema.subset(aggregationMeta.getKeys());
			switch (aggregationMeta.getAggTypes()) {
				case COUNT:
					schema.valueSchema.addField("count", DataType.LONG);
					break;
				
				case SUM:
					schema.valueSchema.addField("sum", DataType.LONG);
					break;
				
				case MIN:
					schema.valueSchema.addField("min", DataType.LONG);
					break;
				
				case MAX:
					schema.valueSchema.addField("max", DataType.LONG);
					break;
			}
			schemaList.add(schema);
		}
		return schemaList;
	}
}
