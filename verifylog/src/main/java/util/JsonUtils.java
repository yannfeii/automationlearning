package util;

import org.json.JSONObject;


public class JsonUtils {
    public static int compare(JSONObject a, JSONObject b, String key) {
        Object va=a.get(key);
        Object vb=b.get(key);
        if (null == va && null ==vb) {
            return 0;
        }
        if (null ==va) {
            return -1;

        }
        if (null ==vb) {
            return 1;

        }
        if(va.equals(vb)) {
            return 0;

        }
        if (va instanceof Number && vb instanceof Number) {/*取double值相减，兼容整数*/
            if (a.getDouble(key) - b.getDouble(key) > 0) {
                return 1;
            }else{
                return -1;
            }
        }
        return a.getString(key).compareToIgnoreCase(b.getString(key));
    }
}
