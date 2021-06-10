# Tech stack

1. Feature modules with clean architecture.
2. MVI behaviour for presentation layer.
3. Coroutines + Flow for asynchronous operations
4. Paging library 3.0 for pagination with DIffUtill
5. ViewBinding 
6. Retrofit 2.0
7. Navigation component
8. Gson for parsing
9. BuildSrc module for one place of dependencies
10. Kotlin gradle
11. Coil for loading images

# What is done:

1. Search by user name and display results in a list.
2. Pagination of results.
3. Implemented smooth design.
4. Implemented flexible architecture.
5. Implemented multi module architecture.
6. Added Detail page for users. On this page there is a link which opens the user in the web browser.
7. Implemented search bar.

# What can be improved for release/stable version:

1. First of all, as there is used GitHub API we don't have a lot of tries for each type of request. To improve that we should add Oauth 2.0 protocol. 
  for the second option, we can use a secret-key to increase tries, but it is not safe. We can do it through the browser, or through the additional registration page in the app.
2. Should handle situations of absence of internet connection and provide appropriate messages.
3. Should add request retry with delay or whatever if there is some problem.
4. Create an additional module for the Registration flow on GitHub regarding item number 1.
5. Need to implement DB for offline mode.
6. Need to implement a runtime cache to not make a lot of requests.
7. Need to handle configuration changes for data.
8. Add Unit/Integration tests
9. Move all dependencies to buildSrc module instead of using them strictly in build files
10. Use detekt as code analyzer
11. Configure CI/CD
