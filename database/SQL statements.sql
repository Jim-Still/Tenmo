
//Get user_id & balance from user_id
SELECT user_id, balance 
FROM accounts
WHERE user_id = ?



//Create a new transfer from 2 user_ids & transfer amount
INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount)
VALUES (2, 2, 
        (SELECT account_id from accounts
        WHERE user_id = 1001), 
        (SELECT account_id from accounts
        WHERE user_id = 1002), 
        100)


//Get tranfer

SELECT transfers.*, user FROM transfers WHERE transfer_id = ?

SELECT * FROM transfers WHERE transfer_id = 3002

//Get from user id from transfer_id
SELECT accounts.user_id AS from_user_id
FROM transfers
JOIN accounts on transfers.account_from = accounts.account_id
WHERE transfers.transfer_id = 3002


//Get to user id from transfer_id
SELECT accounts.user_id AS to_user_id
FROM transfers
JOIN accounts on transfers.account_to = accounts.account_id
WHERE transfers.transfer_id = 3002


//Get all tranfer info & 2 user ids

SELECT *,

(SELECT accounts.user_id
FROM transfers
JOIN accounts on transfers.account_from = accounts.account_id
WHERE transfers.transfer_id = 3002) AS from_user_id ,

(SELECT accounts.user_id 
FROM transfers
JOIN accounts on transfers.account_to = accounts.account_id
WHERE transfers.transfer_id = 3002) AS to_user_id,

(SELECT transfer_types.transfer_type_desc
FROM transfers
JOIN transfer_types ON transfer_types.transfer_type_id = transfers.transfer_type_id
WHERE transfers.transfer_id = 3002) AS transfer_type,

(SELECT transfer_statuses.transfer_status_desc
FROM transfers
JOIN transfer_statuses ON transfer_statuses.transfer_status_id = transfers.transfer_status_id
WHERE transfers.transfer_id = 3002) AS transfer_status

FROM transfers

WHERE transfer_id = 3002


//add transfer amount to user's balance


//subtract transfer amount to user's balance