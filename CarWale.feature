Feature: Carwale TestCase

Background: 
Given User opens the Chrome Browser
And Launch https://www.carwale.com/
And User maximise the browser
And User set the default implicit timeout

Scenario: Lowest KM Ran Car
When Select the City as Chennai
And click on Used
And Select budget min 8L and max 12L and click Search
And Select Cars with Photos under Only Show Cars With
And Select Manufacturer as Hyundai --> Creta
And Select Fuel Type as Petrol
And Select Best Match as KM: Low to High
Then Validate the Cars are listed with KMs Low to High 
And Add the least KM ran car to Wishlist
And Go to Wishlist and click on More Details
Then Print all the details under Overview in the same way as displayed in application 
And Close the browser