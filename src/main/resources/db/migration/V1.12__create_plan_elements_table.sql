CREATE TABLE IF NOT EXISTS plan_elements (
  id serial primary key not null,
  plan_id integer not null,
  element_id integer not null,
  seq integer not null,
  from_data timestamp not null,
  to_data timestamp not null,
  created_at timestamp default current_timestamp not null,
  updated_at timestamp default current_timestamp not null
);

create trigger update_tri before update on plan_elements for each row execute procedure set_update_time();