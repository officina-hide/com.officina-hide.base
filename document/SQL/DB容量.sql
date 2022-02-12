SELECT
    table_schema, sum(data_length+index_length) /1024 /1024 AS MB
FROM
    information_schema.tables
GROUP BY
    table_schema
ORDER BY
    sum(data_length+index_length) DESC;
    
SELECT  
    table_name, table_rows AS tbl_rows,
    avg_row_length AS avelen,  
    floor((data_length+index_length)/1024) AS all_kb,
    floor((data_length)/1024) AS data_kb,
    floor((index_length)/1024) AS index_kb,
	 engine
FROM 
    information_schema.tables  
WHERE
--    table_schema='FDBASE'
--    table_schema='MYPAGE'  
--    table_schema='EDUCATE'  
    table_schema='ACCOUNT'  
ORDER BY
    (data_length+index_length) DESC;

