INSERT INTO genres 
(Name)
VALUES ('MegaMix');

INSERT INTO artists 
(Name)
VALUES ('Mike SuperCool');

INSERT INTO albums 
(Title, ArtistId)
VALUES ('First ever Mega Album', 278);

INSERT INTO tracks 
(Name, AlbumId, MediaTypeId, GenreId, Composer, Milliseconds, Bytes, UnitPrice)
VALUES ('Ima daaancing to the miiix', 349, 1, 26, 'Mike SuperCool', 284800, 4333779, 6.99);

INSERT INTO tracks 
(Name, AlbumId, MediaTypeId, GenreId, Composer, Milliseconds, Bytes, UnitPrice)
VALUES ('Ima chilling to the miiix', 349, 1, 26, 'Mike SuperCool', 384800, 5333779, 6.99);

UPDATE tracks
SET GenreId = 25
WHERE TrackId = 3506;

DELETE FROM tracks 
WHERE TrackId = 3506;

SELECT t.TrackId, t.Name, g.Name, a.Title, a2.Name FROM tracks t 
JOIN genres g 
ON t.GenreId = g.GenreId
JOIN albums a 
ON t.AlbumId = a.AlbumId
JOIN artists a2 
ON a.ArtistId = a2.ArtistId
WHERE UnitPrice = 6.99;

