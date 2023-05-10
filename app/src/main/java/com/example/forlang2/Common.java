package com.example.forlang2;

import android.widget.TextView;

import com.example.forlang2.Model.User;

import java.util.Calendar;

public class Common {
    public static final String RIDER_INFO_REFERENCE = "UserInfo";

    public static User currentUser;

    public static String buildWelcomeMessage() {
        if(currentUser != null) {
            return new StringBuilder("Здравствуйте ")
                    .append(Common.currentUser.getName()).toString();
        }else {
            return "";
        }
    }

    public static String buildName(String name) {
        return new StringBuilder(name).toString();
    }

    public static void setWelcomeMessage(TextView txt_welcome) {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour >= 1 && hour <= 12) {
            txt_welcome.setText(new StringBuilder("Доброе утро"));
        } else if (hour >= 13 && hour <= 17) {
            txt_welcome.setText(new StringBuilder("Добрый день"));
        } else {
            txt_welcome.setText(new StringBuilder("Добрый вечер"));
        }
    }
}
