Scenario: User searches for a single step
 
Given user is on Home page
When user opens Registration link
And user type login test10
And user type password testest
And user retype password testest
And user type email testest@gmail.com
And user click register
Then Registration page is shown
