import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

//    public static void sort(JSONObject[] array, String key) {
//        Arrays.sort(array,new Comparator() {
//            int compare(JSONObject a, JSONObject b) {
//                return JsonUtils.compare(a, b, key);
//            }
//        });
//    }
//    public static void sort(List list, String key) {
//        Collections.sort(list, new Comparator() {
//            int compare(JSONObject a, JSONObject b) {
//                return JsonUtils.compare(a, b, key);
//            }
//        });
//    }
//    public static void sortDesc(JSONObject[] array, String key) {
//        Arrays.sort(array, new Comparator() {
//            @Overridepublic
//            int compare(JSONObject a, JSONObject b) {
//                return -1 * JsonUtils.compare(a, b, key);
//
//            }
//
//        });
//    }
}
