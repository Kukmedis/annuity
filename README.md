## How to run

`mvn clean spring-boot:run` in the home directory will run the service in seconds. 
The service will start running on port 8080.

## How to use

Use GET `/annuity-plan` with query parameters
* `loanAmount` - String representation of javax.money.MoneraryAmount  
* `nominalRate` - Double
* `durationInMonths` - Integer
* `startDate` - Date as `yyyy-mm-dd`

e.g. `curl -X GET 'http://localhost:8080/annuity-plan?loanAmount=EUR%205000&nominalRate=5&durationInMonths=3&startDate=2018-01-01'`
will return
```{
     "schedule": [
         {
             "borrowerPayment": {
                 "amount": 1680.57,
                 "currency": "EUR"
             },
             "principal": {
                 "amount": 1659.74,
                 "currency": "EUR"
             },
             "interest": {
                 "amount": 20.83,
                 "currency": "EUR"
             },
             "initialOutstandingPrincipal": {
                 "amount": 5000.00,
                 "currency": "EUR"
             },
             "remainingOutstandingPrincipal": {
                 "amount": 3340.26,
                 "currency": "EUR"
             },
             "date": "2018-01-01"
         },
         {
             "borrowerPayment": {
                 "amount": 1680.57,
                 "currency": "EUR"
             },
             "principal": {
                 "amount": 1666.65,
                 "currency": "EUR"
             },
             "interest": {
                 "amount": 13.92,
                 "currency": "EUR"
             },
             "initialOutstandingPrincipal": {
                 "amount": 3340.26,
                 "currency": "EUR"
             },
             "remainingOutstandingPrincipal": {
                 "amount": 1673.61,
                 "currency": "EUR"
             },
             "date": "2018-02-01"
         },
         {
             "borrowerPayment": {
                 "amount": 1680.58,
                 "currency": "EUR"
             },
             "principal": {
                 "amount": 1673.61,
                 "currency": "EUR"
             },
             "interest": {
                 "amount": 6.97,
                 "currency": "EUR"
             },
             "initialOutstandingPrincipal": {
                 "amount": 1673.61,
                 "currency": "EUR"
             },
             "remainingOutstandingPrincipal": {
                 "amount": 0.00,
                 "currency": "EUR"
             },
             "date": "2018-03-01"
         }
     ]
 }