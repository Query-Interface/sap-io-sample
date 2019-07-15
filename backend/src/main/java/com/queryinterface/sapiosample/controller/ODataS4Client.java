package com.queryinterface.sapiosample.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.retrieve.EdmMetadataRequest;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntitySetRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.commons.api.edm.Edm;

import com.queryinterface.sapiosample.Product;

public class ODataS4Client {

	private ODataClient client = null;
	private Edm metadata = null;
	private static String apiEndpoint = "https://sandbox.api.sap.com/s4hanacloud/sap/opu/odata/sap/API_PRODUCT_SRV";
	private static String apiKey = "xxxxxx";  // login to the previous URI to obtain the API Key

	
	public ODataS4Client() {
		client = ODataClientFactory.getClient();
	}
	
	private Edm loadEdm() {
		EdmMetadataRequest request = this.client.getRetrieveRequestFactory().getMetadataRequest(apiEndpoint);
		request.addCustomHeader("apikey", apiKey);
		request.addCustomHeader("Accept", "application/xml");
		ODataRetrieveResponse<Edm> response = request.execute();
		return response.getBody();		
	}
	
	private void ensureEdmlIsLoaded() {
		if (this.metadata == null) {
			loadEdm();
		}
	}
	
	public List<Product> getProducts() {
		//ensureEdmlIsLoaded();
		List<Product> products = new ArrayList<>();
		try {
			ODataEntitySetRequest<ClientEntitySet> request = this.client.getRetrieveRequestFactory().getEntitySetRequest(new URI(apiEndpoint + "/A_Product?$top=25"));
			request.addCustomHeader("apikey", apiKey);
			request.addCustomHeader("Accept", "application/xml");
			ODataRetrieveResponse<ClientEntitySet> response = request.execute();
			List<ClientEntity> entities = response.getBody().getEntities();
			products = entities.stream().map( e -> {
				Product product = new Product();
				product.setId(e.getProperty("Product").getValue().toString());
				product.setProductGroup(e.getProperty("ProductGroup").getValue().toString());
				product.setProductType(e.getProperty("ProductType").getValue().toString());
				product.setEAN(e.getProperty("ProductStandardID").getValue().toString());
				
				return product;
			}).collect(Collectors.toList());;
			
		} catch (URISyntaxException e) {
			// TODO handle error correctly
			e.printStackTrace();
		}
		
		return products;
	}
}
