package ConnectSQL;
//判断字符串是否为空
public class StringUtil {
    public static boolean isEmpty(String str){
        if(str==null||"".equals(str.trim())){
            return true;
        }else {
            return false;
        }
    }
    public static boolean isNotEmpty(String str){
        if(str!=null&&!"".equals(str.trim())){
            return true;
        }else {
            return false;
        }
    }
}
