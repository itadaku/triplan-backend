CREATE TABLE IF NOT EXISTS plan_locations (
  id serial primary key not null,
  plan_id integer not null,
  location_id integer not null,
  created_at timestamp default current_timestamp not null,
  updated_at timestamp default current_timestamp not null
);

create trigger update_tri before update on plan_locations for each row execute procedure set_update_time();