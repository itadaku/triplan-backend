CREATE TABLE IF NOT EXISTS users (
  id serial primary key not null,
  name text not null,
  email text not null,
  password text not null,
  line_station_id integer not null,
  age integer not null,
  gender integer not null,
  token text not null,
  created_at timestamp default current_timestamp not null,
  updated_at timestamp default current_timestamp not null
);

create trigger update_tri before update on users for each row execute procedure set_update_time();