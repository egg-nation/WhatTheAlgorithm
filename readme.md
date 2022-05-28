**WhatTheAlgorithm**

WhatTheAlgorithm is an educative platform for time complexity comparison between algorithms which solve the same problem. To better visualise how certain algorithms are performing data visualisation was added through line charts which show the differences time and input length wise for each run that was performed.
The runs are stored in a database for each particular user so that the platform users can easily check past runs and form an idea as to what algorithm is optimal in certain cases.

**Project architecture**
- Client
- Server
    - Model
      - User: id, first name, last name, username (mandatory), e-mail address (mandatory), password hash (mandatory)
      - Algorithm history: id, algorithm name, method name, input length, execution time, user id
    - View
        - Menu
            - Main Menu: login, register, exit
            - Authenticated menu: 
              - sort array (view chart with previously performed sorting runs, start sorting to sort a new array which can be automatically generated for a given length using the automatic input command or a manually given array using the manual input command)
              - search array (view chart with previously performed searching runs, start searching to search in a new array which can be automatically generated for a given length and a random element from the array using the automatic input command or a manually given array and element using the manual input command)
        - Input
          - For menu:
            - Input validator: checkers for user input: username, password, email
            - Input getters: get username, get password etc.
            - Input hashing: sha 256 for password security
          - For the algorithms:
              - Sorting: getting and preparing the format for the sorting algorithms either manually or automatically
              - Searching: getting and preparing the format for the searching algorithm either manually or automatically
        - Messages: success messages, error messages, input fields, commands for the menus etc.
    - Service
        - User service: create a new user, login into an existent user account or delete an existent user account
        - Algorithm history service: add new entry for a run, view chart with runs for an algorithm, get history for an existent user account or delete the history for an existent user account
    - Repository
        - Session: establish the database connection
        - Repositories: perform the operations on the database tables 
          - User repository
          - Algorithm history repository
    - Visualisation
      - Line chart visualisation for the custom given input
    - Algorithms
    - Algorithm timer: times the execution of a given algorithm
    - Categories
    - Sorting
      - Start sorting: start all the sorting algorithms, times their execution and adds the run to the algorithm history table
      - Methods: bubble sort, cocktail sort, counting sort, heap sort, insertion sort, pigeonhole sort, quick sort, selection sort
    - Searching
      - Start searching: start all the searching algorithms, times their execution and adds the run to the algorithm history table
      - Methods: binary search, interpolation search, linear search, ternary search

- Unit testing:
    - Services
    - Algorithms
