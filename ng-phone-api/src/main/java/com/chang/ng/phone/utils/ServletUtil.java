package com.chang.ng.phone.utils;

import java.io.IOException;

import javax.servlet.http.Part;

public class ServletUtil {
	 // Content-Disposition: form-data; name="file"; filename="unicorn.jpg"
    public static String getFileName(Part part) throws IOException {
        String contentDisp = part.getHeader("content-disposition");
        //response.getWriter().println(contentDisp);
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {	// filename="unicorn.jpg"
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
