package utils;

public class DataTypeCheckerUtil {
    public static Boolean isBoolean(String value)
    {
        if(value.isEmpty())
            return false;
        return "true".equals(value) || "false".equals(value);
    }
    public static Boolean isDouble(String value)
    {
        try {
            Double.parseDouble(value);
        }
        catch (NumberFormatException e){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    public static Boolean isInteger(String value)
    {
        {
            try {
                Integer.parseInt(value);
            }
            catch (NumberFormatException e){
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }
    }
    public static String validateType(String value)
    {
        if(isBoolean(value))
            return "Boolean";
        else if (isDouble(value)) {
            return "Double";
        } else if (isInteger(value)) {
            return "Integer";
        }
        return "String";
    }
}
