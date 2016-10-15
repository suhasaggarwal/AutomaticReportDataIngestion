// Copyright 2016 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.adwords.datadownload;

import com.google.api.ads.adwords.lib.client.AdWordsSession;
import com.google.api.ads.adwords.lib.client.reporting.ReportingConfiguration;
import com.google.api.ads.adwords.lib.jaxb.v201603.DownloadFormat;
import com.google.api.ads.adwords.lib.jaxb.v201603.ReportDefinition;
import com.google.api.ads.adwords.lib.jaxb.v201603.ReportDefinitionDateRangeType;
import com.google.api.ads.adwords.lib.jaxb.v201603.ReportDefinitionReportType;
import com.google.api.ads.adwords.lib.jaxb.v201603.Selector;
import com.google.api.ads.adwords.lib.utils.ReportDownloadResponse;
import com.google.api.ads.adwords.lib.utils.ReportDownloadResponseException;
import com.google.api.ads.adwords.lib.utils.v201603.ReportDownloader;
import com.google.api.ads.common.lib.auth.OfflineCredentials;
import com.google.api.ads.common.lib.auth.OfflineCredentials.Api;
import com.google.api.client.auth.oauth2.Credential;
import com.google.common.collect.Lists;

import java.io.File;

/**
 * 
 * Downloads specific Reports Using Adwords Query Language.
 * Different report codes such as Geo based,Device based,Demographics based and more Reports are downloaded 
 * These reports are scheduled in cron and data is ingested regularly in Mysql 
 * 
 * 
 */
public class DownloadAdwordsReport {

	public static void main(String[] args) throws Exception {
		// Generate a refreshable OAuth2 credential.
		Credential oAuth2Credential = new OfflineCredentials.Builder()
				.forApi(Api.ADWORDS).fromFile().build().generateCredential();

		// Construct an AdWordsSession.
		AdWordsSession session = new AdWordsSession.Builder().fromFile()
				.withOAuth2Credential(oAuth2Credential).build();

		session.setClientCustomerId("759-344-3463");

		// Location to download report to.
	//	String reportFile = System.getProperty("user.home")
		//		+ File.separatorChar + "report.csv";

		// Location to download report to.
	//	String reportFile1 = System.getProperty("user.home")
		//		+ File.separatorChar + "report1.csv";

		
		String reportFile1 = "Adwords_Geo_Report.csv";
		String reportFile2= "Adwords_Audience_Report.csv";
		String reportFile3 = "Adwords_General_Report.csv";
		String reportFile4 = "Adwords_Age_Report.csv";
		String reportFile5 = "Adwords_Gender_Report.csv"; 
		
		runReportDownloader(session, reportFile1, reportFile2, reportFile3, reportFile4, reportFile5);
		
	}

	public static void runReportDownloader(AdWordsSession session,
			String reportFile1, String reportFile2, String reportFile3, String reportFile4, String reportFile5) throws Exception {

	
		//Use of Adwords Query Language to pull certain columns from different Reports such as Campaign Performance Report and Geo Performance Report	
		
		
/*		
		String query = "SELECT Date,CampaignId,CampaignName, "
				+ "Impressions, Clicks, Cost FROM CRITERIA_PERFORMANCE_REPORT "
				// +
				// "ORDER BY CampaignId,Location,Operating System Version,Mobile Device "
				+ "DURING LAST_30_DAYS";
*/
		
		
		String query1 = "SELECT Date,CampaignId,CampaignName, "
				+ "Impressions, Clicks, Conversions, Cost, CityCriteriaId, CountryCriteriaId, Device FROM GEO_PERFORMANCE_REPORT "
				// +
				// "ORDER BY CampaignId,Location,Operating System Version,Mobile Device "
				+ "DURING 20160401,20160731";

	/*	
		String query2= "SELECT Date,CampaignId,CampaignName, "
				+ "Impressions, Clicks, Cost FROM CAMPAIGN_PERFORMANCE_REPORT "
				// +
				// "ORDER BY CampaignId,Location,Operating System Version,Mobile Device "
				+ "DURING LAST_30_DAYS";
*/
		
		String query2 = "SELECT Date,CampaignId,CampaignName,Criteria,Impressions,Clicks,Conversions,Cost "
				+ "FROM AUDIENCE_PERFORMANCE_REPORT "
				// +
				// "ORDER BY CampaignId,Location,Operating System Version,Mobile Device "
				+ "DURING 20160401,20160701";
		
		

		String query3= "SELECT Date,CampaignId,CampaignName, "
				+ "Impressions, Clicks, Conversions,Cost,ImpressionReach FROM CAMPAIGN_PERFORMANCE_REPORT "
				// +
				// "ORDER BY CampaignId,Location,Operating System Version,Mobile Device "
				+ "DURING 20160413,20160731";
		
		
		String query4 = "SELECT Date,CampaignId,CampaignName,Criteria,Impressions,Clicks,Conversions,Cost "
				+ "FROM AGE_RANGE_PERFORMANCE_REPORT "
				// +
				// "ORDER BY CampaignId,Location,Operating System Version,Mobile Device "
				+ "DURING 20160401,20160701";

		String query5 = "SELECT Date,CampaignId,CampaignName,Criteria,Impressions,Clicks,Conversions,Cost "
				+ "FROM GENDER_PERFORMANCE_REPORT "
				// +
				// "ORDER BY CampaignId,Location,Operating System Version,Mobile Device "
				+ "DURING 20160401,20160701";
		
		
		//Reports generated are saved to CSVs from which they are uploaded to mysql 
		
		
		// String query2 = "SELECT Date,CampaignId, "
		// + "Impressions, Clicks, Cost,DA= FROM _PERFORMANCE_REPORT "
		// +
		// "ORDER BY CampaignId,Location,Operating System Version,Mobile Device "
		// + "DURING LAST_30_DAYS";

		// Optional: Set the reporting configuration of the session to suppress
		// header, column name, or
		// summary rows in the report output. You can also configure this via
		// your ads.properties
		// configuration file. See AdWordsSession.Builder.from(Configuration)
		// for details.
		// In addition, you can set whether you want to explicitly include or
		// exclude zero impression
		// rows.
	
	/*	
		ReportingConfiguration reportingConfiguration = new ReportingConfiguration.Builder()
				.skipReportHeader(false).skipColumnHeader(false)
				.skipReportSummary(false)
				// Set to false to exclude rows with zero impressions.
				.includeZeroImpressions(true).build();
		session.setReportingConfiguration(reportingConfiguration);

		try {
			// Set the property api.adwords.reportDownloadTimeout or call
			// ReportDownloader.setReportDownloadTimeout to set a timeout (in
			// milliseconds)
			// for CONNECT and READ in report downloads.
			ReportDownloadResponse response = new ReportDownloader(session)
					.downloadReport(query2, DownloadFormat.CSV);
			response.saveToFile(reportFile);

			System.out.printf("Report successfully downloaded to: %s%n",
					reportFile);

		} catch (ReportDownloadResponseException e) {
			System.out.printf("Report successfully downloaded to: %s%n", e);

		}

		*/
		
		ReportingConfiguration reportingConfiguration1 = new ReportingConfiguration.Builder()
				.skipReportHeader(false).skipColumnHeader(false)
				.skipReportSummary(false)
				// Set to false to exclude rows with zero impressions.
				.includeZeroImpressions(false).build();
		session.setReportingConfiguration(reportingConfiguration1);

		try {
			ReportDownloadResponse response1 = new ReportDownloader(session)
					.downloadReport(query1, DownloadFormat.CSV);
			response1.saveToFile(reportFile1);

			ReportDownloadResponse response2 = new ReportDownloader(session)
			.downloadReport(query2, DownloadFormat.CSV);
	        response2.saveToFile(reportFile2);

	
	        ReportDownloadResponse response3 = new ReportDownloader(session)
	        .downloadReport(query3, DownloadFormat.CSV);
             response3.saveToFile(reportFile3);

            ReportDownloadResponse response4 = new ReportDownloader(session)
            .downloadReport(query4, DownloadFormat.CSV);
             response4.saveToFile(reportFile4);

            ReportDownloadResponse response5 = new ReportDownloader(session)
            .downloadReport(query5, DownloadFormat.CSV);
             response5.saveToFile(reportFile5);

			
			
			
			
			System.out.printf("Report successfully downloaded to: %s%n",
					reportFile1);

		} catch (ReportDownloadResponseException e) {
			System.out.printf("Report successfully downloaded to: %s%n",e);
		
		}

	}

}