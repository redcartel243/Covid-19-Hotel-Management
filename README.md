# Covid-19-Hotel-Management

## 1App Name: CHM (Covid 19Hotel Management)

## 2 Background

2019 presented many challenges but what stood out was the Coronavirus better known as covid-19. The covid-19 pandemic made everyone rethink their approach to daily life in particular how they started running their companies and businesses. This pandemic is affecting many areas including the economy of countries thus governments hand in hand with multiple companies came up with the appropriate solutions. Businesses such as hotels came up with different solutions depending on country&#39;s regulations issued due to the covid-19 pandemic. Managing institutions such as hotels is of high importance according to different countries in order to limit the spread of the virus.


### 3. Purpose and framework

This software has been developed to demonstrate a solution for different hotels to manage their business during the covid-19 pandemic which intern reduces the number of potential infection in the country.

Managing a hotel during the covid-19 pandemic can be a difficult task, reason being we need to manage the main functions of a normal hotel such as room management, room reservation, assigning the rooms to a cleaner and even the date of the reservation and the departure and now during the pandemic we need to take in to consideration some other aspects such as the potential infected rooms, the countries that must be banned form booking a hotel room or not. Just to mention a few items there&#39;s a lot more that must be taking into account.

##


# 1. Main functions (Layout Design)
![1](https://user-images.githubusercontent.com/61031416/123671274-a91b2f80-d870-11eb-9cb9-7753f2b7c7e8.JPG)
![2](https://user-images.githubusercontent.com/61031416/123671280-aa4c5c80-d870-11eb-9548-c6363d059ef4.JPG)
![3](https://user-images.githubusercontent.com/61031416/123671282-aae4f300-d870-11eb-91b3-06f239955688.JPG)
![4](https://user-images.githubusercontent.com/61031416/123671287-ac162000-d870-11eb-8428-5ce6a7593930.JPG)

## 2.Main functions(layout design)

Diagram 1

![5](https://user-images.githubusercontent.com/61031416/123671289-acaeb680-d870-11eb-9a03-c75bc0e912fb.JPG)

Diagram 2

![6](https://user-images.githubusercontent.com/61031416/123671291-ad474d00-d870-11eb-9fa1-a1fe0fb93439.JPG)

Diagram 3
![7](https://user-images.githubusercontent.com/61031416/123671293-addfe380-d870-11eb-97dc-91ea71bc6c1f.JPG)

Diagram 4
![8](https://user-images.githubusercontent.com/61031416/123671296-addfe380-d870-11eb-9633-3b51cc4564e6.JPG)

Diagram 5

![Picture1](https://user-images.githubusercontent.com/61031416/123671299-ae787a00-d870-11eb-8f90-99e7015a8c2a.png)

Diagram 6

![Picture2](https://user-images.githubusercontent.com/61031416/123671327-b6d0b500-d870-11eb-9613-9dfb259304cf.png)

Diagram 7

![Picture3](https://user-images.githubusercontent.com/61031416/123671348-be905980-d870-11eb-8d9f-be3294bdac5a.png)

Diagram 8

![Picture4](https://user-images.githubusercontent.com/61031416/123671173-90ab1500-d870-11eb-8091-8c277615e347.png)

Diagram 9
![Picture5](https://user-images.githubusercontent.com/61031416/123671219-9c96d700-d870-11eb-9569-210a43083e2a.png)

1. Compulsory functions

1. Room reservation and room booking

The application allows the user to book or reserve a room of his choice but since the covid-19 outbreak is upon us, hotels had to find a way to prevent the spread covid-19. The solution we found is the hotel room reservation or booking depends on the actual location of the user. We set some countries that are not be able to book a hotel on the app following the latest news about the covid-19 on the given country.

1. Covid-19 prevention info ( Educational feature)

The main purpose of this app is to allow the user to book or reserve a hotel room but since the covid-19 outbreak is an important topic to share, we have a feature in the app that raise awareness called covid-19 prevention info.

1. **Function realization(The related technologies)**

-Among the related technologies that we used to write this software and make it covide-19 related , we used the google APIin order to get the last known location of the user (the latitude and the longitude) the translate the longitude and the latitude to the corresponding country name(EX CN). The result will be used to determine whether the user is able to book a hotel room or not

Database handler:


We made a java class that is able to handle any kind of transactions between the app and the database

An example of the class DatabaseHandlerOnline:

![Picture8](https://user-images.githubusercontent.com/61031416/123671255-a587a880-d870-11eb-94f6-97d977f08cd2.png)

Below is our database:
![Picture9](https://user-images.githubusercontent.com/61031416/123671266-a6b8d580-d870-11eb-8e52-3b4606ceb03d.png)


Here&#39;s our php API

![Picture10](https://user-images.githubusercontent.com/61031416/123671271-a8829900-d870-11eb-8b13-01a73d9dabd3.png)

