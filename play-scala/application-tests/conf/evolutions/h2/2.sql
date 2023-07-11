-- !Ups

INSERT INTO arrival(id, origin, destination, plane) VALUES
(1, 'Istanbul', 'Heathrow', 'A320'),
(2, 'Istanbul', 'Charles de Gaulle', 'B737'),
(3, 'Charles de Gaulle', 'Gatwick', 'DC10'),
(4, 'Gatwick', 'Lisbon', 'Concorde'),
(5, 'Dublin', 'Athens', 'B747'),
(6, 'Oslo', 'Copenhagen', 'Bombardier Q-400');

-- !Downs

DELETE FROM arrival;