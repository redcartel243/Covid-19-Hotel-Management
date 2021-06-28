/*DatabseHandler is a class that is used toi handle transactions and processes between the app and the database. different functions have been made for the purpose of making
everything easier
 */
package com.example.hotelmanagement;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Arrays;

import net.sourceforge.jtds.jdbc.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Arrays;
import java.util.prefs.PreferenceChangeListener;

public class DatabseHandler extends AsyncTask<String, Void, String>{

    private static final String url = "jdbc:mysql://192.168.56.101:3306/HotelRegistration";
    private static final String user = "hoteladmin";
    private static final String pass = "1921685117";
    public String objects[]={};
    public boolean exist;


    public String state[]={};
    private String Case="";



    private int mRoomNumber;
    boolean mBooked=true;

    private String mFirstname,mLastname,mEmail,mPhonenumber,mUsername,mPassword;
    String resultlog;
    boolean passlog= Boolean.parseBoolean(null);
    public void setRoomNumber(int roomNumber) {
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

    public DatabseHandler(String action){//when all we need is to set the case(we might need it in some cases)
        this.Case=action;
    }
    public DatabseHandler(String Case,int rommn){//to check if a room is free or not
        this.Case=Case;
        this.mRoomNumber=rommn;

    }
    public DatabseHandler(String action,String username,String password){//used to login
        this.Case=action;
        this.mUsername=username;
        this.mPassword=password;
    }

    public DatabseHandler(String action,String fn,String ln,String mail,String number,String un,String pass){//used to register the user
        this.Case=action;
        this.mFirstname=fn;
        this.mLastname=ln;
        this.mEmail=mail;
        this.mPhonenumber=number;
        this.mUsername=un;
        this.mPassword=pass;
    }

    public String[] getObjects() {
        return objects;
    }

    public String[] getState() {
        return state;
    }
    String res = "";
    public void updateTable(Connection con,String table)
    {//con is used to link the actual connexion to the method in order to perform the actions
        //table is used to state in which table we want to effectuate the operations
        //this method is basically used to add register a user or to book a room
        try
        {
            Statement stmt  = con.createStatement();
            int rowcount= stmt.executeUpdate("show tables");
            if (table.equals("user")){

                rowcount = stmt.executeUpdate("INSERT INTO user(UFirstName,ULastName,UEmail,UPhoneNumber,UPassword,UserName) values( '"+mFirstname+"','"+mLastname+"','"+mEmail+"','"+mPhonenumber+"','"+mPassword+"','"+mUsername+"')");}
            else if(table.equals("rooms")){
                rowcount = stmt.executeUpdate("update rooms set RState='UNAVAILABLE',UserName='"+mUsername+"' WHERE RNumber='"+mRoomNumber+"'");
            }
            System.out.println("");
            System.out.println("Success - "+rowcount+" rows affected.");
        }
        catch(Exception err)
        {
            System.out.println("An error has occurred.");
            System.out.println("See full details below.");
            err.printStackTrace();
            this.exist=true;
            System.out.println("check it"+this.exist);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        System.out.println("attributing...");


    }

    @Override
    protected String doInBackground(String... params) {
            //this is the main method used to access the database and execute sql query whether it is directly from this method or from other method(linked using the con from this method)

        try {

            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("WE START");
            Connection con = DriverManager.getConnection(url, user, pass);
            System.out.println("DATABASE CONNEXION SUCCESS!!!!");
            ResultSet rs=null;
            String result = "";
            Statement st = con.createStatement();
            if (Case.equals("object")){//deprecated
                System.out.println("THIS METHOD IS DEPRECATED");
                rs = st.executeQuery("select RNumber from rooms");}
            if (Case.equals("state")){//deprecated
                rs = st.executeQuery("select RState from rooms");}
                System.out.println("THIS METHOD IS DEPRECATED");
            if(Case.equals("register")){
               // rs = st.executeQuery("select UserName from user where UserName='"+mUsername+"'");
                updateTable(con,"user");//we register a user then return a value to display in the terminal(for verification)

            }
            if(Case.equals("login")){//we take the username and the password given by the user and check if it exists
                rs=st.executeQuery("select UserName from user where UserName='"+mUsername+"' and UPassword='"+mPassword+"'");
            }
            if(Case.equals("booking")){//we take the state from the room table
                rs = st.executeQuery("select RState from rooms where RNumber='"+mRoomNumber+"'");
            }
            if(Case.equals("CanBook")){//we book the room
                updateTable(con,"rooms");
            }

            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                result += rs.getString(1).toString() + "\n";
            }
            res = result;
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            res = e.toString();
        }

        return res;
    }

    @Override
    protected void onPostExecute(String result) {
        //we separate the objects (room numbers) then store them in an array
        if (Case.equals("object")){
            System.out.println("THIS METHOD IS DEPRECATED");
            this.objects=result.split("\n");
            System.out.println(Arrays.toString(objects));

        }
        if (Case.equals("state")){
            //we separate the states then store them in an array
            System.out.println("THIS METHOD IS DEPRECATED");
            this.state=result.split("\n");
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
            this.resultlog=result;
            System.out.println(resultlog.length()>0);
            this.passlog=resultlog.length()>0;//it means that a user has been found when searching the username and the password given by the user
            System.out.println(this.passlog);//we print it for verification
        }
        if(Case.equals(("booking"))){
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


}

