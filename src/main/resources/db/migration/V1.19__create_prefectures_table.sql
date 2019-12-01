CREATE TABLE IF NOT EXISTS prefectures(
  id serial primary key not null,
  name text not null,
  agriculture integer not null,
  forestry integer not null,
  fishing_industry integer not null,
  population text not null,
  created_at timestamp default current_timestamp not null,
  updated_at timestamp default current_timestamp not null
);

create trigger update_tri before update on prefectures for each row execute procedure set_update_time();