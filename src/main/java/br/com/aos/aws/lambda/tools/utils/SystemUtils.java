package br.com.aos.aws.lambda.tools.utils;

import java.net.URL;

import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

/**
 * @author Wagner Alves
 */
public class SystemUtils {

    private SystemUtils() {

    }

    public static String readFile(String fileName) {
        URL url;
        try {
            url = ClassLoader.getSystemResource(fileName);
            return Resources.toString(url, Charsets.UTF_8);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Não foi possivel ler o arquivo: " + fileName);
        }
    }

}
