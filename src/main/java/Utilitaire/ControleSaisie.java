/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaire;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Dell
 */
public class ControleSaisie {

       private static Matcher matcher;

    public static boolean isString(String text) {



        if (text.matches("^[a-zA-Z]+$")) {

            return true;

        } 

            return false;

    }

     public static boolean isNull(String text){

         if("".equals(text)){

             return true; //null

         }

         return false ;//n'est pas vide

     }

          public static boolean isUsername(String text) {



        if (text.matches("^[A-Za-z0-9]+$+") ) {

            return true;

        } 

            return false;

    }

          public static boolean DateNullCS(String date){

            if("".equals(date)){

                return true ;

            }

              return false;

          }

      public static boolean adresse(String text) {



        if (text.matches("^[A-Z a-z 0-9]+$")) {

            return true;

        }

            return false;

    }

          public static boolean iscin(String text) {



        if (text.matches("^[0-9]+$")&& text.length()== 8) {

            return true;

        } else {

            return false;

        }

    }

                  public static boolean isTel(String text) {



        if (text.matches("^[0-9]+$")&& text.length()>7) {

            return true;

        } else {

            return false;

        }

    }



     private static final String EMAIL_PATTERN

            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"

            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                      private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);

                          private static final String pwd=  "^[A-Za-z0-9]+$";

                                private static Pattern pattern1 = Pattern.compile(pwd);

     public static boolean valiemail(final String hex) {

        matcher = pattern.matcher(hex);

        return matcher.matches();

    }

      public static boolean validPasswor(final String hex) {

        matcher = pattern1.matcher(hex);

        return matcher.matches();

    }

}
