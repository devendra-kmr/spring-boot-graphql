package com.devendra.sample.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.devendra.sample.resolver.XmlResolver;
import com.devendra.sample.resolver.PdfResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLException;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.query.PublicResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;

@CrossOrigin
@RestController
public class GraphqlController {


	private final GraphQL graphQL;

	@Autowired
	public GraphqlController(PdfResolver pdfResolver, XmlResolver xmlResolver) {
		GraphQLSchema schema = new GraphQLSchemaGenerator().withResolverBuilders(
				// Resolve by annotations
				new AnnotatedResolverBuilder(),
				// Resolve public methods inside root package
				new PublicResolverBuilder("com.devendra.*"))
				.withOperationsFromSingleton(pdfResolver, PdfResolver.class)
				.withOperationsFromSingleton(xmlResolver, XmlResolver.class)
				.withValueMapperFactory(new JacksonValueMapperFactory()).generate();
		graphQL = GraphQL.newGraphQL(schema).build();
	}
	
	@PostMapping(value = "/graphql")
	public Map<String,Object> execute(@RequestBody Map<String, String> request, HttpServletRequest raw)
			throws GraphQLException {
		ExecutionResult result = graphQL.execute(request.get("query"));
		Map<String,Object> map =(Map<String,Object>) result.getData();
		if(map.containsKey("getPdf") || map.containsKey("getXml"))
			return null;
		else
			return map;
	
	}

}
