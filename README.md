
# Spendings     

Android native app for keeping track of monthly spendings. It is a simple app I made to demonstrate reading and writing to a room database.  



## Screenshots

This is the first screen of the app, it displays all the months where you have spendings recorded.
![Months](https://raw.githubusercontent.com/andreiflo94/spendings/main/screenshots/months_ss.png)


When clicking "Add" in the first screen you are navigated to the "Add Spending" screen![Add Spending Screen](https://raw.githubusercontent.com/andreiflo94/spendings/main/screenshots/add_spending_ss.png)


When clicking a month the user is presented with all the categories of his spendings with the total sum spent and the month the spending were made.
![Categories](https://raw.githubusercontent.com/andreiflo94/spendings/main/screenshots/categories_ss.png)


By clicking a category it displays to the user all the spendings related.
![Spendings](https://raw.githubusercontent.com/andreiflo94/spendings/main/screenshots/spendings_ss.png)
## Tehnologies used

[Architecture](https://medium.com/@ami0275/mvvm-clean-architecture-pattern-in-android-with-use-cases-eff7edc2ef76#:~:text=MVVM%20(Model%2DView%2DViewModel,)%20and%20data%20(Model).)

For dependency injection, I used  [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)

[StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) is used for observing data from the ViewModel in the presentation layer.

I utilized [Jetpack compose](https://developer.android.com/jetpack/compose) to write/compose the UI in the presentation layer.

The architecture allows for simple unit testing separation for the business logic by testing the UseCase classes. I used the[Mockito](https://site.mockito.org/) library for mocking results of the repositories used in the UseCase classes.
