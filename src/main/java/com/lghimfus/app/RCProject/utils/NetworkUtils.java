package com.lghimfus.app.RCProject.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

/**
 * 
 * This class handles requests over the network.
 * 
 * @author lghimfus
 *
 */
public class NetworkUtils {
	/**
   * Returns the JSON result from the HTTP response.
   *
   * @param url the URL to fetch the HTTP response from.
   * @return a JSON object from the HTTP response.
   * @throws IOException related to network and stream reading.
   */
  public static JSONObject getJsonResponseFromHttpUrl(URL url) throws IOException {
    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
    
    try {
      InputStream in = urlConnection.getInputStream();

      Scanner scanner = new Scanner(in);
      scanner.useDelimiter("\\A");

      boolean hasInput = scanner.hasNext();
      if (hasInput) {
          return new JSONObject(scanner.next());
      } else {
          return null;
      }
    } finally {
      urlConnection.disconnect();
    }
  }
    
}
