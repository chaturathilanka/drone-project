CREATE TABLE drones (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  serial_number VARCHAR(100),
  model VARCHAR(100),
  weight_limit INTEGER,
  battery_capacity INTEGER,
  state VARCHAR(100)
);

CREATE TABLE medications (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  weight DOUBLE,
  code VARCHAR(100),
  image VARCHAR(255)
);