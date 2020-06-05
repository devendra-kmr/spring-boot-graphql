package com.devendra.sample.resolver;

import com.devendra.sample.model.PdfFile;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class PdfResolver {

    @Autowired
    private HttpServletResponse response;

    @GraphQLMutation(name="getPdf")
    public PdfFile getPdf(@GraphQLArgument(name="details") PdfFile pdfFile) {
        try {
            InputStream is = new FileInputStream("/home/devendra/Wipro_T/github-git-cheat-sheet.pdf");
            IOUtils.copy(is, response.getOutputStream());
            response.setContentType("application/pdf");
            response.flushBuffer();
        }catch(IOException ex){

        }
        return pdfFile;
    }


    @GraphQLQuery(name="downloadPdf")
    public PdfFile downloadPdf(@GraphQLArgument(name="fileId") String fileId) {
        try {
            InputStream is = new FileInputStream("/home/devendra/Wipro_T/Deploying_to_OpenShift.pdf");
            IOUtils.copy(is, response.getOutputStream());
            response.setContentType("application/pdf");
            response.flushBuffer();
        }catch(IOException ex){
        }
        return  new PdfFile(fileId,"","");
    }

}
