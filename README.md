# Spendings

Spendings is an Android native app designed to help users track their monthly expenditures. It serves as a simple application to demonstrate reading and writing to a room database.

## Screenshots

- The first screen of the app displays all the recorded months with spendings.
![Months](https://raw.githubusercontent.com/andreiflo94/spendings/main/screenshots/months_ss.png)

- Clicking "Add" on the first screen navigates the user to the "Add Spending" screen.
![Add Spending Screen](https://raw.githubusercontent.com/andreiflo94/spendings/main/screenshots/add_spending_ss.png)

- When a month is selected, the user is presented with all the spending categories, along with the total sum spent and the month the spendings were made.
![Categories](https://raw.githubusercontent.com/andreiflo94/spendings/main/screenshots/categories_ss.png)

- Clicking a category displays all related spendings to the user.
![Spendings](https://raw.githubusercontent.com/andreiflo94/spendings/main/screenshots/spendings_ss.png)

## Technologies Used

- [Architecture](https://medium.com/@ami0275/mvvm-clean-architecture-pattern-in-android-with-use-cases-eff7edc2ef76#:~:text=MVVM%20(Model%2DView%2DViewModel,)%20and%20data%20(Model).)
- For dependency injection, I utilized [Hilt](https://developer.android.com/training/dependency-injection/hilt-android).
- [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) is used to observe data from the ViewModel in the presentation layer.
- I used [Jetpack Compose](https://developer.android.com/jetpack/compose) to write/compose the UI in the presentation layer.
- The architecture facilitates simple unit testing separation for the business logic by testing the UseCase classes. I employed the [Mockito](https://site.mockito.org/) library to mock results from the repositories used in the UseCase classes.
