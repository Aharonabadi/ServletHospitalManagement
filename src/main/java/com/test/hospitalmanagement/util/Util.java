package com.test.hospitalmanagement.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Util {
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public static void isValidEmailAddress(String email, HttpServletResponse response) throws IOException {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("Bad email " + email);
        }
    }

    public static HashMap<String,String> getHashMapFromJsonRequest(HttpServletRequest request) throws IOException {

        StringBuffer stringBuffer = new StringBuffer();
        String line;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                stringBuffer.append(line);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<String,String> result = new ObjectMapper().readValue(stringBuffer.toString(), HashMap.class);

        return result;
    }

    public static Map<String, String> decodeQueryString(String query) {
        try {
            Map<String, String> params = new LinkedHashMap<>();
            for (String param : query.split("&")) {
                String[] keyValue = param.split("=", 2);
                String key = URLDecoder.decode(keyValue[0], "UTF-8");
                String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], "UTF-8") : "";
                if (!key.isEmpty()) {
                    params.put(key, value);
                }
            }
            return params;
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e); // Cannot happen with UTF-8 encoding.
        }
    }

    public static String sanitizeUntrustedHTML(String untrustedHTML) {

        PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
        return policy.sanitize(untrustedHTML);
    }

    public static void isValidEmailAddressThrowExecption(String email) throws ServletException {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            throw new ServletException(email);
        }
    }
}
