/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vi.comun.util;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import java.io.IOException;

/**
 *
 * @author jerviver21
 */
public class AWSUtils {
    
    public static AmazonS3 getAmazonS3()throws IOException{
        PropertiesCredentials prop = new PropertiesCredentials(AWSUtils.class.getResourceAsStream("AwsCredentials.properties"));
        AmazonS3 s3client = new AmazonS3Client(new PropertiesCredentials(AWSUtils.class.getResourceAsStream("AwsCredentials.properties")));
        return s3client;
    }
    
}
