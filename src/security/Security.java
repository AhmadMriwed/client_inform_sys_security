package security;

import javax.crypto.Mac;
import java.security.Key;
import java.util.ArrayList;

public class Security {
    public static String MAC(Key key,Object data) throws Exception {

        Mac mac = Mac.getInstance("HMACSHA1");
        mac.init(key);
        mac.update(data.toString().getBytes());
        byte[] macResult = mac.doFinal();

           System.out.println(new String(macResult));
        return new String(macResult);
    }
}
