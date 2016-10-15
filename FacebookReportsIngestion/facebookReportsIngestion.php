<html>
<head><title>Facebook php</title></head>
<body>

<?php
  	require_once __DIR__ . '/vendor/autoload.php';

use Facebook\Facebook;
use Facebook\FacebookRequest;
use Facebook\FacebookApp;

use Facebook\Exceptions\FacebookResponseException;
use Facebook\Exceptions\FacebookSDKException;
use FacebookAds\Api;
use FacebookAds\Object\AdUser;
use FacebookAds\Object\Fields\AdAccountFields;
use FacebookAds\Object\AdAccount;
use FacebookAds\Object\AdCampaign;
use FacebookAds\Object\Fields\AdCampaignFields;
use FacebookAds\Object\Fields\AdGroupFields;
use FacebookAds\Object\AdGroup;
use FacebookAds\Object\Fields\AdSetFields;
use FacebookAds\Object\AdSet;
use FacebookAds\Object\Insights;
use FacebookAds\Object\AbstractObject;
use FacebookAds\Object\Values\InsightsBreakdowns;
// Init PHP Sessions
session_start();

$app_id = '839000112895379';
$app_secret = '1bc9adcdfbdc6da9bb313868b2308289';
$_SESSION['facebook_access_token'] = 'EAAL7EOZByFZAMBADeFaw8qZA2m7flCyYMZBsGvbHDnyjIjuRRiNZChuEpcalBGe1Fc8UphxfvLCnjAqOZAYA6fIlQ04QEYOFZBC9jZBz3Tu29P2hbZB7rZBjygE7DSm0ese4R4OMeibuQqZAcoV5eFemQUWBhicaFaPZC5kZD';

$fb = new Facebook([
  'app_id' => $app_id,
  'app_secret' => $app_secret,
]);



$helper = $fb->getRedirectLoginHelper();



//echo '<p>hello world! ' . $_SESSION['facebook_access_token'] . ' </p>';

if (!isset($_SESSION['facebook_access_token'])) {
  $_SESSION['facebook_access_token'] = null;
}

if (!$_SESSION['facebook_access_token']) {
  $helper = $fb->getRedirectLoginHelper();
  try {
    $_SESSION['facebook_access_token'] = (string) $helper->getAccessToken();
	echo '<p>hello world! ' . $_SESSION['facebook_access_token'] . ' </p>';
  } catch(FacebookResponseException $e) {
    // When Graph returns an error
    echo 'Graph returned an error: ' . $e->getMessage();
    exit;
  } catch(FacebookSDKException $e) {
    // When validation fails or other local issues
    echo 'Facebook SDK returned an error: ' . $e->getMessage();
    exit;
  }
}

if ($_SESSION['facebook_access_token']) {
  echo '<p>Logged in! ' . $_SESSION['facebook_access_token'] . ' </p>';
  
  // Add to header of your file


// Add after echo "You are logged in "

// Initialize a new Session and instantiate an Api object
Api::init(
  $app_id, // App ID
  $app_secret,
  $_SESSION['facebook_access_token'] // Your user access token
);
 
 // Add after Api::init()
 
//list all accounts
$me = new AdUser('me');
$accounts = $me->getAdAccounts(array(
  AdAccountFields::ID,
  AdAccountFields::NAME,
));


$fbApp = new FacebookApp($app_id, $app_secret);

echo "<P>Accounts:</p>";

foreach($accounts as $account) {
  echo $account->id . ' - ' .$account->name."\n";
  
  $request = new FacebookRequest(
  $fbApp,
  $_SESSION['facebook_access_token'],
  'GET',
  '/'.$account->id.'/campaigns');
  
  $campaigns = $account->getCampaigns();//($fb->getClient()->sendRequest($request))->getObjects();
//$graphObject = $response->getGraphObject();

 echo "Campaigns:\n";

 $file_input = fopen("facebook_report2.csv","a");

 $file_input1 = fopen("facebook_demographics_report2.csv","a");

 $file_input2 = fopen("facebook_device_report2.csv","a");

 $file_input3 = fopen("facebook_geo_report2.csv","a");

 
 $file_input4 = fopen("facebook_general_report2.csv","a");


  fwrite($file_input,"date".","."likes".","."campaign_id".","."ctr".","."frequency".","."impressions".","."reach".","."actions".","."people_taking_actions".","."unique_clicks".","."unique_ctr".","."clicks"."\n");




	foreach($campaigns as $campaign) {
	
//	$insights = $campaign->getInsights()->getObjects();
/*
         $fields = array(
      InsightsFields::CAMPAIGN_NAME,
      InsightsFields::CAMPAIGN_ID,
      InsightsFields::DATE_START,
      InsightsFields::DATE_STOP,
      InsightsFields::IMPRESSIONS,
      InsightsFields::SOCIAL_IMPRESSIONS,
      InsightsFields::UNIQUE_CLICKS,
      InsightsFields::REACH,
      InsightsFields::SPEND,
      InsightsFields::TOTAL_ACTIONS,
      InsightsFields::TOTAL_ACTION_VALUE,
      InsightsFields::ACTIONS
    );

*/


$date=date('Y-m-d',strtotime("-0 days"));
//echo $date."\n";
    
$date1=date('Y-m-d',strtotime("-390 days"));
//echo $date1."\n";




    $params = array(
      'time_range' => array(
        'since' => "2016-01-01",
        'until' => "2016-07-29"
      ),

    
  
    );
    




         $fields = array();

        $insights =  $campaign->getInsights($fields,$params);

//        print_r($insights);

//        $insights = (array)$insights;	
    //      $name = $campaign->getSelf();

/*    
             $fields = array(
      InsightsFields::CAMPAIGN_NAME,
      InsightsFields::CAMPAIGN_ID,
      InsightsFields::DATE_START,
      InsightsFields::DATE_STOP,
      InsightsFields::IMPRESSIONS,
      InsightsFields::SOCIAL_IMPRESSIONS,
      InsightsFields::UNIQUE_CLICKS,
      InsightsFields::REACH,
      InsightsFields::SPEND,
      InsightsFields::TOTAL_ACTIONS,
      InsightsFields::TOTAL_ACTION_VALUE,
      InsightsFields::ACTIONS
    );

           $dump =  $campaign->getInsights($fields, $params);

           print_r($dump);

*/

      //      print_r($insights);

    //      print_r("Camp-



 $i =0;

          foreach ($insights as $key => $value){ 

       

             foreach ((array)$insights[$key] as $key1 => $value1)
                {
                  foreach ($value1 as $key2 => $value2){ 
                       


                      //   fwrite($file_input,$date.",");
                          

                         if($key2 == 'campaign_id') {
                             echo "campaign_id".":".$value2."\n";
                          


                            $command4 = 'curl -s -G -d "fields=campaign_name,impressions,clicks,unique_clicks,ctr,unique_ctr,frequency,actions,total_actions,total_unique_actions,reach,spend" -d "date_preset=lifetime" -d "access_token=EAAL7EOZByFZAMBADeFaw8qZA2m7flCyYMZBsGvbHDnyjIjuRRiNZChuEpcalBGe1Fc8UphxfvLCnjAqOZAYA6fIlQ04QEYOFZBC9jZBz3Tu29P2hbZB7rZBjygE7DSm0ese4R4OMeibuQqZAcoV5eFemQUWBhicaFaPZC5kZD" "https://graph.facebook.com/v2.7/'.$value2.'/insights"';


                             $result1=exec($command4);

//                            echo $command3."\n";

                           // echo $result3."\n";

                            $result2 = array();


                            $result2 = json_decode($result1,true);

                     //     echo $result3['data']."\n";

  //                         echo $result3."\n";


                           $result3 = array();

                            $result3 = $result2;

                          while($result3['paging']['next'])
                          {
                          $result3 = file_get_contents($result3['paging']['next']);

                        //  echo $result4."\n";
                          $result3 = json_decode($result3,true);

                           $result2 = array_merge($result2['data'], $result3['data']);

                          }



                           /*
                           foreach($data->data as $data1)
                                    echo $data1."\n";


                           var_dump($data['paging']['cursors']);
                           */


//                         echo $result1."\n"."\n";





                            $result5 =  json_encode($result2)."\n"."\n";

                            echo $result5."\n";


                           fwrite($file_input4,$value2."\n".$result5."\n");




                           $command1 = 'curl -s -G -d "breakdowns=age,gender" -d "fields=impressions,clicks,total_actions,reach,spend" -d "date_preset=lifetime" -d "access_token=EAAL7EOZByFZAMBADeFaw8qZA2m7flCyYMZBsGvbHDnyjIjuRRiNZChuEpcalBGe1Fc8UphxfvLCnjAqOZAYA6fIlQ04QEYOFZBC9jZBz3Tu29P2hbZB7rZBjygE7DSm0ese4R4OMeibuQqZAcoV5eFemQUWBhicaFaPZC5kZD" "https://graph.facebook.com/v2.7/'.$value2.'/insights"';

                           echo $command1."\n";

                           
                           $result1=exec($command1);

//                            echo $command3."\n";

                           // echo $result3."\n";

                           $result2 = array();


                           $result2 = json_decode($result1,true);

                     //     echo $result3['data']."\n";

  //                         echo $result3."\n";


                           $result3 = array();
                           $result3 = $result2;

                          while($result3['paging']['next'])
                          {
                          $result3 = file_get_contents($result3['paging']['next']);

                        //  echo $result4."\n";
                          $result3 = json_decode($result3,true);

                           $result2 = array_merge($result2['data'], $result3['data']);

                          }


                                 
                           /*
                           foreach($data->data as $data1)
                                    echo $data1."\n";


                           var_dump($data['paging']['cursors']);
                           */

        
//                         echo $result1."\n"."\n";                       




                            
                            $result5 =  json_encode($result2)."\n"."\n";

                            echo $result5."\n";

                            
                           fwrite($file_input1,$value2."\n".$result5."\n");




                           $command2 = 'curl -s -G -d "breakdowns=impression_device" -d "fields=impressions,clicks,total_actions,reach,spend" -d "date_preset=lifetime" -d "access_token=EAAL7EOZByFZAMBADeFaw8qZA2m7flCyYMZBsGvbHDnyjIjuRRiNZChuEpcalBGe1Fc8UphxfvLCnjAqOZAYA6fIlQ04QEYOFZBC9jZBz3Tu29P2hbZB7rZBjygE7DSm0ese4R4OMeibuQqZAcoV5eFemQUWBhicaFaPZC5kZD" "https://graph.facebook.com/v2.7/'.$value2.'/insights"';

//                           $result2=exec($command2);


                           
                           $result1=exec($command2);

//                            echo $command3."\n";

                         //   echo $result3."\n";

                            $result2 = array();
  
                           $result2 = json_decode($result1,true);

                     //     echo $result3['data']."\n";

  //                         echo $result3."\n";

                           $result3 = array();

                           $result3 = $result2;

                          while($result3['paging']['next'])
                          {
                          $result3 = file_get_contents($result3['paging']['next']);

                        //  echo $result4."\n";
                          $result3 = json_decode($result3,true);

                           $result2 = array_merge($result2['data'], $result3['data']);

                          }

                           
                            $result5 =  json_encode($result2)."\n"."\n";
                         
                            echo $result5."\n";
 
                           fwrite($file_input2,$value2."\n".$result5."\n");
                               

                           $command3 =  'curl -s -G -d "breakdowns=region" -d "fields=impressions,clicks,total_actions,reach,spend" -d "date_preset=lifetime" -d "access_token=EAAL7EOZByFZAMBADeFaw8qZA2m7flCyYMZBsGvbHDnyjIjuRRiNZChuEpcalBGe1Fc8UphxfvLCnjAqOZAYA6fIlQ04QEYOFZBC9jZBz3Tu29P2hbZB7rZBjygE7DSm0ese4R4OMeibuQqZAcoV5eFemQUWBhicaFaPZC5kZD" "https://graph.facebook.com/v2.7/'.$value2.'/insights"';

                           $result1=exec($command3);

//                            echo $command3."\n";

                          //  echo $result3."\n";
                           $result2 = array();

                           $result2 = json_decode($result1,true);

                     //     echo $result3['data']."\n";

  //                         echo $result3."\n";

                           $result3 = array();

                           $result3 = $result2;

                          while($result3['paging']['next'])
                          {
                          $result3 = file_get_contents($result3['paging']['next']);
                            
                        //  echo $result4."\n";
                          $result3 = json_decode($result3,true);

                           $result2 = array_merge($result2['data'], $result3['data']);

                          }

                         // $pos1 = strrpos($result3,"next");
 
                           


                          // echo "UrlValue $pos1";

                           $result5 =  json_encode($result2)."\n"."\n";
                          
                               
  
                       //      echo "UrlValue $pos1";

                           echo $result5."\n";
 
                           fwrite($file_input3,$value2."\n".$result5."\n");

                           fwrite($file_input,$value2.",");
                           }
                   
                       



                        if($key2 == 'impressions') {
                             echo "impressions".":".$value2."\n";

                           fwrite($file_input,$value2.",");                
                           }     


                        if($key2 == 'clicks'){
                             echo "clicks".":".$value2."\n";
                                
                  //       echo $key2.":".$value2."\n";
                           fwrite($file_input,$value2."\n");
                           }
                         

                        if($key2 == 'unique_clicks'){
                             echo "unique_clicks".":".$value2."\n";

                            fwrite($file_input,$value2.",");

                           }

                        if($key2 == 'reach'){
                              echo "reach".":".$value2."\n";
                            fwrite($file_input,$value2.",");
                            }  

                        if($key2 == 'ctr'){
                             echo "ctr".":".$value2."\n";

                          fwrite($file_input,$value2.",");
                        }
                          

                        if($key2 == 'unique_ctr'){
                             echo "unique_ctr".":".$value2."\n";

                           fwrite($file_input,$value2.",");
                        }                           
 

                        if($key2 == 'total_actions'){
                             echo "actions".":".$value2."\n";
                            fwrite($file_input,$value2.",");
                           }


                         if($key2 == 'total_unique_actions'){
                            echo "people_taking_actions".":".$value2."\n";

  
                             fwrite($file_input,$value2.",");
                          }                   

                          if($key2 == 'frequency'){
                            echo "frequency".":".$value2."\n";

                             fwrite($file_input,$value2.",");
                           }

   

                               if($value1 == null || $value1 == "")
                                  fwrite($file_input,"".",");



 
                     if($key2 == 'actions'){
                         //    var_dump($value2);
                                   
                          foreach ($value2 as $key3  => $value3)
                               {
    
                                  if($value2 == null || $value2 == "")
                                  fwrite($file_input,"".",");


                               
                                  foreach ($value3 as $key4 => $value4){
                                  if ($key4 == 'action_type' && $value4 == "post_like"){
                                 


                                     echo "likes".":".$value3['value']."\n";
                                     fwrite($file_input,$date.",");
                                     fwrite($file_input,$value3['value'].",");
                                       $i =1;

                                  }                                      
                          }
        
                                                    }                 
                        if($i == 0){
                        fwrite($file_input,"".",");
                          
                       }       

              }
            
                                    




 }
  

         }

     }




//          print_r($insights->{0}->{"impressions"});




//          print_r($insights);

 

//echo "<p>insights::start</p>";

//         print_r($insights);

//	$csv=outputCsv("report.csv",(array)$insights);
  //  echo $csv;
//	echo "<p>insights::end</p>";	
		
	}
}

} else {
  $permissions = ['ads_management'];
  $loginUrl = $helper->getLoginUrl('http://localhost/dashboard/facebook/facebook.php', $permissions);
  echo '<a href="' . $loginUrl . '">Log in with Facebook</a>';
} 

/*
function arrayToCsv( array $fields, $delimiter = ';', $enclosure = '"', $encloseAll = false, $nullToMysqlNull = false ) {
    $delimiter_esc = preg_quote($delimiter, '/');
    $enclosure_esc = preg_quote($enclosure, '/');

    $output = array();
    foreach ( $fields as $field ) {
        if ($field === null && $nullToMysqlNull) {
            $output[] = 'NULL';
            continue;
        }

        // Enclose fields containing $delimiter, $enclosure or whitespace
        if ( $encloseAll || preg_match( "/(?:${delimiter_esc}|${enclosure_esc}|\s)/", (string)$field ) ) {
            $output[] = $enclosure . str_replace($enclosure, $enclosure . $enclosure, $field) . $enclosure;
        }
        else {
            $output[] = $field;
        }
    }

   // return implode( $delimiter, $output );
     return $output;

}
*/
/*
function outputCsv($fileName, $assocDataArray)
{
//    ob_clean();
//    header('Pragma: public');
  //  header('Expires: 0');
//    header('Cache-Control: must-revalidate, post-check=0, pre-check=0');
  //  header('Cache-Control: private', false);
 //   header('Content-Type: text/csv');
//    header('Content-Disposition: attachment;filename=' . $fileName);    
    if(isset($assocDataArray['0'])){
        $fp = fopen($fileName, 'w');
        fputcsv($fp, array_keys($assocDataArray['0']));
        foreach($assocDataArray AS $values){
            fputcsv($fp, $values);
        }
        fclose($fp);
    }
  //  ob_flush();
}
*/

 $file_input = fopen("report.csv","w");

/*

$array = json_decode(json_encode($insights), true);

echo $array["impressions"];

var_dump($array);
*/






/*
  foreach ((array)$insights as $row) {
//  print_r($row);
q

//       fputcsv($file_input, (string)$row);
//    print_r($row);
  

    $row = (array)$row;
    var_dump($row);
    fwrite($file_input,$row["campaign_id"].",".$row["impressions"].",".$row["likes"]."\n");
  


 }
*/


 fclose($file_input);

/*
function object_to_array($data)
{
    if (is_array($data) || is_object($data))
    {
        $result = array();
        foreach ($data as $key => $value)
        {
         $result[$key] = object_to_array($value);
         var_dump($result);

        }
        return $result;
    }
    return $data;
}
*/








?>

</body>
</html>
