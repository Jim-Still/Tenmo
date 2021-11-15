# Tech Elevator Module 2 Capstone - TEnmo

This was a pair project focused on building a Venmo clone with a randomly assigned partner. We used the driver/navigator principles of pair programming & worked our way through multiple classes to build both the client & RESTful server sides of the app while utilizing Java, SQL, & SpringBoot.


## Technology Used

Java (IntelliJ)
SQL/PostegreSQL (dbVIsualizer)
SpringBoot

##Lessons Learned

We tackled this one in vertical slices, which I think was helpful for visualizing the flow of data whole tackling a project like this for the first time. We started with the getBalance() method, and worked our way from the database to the command line app all the way through on that one method. Once we completed that, it was easier to see how all the individual layers worked together to put the contents of the database on the user's screen.


### A user can:

1. A user of the system can register himself/herself with a username and password.
   1. A new registered user starts with an initial balance of 1,000 TE Bucks.
2. A user of the system can log in using thier registered username and password.
   1. Logging in returns an Authentication Token. This token is included with all my subsequent interactions with the system outside of registering and logging in.
3. An authenticated user of the system can see their Account Balance.
4. A authenticated user of the system can *send* a transfer of a specific amount of TE Bucks to a registered user.
   1. User can choose from a list of users to send TE Bucks to.
   2. A transfer includes the User IDs of the from and to users and the amount of TE Bucks.
   3. The receiver's account balance is increased by the amount of the transfer.
   4. The sender's account balance is decreased by the amount of the transfer.
   5. A user can't send more TE Bucks than they have in their account.
   6. A Sending Transfer has an initial status of "approve."
5. An authenticated user of the system can see all transfers they have sent or received.
6. An authenticated user of the system can retrieve the details of any transfer based upon the transfer ID.



## Sample screens

### Current balance
```
Your current account balance is: $9999.99
```

### Send TE Bucks
```
-------------------------------------------
Users
ID          Name
-------------------------------------------
313         Bernice
54          Larry
---------

Enter ID of user you are sending to (0 to cancel):
Enter amount:
```

### View transfers
```
-------------------------------------------
Transfers
ID          From/To                 Amount
-------------------------------------------
23          From: Bernice          $ 903.14
79          To:    Larry           $  12.55
---------
Please enter transfer ID to view details (0 to cancel): "
```

### Transfer details
```
--------------------------------------------
Transfer Details
--------------------------------------------
 Id: 23
 From: Bernice
 To: Me Myselfandi
 Type: Send
 Status: Approved
 Amount: $903.14
```

## Database schema

![Database schema](./img/database_schema.png)

### Users table

The `users` table stores the login information for users of the system.

| Field           | Description                                                                    |
| --------------- | ------------------------------------------------------------------------------ |
| `user_id`       | Unique identifier of the user                                                  |
| `username`      | String that identifies the name of the user; used as part of the login process |
| `password_hash` | Hashed version of the user's password                                          |

### Accounts table

The `accounts` table stores the accounts of users in the system.

| Field           | Description                                                        |
| --------------- | ------------------------------------------------------------------ |
| `account_id`    | Unique identifier of the account                                   |
| `user_id`       | Foreign key to the `users` table; identifies user who owns account |
| `balance`       | The amount of TE bucks currently in the account                    |

### Transfer types table

The `transfer_types` table stores the types of transfers that are possible.

| Field                | Description                             |
| -------------------- | --------------------------------------- |
| `transfer_type_id`   | Unique identifier of the transfer type  |
| `transfer_type_desc` | String description of the transfer type |

There are two types of transfers:

| `transfer_type_id` | `transfer_type_desc` | Purpose                                                                |
| ------------------ | -------------------- | ---------------------------------------------------------------------- |
| 1                  | Request              | Identifies transfer where a user requests money from another user      |
| 2                  | Send                 | Identifies transfer where a user sends money to another user           |

### Transfer statuses table

The `transfer_statuses` table stores the statuses of transfers that are possible.

| Field                  | Description                               |
| ---------------------- | ----------------------------------------- |
| `transfer_status_id`   | Unique identifier of the transfer status  |
| `transfer_status_desc` | String description of the transfer status |

There are three statuses of transfers:

| `transfer_status_id` | `transfer_status_desc` |Purpose                                                                                 |
| -------------------- | -------------------- | ---------------------------------------------------------------------------------------  |
| 1                    | Pending                | Identifies transfer that hasn't occurred yet and requires approval from the other user |
| 2                    | Approved               | Identifies transfer that has been approved and occurred                                |
| 3                    | Rejected               | Identifies transfer that wasn't approved                                               |

### Transfers table

The `transfer` table stores the transfers of TE bucks.

| Field                | Description                                                                                     |
| -------------------- | ----------------------------------------------------------------------------------------------- |
| `transfer_id`        | Unique identifier of the transfer                                                               |
| `transfer_type_id`   | Foreign key to the `transfer_types` table; identifies type of transfer                          |
| `transfer_status_id` | Foreign key to the `transfer_statuses` table; identifies status of transfer                     |
| `account_from`       | Foreign key to the `accounts` table; identifies the account that the funds are being taken from |
| `account_to`         | Foreign key to the `accounts` table; identifies the account that the funds are going to         |
| `amount`             | Amount of the transfer                                                                          |
