package com.example.service;

import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.example.model.Vehicle;

@Service
public class QueryDslService {

	@Autowired
	private ElasticsearchTemplate template;

	public List<Vehicle> serchMultipuleFileds(int id, String name) {
		QueryBuilder quary = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("vid", id))
				.must(QueryBuilders.matchQuery("vname", name));
		NativeSearchQuery searchquary = new NativeSearchQueryBuilder().withQuery(quary).build();
		List<Vehicle> vehicle = template.queryForList(searchquary, Vehicle.class);
		return vehicle;
	}

	public List<Vehicle> getVehicleNameDetails(String name) {
		String name1 = ".*" + name + ".*";
		SearchQuery serch = new NativeSearchQueryBuilder().withFilter(QueryBuilders.regexpQuery("vname", name1))
				.build();
		List<Vehicle> list = template.queryForList(serch, Vehicle.class);
		return list;
	}

	public List<Vehicle> getAnyFiled(String text) {
		SearchQuery search = new NativeSearchQueryBuilder().withQuery(QueryBuilders.multiMatchQuery(text).field("vname")
				.field("type").type(MultiMatchQueryBuilder.Type.BEST_FIELDS)).build();
		List<Vehicle> list = template.queryForList(search, Vehicle.class);
		return list;
	}

	public List<Vehicle> getRanges(double sal1, double sal2) {
		SearchQuery search=new NativeSearchQueryBuilder().withQuery(QueryBuilders.rangeQuery("price").gte(sal1).lte(sal2)).build();
		List<Vehicle> list=template.queryForList(search, Vehicle.class);
		return list;
	}

	public double getMaxPrice() {
		SearchResponse sr = template.getClient().prepareSearch()
				.setQuery(QueryBuilders.matchAllQuery())
			    .addAggregation(
			            AggregationBuilders.max("price").field("price")
			    )
		        .execute().actionGet();
		Max agg = sr.getAggregations().get("price");
		double value = agg.getValue();
		System.out.println("AGE:"+value);

		return value;
	}

}
