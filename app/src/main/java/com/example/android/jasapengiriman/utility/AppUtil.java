package com.example.android.jasapengiriman.utility;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.widget.EditText;
import android.widget.Toast;


import com.example.android.jasapengiriman.modelservice.Jasa;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


/**
 * Created by Mounzer on 8/16/2017.
 */

public class AppUtil {

    /**
     * to check if the form is not empty, otherwise
     * return false.
     */
    public static boolean isEmpty(EditText editText) {
        return editText.getText() == null
                || editText.getText().toString().isEmpty();

    }

    public static boolean isValidPassword(String pass) {
        if (pass.length() >= 6) {
            return true;
        }
        return false;
    }

    /**
     * @param context the application context
     * @return true or false
     * @brief methods for identifying the device is supported for calling feature or not
     */
    public static boolean isDeviceCallSupported(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm.getPhoneType() == TelephonyManager.PHONE_TYPE_NONE) {
            Toast.makeText(context, "No Phone Available",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }


    /**
     * @param context the application context
     * @param number  the specified phone number
     * @brief methods for doing a phone call with specified phone number
     */
    public static void phoneCall(Context context, String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
        context.startActivity(intent);
    }

    public static String formatCurrency(String s){
        return insertStringRev(s, ",", 3);
    }
    public static String formatCurrency(int s){
        return insertStringRev(s+"", ",", 3);
    }
    public static String insertStringRev(String original, String sInsert, int igroup) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < original.length(); i++) {

            if (((original.length()-i)%igroup)==0 && igroup!=0 && i!=0) {
                sb.append(sInsert+original.substring(i, i+1));
            }else {
                sb.append(original.substring(i, i+1));
            }
        }
        return sb.toString();
    }
    public static int getIntCut(String s){
        StringBuffer sbBuffer = new StringBuffer("");
        for (int i = 0; i < s.length(); i++) {
            if ("1234567890".indexOf(s.substring(i,i+1))>=0) {
                sbBuffer.append(s.substring(i,i+1));
            }else{
                break;
            }
        }
        return getInt(sbBuffer.toString());
    }
    public static int getInt(String s){
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }
    public static String Now(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sdf.format(calendar.getTime());
    }

    public final static Map<String, Object> pojo2Map(Object obj) {
        Map<String, Object> hashMap = new HashMap<String, Object>();
        try {
            Class<? extends Object> c = obj.getClass();
            Method m[] = c.getMethods();
            for (int i = 0; i < m.length; i++) {
                if (m[i].getName().indexOf("get") == 0) {
                    if (m[i].getName().equalsIgnoreCase("getDendasisa")){
                        String x="";
                    }
                    String name = m[i].getName().toLowerCase().substring(3, 4) + m[i].getName().substring(4);
                    hashMap.put(name, m[i].invoke(obj, new Object[0]));
                }
            }
        } catch (Throwable e) {
            //log error
        }
        return hashMap;
    }
    public static Vector<String> spliter(String original, String separator) {
        Vector<String> nodes = new Vector<String>();
        int index = original.indexOf(separator);
        while(index >= 0) {
            nodes.addElement( original.substring(0, index) );
            original = original.substring(index+separator.length());
            index = original.indexOf(separator);
        }
        nodes.addElement( original );
        return nodes;
    }

    public static String getSetting(Context context, String key, String def){//baca data yang disimpan(string)
        SharedPreferences settings = context.getApplicationContext().getSharedPreferences("rkrzmail.cctv", 0);
        String silent = settings.getString(key, def);
        return silent;
    }
    public static void setSetting(Context context, String key, String val){//Simpat data string
        SharedPreferences settings = context.getApplicationContext().getSharedPreferences("rkrzmail.cctv", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, val);
        editor.commit();
    }
    public static int getNumber(String s){
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if ("01234567890".indexOf(s.charAt(i))!=-1) {
                buf.append(s.charAt(i));
            }
        }
        try {
            return Integer.parseInt(buf.toString());
        } catch (Exception e) { }
        return 0;
    }


//    public static Market getMarketModel() {
//        return MarketModel;
//    }
//
//    public static void setMarketModel(Market marketModel) {
//        MarketModel = marketModel;
//    }
//
//    public static Market MarketModel;
//
//    public static Biodata biodataModel;
//
//    public static Biodata getBiodataModel() {
//        return biodataModel;
//    }
//
//    public static void setBiodataModel(Biodata biodataModel) {
//        AppUtil.biodataModel = biodataModel;
//    }

    private static Jasa jasaModel;

    public static Jasa getJasaModel() {
        return jasaModel;
    }

    public static void setJasaModel(Jasa jasaModel) {
        AppUtil.jasaModel = jasaModel;
    }
}
