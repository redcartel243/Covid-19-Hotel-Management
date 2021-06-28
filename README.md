# Covid-19-Hotel-Management

## 1App Name: CHM (Covid 19Hotel Management)

## 2 Background

2019 presented many challenges but what stood out was the Coronavirus better known as covid-19. The covid-19 pandemic made everyone rethink their approach to daily life in particular how they started running their companies and businesses. This pandemic is affecting many areas including the economy of countries thus governments hand in hand with multiple companies came up with the appropriate solutions. Businesses such as hotels came up with different solutions depending on country&#39;s regulations issued due to the covid-19 pandemic. Managing institutions such as hotels is of high importance according to different countries in order to limit the spread of the virus.

1.
  1.

1.
  1.

###


###


### 3. Purpose and framework

This software has been developed to demonstrate a solution for different hotels to manage their business during the covid-19 pandemic which intern reduces the number of potential infection in the country.

Managing a hotel during the covid-19 pandemic can be a difficult task, reason being we need to manage the main functions of a normal hotel such as room management, room reservation, assigning the rooms to a cleaner and even the date of the reservation and the departure and now during the pandemic we need to take in to consideration some other aspects such as the potential infected rooms, the countries that must be banned form booking a hotel room or not. Just to mention a few items there&#39;s a lot more that must be taking into account.

##


# 3Main functions (Layout Design)

The main pages, main functions and its related technologies are listed in the following table. The layout design diagrams are listed in **Layout Design** and its corresponding number is also listed in the following table.

Table 1 Main pages, functions and related technologies

| Main Page | Main Function | Related Technology | Related Diagram |
| --- | --- | --- | --- |
| Home Page |
1. Displays Covid 19 info button
2. Login button, registration button and image button that shows contact menu.
 | Activity Life Cycle;Activity Jump;Layout Design;Control (ImageView);View Pager auto slideImage Button and Menu | Diagram 1 |
| Register Page |
1. Allows the user to input their info to register to the app
2. Displays Confirmation Button
 | Data Transfer;Activity Life Cycle;EditText;CheckBox;
 | Diagram 2 |
| Login Page |
1. Allows the user to log into the app
2. Displays confirmation button
3. Displays Login button
 | Activity Life Cycle;Activity Jump;Layout Design;Control (Button,TextView); | Diagram 3 |
| Room Booking Page |
1. Image button displays a logout function
2. Two drop down menus
3. Displays location
4. Shows rooms available for booking on a list view
 | GPS Location;Adapter (Spinner);Adapter (List View);Activity Life Cycle;Activity Jump;Layout Design;Control (TextView,ImageView) | Diagram 4 |
| Confirmation Page | (12) Allows the user to confirm the room they are booking(13) Displays Confirm and Cancel Buttons | Fragment displayed on a ViewPager | Diagram 5 |

|
Covid 19 infoMain Page | (14) image button containing different functions that enable the user to navigate through various info that raises covid-19 awareness | Control (TextView,ImageView);Activity Life Cycle;Layout Design;ImageView | Diagram 6 |
| --- | --- | --- | --- |
| Symptoms and diagnosis Page | (15) Image button containing different functions that enable the user to navigate through various info that raises covid-19 awareness. And a short video. | Control (TextView,ImageView);Activity Life Cycle;Layout Design;VideoView | Diagram 7 |
| RepresentationPage | (16)Allows the user to view how covid-19 affects different parts of the body(17) Image view button |

Control (imageView Button);Activity Life Cycle;Activity Jump;PhotoView LibraryLayout Design; | Diagram 8 |
| Emergency Dialogue | (18) Displays a clickable image that allows the user to make an emergency call(19) Displays an Ok button |

Dialogue FragmentLayout Design;Controls (TextView,ImageView, Button); | Diagram 9 |

##


## 4Main functions(layout design)

Diagram 1

![](RackMultipart20210628-4-6hg6t_html_a8f5ca3f044e4d43.jpg)

Diagram 2

![](RackMultipart20210628-4-6hg6t_html_3b5b5151484bacc9.jpg)

Diagram 3

![](RackMultipart20210628-4-6hg6t_html_54e8b4d306c2bdbd.jpg)

Diagram 4

![](RackMultipart20210628-4-6hg6t_html_220cdbf8b5f0a4a6.jpg)

Diagram 5

![](RackMultipart20210628-4-6hg6t_html_768d311786a9b5ff.jpg)

Diagram 6

![](RackMultipart20210628-4-6hg6t_html_a1509ab8550491f3.jpg)

Diagram 7

![](RackMultipart20210628-4-6hg6t_html_8cac42a08d8e5e77.jpg)

Diagram 8

![](RackMultipart20210628-4-6hg6t_html_517b8cf77373b9d2.jpg)

Diagram 9

![](RackMultipart20210628-4-6hg6t_html_8ff7e46ba66ce6c.jpg)

1. Compulsory functions

1. Room reservation and room booking

The application allows the user to book or reserve a room of his choice but since the covid-19 outbreak is upon us, hotels had to find a way to prevent the spread covid-19. The solution we found is the hotel room reservation or booking depends on the actual location of the user. We set some countries that are not be able to book a hotel on the app following the latest news about the covid-19 on the given country.

1. Covid-19 prevention info ( Educational feature)

The main purpose of this app is to allow the user to book or reserve a hotel room but since the covid-19 outbreak is an important topic to share, we have a feature in the app that raise awareness called covid-19 prevention info.

1. **Function realization(The related technologies)**

-Among the related technologies that we used to write this software and make it covide-19 related , we used the google APIin order to get the last known location of the user (the latitude and the longitude) the translate the longitude and the latitude to the corresponding country name(EX CN). The result will be used to determine whether the user is able to book a hotel room or not

Database handler:

Since our database app is not hosted on a cloud host, we created a link between the host machine and a virtual machine and used Maria DB to create the database on the virtual machine (sql)

The Java Database Connectivity (JDBC) is an application programming interface (API) for the programming language Java, which defines how a client may access a database. It is a Java-based data access technology used for Java database connectivity. It is part of the Java Standard Edition platform, from Oracle Corporation.

Here is an insight of the Database Handler:

![](RackMultipart20210628-4-6hg6t_html_49de8a5fd0699cdc.png)

![](RackMultipart20210628-4-6hg6t_html_b4a9d5474f5d78a4.png)

We made a java class that is able to handle any kind of transactions between the app and the database

An example of the class DatabaseHandlerOnline:

![](RackMultipart20210628-4-6hg6t_html_f0751e70fc43a58.png)

Below is our database:

![](RackMultipart20210628-4-6hg6t_html_b3f640ad2f8d9683.png)

Here&#39;s our php API

![](RackMultipart20210628-4-6hg6t_html_cdac6227c176e719.png)

