#  Assignment 5: Crafting with compose

[source](https://github.com/rael346/cs-4520-assignment-5)

## Project Structure
- `api`: contains the services for getting data from the API
    - `RetrofitInstance`: initializing the services as an singleton
- `data`: contains the room database DAO, repository, and related workers
- `model`: dataclasses for the products
- `ui`: contains the compose screen and their respective view models

## Implementation
- In `OfflineProductsRepository`
    - Check if the data is offline
    - If not, fetch the data from the API, else get the data from the local database
        - If the request from the API succeeded, sync with local database
- Pagination
    - Use button to increment/decrement the page number in the view model, and make request for the
      new page data based on the new current page
- Workers
  - `ProductsWorker` and `ProductsWorkerFactory` in `data` folder: mainly used for dependency injection
    when initializing the WorkManager
  - `WorkManager` is initialized in `ViewModelProvider` in `ui` folder
  - Worker is scheduled every hour when the user first load the page, if the user change the current
    page because of pagination, the old worker will be canceled and a new worker will be scheduled
    for that new current page

## Build
- Tested the app using Pixel 7 API 34