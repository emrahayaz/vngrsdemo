package com.emrahayaz.vngrs;

/**
 * Created by poseydon on 13.09.2015.
 */
public final class AppData {
    private static final  String fetchUrl="https://api.foursquare.com/v2/venues/search";
    private static  final  String clientID="4UN5MBNGNW0HR32R4CMC3QZ2EEOCUGXFJYMW2C2U1LFHOMXQ";
    private static final  String clientSecret="LSECBYENVCUFACQJXMGCI5BUFMP5PWQG5WDO4P1OXDFZ5MYL";
    public static String getRequestUrl(String location){
        return fetchUrl+"?client_id="+clientID+"&client_secret="+clientSecret+"&v=20130815&limit=50&near="+location;
    }
}
