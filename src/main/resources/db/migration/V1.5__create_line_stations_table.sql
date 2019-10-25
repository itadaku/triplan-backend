CREATE TABLE IF NOT EXISTS line_stations (
  id serial primary key not null,
  line_id integer not null,
  station_id integer not null,
  created_at timestamp default current_timestamp not null,
  updated_at timestamp default current_timestamp not null
);

create trigger update_tri before update on line_stations for each row execute procedure set_update_time();