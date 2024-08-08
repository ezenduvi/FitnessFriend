# my-fitness-friend

Team members:		Kevin Derksen, Victor Ezendu, Ana Ignat, Weixi Sun, Andrea Unrau

Packages and major source code files:	The packages consist of Application, Business, Objects, Persistence, Presentation. The unit tests and test suite are in the package com.example.myfitnessfriend. The app is designed using 3-layer architecture. For the domain-specific objects, there is Day class which consists of 4 different Meals objects plus a Meals object for water. The Meals class consists of an arraylist of type Food to hold all the foods consumed in one meal. The Food class is for the creation of individual food objects to populate the database. The User class creates a new user that has a name, email, and calorie & water goals. Future iterations will allow a user profile picture. There are two enum classes: MealType and Unit which is the serving unit for the food objects. NEWLY ADDED FOR ITERATION 2: A services class was added for the purposes of dependency injection. A new class Calculate to handle calculations was added to the business layer, as was a new AccessExercise class. One new domain-specific object was created, Exercise. As required, HSQLDB has been utilized alongside the stub database. NEWLY ADDED FOR ITERATION 3: Acceptance and integration tests were added to this iteration, along with a new Diary object class and AccessDiary & AccessDay business classes. The test folders and classes were restructured as required.

Github private repo:	https://github.com/anaignat/my-fitness-friend

Developer log:	The developer log was maintained in a shared Google Sheets file.

Overview of the major implemented features:	By clicking on the Add Food button corresponding to a particular meal, a user is able to select foods from a stub database and add them as a food eaten for breakfast, lunch, dinner or snack. They can also click Add Water to enter in how many cups of water they've drank. The number of servings is currently hardcoded, but future iterations will allow the user to change the number of servings. After adding a food, that food is visible under the corresponding meal and a sum of calories consumed in that meal is shown and updated as foods are added. A running tally can also be seen at the top of the screen where a calculation is done for the number of calories remaining to be consumed that day. When clicking on the View Daily Summary button, the grams of fat, carbs and protein consumed is shown for the current day. The proportion of calories consumed in fat, carbs and protein is also shown as a pie chart. Clicking on View Profile will allow the user to enter a goal for the daily maximum number of calories they want to consume, and a goal for daily water consumption in number of cups. Clicking on View Weekly Summary shows a bar chart of the calories consumed per day for the current week. For this iteration, the graph is showing hard-coded values. A future iteration will show actual calories consumed. NEWLY ADDED FOR ITERATION 2: The user now has the ability to select exercises from a list to save it as well as select the duration they performed the exercise for. They can also see a weekly summary in the form of a bar chart of how many calories they've burned each day of the current week. As of this iteration, there are numbers hardcoded into the bar chart. A future iteration will have the bar chart reflecting the selected exercises. NEWLY ADDED FOR ITERATION 3: The Exercise Summary button was moved to the Exercise page and on the main page there is a new button where the user can enter a reflection about their day.

Issues not resolved this iteration:
1) Calorie goal changes are not reflected in the daily view interface, only in the profile.
2) After adding some foods, then going to either of the summary pages or view profile page and then returning to the home screen, the foods added are no longer visible.
3) Calorie amounts in the calculation in the toolbar are not persisting as they should be.

The app has been solely tested in the Android Studio emulator using the Nexus 7 API 30 device.

Credits to outside sources:	MPAndroidChart (Android chart library), and nutritional information obtained from https://www.kaggle.com/niharika41298/nutrition-details-for-most-common-foods?select=nutrients_csvfile.csv
