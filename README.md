# TrendingRepoList
The main concept of this project is to list trending repositories using Android MVVM architecture. 

This project builds using Android Architecture Components (View Model, Data Binding, Live Data, lifecycle) along with Room database and Dagger 2 dependency injection to build a robust application (less bug, support offline mode portrait mode and lanscape mode).

Room DataBase works well with LiveData and lifecycle to prevent memory leaks.

"Pull to Refresh" to fetch latest data has been implemented using SwipeRefreshLayout.

To filter a particular repository from a list will have a option called search button. Right now I did filter by author and language.

In a repository list there is a attribute language , To differentiate language in left side set a round dot with different colors each color set it from api response(Hex code value).

For first time alone internet is required and it will push data into database(local), for next frequent access without internet able to access content from local.
