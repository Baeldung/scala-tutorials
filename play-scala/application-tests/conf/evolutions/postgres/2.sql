-- !Ups

INSERT INTO arrival(id, origin, destination, plane) VALUES
(1, 'Istanbul', 'Heathrow', 'A320'),
(2, 'Charles de Gaulle', 'Gatwick', 'DC10'),
(3, 'Gatwick', 'Lisbon', 'Concorde'),
(4, 'Dublin', 'Athens', 'B747'),
(5, 'Oslo', 'Copenhagen', 'Bombardier Q-400');

-- !Downs

DELETE FROM arrival;