package com.example.hotelmanagement;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseHandlerOnline extends AsyncTask<String, Void, String> {
    String URLaddUser = "http://xebo243.atwebpages.com/get_new_user.php" ;
    String URLupdateRoom="http://xebo243.atwebpages.com/update_room.php";
    String URLGetAllState="http://xebo243.atwebpages.com/all_states.php";
    public String objects[]={};
    public boolean exist;
    public boolean workdone=false;
    String TempName,TempsLast, TempEmail ,Tempphone,Temppass,Tempuser;


    public String state[]={};
    private String Case="";



    private String mRoomNumber;
    boolean mBooked=true;

    private String mFirstname,mLastname,mEmail,mPhonenumber,mUsername,mPassword;
    String resultlog;
    boolean passlog= Boolean.parseBoolean(null);
    public void setRoomNumber(String roomNumber) {
        mRoomNumber = roomNumber;
    }


    public void setCase(String aCase) {
        Case = aCase;
    }

    public void setFirstname(String firstname) {
        mFirstname = firstname;
    }

    public void setLastname(String lastname) {
        mLastname = lastname;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public void setPhonenumber(String phonenumber) {
        mPhonenumber = phonenumber;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public DatabaseHandlerOnline(String action){//when all we need is to set the case(we might need it in some cases)
        this.Case=action;
    }
    public DatabaseHandlerOnline(String Case,String rommn){//to check if a room is free or not
        this.Case=Case;
        this.mRoomNumber=rommn;

    }
    public DatabaseHandlerOnline(String action,String username,String password){//used to login
        this.Case=action;
        this.mUsername=username;
        this.mPassword=password;
    }

    public DatabaseHandlerOnline(String action,String fn,String ln,String mail,String number,String un,String pass){//used to register the user
        this.Case=action;
        this.mFirstname=fn;
        this.mLastname=ln;
        this.mEmail=mail;
        this.mPhonenumber=number;
        this.mUsername=un;
        this.mPassword=pass;


    }
    public String[] getState() {
        return state;
    }
    String res = "";


    public void GetData(){

        TempName = mFirstname;

        TempsLast = mLastname;
        TempEmail=mEmail;
        Tempphone=mPhonenumber;
        Temppass=mPassword;
        Tempuser=mUsername;

    }
    @Override
    protected String doInBackground(String... params) {
        String FirstHolder = this.mFirstname ;
        String LastHolder = this.mLastname ;
        String EmailHolder = this.mEmail;
        String PhoneHolder = this.mPhonenumber;
        String PassHolder = this.mPassword ;
        String UserHolder = this.mUsername ;
        String RoomHolder =String.valueOf(this.mRoomNumber).replace(" ","");
        if (this.Case.equals("register")) {
            //this action insert a new user

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("UFirstName", FirstHolder));
                nameValuePairs.add(new BasicNameValuePair("ULastName", LastHolder));
                nameValuePairs.add(new BasicNameValuePair("UEmail", EmailHolder));
                nameValuePairs.add(new BasicNameValuePair("UPhoneNumber", PhoneHolder));
                nameValuePairs.add(new BasicNameValuePair("UPassword", PassHolder));
                nameValuePairs.add(new BasicNameValuePair("UserName", UserHolder));

                try {
                    System.out.println("ok1");
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(URLaddUser);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse httpResponse = httpClient.execute(httpPost);
                    System.out.println("ok2");
                    HttpEntity httpEntity = httpResponse.getEntity();


                } catch (ClientProtocolException e) {
                    return "USER NOT REGISTERED";
                } catch (IOException e) {
                    return "USER NOT REGISTERED";

                }
                return "USER REGISTERED";
        }
        if(this.Case.equals("rooms")) {    //this action update a the table rooms when someone book

            try {
                String URLLogin = "http://xebo243.atwebpages.com/update_room.php?UserName=" + this.mUsername + "&RNumber=" + this.mPassword;
                URL url = new URL(URLLogin);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String json;
                while ((json = bufferedReader.readLine()) != null) {
                    sb.append(json + "\n");
                }
                return sb.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
                //this action get state info of all rooms
        if(this.Case.equals("state")) {

                try {
                    System.out.println("ok1");
                    URL url = new URL(URLGetAllState);
                    System.out.println("ok2");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    System.out.println("ok3");
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    System.out.println("ok4");
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    System.out.println("here 1 " +sb.toString().trim());

                    return parsing(sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
        }
        if(this.Case.equals("login")) {
            //this action get info from table

                try {
                    String URLLogin = "http://xebo243.atwebpages.com/login.php?UserName=" + this.mUsername + "&UPassword=" + this.mPassword;
                    URL url = new URL(URLLogin);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
        }
        if(this.Case.equals("check state")) {
            //this action get info from table
                try {
                    String URLCheckState = "http://xebo243.atwebpages.com/check_state.php?RNumber="+this.mRoomNumber;
                    System.out.println(this.mRoomNumber);
                    URL url = new URL(URLCheckState);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    System.out.println(sb.toString().trim());

                    return parsing(sb.toString());
                } catch (Exception e) {
                    return null;
                }
        }
        return null;

    }



    @Override
    protected void onPostExecute(String result) {

        if (Case.equals("state")){
            //we separate the states then store them in an array
            System.out.println(result);
            System.out.println("THIS METHOD SEND THE STATES");
            this.state=result.split(" ");
            System.out.println(Arrays.toString(state));
        }
        if(Case.equals("register")){

            String ex[]=result.split("\n");
            System.out.println(Arrays.toString(ex));
            if(this.exist==true){//this username already exists
                System.out.println("check it"+this.exist);
                this.passlog=true;
            }
            else{
                this.passlog=false;
            }
        }
        if(Case.equals("login")){

            System.out.println(result);
            this.resultlog=result.replace("[","").replace("]","");
            System.out.println(resultlog.length()>0);
            this.passlog=resultlog.length()>0;//it means that a user has been found when searching the username and the password given by the user
            System.out.println("handler "+this.passlog);//we print it for verification
        }
        if(Case.equals(("check state"))){
            String can= result.toString().replaceAll("\\s","");
            System.out.println(can);
            System.out.println(can.equals("AVAILABLE"));
            if(can.equals("AVAILABLE")){
                this.mBooked=false;//we state that the room has not been booked yet
                System.out.println(this.mBooked);
            }else{
                this.mBooked=true;//we state that the room has already been booked
            }


        }


    }
    private String parsing(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] stocks = new String[jsonArray.length()];
        String [] sb = new String[jsonArray.length()];
        String getjs="";
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            stocks[i] = obj.getString("RState");
            getjs=getjs+" "+stocks[i];
            if(i<0){
                getjs=getjs.substring(1);
            }


        }
        getjs=getjs.substring(1);
        System.out.println(getjs);

        return getjs;


    }

}
