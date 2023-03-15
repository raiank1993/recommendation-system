# recommendation-system
Recommendation System of Resturant 

Curl to get the recommendation -- 


Curl --

curl --location 'http://localhost:8080/user/recommendation'
--header 'Content-Type: application/json'
--data '{ "user": { "cuisines": [ { "type": "1", "noOfOrders": "2" }, { "type": "2", "noOfOrders": "10" }, { "type": "3", "noOfOrders": "5" } ], "costBracket": [ { "type": "1", "noOfOrders": "2" }, { "type": "2", "noOfOrders": "4" }, { "type": "4", "noOfOrders": "10" }, { "type": "3", "noOfOrders": "9" } ] }, "availableRestaurants": [ { "restaurantId": "1", "cuisine": "NORTH_INDIAN", "costBracket": 1, "rating": 2, "recommended": false, "onboardedTime": "2013-02-27-13:57:21" }, { "restaurantId": "2", "cuisine": "SOUTH_INDIAN", "costBracket": 1, "rating": 2, "recommended": false, "onboardedTime": "2013-02-27-13:57:21" }, { "restaurantId": "3", "cuisine": "NORTH_INDIAN", "costBracket": 4, "rating": 4.5, "recommended": false, "onboardedTime": "2013-02-27-13:57:21" }, { "restaurantId": "4", "cuisine": "CHINESE", "costBracket": 5, "rating": 4, "recommended": false, "onboardedTime": "2013-02-27-13:57:21" }, { "restaurantId": "5", "cuisine": "NORTH_INDIAN", "costBracket": 1, "rating": 2, "recommended": false, "onboardedTime": "2023-03-13-13:57:21" }, { "restaurantId": "6", "cuisine": "NORTH_INDIAN", "costBracket": 4, "rating": 2, "recommended": false, "onboardedTime": "2013-02-27-13:57:21" }, { "restaurantId": "7", "cuisine": "SOUTH_INDIAN", "costBracket": 1, "rating": 1, "recommended": false, "onboardedTime": "2013-02-27-13:57:21" },{ "restaurantId": "8", "cuisine": "CHINESE", "costBracket": 4, "rating": 5, "recommended": false, "onboardedTime": "2013-02-27-13:57:21" }, { "restaurantId": "9", "cuisine": "NORTH_INDIAN", "costBracket": 4, "rating": 3, "recommended": true, "onboardedTime": "2013-02-27-13:57:21" }, { "restaurantId": "10", "cuisine": "NORTH_INDIAN", "costBracket": 3, "rating": 4.5, "recommended": false, "onboardedTime": "2013-01-27-13:57:21" } ] }'
