/*******************************************************************************
 * Copyright (c) 2016 EclipseSource.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the \"Software\"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package com.eclipsesource.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ParseJson7 {

  public static void main(String[] args) throws IOException, JSONException {
    // TODO Auto-generated method stub

    String strLine;
    String json = null;
    // Read File Line By Line
    String campaign_id = null;
    String campaign_name = null;
    String impressions;
    String clicks;
    String reach;
    String conversions;
    String date_start;
    String region;
    String date;
    String spend;
    String location;
    String content1;
    String content2;
    String impression_device;
    String age;
    String gender;
    String page_like = null;
    String post_like = null;
    String page_engagement = null;
    String post_engagement = null;
    String link_click;
    String keyname;
    String ctr;
    String unique_ctr;
    String unique_clicks;
    String frequency;



    BufferedReader br = null;

    String sCurrentLine;

    br =
         new BufferedReader(
                            new FileReader(
                                           "C:\\Users\\Sony\\Desktop\\Reports\\facebook_geo_report2.csv"));

    File file = new File("C:\\Users\\Sony\\Desktop\\facebook_geo_report1.csv");

    String content =
                     "date,campaignId,campaignName,impressions,clicks,conversions,reach,pagelike,postlike,pageengagement,postengagement,spend,region"
                         + "\n";

    // if file doesnt exists, then create it
    if (!file.exists()) {
      file.createNewFile();
    }

    FileWriter fw = new FileWriter(file.getAbsoluteFile());
    BufferedWriter bw = new BufferedWriter(fw);
    bw.write(content);

    JSONObject o = null;
    int j = 0;

    while ((strLine = br.readLine()) != null) {
      // Print the content on the console
      // System.out.println (strLine);

      if (strLine != null && strLine.isEmpty() == false && strLine.equals("null") == false) {
        json = strLine;
        if (json.contains("data") == false) {
          json = "{data:" + json + "}";
          o = new JSONObject(json);

        } else {

          o = new JSONObject(json);

        }

        j++;

        JSONArray arrayOfTests = null;
        JSONArray arrayofTests1 = null;

        if (o.has("data") && o.get("data") != null) {
          try {

            arrayOfTests = (JSONArray)o.get("data");
          } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }

          for (int i = 0; i < arrayOfTests.length(); i++) {

            JSONObject jsonObject = null;
            JSONObject jsonObject1 = null;

            try {
              jsonObject = new JSONObject(arrayOfTests.get(i).toString());
              impressions = jsonObject.getString("impressions");
              campaign_id = jsonObject.getString("campaign_id");
              campaign_name = jsonObject.getString("campaign_name");
              clicks = jsonObject.getString("clicks");
              conversions = jsonObject.getString("total_actions");
              reach = jsonObject.getString("reach");
              spend = jsonObject.getString("spend");
              date = jsonObject.getString("date_start");
              region = jsonObject.getString("region");
              // System.out.println(jsonObject.getString("impressions"));
              if (jsonObject.has("actions")) {
                arrayofTests1 = (JSONArray)jsonObject.get("actions");
                for (int k = 0; k < arrayofTests1.length(); k++) {
                  jsonObject1 = new JSONObject(arrayofTests1.get(k).toString());
                  keyname = jsonObject1.getString("action_type");

                  if (keyname.equals("post_like")) {
                    post_like = jsonObject1.getString("value");
                  }

                  if (keyname.equals("like")) {
                    page_like = jsonObject1.getString("value");
                  }

                  if (keyname.equals("page_engagement")) {
                    page_engagement = jsonObject1.getString("value");
                  }

                  if (keyname.equals("post_engagement")) {
                    post_engagement = jsonObject1.getString("value");
                  }

                }
              }
              content1 =
                         date + "," + campaign_id + "," + campaign_name + "," + impressions + ","
                             + clicks + "," + conversions + "," + reach + "," + page_like + ","
                             + post_like + "," + page_engagement + "," + post_engagement + ","
                             + spend + "," + region + "\n";
              System.out.println(content1);
              bw.write(content1);

            } catch (JSONException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }

          }

        }

      }

    }

    bw.close();

    br =
         new BufferedReader(
                            new FileReader(
                                           "C:\\Users\\Sony\\Desktop\\Reports\\facebook_device_report2.csv"));

    file = new File("C:\\Users\\Sony\\Desktop\\facebook_device_report1.csv");

    content =
              "date,campaignId,campaignName,impressions,clicks,conversions,reach,pagelike,postlike,pageengagement,postengagement,spend,device"
                  + "\n";

    // if file doesnt exists, then create it
    if (!file.exists()) {
      file.createNewFile();
    }

    fw = new FileWriter(file.getAbsoluteFile());
    BufferedWriter bw1 = new BufferedWriter(fw);
    bw1.write(content);

    j = 0;

    while ((strLine = br.readLine()) != null) {
      // Print the content on the console
      // System.out.println (strLine);
      if (strLine != null && strLine.isEmpty() == false && strLine.equals("null") == false) {
        json = strLine;
        if (json.contains("data") == false) {
          json = "{data:" + json + "}";
          o = new JSONObject(json);

        } else {

          o = new JSONObject(json);

        }

        j++;

        JSONArray arrayOfTests = null;
        JSONArray arrayofTests1 = null;

        if (o.has("data") && o.get("data") != null) {
          try {

            arrayOfTests = (JSONArray)o.get("data");
          } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }

          for (int i = 0; i < arrayOfTests.length(); i++) {

            JSONObject jsonObject = null;
            JSONObject jsonObject1 = null;

            try {
              jsonObject = new JSONObject(arrayOfTests.get(i).toString());
              impressions = jsonObject.getString("impressions");
              campaign_id = jsonObject.getString("campaign_id");
              campaign_name = jsonObject.getString("campaign_name");
              clicks = jsonObject.getString("clicks");
              conversions = jsonObject.getString("total_actions");
              reach = jsonObject.getString("reach");
              spend = jsonObject.getString("spend");
              date = jsonObject.getString("date_start");
              impression_device = jsonObject.getString("impression_device");
              // System.out.println(jsonObject.getString("impressions"));
              if (jsonObject.has("actions")) {
                arrayofTests1 = (JSONArray)jsonObject.get("actions");
                for (int k = 0; k < arrayofTests1.length(); k++) {
                  jsonObject1 = new JSONObject(arrayofTests1.get(k).toString());
                  keyname = jsonObject1.getString("action_type");

                  if (keyname.equals("post_like")) {
                    post_like = jsonObject1.getString("value");
                  }

                  if (keyname.equals("like")) {
                    page_like = jsonObject1.getString("value");
                  }

                  if (keyname.equals("page_engagement")) {
                    page_engagement = jsonObject1.getString("value");
                  }

                  if (keyname.equals("post_engagement")) {
                    post_engagement = jsonObject1.getString("value");
                  }



                }

              }
                content1 =
                           date + "," + campaign_id + "," + campaign_name + "," + impressions + ","
                               + clicks + "," + conversions + "," + reach + "," + page_like + ","
                               + post_like + "," + page_engagement + "," + post_engagement + ","
                               + spend + "," + impression_device + "\n";

                System.out.println(content1);

                bw1.write(content1);

            }

             catch (JSONException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }

          }

        }

      }
    }
      bw1.close();

      br =
           new BufferedReader(
                              new FileReader(
                                             "C:\\Users\\Sony\\Desktop\\Reports\\facebook_demographics_report2.csv"));

      file = new File("C:\\Users\\Sony\\Desktop\\facebook_demographics_report1.csv");

      content =
                "date,campaignId,campaignName,impressions,clicks,conversions,reach,pagelike,postlike,pageengagement,postengagement,spend,age,gender"
                    + "\n";

      // if file doesnt exists, then create it
      if (!file.exists()) {
        file.createNewFile();
      }

      fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw2 = new BufferedWriter(fw);
      bw2.write(content);

      j = 0;

      while ((strLine = br.readLine()) != null) {
        // Print the content on the console
        // System.out.println (strLine);
        if (strLine != null && strLine.isEmpty() == false && strLine.equals("null") == false) {
          json = strLine;
          if (json.contains("data") == false) {
            json = "{data:" + json + "}";
            o = new JSONObject(json);

          } else {

            o = new JSONObject(json);

          }

          j++;

          JSONArray arrayOfTests = null;
          JSONArray arrayofTests1 = null;

          if (o.has("data") && o.get("data") != null) {
            try {

              arrayOfTests = (JSONArray)o.get("data");
            } catch (JSONException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }

            for (int i = 0; i < arrayOfTests.length(); i++) {

              JSONObject jsonObject = null;
              JSONObject jsonObject1 = null;

              try {
                jsonObject = new JSONObject(arrayOfTests.get(i).toString());
                campaign_id = jsonObject.getString("campaign_id");
                campaign_name = jsonObject.getString("campaign_name");
                impressions = jsonObject.getString("impressions");
                clicks = jsonObject.getString("clicks");
                conversions = jsonObject.getString("total_actions");
                reach = jsonObject.getString("reach");
                spend = jsonObject.getString("spend");
                date = jsonObject.getString("date_start");
                age = jsonObject.getString("age");
                gender = jsonObject.getString("gender");
                // System.out.println(jsonObject.getString("impressions"));
                if (jsonObject.has("actions")) {
                  arrayofTests1 = (JSONArray)jsonObject.get("actions");
                  for (int k = 0; k < arrayofTests1.length(); k++) {
                    jsonObject1 = new JSONObject(arrayofTests1.get(k).toString());
                    keyname = jsonObject1.getString("action_type");

                    if (keyname.equals("post_like")) {
                      post_like = jsonObject1.getString("value");
                    }

                    if (keyname.equals("like")) {
                      page_like = jsonObject1.getString("value");
                    }

                    if (keyname.equals("page_engagement")) {
                      page_engagement = jsonObject1.getString("value");
                    }

                    if (keyname.equals("post_engagement")) {
                      post_engagement = jsonObject1.getString("value");
                    }

                    if (keyname.equals("post_like")) {
                      post_like = jsonObject1.getString("value");
                    }

                  }

                }
                  content1 =
                             date + "," + campaign_id + "," + campaign_name + "," + impressions
                                 + "," + clicks + "," + conversions + "," + reach + "," + page_like
                                 + "," + post_like + "," + page_engagement + "," + post_engagement
                                 + "," + spend + "," + age + "," + gender + "\n";
                  System.out.println(content1);

                  bw2.write(content1);


              } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }

            }

          }

        }

      }
        bw2.close();



        br =
            new BufferedReader(
                               new FileReader(
                                              "C:\\Users\\Sony\\Desktop\\Reports\\facebook_general_report2.csv"));

       file = new File("C:\\Users\\Sony\\Desktop\\facebook_general_report1.csv");

       content =
                 "date,campaignId,campaignName,impressions,clicks,conversions,reach,pagelike,postlike,pageengagement,postengagement,spend,ctr,unique_ctr,unique_clicks,frequency"
                     + "\n";

       // if file doesnt exists, then create it
       if (!file.exists()) {
         file.createNewFile();
       }

       fw = new FileWriter(file.getAbsoluteFile());
       BufferedWriter bw3 = new BufferedWriter(fw);
       bw3.write(content);

       j = 0;

       while ((strLine = br.readLine()) != null) {
         // Print the content on the console
         // System.out.println (strLine);
         if (strLine != null && strLine.isEmpty() == false && strLine.equals("null") == false) {
           json = strLine;
           if (json.contains("data") == false) {
             json = "{data:" + json + "}";
             o = new JSONObject(json);

           } else {

             o = new JSONObject(json);

           }

           j++;

           JSONArray arrayOfTests = null;
           JSONArray arrayofTests1 = null;

           if (o.has("data") && o.get("data") != null) {
             try {

               arrayOfTests = (JSONArray)o.get("data");
             } catch (JSONException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
             }

             for (int i = 0; i < arrayOfTests.length(); i++) {

               JSONObject jsonObject = null;
               JSONObject jsonObject1 = null;

               try {
                 jsonObject = new JSONObject(arrayOfTests.get(i).toString());
                 campaign_id = jsonObject.getString("campaign_id");
                 campaign_name = jsonObject.getString("campaign_name");
                 impressions = jsonObject.getString("impressions");
                 clicks = jsonObject.getString("clicks");
                 conversions = jsonObject.getString("total_actions");
                 reach = jsonObject.getString("reach");
                 spend = jsonObject.getString("spend");
                 ctr =jsonObject.getString("ctr");
                 unique_ctr = jsonObject.getString("unique_ctr");
                 unique_clicks = jsonObject.getString("unique_clicks");
                 frequency = jsonObject.getString("frequency");

                 date = jsonObject.getString("date_start");

                 // System.out.println(jsonObject.getString("impressions"));
                 if (jsonObject.has("actions")) {
                   arrayofTests1 = (JSONArray)jsonObject.get("actions");
                   for (int k = 0; k < arrayofTests1.length(); k++) {
                     jsonObject1 = new JSONObject(arrayofTests1.get(k).toString());
                     keyname = jsonObject1.getString("action_type");

                     if (keyname.equals("post_like")) {
                       post_like = jsonObject1.getString("value");
                     }

                     if (keyname.equals("like")) {
                       page_like = jsonObject1.getString("value");
                     }

                     if (keyname.equals("page_engagement")) {
                       page_engagement = jsonObject1.getString("value");
                     }

                     if (keyname.equals("post_engagement")) {
                       post_engagement = jsonObject1.getString("value");
                     }


                   }

                 }
                   content1 =
                              date + "," + campaign_id + "," + campaign_name + "," + impressions
                                  + "," + clicks + "," + conversions + "," + reach + "," + page_like
                                  + "," + post_like + "," + page_engagement + "," + post_engagement
                                  + "," + spend + "," + ctr + ","+ unique_ctr + "," + unique_clicks+ ","+ frequency + "\n";
                   System.out.println(content1);

                   bw3.write(content1);


               } catch (JSONException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
               }

             }

           }

         }

       }
         bw3.close();




























  }

}
