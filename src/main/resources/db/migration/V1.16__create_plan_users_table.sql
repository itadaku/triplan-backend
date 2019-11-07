CREATE TABLE IF NOT EXISTS plan_users (
  id serial primary key not null,
  user_id integer not null,
  plan_id integer not null,
  is_progress boolean,
  now_progress_plan_element_id integer not null,
  created_at timestamp default current_timestamp not null,
  updated_at timestamp default current_timestamp not null
);

create trigger update_tri before update on plan_users for each row execute procedure set_update_time();