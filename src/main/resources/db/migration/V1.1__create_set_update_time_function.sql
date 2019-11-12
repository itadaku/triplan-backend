drop function if exists set_update_time();
create function set_update_time() returns opaque as '
  begin
    new.updated_at := ''now'';
    return new;
  end;
' language 'plpgsql';