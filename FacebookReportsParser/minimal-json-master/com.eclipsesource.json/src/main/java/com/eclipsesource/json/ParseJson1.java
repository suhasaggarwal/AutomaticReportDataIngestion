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

public class ParseJson1 {

  public static void main(String[] args) throws IOException {
    // TODO Auto-generated method stub

       BufferedReader br = null;

       String sCurrentLine;

       br = new BufferedReader(new FileReader("C:\\Users\\Sony\\Desktop\\facebook_geo_report.csv"));

       File file = new File("C:\\Users\\Sony\\Desktop\\facebook_geo_report1.csv");

       String content = "Date CampaignId Impressions Clicks Conversions Region"+"\n";

       // if file doesnt exists, then create it
       if (!file.exists()) {
           file.createNewFile();
       }

       FileWriter fw = new FileWriter(file.getAbsoluteFile());
       BufferedWriter bw = new BufferedWriter(fw);
       bw.write(content);



       String strLine;
       String json = null;
       //Read File Line By Line
       String campaign_id=null;
       String impressions;
       String clicks;
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


       int j=0;

          while ((strLine = br.readLine()) != null)   {
           // Print the content on the console
         //  System.out.println (strLine);
           if(j%2 == 0) {
            campaign_id = strLine;
            System.out.println("cid:"+campaign_id+"\n");
            j++;

           } else {
            json = strLine;
            JSONObject o = null;
            try {
              o = new JSONObject(json);
            } catch (JSONException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            j++;

            JSONArray arrayOfTests = null;
            try {
              arrayOfTests = (JSONArray)o.get("data");
            } catch (JSONException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }

            for (int i = 0; i < arrayOfTests.length(); i++) {

                  JSONObject jsonObject=null;

                  try {
                    jsonObject = new JSONObject(arrayOfTests.get(i).toString());
                    impressions=jsonObject.getString("impressions");
                    clicks=jsonObject.getString("clicks");
                    conversions=jsonObject.getString("total_actions");
                    date=jsonObject.getString("date_start");
                    region=jsonObject.getString("region");
                   // System.out.println(jsonObject.getString("impressions"));





                  } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                  }




            System.out.println("data:"+json + "\n");




          }






     //  String json = "{\"data\":[{\"impressions\":\"50229\",\"clicks\":518,\"total_actions\":533,\"spend\":523.85,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"18-24\",\"gender\":\"female\"},{\"impressions\":\"350188\",\"clicks\":3998,\"total_actions\":3962,\"spend\":4677.28,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"18-24\",\"gender\":\"male\"},{\"impressions\":\"93\",\"clicks\":0,\"total_actions\":0,\"spend\":0,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"18-24\",\"gender\":\"unknown\"},{\"impressions\":\"22625\",\"clicks\":267,\"total_actions\":268,\"spend\":340.87,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"25-34\",\"gender\":\"female\"},{\"impressions\":\"261781\",\"clicks\":2565,\"total_actions\":2546,\"spend\":3553.15,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"25-34\",\"gender\":\"male\"},{\"impressions\":\"308\",\"clicks\":2,\"total_actions\":2,\"spend\":1.48,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"25-34\",\"gender\":\"unknown\"},{\"impressions\":\"12008\",\"clicks\":128,\"total_actions\":125,\"spend\":158.26,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"35-44\",\"gender\":\"female\"},{\"impressions\":\"124033\",\"clicks\":1287,\"total_actions\":1210,\"spend\":1723.02,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"35-44\",\"gender\":\"male\"},{\"impressions\":\"177\",\"clicks\":2,\"total_actions\":2,\"spend\":2.14,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"35-44\",\"gender\":\"unknown\"},{\"impressions\":\"4095\",\"clicks\":59,\"total_actions\":55,\"spend\":78.83,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"45-54\",\"gender\":\"female\"},{\"impressions\":\"46343\",\"clicks\":478,\"total_actions\":459,\"spend\":684.63,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"45-54\",\"gender\":\"male\"},{\"impressions\":\"107\",\"clicks\":2,\"total_actions\":2,\"spend\":8.52,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"45-54\",\"gender\":\"unknown\"},{\"impressions\":\"8\",\"clicks\":3,\"total_actions\":2,\"spend\":2.15,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"55-64\",\"gender\":\"female\"},{\"impressions\":\"212\",\"clicks\":6,\"total_actions\":5,\"spend\":13.21,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"55-64\",\"gender\":\"male\"},{\"impressions\":\"8\",\"clicks\":0,\"total_actions\":0,\"spend\":0,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"65+\",\"gender\":\"female\"},{\"impressions\":\"86\",\"clicks\":0,\"total_actions\":0,\"spend\":0,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"65+\",\"gender\":\"male\"}],\"paging\":{\"cursors\":{\"before\":\"MAZDZD\",\"after\":\"MTUZD\"}}}";


    JSONObject o = null;
    try {

      o = new JSONObject(json);
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    JSONArray arrayOfTests = null;
    try {
      arrayOfTests = (JSONArray)o.get("data");
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    for (int i = 0; i < arrayOfTests.length(); i++) {

          try {
            System.out.println(arrayOfTests.get(i));
          } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }

          JSONObject jsonObject=null;

          try {
            jsonObject = new JSONObject(arrayOfTests.get(i).toString());

            date=jsonObject.getString("date_start");
            impressions=jsonObject.getString("impressions");
            clicks=jsonObject.getString("clicks");
            conversions=jsonObject.getString("total_actions");
            spend=jsonObject.getString("spend");
            location=jsonObject.getString("region");
            content1 = date+" "+campaign_id+" "+impressions+" "+clicks+" "+conversions+" "+spend+" "+location+"\n";
            bw.write(content1);


          } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }

    }

        }

        bw.close();

         }



        br = new BufferedReader(new FileReader("C:\\Users\\Sony\\Desktop\\facebook_device_report.csv"));

        file = new File("C:\\Users\\Sony\\Desktop\\facebook_device_report1.csv");

        content = "Date CampaignId Impressions Clicks Conversions Device"+"\n";

        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }

        fw = new FileWriter(file.getAbsoluteFile());
        bw = new BufferedWriter(fw);
        bw.write(content);


        j=0;

        while ((strLine = br.readLine()) != null)   {
         // Print the content on the console
       //  System.out.println (strLine);
         if(j%2 == 0) {
          campaign_id = strLine;
          System.out.println("cid:"+campaign_id+"\n");
          j++;

         } else {
          json = strLine;
          JSONObject o = null;
          try {
            o = new JSONObject(json);
          } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          j++;

          JSONArray arrayOfTests = null;
          try {
            arrayOfTests = (JSONArray)o.get("data");
          } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }

          for (int i = 0; i < arrayOfTests.length(); i++) {

                JSONObject jsonObject=null;

                try {
                  jsonObject = new JSONObject(arrayOfTests.get(i).toString());
                  impressions=jsonObject.getString("impressions");
                  clicks=jsonObject.getString("clicks");
                  conversions=jsonObject.getString("total_actions");
                  date=jsonObject.getString("date_start");
                  region=jsonObject.getString("impression_device");
                 // System.out.println(jsonObject.getString("impressions"));





                } catch (JSONException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                }




          System.out.println("data:"+json + "\n");




        }



        }


   //  String json = "{\"data\":[{\"impressions\":\"50229\",\"clicks\":518,\"total_actions\":533,\"spend\":523.85,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"18-24\",\"gender\":\"female\"},{\"impressions\":\"350188\",\"clicks\":3998,\"total_actions\":3962,\"spend\":4677.28,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"18-24\",\"gender\":\"male\"},{\"impressions\":\"93\",\"clicks\":0,\"total_actions\":0,\"spend\":0,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"18-24\",\"gender\":\"unknown\"},{\"impressions\":\"22625\",\"clicks\":267,\"total_actions\":268,\"spend\":340.87,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"25-34\",\"gender\":\"female\"},{\"impressions\":\"261781\",\"clicks\":2565,\"total_actions\":2546,\"spend\":3553.15,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"25-34\",\"gender\":\"male\"},{\"impressions\":\"308\",\"clicks\":2,\"total_actions\":2,\"spend\":1.48,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"25-34\",\"gender\":\"unknown\"},{\"impressions\":\"12008\",\"clicks\":128,\"total_actions\":125,\"spend\":158.26,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"35-44\",\"gender\":\"female\"},{\"impressions\":\"124033\",\"clicks\":1287,\"total_actions\":1210,\"spend\":1723.02,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"35-44\",\"gender\":\"male\"},{\"impressions\":\"177\",\"clicks\":2,\"total_actions\":2,\"spend\":2.14,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"35-44\",\"gender\":\"unknown\"},{\"impressions\":\"4095\",\"clicks\":59,\"total_actions\":55,\"spend\":78.83,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"45-54\",\"gender\":\"female\"},{\"impressions\":\"46343\",\"clicks\":478,\"total_actions\":459,\"spend\":684.63,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"45-54\",\"gender\":\"male\"},{\"impressions\":\"107\",\"clicks\":2,\"total_actions\":2,\"spend\":8.52,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"45-54\",\"gender\":\"unknown\"},{\"impressions\":\"8\",\"clicks\":3,\"total_actions\":2,\"spend\":2.15,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"55-64\",\"gender\":\"female\"},{\"impressions\":\"212\",\"clicks\":6,\"total_actions\":5,\"spend\":13.21,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"55-64\",\"gender\":\"male\"},{\"impressions\":\"8\",\"clicks\":0,\"total_actions\":0,\"spend\":0,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"65+\",\"gender\":\"female\"},{\"impressions\":\"86\",\"clicks\":0,\"total_actions\":0,\"spend\":0,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"65+\",\"gender\":\"male\"}],\"paging\":{\"cursors\":{\"before\":\"MAZDZD\",\"after\":\"MTUZD\"}}}";


         JSONObject  o = null;
  try {
    o = new JSONObject(json);
  } catch (JSONException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }

  JSONArray arrayOfTests = null;
  try {
    arrayOfTests = (JSONArray)o.get("data");
  } catch (JSONException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }

  for (int i = 0; i < arrayOfTests.length(); i++) {

        try {
          System.out.println(arrayOfTests.get(i));
        } catch (JSONException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

        JSONObject jsonObject=null;

        try {
          jsonObject = new JSONObject(arrayOfTests.get(i).toString());

          date=jsonObject.getString("date_start");
          impressions=jsonObject.getString("impressions");
          clicks=jsonObject.getString("clicks");
          conversions=jsonObject.getString("total_actions");
          spend=jsonObject.getString("spend");
          impression_device=jsonObject.getString("impression_device");
          content2 = date+" "+campaign_id+" "+impressions+" "+clicks+" "+conversions+" "+spend+" "+impression_device+"\n";
          bw.write(content2);


        } catch (JSONException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }



      }

      bw.close();

      }

      br = new BufferedReader(new FileReader("C:\\Users\\Sony\\Desktop\\facebook_demographics_report.csv"));

      file = new File("C:\\Users\\Sony\\Desktop\\facebook_demographics_report1.csv");

      content = "Date CampaignId Impressions Clicks Conversions Age Gender"+"\n";

      // if file doesnt exists, then create it
      if (!file.exists()) {
          file.createNewFile();
      }

      fw = new FileWriter(file.getAbsoluteFile());
      bw = new BufferedWriter(fw);
      bw.write(content);


      j=0;

      while ((strLine = br.readLine()) != null)   {
       // Print the content on the console
     //  System.out.println (strLine);
       if(j%2 == 0) {
        campaign_id = strLine;
        System.out.println("cid:"+campaign_id+"\n");
        j++;

       } else {
        json = strLine;
        JSONObject o = null;
        try {
          o = new JSONObject(json);
        } catch (JSONException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        j++;

        JSONArray  arrayOfTests = null;
        try {
          arrayOfTests = (JSONArray)o.get("data");
        } catch (JSONException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

        for (int i = 0; i < arrayOfTests.length(); i++) {

              JSONObject jsonObject=null;

              try {
                jsonObject = new JSONObject(arrayOfTests.get(i).toString());
                impressions=jsonObject.getString("impressions");
                clicks=jsonObject.getString("clicks");
                conversions=jsonObject.getString("total_actions");
                date=jsonObject.getString("date_start");
                age=jsonObject.getString("age");
               // System.out.println(jsonObject.getString("impressions"));
                gender=jsonObject.getString("gender");




              } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }




        System.out.println("data:"+json + "\n");




      }



      }


 //  String json = "{\"data\":[{\"impressions\":\"50229\",\"clicks\":518,\"total_actions\":533,\"spend\":523.85,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"18-24\",\"gender\":\"female\"},{\"impressions\":\"350188\",\"clicks\":3998,\"total_actions\":3962,\"spend\":4677.28,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"18-24\",\"gender\":\"male\"},{\"impressions\":\"93\",\"clicks\":0,\"total_actions\":0,\"spend\":0,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"18-24\",\"gender\":\"unknown\"},{\"impressions\":\"22625\",\"clicks\":267,\"total_actions\":268,\"spend\":340.87,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"25-34\",\"gender\":\"female\"},{\"impressions\":\"261781\",\"clicks\":2565,\"total_actions\":2546,\"spend\":3553.15,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"25-34\",\"gender\":\"male\"},{\"impressions\":\"308\",\"clicks\":2,\"total_actions\":2,\"spend\":1.48,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"25-34\",\"gender\":\"unknown\"},{\"impressions\":\"12008\",\"clicks\":128,\"total_actions\":125,\"spend\":158.26,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"35-44\",\"gender\":\"female\"},{\"impressions\":\"124033\",\"clicks\":1287,\"total_actions\":1210,\"spend\":1723.02,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"35-44\",\"gender\":\"male\"},{\"impressions\":\"177\",\"clicks\":2,\"total_actions\":2,\"spend\":2.14,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"35-44\",\"gender\":\"unknown\"},{\"impressions\":\"4095\",\"clicks\":59,\"total_actions\":55,\"spend\":78.83,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"45-54\",\"gender\":\"female\"},{\"impressions\":\"46343\",\"clicks\":478,\"total_actions\":459,\"spend\":684.63,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"45-54\",\"gender\":\"male\"},{\"impressions\":\"107\",\"clicks\":2,\"total_actions\":2,\"spend\":8.52,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"45-54\",\"gender\":\"unknown\"},{\"impressions\":\"8\",\"clicks\":3,\"total_actions\":2,\"spend\":2.15,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"55-64\",\"gender\":\"female\"},{\"impressions\":\"212\",\"clicks\":6,\"total_actions\":5,\"spend\":13.21,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"55-64\",\"gender\":\"male\"},{\"impressions\":\"8\",\"clicks\":0,\"total_actions\":0,\"spend\":0,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"65+\",\"gender\":\"female\"},{\"impressions\":\"86\",\"clicks\":0,\"total_actions\":0,\"spend\":0,\"date_start\":\"2016-01-27\",\"date_stop\":\"2016-02-27\",\"age\":\"65+\",\"gender\":\"male\"}],\"paging\":{\"cursors\":{\"before\":\"MAZDZD\",\"after\":\"MTUZD\"}}}";


JSONObject o = null;
try {
  o = new JSONObject(json);
} catch (JSONException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
}

JSONArray arrayOfTests = null;
try {
  arrayOfTests = (JSONArray)o.get("data");
} catch (JSONException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
}

for (int i = 0; i < arrayOfTests.length(); i++) {

      try {
        System.out.println(arrayOfTests.get(i));
      } catch (JSONException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      JSONObject jsonObject=null;

      try {
        jsonObject = new JSONObject(arrayOfTests.get(i).toString());

        date=jsonObject.getString("date_start");
        impressions=jsonObject.getString("impressions");
        clicks=jsonObject.getString("clicks");
        conversions=jsonObject.getString("total_actions");
        spend=jsonObject.getString("spend");
        age=jsonObject.getString("age");
        gender= jsonObject.getString("gender");
        content2 = date+" "+campaign_id+" "+impressions+" "+clicks+" "+conversions+" "+spend+" "+age+" "+gender+"\n";
        bw.write(content2);


      } catch (JSONException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }



    }

    bw.close();

   }
































  }


  }




