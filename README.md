# HOW TO TEST

## Start the test server

1. `cd` to `closest-zero` folder
2. Run `./gradlew bootRun`

## Send post request to the rest api

```
curl --location --request POST 'http://localhost:8080/api/closest_zero' \
--header 'Content-Type: application/json' \
--data-raw '{
    "list": [2,2,-3,0,2],
    "n": 2
}'
```

## Run JUnit test

`./gradlew test --tests "com.daitum.hiring.test.closestzero.ClosestZeroControllerTests"`

`./gradlew test --tests "com.daitum.hiring.test.closestzero.ClosestZeroServiceTests"`
