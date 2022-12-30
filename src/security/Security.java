package security;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.crypto.Mac;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;

public class Security {
    public static String MAC(Key key,Object data) throws Exception {

        Mac mac = Mac.getInstance("HMACSHA1");
        mac.init(key);
        mac.update(data.toString().getBytes());
        byte[] macResult = mac.doFinal();

          // System.out.println(new String(macResult));
        return new String(macResult);
    }
    public static String getKeyClient(int number,String password){
        try {
            String keyClient = Symmetric.generateStorngPasswordHash(password);
          Json.put(number,keyClient);

            return keyClient;
        }catch (  Exception e){

        }
      return null;

    }

}
