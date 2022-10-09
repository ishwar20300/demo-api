# finolane-service

GWI Loan API
Delivered by Ayansh Dash (25 Mar 2021)



REF LIST
# PDF
https://zetcode.com/springboot/servepdf/


SQL IF CONDITION
https://stackoverflow.com/questions/36479844/sql-for-active-and-inactive-status

# http://localhost:8080/api/v1/export-excel

findAll(Sort.by("email").ascending());
import org.springframework.data.domain.Sort;


SELECT * FROM loan_request l WHERE l.request_status != 'Not Submitted' AND l.created_date >= '2021-09-03' AND l.created_date <= '2021-09-04'