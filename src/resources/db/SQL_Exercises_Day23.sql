SELECT * FROM customers
WHERE Country != 'USA';

SELECT * FROM customers
WHERE Country = 'Germany';

SELECT * FROM employees
WHERE Title = 'Sales Support Agent';

SELECT DISTINCT BillingCountry 
FROM invoices
ORDER BY BillingCountry ASC;

SELECT * FROM tracks t;

SELECT * FROM albums a
JOIN artists a2 
ON a.ArtistId = a2.ArtistId;

SELECT * FROM tracks t 
ORDER BY UnitPrice DESC;

DROP VIEW IF EXISTS track_view;

CREATE VIEW track_view
AS
SELECT a.Title Album, 
a2.Name Artist, 
t.Name SongName,
g.Name Genre 
FROM tracks t 
JOIN albums a 
ON t.AlbumId = a.AlbumId 
JOIN artists a2 
ON a.ArtistId = a2.ArtistId
JOIN genres g 
ON t.GenreId = g.GenreId;


