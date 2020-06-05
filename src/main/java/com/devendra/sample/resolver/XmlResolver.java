package com.devendra.sample.resolver;

import com.devendra.sample.model.XmlFile;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Component
public class XmlResolver {

    @Autowired
    private HttpServletResponse response;

    @GraphQLMutation(name="getXml")
    public XmlFile getXml(@GraphQLArgument(name="details") XmlFile xmlFile) throws FileNotFoundException {
        try {
            InputStream is = new FileInputStream("/home/devendra/Wipro_T/pom.xml");
            IOUtils.copy(is, response.getOutputStream());
            response.setContentType("application/xml");
            response.flushBuffer();
        }catch(IOException ex){

        }
        return xmlFile;
    }
}
