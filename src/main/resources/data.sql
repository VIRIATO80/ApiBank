insert into accounts (account_iban, total_balance) 
values
('E1234567', 1000),
('E1234200', 2300),
('E1234204', 2500);


insert into transactions (reference, account_iban, date, amount, fee, description)
values 
 ('12345A','E1234567', parsedatetime('17-09-2019 18:47', 'dd-MM-yyyy hh:mm'), 193.1, 3.1, 'Pago restaurante'),
 ('12346A','E1234567', parsedatetime('20-09-2019 13:47', 'dd-MM-yyyy hh:mm'), 50, 1, 'Gasolinera'),
 ('12347A','E1234200', parsedatetime('23-09-2019 20:47', 'dd-MM-yyyy hh:mm'), 193.1, 3.1, 'Burger King');


