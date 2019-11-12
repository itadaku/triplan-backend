CREATE TABLE IF NOT EXISTS plan_purposes (
  id serial primary key not null,
  plan_id integer not null,
  purpose_id integer not null,
  created_at timestamp default current_timestamp not null,
  updated_at timestamp default current_timestamp not null
);

create trigger update_tri before update on plan_purposes for each row execute procedure set_update_time();